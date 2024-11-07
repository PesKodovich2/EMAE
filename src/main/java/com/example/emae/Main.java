package com.example.emae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Установка иконки приложения
        stage.getIcons().add(new Image("/photo/logo.png"));
        // Запрет изменения размеров окна
        stage.setResizable(false);
        // Загрузка FXML-файла для интерфейса входа
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        // Создание сцены и установка размеров окна
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        // Установка заголовка окна
        stage.setTitle("Contemporary Oasis Hotel");
        // Установка сцены на окно
        stage.setScene(scene);
        // Отображение окна
        stage.show();
    }
    public static void main(String[] args) {
        // Запуск приложения
        launch(args);
    }
}
