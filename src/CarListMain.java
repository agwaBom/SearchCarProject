import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.Random;

public class CarListMain {
	Scanner scan = new Scanner(System.in);
	Random random = new Random();
	ArrayList<Car> carList = new ArrayList<>();
	ArrayList<Car> quizAnswerList = new ArrayList<>();
	ArrayList<Car> top10 = new ArrayList<>();
	ArrayList<Integer> answerNumber = new ArrayList<>();

	void carMain() {
		readCar();
		printMenu();
	}

	void pushCarToQuiz(ArrayList<Car> carList, ArrayList<Integer> quizNumber) {
		for (int num : quizNumber) {
			for (Car car : carList)
				if (car.number == num)
					quizAnswerList.add(car);
		}
	}

	void quizGame() {
		int quizNumber = 0;
		int score = 0;
		UniqueRandomGenerator gen = new UniqueRandomGenerator(1, 196);
		
		for (int i = 0; i < 5; i++) {
			int rand = gen.getRandomNumber();
			answerNumber.add(rand);
		}
		pushCarToQuiz(carList, answerNumber);

		System.out.println();
		System.out.println("자동차 맞추기 퀴즈를 시작합니다.");
		for (int i = 0; i < 5; i++) {
			Game game = new Game();
			if (game.generateQuiz(scan, quizNumber, quizAnswerList.get(i)))
				score += 1;
			quizNumber++;
		}
		score = score * 2 * 10;
		System.out.println("============================================================================");
		System.out.printf("당신의 점수는 %d / %d입니다.\n", score, 100);
		System.out.println("============================================================================");
		for (int i = 4; i > 0; i--) {
			answerNumber.remove(i);
		}

		printMenu();
	}

	void readCar() {
		Scanner scanf = readFile("car.txt");
		while (scanf.hasNext()) {
			Car car = new Car();
			car.scan(scanf);
			carList.add(car);
		}
	}

	void printSummerizedCar() {
		System.out.println(
				"====================================================================================================");
		System.out.printf("[%2s]%-15s  %-9s   [%2s]%-15s  %-9s   [%2s]%-15s  %-9s\n", "NUM", "MODEL-NAME", "BRAND",
				"NUM", "MODEL-NAME", "BRAND", "NUM", "MODEL-NAME", "BRAND");
		for (int i = 0; i < carList.size(); i += 3) {
			if (i >= carList.size())
				break;
			carList.get(i).printSummerized();
			if (i + 1 >= carList.size())
				break;
			carList.get(i + 1).printSummerized();
			if (i + 2 >= carList.size())
				break;
			carList.get(i + 2).printSummerized();
			System.out.println();
		}
		System.out.println();
		System.out.println(
				"====================================================================================================");
		printMenu();
	}

	void printCar() {
		System.out.println(
				"==================================================================================================================");
		System.out.printf("[%2s]%-15s %-9s %-8s %-8s %-10s %-5s %-9s %-4s %3s %2s %3s %s\n", "번호", "모델명", "브랜드", "차종",
				"크기", "가격", "엔진형식", "연료종류", "배기량", "출력", "구동", "변속기", "연비");
		for (Car car : carList) {
			car.print();
		}
		System.out.println(
				"==================================================================================================================");
		printMenu();
	}

