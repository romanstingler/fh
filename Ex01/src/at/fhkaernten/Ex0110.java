package at.fhkaernten;

public class Ex0110 {
	
	public static void main(String[] args) {
		Clock sc = new SimpleClock();
		Clock ac = new AdvancedClock();
		
		System.out.println(sc.getTime());
		System.out.println(ac.getTime());
		
		System.out.println(((AdvancedClock) ac).getDate());
		
		// 10.5
		//extends is for when you're inheriting from a base class (i.e. extending its functionality).
		//implements is for when you're implementing an interface.
		
		// 10.6
		//Adapter is a pattern that provides default (often empty) implementation of interface or abstract class.
		//For example MouseAdapter provides empty implementation of MouseListener interface.
		//It is useful because very often you do not really use all methods declared by interface, so implementing the interface directly is very verbose.
		
		// 10.7
		//An interface allows somebody to start from scratch to implement your interface or implement your interface in some other code whose original or primary purpose was quite different from your interface.
		//An abstract class, in contrast, provides more structure. It usually defines some default implementations and provides some tools useful for a full implementation. Code using it must use your class as the base. That may be highly inconvenient if the other programmers wanting to use your package have already developed their own class hierarchy independently.
	}

}
