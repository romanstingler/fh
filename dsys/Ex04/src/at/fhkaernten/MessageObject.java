package at.fhkaernten;

import java.io.Serializable;

public class MessageObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int number = 0;
	private int objNumber = 0;

	public MessageObject() {
		this.objNumber = ++number;
	}

	public static int getNumber() {
		return number;
	}

	public int getObjNumber() {
		return objNumber;
	}

	@Override
	public String toString() {
		return ("Number: " + this.getObjNumber() + " objNumber: " + MessageObject
				.getNumber());

	}

}
