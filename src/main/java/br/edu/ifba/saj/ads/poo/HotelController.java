package br.edu.ifba.saj.ads.poo;

import java.util.Objects;
import br.edu.ifba.saj.ads.poo.data.HotelData;
import br.edu.ifba.saj.ads.poo.model.Hotel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HotelController {

    @FXML private TextField txNome;
    @FXML private TextField txEndereco;
    @FXML private TableColumn<Hotel, String> clmNome;
    @FXML private TableColumn<Hotel, String> clmEndereco;
    @FXML private TableView<Hotel> tbHoteis;

    @FXML
    private void initialize() {
        clmNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clmEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        loadList();
        Platform.runLater(() -> txNome.requestFocus());
    }

    public void loadList() {
        tbHoteis.setItems(FXCollections.observableList(HotelData.hoteis));
    }

    @FXML
    void salvar(ActionEvent event) {
        if (Objects.nonNull(txNome.getText()) && !txNome.getText().isEmpty()
         && Objects.nonNull(txEndereco.getText()) && !txEndereco.getText().isEmpty()) {

            Hotel hotel = new Hotel(txNome.getText(), txEndereco.getText());
            HotelData.hoteis.add(hotel);
            new Alert(Alert.AlertType.INFORMATION,
                "Hotel " + hotel.getNome() + " cadastrado!").showAndWait();
            txNome.clear();
            txEndereco.clear();
        } else {
            new Alert(Alert.AlertType.ERROR,
                "Nome e endereço são obrigatórios!").showAndWait();
        }
        loadList();
    }
}
