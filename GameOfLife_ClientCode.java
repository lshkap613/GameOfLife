import java.util.Scanner;

public class GameOfLife_ClientCode {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int dimension;
		
		ToroidalBoard tb = new ToroidalBoard();
		dimension = 20;
		
		GameOfLife gl = new GameOfLife(tb);
		
		gl.startGame();
		
		System.out.println("\nSpecify board dimensions (20 - 30)");
		dimension = input.nextInt();
		
		while (dimension < 20 || dimension > 30) {
			System.out.println("Please enter a number between 20 and 30");
			dimension = input.nextInt();
		}
				
		if (dimension != 20) {
			tb = new ToroidalBoard(dimension);
			gl = new GameOfLife(tb);
		}
		
		System.out.println("\nGame Of Life Board: ");
		
		tb.printGameBoard();
		
		System.out.println("Select cells to turn into live cells");
		System.out.println("or enter -1 to stop changing cell states");
		
		
		boolean cont = true;
		int row;
		int col;
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
		
		tb.printGameBoard_OnlyLive();
		
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
		
		System.out.println("\nPress enter to start simulation");
		input.nextLine();
		
		boolean extinct = false;
		int generation = 1;
		while (!extinct && generation <= generations) {
			System.out.println("Generation " + generation + ":");
			tb.printGameBoard_OnlyLive();
			
			gl.enqueuCells();
			
			CircularQueue<Cell> cq = gl.getCQ();

			if (cq.isEmpty()) {
				System.out.println("Population extinct :(");
				extinct = true;
			}
			
			while (!cq.isEmpty()) {
				Cell dequeued = cq.dequeue();
				tb.toggleState(dequeued.getRow(), dequeued.getCol());
				
			}
			
			generation++;
		}

	}

}
