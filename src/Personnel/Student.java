package Personnel;

/**
 * Class Studient
 * 
 * @author Amir BEN SLIMANE
 * 
 */
public class Student extends Borrower {
	public Student(String firstName, String name, String email,
			String password, int identifiant) {
		super(firstName, name, email, password, identifiant);
	}

	@Override
	public String toString() {
		return "etudiant";
	}

}
