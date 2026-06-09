package br.edu.ifba.saj.ads.poo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class IndexController {

    @FXML
    private BorderPane pane;

    @FXML
    public void abrirCadastrarHotel(ActionEvent event) {
        try {
            pane.setCenter(FXMLLoader.load(getClass().getResource("Hotel.fxml")));
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void abrirCadastrarQuarto(ActionEvent event) {
        try {
            pane.setCenter(FXMLLoader.load(getClass().getResource("Quarto.fxml")));
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void abrirReservar(ActionEvent event) {
        try {
            pane.setCenter(FXMLLoader.load(getClass().getResource("Reserva.fxml")));
        } catch (Exception e) { e.printStackTrace(); }
    }
}
