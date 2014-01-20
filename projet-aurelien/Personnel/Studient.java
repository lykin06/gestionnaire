package Personnel;

/**
 * Class Studient
 * 
 * @author Amir BEN SLIMANE
 * 
 */
public class Studient extends Borrower {
	public Studient(String firstName, String name, String email,
			String password, int identifiant) {
		super(firstName, name, email, password, identifiant);
	}

	@Override
	public String toString() {
		return "etudiant";
	}

}
