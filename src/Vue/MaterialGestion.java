package Vue;

import java.util.ArrayList;
import java.util.Date;

import Gestion.Database;
import Gestion.ListMaterial;
import Gestion.ListReparation;
import Gestion.ListReservations;
import Gestion.Manager;
import Gestion.Reparation;
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
    private ListReparation listReparations;

    public MaterialGestion(Database database, Administrator administrator,
            Manager manager, ListMaterial listMaterial,
            ListReservations listReservations) {
        this.manager = manager;
        this.database = database;
        this.user = administrator;
        this.listMaterial = listMaterial;
        this.listReservations = listReservations;

        this.listReparations = new ListReparation();
        if (this.listReparations.getReparation() == null) {
            this.listReparations.reinitialize();
        }

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
        System.out.println("3. Envoyer du materiel en reparation.");
        System.out.println("4. Recuperer le materiel repare.");
        System.out.println("5. Afficher la liste des materiels");
        System.out
                .println("6. Reinitialiser le stock de materiel (!Attention cela va Reinitialiser les reservations)");
        System.out.println("7. Retour");

        int value = this.manager.requestInt(1, 7);

        switch (value) {
        case 1:
            this.addMaterial();
            break;
        case 2:
            this.removeMaterial();
            break;
        case 3:
            this.addReparation();
            break;
        case 4:
            this.recoverReparation();
            break;
        case 5:
            System.out.println(this.displayMaterials(true));
            break;
        case 6:
            this.listMaterial.reinitialize();
            this.listReservations.reinitialize();
            if (!this.listReparations.reinitialize()) {
                System.err.println("Store in the file failed.");
            }
            break;
        case 7:
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
            System.out.println("Aucun materiel a enlever.");
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
        StringBuilder str = new StringBuilder();

        str.append("Le stock contient :\n");
        str.append(listMaterial.toString());

        if (withResa) {
            str.append("\nListe des reservations :\n");
            str.append(this.listReservations.toString());
            str.append("\nListe des reparations en cours : \n");
            str.append(this.listReparations.toString());
        }
        return str.toString();
    }

    /**
     * <b>Send some materials to Reparation</b>
     */
    private void addReparation() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();
        System.out.println(this.displayMaterials(false));
        System.out.println("Quel materiel faut-il envoyer en reparation ? (0 pour quitter)");

        int indice = this.manager.requestInt(0, materials.size()) - 1;
        if(!(indice==-1)){
	        Material material = materials.get(indice);
	
	        if (material.isEmpty()) {
	            System.out.println("Aucun materiel a enlever.");
	            return;
	        }
	
	        int max = material.getQuantity();
	        System.out
	                .println("Combien de materiel voulez-vous envoyer en reparation ? (maximum "
	                        + max + ") (-1 pour quitter)");
	        int quantity = manager.requestInt(-1, max);
	        
	        if(!(quantity==-1)){
		        listReparations.add(new Reparation(material, quantity));
		        listMaterial.getMaterials().get(indice).setQuantity(max - quantity);
		        listMaterial.store();
	        }
        }
    }

    /**
     * <b>Recover the finished Reparations</b>
     */
    private void recoverReparation() {
        ArrayList<Reparation> reparations = this.listReparations
                .getReparation();
        ArrayList<Material> materials = this.listMaterial.getMaterials();

        Date today = new Date();

        for (int i = 0; i < reparations.size(); ++i) {
            Reparation reparation = reparations.get(i);

            if (reparation.getFinish().before(today)) {
                int indice = this.listMaterial.getIndice(reparation
                        .getMaterial());
                if (indice < 0) {
                    System.err.println("This material is not in listMaterial");
                    return;
                }
                int quantity = materials.get(indice).getQuantity()
                        + reparation.getNumber();
                materials.get(indice).setQuantity(quantity);
                
                this.listReparations.remove(reparation);
            }
        }
        
        this.listMaterial.setMaterials(materials);
    }

}
