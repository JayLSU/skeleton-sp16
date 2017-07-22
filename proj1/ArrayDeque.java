public class ArrayDeque<Blorp>{
	private Blorp[] items;
	private int size;
	private int front;
	private int back;
	private int remainedLen;

	public ArrayDeque(){
		items = (Blorp []) new Object[8];
		size = 0;
		front = 0;
		back = 1;
		remainedLen = 8;
	}

	// addOne method: Adds one to an index
	public int addOne(int index){
		int reminder = (index+1);
		if (reminder >= (size + remainedLen)) {
			reminder = reminder - (size + remainedLen);
		}
		return reminder;
	}

	// minusOne method: Minus one to an index
	public int minusOne(int index){
		int reminder = (index-1);
		if (reminder < 0) {
			reminder = (size + remainedLen) + reminder;
		}
		return reminder;
	}


	// addFirst method: Adds an item to the front of the Deque
	public void addFirst(Blorp x){
		this.items[front] = x;
		front = this.minusOne(front);
		size += 1;
		remainedLen -= 1;
	}

	// addLast method: put an item to the back of the Deque
	public void addLast(Blorp x){
		this.items[back] = x;
		back = this.addOne(back);
		size += 1;
		remainedLen -= 1;
	}

	// isEmpty method: Returns true if Deque is empty, false otherwise
	public boolean isEmpty(){
		if (size == 0) {
			return true;
		}
		return false;
	}

	// size method: Returns the number of itmes in the Deque
	public int size(){
		return this.size;
	}

	// printDeque method: Prints the items in the Deque from first to last, separated by a space.
	public void printDeque(){
		if (this.isEmpty()) {
			System.out.print("The ArrayDeque is EMPTY!");
		}else{
			System.out.print("The ArrayDeque is: ");
			for (int i = 0; i < (this.size + this.remainedLen); i++ ) {
				System.out.print( this.get(i) + " ");
			}
		}
		System.out.println();
	}

	// removeFirst method: Removes and retruns the item at the front of the Deque. If no such item exists, returns null.
	public Blorp removeFirst(){
		if (this.isEmpty()) {
			return null;
		}
		size -= 1;
		remainedLen += 1;
		front = this.addOne(front);
		this.items[front] = null;
		return this.items[addOne(front)];		
	}

	// removeLast method: Removes and retruns the item at the back of the Deque. If no such item exists, returns null.
	public Blorp removeLast(){
		if (this.isEmpty()) {
			return null;
		}
		size -= 1;
		remainedLen += 1;
		back = this.minusOne(back);
		this.items[back] = null;
		return this.items[minusOne(back)];	
	}

	// get mehod: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no
	// such item exists, returns null. Must not alter the Deque!
	public Blorp get(int index){
		if (index > (size + remainedLen - 1)) {
			return null;
		}
		return this.items[index];
	}


	
	public static void main(String[] args) {
		ArrayDeque<String> A1 = new ArrayDeque<String>();
		A1.printDeque();
		A1.addLast("a");
		A1.addLast("b");
		A1.addLast("c");
		A1.addLast("d");
		A1.removeFirst();
		A1.removeFirst();
		A1.removeFirst();
		A1.removeFirst();
		System.out.println("Front is " + A1.front + ". Back is " + A1.back);
		A1.addLast("a");
		System.out.println("Front is " + A1.front + ". Back is " + A1.back);
		A1.addLast("b");
		System.out.println("Front is " + A1.front + ". Back is " + A1.back);
		A1.addFirst("c");
		System.out.println("Front is " + A1.front + ". Back is " + A1.back);
		A1.addLast("d");
		System.out.println("Front is " + A1.front + ". Back is " + A1.back);
		A1.addLast("e");
		System.out.println("Front is " + A1.front + ". Back is " + A1.back);
		A1.printDeque();
		System.out.println(A1.get(5));
	}
	
}