package emp.assignment;

import java.util.*;
import java.util.regex.*;
import java.io.*;

abstract class Employee {
    private int eid;
    private String name;
    private int age;
    private float sal;
    private String dgn;

    public static Map<Integer, Employee> empMap = new HashMap<>();

    public Employee(int eid, String name, int age, float sal, String dgn) {
        this.eid = eid;
        this.name = name;
        this.age = age;
        this.sal = sal;
        this.dgn = dgn;
    }

    public int getEid() {
        return eid;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getDgn() {
        return dgn;
    }

    protected void setSal(float sal) {
        this.sal = sal;
    }

    public float getSal() {
        return sal;
    }


    public static final void search(int type) {
        Scanner sc = new Scanner(System.in); 
        int count = 0;

        switch (type) {
            case 1: 
                System.out.println("Enter the ID:");
                int eid = sc.nextInt();
                if (empMap.containsKey(eid)) {
                    empMap.get(eid).display();
                    count++;
                }
                break;

            case 2: 
                System.out.println("Enter the Designation:");
                String eDgn = sc.next();
                for (Map.Entry<Integer, Employee> entry : empMap.entrySet()) {
                    if (entry.getValue().getDgn().equalsIgnoreCase(eDgn)) {
                        entry.getValue().display();
                        count++;
                    }
                }
                break;

            case 3: 
                System.out.println("Enter the Name:");
                String eName = sc.nextLine();
                for (Map.Entry<Integer, Employee> entry : empMap.entrySet()) {
                    if (entry.getValue().getName().equalsIgnoreCase(eName)) {
                        entry.getValue().display();
                        count++;
                    }
                }
                break;

            default:
                System.out.println("Invalid search type!");
                break;
        }

        if (count == 0) {
            System.out.println("No Match found!");
        }
    }
    public String toString(){
	return (eid+","+name+","+age+","+dgn+","+sal+"\n");
    }

    public final void display() {
        System.out.println("Eid: " + eid);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: Rs." + sal);
        System.out.println("Designation: " + dgn);
        System.out.println();
    }
    public abstract void raiseSalary();

    public static void writeAndSaveInFile(String fileName){
	try(BufferedWriter bw=new BufferedWriter(new FileWriter(fileName))){
		for(Employee e:Employee.empMap.values()){
			String temp=e.toString();
			bw.write(temp);
		}
		System.out.println("Employee data successfully saved.");
	}catch(Exception e){System.out.println(e.getMessage());}
    }

    public static void readAndLoadInMap(String fileName){	
	try(BufferedReader br=new BufferedReader(new FileReader(fileName))){
        while(br.ready()){
           String temp=br.readLine();
           String[] tokens=temp.split(",");
	   if(tokens.length==5){
		int eid = Integer.parseInt(tokens[0].trim());
                String name = tokens[1].trim();
                int age = Integer.parseInt(tokens[2].trim());
                String dgn = tokens[3].trim();
		float sal = Float.parseFloat(tokens[4].trim());
		if(dgn=="Clerk")
			Employee.empMap.put(eid,new Clerk(eid, name, age));
		else if(dgn=="Programmer")
			Employee.empMap.put(eid,new Programmer(eid, name, age));
		else
			Employee.empMap.put(eid,new Manager(eid, name, age));
		
	   }
        }
  	System.out.println("Successfully loaded From File.");
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	
    }
}



final class Manager extends Employee {
    Manager(int eid, String name, int age)  {
        super(eid, name, age, 100000, "Manager");
    }

    public void raiseSalary() {
        float newsal = getSal() + 15000f;
        setSal(newsal);
    }
}

final class Clerk extends Employee {
    Clerk(int eid, String name, int age)  {
        super(eid, name, age, 20000, "Clerk");
    }

    public void raiseSalary() {
        float newsal = getSal() + 2000f;
        setSal(newsal);
    }
}

final class Programmer extends Employee {
    Programmer(int eid, String name, int age)  {
        super(eid, name, age, 30000, "Programmer");
    }

    public void raiseSalary() {
        float newsal = getSal() + 5000f;
        setSal(newsal);
    }
}


///////Custom Exceptions////// 

class InvalidAgeException extends RuntimeException {
    public InvalidAgeException() {
        super();
    }

    public InvalidAgeException(String msg) {
        super(msg);
    }
}

class InvalidChoiceException extends RuntimeException {
    public InvalidChoiceException() {
        super();
    }

    public InvalidChoiceException(String msg) {
        super(msg);
    }
}

class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
        super();
    }

    public InvalidNameException(String msg) {
        super(msg);
    }
}

///////////////////////Checkers/////////////////////////////////

