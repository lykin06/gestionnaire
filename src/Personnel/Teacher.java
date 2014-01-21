package Personnel;

/**
 * Class Studient.
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Teacher extends Borrower {
	public Teacher(String firstName, String name, String email,
			String password, int identifiant) {
		super(firstName, name, email, password, identifiant);
	}

	@Override
	public String toString() {
		return "enseignant";
	}
}
