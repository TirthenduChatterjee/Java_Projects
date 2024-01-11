import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    int id;
    @FXML
    Button LOGIN;

    @FXML
    private TextField Id;

    @FXML
    private TextField Name;

    @FXML
    private PasswordField Password;

    @FXML
    private Button cancel;

    @FXML
    private Label warning;

    @FXML
    void OnPressLogin(ActionEvent event) throws IOException {

        if (Name.getText().isBlank() == false && Password.getText().isBlank() == false) {
            int a = validate();
            if (a == 1) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/dashboard.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Dashboard");
                Controller2 controller2 = loader.getController();
                controller2.getId(id);
                Scene dashboard = new Scene(root);
                // dashboard.getStylesheets().add(getClass().getResource("css/tranchist.css").toExternalForm());

                stage.setScene(dashboard);
                
                stage.show();
            } else {
                warning.setText("You Entered Wrong Password or Username");

            }

        } else {
            warning.setText("Please Enter Both Password and Username");
        }

    }

    @FXML
    void onClickCancel(ActionEvent event) {
        Password.setText("");
        Name.setText("");
        warning.setText("");
        Id.setText("");
    }

    int validate() {
        Databaseconnect connection = new Databaseconnect();
        Connection connectdb = connection.connect();
        String Query = "select * from Users where name = '" + Name.getText() + "' and pin = '" + Password.getText()
                +"'and id ='"+Id.getText()+"'";
        try {
            int i = 0;
            Statement st = connectdb.createStatement();
            ResultSet result = st.executeQuery(Query);
            while (result.next()) {
                id = result.getInt(1);
                i++;
            }
            connectdb.close();
            return i;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;

    }

}