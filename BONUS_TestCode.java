import java.util.Scanner;

public class BONUS_TestCode {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int dimension;
		int row;
		int col;
		
		System.out.println("Welcome to the Game of Life TEST CODE");
		
		System.out.println("\n Printing default board (20x20)...");
		ToroidalBoard tb = new ToroidalBoard();
		tb.printGameBoard();
		
		System.out.println("\nPress enter to continue");
		input.nextLine();
		
		System.out.println("\nChoose a cell to change it's state.");
		System.out.print("Row index: ");
		row = input.nextInt();
		
		while(row < 0 || row > 19) {
			System.out.println("\nRow index out of bounds. Please re-enter. \n\nRow index: ");
			row = input.nextInt();
		}
		
		System.out.print("\n\nColumn index: ");
		col = input.nextInt();
		
		while (col < 0 || col > 19) {
			System.out.println("\nColumn index out of bounds. Please re-enter\n\nColumn index: ");
			col = input.nextInt();
		}
		
		System.out.println("\nToggling state of board cell at row index " + row +", column index " + col);
		tb.toggleState(row, col);
		
		
		tb.printGameBoard();
		
		System.out.println("\nPress enter to view only live cells");
		input.nextLine();
		input.nextLine();

		
		tb.printGameBoard_OnlyLive();
		
		System.out.println("\nPress enter to continue");
		input.nextLine();

		System.out.println("Create a custom board!");
		System.out.println("\nSpecify board dimensions (20 - 30):");
		dimension = input.nextInt();
		
		ToroidalBoard tb2 = new ToroidalBoard(dimension);
		
		tb2.printGameBoard();
		
		System.out.println("\nPress enter to continue");
		input.nextLine();
		input.nextLine();

		System.out.println("\nInstantiating double array of 0's and 1's (with mistaken 2)");
		int[][] states = {
					{0, 2, 0},
					{1, 1, 0},
					{0, 0, 0}
				};
		
		System.out.println("\nArray:");
		for (int out = 0; out < states.length; out++) {
			for (int in = 0; in < states[out].length; in++) {
				System.out.print(states[out][in] + ", ");
			}
			System.out.println();
		}
		
		System.out.println("\nCreating a 3x3 board filled with values of array");
		ToroidalBoard tb3 = new ToroidalBoard(states);
		
		tb3.printGameBoard();
		
		System.out.println("\nPress enter to continue");
		input.nextLine();

		System.out.println("\nNow we will print out the neighbor states of 4 random cells on this board.");

		System.out.println("\nPress enter if you're ready");
		input.nextLine();
		
		System.out.println("Row 0, Column 1: ");
		System.out.println("\tNorth Neighbor State: " + tb3.northNeighborState(0, 1));
		System.out.println("\tNorth-East Neighbor State: " + tb3.northEastNeighborState(0, 1));
		System.out.println("\tEast Neighbor State: " + tb3.eastNeighborState(0, 1));
		System.out.println("\tSouth-East Neighbor State: " + tb3.southEastNeighborState(0, 1));
		System.out.println("\tSouth Neighbor State: " + tb3.southNeighborState(0, 1));
		System.out.println("\tSouth-West Neighbor State: " + tb3.southWestNeighborState(0, 1));
		System.out.println("\tWest Neighbor State: " + tb3.westNeighborState(0, 1));
		System.out.println("\tNorth-West Neighbor State: " + tb3.northWestNeighborState(0, 1));
		System.out.println("Row 1, Column 2 neighbor count: " + tb3.neighborCount(0, 1));
		System.out.println();

		
		System.out.println("Row 1, Column 1:");
		System.out.println("\tNorth Neighbor State: " + tb3.northNeighborState(1, 1));
		System.out.println("\tNorth-East Neighbor State: " + tb3.northEastNeighborState(1, 1));
		System.out.println("\tEast Neighbor State: " + tb3.eastNeighborState(1, 1));
		System.out.println("\tSouth-East Neighbor State: " + tb3.southEastNeighborState(1, 1));
		System.out.println("\tSouth Neighbor State: " + tb3.southNeighborState(1, 1));
		System.out.println("\tSouth-West Neighbor State: " + tb3.southWestNeighborState(1, 1));
		System.out.println("\tWest Neighbor State: " + tb3.westNeighborState(1, 1));
		System.out.println("\tNorth-West Neighbor State: " + tb3.northWestNeighborState(1, 1));
		System.out.println("Row 2, Column 2 neighbor count: " + tb3.neighborCount(1, 1));

		System.out.println();
		
		System.out.println("Row 2, Column 0:");
		System.out.println("\tNorth Neighbor State: " + tb3.northNeighborState(2, 0));
		System.out.println("\tNorth-East Neighbor State: " + tb3.northEastNeighborState(2, 0));
		System.out.println("\tEast Neighbor State: " + tb3.eastNeighborState(2, 0));
		System.out.println("\tSouth-East Neighbor State: " + tb3.southEastNeighborState(2, 0));
		System.out.println("\tSouth Neighbor State: " + tb3.southNeighborState(2, 0));
		System.out.println("\tSouth-West Neighbor State: " + tb3.southWestNeighborState(2, 0));
		System.out.println("\tWest Neighbor State: " + tb3.westNeighborState(2, 0));
		System.out.println("\tNorth-West Neighbor State: " + tb3.northWestNeighborState(2, 0));
		System.out.println("Row 3, Column 1 neighbor count: " + tb3.neighborCount(2, 0));

		System.out.println();
		
		System.out.println("Printing another gameboard for ease of visibility");
		tb3.printGameBoard();

