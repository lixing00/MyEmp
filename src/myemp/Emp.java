package myemp;

import java.util.Scanner;

public class Emp {
	
	//�h���T��
	private static void delEmp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ոݔ��Ҫ�h����id");
		String id = sc.nextLine();
		Message.removeEmp(id);
	}
	
	//���Ա����Ϣ
	private static void addEmp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("������Ա��id:");
		String id = sc.nextLine();
		System.out.println("������Ա������:");
		String name = sc.nextLine();
		System.out.println("������Ա������:");
		int age = Integer.parseInt(sc.nextLine());
		System.out.println("������Ա��н��:");
		double salary = Double.parseDouble(sc.nextLine());
		
		Message.regEmp(id, name, age, salary);
	}

	//��ӡԱ����Ϣ
	private static void printEmp() {
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1.���Ա����Ϣ");
		System.out.println("2.ɾ��Ա����Ϣ");
		System.out.println("3.�޸�Ա����Ϣ");
		System.out.println("4.�鿴Ա����Ϣ");
		int a = Integer.parseInt(sc.nextLine());
		switch(a) {
		case 1:
			addEmp();
			break;
		case 2:
			delEmp();
			break;
		case 3:
			Message.setEmp();
			break;
		case 4:
			Message.printEmp();
			break;
		default:
			System.out.println("���������������ȷ������");
		}
		
	}

}
