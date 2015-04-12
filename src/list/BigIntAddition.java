package list;

class BigInt{
	
	private MyList digits = null;
	
	public BigInt(){
		digits = new MyList();
	}
	
	public BigInt(String aLargeNumber){
		digits = new MyList();
		for(int i = 0; i < aLargeNumber.length(); i++){
			MyList.Node d = new MyList.Node(aLargeNumber.charAt(i));
			d.next = digits.head;
			digits.head = d;
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		MyList.Node p = digits.head;
		while(p!=null){
			sb.append((int)(p.e) - 48);
			p=p.next;
		}
		return sb.reverse().toString();
	}

	/**
	 * add two big Int
	 * (list based addition)
	 * @param other
	 * @return
	 */
	public BigInt add(BigInt other) {
		BigInt ret = new BigInt();
		
		return ret;
	}
}


public class BigIntAddition {
	
	public static void main(String[] args) {
		BigInt a = new BigInt("123456789123456789");
		System.out.println(a);
		
		BigInt b = new BigInt("11111111111111111111");
		System.out.println(b);
		
		BigInt c = a.add(b);
		System.out.println(c);
	}
	
}
