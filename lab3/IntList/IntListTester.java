public class IntListTester{
	public static void main(String[] args) {
		IntList A = IntList.list(0,1);
		System.out.println("Original List is :" + A);
		System.out.println("Reversed List is : " + IntList.reverse(A));
	}
}