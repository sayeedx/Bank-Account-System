package coe528.project;
/**
 *
 * @author Sayeed
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Manager extends User
{

    public Manager(String username, String password, String role)
    {
        super(username, password, role);
    }

    @Override
    public boolean authenticate() //authenticating manager user and pass
    {
        return getUsername().equals("admin") && getPassword().equals("admin") && getRole().equals("manager");
    }

    public List<String> readCustomerAccounts() //reading all the customer account data and returns string a list of strings representing customer data
    {
        try
        {
            return Files.readAllLines(Paths.get("CustomerAccounts.txt"));
        }
        catch (IOException e)
        {
            System.err.println("Error reading customer accounts file: " + e.getMessage());
            return null;
        }
    }

    public String addCustomer(String userData) //adding new customer to the dataset
    {
        try
        {
            Files.write(Paths.get("CustomerAccounts.txt"), (userData + "\n").getBytes(), java.nio.file.StandardOpenOption.APPEND);
            return "Customer added successfully.";
        }
        catch (IOException e)
        {
            System.err.println("Error adding customer: " + e.getMessage());
            return "Error adding customer: " + e.getMessage();
        }
    }

    public String removeCustomer(String username) //removing existing customer data from the file
    {
        try
        {
            List<String> lines = Files.readAllLines(Paths.get("CustomerAccounts.txt"));
            StringBuilder result = new StringBuilder();
            boolean found = false;
            for (String line : lines)
            {
                if (!line.startsWith(username + ":"))
                {
                    result.append(line).append("\n");
                }
                else
                {
                    found = true;
                }
            }
            if (!found)
            {
                return "Customer not found.";
            }
            Files.write(Paths.get("CustomerAccounts.txt"), result.toString().getBytes());
            return "Customer removed successfully.";
        }
        catch (IOException e)
        {
            System.err.println("Error removing customer: " + e.getMessage());
            return "Error removing customer: " + e.getMessage();
        }
    }
}
