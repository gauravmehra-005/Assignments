package emp.assignment;

import java.util.*;
import java.util.regex.*;


abstract class Employee {
    private int eid;
    private String name;
    private int age;
    private float sal;
    private String dgn;

    public Employee(int eid, String name, int age, float sal, String dgn)  {
        this.age = age;
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

    public final void display() {
        System.out.println("Eid: " + eid);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: Rs." + sal);
        System.out.println("Designation: " + dgn);
        System.out.println();
    }

    public abstract void raiseSalary();

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

///////////////////////

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

public class EmployeeManagement_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee[] employees = new Employee[100];
        int totalEmp = 0;

        while (true) {
            System.out.println("--------------------");
            System.out.println("1. Create\n2. Display\n3. Raise Salary\n4. Remove\n5. Exit");
            System.out.println("--------------------");
            int choice1 = Checkers.choiceCheck(5);

            switch (choice1) {
                case 1: 
                    while (true) {
                        System.out.println("1. Clerk\n2. Programmer\n3. Manager\n4. Exit");
                        System.out.println("--------------------");
                        int empType = Checkers.choiceCheck(4);
                        if (empType == 4) break;

                        System.out.print("Enter Employee ID: ");
                        int eid = sc.nextInt();
                        boolean exists = false;
                        for (int i = 0; i < totalEmp; i++) {
                            if (eid == employees[i].getEid()) {
                                System.out.println("Invalid ID. Already in use!");
                                exists = true;
                                break;
                            }
                        }
                        if (exists) continue;
                        String name = Checkers.NameCheck();
                        int age =Checkers.AgeCheck(21,60);
                        switch (empType) {
                             case 1:
                                 employees[totalEmp++] = new Clerk(eid, name, age);
                                 break;
                             case 2:
                                 employees[totalEmp++] = new Programmer(eid, name, age);
                                 break;
                             case 3:
                                 employees[totalEmp++] = new Manager(eid, name, age);
                                 break;
                        } 
                    }
                    break;

                case 2: 
                    for (int i = 0; i < totalEmp; i++) {
                        employees[i].display();
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
                    for (int i = 0; i < totalEmp; i++) {
                        if (employees[i].getDgn().equals(empDgn)) {
                            employees[i].raiseSalary();
                        }
                    }
                    System.out.println("Salary raised for " + empDgn + " employees.");
                    break;

                case 4: 
                    System.out.print("Enter Employee ID to remove: ");
                    int eid = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < totalEmp; i++) {
                        if (employees[i].getEid() == eid) {
                            employees[i].display();
                            System.out.print("Do you want to remove this employee (Y/N)? ");
                            char ch = sc.next().charAt(0);
                            if (ch == 'Y' || ch == 'y') {
                                employees[i] = employees[--totalEmp];
                                employees[totalEmp] = null;
                                System.out.println("Employee removed successfully!");
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Employee ID not found.");
                    }
                    break;

                case 5: 
                    System.out.println("Exiting... Total Employees: " + totalEmp);
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
