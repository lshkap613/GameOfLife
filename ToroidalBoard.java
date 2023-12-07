import java.util.Scanner;

public class ToroidalBoard {
	private Cell[] board;
	private int dimension;
	private Scanner input;
	
	
	/**
	 * Constructor - default size 20x20
	 */
	public ToroidalBoard() {
		// set dimension to 20, create 20x20 board
		dimension = 20;
		board = new Cell[dimension * dimension];
		
		// fill board with 0's
		for (int row=0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {
				board[row* dimension + col] =  new Cell(row, col, 0);
			}
		}
	}
	
	
	/**
	 * Constructor: Set dimension between 20x20 and 30x30
	 * @param dimension - board dimension
	 */
	public ToroidalBoard(int dimension) {
		// restrict board size to between 20 and 30
		while (dimension < 20 || dimension > 30) {
			System.out.println("Please specify a dimension between 20 and 30 (inclusive)");
			input = new Scanner(System.in);
			dimension = input.nextInt();
		}
		
		// set dimension, create board
		this.dimension = dimension;
		board = new Cell[dimension *dimension];
		
		// fill board with 0's
		for (int row=0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {
				board[row * dimension + col] = new Cell(row, col, 0);
			}
		}
	}
	
	
	/**
	 * Constructor: Using 2D array. (No size restrictions.)
	 * @param boardSetUp 2d array of 0's and 1's
	 * This method adjusts inputted array if it contains an illegal
	 * value by replacing that value with a 1
	 */
	public ToroidalBoard(int[][] boardSetUp) {
		try{
			validateArray(boardSetUp);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("All illegal values will be replaced with 1's");
						
			for (int out = 0; out < boardSetUp.length; out++) {
				for (int in = 0; in < boardSetUp[out].length; in++) {
					if (boardSetUp[out][in] != 0 && boardSetUp[out][in] != 1) {
						boardSetUp[out][in] = 1;
					} 
				}
			}			
		}
		
		this.dimension = boardSetUp.length; // set board dimension based on array length
		
		// create board
		board = new Cell[dimension * dimension];
		
		// Fill board with 0's and 1's based on the array
		for (int row=0; row < dimension; row++) {
			for (int col=0; col < dimension; col++) {
				// initialize each cell of game board char determined by boardSetUp double array
				board[row * dimension + col] = new Cell(row, col, boardSetUp[row][col]);
			}
		}
	}
	
	
	/**
	 * Method that returns cell at selected board index
	 * @param row row index of cell
	 * @param col
	 * @return
	 */
	public Cell getCell(int row, int col) {
		return board[row * dimension + col];
	}
	/**
	 * Method checks to make sure array used to set up the toroidal board only contains 0's and 1's
	 * 
	 */
	public static void validateArray(int[][] boardSetUp) {
		for (int out = 0; out < boardSetUp.length; out++) {
			for (int in = 0; in < boardSetUp[out].length; in++) {
				if (boardSetUp[out][in] != 0 && boardSetUp[out][in] != 1) {
					throw new IllegalArgumentException("\nArray contains values other than 0 and 1");
				}
			}
		}
	}
	
	
	/**
	 * Method to get the number of rows/columns of the game board
	 * @return int number of rows/columns 
	 */
	public int getDimension() {
		return this.dimension;
	}
	
	
	/**
	 * @param row row index of requested cell
	 * @param col column index of requested cell
	 * @return char at requested cell
	 */
	public int getState(int row, int col) {
		if (row < 0 || row > dimension || col < 0 || col > dimension) {
			throw new IllegalArgumentException("This cell does not exist");
		}
		return board[row * dimension + col].getState();
	}
	

