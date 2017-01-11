package MultipleInheritance;

/**
 * 通过接口实现多继承
 * @author Zhanghj @ 2017年1月11日
 *
 */
public class ByInterfaces implements DoA, DoB {

	@Override
	public void a() {
		System.out.println("Do something about A");
	}

	@Override
	public void b() {
		System.out.println("Do something about B");
	}
	
	public void describe() {
		a();
		b();
	}

}

interface DoA {
	void a();
}

interface DoB {
	void b();
}
