import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller2 {
    Databaseconnect connection = new Databaseconnect();
    Connection connectdb = connection.connect();
   private String query,query2;
    private int id;
    @FXML
    private Label balance;

    @FXML
    private Button check;

    @FXML
    private TextField depAmt;
    @FXML
    private Button history;
    @FXML
    private Button deposit;

    @FXML
    private TextField drawAmt;

    @FXML
    private Button exit;

    @FXML
    private Label warning;

    @FXML
    private Button withdraw;

    @FXML
    private Button transfer;

    @FXML
    void onClickDeposit(ActionEvent event) {
        if (depAmt.getText().isBlank()!=true) {
            double amt = Double.parseDouble(depAmt.getText());
            query = "update Users set amount = amount + '"+amt+"' where id ='"+id+"'" ;
            query2= "insert into Transactions (operation,payerId,receipentId,amount,payeramt,receipentamt) value (?,?,?,?,?,?)";
            try {
                Statement st = connectdb.createStatement();
                PreparedStatement pt = connectdb.prepareStatement(query2);
                int rows = st.executeUpdate(query);
                st.close();
                if (rows>0) {
                    double temp = check(id);
                    pt.setString(1, "deposit");
                    pt.setInt(2, id);
                    pt.setInt(3, id);
                    pt.setDouble(4, amt);
                    pt.setDouble(5, temp);
                    pt.setDouble(6, temp);

                    warning.setTextFill(Color.BLUE);
                    warning.setText(amt+" Successfully Deposited in Your Account");
                    pt.executeUpdate();
                    pt.close();
                }else{
                    warning.setTextFill(Color.RED);
                    warning.setText("Error In Depositing Amount");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            warning.setTextFill(Color.RED);
            warning.setText("No Amount To Deposit");
        }  
    }

    @FXML
    void onClickExit(ActionEvent event) throws IOException, SQLException {
        connectdb.close();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("TCTS");
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void onClickWithdraw(ActionEvent event) {
        if (drawAmt.getText().isBlank()!=true) {
            double balc=check(id);
            double amt =  Double.parseDouble(drawAmt.getText());
            if (amt<balc) {
                 query = "update Users set amount = amount - '"+amt+"' where id ='"+id+"'" ;
                 query2= "insert into Transactions (operation,payerId,receipentId,amount,payeramt,receipentamt) value (?,?,?,?,?,?)";
            try {
                Statement st = connectdb.createStatement();
                PreparedStatement pt = connectdb.prepareStatement(query2);
                
                int rows = st.executeUpdate(query);
                st.close();
                if (rows>0) {
                     double temp = check(id);
                pt.setString(1, "withdraw");
                pt.setInt(2, id);
                pt.setInt(3, id);
                pt.setDouble(4, amt);
                pt.setDouble(5, temp);
                pt.setDouble(6, temp);

                    warning.setTextFill(Color.BLUE);
                    warning.setText(amt+" Successfully Withdrawn from Your Account");
                    pt.executeUpdate();
                    pt.close();
                }else{
                    warning.setTextFill(Color.RED);
                    warning.setText("Error In Withdrawing Amount");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            }else{
                 warning.setTextFill(Color.RED);
                 warning.setText("!! Not Enough Balance to Withdraw !!");
            }
        }else{
            warning.setTextFill(Color.RED);
            warning.setText("No Amount To Withdraw");
        }  
    }

    @FXML
    void onClickcheck(ActionEvent event) {
        double balc = check(id);
        balance.setText(balc+"");
    }

    @FXML
    void onClickTransfer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/transfer.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Tranfer Page");
        Controller3 controller3 = loader.getController();
        controller3.getid(id);
        Scene Tranfer = new Scene(root);
        stage.setScene(Tranfer);
        stage.show();
    }

    void getId(int id){
        this.id = id;
    }
    double check(int id){
        double balc=0;
         query = "Select amount from Users where id = '"+id+"'";
        try{
        Statement st = connectdb.createStatement();
        ResultSet result = st.executeQuery(query);
        if (result.next()) {
             balc = result.getDouble("amount");
            
        }
        }catch(Exception e){
            System.out.println(e);
        }
        return balc;
    }
    @FXML
    void onClickHistory(ActionEvent event) throws IOException{
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/transactionhist.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Transaction History");
        Controller4 controller = loader.getController();
        controller.getId(id);
        controller.initialize(null, null);
        stage.setScene(new Scene(root));
        stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}