package ca.bcit.comp2522.bank;

public class BankAccount {
    private static final int MIN_ACCOUNT_LENGTH = 6;
    private static final int MAX_ACCOUNT_LENGTH = 7;

    private final BankClient client;
    private final int pin;
    private final String accountNumber;
    private final Date accountOpened;
    private final Date accountClosed;

    private double balanceUSD;

    public BankAccount(final BankClient client, final int pin, final String accountNumber,
                       final Date accountOpened, final Date accountClosed) {
        if ( client == null) {
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

    public void deposit(final double amountUSD) {
        if (amountUSD <= 0 || amountUSD > balanceUSD) {
            throw new IllegalArgumentException("must deposit more than 0 USD");
        }
        balanceUSD += amountUSD;
    }
    public void withdraw(final double amountUSD) {
        if (amountUSD <= 0) {
            throw new IllegalArgumentException("withdrawal must be more than 0 USD ");
        }
        if (amountUSD > balanceUSD) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        balanceUSD -= amountUSD;
    }

    public void withdraw(final double amountUSD, final int pinToMatch) {
        if (pin != pinToMatch) {
            throw new IllegalArgumentException("Wrong pin!");
        }
        withdraw(amountUSD);
    }
    public String getDetails() {
        return client.getName().getFullName()
                + "had $" + balanceUSD + "USD in account #"
                + accountNumber
                + "which opened on" + accountOpened + "and closed"
                + accountClosed;
    }



}
