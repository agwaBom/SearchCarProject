import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.Random;

public class CarListMain {
	Scanner scan = new Scanner(System.in);
	Random random = new Random();
	ArrayList <Car> carList = new ArrayList <> ();
	ArrayList <Car> quizAnswerList = new ArrayList <> ();
	ArrayList <Integer> answerNumber = new ArrayList<>();

	void carMain() {
		readCar();
		printCar();
		printMenu();
	}
	
	int generateRandom() {
		int number;
		int maxCarNumber = 195;
		number = random.nextInt(maxCarNumber) + 1;
		return number;
	}
	
	void pushCarToQuiz(ArrayList <Car> carList, ArrayList <Integer> quizNumber) {
		for(int num : quizNumber) {
			for(Car car : carList)
				if(car.number == num)
					quizAnswerList.add(car);
		}			
	}
	
	void quizGame() {
		int quizNumber = 0;
		int score = 0;
		for(int i = 0; i < 5; i++) {
			if(i != 0) {
				if(answerNumber.get(i - 1) != generateRandom())
					answerNumber.add(generateRandom());				
			} else {
				answerNumber.add(generateRandom());
			}
		}
		pushCarToQuiz(carList, answerNumber);
		
		System.out.println();
		System.out.println("자동차 맞추기 퀴즈를 시작합니다.");
		for(int i = 0; i < 5; i++) {
			Game game = new Game();
			if(game.generateQuiz(scan, quizNumber, quizAnswerList.get(i)))
				score += 1;
			quizNumber++;
		}
		score = score * 2 * 10;
		System.out.println("----------------------------------------------------------------------------");
		System.out.printf("당신의 점수는 %d / %d입니다.\n", score, 100);
		for(int i = 4; i > 0; i--) {
			answerNumber.remove(i);
		}
		
		printMenu();
	}
	
	void readCar() {
		Scanner scanf = readFile("car.txt");
		while(scanf.hasNext()) {
			Car car = new Car();
			car.scan(scanf);
			carList.add(car);
		}
	}
	
	void printCar() {
		System.out.printf("[%2s]%-15s %-9s %-8s %-8s %-10s %-5s %-9s %-4s %3s %2s %3s %s\n", 
				"번호", "모델명", "브랜드", "차종", "크기", "가격", "엔진형식", "연료종류", 
				"배기량", "출력", "구동", "변속기","연비");
		for(Car car : carList) {
			car.print();
		}
	}
	
	void searchCar() {
		int subMenu;
		String keyword;
		int range;
		float fuelRange;
		System.out.print("\n검색하고자 하는 종류를 선택하여 주십시오.\n");
		System.out.print("[1]이름\n[2]브랜드\n[3]가격\n[4]엔진형식\n[5]사용연료\n[6]배기량\n[7]최대출력\n[8]구동방식\n[9]변속기\n[10]연비\n[11]돌아가기\n");
		System.out.print(">> ");
		subMenu = scan.nextInt();
		switch(subMenu) {
		case 1:
			System.out.println("모델명을 입력해주세요.");
			System.out.print(">> ");
			keyword = scan.next();
			for(Car car : carList) {
				if(car.searchName(keyword))
					car.print();
				
			}
			searchCar();
		case 2:
			System.out.println("브랜드를 입력해주세요.");
			System.out.print(">> ");
			keyword = scan.next();
			for(Car car : carList) {
				if(car.searchBrand(keyword))
					car.print();
			}
			searchCar();
		case 3:
			System.out.println("범위를 입력해주세요. 예)3000만원~8000만원이면 3000 8000");
			System.out.print(">> ");
			keyword = scan.next();
			range = scan.nextInt();
			if(!keyword.matches("[0-9]+")) {
				System.out.println("형식에 맞지 않습니다.");
			} else {
				for(Car car : carList) {
					if(car.searchPrice(keyword, range))
						car.print();
				}				
			}
			searchCar();
		case 4:
			System.out.println("엔진형식을 입력해주세요.");
			System.out.print(">> ");
			keyword = scan.next();
			for(Car car : carList) {
				if(car.searchEngineFormat(keyword))
					car.print();
			}
			searchCar();
		case 5:
			System.out.println("연료타입을 입력해주세요. gasoline/diesel/electric/hybrid");
			System.out.print(">> ");
			keyword = scan.next();
			for(Car car : carList) {
				if(car.searchFuelType(keyword))
					car.print();
			}
			searchCar();
		case 6:
			System.out.println("범위를 입력해주세요. 예)3000cc~8000cc이면 3000 8000");
			System.out.print(">> ");
			keyword = scan.next();
			range = scan.nextInt();
			for(Car car : carList) {
				if(car.searchDisplacement(keyword, range))
					car.print();
			}
			searchCar();
		case 7:
			System.out.println("범위를 입력해주세요. 예)300HP~800HP이면 300 800");
			System.out.print(">> ");
			keyword = scan.next();
			range = scan.nextInt();
			for(Car car : carList) {
				if(car.searchMaxHP(keyword, range))
					car.print();
			}
			searchCar();
		case 8:
			System.out.println("구동형식을 입력해주세요. 전륜구동 - FF/사륜구동 - 4WD/후륜구동 - FR/풀타임 사륜 - AWD/미드쉽 후륜 - MR/후방엔진 후륜 - RR");
			System.out.print(">> ");
			keyword = scan.next();
			for(Car car : carList) {
				if(car.searchDriveMethod(keyword))
					car.print();
			}
			searchCar();
		case 9:
			System.out.println("변속기 타입을 입력해주세요. n단 자동 - nAT/n단 수동 - nMT/무단변속기 - CVT/n단 듀얼클러치 - nDCT/변속기가 없음 - NONE");
			System.out.print(">> ");
			keyword = scan.next();
			for(Car car : carList) {
				if(car.searchTransmission(keyword))
					car.print();
			}
			searchCar();
		case 10:
			System.out.println("범위를 입력해주세요. 예)3.0km/l ~ 13.0km/l이면 3.0 13.0");
			System.out.print(">> ");
			keyword = scan.next();
			fuelRange = scan.nextFloat();
			for(Car car : carList) {
				if(car.searchFuelEffeciency(keyword, fuelRange))
					car.print();
			}
			searchCar();
		case 11:
			printMenu();
		}
	}
	
