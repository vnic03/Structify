package structs.tree.binary;

import structs.Structure;
import java.util.Collection;


public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(Collection<T> collection) {
        super(collection);
    }

    public BinarySearchTree(Structure<T> structure) {
        super(structure);
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
        if (x.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new BinaryNode<>(x);
                return true;
            }
            return insert(node.left, x);
        
        } else if (x.compareTo(node.data) > 0) {
            if (node.right == null) {
                node.right = new BinaryNode<>(x);
                return true;
            }
            return insert(node.right, x);
        }
        return false;
    }

    @Override
    public boolean remove(T x) {
        if (this.contains(x)) {
            root = remove(root, x);
            size--;
            return true;
        }
        return false;
    }

    private BinaryNode<T> remove(BinaryNode<T> node, T x) {
        if (node == null) return node;

        if (x.compareTo(node.data) < 0) {
            node.left = remove(node.left, x);
        
        } else if (x.compareTo(node.data) > 0) {
            node.right = remove(node.right, x);

        } else {
            if (node.left == null) return node.right;

            else if (node.right == null) return node.left;

            node.data = min(node.right);

            node.right = remove(node.right, node.data);
        }

        return node;
    }

    @Override
    public boolean contains(T x) {
        return contains(root, x);
    }

    private boolean contains(BinaryNode<T> node, T x) {
        if (node == null) return false;

        if (x.compareTo(node.data) < 0) {
            return contains(node.left, x);

        } else if (x.compareTo(node.data) > 0) {
            return contains(node.right, x);

        } else return true;
    }
}
