package br.edu.ifba.saj.ads.poo;

import br.edu.ifba.saj.ads.poo.data.HotelData;
import br.edu.ifba.saj.ads.poo.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

public class QuartoController {

    @FXML private ChoiceBox<Hotel> slHotel;
    @FXML private TextField txNumero;
    @FXML private ChoiceBox<CategoriaQuarto> slCategoria;

    private Hotel hotelSelecionado;

    @FXML
    private void initialize() {
        slHotel.getItems().addAll(HotelData.hoteis);
        slCategoria.getItems().addAll(CategoriaQuarto.values());

        slHotel.getSelectionModel().selectedItemProperty()
            .addListener((obs, old, novo) -> {
                if (novo != null) hotelSelecionado = novo;
            });

        slHotel.setConverter(new StringConverter<>() {
            @Override public String toString(Hotel h) {
                return h == null ? "" : h.getNome();
            }
            @Override public Hotel fromString(String s) { return null; }
        });
    }

    @FXML
    void salvar(ActionEvent event) {
        if (hotelSelecionado == null || txNumero.getText().isEmpty()
         || slCategoria.getValue() == null) {
            new Alert(Alert.AlertType.ERROR,
                "Preencha todos os campos!").showAndWait();
            return;
        }

        Quarto quarto = new Quarto(txNumero.getText(), slCategoria.getValue());
        hotelSelecionado.addQuarto(quarto);

        new Alert(Alert.AlertType.INFORMATION,
            String.format("Quarto %s (%s) adicionado ao hotel %s",
                quarto.getNumero(),
                quarto.getCategoria(),
                hotelSelecionado.getNome())).showAndWait();

        txNumero.clear();
    }
}