	void printMenu() {
		int menuSelector;
		System.out.print("\n원하시는 번호를 입력해주세요.\n");
		System.out.print("[1]기본 검색\n[2]자동차 비교\n[3]BestCar\n[4]자동차이름 맞추기 게임!\n[5]종료\n");
		System.out.print(">> ");
		menuSelector = scan.nextInt();
		switch(menuSelector) {
			case 1:
				searchCar();
				break;
			case 2:
				matchCar();
				break;
			case 3:
				bestCar();
				break;
			case 4:
				quizGame();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				System.exit(1);
		}
		scan.close();
	}
	
	void raceCar(Car compare1, Car compare2) {
		int time = 1;
		int raceTrack[] = new int[2];
		int finish = 0;
		while (finish != 1) {
			raceTrack[0] = compare1.race(time);
			raceTrack[1] = compare2.race(time);
			for (int num : raceTrack)
				finish += num;
			time++;
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(finish == 2) {
				System.out.println("Both Car Wins!\n");
				break;
			}
			System.out.println();
			System.out.println();
		}
	}
	
	//자동차 비교 
	void matchCar() {
		String match1;
		String match2;
		Car compare1 = new Car();
		Car compare2 = new Car();
		System.out.println("비교하실 차량 두 대의 이름을 적어주세요.");
		System.out.print(">> ");
		match1 = scan.next();
		match2 = scan.next();
		for(Car car : carList) {
			if(car.name.equals(match1.toLowerCase()))
				compare1 = car;
			if(car.name.equals(match2.toLowerCase()))
				compare2 = car;
		}
		raceCar(compare1, compare2);
		printCompare(compare1, compare2);
		printMenu();
	}
	
	void printCompare(Car a, Car b) {
		System.out.printf("%-20s%16s|%s\n%-20s%16s|%s\n%-20s%16s|%s\n%-20s%16s|%s\n%-20s%,16d|%,d\n%-20s%16s|%s\n"
				+ "%-20s%16s|%s\n%-20s%16d|%d\n%-20s%16d|%d\n%-20s%16s|%s\n%-20s%16s|%s\n%-20s%16.2f|%.2f\n",
				"CarName:", a.name.toUpperCase(), b.name.toUpperCase(), "Company:", a.brand.toUpperCase(), b.brand.toUpperCase(), "Class:", a.carClass.toUpperCase(), b.carClass.toUpperCase(), 
				"Size:", a.size, b.size, "Price:", a.price * 10000, b.price * 10000, "Engine:", a.engineFormat, b.engineFormat,"FuelType:", a.fuelType, b.fuelType, 
				"Displacement:", a.displacement, b.displacement, "maxHP:", a.maxHorsePower, b.maxHorsePower, "drive method:", a.driveMethod.toUpperCase(), b.driveMethod.toUpperCase(), "transmission:", a.transmission.toUpperCase(), 
				b.transmission.toUpperCase(), "Fuel Effeciency:", a.fuelEffeciency, b.fuelEffeciency);
	}
	
	void bestCar() {
		int menuSelect;
		Car compare = new Car();
		System.out.println("각 정보에 대한 최대의 값을 지닌 자동차를 알려드립니다. 어서오세요.");
		System.out.println("최대 수치를 찾고자하는 값을 골라주세요!");
		System.out.print("[1]가격\n[2]배기량\n[3]마력\n[4]연비\n>> ");
		menuSelect = scan.nextInt();
		switch(menuSelect) {
		case 1:
			for(Car car : carList) {
				if(car.price > compare.price)
					compare = car;
			}
			compare.print();
			printMenu();
		case 2:
			for(Car car : carList) {
				if(car.displacement > compare.displacement)
					compare = car;
			}
			compare.print();
			printMenu();
		case 3:
			for(Car car : carList) {
				if(car.maxHorsePower > compare.maxHorsePower)
					compare = car;
			}
			compare.print();
			printMenu();
		case 4:
			for(Car car : carList) {
				if(car.fuelEffeciency > compare.fuelEffeciency)
					compare = car;
			}
			compare.print();
			printMenu();
		}
	}
	
	
	Scanner readFile(String filename) {
		Scanner fscan = null;
		File file = null;
		try {
			file = new File(filename);
			fscan = new Scanner(file);
			
		} catch(FileNotFoundException e) {
			throw new RuntimeException (e);
		}
		return fscan;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CarListMain my = new CarListMain();
		my.carMain();
	}
}
