package structs.tree.binary;

import structs.Tree;


public class BinaryNode<N> implements Tree.Node<N> {

    protected N data;

    protected BinaryNode<N> left, right;

    public BinaryNode(N data) {
        this.data = data;
        left = right = null;
    }

    @Override
    public N getData() {
        return data;
    }

    @Override
    public BinaryNode<N> getLeft() {
        return left;
    }

    @Override
    public BinaryNode<N> getRight() {
        return right;
    }
}
