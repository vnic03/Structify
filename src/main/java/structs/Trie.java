package structs;

import java.util.HashMap;
import java.util.Map;


public class Trie implements Tree<String> {

    private static class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean isCompleteWord = false;
    }

    private final Node root;

    public Trie() {
        root = new Node();
    }

    @Override
    public boolean insert(String word) {
        Node current = root;

        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new Node());
            current = current.children.get(c);
        }

        if (current.isCompleteWord) return false;

        current.isCompleteWord = true;
        return true;
    }

    @Override
    public boolean remove(String word) {
        return remove(root, word, 0);
    }

    private boolean remove(Node current, String word, int index) {
        if (index == word.length()) {
            if (!current.isCompleteWord) return false;

            current.isCompleteWord = false;
            return current.children.isEmpty();
        }

        char c = word.charAt(index);
        Node node = current.children.get(c);
        if (node == null) return false;

        boolean deleteCurrentNode = remove(node, word, index + 1);

        if (deleteCurrentNode) {
            current.children.remove(c);
            return current.children.isEmpty();
        }
        return false;
    }

    @Override
    public boolean contains(String word) {
        Node node = getNode(word);
        return node != null && node.isCompleteWord;
    }

    public boolean startsWith(String prefix) {
        return getNode(prefix) != null;
    }

    private Node getNode(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            current = current.children.get(word.charAt(i));
            if (current == null) return null;
        }
        return current;
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node node) {
        if (node.children.isEmpty()) return 0;
        int h = 0;

        for (Node child : node.children.values()) {
            h = Math.max(h, getHeight(child));
        }
        return h + 1;
    }

    @Override
    public boolean isEmpty() {
        return root.children.isEmpty();
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        int counter = 0;
        for (Node child : node.children.values()) {
            counter += size(child);
        }
        return counter + 1;
    }

    @Override
    public void clear() {
        root.children.clear();
        root.isCompleteWord = false;
    }
}
