package company.twosigma;

import cern.colt.Arrays;

// http://www.garretwilson.com/education/institutions/soas/dissertation/lifecellauto1d.html

public class GameOfLife1D {
	
	int n;
	int [] M = null;
	int [] T = null;
	GameOfLife1D(int[] initial){
		this.n = initial.length;
		M = new int[n];
		T = new int[n];
		for(int i = 0; i < n; i++){
			M[i] = initial[i];
		}
	}
	
	void go(){
		// update Temporary T 
		for(int i = 0; i < n; i++){
			int left = M[(i+n-1) % n];
			int right = M[(i+1) % n];
			if(M[i] == 1){
//				if( left == 1 && right == 1) T[i] = 0;     // 111 => 0
//				if( left == 0 && right == 1) T[i] = 0;     // 011 => 0
//				if( left == 1 && right == 0) T[i] = 1;     // 110 => 1
//				if( left == 0 && right == 0) T[i] = 1;     // 010 => 1
				T[i] = 0;
			}else{
//				if( left == 1 && right == 1) T[i] = 1;     // 101 => 1
//				if( left == 0 && right == 1) T[i] = 1;     // 001 => 1
//				if( left == 1 && right == 0) T[i] = 0;     // 100 => 0
//				if( left == 0 && right == 0) T[i] = 0;     // 000 => 0
				if( left == 1 && right == 1) T[i] = 0;     // 101 => 1
				if( left == 0 && right == 1) T[i] = 1;     // 001 => 1
				if( left == 1 && right == 0) T[i] = 1;     // 100 => 0
				if( left == 0 && right == 0) T[i] = 0;     // 000 => 0
			}
		}
		// swap M and T
		int[] temp = T;
		T = M;
		M = temp;
	}	
	
	void print(){
		System.out.println(Arrays.toString(M));
	}
	
	public static void main(String[] args) {
		int [] initial = new int[16];
		initial[0] = 1;
		initial[1] = 1;
		GameOfLife1D game = new GameOfLife1D(initial);
		game.print();
		for (int i = 0; i < 10; i++) {
			game.go();
			game.print();
		}
	}
}

