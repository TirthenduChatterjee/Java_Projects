import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class retrie {
    private Stage stage;
   
    @FXML
    private Button cancel;

    @FXML
    private Button ok;

    @FXML
    void OnPressOk(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rand.fxml"));
        Parent root = loader.load();
        Stage current = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        current.close();
    }

    @FXML
    void onPressCancel(ActionEvent event) {
        stage.close();
        Stage current = (Stage)((Node)event.getSource()).getScene().getWindow();
        current.close();

    }
public void store(Stage stage){
        this.stage =stage;
    }
}
