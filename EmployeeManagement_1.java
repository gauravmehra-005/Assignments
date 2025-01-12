package emp.assignment;
import java.util.Scanner;

abstract class Employee {
    private int eid;
    private String name;
    private int age;
    private float sal;
    private String dgn;
    
    public Employee(int eid,String name,int age,float sal,String dgn){
	if (age < 18) {
            System.out.println("Invalid age");
	    return;
	}
	this.eid=eid;
	this.name=name;
	this.age=age;
	this.sal=sal;
	this.dgn=dgn;
    }
    public int getEid(){
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
    Manager(int eid,String name, int age) {
	super(eid,name,age,100000,"Manager");
    }
    public void raiseSalary(){
	float newsal=getSal()+15000f;
	setSal(newsal);
    }
}

final class Clerk extends Employee {
    Clerk(int eid,String name, int age) {
	super(eid,name,age,20000,"Clerk");
    }
    public void raiseSalary(){
	float newsal=getSal()+2000f;
	setSal(newsal);
    }
}

final class Programmer extends Employee {
    Programmer(int eid,String name, int age) {
	super(eid,name,age,30000,"Programmer");
    }
    public void raiseSalary(){
	float newsal=getSal()+5000f;
	setSal(newsal);
    }
}

public class EmployeeManagement_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee[] employees = new Employee[100];
        int totalEmp = 0;

        while (true) {
            System.out.println("--------------------");
            System.out.println("1. Create\n2. Display\n3. Raise Salary\n4. Remove\n5. Exit");
            System.out.println("--------------------");
            System.out.print("Enter choice: ");
            int choice1 = sc.nextInt();
            System.out.println("--------------------");
            switch (choice1) {
                case 1: 
                    while (true) {
                        System.out.println("1. Clerk\n2. Programmer\n3. Manager\n4. Exit");
                        System.out.println("--------------------");
                        System.out.print("Enter choice: ");
                        int empType = sc.nextInt();
                        if (empType == 4) break;
			
			System.out.print("Enter eid");
			int eid=sc.nextInt();	
			int f=0;
			for(int i=0;i<totalEmp;i++)
			{
				if(eid==employees[i].getEid())
				{
					System.out.println("Invalid eid. Already in use!!");
					f=1;	
					break;
				}
			}
			if(f==1)
				continue;
                        System.out.println("Enter Name: ");
                        String name = sc.next();
			System.out.println("Enter Age: ");
                        int age = sc.nextInt();
                        switch (empType) {
                            case 1:
                                employees[totalEmp++] = new Clerk(eid,name, age);
                                break;
                            case 2:
                                employees[totalEmp++] = new Programmer(eid,name, age);
                                break;
                            case 3:
                                employees[totalEmp++] = new Manager(eid,name, age);
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
                    String empdgn = "";
                    
                    if (emptype == 1) {
                        empdgn = "Clerk";
                    } else if (emptype == 2) {
                        empdgn = "Programmer";
                    } else if (emptype == 3) {
                        empdgn = "Manager";
                    } else {
                        System.out.println("Invalid category.");
                        break;
                    }
                    for (int i = 0; i < totalEmp; i++) {
                        if (employees[i].getDgn().equals(empdgn)) {
                            employees[i].raiseSalary();
                        }
                    }
                    System.out.println("Salary Raised for " + empdgn + ".");
                    break;
		case 4: 
		    System.out.println("Enter emp id: ");
	            int teid=sc.nextInt();
		    int f=1;
		    for(int i=0;i<totalEmp;i++){
			if(teid==employees[i].getEid()){
				employees[i].display();
				System.out.println("Do you really want to remove this employee record (Y/N): ");
				char ch=sc.next().charAt(0);
				if(ch=='Y'){
					employees[i]=employees[totalEmp-1];
					employees[totalEmp-1]=null;
					totalEmp--;
					f=0;
				}
				else{
					continue;
				}
				break;
			}	
		    }
		    if(f==1){
			System.out.println("Invalid Employee id");
		    }
		    break;
                case 5: 
                    System.out.println("Exiting...\nTotal Employees: " + totalEmp);
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
