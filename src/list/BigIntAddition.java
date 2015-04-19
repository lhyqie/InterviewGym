package list;

class BigInt{
	
	private MyList digits = null;
	
	public BigInt(){
		digits = new MyList();
	}
	
	public BigInt(String aLargeNumber){
		digits = new MyList();
		for(int i = 0; i < aLargeNumber.length(); i++){
			MyList.Node d = new MyList.Node(aLargeNumber.charAt(i) - 48);
			d.next = digits.head;
			digits.head = d;
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		MyList.Node p = digits.head;
		while(p!=null){
			sb.append(p.e);
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
		MyList.Node p1 = this.digits.head;
		MyList.Node p2 = other.digits.head;
		ret.digits.head = new MyList.Node(-999); // dummy node
		MyList.Node p3 = ret.digits.head;
		int c = 0;
		while(p1 != null && p2 != null){
			int sum = p1.e + p2.e + c;
			if( sum >= 10){
				sum %= 10;
				c = 1;
			}else{
				c = 0;
			}
			p1 = p1.next;  p2 = p2.next;
			p3.next = new MyList.Node(sum);
			p3 = p3.next;
		}
		while(p1 != null){ // if list 1 is longer
			int sum = p1.e+ c;
			if( sum >= 10){
				sum %= 10;
				c = 1;
			}else{
				c = 0;
			}
			p1 = p1.next;
			p3.next = new MyList.Node(sum);
			p3 = p3.next;
		}
		while(p2 != null){ // if list 2 is longer
			int sum = p2.e + c;
			if( sum >= 10){
				sum %= 10;
				c = 1;
			}else{
				c = 0;
			}
			p2 = p2.next;
			p3.next = new MyList.Node(sum);
			p3 = p3.next;
		}
		//don't forget the carry
		if( c == 1){
			p3.next = new MyList.Node(c);
			p3 = p3.next;
		}
		
		ret.digits.head = ret.digits.head.next;  // throw the dummy node
		return ret;
	}
}


public class BigIntAddition {
	
	public static void main(String[] args) {
		
		String[] As = {"5","1","","1", "123456789123456789"};
		String[] Bs = {"5","" ,"1","9999","111111111111111111"};
		
		for (int i = 0; i < Bs.length; i++) {
			BigInt a = new BigInt(As[i]);
			System.out.println("a = " + a);
			
			BigInt b = new BigInt(Bs[i]);
			System.out.println("b = " + b);
			
			BigInt c = a.add(b);
			System.out.println("c = " + c);
			System.out.println();
		}
		
		BigInt a = new BigInt("123456789123456789");
		System.out.println(a);
		
		BigInt b = new BigInt("11111111111111111111");
		System.out.println(b);
		
		BigInt c = a.add(b);
		System.out.println(c);

		
	}
	
}
