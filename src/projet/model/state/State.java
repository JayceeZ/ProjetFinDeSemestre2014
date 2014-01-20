package projet.model.state;

public enum State {
	// OS de base, dont on a besoin lors de l'execution.
	Accept("Accept", "Accepted"), Wait("Wait", "Waiting for validation");

	private String name;
	private String commentary;

	/**
	 * Construct the state
	 * 
	 * @param name
	 *            of the state
	 * @param commentary
	 *            for user understanding
	 */
	State(String name, String commentary) {
		this.name = name;
		this.commentary = commentary;
	}

	/**
	 * Getter for name
	 * 
	 * @return name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for commentary
	 * 
	 * @return commentary
	 * 
	 */
	public String getCommentary() {
		return commentary;
	}

	/**
	 * Setter for name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for commentary
	 * 
	 * @param commentary
	 */
	public void setcommentary(String commentary) {
		this.commentary = commentary;
	}

	@Override
	public String toString() {
		return "State : " + this.name + "\ncommentary : " + this.commentary
				+ "\n";
	}

}
