package ca.bcit.comp2522.bank;

public class Main {
    public static void main(String[] args) {

        /* Albert Einstein */
        Name einsteinName = new Name("Albert", "Einstein");
        Date einsteinBirth = new Date(1879, 3, 14);
        Date einsteinDeath = new Date(1955, 4, 18);

        System.out.println(einsteinName.getInitials());
        System.out.println(einsteinName.getFullName());
        System.out.println(einsteinName.getReverseName());

        BankClient einsteinClient = new BankClient(
                einsteinName,
                einsteinBirth,
                einsteinDeath,
                "abc123",
                new Date(1900, 1, 1)
        );

        System.out.println(einsteinClient.getDetails());

        BankAccount einsteinAccount = new BankAccount(
                einsteinClient,
                3141,
                "256123",
                new Date(1900, 1, 1),
                new Date(1950, 10, 14)
        );

        einsteinAccount.deposit(1000);
        einsteinAccount.withdraw(100);

        System.out.println(einsteinAccount.getDetails());

        System.out.println();

        /* Nelson Mandela */
        Name mandelaName = new Name("Nelson", "Mandela");
        Date mandelaBirth = new Date(1918, 7, 18);
        Date mandelaDeath = new Date(2013, 12, 5);

        System.out.println(mandelaName.getInitials());
        System.out.println(mandelaName.getFullName());
        System.out.println(mandelaName.getReverseName());

        BankClient mandelaClient = new BankClient(
                mandelaName,
                mandelaBirth,
                mandelaDeath,
                "654321",
                new Date(1994, 5, 10)
        );

        System.out.println(mandelaClient.getDetails());

        BankAccount mandelaAccount = new BankAccount(
                mandelaClient,
                4664,
                "654321",
                new Date(1994, 5, 10),
                null
        );

        mandelaAccount.deposit(2000);
        mandelaAccount.withdraw(200);

        System.out.println(mandelaAccount.getDetails());

        System.out.println();


        /* Frida Kahlo */
        Name kahloName = new Name("Frida", "Kahlo");
        Date kahloBirth = new Date(1907, 7, 6);
        Date kahloDeath = new Date(1954, 7, 13);

        System.out.println(kahloName.getInitials());
        System.out.println(kahloName.getFullName());
        System.out.println(kahloName.getReverseName());

        BankClient kahloClient = new BankClient(
                kahloName,
                kahloBirth,
                kahloDeath,
                "frd123",
                new Date(1940, 1, 1)
        );

        System.out.println(kahloClient.getDetails());

        BankAccount kahloAccount = new BankAccount(
                kahloClient,
                1907,
                "672123",
                new Date(1940, 1, 1),
                new Date(1954, 7, 13)
        );

        kahloAccount.deposit(500);
        kahloAccount.withdraw(50);

        System.out.println(kahloAccount.getDetails());

        System.out.println();


        /* Jackie Chan */
        Name chanName = new Name("Jackie", "Chan");
        Date chanBirth = new Date(1954, 4, 7);

        System.out.println(chanName.getInitials());
        System.out.println(chanName.getFullName());
        System.out.println(chanName.getReverseName());

        BankClient chanClient = new BankClient(
                chanName,
                chanBirth,
                null,
                "chan789",
                new Date(1980, 10, 1)
        );

        System.out.println(chanClient.getDetails());

        BankAccount chanAccount = new BankAccount(
                chanClient,
                1954,
                "496789",
                new Date(1980, 10, 1),
                null
        );

        chanAccount.deposit(3000);
        chanAccount.withdraw(500);

        System.out.println(chanAccount.getDetails());
    }
}
