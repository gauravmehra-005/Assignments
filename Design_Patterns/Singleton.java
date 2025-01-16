//////Early Instantiation ////////////
class A{
	static A a1=new A();
	private A(){
		System.out.println("Object created--Early");
	}
	public static A getObj(){	
		return a1;
	}
}
//////Lazy Instantiation//////////////
class B{
	static B b1=null;
	private B(){	
		System.out.println("Object created--Lazy");
	}
	public static B getObj(){	
		if(b1==null)
		{
			b1=new B();
		}
		return b1;
	}
}
public class Singleton{	
	public static void main(String args[]){
		A o1=A.getObj();
		B o2=B.getObj();
		System.out.println(o1);
		System.out.println(o2);
	}
}