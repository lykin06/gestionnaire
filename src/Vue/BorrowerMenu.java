package Vue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import Gestion.Database;
import Gestion.ListMaterial;
import Gestion.ListReservations;
import Gestion.Manager;
import Gestion.Reservation;
import Material.Material;
import Personnel.Borrower;
import Personnel.Student;

/**
 * BorrowerMenu class
 * 
 * @author Amir BEN SLIMANE
 */
public class BorrowerMenu {
	private Database database;
	private Manager manager;
	private ListReservations listReservations;
	private ListMaterial listMaterial;

	public BorrowerMenu(Database database) {
		this.database = database;
		this.setManager(new Manager());
		this.listMaterial=new ListMaterial();
		this.listReservations = new ListReservations();
		if (this.getListMaterial().getMaterials() == null) {
			this.getListMaterial().reinitialize();
		}
		if (this.listReservations.getReservations() == null) {
			this.getListReservations().reinitialize();
		}
		this.studientMenu();
	}

	private void studientMenu() {
		System.out.println(this.getDatabase().getCurrentUser().getFirstName()
				+ " - " + this.getDatabase().getCurrentUser().toString());
		System.out.println("1. Voir le materiel emprunte");
		System.out.println("2. Emprunter du materiel");
		System.out.println("3. Rendre un materiel");
		System.out.println("4. Deconnexion");

		int value = this.getManager().requestInt(1, 4);

		switch (value) {
		case 1:
			displaysReservations();
			break;
		case 2:
			borrow();
			break;
		case 3:
			giveBack();
			break;
		case 4:
			new Login();
			break;
		default:
			break;
		}

		studientMenu();
	}

	private void giveBack() {
		System.out.println("Voici la liste du materiel reserves :");
		ArrayList<Reservation> res = this.getListReservations()
				.getReservationsOf(
						(Borrower) this.getDatabase().getCurrentUser());
		if (res.size() == 0) {
			System.out.println("Aucun materiel emprunte.");
		} else {
			for (int i = 0; i < res.size(); i++) {
				System.out.println((i + 1) + ". " + res.get(i).toString());
			}
			System.out.println("Quel materiel rendre ?");
			int indice = this.getManager().requestInt(1, res.size()) - 1;
			Reservation reservationDelete = res.get(indice);
			res.get(indice)
					.getMaterial()
					.setQuantity(
							res.get(indice).getMaterial().getQuantity() + 1);
			res.remove(indice);
			this.getListReservations().remove(reservationDelete);
		}
	}

	private void borrow() {
		System.out.println("Que voulez vous emprunter ?");
		ListMaterial list;
		if (this.getDatabase().getCurrentUser() instanceof Student) {
			list = new ListMaterial();
			list.setMaterials(this.getListMaterial().forStudients());
		} else {
			list = this.getListMaterial();
		}
		System.out.println(list.toString());
		int indice = this.getManager()
				.requestInt(1, list.getMaterials().size()) - 1;
		Date date = new Date();
		System.out.println("1. Des aujourd'hui.\n2. Faire une reservation");
		int value = this.getManager().requestInt(1, 2);
		if (value == 1) {
			date = new Date();
		} else {
			System.out
					.println("Entrer la date desiree sous la forme jour/mois/annee :");
			String[] str = this.getManager().requestString().split("/");
			date = new Date();
			date.setDate(Integer.parseInt(str[0]));
			date.setMonth(Integer.parseInt(str[1]));
			date.setYear(Integer.parseInt(str[2]));
		}

		this.listReservations.add(new Reservation((Borrower) this.getDatabase()
				.getCurrentUser(), list.getMaterials().get(indice), date));
		list.getMaterials()
				.get(indice)
				.setQuantity(
						this.getListMaterial().getMaterials().get(indice)
								.getQuantity() - 1);
	}

	private void displaysReservations() {
		ArrayList<Reservation> res = this.getListReservations()
				.getReservationsOf(
						(Borrower) this.getDatabase().getCurrentUser());
		if (res.isEmpty()) {
			System.out.println("Vous n'avez fait aucun emprunt en cours.");
		} else {
			System.out.println("Voici la liste du materiel reserves :");
			for (int i = 0; i < res.size(); i++) {
				System.out.println(res.get(i).toString());
			}
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

	/**
	 * @return the listReservations
	 */
	public ListReservations getListReservations() {
		return listReservations;
	}

	/**
	 * @param listReservations
	 *            the listReservations to set
	 */
	public void setListReservations(ListReservations listReservations) {
		this.listReservations = listReservations;
	}

	/**
	 * @return the listMaterial
	 */
	public ListMaterial getListMaterial() {
		return listMaterial;
	}

	/**
	 * @param listMaterial
	 *            the listMaterial to set
	 */
	public void setListMaterial(ListMaterial listMaterial) {
		this.listMaterial = listMaterial;
	}
}
