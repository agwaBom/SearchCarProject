import java.util.Scanner;

public class Car {
	int number;
	String name;
	String brand;
	String carClass;
	String size;
	int price;
	String engineFormat;
	String fuelType;
	int displacement;
	int maxHorsePower;
	String driveMethod;
	String transmission;
	float fuelEffeciency;

	void scan(Scanner scan) {
		number = scan.nextInt();
		name = scan.next();
		brand = scan.next();
		carClass = scan.next();
		size = scan.next();
		price = scan.nextInt();
		engineFormat = scan.next();
		fuelType = scan.next();
		displacement = scan.nextInt();
		maxHorsePower = scan.nextInt();
		driveMethod = scan.next();
		transmission = scan.next();
		fuelEffeciency = scan.nextFloat();
	}

	void printSummerized() {
		System.out.printf("[%3d]%-16s|%-10s  ", number, name.toUpperCase(), brand.toUpperCase());
	}

	void printTopPrice(int rank) {
		System.out.printf("[%2d위]%-16s|%-10s|%,6d만원\n", rank, name.toUpperCase(), brand.toUpperCase(), price);
	}

	void printTopHP(int rank) {
		System.out.printf("[%2d위]%-16s|%-10s|%3dHP\n", rank, name.toUpperCase(), brand.toUpperCase(), maxHorsePower);
	}

	void printTopEffeciency(int rank) {
		System.out.printf("[%2d위]%-16s|%-10s|%5.2fkm/l\n", rank, name.toUpperCase(), brand.toUpperCase(),
				fuelEffeciency);
	}

	void print() {
		System.out.printf("[%3d]%-16s|%-10s|%-9s|%-9s|%,6d만원|%5s엔진|%9s|%,5dCC|%3dHP|%3s|%4s|%5.2fkm/l\n", number,
				name.toUpperCase(), brand.toUpperCase(), carClass, size, price, engineFormat, fuelType, displacement,
				maxHorsePower, driveMethod.toUpperCase(), transmission.toUpperCase(), fuelEffeciency);
	}

	int race(int time) {
		System.out.printf("%-16s|", name.toUpperCase());
		double temp;
		temp = maxHorsePower / 50 * time;
		for (int i = 0; i < 50; i++) {
			if (i >= temp - 1 && i < temp) {
				if (temp >= 50)
					break;
				System.out.printf("|%02dMeter|", (int) (temp * 2));
			}
			System.out.print("-");
		}
		System.out.println();
		if (temp >= 50) {
			System.out.printf("Finished! %s wins!\n", name.toUpperCase());
			return 1;
		}
		return 0;
	}

	boolean search(String keyword) {
		String lowered = keyword.toLowerCase();	
		if (name.indexOf(lowered) == 0)
			return true;
		if (lowered.equals(name))
			return true;
		if (lowered.equals(carClass))
			return true;
		if (lowered.equals(size))
			return true;
		if (lowered.equals(brand))
			return true;
		if (lowered.equals(engineFormat))
			return true;
		if (lowered.equals(fuelType))
			return true;
		if (lowered.equals(driveMethod))
			return true;
		if (lowered.equals(transmission))
			return true;
		return false;
	}

	boolean searchName(String keyword) {
		if (keyword.toLowerCase().equals(name))
			return true;
		return false;
	}

	// 범위
	boolean searchPrice(String keyword) {
		if (keyword.matches("[0-9]+")) {
			if (Integer.parseInt(keyword) >= price - 500 && Integer.parseInt(keyword) <= price + 500)
				return true;
		}
		return false;
	}

	// 범위
	boolean searchDisplacement(String keyword) {
		if (keyword.matches("[0-9]+")) {
			if (Integer.parseInt(keyword) <= displacement + 300 && Integer.parseInt(keyword) >= displacement - 300)
				return true;
		}
		return false;
	}

	// 범위
	boolean searchMaxHP(String keyword) {
		if (keyword.matches("[0-9]+")) {
			if (Integer.parseInt(keyword) <= maxHorsePower + 50 && Integer.parseInt(keyword) >= maxHorsePower - 50)
				return true;
		}
		return false;
	}

	boolean searchFuelEffeciency(String keyword) {
		if (keyword.matches(".*[0-9]+.*")) {
			if (Float.parseFloat(keyword) <= fuelEffeciency + 2 && Float.parseFloat(keyword) >= fuelEffeciency - 2)
				return true;
		}
		return false;
	}
}
