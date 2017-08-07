package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(2);
        arb.enqueue(12.0);
        arb.enqueue(24.0);
        assertEquals(arb.dequeue(),12,0.1);
        arb.enqueue(20.0);
        assertEquals(arb.dequeue(),24,0.1);
        assertEquals(arb.dequeue(),20,0.1);
        assertEquals(arb.isEmpty(), true);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
