package Vue;

import java.util.ArrayList;

import Gestion.Database;
import Gestion.ListMaterial;
import Gestion.ListReservations;
import Gestion.Manager;
import Gestion.Reservation;
import Material.Material;
import Personnel.Administrator;

/**
 * <b>MaterialGestion Class</b>
 * <p>
 * This class provides an interface to manage all the materials.
 * </p>
 * 
 * @author mathieu BOUTELIER
 * 
 */
public class MaterialGestion {
    private Administrator user;
    private Manager manager;
    private Database database;
    private ListMaterial listMaterial;
    private ListReservations listReservations;

    public MaterialGestion(Database database, Administrator administrator,
            Manager manager, ListMaterial listMaterial,
            ListReservations listReservations) {
        this.manager = manager;
        this.database = database;
        this.user = administrator;
        this.listMaterial = listMaterial;
        this.listReservations = listReservations;

        this.managementMaterial();
    }

    /**
     * <b>Shows the command on materials</b>
     * <p>
     * An administrator can do the following actions on materials:
     * <ul>
     * <li>Add a new material</li>
     * <li>Delete a material</li>
     * <li>See the list of materials</li>
     * <li>Reset the stock and the reservation</li>
     * TODO envois de matos en réparation
     * </ul>
     * </p>
     */
    private void managementMaterial() {
        System.out.println("1. Ajouter un materiel.");
        System.out.println("2. Supprimer un materiel.");
        System.out.println("3. Afficher la liste des materiels");
        System.out
                .println("4. Reinitialiser le stock de materiel (!Attention cela va Reinitialiser les reservations)");
        System.out.println("5. Retour");

        int value = this.manager.requestInt(1, 5);

        switch (value) {
        case 1:
            this.addMaterial();
            break;
        case 2:
            this.removeMaterial();
            break;
        case 3:
            System.out.println(this.displayMaterials(true));
            break;
        case 4:
            this.listMaterial.reinitialize();
            this.listReservations.reinitialize();
            break;
        case 5:
            new AdminMenu(database, user);
            return;
        default:
            break;
        }

        this.managementMaterial();
    }

    /**
     * <b>Add material to the stock</b>
     * <p>
     * This method can be use by the administrator in order to show the
     * variation of the stock when he add some Materials. He can add at the
     * maximum 10 objects.
     * </p>
     */
    private void addMaterial() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();

        System.out.println(this.displayMaterials(false));
        System.out
                .println("Quel est le materiel que vous souhaitez ajouter au stock ?");
        int indice = this.manager.requestInt(1, materials.size()) - 1;

        System.out
                .println("Combien d'objets voulez-vous ajouter ? (Maximum 10)");
        int n = manager.requestInt(0, 10);
        materials.get(indice).setQuantity(
                materials.get(indice).getQuantity() + n);

        this.listMaterial.setMaterials(materials);
    }

    /**
     * <b>Remove material from the stock</b>
     * <p>
     * This method can be use by the admin in order to show the variation of the
     * stock when he remove some Materials
     * </p>
     */
    private void removeMaterial() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();
        System.out.println(this.displayMaterials(false));

        System.out
                .println("Quel est le materiel que vous souhaitez retirer du stock ?");
        int indice = this.manager.requestInt(1, materials.size()) - 1;
        Material material = materials.get(indice);
        
        if (material.isEmpty()) {
            System.out.println("Aucun materiel à enlever.");
            return;
        }

        StringBuilder str = new StringBuilder();
        str.append("Combien de ");
        str.append(material.getName());
        str.append(" voulez-vous enlever ? (il en reste ");
        str.append(material.getQuantity());
        str.append(')');
        System.out.println(str.toString());

        int n = manager.requestInt(0, material.getQuantity());

        materials.get(indice).setQuantity(
                materials.get(indice).getQuantity() - n);
        this.listMaterial.setMaterials(materials);
    }

    /**
     * <b>Show the material in the stock</b>
     */
    private String displayMaterials(boolean withResa) {
        ArrayList<Material> materials = this.listMaterial.getMaterials();
        StringBuilder str = new StringBuilder();
        
        
        str.append("Le stock contient :\n");
        for (int i = 0; i < materials.size(); ++i) {
            str.append(materials.get(i).toString());
            str.append(" [");
            str.append(materials.get(i).getQuantity());
            str.append("]\n");
        }

        if (withResa) {
            str.append("\nListe des reservations acceptes :\n");
            str.append(this.displayReservationsAccepted());
        }
        return str.toString();
    }

    /**
     * <b>Show the material in reservation</b>
     */
    private String displayReservationsAccepted() {
        ArrayList<Reservation> res = this.listReservations.getReservations();
        StringBuilder str = new StringBuilder();
        
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).isAccepted()) {
                str.append(i);
                str.append(" - ");
                str.append(res.get(i).toString());
            }
        }
        return str.toString();
    }

}
