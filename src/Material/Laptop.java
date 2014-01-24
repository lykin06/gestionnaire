package Material;

import Config.Config;

/**
 * Class representing the Laptops.
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Laptop extends WithOS {
	public Laptop(String os) {
		super(os, "ordinateur", Config.DUREE_EMPRUNT_LAPTOP, Config.QUANTITE_INITIAL_LAPTOP);
	}
}
