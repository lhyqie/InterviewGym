package javautils;

import java.util.*;

public class TestIterator {
	public static void main(String[] args) {
		testRemove();
	}
	
	public static void testRemove(){
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
		System.out.println(list);
		Iterator<Integer> iter = list.iterator();
		while(iter.hasNext()){
			int e = iter.next();
			if(e % 2 == 0) iter.remove(); 
		}
		System.out.println(list);
	}
}
