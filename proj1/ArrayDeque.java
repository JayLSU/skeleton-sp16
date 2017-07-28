public class ArrayDeque<Blorp> implements Deque<Blorp>{
	private Blorp[] items;
	private int size;
	private int front;
	private int back;
	private int resizeFactor;
	private double usageFactor;

	public ArrayDeque(){
		items = (Blorp []) new Object[8];
		size = 0;
		front = 0;
		back = 0;
		resizeFactor = 2;
		usageFactor = 0.25;
	}

	// addOne method: Adds one to an index
	public int addOne(int index){
		int reminder = (index+1);
		if (reminder >= items.length) {
			reminder = reminder - items.length;
		}
		return reminder;
	}

	// minusOne method: Minus one to an index
	public int minusOne(int index){
		int reminder = (index-1);
		if (reminder < 0) {
			reminder = items.length + reminder;
		}
		return reminder;
	}

	@Override
	// addFirst method: Adds an item to the front of the Deque
	public void addFirst(Blorp x){
		boolean emptyStart = isEmpty();
		if (items.length == size) {
			this.resize();
		}

		this.items[front] = x;
		if (emptyStart){
			this.front = this.minusOne(this.front);
			this.back = this.addOne(this.back);
		}else {
			this.front = this.minusOne(this.front);
		}
		size += 1;
	}
	@Override
	// addLast method: put an item to the back of the Deque
	public void addLast(Blorp x){
		boolean emptyStart = isEmpty();
		if (items.length == size) {
			this.resize();
		}

		this.items[back] = x;
		if (emptyStart){
			this.front = this.minusOne(this.front);
			this.back = this.addOne(this.back);
		}else {
			back = this.addOne(back);
		}
		size += 1;
	}
	@Override
	// isEmpty method: Returns true if Deque is empty, false otherwise
	public boolean isEmpty(){
		if (size == 0) {
			this.front = this.back = 0;
			return true;
		}
		return false;
	}

	// isFull method: Returns true if Deque is full, false otherwise
	public boolean isFull(){
		if (size == items.length) {
			return true;
		}
		return false;
	}
	@Override
	// size method: Returns the number of itmes in the Deque
	public int size(){
		return this.size;
	}

	// doublesize method:
	public void doublesize(){
		int newsize = resizeFactor * size;
		int frontlength;
		Blorp[] temp = (Blorp []) new Object[newsize];
		if(back == 0){
			System.arraycopy(items,0,temp,0,size);
			items = temp;
			back = size;
			front = this.minusOne(0);
		}else{
			frontlength = this.size() - back;
			System.arraycopy(items,0,temp,frontlength,back);
			System.arraycopy(items,back,temp,0,frontlength);
			items = temp;
			front = this.minusOne(0);
			back = this.size();
		}
	}

	// halfsize method:
	public void halfsize(){
		int newsize = items.length / resizeFactor;
		int cpPos = this.addOne(front);
		Blorp[] temp = (Blorp []) new Object[newsize];
		for(int i=0;i <= this.size();i++){
			temp[i] = this.items[cpPos];
			cpPos = this.addOne(cpPos);
		}
		items = temp;
		front = minusOne(0);
		back = size;
	}

	// resize method: Resize the ArrayDeque to proper size
	public void resize(){
		if(this.isFull()){
			this.doublesize();
		}else{
			this.halfsize();
		}
	}
	@Override
	// printDeque method: Prints the items in the Deque from first to last, separated by a space.
	public void printDeque(){
		int printEnd = minusOne(this.back);
		if (this.isEmpty()) {
			System.out.print("The ArrayDeque is EMPTY!");
		}else{
			System.out.print("The ArrayDeque is: ");
			for (int printPos = addOne(this.front); printPos <= printEnd; printPos++ ) {
				System.out.print( this.get(printPos) + " ");
			}
		}
		System.out.println();
	}
	@Override
	// removeFirst method: Removes and retruns the item at the front of the Deque. If no such item exists, returns null.
	public Blorp removeFirst(){
		double currentusage;
		currentusage = (double)this.size()/items.length;
		if (this.isEmpty()) {
			return null;
		}
		if(currentusage <= usageFactor && items.length > 8){
			this.resize();
		}
		size -= 1;
		front = this.addOne(front);
		Blorp temp = this.items[front];
		this.items[front] = null;
		return temp;
	}
	@Override
	// removeLast method: Removes and retruns the item at the back of the Deque. If no such item exists, returns null.
	public Blorp removeLast(){
		double currentusage = (double)this.size()/items.length;
		if (this.isEmpty()) {
			return null;
		}
		if(currentusage <= usageFactor && items.length > 8){
			this.resize();
		}
		size -= 1;
		back = this.minusOne(back);
		Blorp temp = this.items[front];
		this.items[back] = null;
		return temp;
	}
	@Override
	// get mehod: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no
	// such item exists, returns null. Must not alter the Deque!
	public Blorp get(int index){
		if (index > (items.length - 1)) {
			return null;
		}
		return this.items[index];
	}

	// getlength method: Returns the length of ArrayDeque
	public int getlength(){
		return items.length;
	}
	
	public static void main(String[] args) {
		ArrayDeque<Integer> A1 = new ArrayDeque<Integer>();
		int temp;
		A1.addFirst(0);
		A1.printDeque();
		System.out.println("front is " + A1.front + " back is " + A1.back);
		for(int i = 1 ; i <16;i++){
			A1.addLast(i);
		}
		A1.printDeque();
		System.out.println("front is " + A1.front + " back is " + A1.back);
		/*for(int j = 1;j<3;j++) {
			A1.removeLast();
		}
		for(int j = 1;j<3;j++) {
			A1.removeFirst();
		}*/

		A1.addLast(0);
		A1.printDeque();
		System.out.println("front is " + A1.front + " back is " + A1.back);

	}
	
}