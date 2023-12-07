import java.util.Scanner;

public class GameOfLife_ClientCode {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int dimension;
		
		// create toroidal board of default size 20x20
		ToroidalBoard tb = new ToroidalBoard();
		dimension = 20;
		
		// create game of life using that board
		GameOfLife gl = new GameOfLife(tb);
		
		// start the game
		gl.startGame();
		
		// Ask user to specify board dimensions
		System.out.println("\nSpecify board dimensions (20 - 30)");
		dimension = input.nextInt();
		
		// validate size
		while (dimension < 20 || dimension > 30) {
			System.out.println("Please enter a number between 20 and 30");
			dimension = input.nextInt();
		}
				
		// if user chose a number other than the default 20, create new board
		// and create a new game using that board
		if (dimension != 20) {
			tb = new ToroidalBoard(dimension);
			gl = new GameOfLife(tb);
		}
		
		System.out.println("\nGame Of Life Board: ");
		
		// print board
		tb.printGameBoard();
		
		// Allow user to turn specified cells into live cells
		System.out.println("Select cells to turn into live cells or enter -1 to stop changing cell states");
		
		boolean cont = true;
		int row, col;
		while (cont) {
			System.out.print("\nRow: ");
			row = input.nextInt();
			
			while (row < -1 || row > dimension) {
				System.out.println("Input out of range. Please enter a number"
						+ "between 0 and " + dimension + " or -1 to exit");
				row = input.nextInt();
			}
			
			if (row == -1) {
				cont = false;
			} else {
			
				System.out.print("Column: ");
				col = input.nextInt();
				
				while (col < -1 || col > dimension) {
					System.out.println("Input out of range. Please enter a number"
							+ "between 0 and " + dimension + " or -1 to exit");
					col = input.nextInt();
				}
				
				if (col == -1) {
					cont = false;
				} else {
					tb.setState(row, col, 1);
				}
			}
			
		}
		
		// print the game board, only indicating live cells
		tb.printGameBoard_OnlyLive();
		
		// Ask user how many generations to view
		System.out.println("\nHow many generations would you like to view?");
		int generations = input.nextInt();
		
		// validate generations input
		while(generations < 0) {
			System.out.println("\nOnly positiveinput allowed");
			generations = input.nextInt();
		}
		
		while(generations > 1000) {
			System.out.println("\nThis program does not allow more than 1000 generations");
			generations = input.nextInt();
		}
		
		input.nextLine();
		
		System.out.println("\nPress enter to start simulation");
		input.nextLine();
		
		// start simulation
		// set extinct boolean to false
		boolean stop = false;
		int generation = 1; // start with first generation
		
		// while population is not extinct or no longer going to change and generation number is less than limit...
		while (!stop && generation <= generations) {
			System.out.println("Generation " + generation + ":");
			
			// print this generation's population
			tb.printGameBoard_OnlyLive();
			
			// enqueue cells that need to be updated according to the rules of the Game Of Life..
			gl.enqueuCells();
			
			// get that circular queue
			CircularQueue<Cell> cq = gl.getCQ();

			// if no cells to update...
			if (cq.isEmpty()) {
				// check all cells
				boolean extinct = true;
				outerloop:
				for (row = 0; row < tb.getDimension(); row++) {
					for (col = 0; col < tb.getDimension(); col++) {
						// if a live cell found, that means this is a stable, unchanging pattern
						if (tb.getState(row, col) == 1) {
							extinct = false;
							System.out.println("All subsequent generations will forever be the same.");
							break outerloop;
						}
					}
				}
				
				// but if no live cell found, it means the population is extinct
				if (extinct) {
					System.out.println("Population extinct :(");
				}
				stop = true;
				
			// If there ARE cells to update...
			} else {
				// dequeue each cell and toggle the states of those cells.
				while (!cq.isEmpty()) {
					Cell dequeued = cq.dequeue();
					tb.toggleState(dequeued.getRow(), dequeued.getCol());
					
				}
				
				// increment generation
				generation++;
			}
		}

	}

}
