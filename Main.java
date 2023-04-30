import java.util.Scanner;

import static java.lang.System.*;

class BankAccount {

    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 100000f;
    int transactions = 0;
    String transactionHistory = "";


    public void register() {
        Scanner sc = new Scanner(in);
        out.print("\nEnter Your Name - ");
        this.name = sc.nextLine();
        out.print("\nEnter Your Username - ");
        this.userName = sc.nextLine();
        out.print("\nEnter Your Password - ");
        this.password = sc.nextLine();
        out.print("\nEnter Your Account Number - ");
        this.accountNo = sc.nextLine();
        out.println("\nRegistration completed..kindly login");
    }

    public boolean login() {
        boolean isLogin = false;
        Scanner sc = new Scanner(in);
        while ( !isLogin ) {
            out.print("\nEnter Your Username - ");
            String Username = sc.nextLine();
            if ( Username.equals(userName) ) {
                while ( !isLogin ) {
                    out.print("\nEnter Your Password - ");
                    String Password = sc.nextLine();
                    if ( Password.equals(password) ) {
                        out.print("\nLogin successful!!");
                        isLogin = true;
                    }
                    else {
                        out.println("\nIncorrect Password");
                    }
                }
            }
            else {
                out.println("\nUsername not found");
            }
        }
        return true;
    }

    public void withdraw() {

        out.print("\nEnter amount to withdraw - ");
        Scanner sc = new Scanner(in);
        float amount = sc.nextFloat();
        try {

            if ( balance >= amount ) {
                transactions++;
                balance -= amount;
                out.println("\nWithdraw Successfully");
                String str = amount + " Rs Withdrawed\n";
                transactionHistory = transactionHistory.concat(str);

            }
            else {
                out.println("\nInsufficient Balance");
            }

        }
        catch ( Exception e) {
            out.println("error generated");
        }
    }

    public void deposit() {

        out.print("\nEnter amount to deposit - ");
        Scanner sc = new Scanner(in);
        float amount = sc.nextFloat();

        try {
            if ( amount <= 100000f ) {
                transactions++;
                balance += amount;
                out.println("\nSuccessfully Deposited");
                String str = amount + " Rs deposited\n";
                transactionHistory = transactionHistory.concat(str);
            }
            else {
                out.println("\nSorry...Limit is 100000.00");
            }

        }
        catch ( Exception e) {
            out.println("error generated");
        }
    }

    public void transfer() {

        Scanner sc = new Scanner(in);
        out.print("\nEnter Receipent's Name - ");
        String receipent = sc.nextLine();
        out.print("\nEnter amount to transfer - ");
        float amount = sc.nextFloat();

        try {
            if ( balance >= amount ) {
                if ( amount <= 50000f ) {
                    transactions++;
                    balance -= amount;
                    out.println("\nSuccessfully Transfered to " + receipent);
                    String str = amount + " Rs transfered to " + receipent + "\n";
                    transactionHistory = transactionHistory.concat(str);
                }
                else {
                    out.println("\nSorry...Limit is 50000.00");
                }
            }
            else {
                out.println("\nInsufficient Balance");
            }
        }
        catch ( Exception e) {
            out.println("error generated");
        }
    }

    public void checkBalance() {
        out.println("\n" + balance + " Rs");
    }

    public void transHistory() {
        if ( transactions == 0 ) {
            out.println("\nEmpty");
        }
        else {
            out.println("\n" + transactionHistory);
        }
    }

}

class atmmachine {


    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;

        while ( !flag ) {
            try {
                Scanner sc = new Scanner(in);
                input = sc.nextInt();
                flag = true;

                if (input > limit || input < 1) {
                    out.println("Choose the number between 1 to " + limit);
                    flag = false;
                }
            }
            catch ( Exception e ) {
                out.println("Enter only integer value");
                flag = false;
            }
        }
        return input;
    }


    public static void main(String[] args) {

        out.println("\n*********WELCOME TO SBI ATM SYSTEM*********\n");
        out.println("1.Register \n2.Exit");
        out.print("Enter Your Choice - ");
        int choice = takeIntegerInput(2);

        if ( choice == 1 ) {
            BankAccount b = new BankAccount();
            b.register();
            while(true) {
                out.println("\n1.Login \n2.Exit");
                out.print("Enter Your Choice - ");
                int ch = takeIntegerInput(2);
                if ( ch == 1 ) {
                    if (b.login()) {
                        out.println("\n\n**********WELCOME BACK " + b.name + " **********\n");
                        boolean isFinished = false;
                        while (!isFinished) {
                            out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                            out.print("\nEnter Your Choice - ");
                            int c = takeIntegerInput(6);
                            switch (c) {
                                case 1 -> b.withdraw();
                                case 2 -> b.deposit();
                                case 3 -> b.transfer();
                                case 4 -> b.checkBalance();
                                case 5 -> b.transHistory();
                                case 6 -> isFinished = true;
                            }
                        }
                    }
                }
                else {
                    exit(0);
                }
            }
        }
        else {
            exit(0);
        }
    }
}