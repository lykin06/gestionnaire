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
 * Class ListMaterial
 *TODO commenter cette section
 * @author Aurelien COLOMBET
 * 
 */
public class ListMaterial {
    private ArrayList<Material> materials;
    private int size;

    public ListMaterial() {
        this.load();
        size = materials.size();
    }

    /**
     * this method load the informations in the database and set them in the
     * arraylist.
     */
    public void load() {
        // TODO peut on enlever ce warning
        materials = (ArrayList <Material>) Data.load("material");
    }

    /**
     * @return the list of available materials for Students
     */
    public String forStudents() {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            if (materials.get(i) instanceof Phone || materials.get(i) instanceof Camera) {
                continue; // Skip this index
            }
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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; ++i) {
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
     * @return the materials
     */
    public ArrayList<Material> getMaterials() {
        return materials;
    }

    /**
     * @param materials
     *            the materials to set
     */
    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }

    public void store() {
        // Data.store(materials, "reservations");
        Data.store(materials, "material");
    }
}
