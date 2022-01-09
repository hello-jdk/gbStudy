package project2;

import java.util.Scanner;

abstract class Beverage {
	private static int pkNum = 1000;

	private int pk; // DB관리를 위한 변수
	private String name; // 음료수 이름
	private int stock; // 음료수 재고
	private int price; // 음료수 가격

	// 생성자
	public Beverage(String name) {
		this(name, 0, 0);
	}

	public Beverage(String name, int stock, int price) {
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.pk = pkNum++;
	}

	// 메서드
	@Override
	public String toString() {
		if (stock <= 0)
			return name + "\t(품절)";
		else
			return name + "\t가격:" + price;
	}

	public String toStringM() {
		return "이름: " + name + "\t가격:" + price + "\t재고:" + stock;
	}

	// getter setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
		if (this.stock <= 0) {
			this.stock = 0;
		}
	}

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

		// 기본 생성
		int MAX = 10;
		Beverage[] data = new Beverage[MAX];
		data[0] = new Juice("주스음료", 10, 1000);
		data[1] = new CarbonatedDrink("탄산음료", 0, 500);
		data[2] = new SportDrink("이온음료", 5, 700);

		// 변수
		int money = 0;
		String code = "9999";

		String[] rName = new String[MAX];
		int[] rPrice = new int[MAX];

		while (true) {
			System.out.println("===========================");
			System.out.println("\t음료수 자판기");
			int a = 1;
			for (Beverage v : data) {
				if (v == null)
					continue;
				System.out.println("[" + a++ + "]" + v);
			}
			System.out.println("1. 돈투입 2. 제품구입 3. 종료");
			System.out.println("===========================");
			System.out.print("숫자를 입력하세요 >> ");
			int num = sc.nextInt();

			if (num == 1) {
				System.out.print("얼마를 입력하시겠습니까? >> ");
				money += sc.nextInt();
				System.out.println("현재 잔액은 " + money + "원 입니다."); // 안내문구 (현재잔액)
				continue;

			} else if (num == 2) {
				while (true) {
					System.out.print("마실 음료의 숫자를 입력하세요 (뒤로가기 : 0) >> ");
					int act = sc.nextInt();

					if (Integer.toString(act).equals(code)) {
						System.out.println("&&&&관리자모드&&&&");
						data = manage(data);

					} else if (act >= 1 && act <= a) {
						String cName = data[act - 1].getName();
						int cStock = data[act - 1].getStock();
						int cPrice = data[act - 1].getPrice();

						if (cStock == 0) {
							System.out.println(">> 품절입니다. <<");
							continue;
						} else if (cPrice > money) {
							System.out.println(">> 소지금이 부족합니다. <<");
							continue;
						} else {
							data[act - 1].setStock(cStock - 1); // 재고 감소
							money -= cPrice; // 소지금 감소

							// 영수증
							boolean flag = true;
							int empty = MAX;
							for (int i = 0; i < rName.length; i++) {
								if (rName[i] == null) {
									if (empty > i) {
										empty = i;
									}
									continue;
								}
								if (rName[i].equals(cName)) {
									rPrice[i] += cPrice;
									flag = false;
									break;
								}
							}
							
							
							if (flag) {
								rName[empty] = cName;
								rPrice[empty] = cPrice;
							}

							// 안내문구 깔끔하게 할려면 없애는게 나을수도
							System.out.println("\"" + cName + "\"구입을 완료했습니다.");
							System.out.println("현재 남은 금액: " + money + "원");

							// 제품구입화면
							continue;
						}
					}

					// 뒤로가기
					else if (act == 0) {
						System.out.println("뒤로가기");
						break;
					}

					else {
						// 안내문구 (유효성검사)
						System.out.println("잘못된 입력입니다. 다시 입력하세요. ");
						continue;
					}

				}

			}
			// 3 종료 및 예외성 입력
			else if (num == 3) {

				// 돈을 넣었지만 구매하지 않은 경우
				if (rName[0] == null)
					if (money > 0)
						System.out.println("거스름돈 : " + money + "원");

				// 영수증 출력
				if (rName[0] != null) {
					System.out.println("=============영수증=============");
					System.out.println("제품이름\t\t\t가격");
					for (int i = 0; i < rName.length; i++) {
						if (rName[i] == null)
							continue;
						System.out.println(rName[i] + "\t\t\t" + rPrice[i] + "원");
					}
					if (money != 0)
						System.out.println("\t\t거스름돈:\t" + money + "원");
					System.out.println("==============================");
				}
				System.out.println("이용해주셔서 감사합니다.");
				break;
			} else {
				// 안내문구 (유효성검사)
				System.out.println("잘못된 입력입니다. 다시 입력하세요. ");
				continue;
			}

		}

	}

	private static Beverage[] manage(Beverage[] data) {

		Beverage[] nData = data;

		while (true) {
			// 관리자모드
			Scanner sc = new Scanner(System.in);
			System.out.println("1.목록 2.수량변경   3.물품추가 0.나가기");
			System.out.print(">>");
			int act = sc.nextInt();

			// 물품목록보기(재고확인)
			if (act == 1) {
				for (Beverage v : nData) {
					if (v == null)
						continue;
					System.out.println(v.toStringM());
				}
			}

			// 수량변경
			else if (act == 2) {
				int a = 1;
				for (Beverage v : nData) {
					if (v == null)
						continue;
					System.out.println(
							String.format("[%d]", a++) + v.getName() + "\t가격:" + v.getPrice() + "\t재고:" + v.getStock());
				}
				System.out.print("수량을 변경할 제품을 골라주세요>> ");
				int act2 = sc.nextInt();
				System.out.print("몇개로 변경하시겠습니까?>> ");
				int act3 = sc.nextInt();
				if (act2 >= 1 && act2 <= a) {
					System.out.println("이전 수량:" + nData[act2 - 1].getStock());
					nData[act2 - 1].setStock(act3);
					System.out.println("변경 수량:" + nData[act2 - 1].getStock());
				} else {
					// 유효성 검사
					System.out.println("목록 안에서 입력해주세요.");
				}

			}
			// 물품추가
			else if (act == 3) {

				// 카테고리
				int c = 0;
				do {
					System.out.println("카테고리를 설정해주세요.");
					System.out.println("1.주스음료 2.탄산음료 3.이온음료");
					System.out.print(">>");
					c = sc.nextInt();
				} while (c < 1 || c > 3);

				// 이름입력
				System.out.print("제품이름 >>");
				String s = sc.next();
				// 수량
				System.out.print("수량 >>");
				int n = sc.nextInt();
				// 가격입력
				System.out.print("가격 >>");
				int p = sc.nextInt();

				// 빈공간 찾기
				int index = nData.length;
				for (int i = 0; i < index; i++) {
					if (nData[i] == null) {
						index = i;
						break;
					}
				}

				if (c == 1) {
					nData[index] = new Juice(s, n, p);
				} else if (c == 2) {
					nData[index] = new CarbonatedDrink(s, n, p);
				} else {
					nData[index] = new SportDrink(s, n, p);
				}
				System.out.println("추가가 완료되었습니다.");

			}
			// 나가기
			else if (act == 0) {
				break;
			}
			// 유효성
			else {
				System.out.println("다시입력부탁드립니다.");
			}
		}

		return nData;

	}
}
