package Vue;

import Gestion.Database;
import Personnel.Administrator;
import Personnel.Personnel;

/**
 * Menu class
 * 
 * @author Amir BEN SLIMANE
 */
public class Menu {
	private Database database;
	private Personnel user;

	public Menu(Database database) {
		setDatabase(database);
		setUser(this.getDatabase().getCurrentUser());
		welcome();
		displayMenu();
	}

	/**
	 * 
	 * Welcome message.
	 */
	private void welcome() {
		System.out.println("Bienvenue " + this.getUser().getFirstName() + " "
				+ this.getUser().getName() + " !");
		System.out.println("Etant " + this.getUser().toString()
				+ ", voici vos options disponibles :");
	}

	/**
	 * 
	 * This method diplay the interface for the user.
	 */
	private void displayMenu() {
		if (getUser() instanceof Administrator) {
			new AdminMenu(getDatabase());
		} else {
			new BorrowerMenu(getDatabase());
		}
	}

	/**
	 * @return the database
	 */
	public Database getDatabase() {
		return database;
	}

	/**
	 * @param database
	 *            the database to set
	 */
	public void setDatabase(Database database) {
		this.database = database;
	}

	/**
	 * @return the user
	 */
	public Personnel getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(Personnel user) {
		this.user = user;
	}
}