	void searchCar() {
		String keyword;
		while (true) {
			System.out.println("검색하시고 싶은 키워드를 검색하여 주세요");
			System.out.println("가격검색: INT+만원 최대출력검색: INT+hp 연비검색: FLOAT+km/l 배기량검색: INT+cc 끝내기: end");
			System.out.print(">> ");
			keyword = scan.next();
			System.out.println(
					"==================================================================================================================");
			if (keyword.toLowerCase().equals("end")) {
				break;
			}
			System.out.printf("[%2s]%-15s %-9s %-8s %-8s %-10s %-5s %-9s %-4s %3s %2s %3s %s\n", "번호", "모델명", "브랜드",
					"차종", "크기", "가격", "엔진형식", "연료종류", "배기량", "출력", "구동", "변속기", "연비");
			if (keyword.contains("만원")) {
				for (Car car : carList) {
					if (keyword.length() == 7) {
						if (car.searchPrice(keyword.substring(0, 5)))
							car.print();
					} else {
						if (car.searchPrice(keyword.substring(0, 4)))
							car.print();
					}
				}
			} else if (keyword.toLowerCase().contains("hp")) {
				for (Car car : carList) {
					if (keyword.length() == 4) {
						if (car.searchMaxHP(keyword.substring(0, 2)))
							car.print();
					} else {
						if (car.searchMaxHP(keyword.substring(0, 3))) {
							car.print();
						}
					}
				}
			} else if (keyword.toLowerCase().contains("km/l")) {
				for (Car car : carList) {
					if (keyword.length() == 7) {
						if (car.searchFuelEffeciency(keyword.substring(0, 3))) {
							car.print();
						}
					} else {
						if (car.searchFuelEffeciency(keyword.substring(0, 4))) {
							car.print();
						}
					}
				}
			} else if (keyword.toLowerCase().contains("cc")) {
				for (Car car : carList) {
					if (keyword.length() == 3) {
						if (car.searchDisplacement(keyword.substring(0, 1))) {
							car.print();
						}
					} else if (keyword.length() == 5) {
						if (car.searchDisplacement(keyword.substring(0, 3)))
							car.print();
					} else {
						if (car.searchDisplacement(keyword.substring(0, 4))) {
							car.print();
						}
					}
				}
			} else {
				for (Car car : carList) {
					if (car.search(keyword))
						car.print();
				}
			}
			System.out.println(
					"==================================================================================================================");
		}
		printMenu();
	}

