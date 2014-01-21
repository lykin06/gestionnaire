package Material;

import Config.Config;

/**
 * Class representing the Cameras.
 * 
 * @author Aurélien Colombet
 * 
 */
public class Camera extends Material {

	public Camera() {
		super("camera");
		this.setDureeEmprunt(Config.DUREE_EMPRUNT_CAMERA);
		this.setQuantity(Config.QUANTITE_INITIAL_CAMERA);
	}
}