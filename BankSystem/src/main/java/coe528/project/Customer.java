package coe528.project;
/**
 *
 * @author Sayeed
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Customer extends User
{

    private String localUser;
    private Level level;
    private double balance;

    public Customer(String username, String password, String role, double balance)
    {
        super(username, password, role);
        this.localUser = username;
        this.balance = balance;
        this.level = Level.SILVER; //defaulting level as silver, since could start at $0 which is under $10k
    }

    @Override
    public boolean authenticate()
    {
        File file = new File("CustomerAccounts.txt"); //Retrieving customer data from the file

        if (!file.exists()) //checking if the file exists
        {
            System.out.println("Customer database file does not exist."); //if it doesnt..
            return false;
        }
        else
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                String line;
                while ((line = reader.readLine()) != null) //reading each line in file
                {
                    String[] userData = line.split(":"); //splitting data w :
                    if (userData.length == 4 && userData[0].equals(localUser))
                    {
                        String storedPassword = userData[1];
                        if (getPassword().equals(storedPassword)) //checking if stored password matches the provided password
                        {
                            this.balance = Double.parseDouble(userData[3]);
                            changeLevel(); //update level based on balance
                            return true; //authentication successful
                        }
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println("Error retrieving customer information: " + e.getMessage()); //catching if unable to etrieve data due to invalidity of sort
            }
        }

        return false; //this for if the authentication failed
    }

    public double checkBalance() //checking balance
    {
        return balance;
    }

    public boolean deposit(double amount)
    {
        if (amount < 0) //this is for in case someone want to take out balance by inputting negative bvalue, just precaution
        {
            System.out.println("Deposit amount must be positive."); 
            return false;
        }
        balance += amount;
        changeLevel(); //update level based on balance, in place to refresh work the level so it doesnt stay same
        return true;
    }

    public Level getLevel() //retrieving level info
    {
        return level;
    }

    public double getFee() //retrieving fee info based off level
    {
        return level.getFee();
    }

    public void changeLevel()
    {
        if (balance >= 20000) //setting balance >= $20k as platinum level
        {
            level = Level.PLATINUM;
        }
        else if (balance >= 10000) //setting balance >= $10k but under $20k as gold level
        {
            level = Level.GOLD;
        }
        else //not necessary to check but any value under $10k will be accounted as silver level
        {
            level = Level.SILVER;
        }
    }

    public boolean withdraw(double amount) //method for withdrawing money from account
    {
        if (amount < 0) //cant hav negative withdrawals
        {
            System.out.println("Withdrawal amount needss to be positive.");
            return false;
        }
        if (balance - amount < 0) //checking if there is enough momney to proceed with the withdrawal
        {
            System.out.println("Insufficient balance.");
            return false;
        }
        balance -= amount;
        changeLevel(); //update level based on balance, in place to refresh work the level so it doesnt stay same
        return true;
    }
}
