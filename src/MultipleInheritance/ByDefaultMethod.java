package MultipleInheritance;
/**
 * 通过接口的默认方法实现内部类，Java8以后版本
 * @author Zhanghj @ 2017年1月11日
 *
 */
public class ByDefaultMethod implements A,B{

	//不用重写a,b方法，但却可以调用
	public void describe() {
		a();
		b();
	}
	
	@Override
	public void a() {
		System.out.println("I'm override A.a()");
	}
}

interface A{
	default void a(){
		System.out.println("Do something about A");
	}
}

interface B{
	default void b(){
		System.out.println("Do something about B");
	}
}