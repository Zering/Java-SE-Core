package MultipleInheritance;

/**
 * 通过内部类实现多继承
 * @author Zhanghj @ 2017年1月11日
 *
 */
public class ByInnerClass {

	class FromFather extends Father{
		//可以选择重新strong方法
		@Override
		public void strong() {
			System.out.println("I'm more Strong");
		}
	}
	
	class FromMother extends Mother{
		//也可以不重写，直接使用
	}
	
	public void describe() {
		new FromFather().strong();
		new FromMother().kind();
	}
}


class Father{
	public void strong() {
		System.out.println("I'm Strong");
	}
}

class Mother{
	public void kind() {
		System.out.println("I'm kind");
	}
}