class Checkers{
	public static String NameCheck(){	
		while(true){
			System.out.print("Enter name:");
			try{
				 String name = new Scanner(System.in).nextLine();
				Pattern pat = Pattern.compile("^[A-Z][a-z]* [A-Z][a-z]*$");
				Matcher m=pat.matcher(name);
				if(m.matches())
					return name;
				else
					throw new InvalidNameException("Wrong Format of name");
			}catch (InputMismatchException e) {
               			 System.out.println("Invalid input. Please enter a string.");
            		} catch (InvalidNameException e) {
             			 System.out.println(e.getMessage());
           		}
		}
	} 
	public static int AgeCheck(int minAge,int maxAge){
		
		while(true){
			System.out.print("Enter age:");
			try{
				int age = new Scanner(System.in).nextInt();
				if (age < minAge || age > maxAge) {
            				throw new InvalidAgeException("Age must be between"+minAge+" and "+maxAge+".");
        			}
				else
					return age;
			}catch (InputMismatchException e) {
               			 System.out.println("Invalid input.Please enter a Number.");
            		} catch (InvalidAgeException e) {
             			 System.out.println(e.getMessage());
           		}
		}
	}
	public static int choiceCheck(int maxChoice){
		while(true){
			System.out.print("Enter Choice: ");
			try{
				int choice=new Scanner(System.in).nextInt();
				if(choice<1 || choice>maxChoice){
					throw new InvalidChoiceException("Choice must be between 1 and "+maxChoice);
				}
				else	return choice;
			}catch (InputMismatchException e) {
               			 System.out.println("Invalid input.Please enter a Number.");
            		} catch (InvalidChoiceException e) {
             			 System.out.println(e.getMessage());
           		}
		}

	}
}

///////////////////////////////Public Class/////////////////////////////////////////

public class EmployeeManagement_6 {
    public static void main(String[] args) {
	String fileName="employee.txt";
	File f=new File(fileName);
	if(f.exists() && f.isFile())
		Employee.readAndLoadInMap(fileName);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------");
            System.out.println("1. Create\n2. Display\n3. Raise Salary\n4. Remove\n5. Search\n6. Exit");
            System.out.println("--------------------");
            int choice1 = Checkers.choiceCheck(6);

            switch (choice1) {
                case 1: 
                    while (true) {
                        System.out.println("1. Clerk\n2. Programmer\n3. Manager\n4. Exit");
                        System.out.println("--------------------");
                        int empType = Checkers.choiceCheck(4);
                        if (empType == 4) break;

                        System.out.print("Enter Employee ID: ");
                        int eid = sc.nextInt();
			if(Employee.empMap.containsKey(eid)){
                                System.out.println("Invalid ID. Already in use!");
                                break;
			}
                        String name = Checkers.NameCheck();
                        int age =Checkers.AgeCheck(21,60);
			
                        switch (empType) {
                             case 1:
                                 Employee.empMap.put(eid,new Clerk(eid, name, age));
                                 break;
                             case 2:
                                 Employee.empMap.put(eid,new Programmer(eid, name, age));
                                 break;
                             case 3:
                                 Employee.empMap.put(eid,new Manager(eid, name, age));
                                 break;
                        }
                    }
                    break;

                case 2: 
		    if(Employee.empMap.size()==0){
				System.out.println("Currently there are no employees!");
				break;
			}
                    System.out.println("Enter the record through which you want to sort the employees:\n1. Eid\n2. Age\n3. Name\n4. Salary\n5. Designation");
                    int sortChoice = Checkers.choiceCheck(5);
                    List<Employee> employees = new ArrayList<>(Employee.empMap.values());

                    switch (sortChoice) {
                        case 1:
                            employees.sort(Comparator.comparingInt(Employee::getEid));
                            break;
                        case 2:
                            employees.sort(Comparator.comparingInt(Employee::getAge));
                            break;
                        case 3:
                            employees.sort(Comparator.comparing(Employee::getName));
                            break;
                        case 4:
                            employees.sort(Comparator.comparingDouble(Employee::getSal));
                            break;
                        case 5:
                            employees.sort(Comparator.comparing(Employee::getDgn));
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            continue;
                    }

                    for (Employee emp : employees) {
                        emp.display();
                    }
                    break;

                case 3: 
                    System.out.print("Enter Employee category to raise salary:\n1. Clerk\n2. Programmer\n3. Manager\n");
                    int empType = sc.nextInt();
                    String empDgn = "";
                    switch (empType) {
                        case 1:
                            empDgn = "Clerk";
                            break;
                        case 2:
                            empDgn = "Programmer";
                            break;
                        case 3:
                            empDgn = "Manager";
                            break;
                        default:
                            System.out.println("Invalid category.");
                            continue;
                    }
                    for (int eid:Employee.empMap.keySet()) {
                        if (Employee.empMap.get(eid).getDgn().equals(empDgn)) {
                            Employee.empMap.get(eid).raiseSalary();
                        }
                    }
                    System.out.println("Salary raised for " + empDgn + " employees.");
                    break;

                case 4: 
                    System.out.print("Enter Employee ID to remove: ");
                    int id = sc.nextInt();
	            if(Employee.empMap.containsKey(id)){
			Employee.empMap.get(id).display();
			System.out.print("Do you want to remove this employee (Y/N)? ");
                        char ch = sc.next().charAt(0);
                        if (ch == 'Y' || ch == 'y') {
			    Employee.empMap.remove(id);
                            System.out.println("Employee removed successfully!");
                        }      
                    }
                    else{
                        System.out.println("Employee ID not found.");
                    }
                    break;
		case 5:
		     System.out.println("Search on the basis of \n1.Id \n2.Designation \n3.Name");
		     int ch5=Checkers.choiceCheck(3);
		     Employee.search(ch5);
		     break;
                case 6: 
                    Employee.writeAndSaveInFile(fileName);
                    System.out.println("Exiting... Total Employees: " + Employee.empMap.size());
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
