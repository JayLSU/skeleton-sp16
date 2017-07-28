public interface Deque<Blorp> {
    public void addFirst(Blorp Item);
    public void addLast(Blorp Item);
    public boolean isEmpty();
    public int size();
    public void printDeque();
    public Blorp removeFirst();
    public Blorp removeLast();
    public Blorp get(int index);
}
