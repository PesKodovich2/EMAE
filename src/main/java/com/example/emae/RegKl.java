package com.example.emae;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegKl {
    public static int idK;
    @FXML
    private TextField tel;
    @FXML
    private TextField parol;
    @FXML
    private Button vhod;
    @FXML
    private Button reg;
    @FXML
    private Label messageLabel;

    private static final String url = "jdbc:mysql://localhost:13306/kyrsach";
    private static final String user = "javafxTest";
    private static final String password = "changeme";

    private Connection connection;

    public RegKl() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        vhod.setOnAction(event -> {
            String phoneNumber = tel.getText();
            String password = parol.getText();
            boolean isAuthorized = checkAuthorization(phoneNumber, password);

            if (isAuthorized) {
                openOrderWindow();
            } else {
                messageLabel.setText("Неправильно введены номер телефона или пароль!");
            }
        });
        reg.setOnAction(event -> {
            openRegistrationWindow();
        });
    }

    private boolean checkAuthorization(String phoneNumber, String password) {
        try {
            String selectQuery = "SELECT * FROM Klients WHERE numTel = ? AND parolK = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, phoneNumber);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idK = resultSet.getInt("idK"); // Получить idK
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void openOrderWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ordeer.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image("/photo/logo.png"));
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) vhod.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openRegistrationWindow() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("zareg.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image("/photo/logo.png"));
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) reg.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
