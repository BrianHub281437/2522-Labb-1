package ca.bcit.comp2522.bank;

public class BankClient {
    private static final int MIN_ID_LENGTH = 6;
    private static final int MAX_ID_LENGTH = 7;

    private final Name name;
    private final Date born;
    private final Date died;
    private final String clientID;
    private final Date signupDate;

    public BankClient(final Name name, final Date born, final Date died, final String clientID, final Date signupDate ) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (born == null) {
            throw new IllegalArgumentException("Birth cannot be null");
        }
        if (clientID == null || clientID.length() < MIN_ID_LENGTH || clientID.length() > MAX_ID_LENGTH ) {
            throw new IllegalArgumentException("clientID must be between 6 and 7 characters");
        }
        if (signupDate == null) {
            throw new IllegalArgumentException("signupDate cannot be null");
        }
        this.name = name;
        this.born = born;
        this.died = died;
        this.clientID = clientID;
        this.signupDate = signupDate;
    }
    public Name getName() {
        return name;
    }
    public boolean isAlive() {
        return died == null;
    }
    public String getDetails() {
        String status;
        if(isAlive()) {
            status = "(alive)";
        } else {
            status = "(died " + died.getDayOfWeek() + ", "
                    + died.getMonthName() + " "
                    + died.getDay() + ", "
                    + died.getYear() + ")";
        }
        return name.getFullName()
                + " client #" + clientID + " "
                + status
                + " joined the bank on "
                + signupDate.getDayOfWeek() + ", "
                + signupDate.getMonth() + " "
                + signupDate.getDay() + ", "
                + signupDate.getYear();

    }

}
