
public class CircularQueue<T> implements IQueue<T>{
    private Node<T> rear;
    private int size;

    
    /**
     * Constructor. Sets rear value to null and
     * size to 0
     */
    public CircularQueue() {
        rear = null;
        size = 0;
    }

    
    /**
     * Inner class: Node
     * @param <T> abstract data type
     */
    private class Node<T> {
        T data;
        Node<T> next;

        
        /**
         * Node Constructor
         * @param data value of node
         * Next pointer set to null
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    
    /**
     * Method that enqueues a node
     * @param data value of node to enqueue
     */
    public void enqueue(T data) {
    	// Create new node
        Node<T> newNode = new Node<>(data);
        
        // If queue is empty, next pointer of enqueued 
        // node points to itself
        if (isEmpty()) {
            newNode.next = newNode;       
        }
        // Otherwise...
        else {
            newNode.next = rear.next;      // New node points to the head
            rear.next = newNode;            // Rear now points to the new node
            
        }
        
        rear = newNode;                  // The new node is now the rear
       
        size++;
    }

    
    /**
     * Method that dequeues node from circular queue
     */
    public T dequeue() {
    	
    	// If queue is not empty
        if (!isEmpty()) {	
        	// Get the data of the head
        	T data = rear.next.data;     
           
        	if (rear.next == rear) {
        		 // If only one node, make the queue empty
                rear = null;
                
            } else {
            	 // Skip the head in the circular linked list
            	rear.next = rear.next.next;     
            }
            size--;
            
            return data;
            
        }
        // If queue IS empty, return null
        return null;
    }

    
    /**
     * Checks if circular queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    
    /**
     * returns size of queue
     * @return int size of queue
     */
    public int size() {
        return size;
    }

    @Override
    /**
     * to string
     */
    public String toString() {
        if (isEmpty()) {
            return "Queue is empty";
        }

        StringBuilder result = new StringBuilder();
        Node<T> current = rear.next;
        do {
            result.append(current.data).append(" ");
            current = current.next;
        } while (current != rear.next);

        return result.toString().trim();
    }

    
	@Override
	/**
	 * returns value of last node on queue
	 */
	public T peek() {
		return (T) rear.next.data;
	}

	@Override
	/**
	 * clears the queue
	 */
	public void clear() {
		rear = null;
		size = 0;
	}
}
