package com.lotus.util;

public class Pair<K,V> {
	K first;
	V second;
	public Pair(K first, V second) {
		this.first = first;
		this.second = second;
	}
	public K getFirst() {
		return first;
	}
	public void setFirst(K first) {
		this.first = first;
	}
	public V getSecond() {
		return second;
	}
	public void setSecond(V second) {
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o instanceof Pair) {
			Pair p = (Pair)o;
			return first.equals(p.first);
		}
		return false;
	}
}
