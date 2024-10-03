package com.spurnet.state;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class MerkleTrie {
    private final Node root;

    public MerkleTrie() {
        this.root = new Node("");
    }

    public void insert(String key, String value) {
        Node current = root;
        for (char c : key.toCharArray()) {
            current = current.getChildren().computeIfAbsent(c, k -> new Node(String.valueOf(k)));
        }
        current.setValue(value);
    }

    public String get(String key) {
        Node current = root;
        for (char c : key.toCharArray()) {
            current = current.getChildren().get(c);
            if (current == null) return null;
        }
        return current.getValue();
    }

    public String getMerkleRoot() throws NoSuchAlgorithmException {
        return computeHash(root);
    }

    private String computeHash(Node node) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        for (Node child : node.getChildren().values()) {
            digest.update(computeHash(child).getBytes());
        }
        if (node.getValue() != null) {
            digest.update(node.getValue().getBytes());
        }
        byte[] hashBytes = digest.digest();
        return bytesToHex(hashBytes);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static class Node {
        private final String key;
        private String value;
        private final Map<Character, Node> children;

        public Node(String key) {
            this.key = key;
            this.children = new HashMap<>();
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Map<Character, Node> getChildren() {
            return children;
        }
    }
}
