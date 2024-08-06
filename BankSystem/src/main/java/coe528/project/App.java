package coe528.project;
/**
 *
 * @author Sayeed
 */
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
    
    private Scene loginScene;
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Bank Account Login");
        
        LoginUI login = new LoginUI(primaryStage); //creating login scene/box
        loginScene = login.getScene();
        
        primaryStage.setScene(loginScene); //initial scene/box will be the login page
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}


