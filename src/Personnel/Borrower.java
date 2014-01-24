package Personnel;

/**
 * Class Borrower
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Borrower extends Personnel {
	private int cpt;
	public Borrower(String firstName, String name, String email,
			String password, int identifiant) {
		super(firstName, name, email, password, identifiant);
		this.cpt=0;
	}

	public boolean equals(Borrower borrower) {
		if (this.getEmail().equals(borrower.getEmail())
				&& this.getFirstName().equals(borrower.getFirstName())
				&& this.getIdentifiant() == borrower.getIdentifiant()
				&& this.getName().equals(borrower.getName())
				&& this.getPassword().equals(borrower.getPassword())
				&& this.cpt == borrower.getCompteur()) {
			return true;
		}
		return false;
	}
	
	public int getCompteur(){
		return this.cpt;
	}
	public void setCompteur(int compteur){
		this.cpt=compteur;
		
	}
}
