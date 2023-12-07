
public class CircularQueueTest {

    public static void main(String[] args) {
        CircularQueue<Integer> circularQueue = new CircularQueue<>();

        // Enqueue elements
        for (int i = 1; i <= 5; i++) {
            circularQueue.enqueue(i);
        }

        // Print the initial state
        System.out.println("Initial Queue: " + circularQueue);

        // Peek
        System.out.println("\nPeek at first in line:");
        System.out.println("Peek: " + circularQueue.peek());
        // Dequeue elements
        int dequeueCount = 3;
        System.out.println("\nDequeue " + dequeueCount + " elements:");
        for (int i = 0; i < dequeueCount; i++) {
            Integer dequeued = circularQueue.dequeue();
            System.out.println("Dequeued: " + dequeued);
        }

        // Peek
        System.out.println("\nPeek at first in line:");
        System.out.println("Peek: " + circularQueue.peek());
        
        // Print the updated state
        System.out.println("\nUpdated Queue: " + circularQueue);

        // Enqueue more elements
        System.out.println("\nEnqueue more elements:");
        for (int i = 6; i <= 8; i++) {
            circularQueue.enqueue(i);
        }

        // Print the final state
        System.out.println("\nFinal Queue: " + circularQueue);
        
        System.out.println("\nClear queue");
        circularQueue.clear();
        System.out.println("\nUpdated Queue: " + circularQueue);
    }
}
