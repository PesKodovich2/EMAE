package com.example.emae;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public class AdminController {
    @FXML
    private Label idLable;

    @FXML
    private TableView<BookingData> idTableZ;

    @FXML
    private TableColumn<BookingData, Integer> idComNum;

    @FXML
    private TableColumn<BookingData, String> idColName;

    @FXML
    private TableColumn<BookingData, Date> IdColDateOne;

    @FXML
    private TableColumn<BookingData, Date> idColDateDva;

    @FXML
    private TableColumn<BookingData, Double> idColPrice;

    @FXML
    private TableColumn<BookingData, Integer> idColStatys;

    @FXML
    private TableColumn<BookingData, String> idColKlient;

    @FXML
    private TableColumn<BookingData, String> idColYsl;

    @FXML
    private Button idVon;
    @FXML
    private Button refreshButton;


    private static final String JDBC_URL = "jdbc:mysql://localhost:13306/kyrsach";
    private static final String JDBC_USER = "javafxTest";
    private static final String JDBC_PASSWORD = "changeme";

    @FXML
    private void initialize() {
        updateTimeGreeting();
        populateTable();
        setupRefreshButton();

        updateTimeGreeting();
        populateTable();

        idColStatys.setSortType(TableColumn.SortType.DESCENDING);
        idTableZ.getSortOrder().add(idColStatys);


        ContextMenu contextMenu = new ContextMenu();

        MenuItem editButton = new MenuItem("Изменить");
        MenuItem addOrderButton = new MenuItem("Добавить заказ");
        MenuItem closeOrderButton = new MenuItem("Закрыть заказ");

        editButton.setOnAction(e -> {
            BookingData selectedItem = idTableZ.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                if (selectedItem.getStatus() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Ошибка");
                    alert.setContentText("Этот заказ закрыт!");
                    alert.showAndWait();
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("izmen.fxml"));
                        Parent root = loader.load();
                        IzmenController controller = loader.getController();
                        controller.setBookingData(selectedItem);
                        controller.initialize();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        addOrderButton.setOnAction(e -> {
            BookingData selectedItem = idTableZ.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                if (selectedItem.getStatus() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Ошибка");
                    alert.setContentText("Этот заказ закрыт!");
                    alert.showAndWait();
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("dob.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        closeOrderButton.setOnAction(e -> {
            BookingData selectedItem = idTableZ.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                if (selectedItem.getStatus() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Ошибка");
                    alert.setContentText("Этот заказ закрыт!");
                    alert.showAndWait();
                } else {
                    selectedItem.setStatus(0);

                    try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                        String query = "UPDATE Booking SET status = 0 WHERE idB = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, selectedItem.getIdB());
                        preparedStatement.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Ошибка");
                alert.setContentText("Необходимо выбрать заказ для закрытия.");
                alert.showAndWait();
            }
        });

        contextMenu.getItems().addAll(editButton, addOrderButton, closeOrderButton);

        idTableZ.setContextMenu(contextMenu);
    }

    private void updateTimeGreeting() {
        java.time.LocalTime currentTime = java.time.LocalTime.now();

        if (currentTime.isAfter(java.time.LocalTime.of(4, 0)) && currentTime.isBefore(java.time.LocalTime.of(10, 0))) {
            idLable.setText("Доброе утро!");
        } else if (currentTime.isAfter(java.time.LocalTime.of(10, 0)) && currentTime.isBefore(java.time.LocalTime.of(16, 0))) {
            idLable.setText("Добрый день!");
        } else {
            idLable.setText("Добрый вечер!");
        }
    }
    private void setupRefreshButton() {
        refreshButton.setOnAction(e -> {
            populateTable();
        });
    }

    private void populateTable() {
        ObservableList<BookingData> bookingDataList = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "SELECT Booking.idB, Room.nameR, Booking.dz, Booking.dv, Booking.fullPrice, Booking.status, Klients.nameK, Klients.surnameK, GROUP_CONCAT(Boo_Ser.idSer) AS idSerList " +
                    "FROM Booking " +
                    "JOIN Room ON Booking.Room_idR = Room.idR " +
                    "JOIN Klients ON Booking.Klient_idK = Klients.idK " +
                    "LEFT JOIN Boo_Ser ON Booking.idB = Boo_Ser.idBoo " +
                    "GROUP BY Booking.idB";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int idB = resultSet.getInt("idB");
                    String nameR = resultSet.getString("NameR");
                    Date dz = resultSet.getDate("dz");
                    Date dv = resultSet.getDate("dv");
                    double fullPrice = resultSet.getDouble("fullPrice");
                    int status = resultSet.getInt("status");
                    String nameK = resultSet.getString("nameK");
                    String surnameK = resultSet.getString("surnameK");
                    String idSerList = resultSet.getString("idSerList");

                    BookingData bookingData = new BookingData(idB, nameR, dz, dv, fullPrice, new SimpleIntegerProperty(status), nameK, surnameK, idSerList);
                    bookingDataList.add(bookingData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idTableZ.setItems(bookingDataList);

        IdColDateOne.setCellValueFactory(new PropertyValueFactory<>("dz"));
        idColDateDva.setCellValueFactory(new PropertyValueFactory<>("dv"));
        idComNum.setCellValueFactory(new PropertyValueFactory<>("idB"));
        idColName.setCellValueFactory(new PropertyValueFactory<>("nameR"));
        idColKlient.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        idColPrice.setCellValueFactory(new PropertyValueFactory<>("fullPrice"));
        idColYsl.setCellValueFactory(new PropertyValueFactory<>("idSerList"));
        idColStatys.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) idVon.getScene().getWindow();
        stage.close();
    }
}