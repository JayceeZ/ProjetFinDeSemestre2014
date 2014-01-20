package projet.model.user.borrower;

/**
 * Define a student user
 * 
 * @author Jean-Christophe Isoard
 * 
 */
public class Student extends Borrower {

	public final static int timeLimitReserved = 7;
	public final static int timeLimit = 7;

	/**
	 * Construct a student with names parameters
	 * 
	 * @param firstname
	 * @param lastname
	 */
	public Student(String firstname, String lastname) {
		super(firstname, lastname);
	}

}
