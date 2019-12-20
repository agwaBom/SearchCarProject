import java.util.ArrayList;
import java.util.Random;

public class UniqueRandomGenerator {
	int min;
	int max;
	ArrayList<Integer> numbers = new ArrayList<>();
	
	public UniqueRandomGenerator(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	public void reset() {
		this.numbers.clear();
	}
	
	public int getRandomNumber() {
		Random r = new Random();
		
		int number = r.nextInt((max - min) + 1) + min;
		while (numbers.contains(number)) {
			number = r.nextInt((max - min) + 1) + min;
		}
		
		this.numbers.add(number);
		return number;
	}
	
}
