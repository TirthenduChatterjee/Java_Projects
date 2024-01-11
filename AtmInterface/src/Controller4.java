import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Controller4 implements Initializable{
    private int id;
        Databaseconnect connection = new Databaseconnect();
        Connection connectdb = connection.connect();
    ObservableList<Transaction> list;
     @FXML
    private Button back;
    @FXML
    private TableColumn<Transaction, Double> close_balc;

    @FXML
    private TableColumn<Transaction, Double> credit;

    @FXML
    private TableColumn<Transaction, Timestamp> date;

    @FXML
    private TableColumn<Transaction, Double> debit;

    @FXML
    private TableColumn<Transaction, String> particulars;

    @FXML
    private TableView<Transaction> table;

    ObservableList<Transaction> getListData() throws Exception{
         ObservableList<Transaction> ob= FXCollections.observableArrayList();

        try{
        
       PreparedStatement ps = connectdb.prepareStatement("select operation,payerId,receipentId,amount,payeramt,receipentamt,date from Transactions where payerId = '"+id+"' or receipentId ='"+id+"'");
        ResultSet result = ps.executeQuery();
         String temp;
        while(result.next()){
            String op = result.getString("operation");
            int payerId =result.getInt("payerId");
            double payeramt=result.getDouble("payeramt");
            int receipentId = result.getInt("receipentId");
            double receipentamt=result.getDouble("receipentamt");

            if (!"transfer".equals(op)) {

                if ("withdraw".equals(op)) {
                    ob.add(new Transaction(result.getTimestamp("date"), "Withdrawn",result.getDouble("amount"), 0, receipentamt));
                }
                else if("deposit".equals(op)){
                    ob.add(new Transaction(result.getTimestamp("date"), "Deposited",0, result.getDouble("amount"), payeramt));
                }
            }else{
                String name = checkName(id);
                if (name.equals(checkName(payerId))) {
                    temp= checkName(receipentId);
                    ob.add(new Transaction(result.getTimestamp("date"), "To "+temp,result.getDouble("amount"), 0, payeramt));

                }
                else if(name.equals(checkName(receipentId))){
                        temp= checkName(payerId);
                    ob.add(new Transaction(result.getTimestamp("date"), "From "+temp,0, result.getDouble("amount"), receipentamt));                  
                }
            }
        }

    }catch(Exception e){
        System.out.println(e);
    }
        return ob;
    }
    void getId(int id){
        this.id=id;
    }
    void showData() throws Exception{
        try {
        date.setCellValueFactory(new PropertyValueFactory<Transaction,Timestamp>("date"));
        particulars.setCellValueFactory(new PropertyValueFactory<Transaction,String>("particulars"));
        debit.setCellValueFactory(new PropertyValueFactory<Transaction,Double>("debit"));
        credit.setCellValueFactory(new PropertyValueFactory<Transaction,Double>("credit"));
        close_balc.setCellValueFactory(new PropertyValueFactory<Transaction,Double>("amt"));
        list=getListData();
        table.setItems(list);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
private String checkName(int id) {
            String name =" ";
          String  query = "Select name from Users where id = '" + id + "'";
            try {
                Statement st = connectdb.createStatement();
                ResultSet result = st.executeQuery(query);
                if (result.next()) {
                    name = result.getString("name");
                }
                result.close();
                st.close();
            } catch (Exception e) {
                System.out.println(e);
            }
    
            return name;
        }

@Override
public void initialize(URL location, ResourceBundle resources) {
                try {
                    showData();
                } catch (Exception e) {
                    System.out.println(e);
                }

    
}
@FXML
    void onClickBack(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/dashboard.fxml"));
                Parent root;
                try {
                    root = loader.load();
                     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Dashboard");
                Controller2 controller2 = loader.getController();
                controller2.getId(id);
                Scene dashboard = new Scene(root);
                stage.setScene(dashboard);
                stage.show();
                } catch (IOException e) {
                    System.out.println(e);
                }
               
    }

    }
