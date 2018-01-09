package lab8;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
	private Node root;
	private class Node{
		private K k;
		private V v;
		private Node left, right;
		private int size;

		public Node(K k, V v, int size){
			this.k = k;
			this.v = v;
			this.size = size;
		}
	}

	public BSTMap(){
	}

	@Override
	public void clear(){
		root = null;
	}

	@Override
	public int size(){
		return size(root);
	}
	private int size(Node x){
		if (x == null) return 0;
		else return x.size;
	}

	@Override
	public boolean containsKey(K k){
		if (k == null) throw new IllegalArgumentException("Argument to containsKey() is null!");
		return get(k)!=null;
	}

	@Override
	public V get(K k){
		return get(root, k);
	}
	private V get(Node x, K k){
		if (x == null) return null;
		if (k == null) throw new IllegalArgumentException("Call get() with a null key!");
		int cmp = k.compareTo(x.k);
		if (cmp < 0) return get(x.left, k);
		else if (cmp > 0) return get(x.right, k);
		else return x.v;
	}

	@Override
	public void put(K k, V v){
		if (k == null) throw new IllegalArgumentException("Call put() with a null key!");
		if (v == null) throw new IllegalArgumentException("Call put() with a null value!");
		root = put(root, k, v);
	}
	private Node put(Node x, K k, V v){
		if (x == null) return new Node(k, v, 1);
		int cmp = k.compareTo(x.k);
		if (cmp < 0) x.left = put(x.left, k, v);
		else if (cmp > 0) x.right = put(x.right, k, v);
		else x.v = v;
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void printInOrder(){
		printInOrder(this.root);
	}
	private void printInOrder(Node x){
		if (x == null){
			return;
		} else{
			printInOrder(x.left);
			System.out.print(x.k.toString() + " ");
			printInOrder(x.right);
		}

	}

	@Override
	public Set<K> keySet(){
		throw new UnsupportedOperationException("Not supported operation!");
	}

	@Override
	public V remove(K k){
		throw new UnsupportedOperationException("Not supported operation!");
	}

	@Override
	public V remove(K k, V v){
		throw new UnsupportedOperationException("Not supported operation!");
	}
	@Override
    public Iterator iterator() {
       throw new UnsupportedOperationException("Not supported operation!");
    }
}