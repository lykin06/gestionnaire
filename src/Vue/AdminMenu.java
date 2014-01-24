package Vue;

import java.util.ArrayList;

import Gestion.Database;
import Gestion.ListBorrowing;
import Gestion.ListMaterial;
import Gestion.ListReservations;
import Gestion.Manager;
import Gestion.Reservation;
import Material.Material;
import Personnel.Administrator;
import Personnel.Borrower;

/**
 * <b>AdminMenu class<b>
 * <p>
 * This class operates all the options available to an administrator.
 * </p>
 * <p>
 * An administarator can do the following things:
 * <ul>
 * <li>Manage the reservations</li>
 * <li>Manage the users</li>
 * <li>Manage the materials</li>
 * <li>See the actual statistics</li>
 * </ul>
 * </p>
 * 
 * @author Aurelien COLOMBET
 */
public class AdminMenu {
    private Administrator user;
    private Manager manager;
    private Database database;
    private ListMaterial listMaterial;
    private ListReservations listReservations;
    private ListBorrowing listBorrowing;

    /**
     * <b>Constructor</b>
     * 
     * @param database
     */
    public AdminMenu(Database database, Administrator user) {
        this.manager = new Manager();
        this.database = database;
        this.listMaterial = new ListMaterial();
        this.listReservations = new ListReservations();
        if (this.listMaterial.getMaterials() == null) {
            this.listMaterial.reinitialize();
        }
        if (this.listReservations == null) {
            this.listReservations.reinitialize();
        }
        this.listBorrowing = new ListBorrowing();
        this.user = user;
        adminInterface();
    }

    /**
     * 
     * This method shows the admin's interface.
     */
    private void adminInterface() {
        System.out.println(this.user.getFirstName() + " - Administrateur");
        System.out.println("1. Gestion des demandes de pret.");
        System.out.println("2. Gestion des utilisateurs.");
        System.out.println("3. Gestion du materiel");
        System.out.println("4. Voir les statistiques actuelles");
        System.out.println("5. Deconnexion");

        int value = this.manager.requestInt(1, 5);

        switch (value) {
        case 1:
            this.managementLoan();
            break;
        case 2:
            new userGestion(database, user, manager,listReservations);
            break;
        case 3:
            this.managementMaterial();
            break;
        case 4:
            this.statistiques();
            break;
        case 5:
            new Login();
            break;
        default:
            break;
        }

        this.adminInterface();
    }

    /**
     * 
     * This method shows the command on the Material which can be use by the
     * admin.
     */
    private void managementMaterial() {
        System.out.println("1. Ajouter un materiel.");
        System.out.println("2. Supprimer un materiel.");
        System.out.println("3. Afficher la liste des materiels");
        System.out
                .println("4. Reinitialiser le stock de materiel (!Attention cela va Reinitialiser les reservations)");
        System.out.println("5. Annuler");

        int value = this.manager.requestInt(1, 5);

        switch (value) {
        case 1:
            this.addMaterial();
            break;
        case 2:
            this.removeMaterial();
            break;
        case 3:
            this.displayMaterials();
            break;
        case 4:
            this.listMaterial.reinitialize();
            this.listReservations.reinitialize();
            break;
        default:
            break;
        }

        new AdminMenu(database, user);
    }

    /**
     * 
     * Method which show all the Materials in the stock.
     */
    private void displayMaterials() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();

