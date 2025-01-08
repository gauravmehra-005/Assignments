import java.util.Scanner;

abstract class Employee {
    private String name;
    private int age;
    private float sal;
    private String dgn;

    Employee(String name, int age, float sal, String dgn) {
        this.name = name;
        this.dgn = dgn;
        this.sal = sal;
        
        if (age <= 0) {
            System.out.println("You have inserted an invalid age. Setting age to 1 by default.");
            this.age = 1;
        } else {
            this.age = age;
        }
    }
    public String getdgn(){
	return dgn;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: Rs." + sal);
        System.out.println("Designation: " + dgn);
        System.out.println();
    }

    public void raiseSalary(float raise) {
        this.sal += raise;
    }
}

class Manager extends Employee {
    Manager(String name, int age, float sal) {
        super(name, age, sal, "Manager");
    }
}

class Clerk extends Employee {
    Clerk(String name, int age, float sal) {
        super(name, age, sal, "Clerk");
    }
}

class Programmer extends Employee {
    Programmer(String name, int age, float sal) {
        super(name, age, sal, "Programmer");
    }
}

public class EmployeeManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee[] employees = new Employee[100];
        int totalEmp = 0;

        while (true) {
            System.out.println("--------------------");
            System.out.println("1. Create\n2. Display\n3. Raise Salary\n4. Exit");
            System.out.println("--------------------");
            System.out.print("Enter choice: ");
            int choice1 = sc.nextInt();
            System.out.println("--------------------");

            switch (choice1) {
                case 1: // Create Employee
                    
                    while (true) {
			System.out.println("1. Clerk\n2. Programmer\n3. Manager\n4. Exit");
			System.out.println("--------------------");
                        System.out.print("Enter choice: ");
                        int empType = sc.nextInt();
                        if (empType == 4) break;

                        System.out.print("Enter Name: ");
                        String name = sc.next();
                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        System.out.print("Enter Salary: ");
                        float sal = sc.nextFloat();
                        switch (empType) {
                            case 1:
                                employees[totalEmp++] = new Clerk(name, age, sal);
                                break;
                            case 2:
                                employees[totalEmp++] = new Programmer(name, age, sal);
                                break;
                            case 3:
                                employees[totalEmp++] = new Manager(name, age, sal);
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
			System.out.println("--------------------");
                    }
                    break;

                case 2: 
                    for (int i = 0; i < totalEmp; i++) {
                        employees[i].display();
                    }
                    break;

                case 3: 
                    System.out.print("Enter Employee category to raise\n1. Clerk\n2. Programmer\n3. Manager\n");
                    int emptype = sc.nextInt();
		    String empdgn;
		    if(emptpye==1){ 
			empdgn="Clerk";	    
		    }	
		    else if(emptype==2){
                    	empdgn="Programmer";
                    }
	            else{
	   		empdgn="Manager";
                    }
		    System.out.print("Enter how much salary to raise: ");
                    Float raise=sc.nextFloat();
                    for (int i = 0; i < totalEmp; i++) {
			if(employees[i].getdgn().equals(empdgn)){
				employees[i].raiseSalary(raise);
			}
                    }
		    System.out.println("Salary Raised for"+empdgn+".");
                    break;

                case 4: 
                    System.out.println("Exiting...\nTotal Employees: " + totalEmp);
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
