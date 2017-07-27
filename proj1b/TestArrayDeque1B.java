import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDeque1B{
	@Test
	public void testIsEmpty(){
		StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
		ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<Integer>();
		boolean actual = sad1.isEmpty();
		boolean expected = sad2.isEmpty();
		assertEquals(expected,actual);
	}

	@Test
	public void testSize(){
		StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
		ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<Integer>();
		FailureSequence fs = new FailureSequence();
		for (int i = 0; i < 5 ; i++) {
			sad1.addFirst(i);
			sad2.addFirst(i);
			DequeOperation dequeOp1 = new DequeOperation("addFirst", i);
			fs.addOperation(dequeOp1);
		}
		for (int j = 0; j < 5 ; j++) {
			sad1.addFirst(10-j);
			sad2.addFirst(10-j);
			DequeOperation dequeOp2 = new DequeOperation("addFirst", 10-j);
			fs.addOperation(dequeOp2);
		}
		int actual1 = sad1.size();
		int expected1 = sad2.size();
		DequeOperation dequeOp7 = new DequeOperation("size");
		fs.addOperation(dequeOp7);
		assertEquals(expected1,actual1);
		sad1.removeFirst();
		sad2.removeFirst();
		DequeOperation dequeOp3 = new DequeOperation("removeFirst");
		fs.addOperation(dequeOp3);
		sad1.removeLast();
		sad2.removeLast();
		DequeOperation dequeOp4 = new DequeOperation("removeLast");
		fs.addOperation(dequeOp4);
		int actual2 = sad1.size();
		int expected2 = sad2.size();
		assertEquals(expected2,actual2);
		for (int j = 0; j < 9; j++){
			sad1.removeFirst();
			sad2.removeFirst();
			DequeOperation dequeOp5 = new DequeOperation("removeFirst");
			fs.addOperation(dequeOp5);
		}
		int expected0 = sad2.size();
		int actual0 = sad1.size();
		DequeOperation dequeOp6 = new DequeOperation("size");
		fs.addOperation(dequeOp6);
		assertEquals(fs.toString(),expected0,actual0);
	}

	@Test
	public void testRemoveFirst(){
		StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
		ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<Integer>();
		for (int i = 0; i < 5 ; i++) {
			sad1.addFirst(i);
			sad2.addFirst(i);
		}
		for (int j = 0; j < 5 ; j++) {
			sad1.addFirst(10-j);
			sad2.addFirst(10-j);
		}
		sad1.removeFirst();
		sad1.removeFirst();
		sad2.removeFirst();
		sad2.removeFirst();
		int actual3 = sad1.removeFirst();
		int expected3 = sad2.removeFirst();
		assertEquals(expected3,actual3);
	}

	@Test
	public void testRemoveLast(){
		StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
		ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<Integer>();
		for (int i = 0; i < 5 ; i++) {
			sad1.addFirst(i);
			sad2.addFirst(i);
		}
		for (int j = 0; j < 5 ; j++) {
			sad1.addLast(10-j);
			sad2.addLast(10-j);
		}
		sad1.removeLast();
		sad1.removeLast();
		sad2.removeLast();
		sad2.removeLast();
		int actual3 = sad1.removeLast();
		int expected3 = sad2.removeLast();
		assertEquals(expected3,actual3);
	}

	@Test
	public void testGet(){
		StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<Integer>();
		ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<Integer>();
		for (int i = 0; i < 10 ; i++) {
			sad1.addLast(i);
			sad2.addLast(i);
		}
		int actual4 = sad1.get(6);
		int expected4 = sad2.get(6);
		Integer actual5 = sad1.get(16);
		Integer expected5 = null;
		assertEquals(expected4,actual4);
	}

	public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("all", TestArrayDeque1B.class);
    }

}