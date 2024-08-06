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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginUI
{
    
    private final Scene scene; //scene object for the login interface
    
    public LoginUI(Stage primaryStage)
    {
        
        primaryStage.setTitle("Bank Application"); //title of primary stage
        
        GridPane loginLayout = new GridPane(); //gridpane layout for login interface
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setHgap(10);
        loginLayout.setVgap(10);
        loginLayout.setPadding(new Insets(20));
        
        TextField usernameField = new TextField(); //creating text fields for both username and password
        usernameField.setPromptText("Enter Username");
        
        Label usernameLbl = new Label("Username:");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        
        Label passwordLbl = new Label("Password:");
        
        Button loginButton = new Button("Login"); //login button
        
        Label result = new Label(); //labelling the login result
        
        loginButton.setOnAction(e -> //event handler for login button
        {
            String username = usernameField.getText(); //authentication system
            String password = passwordField.getText();
    
            Manager m = new Manager(username, password, "manager");
            Customer c = new Customer(username, password, "customer", 0.0); //default balance is set to 0.0
    
            if (username.isEmpty() || password.isEmpty())
            {
                result.setText("Invalid Credentials. Try Again.");
            }
            else
            {
                if (m.authenticate())
                {
                    primaryStage.setScene(new ManagerUI(primaryStage, m).getScene());
                }
                else if (c.authenticate())
                {
                    primaryStage.setScene(new CustomerUI(primaryStage, new CustomerAccounts(), c.getUsername()).getScene());
                }
                else
                {
                    result.setText("Invalid Credentials. Try Again.");
                }
            }
        });
        //adding comonetns to gridpane
        loginLayout.add(usernameLbl, 0, 0);
        loginLayout.add(usernameField, 1, 0);
        
        loginLayout.add(passwordLbl, 0, 1);
        loginLayout.add(passwordField, 1, 1);
        
        loginLayout.add(loginButton, 1, 3);
        
        loginLayout.add(result, 1, 4);
        
        this.scene = new Scene(loginLayout, 300, 150); //creating scene w gridpane layout
    }

    public Scene getScene()
    {
        return scene;
    }
}
