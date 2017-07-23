public class ArrayDeque<Blorp>{
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
		back = 1;
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


	// addFirst method: Adds an item to the front of the Deque
	public void addFirst(Blorp x){
		if (items.length == size) {
			this.resize();
		}

		this.items[front] = x;
		front = this.minusOne(front);
		size += 1;
	}

	// addLast method: put an item to the back of the Deque
	public void addLast(Blorp x){
		if (items.length == size) {
			this.resize();
		}

		this.items[back] = x;
		back = this.addOne(back);
		size += 1;
	}

	// isEmpty method: Returns true if Deque is empty, false otherwise
	public boolean isEmpty(){
		if (size == 0) {
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

	// size method: Returns the number of itmes in the Deque
	public int size(){
		return this.size;
	}

	// doublesize method:
	public void doublesize(){
		int newsize = resizeFactor * size;
		int newstartposition;
		int frontlength;
		Blorp[] temp = (Blorp []) new Object[newsize];
		if(back == 0){
			System.arraycopy(items,0,temp,0,size);
			items = temp;
			back = size;
			front = this.minusOne(0);
		}else{
			newstartposition = newsize - items.length + back;
			frontlength = items.length - back;
			System.arraycopy(items,0,temp,0,back);
			System.arraycopy(items,back,temp,newstartposition,frontlength);
			items = temp;
			front = this.minusOne(newstartposition);
		}
	}

	// halfsize method:
	public void halfsize(){
		int newsize = items.length / resizeFactor;
		int cpPos = this.addOne(front);
		Blorp[] temp = (Blorp []) new Object[newsize];
		for(int i=1;i <= this.size();i++){
			temp[i] = this.items[cpPos];
			cpPos = this.addOne(cpPos);
		}
		items = temp;
		front = 0;
		back = size + 1;
	}

	// resize method: Resize the ArrayDeque to proper size
	public void resize(){
		if(this.isFull()){
			this.doublesize();
		}else{
			this.halfsize();
		}
	}

	// printDeque method: Prints the items in the Deque from first to last, separated by a space.
	public void printDeque(){
		if (this.isEmpty()) {
			System.out.print("The ArrayDeque is EMPTY!");
		}else{
			System.out.print("The ArrayDeque is: ");
			for (int i = 0; i < items.length; i++ ) {
				System.out.print( this.get(i) + " ");
			}
		}
		System.out.println();
	}

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
		this.items[front] = null;
		return this.items[addOne(front)];		
	}

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
		this.items[back] = null;
		return this.items[minusOne(back)];	
	}

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
	
	/*public static void main(String[] args) {
		ArrayDeque<Integer> A1 = new ArrayDeque<Integer>();
		A1.addFirst(0);
		for(int i = 1 ; i <16;i++){
			A1.addLast(i);
		}
		A1.printDeque();
		System.out.println("front is " + A1.front + " back is " + A1.back);
		for(int j = 1;j<5;j++) {
			A1.removeFirst();
		}

		for(int j = 1;j<4;j++) {
			A1.removeLast();
		}
		A1.printDeque();
		System.out.println("front is " + A1.front + " back is " + A1.back);

		for(int i = 1 ; i <10;i++){
			A1.addLast(i);
		}
		A1.printDeque();
		System.out.println("front is " + A1.front + " back is " + A1.back);

		for(int i = 1 ; i <12;i++){
			A1.removeFirst();
		}
		A1.printDeque();
		System.out.println("front is " + A1.front + " back is " + A1.back);
	}*/
	
}