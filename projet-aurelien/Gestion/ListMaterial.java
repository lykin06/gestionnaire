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
 * 
 * @author Amir BEN SLIMANE
 * 
 */
public class ListMaterial {
	private ArrayList<Material> materials;

	public ListMaterial() {
		this.load();
	}

	/**
	 * this method load the informations in the database and set them in the
	 * arraylist.
	 */
	public void load() {
		this.setMaterials((ArrayList<Material>) Data.load("material"));
	}

	public ArrayList<Material> forStudients() {
		ArrayList<Material> res = new ArrayList<>();
		for (int i = 0; i < this.getMaterials().size(); i++) {
			if (!(this.getMaterials().get(i) instanceof Camera || this
					.getMaterials().get(i) instanceof Phone)) {
				res.add(this.getMaterials().get(i));
			}
		}
		return res;
	}

	/**
	 * This method reinitialize the list of material
	 */
	public void reinitialize() {
		this.setMaterials(new ArrayList<Material>());
		this.getMaterials().add(new Camera());
		this.getMaterials().add(new Headphone());
		this.getMaterials().add(new Laptop("GNU/Linux"));
		this.getMaterials().add(new Laptop("Windows"));
		this.getMaterials().add(new Laptop("MAC"));
		this.getMaterials().add(new Phone("iOS"));
		this.getMaterials().add(new Phone("Android"));
		this.getMaterials().add(new Phone("Windows Phone"));
		this.getMaterials().add(new Tablet("iOS"));
		this.getMaterials().add(new Tablet("Android"));
		this.getMaterials().add(new Tablet("Windows Phone"));

		Data.store(this.getMaterials(), "material");
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < this.getMaterials().size(); i++) {
			str += (i + 1) + ". " + this.getMaterials().get(i).toString()
					+ " [" + this.getMaterials().get(i).getQuantity() + "]\n";
		}
		return str;
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
		Data.store(this.getMaterials(), "reservations");
	}
}
