
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

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class AStarSearch {
	private Queue<GameState> openSet;
	private HashMap<String, GameState> closedSet;
	private HashMap<String, GameState> openSetHash;

	/**
	 * Implementation of A* search
	 * 
	 * @param flag, integer indicating mode of operation.
	 * @param state, GameState which provides the initial board setup.
	 */
	public void aStarSearch(int flag, GameState state) {
		// feel free to using other data structures if necessary
		openSet = new PriorityQueue<>(stateComparator);
		closedSet = new HashMap<>();
		openSetHash = new HashMap<>();
		int goalCheck = 0;
		int maxOPEN = -1;
		int maxCLOSED = -1;
		int steps = 0;

		// Place the start state into open
		openSet.add(state);
		openSetHash.put(state.getStateID(), state);
		int iteration = 0;
		while (!openSet.isEmpty()) {
			iteration++;
			GameState node = openSet.remove();
			openSetHash.remove(node.getStateID());
			// Now place this on closed.
			closedSet.put(node.getStateID(), node);

			// Print information.
			if (flag == 200 || flag == 400) {
				System.out.println("iteration " + iteration);
				printNodeInformation(node);
			}

			// Goal check
			goalCheck++;
			if (node.goalCheck()) {
				// goal is reached
				steps = node.getSteps();
				if (maxCLOSED < closedSet.size()) {
					maxCLOSED = closedSet.size();
				}
				if (flag == 300 || flag == 500) {
					printPathInformation(node);
				}
				break;
			}

			// Traverse the successors
			List<GameState> successors = node.getNextStates();
			for (int i = 0; i < successors.size(); i++) {
				GameState successor = successors.get(i);
				boolean alreadyPresent = false;

				// Traverse open set to check if the same node ID is already
				// there
				if (openSetHash.containsKey(successor.getStateID())) {
					// Check if this successor is more optimal
					GameState g = openSetHash.get(successor.getStateID());
					if (successor.getSteps() < g.getSteps()) {
						openSet.remove(g);
						openSet.add(successor);
						openSetHash.put(g.getStateID(), g); // Old value is
															// replaced
					}
					alreadyPresent = true;
				}

				// Traverse closed set to check if the same node ID is already
				// there
				if (closedSet.containsKey(successor.getStateID())) {
					GameState g = closedSet.get(successor.getStateID());
					if (successor.getSteps() < g.getSteps()) {
						// Remove from closed set and move into open set
						closedSet.remove(g.getStateID());
						openSet.add(successor);
						openSetHash.put(successor.getStateID(), successor);
					}
					alreadyPresent = true;
				}

				// If not already in open or closed, add into open
				if (!alreadyPresent) {
					openSet.add(successor);
					openSetHash.put(successor.getStateID(), successor);
				}
			}
			// Print information
			printOpenList(flag);
			if (openSet.size() > maxOPEN) {
				maxOPEN = openSet.size();
			}
			if (closedSet.size() > maxCLOSED) {
				maxCLOSED = closedSet.size();
			}
			printClosedList(flag);
		}

		if (flag == 300 || flag == 500) {
			System.out.println("goalCheckTimes " + goalCheck);
			System.out.println("maxOPENSize " + maxOPEN);
			System.out.println("maxCLOSEDSize " + maxCLOSED);
			System.out.println("steps " + steps);
		}
	}

	/**
	 * Print the nodes in OPEN list.
	 * 
	 * @param flag, indicating mode of operation.
	 */
	private void printOpenList(int flag) {
		if (flag == 200 || flag == 400) {
			System.out.println("OPEN");
			Iterator<GameState> openList = openSet.iterator();
			while (openList.hasNext()) {
				printNodeInformation(openList.next());
			}
		}
	}

	/**
	 * Print the nodes in CLOSED list.
	 * 
	 * @param flag, indicating mode of operation.
	 */
	private void printClosedList(int flag) {
		if (flag == 200 || flag == 400) {
			System.out.println("CLOSED");
			Iterator<Map.Entry<String, GameState>> closedList = closedSet
					.entrySet().iterator();
			while (closedList.hasNext()) {
				Map.Entry<String, GameState> m = closedList.next();
				printNodeInformation(m.getValue());
			}
		}
	}

	/**
	 * Helper API to print the node information.
	 */
	private void printNodeInformation(GameState node) {
		System.out.println(node.getStateID());
		node.printBoard();
		System.out.println(node.getCost() + " " + node.getSteps() + " "
				+ (node.getCost() - node.getSteps()));
		if (node.getParent() == null) {
			System.out.println("null");
		} else {
			System.out.println(node.getParent().getStateID());
		}
	}

	/**
	 * Helper API to print the path from initial state to the given GameState
	 * node.
	 */
	private void printPathInformation(GameState node) {
		// Traversing backpointers gives you nodes in the reverse order.
		// Hence, use a stack to invert the order of printing.
		Stack<GameState> stack = new Stack<GameState>();
		stack.push(node);
		GameState parent = node.getParent();
		while (parent != null) {
			stack.push(parent);
			parent = parent.getParent();
		}
		while (!stack.isEmpty()) {
			stack.pop().printBoard();
			System.out.println();
		}
	}

	/**
	 * Comparator of two GameState objects.
	 */
	private Comparator<GameState> stateComparator = new Comparator<GameState>() {
		@Override
		public int compare(GameState o1, GameState o2) {
			if (o1.getCost() - o2.getCost() != 0)
				return o1.getCost() - o2.getCost();
			else
				return o1.getStateID().compareTo(o2.getStateID());
		}
	};
}