	void printMenu() {
		int menuSelector;
		System.out.print("원하시는 번호를 입력해주세요.\n");
		System.out.print("[1]자동차 목록\n[2]자동차 목록(전체정보)\n[3]기본 검색\n[4]자동차 비교\n[5]BestCar\n[6]자동차이름 맞추기 게임!\n[7]종료\n");
		System.out.print(">> ");
		menuSelector = scan.nextInt();
		switch (menuSelector) {
		case 1:
			printSummerizedCar();
			break;
		case 2:
			printCar();
			break;
		case 3:
			searchCar();
			break;
		case 4:
			matchCar();
			break;
		case 5:
			bestCar();
			break;
		case 6:
			quizGame();
			break;
		case 7:
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
				e.printStackTrace();
			}
			if (finish == 2) {
				System.out.println("Both Car Wins!\n");
				break;
			}
			System.out.println();
		}
	}

	// 자동차 비교
	void matchCar() {
		String match1;
		String match2;
		Car compare1 = new Car();
		Car compare2 = new Car();
		System.out.println("비교하실 차량 두 대의 이름을 적어주세요.");
		System.out.print(">> ");
		match1 = scan.next();
		match2 = scan.next();
		System.out.println("============================================================================");
		for (Car car : carList) {
			if (car.name.equals(match1.toLowerCase()))
				compare1 = car;
			if (car.name.equals(match2.toLowerCase()))
				compare2 = car;
		}
		raceCar(compare1, compare2);
		System.out.println("============================================================================");
		printCompare(compare1, compare2);
		System.out.println("============================================================================");
		printMenu();
	}

	void printCompare(Car a, Car b) {
		System.out.printf(
				"%-20s%16s|%s\n%-20s%16s|%s\n%-20s%16s|%s\n%-20s%16s|%s\n%-20s%,16d|%,d\n%-20s%16s|%s\n"
						+ "%-20s%16s|%s\n%-20s%16d|%d\n%-20s%16d|%d\n%-20s%16s|%s\n%-20s%16s|%s\n%-20s%16.2f|%.2f\n",
				"CarName:", a.name.toUpperCase(), b.name.toUpperCase(), "Company:", a.brand.toUpperCase(),
				b.brand.toUpperCase(), "Class:", a.carClass.toUpperCase(), b.carClass.toUpperCase(), "Size:", a.size,
				b.size, "Price:", a.price * 10000, b.price * 10000, "Engine:", a.engineFormat, b.engineFormat,
				"FuelType:", a.fuelType, b.fuelType, "Displacement:", a.displacement, b.displacement, "maxHP:",
				a.maxHorsePower, b.maxHorsePower, "drive method:", a.driveMethod.toUpperCase(),
				b.driveMethod.toUpperCase(), "transmission:", a.transmission.toUpperCase(),
				b.transmission.toUpperCase(), "Fuel Effeciency:", a.fuelEffeciency, b.fuelEffeciency);
	}

	void bestCar() {
		int menuSelect;
		int rank = 1;
		Car compare = new Car();
		System.out.println();
		System.out.println("각 정보에 대한 최대의 값을 지닌 자동차 TOP10을 알려드립니다.");
		System.out.println("최대 수치를 찾고자하는 정보를 골라주세요!");
		System.out.print("[1]가격\n[2]마력\n[3]연비\n[4]돌아가기\n>> ");
		menuSelect = scan.nextInt();
		switch (menuSelect) {
		case 1:
			for (Car car : carList) {
				if (car.price > compare.price)
					compare = car;
			}
			top10.add(compare);
			compare = carList.get(0);
			for (int i = 0; i < 9; i++) {
				for (Car car : carList) {
					if (car.price > compare.price && car.price < top10.get(i).price)
						compare = car;
				}
				top10.add(compare);
				compare = carList.get(0);
			}
			System.out.println("===========================================");
			System.out.printf(" %s  %-16s %-10s %-6s\n", "순위", "MODEL NAME", "BRAND", "PRICE");
			for (Car c : top10) {
				c.printTopPrice(rank);
				rank++;
			}
			System.out.println("===========================================");
			rank = 1;
			top10.clear();
			bestCar();
		case 2:
			for (Car car : carList) {
				if (car.maxHorsePower > compare.maxHorsePower)
					compare = car;
			}
			top10.add(compare);
			compare = carList.get(0);
			for (int i = 0; i < 9; i++) {
				for (Car car : carList) {
					if (car.maxHorsePower > compare.maxHorsePower && car.maxHorsePower < top10.get(i).maxHorsePower)
						compare = car;
				}
				top10.add(compare);
				compare = carList.get(0);
			}
			System.out.println("===========================================");
			System.out.printf(" %s  %-16s %-10s %-6s\n", "순위", "MODEL NAME", "BRAND", "HP");
			for (Car c : top10) {
				c.printTopHP(rank);
				rank++;
			}
			System.out.println("===========================================");
			rank = 1;
			top10.clear();
			bestCar();
		case 3:
			for (Car car : carList) {
				if (car.fuelEffeciency > compare.fuelEffeciency)
					compare = car;
			}
			top10.add(compare);
			compare = carList.get(0);
			for (int i = 0; i < 9; i++) {
				for (Car car : carList) {
					if (car.fuelEffeciency > compare.fuelEffeciency && car.fuelEffeciency < top10.get(i).fuelEffeciency)
						compare = car;
				}
				top10.add(compare);
				compare = carList.get(0);
			}
			System.out.println("===========================================");
			System.out.printf(" %s  %-16s %-10s %-6s\n", "순위", "MODEL NAME", "BRAND", "FUEL EFF");
			for (Car c : top10) {
				c.printTopEffeciency(rank);
				rank++;
			}
			System.out.println("===========================================");
			rank = 1;
			top10.clear();
			bestCar();
		case 4:
			printMenu();
		}
	}

	Scanner readFile(String filename) {
		Scanner fscan = null;
		File file = null;
		try {
			file = new File(filename);
			fscan = new Scanner(file);

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return fscan;
	}

	public static void main(String[] args) {
		CarListMain my = new CarListMain();
		my.carMain();
	}
}
