package myemp;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 员工信息
 * @author xing
 *
 */
public class Message {
	
	private static HashMap<String,Staff> messages = new HashMap<>();
	
	
	static {
		//初始化员工信息
		try {
			System.out.println("开始初始化员工信息");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new File("emp.xml"));
			Element root = doc.getRootElement();
			List<Element> list = root.elements("emp");
			for(Element emp:list) {
				String id = emp.elementText("id");
				String name = emp.elementText("name");
				int age = Integer.parseInt(emp.elementText("age"));
				double salary = Double.parseDouble(emp.elementText("salary"));
				messages.put(id, new Staff(id,name,age,salary));
			}
			System.out.println("员工信息初始化完毕！");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
	//修改員工信息
	public static void setEmp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入員工id");
		String id = sc.nextLine();
		if(messages.containsKey(id)) {
			Staff emp = messages.get(id);
			System.out.println("1.修改員工姓名");
			System.out.println("2.修改員工年齡");
			System.out.println("3.修改員工薪資");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1:
				System.out.println("請輸入修改后的員工姓名");
				String name = sc.nextLine();
				emp.setName(name);
				System.out.println("修改成功！");
				break;
			case 2:
				System.out.println("請輸入修改后的年齡");
				int age = Integer.parseInt(sc.nextLine());
				emp.setAge(age);
				System.out.println("修改成功！");
				break;
			case 3:
				System.out.println("請輸入修改后的薪資");
				double salary = Double.parseDouble(sc.nextLine());
				emp.setSalary(salary);
				System.out.println("修改成功！");
				break;
			default:
				System.out.println("請輸入正確的序號！");
				break;
			}
		}else {
			System.out.println("id不存在！");
		}
		
		writeEmp();
	}
	
	//刪除員工
	public static void removeEmp(String id) {
		if (messages.containsKey(id)) {
			messages.remove(id);
			writeEmp();
			System.out.println("刪除成功！");
		}else {
			System.out.println("id不存在！");
		}
	}
	
	//打印所有員工
	public static void printEmp() {
		Set<Entry<String,Staff>> emps = messages.entrySet();
		for(Entry<String,Staff> emp:emps) {
			System.out.println(emp.getValue());
		}
	}
	
	//添加員工
	public static void regEmp(String id,String name,int age,double salary) {
		if(messages.containsKey(id)) {
			System.out.println("添加失败，id已存在！");
			return;
		}
		messages.put(id, new Staff(id,name,age,salary));
		System.out.println("注册成功!");
		writeEmp();
	}
	
	//写入员工信息
	private static void writeEmp() {
		try {
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("emps");
			Set<Entry<String,Staff>> emps = messages.entrySet();
			for(Entry<String,Staff> entryEmp:emps) {
				Staff staff = entryEmp.getValue();
				Element emp = root.addElement("emp");
				emp.addElement("id").addText(staff.getId());
				emp.addElement("name").addText(staff.getName());
				emp.addElement("age").addText(staff.getAge()+"");
				emp.addElement("salary").addText(staff.getSalary()+"");
			}
			XMLWriter writer = new XMLWriter(new FileOutputStream("emp.xml"));
			writer.write(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
