package Material;

import Config.Config;

/**
 * Class representing the Laptops.
 * 
 * @author Aurélien Colombet
 * 
 */
public class Laptop extends WithOS {
	public Laptop(String os) {
		super(os);
		this.setName("ordinateur");
		this.setDureeEmprunt(Config.DUREE_EMPRUNT_CAMERA);
		this.setQuantity(Config.QUANTITE_INITIAL_LAPTOP);
	}
}
