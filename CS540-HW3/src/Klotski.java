///////////////////////////////////////////////////////////////////////////////
// Title:            Klotski Puzzle
// Files:            Klotski.java AStarSearch.java GameState.java
// Semester:         CS 540 Fall 2018
//
// Author:           Arjun Balasubramanian
// Email:            balarjun@cs.wisc.edu
// CS Login:         balarjun
// Lecturer's Name:  Professor Jerry Zhu
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;

public class Klotski {
	public static void printNextStates(GameState s) {
		List<GameState> states = s.getNextStates();
		for (GameState state : states) {
			state.printBoard();
			System.out.println();
		}
	}

	public static void main(String[] args) {
		if (args == null || args.length < 21) {
			return;
		}
		int flag = Integer.parseInt(args[0]);
		int[][] board = new int[5][4];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = Integer.parseInt(args[i * 4 + j + 1]);
			}
		}
		boolean uniformCostSearch = false;
		if (flag == 200 || flag == 300) {
			uniformCostSearch = true;
		}
		GameState s = new GameState(board, 0, null, uniformCostSearch);

		if (flag == 100) {
			printNextStates(s);
			return;
		}

		AStarSearch search = new AStarSearch();
		search.aStarSearch(flag, s);

	}

}