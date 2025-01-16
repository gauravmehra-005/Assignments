class A {
        private static A a1=null;
	A(){
		if(this instanceof B)	
			System.out.println("B instance created");
		else if(this instanceof A && a1==null)
			a1=this;
		else
			throw new ObjectAlreadyInstantiated();
	}
	public static A getObject(){
		if(a1==null)
			a1=new A();
		return a1;
	}
}

class B extends A{
	private static B b1=new B();
	private B(){
	} 
	public static B getObject(){
		return b1;
	}
}
class ObjectAlreadyInstantiated extends RuntimeException{
	public ObjectAlreadyInstantiated(){
		super();
	}
	public ObjectAlreadyInstantiated(String msg){
		super(msg);
	}
	
}
public class SingletonAssignment{
	public static void main(String args[]){
		A a1=A.getObject();
		B b1=B.getObject();
		A a2=A.getObject();
		B b2=B.getObject();
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(b1);
		System.out.println(b2);
	}
}