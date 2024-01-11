import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
       try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rand.fxml"));
        Parent root =loader.load();
        primaryStage.setTitle("Random Guess");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
       } catch (Exception e) {
        System.out.println(e);
       }
        

    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
