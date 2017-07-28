public class Palindrome {
	public static Deque<Character> wordToDeque(String word){
		Deque<Character> ad = new LinkedListDequeSolution<>();
		for (int i = 0; i < word.length(); i++ ) {
			char x = word.charAt(i);
			ad.addLast(x);
		}
		return ad;
	}


	private static boolean isPalindrome(Deque<Character> x){
		int adsize = x.size();
		int checklen;
		if (isOdd(adsize)){
			checklen = (adsize - 1)/2;
		}else{
			checklen = adsize/2;
		}
		if (adsize < 2) {
			return true;
		}else{
		for (int index = 0; index < checklen ; index++) {
			if (x.get(index) != x.get(adsize - 1 - index)) {
				return false;
			}
		}
		return true;
		}

	}

	public static boolean isPalindrome(String words){
		Deque<Character> ad1 = wordToDeque(words);
		return isPalindrome(ad1);
	}

	private static boolean isOdd(int x){
		int temp = x%2;
		if (temp == 1){
			return true;
		}
		return false;
	}

	private static boolean isPalindrome(Deque<Character> x, CharacterComparator cc){
		int adsize = x.size();
		int checklen;
		if (isOdd(adsize)){
			checklen = (adsize - 1)/2;
		}else{
			checklen = adsize/2;
		}
		if (adsize < 2){
			return true;
		}else{
			for (int index = 0; index < checklen ; index++) {
				if (cc.equalChars(x.get(index), x.get(adsize - 1 - index))) {

				}else{
					return false;
				}
			}
		return true;
		}
	}

	public static boolean isPalindrome(String words, CharacterComparator cc){
		Deque<Character> ad1 = wordToDeque(words);
		return isPalindrome(ad1,cc);
	}


	public static void main(String[] args) {
		String strword = args[0]; // i.e., abbcbba
		Deque<Character> ad1 = wordToDeque(strword);

		boolean result = isPalindrome(strword);
		ad1.printDeque();
		System.out.println(result);

/*
		String strword1 = args[1];  // i.e., flake
		Deque<Character> ad2 = wordToDeque(strword1);
		OffByOne cc = new OffByOne();
		boolean result1 = isPalindrome(strword1,cc);
		ad2.printDeque();
		System.out.println(result1);

		String strword2 = args[2];  // i.e., apple
		Deque<Character> ad3 = wordToDeque(strword2);
		OffByN cc1 = new OffByN(4);
		boolean result2 = isPalindrome(strword2,cc1);
		ad3.printDeque();
		System.out.println(result2);*/

	}

}