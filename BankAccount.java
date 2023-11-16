// imports 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// trasaction class this has all the info for a trasaction but no account 
class Transaction {
    // creating private vars to imitate a bank
    // these 4 vars our for our trasaction
    private String type; //types of transaction
    private double amount; //amount of money
    private int senderAccountnumber; //senders account number this is needed as a int to check its value 
    private String receivingAccountnumber; // receving accounts number this is string as it is not needed as a int 

    // this is our constructer for our trasaction object 
    public Transaction(String type, double amount, int senderAccountnumber2, String receivingAccountnumber) {
        // initilizing my vars 
        this.type = type;
        // sender1 = used to see what account that initiated trasfer 
        // sender2 = used to confirm senders number in BankingApp (ammount, sender2) <- transnaction example 
        this.senderAccountnumber = senderAccountnumber2; 
        this.receivingAccountnumber = receivingAccountnumber;

        if (amount >= 0) {
            this.amount = amount;
            }
        else { throw new IllegalArgumentException("Ammout must be a positive value");}
    }

    // these 4 methods do exactly what there name is
    // all they do is return there corrisponding value to be used in the program, Ex gettype returns the type of trasaction
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public int getsenderAccountnumber() {
        return senderAccountnumber;
    }

    public String getreceivingAccountnumber() {
        return receivingAccountnumber;
    }
    
} //end of Transaction

// here we start to create our bank account 
class BankAccount {
    // these 4 private vars are for our bank account and banker, they have nothing to do with trasnactions
    private int accountNumber; // a uniqe 8 digit number chosen by user when creating account object
    private String name; // name of users account or in this case 1 user has 2 types of accounts there name was there type 
    private double balance; // balance of user 
    private List<Transaction> transactions; // list of trasaction type Ex: deposit, widthraw, trasfer ETC 
    private List<String> failedTransactions; // list of falied trasaction Ex: trying to widthraw more money that you balance 
    
    // this method returns account number a use of this can be to retuen the account number of a account that trasfered you money
    public int getAccountNumber() {
        return accountNumber;
    }

    // method checks the account numbers format NOT IF IT EXISTS that is for the user to make sure 
    public boolean checksenderAccountNumber(int senderAccountnumber){
        if (senderAccountnumber >= 10000000 && senderAccountnumber <= 99999999) {
            return true;
        }
        return false;
    }

    // bank account constructer, this creates the bank account object 
    public BankAccount(int accountNumber, String name) {
        // initializing my vars
        this.name = name;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        this.failedTransactions = new ArrayList<>();

        // this is the account number checker
        // the sender number which if wrong just creates a error 
        // the account number if not 8 digits when creating the acccount will throw a error at you
        if (accountNumber >= 10000000 && accountNumber <= 99999999) {
            this.accountNumber = accountNumber; 
            }
        // a print statement will have just printed the error but the code would have still ran we dont want that 
        // IllegalArgumentException will not even let the code run insted will give you a error along with a custom message 
        else { throw new IllegalArgumentException("Account number must be 8 digits.");}
    }

    // deposit method has two parameters amount and senders account number
    public void deposit(double amount, int senderAccountnumber) {
        // if statement checks if the format of the senders account number is correct
        if (checksenderAccountNumber(senderAccountnumber)){
        balance += amount;
        // adding a new trasaction object to the transactions list we created earlier 
        // the trasaction object has 4 parameters: the type, ammount, sender and receving account numbers 
        transactions.add(new Transaction("Deposit", amount, senderAccountnumber, null));}
        // if the senders account number format is wrong 
        else {
            // adds a falied transaction to the faliedtransaction list, includes a custom message 
            // unlike transactions faliedtransactions is not a object its just a custom message added to the faliedtransaction list
            // faliedtrasaction is not a object as it dose not effect anything
            // if a deposit fails nothing is effected and so a message is suffecient 
            failedTransactions.add("Deposit: $" + amount + " - Your Account Number Was Entered Incorrectly");
        }
    }

    // a custom method created only to add the ammount from a depost send by another account
    // its also a trasaction so it creates a new trasaction object and adds it to the transactions list
    public void directdeposit(double amount, int senderAccountnumber) {
        balance += amount;
        transactions.add(new Transaction("Direct Deposit", amount, senderAccountnumber, null));
    }

    // withdwar method
    public void withdraw(double amount, int senderAccountnumber) {
        if (checksenderAccountNumber(senderAccountnumber)) {
        // unlike deposit a withraw has to be checked with your balance making sure you have enough to withraw you ammount
        if (amount <= balance) {
            balance -= amount;
            // also a trasaction so its added to the list
            transactions.add(new Transaction("Withdraw", amount, senderAccountnumber, null));
        } else {
            // if you do not have enough money the faliedtrasaction is added 
            failedTransactions.add("Withdraw: $" + amount + " - Insufficient funds");

        }
    // if your account number format is incorrect a falied trasaction is added with a message correcponding to the error 
    } else {failedTransactions.add("Withdraw: $" + amount + " - Your Account Number Was Entered Incorrectly");}
    }

