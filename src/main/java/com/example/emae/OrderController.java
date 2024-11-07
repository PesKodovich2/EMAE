package com.example.emae;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.time.temporal.ChronoUnit;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private TextField tel;
    @FXML
    private TextField parol;
    @FXML
    private TableView<Room> roomTable;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private TableColumn<Room, ImageView> photoColumn;
    @FXML
    private TableColumn<Room, String> descriptionColumn;
    @FXML
    private TableColumn<Room, Double> priceColumn;
    @FXML
    private Label label;
    @FXML
    private DatePicker date1;
    @FXML
    private DatePicker date2;
    @FXML
    private TableView<Servicee> tabYsl;
    @FXML
    private TableColumn<Servicee, Integer> colID;
    @FXML
    private TableColumn<Servicee, String> colYsl;
    @FXML
    private TableColumn<Servicee, Double> colPrice;
    @FXML
    private Label price;
    @FXML
    private Button addYsl;
    @FXML
    private Button bookButton;
    @FXML
    private TableColumn<Room, String> nameColumn;
    private Stage stage;
    private ObservableList<Servicee> uslugi = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameR"));

        photoColumn.setCellValueFactory(cellData -> {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            Room room = cellData.getValue();
            Image image = new Image(getClass().getResourceAsStream("/photo/" + room.getPhoto()));
            imageView.setImage(image);
            return new SimpleObjectProperty<>(imageView);
        });

        roomTable.setContextMenu(contextMenu);

        colID.setCellValueFactory(new PropertyValueFactory<>("idS"));
        colYsl.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tabYsl.setItems(uslugi);
        tabYsl.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        roomTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                price.setText(String.format("%.2f", newSelection.getPrice()));
            }
        });
    }

    private ObservableList<Room> getRoomData(int type) {
        ObservableList<Room> data = FXCollections.observableArrayList();

        String url = "jdbc:mysql://localhost:13306/kyrsach";
        String user = "javafxTest";
        String password = "changeme";

        uslugi.clear();

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT idS, nameS, priceS FROM Service");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("nameS");
                double price = resultSet.getDouble("priceS");
                int idS = resultSet.getInt("idS");
                uslugi.add(new Servicee(name, price, idS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            label.setText("Произошла ошибка при получении данных из базы.");
        }

        LocalDate dateZ = date1.getValue();
        LocalDate dateV = date2.getValue();

// Проверяем, что dateZ не меньше сегодняшней даты
        if (dateZ.isBefore(LocalDate.now())) {
            // Если dateZ меньше сегодняшней даты, выводим ошибку
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Введите корректные даты");
            alert.setContentText("Дата начала не может быть меньше сегодняшней даты");
            alert.showAndWait();
            return data;
        }


        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT idR, photo, descriptionR, priceR, nameR FROM Room WHERE type = ? AND ((dateZ > ? AND dateZ > ?) OR (dateV < ? AND dateV < ?) OR (dateZ IS NULL AND dateV IS NULL))")) {

            statement.setInt(1, type);
            statement.setDate(2, java.sql.Date.valueOf(dateV));
            statement.setDate(3, java.sql.Date.valueOf(dateZ));
            statement.setDate(4, java.sql.Date.valueOf(dateZ));
            statement.setDate(5, java.sql.Date.valueOf(dateV));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nameR = resultSet.getString("nameR");
                int idR = resultSet.getInt("idR");
                String photo = resultSet.getString("photo");
                String description = resultSet.getString("descriptionR");
                double price = resultSet.getDouble("priceR");
                data.add(new Room(photo, description, price, idR, nameR));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            label.setText("Произошла ошибка при получении данных из базы.");
        }
        return data;
    }

    @FXML
    private void showRooms(int type) {
        if (areDatesValid()) {
            roomTable.setItems(getRoomData(type));
        }
    }

    @FXML
    private void showSingleRooms() {
        showRooms(1);
    }
    @FXML
    private void showDoubleRooms() {
        showRooms(2);
    }
    @FXML
    private void showTripleRooms() {
        showRooms(3);
    }
    @FXML
    private void openZakazWindow() {
        updatePrice();
    }
    @FXML
    private void addYsl() {
        updatePrice();
    }

    private void updatePrice() {
        Room selectedRoom = roomTable.getSelectionModel().getSelectedItem();
        if (selectedRoom != null && areDatesValid()) {
            LocalDate date1Value = date1.getValue();
            LocalDate date2Value = date2.getValue();

            long daysDifference = ChronoUnit.DAYS.between(date1Value, date2Value) + 1;

            double totalPrice = selectedRoom.getPrice() * daysDifference;
            for (Servicee service : tabYsl.getSelectionModel().getSelectedItems()) {
                totalPrice += service.getPrice();
            }
            price.setText(String.format("%.2f", totalPrice));
        }
    }
    @FXML
    private void bron() {
        Room selectedRoom = roomTable.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            double totalPrice = Double.parseDouble(price.getText().replaceAll(",", "."));
            LocalDate dateZ = date1.getValue();
            LocalDate dateV = date2.getValue();
            int Klient_idK = RegKl.idK;
            int Room_IdR = selectedRoom.getIdR();

            String url = "jdbc:mysql://localhost:3306/kyrsach";
            String user = "root";
            String password = "";

            try (Connection connection = DriverManager.getConnection(url, user, password);
                 PreparedStatement checkRoomStatement = connection.prepareStatement("SELECT * FROM Room WHERE idR = ?");
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO Booking (idB, dz, dv, fullPrice, Klient_idK, Room_IdR, status) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

                checkRoomStatement.setInt(1, Room_IdR);
                ResultSet checkRoomResultSet = checkRoomStatement.executeQuery();

                if (checkRoomResultSet.next()) {
                    statement.setInt(1, 0);
                    statement.setDate(2, java.sql.Date.valueOf(dateZ));
                    statement.setDate(3, java.sql.Date.valueOf(dateV));
                    statement.setDouble(4, totalPrice);
                    statement.setInt(5, Klient_idK);
                    statement.setInt(6, Room_IdR);
                    statement.setInt(7, 1);
                    statement.executeUpdate();

                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int newIdB = generatedKeys.getInt(1);

                        try (PreparedStatement statement2 = connection.prepareStatement("INSERT INTO Boo_Ser (idBS, idBoo, idSer) VALUES (?, ?, ?)")) {
                            for (Servicee service : tabYsl.getSelectionModel().getSelectedItems()) {
                                statement2.setInt(1, 0); // idBS - автоинкрементная
                                statement2.setInt(2, newIdB);
                                statement2.setInt(3, service.getIdS());
                                statement2.addBatch();
                            }
                            statement2.executeBatch();
                            // Если всё прошло успешно, отобразить окно "Комната забронирована!"
                            stage = (Stage) tabYsl.getScene().getWindow();
                            showConfirmationAlert("Комната забронирована!", stage);
                        }
                    }
                } else {
                    // Обрабатываем ситуацию, когда комната не существует
                    label.setText("Ошибка: комната не существует.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                label.setText("Произошла ошибка при добавлении заказа.");
            }
        }
    }

    private void showConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    private void showConfirmationAlert(String message, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(message);
        alert.showAndWait();

        stage.close();
    }



    private boolean areDatesValid() {
        LocalDate date1Value = date1.getValue();
        LocalDate date2Value = date2.getValue();

        if (date1Value == null || date2Value == null || date2Value.isBefore(date1Value)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Введите корректные даты");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}