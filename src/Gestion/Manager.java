package Gestion;

import java.util.Scanner;

/**
 * Class Manager
 * 
 * @author Amir BEN SLIMANE
 * 
 */
public class Manager {
	private Scanner sc = new Scanner(System.in);

	public int requestInt(int min, int max) {
		int value = 0;
		try {
			value = Integer.parseInt(requestString());
			if (value < min || value > max) {
				return requestInt(min, max);
			}
		} catch (Exception e) {
			return requestInt(min, max);
		}

		return value;
	}

	public String requestString() {
		String text = sc.nextLine();
		if (text.length() > 30) {
			return requestString();
		} else {
			return text;
		}
	}
}
