package structs.tree;

import annotation.NeedsFixing;
import structs.Tree;


public class BTree<T extends Comparable<T>> implements Tree<T> {

    private final int t;

    private final int MAX_KEYS;

    private class BNode implements Node<T> {
        int n; // number of keys
        T[] keys;
        BNode[] children;
        boolean leaf;

        @SuppressWarnings("unchecked")
        BNode(boolean leaf) {
            this.leaf = leaf;
            this.keys = (T[]) new Comparable[MAX_KEYS];
            this.children = new BTree.BNode[2 * t];
            this.n = 0;
        }

        @NeedsFixing(reason = "Fix index out of bounds exceptions")
        void insertNonFull(T key) {
            int i = n - 1;

            if (leaf) {
                while (i >= 0 && keys[i].compareTo(key) > 0) {
                    keys[i + 1] = keys[i];
                    i--;
                }
                keys[i + 1] = key;
                n += 1;

            } else {
                while (i >= 0 && keys[i].compareTo(key) > 0) {
                    i--;
                }
                i++;

                if (children[i].n == MAX_KEYS) {
                    splitChild(i, children[i]);

                    if (keys[i].compareTo(key) < 0) {
                        i++;
                    }
                }
                children[i].insertNonFull(key);
            }
        }

        void splitChild(int i, BNode y) {
            BNode z = new BNode(y.leaf);
            z.n = t - 1;

            System.arraycopy(y.keys, t, z.keys, 0, t - 1);

            if (!y.leaf) {
                System.arraycopy(y.children, t, z.children, 0, t);
            }

            y.n = t - 1;

            for (int j = n; j >= i + 1; j--) {
                children[j + 1] = children[j];
            }
            children[i + 1] = z;

            for (int j = n - 1; j >= i; j--) {
                keys[j + 1] = keys[j];
            }
            keys[i] = y.keys[t - 1];

            n += 1;
        }

        int findKey(T key) {
            int index = 0;
            while (index < n && keys[index].compareTo(key) < 0) {
                index++;
            }
            return index;
        }

        void remove(T key) {
            int index = findKey(key);

            if (index < n && keys[index].compareTo(key) == 0) {
                if (leaf) {
                    removeFromLeaf(index);
                } else {
                    removeFromNonLeaf(index);
                }
            } else {
                if (leaf) {
                    return;
                }

                boolean flag = (index == n);

                if (children[index].n < t) {
                    fill(index);
                }

                if (flag && index > n) {
                    children[index - 1].remove(key);
                } else {
                    children[index].remove(key);
                }
            }
        }

        void removeFromLeaf(int index) {
            for (int i = index + 1; i < n; ++i) {
                keys[i - 1] = keys[i];
            }
            n--;
        }

        void removeFromNonLeaf(int index) {
            T key = keys[index];

            if (children[index].n >= t) {
                T pred = pred(index);
                keys[index] = pred;
                children[index].remove(pred);
            } else if (children[index + 1].n >= t) {
                T succ = succ(index);
                keys[index] = succ;
                children[index + 1].remove(succ);
            } else {
                merge(index);
                children[index].remove(key);
            }
        }

        T pred(int index) {
            BNode current = children[index];
            while (!current.leaf) {
                current = current.children[current.n];
            }
            return current.keys[current.n - 1];
        }

        T succ(int index) {
            BNode current = children[index + 1];
            while (!current.leaf) {
                current = current.children[0];
            }
            return current.keys[0];
        }

        void fill(int index) {
            if (index != 0 && children[index - 1].n >= t) {
                borrowFromPrev(index);
            } else if (index != n && children[index + 1].n >= t) {
                borrowFromNext(index);
            } else {
                if (index != n) {
                    merge(index);
                } else {
                    merge(index - 1);
                }
            }
        }

        void borrowFromPrev(int index) {
            BNode child = children[index];
            BNode sibling = children[index - 1];

            for (int i = child.n - 1; i >= 0; --i) {
                child.keys[i + 1] = child.keys[i];
            }

            if (!child.leaf) {
                for (int i = child.n; i >= 0; --i) {
                    child.children[i + 1] = child.children[i];
                }
            }

            child.keys[0] = keys[index - 1];

            if (!child.leaf) {
                child.children[0] = sibling.children[sibling.n];
            }

            keys[index - 1] = sibling.keys[sibling.n - 1];

            child.n += 1;
            sibling.n -= 1;
        }

