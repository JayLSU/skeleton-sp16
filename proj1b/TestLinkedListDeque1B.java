import org.junit.Test;
import static org.junit.Assert.*;



public class TestLinkedListDeque1B{
	@Test
	public void testSLLDIsEmpty(){
		StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
		LinkedListDequeSolution<Integer> slld2 = new LinkedListDequeSolution<Integer>();

		boolean actual = slld2.isEmpty();
		boolean expected = slld1.isEmpty();
		assertEquals(expected,actual);
	}

	@Test
	public void testSLLDSize(){
		StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
		LinkedListDequeSolution<Integer> slld2 = new LinkedListDequeSolution<Integer>();

		for (int i = 0; i < 5 ; i++) {
			slld1.addFirst(i);
			slld2.addFirst(i);

		}
		for (int j = 0; j < 5 ; j++) {
			slld1.addFirst(10-j);
			slld2.addFirst(10-j);

		}
		int actual1 = slld1.size();
		int expected1 = slld2.size();

		assertEquals(expected1,actual1);

		slld1.removeFirst();
		slld1.removeLast();
		slld2.removeFirst();
		slld2.removeLast();

		int actual2 = slld1.size();
		int expected2 = slld2.size();
		assertEquals(expected2,actual2);

		for (int j = 0; j < 9; j++){
			slld1.removeFirst();
			slld2.removeFirst();
		}
		int expected0 = slld2.size();
		int actual0 = slld1.size();

		assertEquals(expected0,actual0);
	}

	@Test
	public void testSLLDRemoveFirst(){
		StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
		LinkedListDequeSolution<Integer> slld2 = new LinkedListDequeSolution<Integer>();
		for (int i = 0; i < 5 ; i++) {
			slld1.addFirst(i);
			slld2.addFirst(i);
		}
		for (int j = 0; j < 5 ; j++) {
			slld1.addFirst(10-j);
			slld2.addFirst(10-j);
		}
		slld1.removeFirst();
		slld1.removeFirst();
		slld2.removeFirst();
		slld2.removeFirst();
		int actual3 = slld1.removeFirst();
		int expected3 = slld2.removeFirst();
		assertEquals(expected3,actual3);
	}

	@Test
	public void testSLLDRemoveLast(){
		StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
		LinkedListDequeSolution<Integer> slld2 = new LinkedListDequeSolution<Integer>();
		for (int i = 0; i < 5 ; i++) {
			slld1.addFirst(i);
			slld2.addFirst(i);
		}
		for (int j = 0; j < 5 ; j++) {
			slld1.addLast(10-j);
			slld2.addLast(10-j);
		}
		slld1.removeLast();
		slld1.removeLast();
		slld2.removeLast();
		slld2.removeLast();
		int actual3 = slld1.removeLast();
		int expected3 = slld2.removeLast();
		assertEquals(expected3,actual3);
	}

	@Test
	public void testSLLDGet(){
		StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
		LinkedListDequeSolution<Integer> slld2 = new LinkedListDequeSolution<Integer>();
		FailureSequence fs = new FailureSequence();
		for (int i = 0; i < 10 ; i++) {
			slld1.addLast(i);
			DequeOperation dequeOp1 = new DequeOperation("addFirst", i);
			fs.addOperation(dequeOp1);
			slld2.addLast(i);
		}
		int actual4 = slld1.get(6);
		DequeOperation dequeOp2 = new DequeOperation("get", 6);
		fs.addOperation(dequeOp2);
		int expected4 = slld2.get(6);
		assertEquals(expected4,actual4);

		Integer actual5 = slld1.get(16);
		DequeOperation dequeOp3 = new DequeOperation("get", 16);
		fs.addOperation(dequeOp3);
		Integer expected5 = null;
		assertEquals(fs.toString(),expected5,actual5);
	}

	public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("all", TestLinkedListDeque1B.class);
    }

}