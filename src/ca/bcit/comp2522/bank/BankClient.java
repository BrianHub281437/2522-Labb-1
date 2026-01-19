package ca.bcit.comp2522.bank;

/**
 * Represents a client of the bank.
 * A BankClient has a name, a birthdate, an optional death date,
 * a unique client ID, and a signup date with the bank.
 *
 * @author Brian Lau, Giant Mak
 */
public class BankClient {

    /** Minimum allowed length of a client ID. */
    private static final int MIN_ID_LENGTH = 6;

    /** Maximum allowed length of a client ID. */
    private static final int MAX_ID_LENGTH = 7;

    private final Name name;
    private final Date birthDate;
    private final Date deathDate;
    private final String clientID;
    private final Date signupDate;

    /**
     * Constructs a BankClient with the specified BankCliental and account details.
     *
     * @param name the client's name
     * @param birthDate the client's date of birth
     * @param deathDate the client's date of death (may be null if alive)
     * @param clientID the client's ID (6–7 characters long)
     * @param signupDate the date the client joined the bank
     *
     * @throws IllegalArgumentException if name, birthDate, or signupDate is null,
     *         or if the clientID length is invalid
     */
    public BankClient(final Name name,
                      final Date birthDate,
                      final Date deathDate,
                      final String clientID,
                      final Date signupDate) {

        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth cannot be null");
        }
        if (clientID == null
                || clientID.length() < MIN_ID_LENGTH
                || clientID.length() > MAX_ID_LENGTH) {
            throw new IllegalArgumentException("clientID must be between 6 and 7 characters");
        }
        if (signupDate == null) {
            throw new IllegalArgumentException("signupDate cannot be null");
        }

        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.clientID = clientID;
        this.signupDate = signupDate;
    }

    /**
     * Returns the name of the client.
     *
     * @return the client's Name
     */
    public Name getName() {
        return name;
    }

    /**
     * Determines whether the client is alive.
     *
     * @return true if the client has no recorded death date; false otherwise
     */
    public boolean isAlive() {
        return deathDate == null;
    }

    /**
     * Returns a formatted String describing the client.
     *
     * @return a String containing the client's name, ID, life status,
     *         and bank signup date
     */
    public String getDetails() {
        String status;

        if (isAlive()) {
            status = "(alive)";
        } else {
            status = "(died " + deathDate.getDayOfWeek() + ", "
                    + deathDate.getMonthName() + " "
                    + deathDate.getDay() + ", "
                    + deathDate.getYear() + ")";
        }

        return name.getFullName()
                + " client #" + clientID + " "
                + status
                + " joined the bank on "
                + signupDate.getDayOfWeek() + ", "
                + signupDate.getMonthName() + " "
                + signupDate.getDay() + ", "
                + signupDate.getYear();
    }
}
