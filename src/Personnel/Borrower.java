package Personnel;

/**
 * Class Borrower
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Borrower extends Personnel {
	public Borrower(String firstName, String name, String email,
			String password, int identifiant) {
		super(firstName, name, email, password, identifiant);
	}

	public boolean equals(Borrower borrower) {
		if (this.getEmail().equals(borrower.getEmail())
				&& this.getFirstName().equals(borrower.getFirstName())
				&& this.getIdentifiant() == borrower.getIdentifiant()
				&& this.getName().equals(borrower.getName())
				&& this.getPassword().equals(borrower.getPassword())) {
			return true;
		}
		return false;
	}
}
