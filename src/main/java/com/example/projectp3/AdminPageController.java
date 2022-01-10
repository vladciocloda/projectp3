package com.example.projectp3;

import com.mysql.cj.log.Log;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.jar.JarOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminPageController implements Initializable {


    @FXML
    private Label lblClose;

    @FXML
    private ComboBox comboBox1;

    @FXML
    private ComboBox comboBox2;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnTeachers;

    @FXML
    private Button btnFees;

    @FXML
    private Button btnUsers;

    @FXML
    private Button btnSettings;

    @FXML
    private Label lblStatus;

    @FXML
    private Pane pnlStatus;

    @FXML
    private TableView<ModelTable> table_students;

    @FXML
    private TableColumn<ModelTable,String> col_sid;

    @FXML
    private TableColumn<ModelTable,String> col_sname;

    @FXML
    private TableColumn<ModelTable,String> col_scourse;

    @FXML
    private TableView<ModelTable1> table_teachers;

    @FXML
    private TableColumn<ModelTable1,String> col_tid;

    @FXML
    private TableColumn<ModelTable1,String> col_tname;

    @FXML
    private TableColumn<ModelTable1,String> col_tcourse;

    @FXML
    private TableView<ModelTable2> table_users;

    @FXML
    private TableColumn<ModelTable2,String> col_ssname;

    @FXML
    private TableColumn<ModelTable2,String> col_ttname;

    @FXML
    private TableView<ModelTable3> table_fee;

    @FXML
    private TableColumn<ModelTable3,String> col_fid;

    @FXML
    private TableColumn<ModelTable3,String> col_fname;

    @FXML
    private TableColumn<ModelTable3,String> col_fcourse;

    @FXML
    private TableColumn<ModelTable,String> col_fee;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtFid;

    @FXML
    private GridPane pnStudents;

    @FXML
    private GridPane pnTeachers;

    @FXML
    private TextField txtAddId;

    @FXML
    private TextField txtAddName;

    @FXML
    private TextField txtAddCourse;

    @FXML
    private TextField txtAddFee;

    @FXML
    private TextField txtAddPass;

    @FXML
    private TextField txtAddTId;

    @FXML
    private TextField txtAddTName;

    @FXML
    private TextField txtAddTCourse;

    @FXML
    private TextField txtAddTPass;

    @FXML
    private GridPane pnUsers;

    @FXML
    private GridPane pnFees;

    @FXML
    private GridPane pnBlank;

    @FXML
    private GridPane pnSettings;

    @FXML
    private AnchorPane adminPane;

    int index = -1;

    Stage stage;
    public void logout(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure you want to logout?: ");

        if(alert.showAndWait().get() == ButtonType.OK) {

            stage = (Stage) adminPane.getScene().getWindow();
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }


    @FXML
    private void handleButtonAction(MouseEvent event){
        if (event.getSource() == lblClose) {
            System.exit(0);
        }
    }

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    ObservableList<ModelTable1> oblist1 = FXCollections.observableArrayList();

    ObservableList<ModelTable2> oblist2 = FXCollections.observableArrayList();

    ObservableList<ModelTable3> oblist3 = FXCollections.observableArrayList();




    PreparedStatement pst;
    public void buttonAddStudents(ActionEvent event){

        ModelTable student = new ModelTable(txtAddId.getText(),txtAddName.getText(),txtAddCourse.getText());
        table_students.getItems().add(student);

        String studid = txtAddId.getText();
        String studname = txtAddName.getText();
        String studcourse = txtAddCourse.getText();
        String studfee = txtAddFee.getText();
        String studpass = txtAddPass.getText();

        try {

            DataBaseConnection connectNow = new DataBaseConnection();
            Connection con = connectNow.getConnection();

            pst = con.prepareStatement("insert into students(id,sname,scourse,fee,spass)values(?,?,?,?,?)");
            pst.setString(1, studid);
            pst.setString(2, studname);
            pst.setString(3, studcourse);
            pst.setString(4, studfee);
            pst.setString(5, studpass);
            int status = pst.executeUpdate();


            if (status == 1) {
                txtAddId.setText("");
                txtAddName.setText("");
                txtAddCourse.setText("");
                txtAddFee.setText("");
                txtAddPass.setText("");
            }

        }
        catch (SQLException ex){
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void buttonAddTeachers(ActionEvent event){
        ModelTable1 teacher = new ModelTable1(txtAddTId.getText(),txtAddTName.getText(),txtAddTCourse.getText());
        table_teachers.getItems().add(teacher);

        String teacid = txtAddTId.getText();
        String teacname = txtAddTName.getText();
        String teaccourse = txtAddTCourse.getText();
        String teacpass = txtAddTPass.getText();

        try {

            DataBaseConnection connectNow = new DataBaseConnection();
            Connection con = connectNow.getConnection();

            pst = con.prepareStatement("insert into teachers(id,tname,tcourse,tpass)values(?,?,?,?)");
            pst.setString(1, teacid);
            pst.setString(2, teacname);
            pst.setString(3, teaccourse);
            pst.setString(4, teacpass);
            int status = pst.executeUpdate();


            if (status == 1) {
                txtAddTId.setText("");
                txtAddTName.setText("");
                txtAddTCourse.setText("");
                txtAddTPass.setText("");
            }

        }
        catch (SQLException ex){
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        //////////////// Drag data for students into the table view ////////////////
        try {
            DataBaseConnection connectNow = new DataBaseConnection();
            Connection connectDB = connectNow.getConnection();

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM students");

            while (rs.next()){
                oblist.add(new ModelTable(rs.getString("id"),
                        rs.getString("sname"),
                        rs.getString("scourse")));
            }

        } catch (SQLException ex){
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_sid.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_sname.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_scourse.setCellValueFactory(new PropertyValueFactory<>("course"));

        table_students.setItems(oblist);

        //////// END ////////

        //////////////// Drag data for teachers into the table ////////////////

        try {
            DataBaseConnection connectNow = new DataBaseConnection();
            Connection connectDB = connectNow.getConnection();

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM teachers");

            while (rs.next()){
                oblist1.add(new ModelTable1(rs.getString("id"),
                        rs.getString("tname"),
                        rs.getString("tcourse")));
            }

        } catch (SQLException ex){
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_tname.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_tcourse.setCellValueFactory(new PropertyValueFactory<>("course"));

        table_teachers.setItems(oblist1);

        //////// END ////////

        //////////////// Drag users data into the table ////////////////


        try {
            DataBaseConnection connectNow = new DataBaseConnection();
            Connection connectDB = connectNow.getConnection();

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM students");
            ResultSet rs1 = connectDB.createStatement().executeQuery("SELECT * FROM teachers");

            while (rs.next()){
                if (rs1.next()){
                    oblist2.add(new ModelTable2(rs.getString("sname"),
                        rs1.getString("tname")));
                } else{
                    oblist2.add(new ModelTable2(rs.getString("sname"),
                            null));
                }
            }

        } catch (SQLException ex){
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_ssname.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_ttname.setCellValueFactory(new PropertyValueFactory<>("name1"));

        table_users.setItems(oblist2);


        //////// END ////////


        //////////////// Drag data for fees into the table ////////////////


        try {
            DataBaseConnection connectNow = new DataBaseConnection();
            Connection connectDB = connectNow.getConnection();

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM students");

            while (rs.next()){
                oblist3.add(new ModelTable3(rs.getString("id"),
                        rs.getString("sname"),
                        rs.getString("scourse"),
                        rs.getString("fee")));
            }

        } catch (SQLException ex){
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_fid.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_fname.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_fcourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        col_fee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        table_fee.setItems(oblist3);

        //////// END ////////

    }


    @FXML
    public void onTableItemSelect(){
        index = table_fee.getSelectionModel().getSelectedIndex();

        if(index <= -1){
            return;
        }
        else{
            txtFid.setText(col_fid.getCellData(index).toString());
            txtFee.setText(col_fee.getCellData(index).toString());
        }
    }

    @FXML
    public void onUpdateFee(ActionEvent event){
        try {
            index = table_fee.getSelectionModel().getSelectedIndex();
            DataBaseConnection connectNow = new DataBaseConnection();
            Connection con = connectNow.getConnection();
            String fee = txtFee.getText();
            String id = txtFid.getText();

            String sql="update students set fee= '"+fee+"' where id='"+id+"' ";
            pst = con.prepareStatement(sql);
            pst.execute();


        }catch (Exception e){
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @FXML
    public void handleClicks(ActionEvent event){
        if (event.getSource()==btnStudents){
            lblStatus.setText("Students");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(255,227,74),CornerRadii.EMPTY, Insets.EMPTY)));
            pnBlank.toFront();
            pnStudents.toFront();
        }
        else if (event.getSource()==btnTeachers){
            lblStatus.setText("Teachers");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(127,141,218),CornerRadii.EMPTY, Insets.EMPTY)));
            pnBlank.toFront();
            pnTeachers.toFront();
        }
        else if (event.getSource()==btnFees){
            lblStatus.setText("Fees");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(129,152,48),CornerRadii.EMPTY, Insets.EMPTY)));
            pnBlank.toFront();
            pnFees.toFront();
        }
        else if (event.getSource()==btnUsers){
            lblStatus.setText("Users");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(156,183,209),CornerRadii.EMPTY, Insets.EMPTY)));
            pnBlank.toFront();
            pnUsers.toFront();
        }
        else if (event.getSource()==btnSettings){
            lblStatus.setText("Settings");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(215,219,221),CornerRadii.EMPTY, Insets.EMPTY)));
            pnBlank.toFront();
            pnSettings.toFront();
        }
    }
}
