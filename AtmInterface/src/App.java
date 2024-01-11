import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("TCTS");
            Scene scene = new Scene(root, 718, 484);
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("resource/tctslogo2.png"));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}