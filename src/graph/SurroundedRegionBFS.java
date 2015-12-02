package graph;

import java.util.*;

public class SurroundedRegionBFS {

	public static void print(char[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i == arr.length - 1) {
				System.out.println("]");
			} else {
				System.out.print(", ");
			}
		}
	}

	public static void print(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			print(arr[i]);
		}
	}

	public static void main(String[] args) {
		 char[][] board = {
		 {'X','X','X','X'},
		 {'X','O','O','X'},
		 {'X','X','O','X'},
		 {'X','O','X','X'}
		 };

		//char[][] board = { { 'O', 'O' }, { 'O', 'O' } };

		print(board);
		System.out.println();
		solve(board);
		print(board);

	}

	// use a queue to do BFS
	private static Queue<Integer> queue = new LinkedList<Integer>();

	public static void solve(char[][] board) {
		if (board == null || board.length == 0)
			return;

		int m = board.length;
		int n = board[0].length;

		// merge O's on left & right boarder
		for (int i = 0; i < m; i++) {
			if (board[i][0] == 'O') {
				bfs(board, i, 0);
			}

			if (board[i][n - 1] == 'O') {
				bfs(board, i, n - 1);
			}
		}

		// merge O's on top & bottom boarder
		for (int j = 0; j < n; j++) {
			if (board[0][j] == 'O') {
				bfs(board, 0, j);
			}

			if (board[m - 1][j] == 'O') {
				bfs(board, m - 1, j);
			}
		}

		// process the board
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				} else if (board[i][j] == '#') {  // # visited
					board[i][j] = 'O';
				}
			}
		}
	}

	private static void bfs(char[][] board, int i, int j) {
		int n = board[0].length;

		// fill current first and then its neighbors
		fillCell(board, i, j);

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int x = cur / n;
			int y = cur % n;

			fillCell(board, x - 1, y);
			fillCell(board, x + 1, y);
			fillCell(board, x, y - 1);
			fillCell(board, x, y + 1);
		}
	}

	private static void fillCell(char[][] board, int i, int j) {
		int m = board.length;
		int n = board[0].length;
		if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O')
			return;

		// add current cell is queue & then process its neighbors in bfs
		queue.offer(i * n + j);
		board[i][j] = '#';
	}
}
