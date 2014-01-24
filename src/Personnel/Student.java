package Personnel;

/**
 * Class Studient
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Student extends Borrower {
	public Student(String firstName, String name, String email,
			String password, int identifiant, int delay) {
		super(firstName, name, email, password, identifiant, delay);
	}

	@Override
	public String toString() {
		return "etudiant";
	}

}
