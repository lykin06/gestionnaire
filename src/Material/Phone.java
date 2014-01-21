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
		super(os);
		this.setName("telephone");
		this.setDureeEmprunt(Config.DUREE_EMPRUNT_PHONE);
		this.setQuantity(Config.QUANTITE_INITIAL_PHONE);
	}
}
