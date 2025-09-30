package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode<K, V> root;
    private int size;

    /**
     * Constructor: build an empty tree.
     */
    public BSTMap () {
        root = null;
        size = 0;
    }

    /**
     * Build a tree with given root.
     * @param key root's key
     * @param val root's val
     */
    public BSTMap (K key, V val) {
        root = new BSTNode<K, V>(key, val);
        size = 1;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(key, root);
    }

    private boolean containsKey(K key, BSTNode<K, V> node) {
        boolean result = false;
        if (node == null) {
            return false;
        } else {
            if (node.getKey().compareTo(key) == 0) {
                return true;
            } else if (node.getKey().compareTo(key) > 0) {
                result = containsKey(key, node.getLeft());
            } else if (node.getKey().compareTo(key) < 0) {
                result = containsKey(key, node.getRight());
            }
            return result;
        }
    }

    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {root = putHelper(key, value, root);}
    }

    private BSTNode<K, V> putHelper(K key, V value, BSTNode<K, V> root) {
        if (root == null) {
            size++;
            return new BSTNode<K, V>(key, value);
        } else if (key.compareTo(root.getKey()) > 0) {
            root.setRight(putHelper(key, value, root.getRight()));
        } else if (key.compareTo(root.getKey()) < 0) {
            root.setLeft(putHelper(key, value, root.getLeft()));
        }
        return root;
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        } else {
            return get(key, root);
        }
    }

    private V get(K key, BSTNode<K, V> node) {
        V result = null;

        if (node.getKey().compareTo(key) == 0) {
            result = node.getVal();
        } else if (node.getKey().compareTo(key) > 0) {
            result = get(key, node.getLeft());
        } else if (node.getKey().compareTo(key) < 0) {
            result = get(key, node.getRight());
        }

        return result;
    }

    @Override
    public int size() {return size;}

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    private class BSTNode<K extends Comparable<K>, V> {
        private K key;
        private V val;
        private BSTNode<K, V> left;
        private BSTNode<K, V> right;
        private int size;

        private BSTNode (K key, V val) {
            this.key = key;
            this.val = val;
            left = null;
            right = null;
        }

        public void setLeft (BSTNode<K, V> left) {this.left = left;}

        public void setRight (BSTNode<K, V> right) {this.right = right;}

        public K getKey() {return key;}

        public V getVal() {return val;}

        public BSTNode<K, V> getLeft() {return left;}

        public BSTNode<K, V> getRight() {return right;}

        public int size() {return size;}
    }
}
