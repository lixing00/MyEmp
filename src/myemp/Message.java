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
 * Ա����Ϣ
 * @author xing
 *
 */
public class Message {
	
	private static HashMap<String,Staff> messages = new HashMap<>();
	
	
	static {
		//��ʼ��Ա����Ϣ
		try {
			System.out.println("��ʼ��ʼ��Ա����Ϣ");
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
			System.out.println("Ա����Ϣ��ʼ����ϣ�");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
	//�޸ĆT����Ϣ
	public static void setEmp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ոݔ��T��id");
		String id = sc.nextLine();
		if(messages.containsKey(id)) {
			Staff emp = messages.get(id);
			System.out.println("1.�޸ĆT������");
			System.out.println("2.�޸ĆT�����g");
			System.out.println("3.�޸ĆT��н�Y");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1:
				System.out.println("Ոݔ���޸ĺ�ĆT������");
				String name = sc.nextLine();
				emp.setName(name);
				System.out.println("�޸ĳɹ���");
				break;
			case 2:
				System.out.println("Ոݔ���޸ĺ�����g");
				int age = Integer.parseInt(sc.nextLine());
				emp.setAge(age);
				System.out.println("�޸ĳɹ���");
				break;
			case 3:
				System.out.println("Ոݔ���޸ĺ��н�Y");
				double salary = Double.parseDouble(sc.nextLine());
				emp.setSalary(salary);
				System.out.println("�޸ĳɹ���");
				break;
			default:
				System.out.println("Ոݔ�����_����̖��");
				break;
			}
		}else {
			System.out.println("id�����ڣ�");
		}
		
		writeEmp();
	}
	
	//�h���T��
	public static void removeEmp(String id) {
		if (messages.containsKey(id)) {
			messages.remove(id);
			writeEmp();
			System.out.println("�h���ɹ���");
		}else {
			System.out.println("id�����ڣ�");
		}
	}
	
	//��ӡ���ІT��
	public static void printEmp() {
		Set<Entry<String,Staff>> emps = messages.entrySet();
		for(Entry<String,Staff> emp:emps) {
			System.out.println(emp.getValue());
		}
	}
	
	//��ӆT��
	public static void regEmp(String id,String name,int age,double salary) {
		if(messages.containsKey(id)) {
			System.out.println("���ʧ�ܣ�id�Ѵ��ڣ�");
			return;
		}
		messages.put(id, new Staff(id,name,age,salary));
		System.out.println("ע��ɹ�!");
		writeEmp();
	}
	
	//д��Ա����Ϣ
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
