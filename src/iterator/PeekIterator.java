package iterator;

import java.util.*;

//Java Iterator interface reference:
//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekIterator implements Iterator<Integer> {
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList(Arrays.asList(1,2,3,5,6,8,10));
		
		PeekIterator piter = new PeekIterator(list.iterator());
		
		while(piter.hasNext()){
			System.out.println(piter.next());
		}
	}
	
	private static Object nullObject = new Object();

	Object cur;

	Iterator<Integer> iter;

	public PeekIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		cur = nullObject;
		iter = iterator;
		if (iter.hasNext()) {
			cur = iter.next();
		}
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		return (Integer) cur;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		Integer ret = (Integer) cur;
		if(iter.hasNext()){
			cur = iter.next();
		}else{
			cur = nullObject;
		}
		return ret;
	}

	@Override
	public boolean hasNext() {
		return cur != nullObject;
	}
}