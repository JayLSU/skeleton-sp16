public class LinkedListDeque<Blorp> implements Deque<Blorp>{
	public class LinkedListNode{ 
		public Blorp item;
		public LinkedListNode pre;
		public LinkedListNode next;

		public LinkedListNode(Blorp i, LinkedListNode p, LinkedListNode n){
			item = i;
			pre = p;
			next = n;
		}
	}

	private LinkedListNode sentinel;
	private int size; 

	public LinkedListDeque(){
		size = 0;
		sentinel = new LinkedListNode(null,null,null);
		sentinel.pre = sentinel;
		sentinel.next = sentinel;
	}

	public LinkedListDeque(Blorp x){
		size = 1;
		sentinel = new LinkedListNode(null,null,null);
		sentinel.pre = sentinel;
		sentinel.next = sentinel;
		LinkedListNode starter = new LinkedListNode(x, null, null); 
		sentinel.next = starter;
		starter.next = starter;
		starter.pre = starter;
	}
	@Override
	// addFirst method: Adds an item to the front of the Deque
	public void addFirst(Blorp x){
		LinkedListNode newnode;
		if (this.isEmpty()) {
			newnode = new LinkedListNode(x,null,null);
			newnode.pre = newnode;
			newnode.next = newnode;
		}else{
			newnode = new LinkedListNode(x,this.sentinel.next.pre,this.sentinel.next);
			sentinel.next.pre = newnode;
			}
		sentinel.next = newnode;
		size += 1;
	}
	@Override
	// addLast method: put an item to the back of the Deque
	public void addLast(Blorp x){
		LinkedListNode newnode = new LinkedListNode(x,sentinel.next.pre,sentinel.next);	
		sentinel.next.pre.next = newnode;
		sentinel.next.pre = newnode;	
		size += 1;
	}
	@Override
	// isEmpty method: Returns true if Deque is empty, false otherwise
	public boolean isEmpty(){
		if(size == 0){
			return true;
		}
		return false;
	}
	@Override
	// size method: Returns the number of itmes in the Deque
	public int size(){
		return this.size;
	}
	@Override
	// printDeque method: Prints the items in the Deque from first to last, separated by a space.
	public void printDeque(){
		int SizeToPrint = size;
		LinkedListNode NodeToPrint = sentinel.next;
		if(this.isEmpty()){
			System.out.print("Deque is empty");
		}else{
			System.out.print("Deque is:");
			while(SizeToPrint != 0){
				System.out.print(" " + NodeToPrint.item);
				NodeToPrint = NodeToPrint.next;
				SizeToPrint -= 1;
			}
		}
		System.out.println();
	}
	@Override
	// removeFirst method: Removes and retruns the item at the front of the Deque. If no such item exists, returns null.
	public Blorp removeFirst(){
		if (this.isEmpty()) {
			return null;
		}
		size -= 1;
		LinkedListNode last = sentinel.next.pre;
		LinkedListNode newfront = sentinel.next.next;
		Blorp removed_item = sentinel.next.item;
		sentinel.next = newfront;
		newfront.pre = last;
		last.next = newfront;
		return removed_item;
	}
	@Override
	// removeLast method: Removes and retruns the item at the back of the Deque. If no such item exists, returns null.
	public Blorp removeLast(){
		if (this.isEmpty()) {
			return null;
		}
		size -= 1;
		LinkedListNode front = sentinel.next;
		Blorp removed_item = sentinel.next.pre.item;
		LinkedListNode newlast = front.pre.pre;
		front.pre = newlast;
		newlast.next = front;
		return removed_item;
	}
	@Override
	// get mehod: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no
	// such item exists, returns null. Must not alter the Deque!
	public Blorp get(int index){
		if (index > size) {
			return null;
		}
		LinkedListNode NodeToGet = sentinel.next;
		for (int position = 0; position < index; position++) {
			NodeToGet = NodeToGet.next;
		}
		return NodeToGet.item;
	}

	// recrusive get helper method: helper for recursive get method.
	public Blorp getRecursivehelper(int index, LinkedListNode p){
		if (index == 0) {
			return p.item;
		}
		return getRecursivehelper(index - 1, p.next);
	}

	// recursive get method: Same with get, but uses recursion.
	public Blorp getRecursive(int index){
		if(index > this.size){
			return null;
		}
		return getRecursivehelper(index, this.sentinel.next);
	}
	
	/*public static void main(String[] args) {
		LinkedListDeque<Integer> l1 = new LinkedListDeque<Integer>();
		l1.addFirst(2);
		l1.addFirst(1);
		l1.addLast(4);
		l1.addLast(5);
		l1.printDeque();
		System.out.println("Index = 6; The obtained item is " + l1.getRecursive(6));
		System.out.println("Index = 2; The obtained item is " + l1.getRecursive(2));
		System.out.println("List size: " + l1.size());
		int removed1 = l1.removeFirst();
		l1.printDeque();
		System.out.println("List size: " + l1.size() + ". The removed item is: " + removed1);
		int removed2 = l1.removeLast();
		l1.printDeque();
		System.out.println("List size: " + l1.size()+ ". The removed item is: " + removed2);
		l1.removeFirst();
		l1.printDeque();
		System.out.println("List size: " + l1.size());		
		l1.removeLast();
		l1.printDeque();
		System.out.println("List size: " + l1.size());
		l1.removeFirst();
		l1.printDeque();
		System.out.println("Front is: " + l1.removeFirst());
		System.out.println("List size: " + l1.size());
		LinkedListDeque<Integer> l2 = new LinkedListDeque<Integer>();
		l2.printDeque();
		System.out.println(l2.sentinel.next.item);
	}*/

}