    // a method made only of transfers, before you can trasfer you must withdraw 
    // this eliminates any errors for trasfers as the bank will first withraw for a trasfer before the trasfer to check if you have enough
    public void withdrawfortransfer(double amount, int senderAccountnumber) {
        // money is checked with the trasfer ammount 
        if (amount <= balance) {
            balance -= amount;
            // this is also a trasaction so its added to our transactions list
            transactions.add(new Transaction("Withdraw For Transfer", amount, senderAccountnumber, null));
        } else {
            // if you dont have enough money you get a falied trasaction, this is added to our falied trasaction list 
            failedTransactions.add("Withdraw For Transfer: $" + amount + " - Insufficient funds");
        }
    }

    // trasfer method this has a additional parameter called destination this is the destination account 
    public void transferTo(BankAccount destination, double amount, int senderAccountnumber) {
        // checking senders account number as a authentication 
        if (checksenderAccountNumber(senderAccountnumber)) {
            // checking funds, i did not have to do this as i created the withrawfortrasfer method for checking if you have enough money too trasfer 
            // this dose not put the withrawfortrasfer method to waste but acts like a second layer of security  
        if (amount <= balance) {
            withdrawfortransfer(amount, senderAccountnumber); // the withraw for trasfer 
            destination.directdeposit(amount, senderAccountnumber); // this is what the recever sees, the ammount and sender 
            // this is also a trasaction so its added to the trasactions list a new object along with its type and information
            transactions.add(new Transaction("Transfer", amount, senderAccountnumber, String.valueOf(destination.getAccountNumber())));
        } else {
            // if you dont have enough money 
            failedTransactions.add("Transfer: $" + amount + " (To Account #" + destination.accountNumber + ")" +" - Insufficient funds");
        }
        // if the account number format is incorrect the sender receves a error (failedtrasaction)
    } else {failedTransactions.add("Transfer: $" + amount + " (To Account #" + destination.accountNumber + ")" + " - Your Account Number Was Entered Incorrectly");}
    }

    // this medthod generates a bank statement using filewriter
    public void generateBankStatement() {
        String filename = accountNumber + "_statement.txt"; // file with this name will be created
        // the statement is wraped in a try catch block to ensure any errors are chought and displaed 
        try (FileWriter writer = new FileWriter(filename)) {
            // the statment strarts with number username and balance
            writer.write("Account Number: " + accountNumber + "\n");
            writer.write("Username: " + name + "\n");
            writer.write("Balance: $" + balance + "\n");
            writer.write("\n");
            // then we move to trasaction history
            writer.write("Transaction History:\n");
            // for each trasaction is the trasactions list 
            for (Transaction transaction : transactions) {
                String transactionInfo; // creating a empty string that will later be set to equal out trasactioninfo to be displayed
                // if trasaction type is trasfer display the message made for trasfer along with the trasfers info
                if (transaction.getType().equals("Transfer")) {
                    // now we set trasactionInfo equal to our transactioninfo we can include any type of info we have here to the user 
                    transactionInfo = transaction.getType() + ": $" + transaction.getAmount() + " (Sent to Account #" + transaction.getreceivingAccountnumber() + ")";
                    // if the trasaction is a deposit from a trasfer (trasfer from another account) display the info corresponding to this trasaction
                } else if (transaction.getType().equals("Direct Deposit")) {
                    transactionInfo = transaction.getType() + ": $" + transaction.getAmount() + " (From Account #" + transaction.getsenderAccountnumber() + ")";
                    // same as before conditional checks the type of trasaction and displays the info corresponding too it 
                } else if (transaction.getType().equals("Withdraw For Transfer")) {
                    transactionInfo = transaction.getType() + ": $" + transaction.getAmount();
                    // any other type of trasaction type
                } else {
                    transactionInfo = transaction.getType() + ": $" + transaction.getAmount();
                }
                writer.write(transactionInfo + "\n"); 
            }
            // falied trasaction list 
            writer.write("\n");
            writer.write("Failed Transactions:\n");
            // for each faliedtrasaction in the failedtrasactions list simply print out the faliedtrasaction 
            // we alredy gave the falied transaction its trasactioninfo in the medthod for its trasaction so we simply just print it
            for (String failedTransaction : failedTransactions) {
                writer.write(failedTransaction + "\n"); // prints the faliedtransactions
            }
            System.out.println("Bank statement generated successfully."); // if the "try" work print successful satement 
            // catch picks up any error while writing to file 
        } catch (IOException e) {
            // if there is a error computer will print a messag along with the error 
            System.out.println("Failed to generate bank statement: " + e.getMessage());
        }
    }


} // end of BankAccount

