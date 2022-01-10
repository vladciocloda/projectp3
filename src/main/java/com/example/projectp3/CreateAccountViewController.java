package com.example.projectp3;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateAccountViewController extends StudentPageController implements Initializable {

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lblLogin;

    @FXML
    private PasswordField txtPas;

    @FXML
    private TextField txtUs;

    @FXML
    private Label lblClose;


    @FXML
    private AnchorPane anchorpane;

    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    @FXML
    private void openLoginScene() throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Scene accountScene=lblLogin.getScene();
        root.translateYProperty().set(accountScene.getHeight());

        StackPane rootPane=(StackPane) accountScene.getRoot();


        rootPane.getChildren().add(root);
        Timeline timeline=new Timeline();
        KeyValue keyValue=new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame keyFrame=new KeyFrame(Duration.seconds(2),keyValue);
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
        timeline.setOnFinished((ActionEvent event2) -> rootPane.getChildren().remove(anchorpane));
    }

    PreparedStatement pst;
    public void createAccount(ActionEvent event){
        String us = txtUs.getText();
        String pas = txtPas.getText();
        try {
            DataBaseConnection connectNow = new DataBaseConnection();
            Connection con = connectNow.getConnection();


            pst = con.prepareStatement("insert into students(sname,scourse,fee,spass)values(?,'Course 1',0,?)");
            pst.setString(1, us);
            pst.setString(2, pas);
            pst.executeUpdate();
        }   catch (SQLException ex){
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void handleButtonAction(MouseEvent event){
        if (event.getSource() == lblClose) {
            System.exit(0);
        }
    }
}
