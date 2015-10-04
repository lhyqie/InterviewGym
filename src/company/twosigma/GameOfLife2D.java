package company.twosigma;

public class GameOfLife2D {
	
	public static void main(String[] args) {
		int[][] board = {{0,0,0,0},
						 {0,1,1,0},
						 {0,1,1,0},
						 {0,0,0,0}};
		
		int m = board.length, n = board[0].length;
		for(int r  = 0; r < m; r ++){
			for(int c = 0; c < n; c ++){
				int cnt = count(board, r, c , m ,n);
				System.out.print(cnt + " ");
			}
			System.out.println();
		}
	}
	
	public static void gameOfLife(int[][] board) {
        if(board == null || board.length == 0) return ;
        int m = board.length, n = board[0].length;
        int[][] buffer = new int[m][n];
        for(int r = 0; r < m; r ++){
            for(int c = 0; c < n; c ++){
                int cnt = count(board, r, c, m, n);
                if(board[r][c] == 1){
                    if(cnt < 2) buffer[r][c] = 0;
                    else if(cnt <= 3)  buffer[r][c] = 1;
                    else {
                        buffer[r][c] = 0;
                    }
                }else{
                    if(cnt == 3) buffer[r][c] = 1;
                }
            }
        }
        for(int r = 0; r < m; r ++){
            for(int c = 0; c < n; c ++){
                board[r][c] = buffer[r][c];
            }
        }
    }
    
    public static int count(int [][] board, int r, int c, int m, int n){
        int ret = 0;
        if(r-1 >= 0) {
            ret += board[r-1][c];
            if(c-1>=0) ret += board[r-1][c-1];
            if(c+1<n) ret+= board[r-1][c+1];
        }
        if(r+1 < m) {
            ret += board[r+1][c];
            if(c-1>=0) ret += board[r+1][c-1];
            if(c+1<n) ret+= board[r+1][c+1];
        }
        if(c-1 >= 0) {
            ret += board[r][c-1];
        }
        if(c+1 < n) {
            ret += board[r][c+1];
        }
        return ret;
    }
}
