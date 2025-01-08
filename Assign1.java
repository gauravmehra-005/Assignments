import java.util.Scanner;
class Employee{
	private String name;
	private int age;
	private float sal;
	private String dgn;
		
	Employee(String name, int age, float sal, String dgn){
		this.sal=sal;
		if(age<=0){
			System.out.println("You have typed an invalid age");
		}
		else{
			this.age=age;
		}
		this.name=name;
		this.dgn=dgn;	
	}
	public void display(){
		System.out.println("Name- "+name);
		System.out.println("Age- "+age);
		System.out.println("Salary- Rs."+sal);			     				
                System.out.println("Designation- "+dgn);
		System.out.println();
	}
	public void raiseSalary(){
		System.out.println("How much salary you want to increment");	
		float raise;
		Scanner sc=new Scanner(System.in);
		raise=sc.nextFloat();
		sal+=raise;
		System.out.println("The updated salary is Rs"+ sal);	
		sc.close();
	}	
	
};

class Manager extends Employee{
	Manager(String name,int age,float sal){
		super(name,age,sal,"Manager");
	}
};

class Clerk extends Employee{
	Clerk(String name,int age,float sal){
		super(name,age,sal,"Clerk");
	}
};


class Programmer extends Employee{
	Programmer(String name,int age,float sal){
		super(name,age,sal,"Programmer");	
	}
};

public class Assign1{
	public static void main(String args[]){
		Clerk c=new Clerk("Random", 20,1000000);
		c.display();
		Manager m=new Manager("Newman",30,1200000);
		m.display();
		m.raiseSalary();
	}
}