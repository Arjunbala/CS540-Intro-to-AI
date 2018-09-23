import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState {
	private int[][] board = new int[5][4];
	private GameState parent = null;
	private int cost = 0;
	private int steps = 0;
	private int heuristic_score = 0;
	private boolean uniform_cost_search = false;

	public GameState(int[][] inputBoard, int steps, GameState parent,
			boolean uniformCostSearch) {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 4; j++)
				this.board[i][j] = inputBoard[i][j];
		this.steps = steps;
		if (!uniformCostSearch) {
			this.heuristic_score = getHeuristicScore();
		} else {
			this.heuristic_score = 0;
		}
		this.cost = this.steps + this.heuristic_score;
		this.parent = parent;
		this.uniform_cost_search = uniformCostSearch;
	}

	public GameState(int[][] inputBoard) {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 4; j++)
				this.board[i][j] = inputBoard[i][j];
	}

	// get all successors and return them in sorted order
	public List<GameState> getNextStates() {
		List<GameState> successors = new ArrayList<>();

		// First, identify the position of the two empty blocks
		int emptyBlock1X = -1, emptyBlock1Y = -1, emptyBlock2X = -1,
				emptyBlock2Y = -1;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 0) {
					if (emptyBlock1X == -1) {
						emptyBlock1X = i;
						emptyBlock1Y = j;
					} else {
						emptyBlock2X = i;
						emptyBlock2Y = j;
						break;
					}
				}
			}
		}

		// Now generate successors by moving 1*1 blocks into the empty position
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 4) {
					// swap board[i][j] with the empties and backtrack
					board[emptyBlock1X][emptyBlock1Y] = 4;
					board[i][j] = 0;
					successors.add(new GameState(board, this.steps + 1, this,
							this.uniform_cost_search));
					board[emptyBlock1X][emptyBlock1Y] = 0;
					board[emptyBlock2X][emptyBlock2Y] = 4;
					successors.add(new GameState(board, this.steps + 1, this,
							this.uniform_cost_search));
					board[emptyBlock2X][emptyBlock2Y] = 0;
					board[i][j] = 4;
				}
			}
		}

		// Now check whether the 1*2 block can be moved into empty spaces
		outerloop_1: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 3) {
					// check if slide left works
					if (j > 0 && board[i][j - 1] == 0) {
						// Works!
						board[i][j - 1] = 3;
						board[i][j + 1] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i][j - 1] = 0;
						board[i][j + 1] = 3;
					}
					// check if slide right works
					if (j < 2 && board[i][j + 2] == 0) {
						// Works!
						board[i][j + 2] = 3;
						board[i][j] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i][j + 2] = 0;
						board[i][j] = 3;
					}
					// check if slide up works
					if (i > 0 && board[i - 1][j] == 0
							&& board[i - 1][j + 1] == 0) {
						// Works!
						board[i - 1][j] = 3;
						board[i - 1][j + 1] = 3;
						board[i][j] = 0;
						board[i][j + 1] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i - 1][j] = 0;
						board[i - 1][j + 1] = 0;
						board[i][j] = 3;
						board[i][j + 1] = 3;
					}
					// check if slide down works
					if (i < 4 && board[i + 1][j] == 0
							&& board[i + 1][j + 1] == 0) {
						// Works!
						board[i + 1][j] = 3;
						board[i + 1][j + 1] = 3;
						board[i][j] = 0;
						board[i][j + 1] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i + 1][j] = 0;
						board[i + 1][j + 1] = 0;
						board[i][j] = 3;
						board[i][j + 1] = 3;
					}
					break outerloop_1;
				}
			}
		}

		// Now check whether the 2*1 blocks can be moved into empty spaces
		// Since, there are multiple 2*1 blocks, we need to ensure we visit each
		// block only once
		ArrayList<Integer> skipBlocks = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 2 && !skipBlocks.contains(4 * i + j)) {
					// Add the other part of the block to skip list.
					skipBlocks.add(4 * (i + 1) + j);
					// Check if block can slide down
					if (i < 3 && board[i + 2][j] == 0) {
						// Works!
						board[i + 2][j] = 2;
						board[i][j] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i + 2][j] = 0;
						board[i][j] = 2;
					}
					// Check if block can move up
					if (i > 0 && board[i - 1][j] == 0) {
						// Works!
						board[i - 1][j] = 2;
						board[i + 1][j] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i - 1][j] = 0;
						board[i + 1][j] = 2;
					}
					// Check if we can move the block to the right
					if (j < 3 && board[i][j + 1] == 0
							&& board[i + 1][j + 1] == 0) {
						board[i][j + 1] = 2;
						board[i + 1][j + 1] = 2;
						board[i][j] = 0;
						board[i + 1][j] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i][j + 1] = 0;
						board[i + 1][j + 1] = 0;
						board[i][j] = 2;
						board[i + 1][j] = 2;
					}
					// Check if we can move the block to the left
					if (j > 0 && board[i][j - 1] == 0
							&& board[i + 1][j - 1] == 0) {
						board[i][j - 1] = 2;
						board[i + 1][j - 1] = 2;
						board[i][j] = 0;
						board[i + 1][j] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i][j - 1] = 0;
						board[i + 1][j - 1] = 0;
						board[i][j] = 2;
						board[i + 1][j] = 2;
					}
				}
			}
		}

		// Now check whether the 2*2 blocks can be moved into empty spaces
		outerloop_2: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 1) {
					// Check if this block can be moved right
					if (j < 2 && board[i][j + 2] == 0
							&& board[i + 1][j + 2] == 0) {
						// Works!
						board[i][j + 2] = 1;
						board[i + 1][j + 2] = 1;
						board[i][j] = 0;
						board[i + 1][j] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i][j + 2] = 0;
						board[i + 1][j + 2] = 0;
						board[i][j] = 1;
						board[i + 1][j] = 1;
					}
					// Check if this block can be moved left
					if (j > 0 && board[i][j - 1] == 0
							&& board[i + 1][j - 1] == 0) {
						// Works!
						board[i][j - 1] = 1;
						board[i + 1][j - 1] = 1;
						board[i][j + 1] = 0;
						board[i + 1][j + 1] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i][j - 1] = 0;
						board[i + 1][j - 1] = 0;
						board[i][j + 1] = 1;
						board[i + 1][j + 1] = 1;
					}
					// Check if this block can be moved down
					if (i < 3 && board[i + 2][j] == 0
							&& board[i + 2][j + 1] == 0) {
						// Works!
						board[i + 2][j] = 1;
						board[i + 2][j + 1] = 1;
						board[i][j] = 0;
						board[i][j + 1] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i + 2][j] = 0;
						board[i + 2][j + 1] = 0;
						board[i][j] = 1;
						board[i][j + 1] = 1;
					}
					// Check if this block can be moved up
					if (i > 0 && board[i - 1][j] == 0
							&& board[i - 1][j + 1] == 0) {
						// Works!
						board[i - 1][j] = 1;
						board[i - 1][j + 1] = 1;
						board[i + 1][j] = 0;
						board[i + 1][j + 1] = 0;
						successors.add(new GameState(board, this.steps + 1,
								this, this.uniform_cost_search));
						board[i - 1][j] = 0;
						board[i - 1][j + 1] = 0;
						board[i + 1][j] = 1;
						board[i + 1][j + 1] = 1;
					}
					break outerloop_2;
				}
			}
		}

		// Now sort them in dictionary order
		Collections.sort(successors, (s1, s2) -> {
			return s1.getStateID().compareTo(s2.getStateID());
		});
		return successors;
	}

	// return the 20-digit number as ID
	public String getStateID() {
		String s = "";
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++)
				s += this.board[i][j];
		}
		return s;
	}

	public GameState getParent() {
		return this.parent;
	}

	public void printBoard() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++)
				System.out.print(this.board[i][j]);
			System.out.println();
		}
	}

	// check whether the current state is the goal
	public boolean goalCheck() {
		outerloop: for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 1 && i == 3 && j == 1) {
					return true;
				} else if (board[i][j] == 1) {
					break outerloop;
				}
			}
		}
		return false;
	}

	public int getSteps() {
		return this.steps;
	}

	public int getCost() {
		return this.cost;
	}

	// Return heuristic cost
	private int getHeuristicScore() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 1) {
					return Math.abs(i - 3) + Math.abs(j - 1);
				}
			}
		}
		return -1; // should never reach here
	}

	// add new methods for the GameState if necessary

}