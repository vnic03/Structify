package structs.tree.binary;

import java.util.ArrayList;
import java.util.List;
import structs.Tree;


public class Traverser<T> {

    public List<T> inorder(Tree.Node<T> node) {
        E(node);
        List<T> elements = new ArrayList<>();
        inorder(node, elements);
        return elements;
    }

    private void inorder(Tree.Node<T> node, List<T> elements) {
        if (node != null) {
            inorder(node.getLeft(), elements);
            elements.add(node.getData());
            inorder(node.getRight(), elements);
        }
    }

    public List<T> postorder(Tree.Node<T> node) {
        E(node);
        List<T> elements = new ArrayList<>();
        postorder(node, elements);
        return elements;
    }

    private void postorder(Tree.Node<T> node, List<T> elements) {
        if (node != null) {
            postorder(node.getLeft(), elements);
            postorder(node.getRight(), elements);
            elements.add(node.getData());
        }
    }

    public List<T> preorder(Tree.Node<T> node) {
        E(node);
        List<T> elements = new ArrayList<>();
        preorder(node, elements);
        return elements;
    }

    private void preorder(Tree.Node<T> node, List<T> elements) {
        if (node != null) {
            elements.add(node.getData());
            preorder(node.getLeft(), elements);
            preorder(node.getRight(), elements);
        }
    }

    private void E(Tree.Node<T> node) {
        if (!(node instanceof BinaryNode)) {
            throw new IllegalArgumentException("Traverser only for Binary-Trees");
        }
    }
}

