package MultipleInheritance;

public class Test {

	public static void main(String[] args) {
		ByInterfaces a = new ByInterfaces();
		a.describe();
		
		ByInnerClass b = new ByInnerClass();
		b.describe();
		
		ByDefaultMethod c = new ByDefaultMethod();
		c.describe();
	}
}
