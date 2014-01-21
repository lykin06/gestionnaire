package Material;

import Config.Config;

/**
 * Class representing the Headphones.
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Headphone extends Material {

	public Headphone() {
		super("casque");
		this.setDureeEmprunt(Config.DUREE_EMPRUNT_HEADPHONE);
		this.setQuantity(Config.QUANTITE_INITIAL_HEADPHONE);
	}
}
