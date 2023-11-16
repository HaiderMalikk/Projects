// DRIVER FILE
public class BankingApp {
    public static void main(String[] args) {
        // i have created 2 accounts and some example trasactions and errors for you to see 
        // Guide to trasfer, fromat account.typeoftransaction(ammount, senders number); "sender number is to confirm sender"

        // creating the savings account object 
        BankAccount savingsAccount = new BankAccount(12345678, "My Savings"); // savings account 

        // transactions 
        savingsAccount.deposit(1000, 12345678);
        savingsAccount.withdraw(500, 12345678);
        savingsAccount.withdraw(100.00, 12345678);

        // errors 
        savingsAccount.withdraw(100.00, 1234578); // acc number wrong
        savingsAccount.deposit(8000, 123); //account number wrong 
        savingsAccount.withdraw(1000000, 12345678); // Insufficient funds


        // creating the checking account object 
        BankAccount checkingAccount = new BankAccount(98765432, "My Checkings");
        checkingAccount.deposit(2000.00, 98765432);
        checkingAccount.deposit(25.00, 98765432);
        checkingAccount.withdraw(500, 98765432);

        // errors 
        checkingAccount.withdraw(100, 9876); // account number wrong
        checkingAccount.deposit(200000, 1234); // account number wrong
        checkingAccount.withdraw(100000, 98765432); // Insufficient funds

        
        // trasfers
        checkingAccount.transferTo(savingsAccount, 225, 98765432); 
        savingsAccount.transferTo(checkingAccount, 150, 12345678);



        //trasfer errors (both accounts)
        checkingAccount.transferTo(savingsAccount, 200000, 98765432); // Insufficient funds
        savingsAccount.transferTo(checkingAccount, 100000, 12345678); // Insufficient funds

        savingsAccount.transferTo(checkingAccount, 500000, 123458232); // account number wrong
        checkingAccount.transferTo(savingsAccount, 250000, 987652242); // account number wrong


        // generates bank statements 
        savingsAccount.generateBankStatement();
        checkingAccount.generateBankStatement();

    }
} // end of driver class