package coe528.project;
/**
 *
 * @author Sayeed
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomerUI
{
    
    private Scene scene;
    private CustomerAccounts CustomerAccounts;
    private Customer c;
    private String username;
    
    public CustomerUI(Stage primaryStage, CustomerAccounts CustomerAccounts, String username)
    {
        //initializing inst var
        this.CustomerAccounts = CustomerAccounts;
        this.username = username;
        String password = CustomerAccounts.getPassword(username);
        String role = CustomerAccounts.getRole(username);
        //customer object with the retrieved customer info
        this.c = new Customer(username, password, role, 0.0); // Default balance is set to 0.0
               
        primaryStage.setTitle("Customer Screen"); //title for customer ui
        
        GridPane customerLayout = new GridPane(); //layout for customer screen
        customerLayout.setAlignment(Pos.CENTER);
        customerLayout.setHgap(10);
        customerLayout.setVgap(5);
        customerLayout.setPadding(new Insets(10));
        
        VBox logout = new VBox(10); //logout button
        logout.setAlignment(Pos.CENTER);
        logout.setPadding(new Insets(20));

        Button depButton = new Button("Deposit Money"); //deposit button
        Button withButton = new Button("Withdraw Money"); //withdraw button
        Button balButton = new Button("Show Balance"); //show bal button
        Button storeButton = new Button("Online Store"); //online store button
        Button logoutButton = new Button("Logout"); //logout button
        
        depButton.setMinWidth(200);
        withButton.setMinWidth(200);
        balButton.setMinWidth(200);
        storeButton.setMinWidth(200);

        logoutButton.setOnAction(e -> //logout button action event
        {
            primaryStage.setScene(new LoginUI(primaryStage).getScene());
            primaryStage.setTitle("Bank Application");
        });
        
        depButton.setOnAction(e -> //logout button action event
        {
            Stage depStage = new Stage();
            depStage.setTitle("Deposit Money to Balance");
            
            GridPane depScreen = new GridPane();
            depScreen.setAlignment(Pos.CENTER);
            depScreen.setHgap(10);
            depScreen.setVgap(5);
            depScreen.setPadding(new Insets(10));
            
            TextField amountField = new TextField();
            amountField.setPromptText("Enter Amount to Deposit");
            
            Label amountLabel = new Label("Amount:");
            
            Label result = new Label();

            Button confButton = new Button("Confirm");
            
            Label bal = new Label("Current Balance: " + c.checkBalance()); // Get balance from the customer object
            
            confButton.setOnAction(event ->
            {
                try
                {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0)
                    {
                        boolean success = c.deposit(amount);
                        if (success)
                        {
                            result.setText("Balance successfully updated");
                            bal.setText("New Balance: $" + c.checkBalance()); // Get balance from the customer object
                            c.changeLevel();
                        }
                        else
                        {
                            result.setText("Error: Unable to deposit amount");
                            bal.setText("Balance: $" + c.checkBalance());
                        }
                        
                    } 
                    else
                    {
                        result.setText("Error: Please enter a positive amount to deposit");
                        bal.setText("Balance: $" + c.checkBalance());

                    }
                } 
                catch (NumberFormatException ex)
                {
                    result.setText("Error: Invalid amount entered");
                    bal.setText("Balance: $" + c.checkBalance());

                }
            });
            
            depScreen.add(amountLabel, 0, 0);
            depScreen.add(amountField, 1, 0);
            depScreen.add(confButton, 1, 4);
            depScreen.add(result, 1, 6);
            depScreen.add(bal, 1, 7);

            Scene addCustomerScene = new Scene(depScreen, 400, 300);
            depStage.setScene(addCustomerScene);
            depStage.show();
            
        });
        
        withButton.setOnAction(e ->
        {
            Stage depStage = new Stage();
            depStage.setTitle("Withdraw Money to Balance");
            
            GridPane depScreen = new GridPane();
            depScreen.setAlignment(Pos.CENTER);
            depScreen.setHgap(10);
            depScreen.setVgap(5);
            depScreen.setPadding(new Insets(10));
            
            TextField amountField = new TextField();
            amountField.setPromptText("Enter Amount to Withdraw");
            
            Label amountLabel = new Label("Amount:");
            
            Label result = new Label();

            Button confButton = new Button("Confirm");
            
            Label bal = new Label("Current Balance: " + c.checkBalance()); // Get balance from the customer object
            
            confButton.setOnAction(event ->
            {
                try
                {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0)
                    {
                        boolean success = c.withdraw(amount);
                        if (success)
                        {
                            result.setText("Balance successfully updated");
                            bal.setText("New Balance: $" + c.checkBalance()); // Get balance from the customer object
                            c.changeLevel();
                        }
                        else
                        {
                            result.setText("Error: Unable to withdraw amount");
                            bal.setText("Balance: $" + c.checkBalance());
                        }
                        
                    } 
                    else
                    {
                        result.setText("Error: Please enter a positive amount to withdraw");
                        bal.setText("Balance: $" + c.checkBalance());

                    }
                } 
                catch (NumberFormatException ex)
                {
                    result.setText("Error: Invalid amount entered");
                    bal.setText("Balance: $" + c.checkBalance());

                }
            });
            
            depScreen.add(amountLabel, 0, 0);
            depScreen.add(amountField, 1, 0);
            depScreen.add(confButton, 1, 4);
            depScreen.add(result, 1, 6);
            depScreen.add(bal, 1, 7);

            Scene addCustomerScene = new Scene(depScreen, 400, 300);
            depStage.setScene(addCustomerScene);
            depStage.show();
            
        });
        
        balButton.setOnAction(e ->
        {
            Stage balanceStage = new Stage();
            balanceStage.setTitle("Check Balance");

            GridPane balanceScreen = new GridPane();
            balanceScreen.setAlignment(Pos.CENTER);
            balanceScreen.setHgap(10);
            balanceScreen.setVgap(5);
            balanceScreen.setPadding(new Insets(10));

            Label balanceLabel = new Label("Current Balance:");
            Label result = new Label("$" + c.checkBalance()); // Get balance from the customer object

            balanceScreen.add(balanceLabel, 0, 0);
            balanceScreen.add(result, 1, 0);

            Scene balanceScene = new Scene(balanceScreen, 300, 150);
            balanceStage.setScene(balanceScene);
            balanceStage.show();
        });
        
        storeButton.setOnAction(e ->
        {
            Stage storeStage = new Stage();
            storeStage.setTitle("Online Store");
            
            GridPane storeScreen = new GridPane();
            storeScreen.setAlignment(Pos.CENTER);
            storeScreen.setHgap(10);
            storeScreen.setVgap(5);
            storeScreen.setPadding(new Insets(10));
            
            Label welcome = new Label("Welcome to the Online Store");
            Label bal = new Label("Your Account Balance Is: $" + c.checkBalance());
            Label item = new Label("Enter Product Name:");
            Label price = new Label("Enter Product Price:");
            Label level = new Label();
            Label fee = new Label();
            Label result = new Label();
            
            Button confButton = new Button("Confirm");
            
            // Make it so that when the cutomer clicks confirm on their purchase the price is deducted from their account and their level change accoridngly
            level.setText("Level: " + c.getLevel());
            fee.setText("Fee: $" + Double.toString(c.getFee()));
            
            TextField itemName = new TextField();
            itemName.setPromptText("Enter Product Name");
            
            TextField itemPrice = new TextField();
            itemPrice.setPromptText("Enter Product Price");
            
            storeScreen.add(welcome, 0, 0);
            storeScreen.add(item, 0, 1);
            storeScreen.add(itemName, 1, 1);
            storeScreen.add(price, 0, 2);
            storeScreen.add(itemPrice, 1, 2);
            storeScreen.add(bal, 0, 3);
            storeScreen.add(result, 0, 4);
            storeScreen.add(level, 0, 5);
            storeScreen.add(fee, 0, 6);
            storeScreen.add(confButton, 1, 3);
            
            confButton.setOnAction(event ->
            {
                try
                {
                    double amount = Double.parseDouble(itemPrice.getText());
                    if (amount > 0)
                    {
                        boolean success = c.withdraw(amount + c.getFee());
                        if (success)
                        {
                            result.setText("Item has been purchased");
                            bal.setText("New Balance: $" + c.checkBalance()); // Get balance from the customer object
                            c.changeLevel();
                            level.setText("Level: " + c.getLevel());
                            fee.setText("Fee: $" + Double.toString(c.getFee()));
                        }
                        else
                        {
                            result.setText("Error: Unable to purchase item");
                            bal.setText("Balance: $" + c.checkBalance());
                        }
                        
                    } 
                    else
                    {
                        result.setText("Error: Please enter a positive amount for the price");
                        bal.setText("Balance: $" + c.checkBalance());

                    }
                } 
                catch (NumberFormatException ex)
                {
                    result.setText("Error: Invalid amount entered");
                    bal.setText("Balance: $" + c.checkBalance());

                }
            
            });

            Scene balanceScene = new Scene(storeScreen, 500, 300);
            storeStage.setScene(balanceScene);
            storeStage.show();
            
        });
        
        customerLayout.add(depButton, 0, 2); //adding buttons n logout pane to the layout
        customerLayout.add(withButton, 1, 2);
        customerLayout.add(balButton, 0, 3);
        customerLayout.add(storeButton, 1, 3);
        
        logout.getChildren().addAll(logoutButton);
        
        customerLayout.add(logout, 0, 4, 2, 1);

        this.scene = new Scene(customerLayout, 500, 200); //creating scene for the customer screen
    }
    
    public Scene getScene(){
        return scene;
    }
    
    
}
