import java.io.IOException;
import java.util.Random;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Randomm {
    int i = 5;
    @FXML
    private Label moves;

    @FXML
    private TextField number;
    @FXML
    private Label warning;

    @FXML
    private Label promptText;

    Random ran = new Random();
    int rand = ran.nextInt(101);
    

    @FXML

    void onEnterPressed(KeyEvent event) {
        try {

            if (event.getCode() == KeyCode.ENTER) {// Check whether you have pressed enter or not
               System.out.println(rand);
                String inputText = number.getText();
                number.setText("");
                if (!isNumeric(inputText)) {// check whether the input string is integer or not
                    promptText.setText("");
                    warning.setText("Please Enter a Valid Number");
                } else {// if the input string is a number
                    int num = Integer.parseInt(inputText);
                    warning.setText("");
                    if (num > 100 || num < 0) {// checks whether the number is with 0 to 100
                        warning.setText("Enter a Number Within 0-100.");
                    } else {
                        if (i > 0 && i <= 5) {// checks if the moves is expired or not
                            warning.setText("");
                            if (num == rand) {
                                promptText.setText("Congrats you Guessed the Correct Number.");
                                i--;
                                moves.setText("Moves :" + i);
                                // Thread.sleep(3000);
                                retry(event);// ask to restart the game
                            } else if (num > rand) {
                                promptText.setText("Guess a Number Lesser Than " + num);
                                i--;
                                moves.setText("Moves :" + i);
                            } else if (num < rand) {
                                promptText.setText("Guess a Number Greater Than " + num);
                                i--;
                                moves.setText("Moves :" + i);
                            }
                        } else {// if all the moves expired
                            promptText.setText("All Moves Expired, the Correct number is "+rand);
                            retry(event);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    void retry(Event event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("retry.fxml"));// loads the fxml file
        retrie controller = new retrie();// instansite the controller class
        controller.store((Stage) ((Node) event.getSource()).getScene().getWindow());//sends the reference of the current stage 
        loader.setController(controller);// adds the controller to the loaded fxml file
        Parent root = loader.load();
        Stage s = new Stage();
        s.setScene(new Scene(root));
        s.setResizable(false);
        s.show();
    }
}
