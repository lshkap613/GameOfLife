
public class Cell {
	private int row;
	private int col;
	private int state;
	
	/**
	 * Cell constructor
	 * @param row row index of cell
	 * @param col col index of cell
	 * @param state state of cell
	 */
	public Cell(int row, int col, int state) {
		this.row = row;
		this.col = col;
		this.state = state;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
	
	
}
