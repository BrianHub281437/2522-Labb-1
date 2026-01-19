package ca.bcit.comp2522.bank;

/**
 * Represents a bank account owned by a BankClient.
 * A bank account has an account number, a PIN, a balance in USD,
 * an opening date, and an optional closing date.
 *
 * @author Brian Lau
 * @author Giant Mak
 * @version 1.0
 */
public class BankAccount {

    /** Minimum allowed length of an account number. */
    private static final int MIN_ACCOUNT_LENGTH = 6;

    /** Maximum allowed length of an account number. */
    private static final int MAX_ACCOUNT_LENGTH = 7;

    private final BankClient client;
    private final int pin;
    private final String accountNumber;
    private final Date accountOpened;
    private final Date accountClosed;

    private double balanceUSD;

    /**
     * Constructs a BankAccount with the client details.
     *
     * @param client the owner of the bank account
     * @param pin the PIN used to authenticate withdrawals
     * @param accountNumber the account number (6–7 characters long)
     * @param accountOpened the date the account was opened
     * @param accountClosed the date the account was closed (may be null)
     *
     * @throws IllegalArgumentException if the client is null or
     *         the account number length is invalid
     */
    public BankAccount(final BankClient client,
                       final int pin,
                       final String accountNumber,
                       final Date accountOpened,
                       final Date accountClosed) {

        if (client == null) {
            throw new IllegalArgumentException("client cannot be null");
        }
        if (accountNumber == null
                || accountNumber.length() < MIN_ACCOUNT_LENGTH
                || accountNumber.length() > MAX_ACCOUNT_LENGTH) {
            throw new IllegalArgumentException("Invalid account number");
        }

        this.client = client;
        this.pin = pin;
        this.accountNumber = accountNumber;
        this.accountOpened = accountOpened;
        this.accountClosed = accountClosed;
    }

    /**
     * Deposits a positive amount of USD into the account.
     *
     * @param amountUSD the amount of money to deposit
     * @throws IllegalArgumentException if the amount is
     *         less than or equal to zero
     */
    public void deposit(final double amountUSD) {
        if (amountUSD <= 0) {
            throw new IllegalArgumentException("must deposit more than 0 USD");
        }
        balanceUSD += amountUSD;
    }

    /**
     * Withdraws a specified amount of USD from the account.
     *
     * @param amountUSD the amount of money to withdraw
     * @throws IllegalArgumentException if the amount is
     *         less than or equal to zero or exceeds the account balance
     */
    public void withdraw(final double amountUSD) {
        if (amountUSD <= 0) {
            throw new IllegalArgumentException("withdrawal must be more than 0 USD");
        }
        if (amountUSD > balanceUSD) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        balanceUSD -= amountUSD;
    }

    /**
     * Withdraws a specified amount of USD from the account
     * after verifying the provided PIN.
     *
     * @param amountUSD the amount of money to withdraw
     * @param pinToMatch the PIN used for verification
     * @throws IllegalArgumentException if the PIN is incorrect
     *         or the withdrawal amount is invalid
     */
    public void withdraw(final double amountUSD, final int pinToMatch) {
        if (pin != pinToMatch) {
            throw new IllegalArgumentException("Wrong pin!");
        }
        withdraw(amountUSD);
    }

    /**
     * Returns a formatted String containing details of the bank account.
     *
     * @return a String describing the account owner, balance,
     *         account number, and open/close dates
     */
    public String getDetails() {
        String closeStatus;

        if(accountClosed == null) {
            closeStatus = "";
        } else {
            closeStatus = " and closed on " + accountClosed;
        }

        return client.getName().getFullName()
                + " had $" + balanceUSD + " USD in account #"
                + accountNumber
                + " which he opened on " + accountOpened
                + closeStatus + ".";
    }
}
