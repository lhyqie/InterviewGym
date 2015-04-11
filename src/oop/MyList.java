package oop;

import java.util.ArrayList;
import java.util.List;

public class MyList implements IList {
	
	private List<Object> objs = null;  // composition
	
	MyList(){
		objs = new ArrayList<Object>(); 
	} 
	
	@Override
	public void add(Object elem) {
		// TODO Auto-generated method stub
		objs.add(elem);
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return objs.get(index);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return objs.size();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		objs.clear();
	}

}
