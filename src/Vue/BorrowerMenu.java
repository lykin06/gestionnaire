package Vue;

import java.util.ArrayList;
import java.util.Date;

import Gestion.Database;
import Gestion.ListMaterial;
import Gestion.ListReservations;
import Gestion.Manager;
import Gestion.Reservation;
import Personnel.Borrower;
import Personnel.Personnel;
import Personnel.Student;

/**
 * BorrowerMenu class
 * 
 * @author Aurelien COLOMBET
 */
public class BorrowerMenu {
    private Database database;
    private Manager manager;
    private ListReservations listReservations;
    private ListMaterial listMaterial;

    public BorrowerMenu(Database database) {
        this.database = database;
        this.setManager(new Manager());
        this.listMaterial = new ListMaterial();
        this.listReservations = new ListReservations();
        if (this.getListMaterial().getMaterials() == null) {
            this.getListMaterial().reinitialize();
        }
        if (this.listReservations.getReservations() == null) {
            this.listReservations.reinitialize();
        }
        this.studentMenu();
    }

    private void studentMenu() {
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

        studentMenu();
    }

    private void giveBack() {
        System.out.println("Voici la liste du materiel reserves :");
        ArrayList<Reservation> res = this.listReservations.getReservationsOf((Borrower) this.database.getCurrentUser());
        if (res.size() == 0) {
            System.out.println("Aucun materiel emprunte.");
        } else {
            for (int i = 0; i < res.size(); i++) {
                System.out.println((i + 1) + ". " + res.get(i).toString());
            }
            System.out.println("Quel materiel rendre ?");
            int indice = this.getManager().requestInt(1, res.size()) - 1;
            Reservation reservationDelete = res.get(indice);
            res.get(indice).getMaterial().setQuantity(res.get(indice).getMaterial().getQuantity() + 1);
            res.remove(indice);
            this.listReservations.remove(reservationDelete);
            
        }
    }

    /**
     * <b>Method to borrow materials</b>
     */
    @SuppressWarnings("deprecation")
    private void borrow() {
        System.out.println("Que voulez vous emprunter ?");
        Personnel user = database.getCurrentUser();
        
        if (user instanceof Student) {
            System.out.println(listMaterial.avaibleMaterials(true));
        } else {
            System.out.println(listMaterial.avaibleMaterials(false));
        }

        int indice = manager.requestInt(1, listMaterial.getMaterials().size()) - 1;
        Date date = new Date();
        System.out.println("1. Des aujourd'hui.\n2. Faire une reservation");
        int value = this.getManager().requestInt(1, 2);
        if (value == 2) {
            System.out
                    .println("Dans combien de jours voulez-vous l'objet (100 jours max):");
            int jours = manager.requestInt(1, 100);
            date = new Date(date.getYear(), date.getMonth(), date.getDate()
                    + jours);
        }

        listReservations.add(new Reservation((Borrower) user, listMaterial
                .getMaterials().get(indice), date));
        listMaterial
                .getMaterials()
                .get(indice)
                .setQuantity(
                        listMaterial.getMaterials().get(indice).getQuantity() - 1);
        listMaterial.store();
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
