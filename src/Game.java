import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class Game extends CarListMain {
	ArrayList<Car> falseAnswer = new ArrayList<>();
	ArrayList<Integer> duplicate = new ArrayList<>();

	void generateFalseSelector(Car answer) {
		UniqueRandomGenerator gen = new UniqueRandomGenerator(1, 195);
		for (int i = 0; i < 4; i++) {
			int number = gen.getRandomNumber();
			if (number == answer.number) {
				i--;
				continue;
			}
			falseAnswer.add(carList.get(number - 1));
		}
	}

	boolean generateQuiz(Scanner scan, int quizNumber, Car answer) {
		UniqueRandomGenerator gen = new UniqueRandomGenerator(1, 11);	
		Random rand = new Random();
		int cases;
		readCar();
		generateFalseSelector(answer);
		System.out.println("============================================================================");
		System.out.printf("Quiz #%d 이 차량의 이름은 무엇인가요?\n", quizNumber + 1);
		System.out.println("이 차는..."); // 힌트 4개 주기.
		for (int i = 0; i < 7; i++) {
			while (true) {
				cases = gen.getRandomNumber();
				if (!duplicate.contains(cases)) {
					duplicate.add(cases);
					break;
				}
			}
		}
		for (int i = 6; i >= 3; i--) {
			printHint(answer, duplicate.get(i));
		}
		System.out.println();
		System.out.println("정답은...?!");
		int rightAnswerPos = rand.nextInt(4);
		int falseAnswerIndex = 0;
		for (int i = 0; i < 5; i++) {
			if (i == rightAnswerPos) {
				System.out.printf("* %s\n", answer.name.toUpperCase());
			} else {
				System.out.printf("* %s\n", falseAnswer.get(falseAnswerIndex).name.toUpperCase());
				falseAnswerIndex += 1;	
			}
		}
		if (printMenu(scan, answer)) {
			System.out.println("정답입니다!");
			return true;
		}
		System.out.println("오답입니다...");
		return false;
	}

	boolean printMenu(Scanner scan, Car answer) {
		String input;
		int hint = 3;
		while (true) {
			System.out.println();
			System.out.println("정답을 적어주세요. 힌트를 원하시면 hint를 적어주세요. (힌트는 문제 당 3번 가능합니다)");
			System.out.print(">> ");
			input = scan.next();
			if (input.equals("hint")) {
				System.out.printf("\n힌트를 알려드리겠습니다. 힌트는 총 %d개 남았습니다.\n", hint - 1);
				printHint(answer, duplicate.get(hint - 1));
				hint--;
			} else if (isTrue(input, answer)) {
				return true;
			}
			if (hint <= 0 || !input.equals("hint")) {
				if (hint <= 0) {
					System.out.println();
					System.out.println("힌트를 다 소모하셨습니다. 정답을 입력해주세요.");
					System.out.print(">> ");
					input = scan.next();
					if (isTrue(input, answer))
						return true;
				}
				break;
			}
		}
		return false;
	}

	boolean isTrue(String input, Car answer) {
		if (answer.searchName(input.toLowerCase()))
			return true;
		return false;
	}

	void printHint(Car answer, int cases) {
		switch (cases) {
		case 1:
			System.out.printf("이 차의 브랜드는 %s입니다.\n", answer.brand.toUpperCase());
			break;
		case 2:
			System.out.printf("이 차의 형태는 %s입니다.\n", answer.carClass.toUpperCase());
			break;
		case 3:
			System.out.printf("이 차의 크기는 %s입니다.\n", answer.size.toUpperCase());
			break;
		case 4:
			System.out.printf("이 차의 가격은 %,d만원 입니다.\n", answer.price);
			break;
		case 5:
			System.out.printf("이 차의 엔진형식은 %s입니다.\n", answer.engineFormat.toUpperCase());
			break;
		case 6:
			System.out.printf("이 차는 (최고트림 기준) %s연료로 움직입니다.\n", answer.fuelType.toUpperCase());
			break;
		case 7:
			System.out.printf("이 차는 %,dCC의 배기량(최고트림 기준)을 가지고 있습니다.\n", answer.displacement);
			break;
		case 8:
			System.out.printf("이 차의 최고출력(최고트림 기준)은 %d마력입니다.\n", answer.maxHorsePower);
			break;
		case 9:
			System.out.printf("이 차의 구동방식은 %s입니다.\n", answer.driveMethod.toUpperCase());
			break;
		case 10:
			System.out.printf("이 차는 %s의 변속기를 탑재하고 있습니다.\n", answer.transmission.toUpperCase());
			break;
		case 11:
			System.out.printf("이 차는 %.2fkm/l의 연비(최고트림 기준)를 소모합니다.\n", answer.fuelEffeciency);
			break;
		}
	}
}
