/*
 * 01/05(수) 3차회의
1. 배열크기정하기 = 10개
2. toString으로 제품목록보여주기		
3. 돈투입 유효성검사			
4. 제품구매부분 				
5. 관리자모드 				
6. receipt 데이터 관리 코드?			
					
1. 제품구매부분
2. toString
3. 관리자모드 or 유효성,receipt 
각자 만들어본다음에 다음날 8시에 코드리뷰
 */

import java.util.Scanner;

abstract class Beverage {
	private static int pkNum = 1000;
	
	private int pk ; // DB관리를 위한 변수
	private String name; // 음료수 이름
	private int stock; // 음료수 재고
	private int price; // 음료수 가격
	
	public Beverage(String name) {
		this(name,0,0);
	}

	public Beverage(String name,int stock, int price) {
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.pk = pkNum++;
	}
	
	
	// Name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Stock
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	// Price
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}

class Juice extends Beverage {

	public Juice(String name) {
		super(name);
	}
	
	public Juice(String name, int stock, int price) {
		super(name, stock, price);
	}

}

class CarbonatedDrink extends Beverage {

	public CarbonatedDrink(String name) {
		super(name);
	}
	
	public CarbonatedDrink(String name, int stock, int price) {
		super(name, stock, price);
	}

}

class SportDrink extends Beverage {
	
	public SportDrink(String name) {
		super(name);
	}

	public SportDrink(String name, int stock, int price) {
		super(name, stock, price);
	}

}

public class Sub {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 음료수[] data = 음료수 배열[크기]
		Beverage[] data = new Beverage[10];
		// 기본제공음료
		data[0] = new Juice("주스");
		data[1] = new CarbonatedDrink("탄산음료");
		data[2] = new SportDrink("이온음료");

		// 변수
		// 소지금, 영수증, 시크릿코드
		int money = 0;
		String receipt = "";
		int code = 0000;

		while (true) {
			System.out.println("===========================");
			System.out.println("       음료수 자판기          ");
			//toString 제품번호(배열인덱스+1)+제품이름 + 가격
			System.out.println("1. 돈투입 2. 제품구입 3. 종료");
			System.out.print("숫자를 입력하세요 : ");
			System.out.println();
			System.out.println("===========================");
			int num = sc.nextInt();
			
			// 첫화면
			// 버튼 : 돈투입, 제품구입, 종료

			// 1 돈투입
			if (num == 1) {
				System.out.print("얼마를 입력하시겠습니까? : ");
				//유효성검사필요
				money += sc.nextInt(); 
				System.out.println("현재 잔액은 " + money + "원 입니다."); // 안내문구 (현재잔액)
				continue; // 첫화면으로

			}
			// 2 제품구입
			else if (num == 2) {
				while (true) {
					System.out.print("마실 음료의 숫자를 입력하세요 : ");
					int act = sc.nextInt();
					
					if(act == 0000) {
						//새로만들부분
						manage();
					}
					
					//새로만들부분
					//제품구입
					//배열로 받아서 정보변경
					//1.buy로 처음부터 모든 입출력담당 2.번호입력받은후 buy로정보변경 3.buy메서드를 쓰지않고 main부분에서 처리
			
					else { 
						// 안내문구 (유효성검사)
						System.out.println("잘못된 입력입니다. 다시 입력하세요. ");
						continue;
					}
					
				}

			}
			// 3 종료 및 예외성 입력
			else if (num == 3) {
				// 영수증 출력
				System.out.println(receipt+"를 구매하셨습니다.");
				System.out.println("이용해주셔서 감사합니다.");
				break;
			} else {
				// 안내문구 (유효성검사)
				System.out.println("잘못된 입력입니다. 다시 입력하세요. ");
				continue;
			}

		}

	}

	private static void manage() {
		//관리자모드
	}
	
	private static void buy(Beverage b){
		b.getPrice();
		b.getStock();
	}

}
