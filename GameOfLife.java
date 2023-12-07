
public class GameOfLife {
	protected ToroidalBoard board;
	private CircularQueue<Cell> cq;
	
	/**
	 * Constructor: Game Of Life
	 * @param board toroidal board
	 */
	public GameOfLife(ToroidalBoard board) {
		this.board = board;
		cq = new CircularQueue<Cell>();
	}
	
	
	/**
	 * returns Game Of Life's circular queue
	 * @return
	 */
	public CircularQueue<Cell> getCQ() {
		return cq;
	}
	
	
	/**
	 * Method that introduces game
	 */
	public void startGame() {
		System.out.println("Welcome to the Game Of Life!");
	}
	
	
	/**
	 * Method that enqueues cells that need to be updated 
	 * onto the circular queue
	 */
	public void enqueuCells() {
		for (int row = 0; row < board.getDimension(); row++) {
			for (int col = 0; col < board.getDimension(); col++) {

				if (updateNeeded(row, col)) {
					Cell cell = board.getCell(row, col);
					cq.enqueue(cell);
				}
			}
		}
	}
	
	
	/**
	 * Method that determines if a cell needs to be updated, 
	 * according to the game rules
	 * @param row current cell's row index
	 * @param col current cell's column index
	 * @return true or false - whether cell needs to be updated
	 */
	public boolean updateNeeded(int row, int col) {
		// IF A CELL WAS ALIVE...
		if (board.getState(row, col) == 1) {
			
			//Under-population: A live cell with fewer than two live neighbors dies.
			if (board.neighborCount(row, col) < 2) {
				return true;
			// Over-population: A live cell with more than three live neighbors dies.
			} else if (board.neighborCount(row, col) > 3) {
				return true;
			// Survival: A live cell with two or three live neighbors remains alive.
			} else {
				return false;
			}
			
		// IF A CELL WAS DEAD...
		} else {
			// Reproduction: A dead cell with exactly three live neighbors becomes alive.
			if (board.neighborCount(row, col) == 3) {
				return true;
			// Otherwise it remains dead
			} else {
				return false;
			}
		}
		
	}
}