        System.out.println("Le stock contient :");
        for (int i = 0; i < materials.size(); i++) {
            System.out.println(materials.get(i).toString() + " ["
                    + materials.get(i).getQuantity() + "]");
        }
        System.out.println("Liste des reservations acceptes :\n");
        this.displayReservationsAccepted();

    }

    private void displayReservationsAccepted() {
        ArrayList<Reservation> res = this.listReservations.getReservations();
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).isAccepted()) {
                System.out.println((i) + " - " + res.get(i).toString());
            }
        }
        System.out.println();
    }

    /**
     * 
     * This method can be use by the admin in order to show the variation of the
     * stock when he remove some Materials
     */
    private void removeMaterial() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();
        System.out
                .println("Quel est le materiel que vous souhaitez retirer du stock ?");
        for (int i = 0; i < materials.size(); i++) {
            System.out.println((i + 1) + ". " + materials.get(i).toString());
        }

        int indice = this.manager.requestInt(1, 9) - 1;

        System.out.println("Combien ? Il en reste "
                + materials.get(indice).getQuantity() + ".");
        int n = manager.requestInt(0, materials.get(indice).getQuantity());
        materials.get(indice).setQuantity(
                materials.get(indice).getQuantity() - n);

        this.listMaterial.store();
    }

    /**
     * 
     * This method can be use by the admin in order to show the variation of the
     * stock when he add some Materials
     */
    private void addMaterial() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();

        this.displayMaterials();
        System.out
                .println("Quel est le materiel que vous souhaitez ajouter au stock ?");
        int indice = this.manager.requestInt(1, materials.size()) - 1;

        System.out.println("Combien ? Il en reste "
                + materials.get(indice).getQuantity() + ".");
        int n = manager.requestInt(0, materials.get(indice).getQuantity());
        materials.get(indice).setQuantity(
                materials.get(indice).getQuantity() + n);

        this.listMaterial.setMaterials(materials);
        this.listMaterial.store();
    }

    private void managementLoan() {
        ArrayList<Reservation> res = this.listReservations.getReservations();
        boolean isaccepted=false;
        for (int j = 0; j < res.size(); j++) {
            if (!res.get(j).isAccepted()) {
                isaccepted=true;
                break;
            }
        }
        if (isaccepted==false) {
            System.out.println("Aucune demande de pret.");
        } else {
            for (int i = 0; i < res.size(); i++) {
                if (!res.get(i).isAccepted()) {
                    System.out.println((i) + " - " + res.get(i).toString());
                }
            }

            System.out.println("Quel demande de reservation autoriser ? (-1 pour quitter)");

            int indice = this.manager.requestInt(-1, res.size() - 1);
            if(indice!=-1){
	            while(res.get(indice).isAccepted()){
	            	System.out.println("Mauvaise entree veuillez resaisir votre demande svp.");
	            	indice = this.manager.requestInt(-1, res.size() - 1);
	            	if(indice==-1){
	            		break;
	            	}
	            }
	            
	            if(indice!=-1){
		            this.listReservations.authorize(this.listReservations
		                    .getReservations().get(indice));
		            this.listReservations.store();
	            }
            }

        }
    }

    private void statistiques() {
        System.out.println("1: le materiel le plus emprunte");
        System.out.println("2: le plus gros emprunteurs ");
        int rep = this.manager.requestInt(1, 2);
        switch (rep) {
        case 1:
            String leplusemprunte = this.listBorrowing.leplusEmprunte();
            if (leplusemprunte.equals("Null")) {
                System.out
                        .println("Aucun emprunts en cours. Impossible de savoir quel materiel est le plus emprunte");
            } else {
                System.out
                        .println("le material le plus emprunte est actuellement: "
                                + leplusemprunte);
            }
            break;
        case 2:
            this.emprunteur();
            break;
        default:
            break;
        }
    }

    public void emprunteur() {
        Borrower b = listBorrowing.getReservation(0).getBorrower();
        int max = this.listBorrowing.getNombreReservationsOf(b);
        for (int i = 1; i < this.listBorrowing.getListBorrowing().size(); i++) {
            Borrower borrower = listBorrowing.getReservation(i).getBorrower();
            if (listBorrowing.getNombreReservationsOf(borrower) > max) {
                b = this.listBorrowing.getReservation(i).getBorrower();
                max = listBorrowing.getNombreReservationsOf(b);
            }

        }
        System.out.println("Actuellement le plus gros emprunteur est: "
                + b.toString());
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
}
