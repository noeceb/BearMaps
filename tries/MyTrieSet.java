package lab9;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    private static class Node {
        private Map<Character, Node> children;
        private boolean isKey;

        Node() {
            children = new HashMap<>();
            isKey = false;
        }
    }

    public MyTrieSet() {
        clear();
    }

    /** Clears all items out of Trie */
    public void clear() {
        root = new Node();
    };

    private Node getNodeWithPrefix(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            Node nextNode = node.children.get(c);
            if (nextNode == null) {
                return null;
            }
            node = nextNode;
        }
        return node;
    }


    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        Node n = getNodeWithPrefix(key);
        return n != null && n.isKey;
    }


    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null) {
            return;
        }
        add(root, key);
    }

    private void add(Node n, String key) {
        if (key.length() == 0) {
            n.isKey = true;
            return;
        }

        char firstChar = key.charAt(0);
        Node nextNode = n.children.get(firstChar);
        if (nextNode == null) {
            nextNode = new Node();
            n.children.put(firstChar, nextNode);
        }
        add(n.children.get(firstChar), key.substring(1));
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        ArrayList<String> list = new ArrayList<>();
        Node n = getNodeWithPrefix(prefix);
        if (n == null) {
            return list;
        }
        prefixHelper(n, prefix, list);
        return list;
    }

    private void prefixHelper(Node n, String prefix, List<String> keys) {
        if (n.isKey) {
            keys.add(prefix);
        }
        for (Map.Entry<Character, Node> e : n.children.entrySet()) {
            String newPrefix = prefix + e.getKey();
            prefixHelper(e.getValue(), newPrefix, keys);
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}


