package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.StdRandom;


public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    public boolean haveNiceHashCodeSpread(Set<ComplexOomage> oomages) {
        /* TODO: Write a utility function that ensures that the oomages have
         * hashCodes that would distribute them fairly evenly across
         * buckets To do this, mod each's hashCode by M = 10,
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int M = 10;
        int[] bucketSize = new int[M];
        for (ComplexOomage o : oomages){
            int hashcode = o.hashCode();
        	int bucketNum = (hashcode & 0x7FFFFFFF) % M;
        	bucketSize[bucketNum] += 1;
        }
        for (int i=0; i < M; i++){
        	if (bucketSize[i] < oomages.size() / 50 || bucketSize[i] > oomages.size() / 2.5 ){
        		return false;
        	}
        }
        return true;
    }


    @Test
    public void testRandomItemsHashCodeSpread() {
        HashSet<ComplexOomage> oomages = new HashSet<ComplexOomage>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(haveNiceHashCodeSpread(oomages));
    }

    @Test
    public void testWithDeadlyParams() {
        /* TODO: Create a Set that shows the flaw in the hashCode function.
         */
        Set<ComplexOomage> oomages = new HashSet<ComplexOomage>();

        int N = 2000;
        int LastDigit = 2;

        for (int i = 0; i < N; i += 1) {
            int X = StdRandom.uniform(1, N);
            List<Integer> params = new ArrayList<Integer>(X);

            for (int j = 0; j < X; j++) {
                int Y = StdRandom.uniform(0, 255);
                params.add(Y);
            }

            params.add(LastDigit);
            ComplexOomage oomage = new ComplexOomage(params);
            oomages.add(oomage);
        }
        // visualize(oomages, 10, 0.5);
        assertTrue(haveNiceHashCodeSpread(oomages));
    }


    /** private static void visualize(Set<ComplexOomage> set, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);
        int bucketsStartPos = 1;
        int bucketsPosDis = 1;
        int[] bucketsSize = new int[M];
        for (ComplexOomage o : set){
            int bucketsNum = (o.hashCode() & 0x7FFFFFFF) % M;
            bucketsSize[bucketsNum] += 1;
            o.draw(HashTableDrawingUtility.xCoord(bucketsStartPos
              + bucketsPosDis * bucketsSize[bucketsNum]), HashTableDrawingUtility.yCoord(bucketsNum,M), scale);
        }
    }*/
         

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
