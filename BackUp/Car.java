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
	
	void print() {
		System.out.printf("[%3d]%-16s|%-10s|%-9s|%-9s|%,6d만원|%5s엔진|%9s|%,5dCC|%3dHP|%3s|%4s|%5.2fkm/l\n", 
				number, name.toUpperCase(), brand.toUpperCase(), carClass, size, price, engineFormat, fuelType, 
				displacement, maxHorsePower, driveMethod.toUpperCase(), transmission.toUpperCase(), fuelEffeciency);
	}
	
	int race(int time) {
		System.out.printf("%-16s|",name.toUpperCase());
	    double temp;
	    temp = maxHorsePower / 50 * time;
	    for (int i = 0; i < 50; i++) {	
	    	if (i >= temp - 1 && i < temp) {
	    		if(temp >= 50)
	    			break;
	    		System.out.printf("|%02dMeter|", (int)(temp * 2));
	    	}
	    	System.out.print("-");
	    }
	    System.out.println();  
	    if(temp >= 50) {
	    	System.out.printf("Finished! %s wins!\n", name.toUpperCase());
	    	return 1;
	   	}
	   	return 0;
	}
	
	boolean searchName(String keyword) {
		if(keyword.toLowerCase().equals(name))
			return true;
		return false;
	}
	
	boolean searchBrand(String keyword) {
		if(keyword.toLowerCase().equals(brand))
			return true;
		return false;
	}
	//범위
	boolean searchPrice(String keyword, int range) { 
		if(keyword.matches("[0-9]+")) {
			if(Integer.parseInt(keyword) <= price && range >= price)
				return true;				
		}
		return false;
	}
	
	boolean searchEngineFormat(String keyword) {
		if(keyword.toLowerCase().equals(engineFormat))
			return true;
		return false;
	}
	
	boolean searchFuelType(String keyword) {
		if(keyword.toLowerCase().equals(fuelType))
			return true;
		return false;
	}
	//범위
	boolean searchDisplacement(String keyword, int range) {
		if(keyword.matches("[0-9]+")) {
			if(Integer.parseInt(keyword) <= displacement && range >= displacement)
				return true;				
		}
		return false;
	}
	//범위
	boolean searchMaxHP(String keyword, int range) {
		if(keyword.matches("[0-9]+")) {
			if(Integer.parseInt(keyword) <= maxHorsePower && range >= maxHorsePower)
				return true;				
		}
		return false;
	}
	
	boolean searchDriveMethod(String keyword) {
		if(keyword.toLowerCase().equals(driveMethod))
			return true;
		return false;
	}
	
	boolean searchTransmission(String keyword) {
		if(keyword.toLowerCase().equals(transmission))
			return true;
		return false;
	}
	//범위
	boolean searchFuelEffeciency(String keyword, float range) {
		if(keyword.matches(".*[0-9]+.*")) {
			if(Float.parseFloat(keyword) <= fuelEffeciency && range >= fuelEffeciency)
				return true;				
		}
		return false;
	}
}
