package Personnel;

/**
 * Class Administrator
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Administrator extends Personnel {
	public Administrator(String firstName, String name, String email,
			String password, int identifiant) {
		super(firstName, name, email, password, identifiant);
	}

	@Override
	public String toString() {
		return "administrateur";
	}
}
