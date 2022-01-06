/*
 * 01/05(��) 3��ȸ��
1. �迭ũ�����ϱ� = 10��
2. toString���� ��ǰ��Ϻ����ֱ�		
3. ������ ��ȿ���˻�			
4. ��ǰ���źκ� 				
5. �����ڸ�� 				
6. receipt ������ ���� �ڵ�?			
					
1. ��ǰ���źκ�
2. toString
3. �����ڸ�� or ��ȿ��,receipt 
���� ���������� ������ 8�ÿ� �ڵ帮��
 */

import java.util.Scanner;

abstract class Beverage {
	private static int pkNum = 1000;
	
	private int pk ; // DB������ ���� ����
	private String name; // ����� �̸�
	private int stock; // ����� ���
	private int price; // ����� ����
	
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
		// �����[] data = ����� �迭[ũ��]
		Beverage[] data = new Beverage[10];
		// �⺻��������
		data[0] = new Juice("�ֽ�");
		data[1] = new CarbonatedDrink("ź������");
		data[2] = new SportDrink("�̿�����");

		// ����
		// ������, ������, ��ũ���ڵ�
		int money = 0;
		String receipt = "";
		int code = 0000;

		while (true) {
			System.out.println("===========================");
			System.out.println("       ����� ���Ǳ�          ");
			//toString ��ǰ��ȣ(�迭�ε���+1)+��ǰ�̸� + ����
			System.out.println("1. ������ 2. ��ǰ���� 3. ����");
			System.out.print("���ڸ� �Է��ϼ��� : ");
			System.out.println();
			System.out.println("===========================");
			int num = sc.nextInt();
			
			// ùȭ��
			// ��ư : ������, ��ǰ����, ����

			// 1 ������
			if (num == 1) {
				System.out.print("�󸶸� �Է��Ͻðڽ��ϱ�? : ");
				//��ȿ���˻��ʿ�
				money += sc.nextInt(); 
				System.out.println("���� �ܾ��� " + money + "�� �Դϴ�."); // �ȳ����� (�����ܾ�)
				continue; // ùȭ������

			}
			// 2 ��ǰ����
			else if (num == 2) {
				while (true) {
					System.out.print("���� ������ ���ڸ� �Է��ϼ��� : ");
					int act = sc.nextInt();
					
					if(act == 0000) {
						//���θ���κ�
						manage();
					}
					
					//���θ���κ�
					//��ǰ����
					//�迭�� �޾Ƽ� ��������
					//1.buy�� ó������ ��� ����´�� 2.��ȣ�Է¹����� buy���������� 3.buy�޼��带 �����ʰ� main�κп��� ó��
			
					else { 
						// �ȳ����� (��ȿ���˻�)
						System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���. ");
						continue;
					}
					
				}

			}
			// 3 ���� �� ���ܼ� �Է�
			else if (num == 3) {
				// ������ ���
				System.out.println(receipt+"�� �����ϼ̽��ϴ�.");
				System.out.println("�̿����ּż� �����մϴ�.");
				break;
			} else {
				// �ȳ����� (��ȿ���˻�)
				System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���. ");
				continue;
			}

		}

	}

	private static void manage() {
		//�����ڸ��
	}
	
	private static void buy(Beverage b){
		b.getPrice();
		b.getStock();
	}

}
