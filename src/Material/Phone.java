package Material;

import Config.Config;

/**
 * Class representing the Phones.
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Phone extends WithOS {

	public Phone(String os) {
		super(os, "telephone", Config.DUREE_EMPRUNT_PHONE, Config.QUANTITE_INITIAL_PHONE);
	}
}
