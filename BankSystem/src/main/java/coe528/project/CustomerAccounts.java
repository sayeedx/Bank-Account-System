package coe528.project;
/**
 *
 * @author Sayeed
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomerAccounts
{
    //this method is for creating the data/customer file within the database
    private static final String CUSTOMER_ACCOUNTS_FILE = "CustomerAccounts.txt"; //the file path for the customer accounts
    //format for user data will be saved as username:password:role:balance
    public static String createCustomerFile(String userData) //creates a new customer file wi the provided user data
    {
        File file = new File(CUSTOMER_ACCOUNTS_FILE); //creating or appending customer accounts file
        
        try(PrintWriter writer = new PrintWriter(new FileWriter(file, true)))
        {
            writer.println(userData); //writing user data to file
            return "Customer file created successfully.";
        }
        catch(IOException e)
        {
            return "Error creating customer file: " + e.getMessage();
        }
    }
    //this method is for removing the data/customer file within the database
    public static String removeCustomer(String username)
    {
        File file = new File(CUSTOMER_ACCOUNTS_FILE); //opening cusotmer accounts file
        File tempFile = new File("temp.txt"); //temporary data
        
        try(BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile)))
        {
            
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) //reading each line of file
            {
                String[] parts = line.split(":");
                if (parts.length > 0 && parts[0].equals(username))
                {
                    found = true;
                    continue;
                }
                writer.println(line); //writing all lines asides the one to be removed to temp file
            }
            
            if (found) //if customer file is found as well removed, delete original file and rename temp file
            {
                file.delete();
                tempFile.renameTo(file);
                return "Customer file successfully deleted for User: " + username;
            }
            else
            {
                return "Customer file does not exist for User: " + username;
            }
        }
        catch(IOException e)
        {
            return "Error removing customer file: " + e.getMessage();
        }
    }
    //method for retrieving username of customers
    public static String getUsername(String username)
    {   //reading each line of accounts file
        try(BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_ACCOUNTS_FILE)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(":");
                if (parts.length > 0 && parts[0].equals(username))
                {
                    return parts[0]; //return username if found
                }
            }
            return null; //if not found then null
        } catch (IOException e)
        {
            return "Error retrieving username: " + e.getMessage();
        }
    }

    public static String getPassword(String username)
    {   //reading each line of accounts file
        try(BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_ACCOUNTS_FILE)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(":");
                if (parts.length > 1 && parts[0].equals(username))
                {
                    return parts[1]; //return password if found
                }
            }
            return null; //if not found then null
        }
        catch (IOException e)
        {
            return "Error retrieving password: " + e.getMessage();
        }
    }

    public static String getRole(String username)
    {   //reading each line of accounts file
        try(BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_ACCOUNTS_FILE)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(":");
                if (parts.length > 2 && parts[0].equals(username))
                {
                    return parts[2]; //return role if found
                }
            }
            return null;
        }
        catch (IOException e)
        {
            return "Error retrieving role: " + e.getMessage();
        }
    }

    public static double getBalance(String username)
    {   //reading each line of accounts file
        try(BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_ACCOUNTS_FILE)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(":");
                if (parts.length > 3 && parts[0].equals(username))
                {
                    return Double.parseDouble(parts[3]);
                }
            }
            return 0;
        }
        catch (IOException | NumberFormatException e)
        {
            return -1; // -1 indicates an error or balance not found
        }
    }
}
