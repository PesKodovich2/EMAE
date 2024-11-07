package com.example.emae;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class Zareg {
    @FXML
    private TextField imya;
    @FXML
    private TextField fam;
    @FXML
    private TextField otch;
    @FXML
    private TextField pass;
    @FXML
    private TextField tel;
    @FXML
    private TextField parol;
    @FXML
    private Label label;
    @FXML
    private Button buttReg;
    @FXML
    private void addUserToDatabase() {
        String url = "jdbc:mysql://localhost:13306/kyrsach";
        String user = "javafxTest";
        String password = "changeme";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             CallableStatement statement = connection.prepareCall("{call add_user(?, ?, ?, ?, ?, ?, ?)}")) {
            statement.setNull(1, Types.INTEGER);
            statement.setString(2, imya.getText());
            statement.setString(3, fam.getText());
            statement.setString(4, otch.getText());
            statement.setString(5, tel.getText());
            statement.setString(6, pass.getText());
            statement.setString(7, parol.getText());
            statement.execute();

            Stage currentStage = (Stage) buttReg.getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("regKl.fxml"));
            Parent root = loader.load();
            Stage regKlStage = new Stage();
            regKlStage.getIcons().add(new Image("/photo/logo.png"));
            regKlStage.setResizable(false);
            regKlStage.setScene(new Scene(root));
            regKlStage.show();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            label.setText("Произошла ошибка при добавлении пользователя в базу данных.");
        }
    }}