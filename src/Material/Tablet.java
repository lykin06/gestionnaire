package Material;

import Config.Config;

/**
 * Class representing the Tablets.
 * 
 * @author Aurélien Colombet
 * 
 */
public class Tablet extends WithOS {

	public Tablet(String os) {
		super(os);
		this.setName("tablette");
		this.setDureeEmprunt(Config.DUREE_EMPRUNT_TABLET);
		this.setQuantity(Config.QUANTITE_INITIAL_TABLET);
	}
}
