public class OffByOne implements CharacterComparator{


	@Override
	public boolean equalChars(char x, char y){
		int diff = Math.abs(x - y);
		if(diff == 1){
			return true;
		}
		return false;
	}

}