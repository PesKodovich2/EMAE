package com.example.emae;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;

public class IzmenController {

    @FXML
    private DatePicker idDataVon;
    @FXML
    private Button idSoxr;
    @FXML
    private Button idOtmena;
    private BookingData bookingData;

    public void setBookingData(BookingData bookingData) {
        this.bookingData = bookingData;
    }
    @FXML
    public void initialize() {
        if (bookingData != null) {
            idDataVon.setValue(bookingData.getDv().toLocalDate());
        } else {
            idDataVon.setValue(null);
        }
        idOtmena.setOnAction(e -> {
            Stage stage = (Stage) idOtmena.getScene().getWindow();
            stage.close();
        });
    }
    @FXML
    public void handleSoxrAction(ActionEvent event) {
        System.out.println("handleSoxrAction method called");
        LocalDate newDate = idDataVon.getValue();
        System.out.println("New date: " + newDate);
        if (newDate.isBefore(bookingData.getDz().toLocalDate())) {
            System.out.println("New date is before dz date");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка");
            alert.setContentText("Введите корректную дату!");
            alert.showAndWait();
        } else {
            bookingData.setDv(Date.valueOf(newDate));
            System.out.println("BookingData object updated");
            BookingDataDAO dao = new BookingDataDAO();
            dao.updateBookingData(bookingData);
            Stage stage = (Stage) idSoxr.getScene().getWindow();
            stage.close();
        }
    }
}