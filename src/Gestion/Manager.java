package Gestion;

import java.util.Scanner;

/**
 * Class Manager TODO commenter cette classe
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Manager {
	private Scanner sc = new Scanner(System.in);

	public int requestInt(int min, int max) {
		int value = 0;
		try {
			value = Integer.parseInt(requestString());
			if (value < min || value > max) {
				System.out.println("Mauvaise entree veuillez resaisir votre demande svp.");
				return requestInt(min, max);
			}
		} catch (Exception e) {
			System.out.println("Mauvaise entree veuillez resaisir votre demande svp.");
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
