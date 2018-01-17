package lab9;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;

public class MyHashMap<K extends Comparable<K>, V> implements Map61B<K, V>{
	private int size;
	private int currentSize;
	private double loadFactor;
	private int resizefactor = 2;
	private Entry<K, V>[] bins;
	private Set<K> keySet;

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

		public Set<K> keySet(){
			HashSet<K> keyset = new HashSet<K>();
        	for (Node x = first; x != null; x = x.next){
                	keyset.add(x.key);
        	}
        	return keyset;
		}
	}

	public MyHashMap(){
		this(127);
	}

	public MyHashMap(int initialSize){
		this(initialSize, 0.1);
	}

	public MyHashMap(int initialSize, double initialloadFactor){
		this.size = initialSize;
		this.currentSize = 0;
		this.loadFactor = initialloadFactor;
		bins = (Entry<K, V>[]) new Entry[initialSize];
		for (int i = 0; i < initialSize; i++){
			bins[i] = new Entry();
		}
		this.keySet = this.keySet();
	}


	private int hash(K key){
		return (key.hashCode() & 0x7fffffff) % size;
	}

	@Override
	public void clear(){
		for (int i = 0; i < this.bins.length; i++) {
            this.bins[i] = new Entry<K, V>();
        }
        this.currentSize = 0;
	}


	@Override
	public boolean containsKey(K key){
		return get(key) != null;
	}


	@Override
	public V get(K key){
		int i = hash(key);
		return bins[i].get(key);
	}



	@Override
	public int size(){
		return this.currentSize;
	}


	@Override
	public void put(K key, V value){
		int i = hash(key);
		if (this.size < loadFactor * this.currentSize){
			resize(this.resizefactor * this.size);
		}

		if(bins[i].get(key) == null) this.currentSize++;
		bins[i].put(key, value);
		keySet = this.keySet();
	}

	private void resize(int s){
		MyHashMap<K, V> temp = new MyHashMap<K, V>(s);
		for (int i = 0; i < this.size; i++){
			for (K key : bins[i].keySet()){
				temp.put(key, bins[i].get(key));
			}
		}
		this.size = temp.size;
		this.currentSize = this.currentSize;
		this.bins = temp.bins;
	}

	@Override
	public Set<K> keySet(){
		HashSet<K> keyset = new HashSet<K>();
        for (int i = 0; i < size; i++) {
            for (K key : bins[i].keySet())
                keyset.add(key);
        }
        return keyset;
	}

	@Override
	public V remove(K key){
		throw new UnsupportedOperationException("Not supported operation!");
	}

	@Override
	public V remove(K key, V value){
		throw new UnsupportedOperationException("Not supported operation!");
	}

	private class MapIterator implements Iterator<K> {

        private Iterator<K> setIterator;
        public MapIterator() {
            this.setIterator = keySet.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.setIterator.hasNext();
        }

        @Override
        public K next() {
            return this.setIterator.next();
        }
    }

	@Override
    public Iterator<K> iterator() {
        return new MapIterator();
    }
}