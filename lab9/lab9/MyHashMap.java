package lab9;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;

public class MyHashMap<K extends Comparable<K>, V> implements Map61B<K, V>{
	private int size;
	private double loadFactor;
	private int resizefactor = 2;
	private Entry<K, V>[] bins;

	private static class Entry<K, V> {
		private Node first;

		public Entry(){
		}

		private class Node{
			K key;
			V value;
			Node next;
			public Node(K key, V value, Node next){
				this.key = key;
				this.value = value;
				this.next = next;
			}
		}

		public V get(K key){
			for (Node x = first; x != null; x = x.next){
				if (key.equals(x.key)){
					return x.value;
				}
			}
			return null;
		}

		public void put(K key, V value){
			for (Node x = first; x != null; x = x.next){
				if (key.equals(x.key)){
					x.value = value;
					return;
				}
			}

			first = new Node(key, value, first);
		}
	}

	public MyHashMap(){
		this(127);
	}

	public MyHashMap(int initialSize){
		this(initialSize, 0.75);
	}

	public MyHashMap(int initialSize, double initialloadFactor){
		this.size = initialSize;
		this.loadFactor = initialloadFactor;
		bins = (Entry<K, V>[]) new Entry[initialSize];
		for (int i = 0; i < initialSize; i++){
			bins[i] = new Entry();
		}
	}


	private int hash(K key){
		return (key.hashCode() & 0x7fffffff) % size;
	}

	@Override
	public void clear(){
		for (int i = 0; i < this.bins.length; i++) {
            this.bins[i] = new Entry<K, V>();
        }
	}


	@Override
	public boolean containsKey(K key){
		if (key == null) throw new IllegalArgumentException("Argument to containsKey() is null!");
		return get(key) != null;
	}


	@Override
	public V get(K key){
		if (key == null) throw new IllegalArgumentException("Argument to get() is null!");
		int i = has(key);
		return bins[i].get(key);
	}



	@Override
	public int size(){
		return this.size;
	}


	@Override
	public void put(K key, V value){
		throw new UnsupportedOperationException("Not supported operation!");
	}


	@Override
	public Set<K> keySet(){
		throw new UnsupportedOperationException("Not supported operation!");
	}

	@Override
	public V remove(K key){
		throw new UnsupportedOperationException("Not supported operation!");
	}

	@Override
	public V remove(K key, V value){
		throw new UnsupportedOperationException("Not supported operation!");
	}

	@Override
    public Iterator iterator() {
       throw new UnsupportedOperationException("Not supported operation!");
    }
}