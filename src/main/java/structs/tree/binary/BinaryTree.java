package structs.tree.binary;

import structs.Structure;
import structs.Tree;
import java.util.Collection;


public class BinaryTree<T> implements Tree<T> {

    protected BinaryNode<T> root;

    protected int size;


    public BinaryTree() {
        root = null;
        size = 0;
    }

    public BinaryTree(Collection<T> collection) {
        this();
        for (T x : collection) {
            insert(x);
        }
    }

    public BinaryTree(Structure<T> structure) {
        this();
        for (T x : structure) {
            insert(x);
        }
    }

    @Override
    public void add(T element) {
        this.insert(element);
    }

    @Override
    public boolean insert(T x) {
        if (root == null) {
            root = new BinaryNode<>(x);
            size++;
            return true;
        }
        if (insert(root, x)) {
            size++;
            return true;
        }
        return false;
    }

    private boolean insert(BinaryNode<T> node, T x) {
        if (node.left == null) {
            node.left = new BinaryNode<>(x);
            return true;

        } else if (node.right == null) {
            node.right = new BinaryNode<>(x);
            return true;

        } else {
            if (insert(node.left, x)) return true;

            else return insert(node.right, x);
        }
    }

    @Override
    public boolean remove(T x) {
        if (contains(x)) {
            root = remove(root, x);
            size--;
            return true;
        }
        return false;
    }

    private BinaryNode<T> remove(BinaryNode<T> node, T x) {
        if (node == null) return null;
        if (node.data.equals(x)) {
            if (node.left == null && node.right == null) {
                return null;

            } else if (node.left == null) {
                return node.right;

            } else if (node.right == null) {
                return node.left;

            } else {
                node.data = min(node.right);
                node.right = remove(node.right, node.data);
            }
        } else {
            node.left = remove(node.left, x);
            node.right = remove(node.right, x);
        }
        return node;
    }

    protected T min(BinaryNode<T> node) {
        T min = node.data;
        while (node.left != null) {
            min = node.left.data;
            node = node.left;
        }
        return min;
    }

    @Override
    public boolean contains(T x) {
        return contains(root, x);
    }

    private boolean contains(BinaryNode<T> node, T x) {
        if (node == null) return false;
        if (node.data.equals(x)) return true;
        return contains(node.left, x) || contains(node.right, x);
    }


    @Override
    public BinaryNode<T> getRoot() {
        return root != null ? root : null;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    protected int getHeight(BinaryNode<T> node) {
        if (node == null) return 0;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "", "");
        System.out.println(sb);
    }

    @Override
    public String toString() {
        return Traverser.inorder(root).toString();
    }

    private void toString(BinaryNode<T> node, StringBuilder sb, String prefix, String childrenPrefix) {
        if (node != null) {
            sb.append(prefix);
            sb.append(node.data);
            sb.append("\n");
            if (node.left != null || node.right != null) {
                if (node.right != null) {
                    toString(node.right, sb, childrenPrefix + "├── ", childrenPrefix + "│   ");

                } else sb.append(childrenPrefix).append("├── null\n");

                if (node.left != null) {
                    toString(node.left, sb, childrenPrefix + "└── ", childrenPrefix + "    ");

                } else sb.append(childrenPrefix).append("└── null\n");
            }
        }
    }
}
