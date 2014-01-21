package Material;

/**
 * Class representing the Objects with OS.
 * 
 * @author Aurélien Colombet
 * 
 */
public class WithOS extends Material {
	private String os;

	public WithOS(String os) {
		super("");
		this.setOs(os);
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	@Override
	public String toString() {
		return this.getName() + "(" + this.getOs() + ")";
	}
}