        void borrowFromNext(int index) {
            BNode child = children[index];
            BNode sibling = children[index + 1];

            child.keys[child.n] = keys[index];

            if (!child.leaf) {
                child.children[child.n + 1] = sibling.children[0];
            }

            keys[index] = sibling.keys[0];

            for (int i = 1; i < sibling.n; ++i) {
                sibling.keys[i - 1] = sibling.keys[i];
            }

            if (!sibling.leaf) {
                for (int i = 1; i <= sibling.n; ++i) {
                    sibling.children[i - 1] = sibling.children[i];
                }
            }

            child.n += 1;
            sibling.n -= 1;
        }

        void merge(int index) {
            BNode child = children[index];
            BNode sibling = children[index + 1];

            child.keys[t - 1] = keys[index];

            if (sibling.n >= 0) {
                System.arraycopy(sibling.keys, 0, child.keys, t, sibling.n);
            }

            if (!child.leaf) {
                if (sibling.n + 1 >= 0) {
                    System.arraycopy(sibling.children, 0, child.children, t, sibling.n + 1);
                }
            }

            for (int i = index + 1; i < n; ++i) {
                keys[i - 1] = keys[i];
            }

            for (int i = index + 2; i <= n; ++i) {
                children[i - 1] = children[i];
            }

            child.n += sibling.n + 1;
            n--;
        }

        boolean contains(T key) {
            int index = 0;
            while (index < n && key.compareTo(keys[index]) > 0) {
                index++;
            }
            if (index < n && keys[index].compareTo(key) == 0) return true;

            if (leaf) return false;

            return children[index].contains(key);
        }

        int height() {
            if (leaf) return 1;
            return 1 + children[0].height();
        }

        int size() {
            int size = n;
            if (!leaf) {
                for (int i = 0; i <= n; i++) {
                    size += children[i].size();
                }
            }
            return size;
        }
    }

    private BNode root;

    public BTree(int t) {
        this.root = new BNode(true);
        this.t = t;
        this.MAX_KEYS = 2 * t - 1;
    }

    @Override
    public boolean insert(T key) {
        BNode r = root;
        if (r.n == MAX_KEYS) {
            BNode s = new BNode(false);
            root = s;
            s.children[0] = r;
            s.splitChild(0, r);
            s.insertNonFull(key);

        } else r.insertNonFull(key);
        return true;
    }

    @Override
    public void add(T element) {
        this.insert(element);
    }

    @Override
    public boolean remove(T key) {
        if (root == null) return false;
        root.remove(key);
        if (root.n == 0) {
            if (root.leaf) root = null;
            else root = root.children[0];
        }
        return true;
    }

    @Override
    public boolean contains(T key) {
        return root != null && root.contains(key);
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }

    @Override
    public int getHeight() {
        return root == null ? 0 : root.height();
    }

    @Override
    public boolean isEmpty() {
        return root == null || root.n == 0;
    }

    @Override
    public int size() {
        return root == null ? 0 : root.size();
    }

    @Override
    public void clear() {
        root = new BNode(true);
    }

    @Override
    public String toString() {
        if (root == null) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("Root: ");
        sb.append("[");
        for (int i = 0; i < root.n; i++) {
            sb.append(root.keys[i]);
            if (i < root.n - 1) {
                sb.append(", ");
            }
        }
        sb.append("]\n");
        toString(root, sb, 1);
        return sb.toString();
    }

    @Override
    public void print() {
        System.out.println(this);
    }

    private void toString(BNode node, StringBuilder sb, int level) {
        if (!node.leaf) {
            for (int i = 0; i <= node.n ; i++) {
                BNode child = node.children[i];
                sb.append("Level ").append(level).append(": ");
                sb.append("[");
                for (int j = 0; j < child.n; j++) {
                    sb.append(child.keys[j]);
                    if (j < child.n - 1) {
                        sb.append(", ");
                    }
                }
                sb.append("]\n");
                toString(child, sb, level + 1);
            }
        }
    }
}
