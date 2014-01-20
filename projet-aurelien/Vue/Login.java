package Vue;

import Gestion.Database;
import Gestion.Manager;

/**
 * Login class
 * 
 * @author Amir BEN SLIMANE
 */
public class Login {
	private Database bdd;
	private Manager manager;

	public Login() {
		this.setBdd(new Database());
		this.setManager(new Manager());
		heading();
		goToMenu();
	}

	/**
	 * 
	 * this method display on the screen Welcome to this application.
	 */
	private void heading() {
		System.out.println("Bienvenue dans ce gestionnaire.");
		System.out
				.println("Afin de vous connecter au serveur, veuillez entrer vos informations personnelles.");
	}

	/**
	 * 
	 * This method enable to login.
	 */
	private boolean login() {
		String email, password;

		System.out.println("Entrez votre adresse mail :");
		email = getManager().requestString();
		System.out.println("Entrez votre mot de passe :");
		password = getManager().requestString();

		if (bdd.isAuthorized(email, password)) {
			return true;
		}

		System.out.println("Erreur d'authentification !");

		return false;
	}

	/**
	 * 
	 * This method call goToMenu.
	 */
	private void goToMenu() {
		if (login()) {
			new Menu(this.getBdd());
		} else {
			goToMenu();
		}
	}

	/**
	 * @return the bdd
	 */
	public Database getBdd() {
		return bdd;
	}

	/**
	 * @param bdd
	 *            the bdd to set
	 */
	public void setBdd(Database bdd) {
		this.bdd = bdd;
	}

	/**
	 * @return the manager
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
