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
		super("casque", Config.DUREE_EMPRUNT_HEADPHONE, Config.QUANTITE_INITIAL_HEADPHONE);
	}
}
