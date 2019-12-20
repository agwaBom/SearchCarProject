import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Game extends CarListMain{
	ArrayList <Car> falseAnswer = new ArrayList <>();
	ArrayList <Integer> duplicate = new ArrayList <>();
	
	void generateFalseSelector(Car answer) {
		for(int i = 0; i < 5; i++) {
			int temp = generateRandom();
			if(carList.get(temp - 1).number != answer.number)
				falseAnswer.add(carList.get(temp - 1));
		}
	}
	
	boolean generateQuiz(Scanner scan, int quizNumber, Car answer) {
		Random rand = new Random();
		int cases;
		boolean cameOut = false;
		readCar();
		generateFalseSelector(answer);
		System.out.println("----------------------------------------------------------------------------");
		System.out.printf("Quiz #%d 이 차량의 이름은 무엇인가요?\n", quizNumber + 1);
		System.out.println("이 차는..."); //힌트 4개 주기.
		for(int i = 0; i < 7; i++) {
			while(true) {
				cases = rand.nextInt(10) + 1;
				if(!duplicate.contains(cases)) {
					duplicate.add(cases);
					break;
				}
			}
		}
		for(int i = 6; i >= 3; i--) {
			printHint(answer, duplicate.get(i));
		}
		System.out.println();
		System.out.println("정답은...?!");//왠만하면 객관식이 좋지 않을까? 갈아엎긴해야함.
		boolean randBool;
		for(int i = 0; i < 5; i++) {
			randBool = random.nextBoolean();
			if(randBool && !cameOut) {
				cameOut = true;
				System.out.printf("* %s\n", answer.name.toUpperCase());	
			} else if(!randBool || cameOut){
				if(cameOut) {
					System.out.printf("* %s\n", falseAnswer.get(i - 1).name.toUpperCase()); //index 3 out of bounds for length 3	진짜 대단한게 두번나온적이 있다...		
				} else {
					System.out.printf("* %s\n", falseAnswer.get(i).name.toUpperCase());	//index 4 out of bound for length 4				
				}
			}
		}
		if(printMenu(scan, answer, rand)) {
			System.out.println("정답입니다!");
			return true;
		}
		System.out.println("이런...");
		return false;
	}
	
	boolean printMenu(Scanner scan, Car answer, Random rand) {
		String temp;
		int menu;
		int hint = 3;
		int hintSelect;
		System.out.println();
		System.out.println("[1]정답적기 [2]힌트얻기(문제 기회 3번) [3]이 문제 패스!!!.");
		System.out.print(">> ");
		temp = scan.next();
		if(temp.matches("[0-9]+"))
			menu = Integer.parseInt(temp);
		else {
			System.out.println("메뉴에 맞는 숫자를 입력하여주세요!");
			menu = scan.nextInt();
		}
		switch(menu) {
		case 1:
			if(isTrue(scan, answer))
				return true;
			break;
		case 2:
			System.out.printf("\n힌트를 알려드리겠습니다. 힌트는 총 %d개 남았습니다.\n", hint - 1);
			printHint(answer, duplicate.get(hint - 1));				
			hint--;
			while(hint != 0) {
				System.out.print("\n힌트가 더 필요하신가요? \n[0]그런거 필요없다! [1]하나만 더 ㅠㅠ\n");
				System.out.print(">> ");
				hintSelect = scan.nextInt();
				if(hintSelect == 0)
					break;
				if(hintSelect == 1) {
					System.out.printf("힌트를 알려드리겠습니다. 힌트는 총 %d개 남았습니다.\n", hint - 1);
					printHint(answer, duplicate.get(hint - 1));
					hint--;					
				}
			}
			if(hint <= 0) {
				System.out.println();
				System.out.println("힌트를 다 소모하셨습니다.");
				if(isTrue(scan, answer))
					return true;
				break;
			}
			if(isTrue(scan, answer))
				return true;
			break;
		case 3:
			System.out.println("다음문제로 넘어갑니다.");
			break;
		}
		return false;
	}
	
	boolean isTrue(Scanner scan, Car answer) {
		String selectedAnswer;
		System.out.printf("정답인 자동차의 이름을 적어주세요!\n>> ");
		selectedAnswer = scan.next();			
		if(answer.searchName(selectedAnswer.toLowerCase()))
			return true;
		return false;
	}
	
	void printHint(Car answer, int cases) {
		switch(cases) {
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
