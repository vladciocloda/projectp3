package com.example.projectp3;


import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TabPane tabPaneLogin;
    @FXML
    private Tab tabAdmin;
    @FXML
    private Tab tabClient;
    @FXML
    private Tab tabUser;
    @FXML
    private Pane slidingPane;
    @FXML
    private Label lblAdmin;
    @FXML
    private Label lblStatus;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField txtaname;
    @FXML
    private PasswordField txtapass;
    @FXML
    private TextField txttname;
    @FXML
    private PasswordField txttpass;
    @FXML
    private TextField txtsname;
    @FXML
    private PasswordField txtspass;
    @FXML
    private Label lblError;
    @FXML
    private Label lblError1;
    @FXML
    private Label lblError2;
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnSignIn1;
    @FXML
    private Button btnSignIn2;
    @FXML
    private Label lblClose;


    @FXML
    private void handleButtonAction(MouseEvent event){
      if (event.getSource() == lblClose) {
          System.exit(0);
      }
   }

   // sign in for admins
    public void btnSignInOnAction(){
        if (!txtaname.getText().isBlank() && !txtapass.getText().isBlank()){
            validateLogin();
        } else {
            lblError.setText("Please enter name and password.");
        }
    }

    // sign in for students
    public void btnSignInOnAction1(){
        if (!txttname.getText().isBlank() && !txttpass.getText().isBlank()){
            validateLogin1();
        }
        else {
            lblError1.setText("Please enter name and password!");
        }
    }

    // sign in for teachers
    public void btnSignInOnAction2(){
        if (!txtsname.getText().isBlank() && !txtspass.getText().isBlank()){
            validateLogin2();
        }
        else {
            lblError2.setText("Please enter name and password!");
        }
    }

    // admins
    public void validateLogin(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM admins WHERE aname = '" + txtaname.getText() + "' AND apass = '" + txtapass.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
          //          lblError.setText("Welcome!");
                    Stage stage = (Stage) btnSignIn.getScene().getWindow();
                    stage.close();

                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminPage.fxml")));

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.setTitle("Admin Panel");
                    stage.show();

                } else {
                    lblError.setText("Invalid login. Please try again.");
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    // teachers
    public void validateLogin1(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM teachers WHERE tname = '" + txttname.getText() + "' AND tpass = '" + txttpass.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    //          lblError.setText("Welcome!");
                    Stage stage = (Stage) btnSignIn1.getScene().getWindow();
                    stage.close();

                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TeacherPage.fxml")));

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.setTitle("Admin Panel");
                    stage.show();

                } else {
                    lblError.setText("Invalid login. Please try again.");
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
    // students
    public void validateLogin2(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM students WHERE sname = '" + txtsname.getText() + "' AND spass = '" + txtspass.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    //          lblError.setText("Welcome!");
                    Stage stage = (Stage) btnSignIn2.getScene().getWindow();
                    stage.close();

                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentPage.fxml")));

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.setTitle("Student Panel");
                    stage.show();

                } else {
                    lblError.setText("Invalid login. Please try again.");
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TO DO
    }



    @FXML
    private void openAdminTab(){
        TranslateTransition toLeftTransition=new TranslateTransition(new Duration(500), lblStatus);
        toLeftTransition.setToX(slidingPane.getTranslateX());
        toLeftTransition.play();
        toLeftTransition.setOnFinished((ActionEvent event2) -> lblStatus.setText("ADMINISTRATOR"));
        tabPaneLogin.getSelectionModel().select(tabAdmin);
    }

    @FXML
    private void openUserTab(){
        TranslateTransition toRightAnimation=new TranslateTransition(new Duration(500), lblStatus);
        //ugly fix
        // toRightAnimation.setToX(slidingPane.getTranslateX()+(slidingPane.getPrefWidth()-lblStatus.getPrefWidth()));
        toRightAnimation.setToX(155);
        toRightAnimation.play();
        toRightAnimation.setOnFinished((ActionEvent event1) -> lblStatus.setText("TEACHER"));
        tabPaneLogin.getSelectionModel().select(tabUser);
    }

    @FXML
    private void openClientTab(){
        TranslateTransition toRightAnimation=new TranslateTransition(new Duration(500), lblStatus);
        //ugly fix
        // toRightAnimation.setToX(slidingPane.getTranslateX()+(slidingPane.getPrefWidth()-lblStatus.getPrefWidth()));
        toRightAnimation.setToX(255);
        toRightAnimation.play();
        toRightAnimation.setOnFinished((ActionEvent event1) -> lblStatus.setText("STUDENT"));
        tabPaneLogin.getSelectionModel().select(tabClient);
    }

    @FXML
    private void openCreateAccountScene() throws IOException {
        Parent root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateAccountView.fxml")));
        Scene loginScene=lblAdmin.getScene();
        root.translateYProperty().set(loginScene.getHeight());
        rootPane.getChildren().add(root);
        Timeline timeline=new Timeline();
        KeyValue keyValue=new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame keyFrame=new KeyFrame(Duration.seconds(2),keyValue);
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
        timeline.setOnFinished((ActionEvent event2) -> rootPane.getChildren().remove(anchorPane));
    }



}

