package sg.util;
import org.junit.Test;
import static org.junit.Assert.*;

public class LineStarterArrayTest {
    @Test
    public void testEmptySize() {
        LineStarterArray<String> L = new LineStarterArray<>();
        assertEquals(0, L.TotalLine());
    }

    @Test
    public void testAddAndSize() {
        LineStarterArray<String> L = new LineStarterArray<>();
        L.addBack("addBack1");
        L.addBack("addBack2");
        assertEquals(2, L.TotalLine());
    }


    @Test
    public void testAddAndRemoveBack() {
        LineStarterArray<String> L = new LineStarterArray<>();
        L.addBack("1");
        L.addBack("2");
        assertEquals("1", L.get(0));
        assertEquals("2", L.get(1));
        String Back = L.removeBack();
        assertEquals("2", Back);
        assertEquals("1", L.get(0));
        assertEquals(null, L.get(1));
    }

    @Test
    public void testDoubleSize(){
        LineStarterArray<String> L = new LineStarterArray<>();
        for (int i = 1; i <= 20; i++){
            String toAdd = Integer.toString(i);
            L.addBack(toAdd);
        }
        assertEquals(20, L.TotalLine());
        L.addBack("21");
        assertEquals(40, L.getLen());
        assertEquals(21, L.TotalLine());
    }

    @Test
    public void testHalfSize(){
        LineStarterArray<String> L = new LineStarterArray<>();
        for (int i = 1; i <= 21; i++){
            String toAdd = Integer.toString(i);
            L.addBack(toAdd);
        }
        assertEquals(40, L.getLen());
        assertEquals(21, L.TotalLine());

        for (int i = 1; i <=10; i++){
            L.removeBack();
        }
        assertEquals(11, L.TotalLine());
        L.removeBack();
        assertEquals(10, L.TotalLine());
        System.out.println(L.get(0));
        L.removeBack();
        assertEquals(20, L.getLen());
    }



    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("all", LineStarterArrayTest.class);
    }
}