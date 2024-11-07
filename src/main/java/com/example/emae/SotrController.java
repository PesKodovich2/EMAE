package com.example.emae;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.*;

public class SotrController {
    @FXML
    private TextField logSotr; // Поле для ввода логина сотрудника
    @FXML
    private TextField parolSotr; // Поле для ввода пароля сотрудника
    @FXML
    private Button vhodSotr; // Кнопка для входа сотрудника
    @FXML
    private Label message; // Метка для вывода сообщений об ошибке или статусе

    // Поля для работы с базой данных
    private Connection connection; // Объект соединения с базой данных
    private PreparedStatement preparedStatement; // Подготовленное SQL-выражение
    // Метод инициализации контроллера
    @FXML
    void initialize() {
        connectToDatabase(); // Вызов метода для подключения к базе данных
    }
    // Обработчик события нажатия кнопки "Вход сотрудника"
    @FXML
    void vhodSotrClicked(ActionEvent event) {
        String login = logSotr.getText(); // Получение введенного логина
        String password = parolSotr.getText(); // Получение введенного пароля

        // Попытка аутентификации пользователя по введенным данным
        if (authenticateUser(login, password)) { // Если аутентификация прошла успешно
            openAdminWindow(event); // Открыть окно администратора
        } else { // Если аутентификация не удалась
            message.setText("Неверный логин или пароль!"); // Вывести сообщение об ошибке
        }
    }
    // Метод для подключения к базе данных
    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/kyrsach"; // URL базы данных
            String user = "root"; // Имя пользователя базы данных
            String password = ""; // Пароль пользователя

            // Установка соединения с базой данных
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace(); // Вывод сообщения об ошибке подключения
        }
    }
    // Метод для аутентификации пользователя в базе данных
    private boolean authenticateUser(String login, String password) {
        try {
            String query = "SELECT * FROM Admin WHERE Login = ? AND Password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login); // Установка параметра логина
            preparedStatement.setString(2, password); // Установка параметра пароля

            ResultSet resultSet = preparedStatement.executeQuery(); // Выполнение запроса

            return resultSet.next(); // Возвращение результата аутентификации
        } catch (SQLException e) {
            e.printStackTrace(); // Вывод сообщения об ошибке SQL
            return false; // Возвращение false в случае ошибки
        }
    }
    // Метод для открытия окна администратора
    private void openAdminWindow(ActionEvent event) {
        try {
            // Загрузка разметки из файла admin.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root = loader.load(); // Загрузка корневого элемента из разметки

            // Создание и настройка нового окна
            Stage stage = new Stage();
            stage.setResizable(false); // Запрет изменения размеров окна
            stage.getIcons().add(new Image("/photo/logo.png")); // Установка иконки окна
            stage.setTitle("Admin Window"); // Установка заголовка окна
            stage.setScene(new Scene(root)); // Установка сцены в окне
            stage.show(); // Отображение окна

            // Закрытие текущего окна
            Stage currentStage = (Stage) vhodSotr.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace(); // Вывод сообщения об ошибке при открытии окна
        }
    }
}