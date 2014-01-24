package Gestion;

import java.util.ArrayList;

import Material.Camera;
import Material.Headphone;
import Material.Laptop;
import Material.Material;
import Material.Phone;
import Material.Tablet;
import Serialization.Data;

/**
 * <b>Class ListMaterial</b>
 * <p>
 * This class contains the stock of materials. It can load it and store it in
 * the file material.data
 * </p>
 * 
 * @author Aurelien COLOMBET
 * 
 * @see Data
 */
public class ListMaterial {

    /**
     * List of materials
     */
    private ArrayList<Material> materials;

    /**
     * <b>Constructor</b>
     * <p>
     * Generate the list of materials from the file.
     * </p>
     */
    public ListMaterial() {
        this.materials = this.load();
    }

    /**
     * <b>Load the information on the materials</b>
     * <p>
     * this method load the informations in the database and set them in the
     * arraylist.
     * <p>
     * 
     * @return a list of materials
     */
    public ArrayList<Material> load() {
        // TODO peut-on enlever ce warning
    	return (ArrayList<Material>) Data.load("material");

    }

    /**
     * <b>Writes a list of materials who are available</b>
     * 
     * <p>
     * This method skip all the materials who are not available for the moment.
     * If the user is a student, it skips all the materials who are forbidden to
     * them.
     * </p>
     * @param students if the user is a student
     * @return the list of available materials for Students
     */
    public String avaibleMaterials(boolean isStudent) {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < materials.size(); ++i) {
            if (this.isNotAvailable(isStudent, materials.get(i))) {
                continue; // Skip this index
            }
            str.append(i + 1);
            str.append(". ");
            str.append(this.materials.get(i).toString());
            str.append(" [");
            str.append(this.materials.get(i).getQuantity());
            str.append("]\n");
        }
        return str.toString();
    }

    /**
     * <b>Watches if the material is still available</b>
     * 
     * @param student
     * @param material
     * @return true if the material is not available
     */
    public boolean isNotAvailable(boolean student, Material material) {
        if (student) {
            // Add here all materials who are not available for students:
            if (material instanceof Phone)
                return true;
            if (material instanceof Camera)
                return true;
        }

        if (material.getQuantity() == 0)
            return true;

        return false;
    }

    /**
     * This method reinitialize the list of material
     */
    public void reinitialize() {
        materials = new ArrayList<Material>();
        materials.add(new Camera());
        materials.add(new Headphone());
        materials.add(new Laptop("GNU/Linux"));
        materials.add(new Laptop("Windows"));
        materials.add(new Laptop("MAC"));
        materials.add(new Phone("iOS"));
        materials.add(new Phone("Android"));
        materials.add(new Phone("Windows Phone"));
        materials.add(new Tablet("iOS"));
        materials.add(new Tablet("Android"));
        materials.add(new Tablet("Windows Phone"));

        Data.store(materials, "material");
    }

    /**
     * <b>Writes a list of materials</b>
     * 
     * @return the list of materials
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < materials.size(); ++i) {
            str.append(i + 1);
            str.append(". ");
            str.append(materials.get(i).toString());
            str.append(" [");
            str.append(materials.get(i).getQuantity());
            str.append("]\n");
        }
        return str.toString();
    }

    /**
     * @return the materials list
     */
    public ArrayList<Material> getMaterials() {
        return materials;
    }

    /**
     * <b>Set the list and it size</b>
     * 
     * @param materials
     *            the materials list to set
     */
    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
        this.store();
    }

    /**
     * <b>Store the materials list in the file</b>
     * 
     * @see Data#store(Object, String)
     */
    public void store() {
        Data.store(materials, "material");
    }

    /**
     * @param material
     * @return the indice in the list of material
     */
    public int getIndice(Material material) {
        for (int i = 0; i < materials.size(); ++i) {
            if (material.getClass().equals(materials.get(i).getClass())) {
                return i;
            }
        }
        return -1;
    }
}
