// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.first = 0;
        this.last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        // If queue is full, throw an exception
        if (this.isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        this.rb[last] = x;
        this.last += 1;
        if (this.last == this.capacity()) {
            this.last = 0;
        }
        fillCount += 1;

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        T retrVal;
        // If queue is empty, do nothing
        if (this.isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        retrVal = this.rb[first];
        this.rb[first] = null;   // (*)
        this.first += 1;
        if (this.first == this.capacity()){
            this.first = 0;
        }
        fillCount -= 1;
        return retrVal;

    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        // If queue is empty, return null
        if (this.isEmpty()){
            System.out.println("Queue is empty. Nothing to peek!");
            return null;
        }
        return this.rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    public class KeyIterator implements Iterator<T> {
        private int notionOfWhereHeIs;

        public KeyIterator() {
            notionOfWhereHeIs = 0;
        }

        public boolean hasNext() {
            return (notionOfWhereHeIs < capacity);
        }

        public T next() {
            T currentThing = rb[notionOfWhereHeIs];
            notionOfWhereHeIs += 1;
            return currentThing;
        }
    }

    public Iterator<T> iterator() {
        return new KeyIterator();
    }
    /** This iterator will return everything in ArrayRingBuffer including null.
     *  If I don't add (*) line in dequeue(), the second while println part will
     *  still give a b c null, because we only change the first and last to point
     *  the next enqueue/dequeue position but not change the content in ArrayRingBuffer
     *  itself. If I don't explain clearly, please try some examples and you will
     *  understand more!
     * */
    /*  // This part is intended to test iterator, which implements enhanced for loop.
        public static void main(String[] args) {
        ArrayRingBuffer<String> test = new ArrayRingBuffer<>(4);
        // test iterator
        test.enqueue("a");
        test.enqueue("b");
        test.enqueue("c");
        ArrayRingBuffer.KeyIterator testi = test.new KeyIterator();
        while (testi.hasNext()){
            System.out.println(testi.next());
        }
        test.dequeue();
        ArrayRingBuffer.KeyIterator testi1 = test.new KeyIterator();
        while (testi1.hasNext()){
            System.out.println(testi1.next());
        }
    }*/
}
