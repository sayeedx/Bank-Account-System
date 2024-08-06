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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ManagerUI
{

    private Scene scene;
    private Manager manager;

    public ManagerUI(Stage primaryStage, Manager manager)
    {
        this.manager = manager;

        primaryStage.setTitle("Manager Control Panel");

        GridPane managerLayout = new GridPane(); //gridpane layout
        managerLayout.setAlignment(Pos.CENTER);
        managerLayout.setHgap(10);
        managerLayout.setVgap(5);
        managerLayout.setPadding(new Insets(10));

        Button addCustomerButton = new Button("Add Customer"); //button for adding customer
        Button delCustomerButton = new Button("Delete Customer"); //button for deleting customer
        Button logoutButton = new Button("Logout"); //button for logout

        addCustomerButton.setMinWidth(100);
        delCustomerButton.setMinWidth(100);

        logoutButton.setOnAction(e -> //event handler for logout button
        {
            primaryStage.setScene(new LoginUI(primaryStage).getScene());
            primaryStage.setTitle("Bank Application");
        });

        addCustomerButton.setOnAction(e -> //event handler for adding customer button
        {
            Stage addStage = new Stage();
            addStage.setTitle("Add Customer");

            GridPane addScreen = new GridPane();
            addScreen.setAlignment(Pos.CENTER);
            addScreen.setHgap(10);
            addScreen.setVgap(5);
            addScreen.setPadding(new Insets(10));

            TextField userDataField = new TextField();
            userDataField.setPromptText("Enter Customer Data");

            Label userDataLabel = new Label("Customer Data:");

            Label result = new Label();

            Button addButton = new Button("Confirm");

            addButton.setOnAction(event -> //event handler to confirm adding of customer
            {
                if (userDataField.getText().isEmpty())
                {
                    result.setText("Customer data is missing.\nPlease try again.");
                }
                else
                {
                    String userData = userDataField.getText();
                    String message = manager.addCustomer(userData); //call method from Manager to add customer
                    result.setText(message);

                    userDataField.clear();
                }
            });

            addScreen.add(userDataLabel, 0, 0);
            addScreen.add(userDataField, 1, 0);
            addScreen.add(addButton, 1, 2);
            addScreen.add(result, 1, 3);

            Scene addCustomerScene = new Scene(addScreen, 400, 200);
            addStage.setScene(addCustomerScene);
            addStage.show();
        });

        delCustomerButton.setOnAction(e -> //event handler for deleting customer button
        {
            Stage delStage = new Stage();
            delStage.setTitle("Delete Customer");

            GridPane delScreen = new GridPane();
            delScreen.setAlignment(Pos.CENTER);
            delScreen.setHgap(10);
            delScreen.setVgap(5);
            delScreen.setPadding(new Insets(10));

            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter Customer Username");

            Label usernameLabel = new Label("Username:");

            Label result = new Label();

            Button delButton = new Button("Confirm");

            delButton.setOnAction(event -> //event handler to confirm deleting of customer
            {
                String username = usernameField.getText();
                String message = manager.removeCustomer(username); //call method from Manager to remove customer
                result.setText(message);

                usernameField.clear();
            });

            delScreen.add(usernameLabel, 0, 0);
            delScreen.add(usernameField, 1, 0);
            delScreen.add(delButton, 1, 2);
            delScreen.add(result, 1, 3);

            Scene delCustomerScene = new Scene(delScreen, 400, 200);
            delStage.setScene(delCustomerScene);
            delStage.show();
        });

        managerLayout.add(addCustomerButton, 0, 2); //adding buttons to manager layout
        managerLayout.add(delCustomerButton, 1, 2);
        managerLayout.add(logoutButton, 0, 3, 2, 1);

        this.scene = new Scene(managerLayout, 300, 200); //setting scene w/ manager layout
    }

    public Scene getScene()
    {
        return scene;
    }
}
