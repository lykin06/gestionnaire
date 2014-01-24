package Material;

import Config.Config;

/**
 * Class representing the Cameras.
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Camera extends Material {

    public Camera() {
		super("camera", Config.DUREE_EMPRUNT_CAMERA, Config.QUANTITE_INITIAL_CAMERA);
	}
}
