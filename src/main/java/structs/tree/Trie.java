package structs.tree;

import structs.Tree;
import java.util.HashMap;
import java.util.Map;


public class Trie implements Tree<String> {

    private static class TrieNode implements Node<String> {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isCompleteWord = false;
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    @Override
    public boolean insert(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }

        if (current.isCompleteWord) return false;

        current.isCompleteWord = true;
        return true;
    }

    @Override
    public void add(String element) {
        this.insert(element);
    }

    @Override
    public boolean remove(String word) {
        return remove(root, word, 0);
    }

    private boolean remove(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isCompleteWord) return false;

            current.isCompleteWord = false;
            return current.children.isEmpty();
        }

        char c = word.charAt(index);
        TrieNode node = current.children.get(c);
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
        TrieNode node = getNode(word);
        return node != null && node.isCompleteWord;
    }

    public boolean startsWith(String prefix) {
        return getNode(prefix) != null;
    }

    private TrieNode getNode(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            current = current.children.get(word.charAt(i));
            if (current == null) return null;
        }
        return current;
    }

    @Override
    public Node<String> getRoot() {
        return root;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(TrieNode node) {
        if (node.children.isEmpty()) return 0;
        int h = 0;

        for (TrieNode child : node.children.values()) {
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

    private int size(TrieNode node) {
        int counter = 0;
        for (TrieNode child : node.children.values()) {
            counter += size(child);
        }
        return counter + 1;
    }

    @Override
    public void clear() {
        root.children.clear();
        root.isCompleteWord = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, new StringBuilder(), sb);
        return sb.toString();
    }

    private void toString(TrieNode node, StringBuilder currentWord, StringBuilder sb) {
        if (node.isCompleteWord) {
            sb.append(currentWord).append("\n");
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            currentWord.append(entry.getKey());
            toString(entry.getValue(), currentWord, sb);
            currentWord.deleteCharAt(currentWord.length() - 1);
        }
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