		System.out.println("Row 2, Column 2:");
		System.out.println("\tNorth Neighbor State: " + tb3.northNeighborState(2, 2));
		System.out.println("\tNorth-East Neighbor State: " + tb3.northEastNeighborState(2, 2));
		System.out.println("\tEast Neighbor State: " + tb3.eastNeighborState(2, 2));
		System.out.println("\tSouth-East Neighbor State: " + tb3.southEastNeighborState(2, 2));
		System.out.println("\tSouth Neighbor State: " + tb3.southNeighborState(2, 2));
		System.out.println("\tSouth-West Neighbor State: " + tb3.southWestNeighborState(2, 2));
		System.out.println("\tWest Neighbor State: " + tb3.westNeighborState(2, 2));
		System.out.println("\tNorth-West Neighbor State: " + tb3.northWestNeighborState(2, 2));
		System.out.println("Row 3, Column 3 neighbor count: " + tb3.neighborCount(2, 2));

		System.out.println();	
		
		
		System.out.println("\nPress enter to continue");
		input.nextLine();
		
		System.out.println("\nCreating a new, bigger array...");
		
		int[][] states2 = {
				{0, 0, 0, 0, 0},
				{1, 0, 1, 0, 0},
				{0, 1, 0, 0, 0},
				{0, 0, 1, 0, 0},
				{1, 0, 0, 0, 0}
			};
		
		System.out.println("\nArray:");
		for (int out = 0; out < states2.length; out++) {
			for (int in = 0; in < states2[out].length; in++) {
				System.out.print(states2[out][in] + ", ");
			}
			System.out.println();
		}
		
		
		ToroidalBoard tb5 = new ToroidalBoard(states2);
				
		tb5.printGameBoard();
		
		System.out.println("\nPress enter to continue");
		input.nextLine();
		
		System.out.println("\nCreating Game Of Life game");
		
		GameOfLife gl2 = new GameOfLife(tb5);
		
		System.out.println("\nHow many generations would you like to view?");
		int generations = input.nextInt();
		
		while(generations < 0) {
			System.out.println("\nOnly positiveinput allowed");
			generations = input.nextInt();
		}
		
		while(generations > 1000) {
			System.out.println("\nThis program does not allow more than 1000 generations");
			generations = input.nextInt();
		}
		
		input.nextLine();
		
		System.out.println("\nThe following simulation only prints the live cells");
		System.out.println("\nPress enter to continue");
		input.nextLine();
		
		
		
		for (int generation = 1; generation <= generations; generation++) {
			System.out.println("Generation " + generation + ":");
			tb5.printGameBoard_OnlyLive();
			
			gl2.enqueuCells();
			
			CircularQueue<Cell> cq = gl2.getCQ();

			if (cq.isEmpty()) {
				System.out.println("Population extinct :(");
				break;
			}
			
			while (!cq.isEmpty()) {
				Cell dequeued = cq.dequeue();
				tb5.toggleState(dequeued.getRow(), dequeued.getCol());
				
			}
		}
		
		System.out.println("\nNow let's try that with a bigger board");
		
		System.out.println("\nPress enter to continue");
		input.nextLine();
		
		System.out.println("\nCreating new board, 22x22, all cells set to 0...");
		
		ToroidalBoard tb22 = new ToroidalBoard(22);
		
		System.out.println("\nSetting random cell states to 1...");
		
		
		tb22.setState(8, 15, 1);
		tb22.setState(9,14, 1);
		tb22.setState(9, 17, 1);
		tb22.setState(10,14, 1);
		tb22.setState(11,14, 1);
		tb22.setState(11, 15, 1);
		tb22.setState(1, 5, 1);
		tb22.setState(3, 4, 1);
		tb22.setState(2, 7, 1);
		tb22.setState(2, 11, 1);
		tb22.setState(18,1, 1);
		tb22.setState(19, 3, 1);
		tb22.setState(19, 15, 1);
		tb22.setState(9,10, 1);
		tb22.setState(12, 17, 1);
		tb22.setState(10,16, 1);
		tb22.setState(20, 20, 1);
		tb22.setState(10, 20, 1);
		tb22.setState(11, 1, 1);
		tb22.setState(4, 4, 1);
		tb22.setState(9, 7, 1);
		tb22.setState(16, 16, 1);
		tb22.setState(8, 8, 1);
		tb22.setState(17, 17, 1);
		tb22.setState(0,  0, 1);
		
		tb22.printGameBoard();

		System.out.println("\nCreating new game with this board...");
		
		GameOfLife gl22 = new GameOfLife(tb22);

		System.out.println("\nHow many generations would you like to view?");
		generations = input.nextInt();
		
		while(generations < 0) {
			System.out.println("\nOnly positiveinput allowed");
			generations = input.nextInt();
		}
		
		while(generations > 1000) {
			System.out.println("\nThis program does not allow more than 1000 generations");
			generations = input.nextInt();
		}
		
		input.nextLine();
		
		System.out.println("\nPress enter to continue");
		input.nextLine();
		
		boolean extinct = false;
		int generation = 1;
		while (!extinct && generation <= generations) {
			System.out.println("Generation " + generation + ":");
			tb22.printGameBoard_OnlyLive();
			
			gl22.enqueuCells();
			
			CircularQueue<Cell> cq = gl22.getCQ();

			if (cq.isEmpty()) {
				System.out.println("Population extinct :(");
				extinct = true;
			}
			
			while (!cq.isEmpty()) {
				Cell dequeued = cq.dequeue();
				tb22.toggleState(dequeued.getRow(), dequeued.getCol());
				
			}
			
			generation++;
		}
	
	System.out.println("\nEnd of testing program. Goodbye!");
	input.close();
	
	}

}
