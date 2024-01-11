import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

public class Controller3 {
    protected int id ,recipientId;

    protected double amt,balc,payeramt,recipientamt;
        Databaseconnect connection = new Databaseconnect();
        Connection connectdb = connection.connect();
        String query0,query1,query2,query3,query4;
    @FXML
    private Button cancel;
    @FXML
    private TextField Id;

    @FXML
    private Button back;

    @FXML
    private TextField recipient;

    @FXML
    private Button transfer;

    @FXML
    private TextField transferamount;

    @FXML
    private Label warning;

    @FXML
    void onClickCancel(ActionEvent event) {
        recipient.setText("");
        transferamount.setText("");
        warning.setText("");
    }

    @FXML
    void onClickTransfer(ActionEvent event) {
       
        if (transferamount.getText().isBlank()!=true && recipient.getText().isBlank()!=true) {
            balc=check(id);
             amt = Double.parseDouble(transferamount.getText());
            String recipientname=recipient.getText();
            String accId = Id.getText();
            if (amt<balc) {
                 query0 = "update Users set amount = amount - '"+amt+"' where id ='"+id+"'" ;
                 query1 = "update Users set amount = amount +'"+amt+"'where name ='"+recipientname+"'and id ='"+accId+"'";
                 query4= "insert into Transactions (operation,payerId,receipentId,amount,payeramt,receipentamt) value (?,?,?,?,?,?)";
            try {
                Statement st = connectdb.createStatement();
                int row1 =st.executeUpdate(query0);
                int row2 = st.executeUpdate(query1);
                st.close();
                if (row1>0 && row2 >0) {
                    warning.setTextFill(Color.BLUE);
                    warning.setText(amt+" Successfully Transfer To "+recipientname);
                    payeramt=check(id);
                    recipientamt =check(recipientname);
                    recipientId=checkId(recipientname);
                    // System.out.println(id +" " +recipientId+" "+amt+" "+" "+payeramt+" "+recipientamt);
                    PreparedStatement pt = connectdb.prepareStatement(query4);
                    pt.setString(1, "transfer");
                    pt.setInt(2, id);
                    pt.setInt(3, recipientId);
                    pt.setDouble(4, amt);
                    pt.setDouble(5, payeramt);
                    pt.setDouble(6, recipientamt);
                    pt.executeUpdate();
                    pt.close();
                }else{
                    warning.setTextFill(Color.RED);
                    warning.setText("Error In Transfering Amount");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            }else{
                 warning.setTextFill(Color.RED);
                 warning.setText("!! Not Enough Balance to Transfer !!");
            }
        }else{
            warning.setTextFill(Color.RED);
            warning.setText("Please Enter Valid Username And Password");
        }  
    }
    void getid(int id){
        this.id = id;
    }
        double check(int id) {
            double balc = 0;
          String  query = "Select amount from Users where id = '" + id + "'";
            try {
                Statement st = connectdb.createStatement();
                ResultSet result = st.executeQuery(query);
                if (result.next()) {
                    balc = result.getDouble("amount");
                }
                result.close();
                st.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        
            return balc;
        }
         int check(String name) {
            int balc = 0;
          String  query = "Select amount from Users where name = '" + name + "'";
            try {
                Statement st = connectdb.createStatement();
                ResultSet result = st.executeQuery(query);
                if (result.next()) {
                    balc = result.getInt("amount");
                }
                result.close();
                st.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        
            return balc;
        }
    int checkId(String name) {
            int id = 0;
          String  query = "Select id from Users where name = '" + name + "'";
            try {
                Statement st = connectdb.createStatement();
                ResultSet result = st.executeQuery(query);
                if (result.next()) {
                    id = result.getInt("id");
                }
                result.close();
                st.close();
            } catch (Exception e) {
                System.out.println(e);
            }
    
            return id;
        }
    @FXML
    void onClickBack(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/dashboard.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Dashboard");
                Controller2 controller2 = loader.getController();
                controller2.getId(id);
                Scene dashboard = new Scene(root);
                stage.setScene(dashboard);
                stage.show();
    }

}
