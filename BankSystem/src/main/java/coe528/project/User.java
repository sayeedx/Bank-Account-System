package coe528.project;
/**
 *
 * @author Sayeed
 */
public abstract class User
{
    
    protected String username;
    protected String password;
    protected String role;
    protected double balance;
    protected String tier;
    
    public User(String username, String password, String role) //user object w/ parameters user, pass, and role
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public abstract boolean authenticate(); //abst method to authenticate user; class will output to user if login was sucess or no
        
    public String getUsername() //getter method for username
    {
        return username;
    }

    public String getPassword() //getter method for password
    {
        return password;
    }

    public String getRole() //getter method for role
    {
        return role;
    }
    
    public double getBal() //getter method for balance
    {
        return balance;
    }
    
   

}
