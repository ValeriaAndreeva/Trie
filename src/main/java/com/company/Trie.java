package com.company;

import java.util.*;

public final class Trie {
    static class Node {
        Node level;
        Node nextLevel;
        Node parent;
        int number;
        boolean isEnd;
        Character info;

        Node(Node level,
             Node nextLevel,
             Node parent,
             int number,
             boolean isEnd,
             Character info) {
            this.level = level;
            this.nextLevel = nextLevel;
            this.parent = parent;
            this.number = number;
            this.isEnd = false;
            this.info = info;
        }

        boolean hasLevel() {
            return this.level != null;
        }

        boolean hasNextLevel() {
            return this.nextLevel != null;
        }
    }

    private Node root = new Node(null, null, null, 0, false, null);

    public boolean find(String string) {
        Node currentNode = root;
        string = string.toLowerCase();

        for (int i = 0; i < string.length(); i++) {
            char nextCh = string.charAt(i);
            if (currentNode.hasLevel()) currentNode = currentNode.level;
            else return false;

            while (currentNode.info != nextCh && currentNode.hasNextLevel()) {
                currentNode = currentNode.nextLevel;
            }

            if (currentNode.info != nextCh) return false;
            if ((i == string.length() - 1) && !currentNode.isEnd) return false;
        }
        return true;
    }

    private boolean existPrefix(String prefix) {
            Node currentNode = root;

            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                if (currentNode.hasLevel()) currentNode = currentNode.level;
                else return false;

                while (currentNode.info != ch && currentNode.hasNextLevel()) {
                    currentNode = currentNode.nextLevel;
                }
                if (currentNode.info != ch) return false;
            }
            return true;
    }

    private void addCh (Node currentNode, String res, ArrayList<String> collector) {
        if (currentNode.hasLevel()) {
            currentNode = currentNode.level;
            res += currentNode.info;
            if (currentNode.isEnd) collector.add(res);

            addCh(currentNode, res, collector);
        }

        while (currentNode.hasNextLevel()) {
            currentNode = currentNode.nextLevel;
            res = res.substring(0, res.length()-1);
            res += currentNode.info;

            if (currentNode.isEnd) collector.add(res);
            addCh(currentNode, res, collector);
        }
    }

    public List<String> prefixFind (String prefix) throws IllegalArgumentException{
        Node currentNode = root;
        prefix = prefix.toLowerCase();

        if (!existPrefix(prefix)) throw new IllegalArgumentException();

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            currentNode = currentNode.level;
            while (currentNode.info != ch && currentNode.hasNextLevel()) currentNode = currentNode.nextLevel;
        }

        ArrayList<String> collector = new ArrayList<String>();

        if (currentNode.isEnd) {
            collector.add(prefix);
            return collector;
        }

        String res = prefix;

        addCh(currentNode, res, collector);
        return collector;
    }

    public void add(String string) {

        Node currentNode = root;
        string = string.toLowerCase();

        for (int i = 0; i < string.length(); i++) {
            char nextCh = string.charAt(i);

            if (currentNode.number == 0) {
                if (!currentNode.hasLevel()) {
                    Node level = new Node(null, null, currentNode, currentNode.number+1, false, nextCh);
                    if (i == string.length() - 1) level.isEnd = true;
                    currentNode.level = level;
                }
                currentNode = currentNode.level;
            }
            while (currentNode.info != nextCh && currentNode.hasNextLevel()) {
                currentNode = currentNode.nextLevel;
            }
            if (currentNode.info != nextCh && !currentNode.hasNextLevel()) {
                Node nextLevel = new Node(null, null, currentNode.parent, currentNode.number, false, nextCh);
                if (i == string.length() - 1) nextLevel.isEnd = true;
                currentNode.nextLevel = nextLevel;
                currentNode = currentNode.nextLevel;
            } else if (currentNode.info == nextCh && (i == string.length() - 1)) {
                currentNode.isEnd = true;
            }

            if (currentNode.hasLevel()) currentNode = currentNode.level;
            else if ((i != string.length() - 1) && !currentNode.hasLevel()) {
                char ch = string.charAt(i+1);
                Node level = new Node(null, null, currentNode, currentNode.number + 1, false, ch);
                currentNode.level = level;
                currentNode = currentNode.level;
            }
        }
    }
   }