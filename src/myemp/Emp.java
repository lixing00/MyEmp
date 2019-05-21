package myemp;

import java.util.Scanner;

public class Emp {
	
	//刪除員工
	private static void delEmp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入要刪除的id");
		String id = sc.nextLine();
		Message.removeEmp(id);
	}
	
	//添加员工信息
	private static void addEmp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入员工id:");
		String id = sc.nextLine();
		System.out.println("请输入员工姓名:");
		String name = sc.nextLine();
		System.out.println("请输入员工年龄:");
		int age = Integer.parseInt(sc.nextLine());
		System.out.println("请输入员工薪资:");
		double salary = Double.parseDouble(sc.nextLine());
		
		Message.regEmp(id, name, age, salary);
	}

	//打印员工信息
	private static void printEmp() {
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1.添加员工信息");
		System.out.println("2.删除员工信息");
		System.out.println("3.修改员工信息");
		System.out.println("4.查看员工信息");
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
			System.out.println("输入错误，请输入正确的命令");
		}
		
	}

}
