package com.example.emae;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button clientButton;
    @FXML
    private Button sotrButton;
    public void openKlientWindow() throws IOException {
        // Создание нового окна
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/photo/logo.png"));
        stage.setResizable(false);
        // Загрузка FXML файла для окна клиента
        Parent root = FXMLLoader.load(getClass().getResource("regKl.fxml"));
        stage.setTitle("Окно авторизации.");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }
    // Метод для открытия окна сотрудника
    public void openSotrWindow() throws IOException{
        // Создание нового окна
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/photo/logo.png"));
        stage.setResizable(false);
        // Загрузка FXML файла для окна сотрудника
        Parent root = FXMLLoader.load(getClass().getResource("sotr.fxml"));
        stage.setTitle("Окно авторизации.");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }
}
