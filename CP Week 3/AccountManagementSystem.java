import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Account {

    private int accountId;
    private String name;
    private double balance;

    public Account(int accountId, String name, double balance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance = balance - amount;
            return true;
        }

        return false;
    }
}

class AccountDAO {

    private LinkedHashMap<Integer, Account> accounts;

    public AccountDAO() {
        accounts = new LinkedHashMap<>();
    }

    public boolean accountExists(int accountId) {
        return accounts.containsKey(accountId);
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public Account getAccount(int accountId) {
        return accounts.get(accountId);
    }

    public boolean isEmpty() {
        return accounts.isEmpty();
    }

    public void viewAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No Accounts Found.");
            return;
        }

        System.out.println("\n===== ALL ACCOUNTS =====");

        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {

            Account account = entry.getValue();

            System.out.println("Account ID : " + account.getAccountId());
            System.out.println("Name       : " + account.getName());
            System.out.println("Balance    : " + account.getBalance());
            System.out.println("----------------------------");
        }
    }
}

public class AccountManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AccountDAO accountDAO = new AccountDAO();

        int choice;

        do {

            System.out.println("\n===== ACCOUNT MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Check");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Account ID: ");
                    int accountId = sc.nextInt();
                    sc.nextLine();

                    if (accountDAO.accountExists(accountId)) {
                        System.out.println("Account ID already exists.");
                        break;
                    }

                    System.out.print("Enter Account Holder Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();

                    if (balance < 0) {
                        System.out.println("Balance cannot be negative.");
                        break;
                    }

                    Account newAccount =
                            new Account(accountId, name, balance);

                    accountDAO.addAccount(newAccount);

                    System.out.println("Account Created Successfully.");

                    break;

                case 2:

                    if (accountDAO.isEmpty()) {
                        System.out.println("No Accounts Found.");
                        break;
                    }

                    System.out.print("Enter Account ID: ");
                    int depositId = sc.nextInt();

                    Account depositAccount =
                            accountDAO.getAccount(depositId);

                    if (depositAccount == null) {
                        System.out.println("Account Not Found.");
                        break;
                    }

                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = sc.nextDouble();

                    if (depositAmount <= 0) {
                        System.out.println(
                                "Deposit amount must be greater than zero."
                        );
                        break;
                    }

                    depositAccount.deposit(depositAmount);

                    System.out.println("Amount Deposited Successfully.");
                    System.out.println(
                            "Current Balance: "
                                    + depositAccount.getBalance()
                    );

                    break;

                case 3:

                    if (accountDAO.isEmpty()) {
                        System.out.println("No Accounts Found.");
                        break;
                    }

                    System.out.print("Enter Account ID: ");
                    int withdrawId = sc.nextInt();

                    Account withdrawAccount =
                            accountDAO.getAccount(withdrawId);

                    if (withdrawAccount == null) {
                        System.out.println("Account Not Found.");
                        break;
                    }

                    System.out.print("Enter Withdrawal Amount: ");
                    double withdrawAmount = sc.nextDouble();

                    if (withdrawAmount <= 0) {
                        System.out.println(
                                "Withdrawal amount must be greater than zero."
                        );
                        break;
                    }

                    if (withdrawAccount.withdraw(withdrawAmount)) {

                        System.out.println(
                                "Amount Withdrawn Successfully."
                        );

                        System.out.println(
                                "Current Balance: "
                                        + withdrawAccount.getBalance()
                        );

                    } else {

                        System.out.println("Insufficient Balance.");
                    }

                    break;

                case 4:

                    if (accountDAO.isEmpty()) {
                        System.out.println("No Accounts Found.");
                        break;
                    }

                    System.out.print("Enter Account ID: ");
                    int balanceId = sc.nextInt();

                    Account balanceAccount =
                            accountDAO.getAccount(balanceId);

                    if (balanceAccount == null) {
                        System.out.println("Account Not Found.");
                        break;
                    }

                    System.out.println("\n===== ACCOUNT DETAILS =====");

                    System.out.println(
                            "Account ID : "
                                    + balanceAccount.getAccountId()
                    );

                    System.out.println(
                            "Name       : "
                                    + balanceAccount.getName()
                    );

                    System.out.println(
                            "Balance    : "
                                    + balanceAccount.getBalance()
                    );

                    break;

                case 5:

                    accountDAO.viewAllAccounts();

                    break;

                case 6:

                    System.out.println("Thank You!");

                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}