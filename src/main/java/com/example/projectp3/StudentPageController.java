package com.example.projectp3;

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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentPageController implements Initializable {
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
    private TextField txtSearch;

    @FXML
    private AnchorPane adminPane;


    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb){
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
    }
    @FXML
    private void handleButtonAction(MouseEvent event){
        if (event.getSource() == lblClose) {
            System.exit(0);
        }
    }
    public void handleClicks(ActionEvent event){
        if (event.getSource()==btnStudents){
            lblStatus.setText("Students");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(255,227,74),CornerRadii.EMPTY, Insets.EMPTY)));
            pnBlank.toFront();
            pnStudents.toFront();
        }
        else if (event.getSource()==btnSettings){
            lblStatus.setText("Settings");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(215,219,221),CornerRadii.EMPTY, Insets.EMPTY)));
            pnBlank.toFront();
            pnSettings.toFront();
        }
    }
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
}
