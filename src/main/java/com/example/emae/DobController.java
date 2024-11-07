package com.example.emae;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DobController implements Initializable {

    @FXML
    private TableView<Klient> idTableKl;
    @FXML
    private TableColumn<Klient, Integer> idID;
    @FXML
    private TableColumn<Klient, String> idNameKl;
    @FXML
    private TableColumn<Klient, String> idFamKl;
    @FXML
    private Button IdDob;
    @FXML
    private Button idOtm;
    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectToDatabase();

        idID.setCellValueFactory(new PropertyValueFactory<>("idK"));
        idNameKl.setCellValueFactory(new PropertyValueFactory<>("nameK"));
        idFamKl.setCellValueFactory(new PropertyValueFactory<>("surnameK"));
        loadData();

        idOtm.setOnAction(e -> {
            ((Node) (e.getSource())).getScene().getWindow().hide();
        });

        IdDob.setOnAction(e -> {
            Klient selectedKlient = idTableKl.getSelectionModel().getSelectedItem();
            if (selectedKlient != null) {
                RegKl.idK = selectedKlient.getIdK();

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ordeer.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/kyrsach", "javafxTest", "changeme");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadData() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Klients");
            while (resultSet.next()) {
                idTableKl.getItems().add(new Klient(resultSet.getInt("idK"), resultSet.getString("nameK"), resultSet.getString("surnameK")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static class Klient {
        private final Integer idK;
        private final String nameK;
        private final String surnameK;

        public Klient(Integer idK, String nameK, String surnameK) {
            this.idK = idK;
            this.nameK = nameK;
            this.surnameK = surnameK;
        }
        public Integer getIdK() {
            return idK;
        }
        public String getNameK() {
            return nameK;
        }
        public String getSurnameK() {
            return surnameK;
        }
    }
}


