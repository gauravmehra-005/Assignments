package emp.assignment;

import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidAgeException extends Exception {
    public InvalidAgeException() {
        super();
    }

    public InvalidAgeException(String msg) {
        super(msg);
    }
}

class InvalidChoiceException extends Exception {
    public InvalidChoiceException() {
        super();
    }

    public InvalidChoiceException(String msg) {
        super(msg);
    }
}

abstract class Employee {
    private int eid;
    private String name;
    private int age;
    private float sal;
    private String dgn;

    public Employee(int eid, String name, int age, float sal, String dgn) throws InvalidAgeException {
        if (age < 21 || age > 60) {
            throw new InvalidAgeException("Age must be between 21 and 60.");
        }
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
    Manager(int eid, String name, int age) throws InvalidAgeException {
        super(eid, name, age, 100000, "Manager");
    }

    public void raiseSalary() {
        float newsal = getSal() + 15000f;
        setSal(newsal);
    }
}

final class Clerk extends Employee {
    Clerk(int eid, String name, int age) throws InvalidAgeException {
        super(eid, name, age, 20000, "Clerk");
    }

    public void raiseSalary() {
        float newsal = getSal() + 2000f;
        setSal(newsal);
    }
}

final class Programmer extends Employee {
    Programmer(int eid, String name, int age) throws InvalidAgeException {
        super(eid, name, age, 30000, "Programmer");
    }

    public void raiseSalary() {
        float newsal = getSal() + 5000f;
        setSal(newsal);
    }
}


public class EmployeeManagement_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee[] employees = new Employee[100];
        int totalEmp = 0;

        while (true) {
            System.out.println("--------------------");
            System.out.println("1. Create\n2. Display\n3. Raise Salary\n4. Remove\n5. Exit");
            System.out.println("--------------------");
            System.out.print("Enter choice: ");
            int choice1 = 0;
            try {
                choice1 = sc.nextInt();
                if (choice1 < 1 || choice1 > 5) {
                    throw new InvalidChoiceException("Invalid choice. Please choose between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (choice1) {
                case 1: 
                    while (true) {
                        System.out.println("1. Clerk\n2. Programmer\n3. Manager\n4. Exit");
                        System.out.println("--------------------");
                        System.out.print("Enter choice: ");
                        int empType = 0;
                        try {
                            empType = sc.nextInt();
                            if (empType < 1 || empType > 4) {
                                throw new InvalidChoiceException("Invalid choice. Please choose between 1 and 4.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.nextLine();
                            continue;
                        } catch (InvalidChoiceException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
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

                        System.out.print("Enter Name: ");
                        String name = sc.next();

                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        try {
                            switch (empType) {
                                case 1:
                                    employees[totalEmp] = new Clerk(eid, name, age);
                                    break;
                                case 2:
                                    employees[totalEmp] = new Programmer(eid, name, age);
                                    break;
                                case 3:
                                    employees[totalEmp] = new Manager(eid, name, age);
                                    break;
                           }
			    System.out.println("Employee created successfully!");
			    totalEmp++;
                        } catch (InvalidAgeException e) {
                            System.out.println(e.getMessage());
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
