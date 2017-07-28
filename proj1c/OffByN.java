public class OffByN implements CharacterComparator {
	private int N;

	public OffByN(int x){
		this.N = x;
	}

	@Override
	public boolean equalChars(char y1, char y2){
		int diff = Math.abs(y1 - y2);
		if (diff == this.N) {
			return true;
		}
		return false;
	}
}