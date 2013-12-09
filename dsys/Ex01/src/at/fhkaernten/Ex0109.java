package at.fhkaernten;

public class Ex0109 {

	public static void main(String[] args) {
		SubFraction f1 = new SubFraction(1, 2), f2 = new SubFraction(1, 2);
		System.out.println(f1.getFraction());
		f1.add(f2);
		System.out.println(f1.getFraction());

	}

}
