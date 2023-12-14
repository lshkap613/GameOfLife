import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToroidalBoard_Test {
	ToroidalBoard tb3;
	ToroidalBoard tb5;
	GameOfLife gl;
	
	@BeforeEach
	void setup() {
		int[][] states = {
				{0, 1, 0},
				{1, 1, 0},
				{0, 0, 0}
			};
		
		tb3 = new ToroidalBoard(states);
	}
	
	@Test
	void checkEachNeighborStateOfFirstCell() {
		int[] neighborStates = {
				tb3.northNeighborState(0, 0),
				tb3.northEastNeighborState(0, 0),
				tb3.eastNeighborState(0, 0),
				tb3.southEastNeighborState(0, 0),
				tb3.southNeighborState(0, 0),
				tb3.southWestNeighborState(0, 0),
				tb3.westNeighborState(0, 0),
				tb3.northWestNeighborState(0, 0)
		};
		
		int[] expectedStates = {0, 0, 1, 1, 1, 0, 0, 0};
		assertArrayEquals(neighborStates, expectedStates);
	}
	
	@Test
	void checkEachNeighborStateOfRandomCell() {
		int[] neighborStates = {
				tb3.northNeighborState(1, 2),
				tb3.northEastNeighborState(1, 2),
				tb3.eastNeighborState(1, 2),
				tb3.southEastNeighborState(1, 2),
				tb3.southNeighborState(1, 2),
				tb3.southWestNeighborState(1, 2),
				tb3.westNeighborState(1, 2),
				tb3.northWestNeighborState(1, 2)
		};
		
		int[] expectedStates = {0, 0, 1, 0, 0, 0, 1, 1};
		assertArrayEquals(neighborStates, expectedStates);
	}
	
	@Test
	void checkNeighborCountMethod() {
		int neighborCount = tb3.neighborCount(1, 1);
		
		assertEquals(neighborCount, 2);
	}
	
	@Test
	void checkToggleStateMethod() {
		tb3.toggleState(0, 0);
		
		assertEquals(tb3.getState(0, 0), 1);
	}
	
	@Test
	void allAndOnlyCellsThatNeedUpdatingEnqueued() {
		int[][] states2 = {
				{0, 0, 0, 0, 0},
				{1, 0, 1, 0, 0},
				{0, 1, 0, 0, 0},
				{0, 0, 1, 0, 0},
				{1, 0, 0, 0, 0}
			};
		tb5 = new ToroidalBoard(states2);
		gl = new GameOfLife(tb5);
		
		gl.enqueuCells(); // misspelled, I just realized, but I'd rather not change all my code now
		
		CircularQueue<Cell> queue = gl.getCQ();
		
		CircularQueue<Cell> expectedQueue = new CircularQueue<Cell>();
		expectedQueue.enqueue(new Cell(0, 1, 0));
		expectedQueue.enqueue(new Cell(1, 0, 1));
		expectedQueue.enqueue(new Cell(1, 1, 0));
		expectedQueue.enqueue(new Cell(1, 2, 1));
		expectedQueue.enqueue(new Cell(2, 2, 0));
		expectedQueue.enqueue(new Cell(3, 1, 0));
		expectedQueue.enqueue(new Cell(3, 2, 1));
		expectedQueue.enqueue(new Cell(4, 0, 1));


		while (!expectedQueue.isEmpty() ) {
			Cell dequeued = queue.dequeue();
			Cell expectedDequeued = expectedQueue.dequeue();
			
			assertEquals(dequeued.getRow(), expectedDequeued.getRow());
			assertEquals(dequeued.getCol(), expectedDequeued.getCol());
			assertEquals(dequeued.getState(), expectedDequeued.getState());
		}
			
	}
	
}