	/**
	 * Method to set the state of a cell
	 * @param row row index of requested cell
	 * @param col column index of requested cell
	 * @param state 0 or 1 value to set cell
	 */
	public void setState(int row, int col, int state) {
		// validate row parameter
		while (row < 0 || row > dimension - 1) {
			System.out.println("Invalide row index. Please enter a number between 0 and " + (dimension - 1));
			row = input.nextInt();
		}
		
		// validate column parameter
		while (col < 0 || col > dimension - 1) {
			System.out.println("Invalide row index. Please enter a number between 0 and " + (dimension - 1));
		}
		
		// validate state parameter
		while (state != 0 && state != 1) {
			System.out.println("Invalide state. Please enter 0 or 1");
			state = input.nextInt();
		}
		
		board[row * dimension + col].setState(state);
	}
	
	
	/**
	 * Change state of specified cell from 0 to 1 or from 1 to 0
	 * @param row row index of specified cell
	 * @param col column index of specified cell
	 */
	public void toggleState(int row, int col) {
		// validate row parameter
		while (row < 0 || row > dimension - 1) {
			System.out.println("Invalide row index. Please enter a number between 0 and " + (dimension - 1));
			row = input.nextInt();
		}
		
		// validate column parameter
		while (col < 0 || col > dimension - 1) {
			System.out.println("Invalide row index. Please enter a number between 0 and " + (dimension - 1));
		}

		// toggle state
		if (board[row * dimension + col].getState() == 0) {
			board[row * dimension + col].setState(1);
		} else {
			board[row * dimension + col].setState(0);;
		}
	}
	
	
	/**
	 * Method to check what the state of the selected cell's "northern neighbor"
	 * @param row row index of selected cell
	 * @param col column index of selected cell
	 * @return state of selected cell's northern neighbor
	 */
	public int northNeighborState(int row, int col) {
		int neighborRow;
		int neighborCol = col;
		
		// if first row, northern neighbor is on last row
		if (row == 0) {
			neighborRow = dimension - 1;
		// Otherwise, in row above
		} else {
			neighborRow = row - 1;
		}
				
		return board[neighborRow * dimension + neighborCol].getState();
	}
	
	
	/**
	 * Method to check what the state of the selected cell's "north-Eastern neighbor"
	 * @param row row index of selected cell
	 * @param col column index of selected cell
	 * @return state of selected cell's North-Eastern neighbor
	 */
	public int northEastNeighborState(int row, int col) {
		int neighborRow;
		int neighborCol;
		
		// If first row, NE neighbor in last row
		if (row == 0) {
			neighborRow = dimension - 1;
		// ...otherwise in row above
		} else {
			neighborRow = row - 1;
		}
		
		// If last column, NE neighbor in first column
		if (col == dimension - 1) {
			neighborCol = 0;
		// ...otherwise in next column
		} else {
			neighborCol = col + 1;
		}
				
		return board[neighborRow * dimension + neighborCol].getState();
	}
	
	
	/**
	 * Method to check what the state of the selected cell's "Eastern neighbor"
	 * @param row row index of selected cell
	 * @param col column index of selected cell
	 * @return state of selected cell's Eastern neighbor
	 */
	public int eastNeighborState(int row, int col) {
		int neighborRow = row;
		int neighborCol;
		
		// If last column, E neighbor in first column
		if (col == dimension - 1) {
			neighborCol = 0;
		// ...otherwise in next column
		} else {
			neighborCol = col + 1;
		}
				
		return board[neighborRow * dimension + neighborCol].getState();
	}
	
	
	/**
	 * Method to check what the state of the selected cell's "South-Eastern neighbor"
	 * @param row row index of selected cell
	 * @param col column index of selected cell
	 * @return state of selected cell's South-Eastern neighbor
	 */
	public int southEastNeighborState(int row, int col) {
		int neighborRow;
		int neighborCol;
		
		// if last row, SE neighbor in first row
		if (row == dimension - 1) {
			neighborRow = 0;
		// otherwise in next row
		} else {
			neighborRow = row + 1;
		}
		
		// If last column, SE neighbor in first col
		if (col == dimension - 1) {
			neighborCol = 0;
		// otherwise in next col
		} else {
			neighborCol = col + 1;
		}
				
		return board[neighborRow * dimension + neighborCol].getState();
	}
	
	
	/**
	 * Method to check what the state of the selected cell's "Southern neighbor"
	 * @param row row index of selected cell
	 * @param col column index of selected cell
	 * @return state of selected cell's Southern neighbor
	 */
	public int southNeighborState(int row, int col) {
		int neighborRow;
		int neighborCol = col;
		
		// if last row, S neighbor in first row
		if (row == dimension - 1) {
			neighborRow = 0;
		// otherwise in next row
		} else {
			neighborRow = row + 1;
		}
				
		return board[neighborRow  * dimension + neighborCol].getState();
	}
	
	
	/**
	 * Method to check what the state of the selected cell's "South-Western neighbor"
	 * @param row row index of selected cell
	 * @param col column index of selected cell
	 * @return state of selected cell's South-Western neighbor
	 */
	public int southWestNeighborState(int row, int col) {
		int neighborRow;
		int neighborCol;
		
		// if last row, SW neighbor in first row
		if (row == dimension - 1) {
			neighborRow = 0;
		// otherwise next row
		} else {
			neighborRow = row + 1;
		}
		
		// if first col, SW neighbor in last col
		if (col == 0) {
			neighborCol = dimension - 1;
		// otherwise in previous column
		} else {
			neighborCol = col - 1;
		}
				
		return board[neighborRow  * dimension + neighborCol].getState();
	}
	
	
	/**
	 * Method to check what the state of the selected cell's "Western neighbor"
	 * @param row row index of selected cell
	 * @param col column index of selected cell
	 * @return state of selected cell's Western neighbor
	 */
	public int westNeighborState(int row, int col) {
		int neighborRow = row;
		int neighborCol;
		
		// if first column, W neighbor in last column
		if (col == 0) {
			neighborCol = dimension - 1;
		// otherwise in previous column
		} else {
			neighborCol = col - 1;
		}
				
		return board[neighborRow  * dimension + neighborCol].getState();
	}
	
	
	/**
	 * Method to check what the state of the selected cell's "North-Western neighbor"
	 * @param row row index of selected cell
	 * @param col column index of selected cell
	 * @return state of selected cell's North-Western neighbor
	 */
	public int northWestNeighborState(int row, int col) {
		int neighborRow;
		int neighborCol;
		
		// if first row, NW neighbor in last row
		if (row == 0) {
			neighborRow = dimension - 1;
		// otherwise in previous row
		} else {
			neighborRow = row - 1;
		}
		
		// if in first column, NW neighbor in last column
		if (col == 0) {
			neighborCol = dimension - 1;
		// otherwise in previous column
		} else {
			neighborCol = col - 1;
		}
				
		return board[neighborRow  * dimension + neighborCol].getState();
	}
	
	
	/**
	 * Method that counts total number of selected cell's live neighbors
	 * @param row row index of selected cell
	 * @param col column index of selected cell
	 * @return total number of selected cell's live neighbors
	 */
	public int neighborCount(int row, int col) {
		int neighborCount = northNeighborState(row, col)
							+ northEastNeighborState(row,col)
							+ eastNeighborState(row, col)
							+ southEastNeighborState(row,col)
							+ southNeighborState(row, col)
							+ southWestNeighborState(row,col)
							+ westNeighborState(row, col)
							+ northWestNeighborState(row,col);
	
		return neighborCount;
	}
	
	
	/**
	 * This method prints out the game board.
	 */
	public void printGameBoard() {		
		// 3.4 is a non-precise factor I came up with when playing around with different 
		// board sizes and the un-proportional length of the dash
		double dashes = Math.floor(this.dimension * 3.4); 
		
		System.out.println();
		
		// print column indexes
		System.out.print("\t");
		for (int col = 0; col < dimension; col++) {
			if (col < 10) {
				System.out.print("| " + col + " ");
			} else {
				System.out.print("| " + col);

			}
		}
		System.out.print("|\t\n\n\n");

		// print top dashed line
		System.out.print("\t");
		for (int length = 0; length < this.dimension; length++) {
			for (int dash = 0; dash < dashes/this.dimension; dash++) {
				System.out.print("-");
			}
		}
		System.out.println();
		
		// PRINT BOARD
		// for each row...
		for (int row = 0; row < this.dimension; row++) {
			
			System.out.print(row + "\t");
			
			// print out each cell 
			for (int col = 0; col < this.dimension; col++) {
				
				System.out.print("| " + board[row * dimension + col].getState() + " ");	
				
				if (col == dimension - 1) {
					System.out.print("|\t");
				}
			}			
			
			// after each row, print out dashes, the amount of which varies according to board size
			System.out.println();
			System.out.print("\t");
			for (int length = 0; length < this.dimension; length++) {
				for (int dash = 0; dash < dashes/this.dimension; dash++) {
					System.out.print("-");
				}
			}
			
			System.out.print("\t\n");
		}
		
		System.out.println();
		
	}
	
	
	/**
	 * This method prints the game board but only shows the live neighbors. It is implied
	 * that the empty game board cells represent 0's, or dead cells
	 */
	public void printGameBoard_OnlyLive() {		
		// 3.4 is a non-precise factor I came up with when playing around with different 
		// board sizes and the unproportional length of the dash
		double dashes = Math.floor(this.dimension*3.4); 
		
		System.out.println();
		
		// print column indexes
		System.out.print("\t");
		for (int col = 0; col < dimension; col++) {
			if (col < 10) {
				System.out.print("| " + col + " ");
			} else {
				System.out.print("| " + col);

			}
		}
		System.out.print("|\t\n\n\n");

		// print top dashed line
		System.out.print("\t");
		for (int length = 0; length < this.dimension; length++) {
			for (int dash = 0; dash < dashes/this.dimension; dash++) {
				System.out.print("-");
			}
		}
		System.out.println();
		
		// PRINT BOARD
		// for each row...
		for (int row = 0; row < this.dimension; row++) {
			
			System.out.print(row + "\t");
			
			// print out each cell 
			for (int col = 0; col < this.dimension; col++) {
				
				if ( board[row * dimension + col].getState() == 1) {
					System.out.print("| " + 1 + " ");	
				} else {
					System.out.print("|   ");	
				}
				if (col == dimension - 1) {
					System.out.print("|\t");
				}
			}			
			
			// after each row, print out dashes, the amount of which varies according to board size
			System.out.println();
			System.out.print("\t");
			for (int length = 0; length < this.dimension; length++) {
				for (int dash = 0; dash < dashes/this.dimension; dash++) {
					System.out.print("-");
				}
			}
			
			System.out.print("\t\n");
		}
		
		System.out.println();
		
	}
	
}