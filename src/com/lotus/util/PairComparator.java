package com.lotus.util;

import java.util.Comparator;

public class PairComparator implements Comparator<Pair<String, Integer>> {

	@Override
	public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
		if (o1.getFirst().equalsIgnoreCase(o2.getFirst())) {
			return 0;
		}else {
			return o2.getSecond() - o1.getSecond();
		}
	}

}