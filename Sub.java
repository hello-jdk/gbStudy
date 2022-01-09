package project2;

import java.util.Scanner;

abstract class Beverage {
	private static int pkNum = 1000;

	private int pk; // DB������ ���� ����
	private String name; // ����� �̸�
	private int stock; // ����� ���
	private int price; // ����� ����

	// ������
	public Beverage(String name) {
		this(name, 0, 0);
	}

	public Beverage(String name, int stock, int price) {
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.pk = pkNum++;
	}

	// �޼���
	@Override
	public String toString() {
		if (stock <= 0)
			return name + "\t(ǰ��)";
		else
			return name + "\t����:" + price;
	}

	public String toStringM() {
		return "�̸�: " + name + "\t����:" + price + "\t���:" + stock;
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

		// �⺻ ����
		int MAX = 10;
		Beverage[] data = new Beverage[MAX];
		data[0] = new Juice("�ֽ�����", 10, 1000);
		data[1] = new CarbonatedDrink("ź������", 0, 500);
		data[2] = new SportDrink("�̿�����", 5, 700);

		// ����
		int money = 0;
		String code = "9999";

		String[] rName = new String[MAX];
		int[] rPrice = new int[MAX];

		while (true) {
			System.out.println("===========================");
			System.out.println("\t����� ���Ǳ�");
			int a = 1;
			for (Beverage v : data) {
				if (v == null)
					continue;
				System.out.println("[" + a++ + "]" + v);
			}
			System.out.println("1. ������ 2. ��ǰ���� 3. ����");
			System.out.println("===========================");
			System.out.print("���ڸ� �Է��ϼ��� >> ");
			int num = sc.nextInt();

			if (num == 1) {
				System.out.print("�󸶸� �Է��Ͻðڽ��ϱ�? >> ");
				money += sc.nextInt();
				System.out.println("���� �ܾ��� " + money + "�� �Դϴ�."); // �ȳ����� (�����ܾ�)
				continue;

			} else if (num == 2) {
				while (true) {
					System.out.print("���� ������ ���ڸ� �Է��ϼ��� (�ڷΰ��� : 0) >> ");
					int act = sc.nextInt();

					if (Integer.toString(act).equals(code)) {
						System.out.println("&&&&�����ڸ��&&&&");
						data = manage(data);

					} else if (act >= 1 && act <= a) {
						String cName = data[act - 1].getName();
						int cStock = data[act - 1].getStock();
						int cPrice = data[act - 1].getPrice();

						if (cStock == 0) {
							System.out.println(">> ǰ���Դϴ�. <<");
							continue;
						} else if (cPrice > money) {
							System.out.println(">> �������� �����մϴ�. <<");
							continue;
						} else {
							data[act - 1].setStock(cStock - 1); // ��� ����
							money -= cPrice; // ������ ����

							// ������
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

							// �ȳ����� ����ϰ� �ҷ��� ���ִ°� ��������
							System.out.println("\"" + cName + "\"������ �Ϸ��߽��ϴ�.");
							System.out.println("���� ���� �ݾ�: " + money + "��");

							// ��ǰ����ȭ��
							continue;
						}
					}

					// �ڷΰ���
					else if (act == 0) {
						System.out.println("�ڷΰ���");
						break;
					}

					else {
						// �ȳ����� (��ȿ���˻�)
						System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���. ");
						continue;
					}

				}

			}
			// 3 ���� �� ���ܼ� �Է�
			else if (num == 3) {

				// ���� �־����� �������� ���� ���
				if (rName[0] == null)
					if (money > 0)
						System.out.println("�Ž����� : " + money + "��");

				// ������ ���
				if (rName[0] != null) {
					System.out.println("=============������=============");
					System.out.println("��ǰ�̸�\t\t\t����");
					for (int i = 0; i < rName.length; i++) {
						if (rName[i] == null)
							continue;
						System.out.println(rName[i] + "\t\t\t" + rPrice[i] + "��");
					}
					if (money != 0)
						System.out.println("\t\t�Ž�����:\t" + money + "��");
					System.out.println("==============================");
				}
				System.out.println("�̿����ּż� �����մϴ�.");
				break;
			} else {
				// �ȳ����� (��ȿ���˻�)
				System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���. ");
				continue;
			}

		}

	}

	private static Beverage[] manage(Beverage[] data) {

		Beverage[] nData = data;

		while (true) {
			// �����ڸ��
			Scanner sc = new Scanner(System.in);
			System.out.println("1.��� 2.��������   3.��ǰ�߰� 0.������");
			System.out.print(">>");
			int act = sc.nextInt();

			// ��ǰ��Ϻ���(���Ȯ��)
			if (act == 1) {
				for (Beverage v : nData) {
					if (v == null)
						continue;
					System.out.println(v.toStringM());
				}
			}

			// ��������
			else if (act == 2) {
				int a = 1;
				for (Beverage v : nData) {
					if (v == null)
						continue;
					System.out.println(
							String.format("[%d]", a++) + v.getName() + "\t����:" + v.getPrice() + "\t���:" + v.getStock());
				}
				System.out.print("������ ������ ��ǰ�� ����ּ���>> ");
				int act2 = sc.nextInt();
				System.out.print("��� �����Ͻðڽ��ϱ�?>> ");
				int act3 = sc.nextInt();
				if (act2 >= 1 && act2 <= a) {
					System.out.println("���� ����:" + nData[act2 - 1].getStock());
					nData[act2 - 1].setStock(act3);
					System.out.println("���� ����:" + nData[act2 - 1].getStock());
				} else {
					// ��ȿ�� �˻�
					System.out.println("��� �ȿ��� �Է����ּ���.");
				}

			}
			// ��ǰ�߰�
			else if (act == 3) {

				// ī�װ�
				int c = 0;
				do {
					System.out.println("ī�װ��� �������ּ���.");
					System.out.println("1.�ֽ����� 2.ź������ 3.�̿�����");
					System.out.print(">>");
					c = sc.nextInt();
				} while (c < 1 || c > 3);

				// �̸��Է�
				System.out.print("��ǰ�̸� >>");
				String s = sc.next();
				// ����
				System.out.print("���� >>");
				int n = sc.nextInt();
				// �����Է�
				System.out.print("���� >>");
				int p = sc.nextInt();

				// ����� ã��
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
				System.out.println("�߰��� �Ϸ�Ǿ����ϴ�.");

			}
			// ������
			else if (act == 0) {
				break;
			}
			// ��ȿ��
			else {
				System.out.println("�ٽ��Էº�Ź�帳�ϴ�.");
			}
		}

		return nData;

	}
}
