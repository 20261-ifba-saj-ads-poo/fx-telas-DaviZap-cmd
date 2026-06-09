package br.edu.ifba.saj.ads.poo;

import br.edu.ifba.saj.ads.poo.data.HotelData;
import br.edu.ifba.saj.ads.poo.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import java.time.LocalDate;

public class ReservaController {

    @FXML private ChoiceBox<Hotel> slHotel;
    @FXML private ChoiceBox<Quarto> slQuarto;
    @FXML private TextField txNomeCliente;
    @FXML private TextField txCpfCliente;
    @FXML private DatePicker dtEntrada;
    @FXML private DatePicker dtSaida;

    private Hotel hotelSelecionado;

    @FXML
    private void initialize() {
        slHotel.getItems().addAll(HotelData.hoteis);

        slHotel.setConverter(new StringConverter<>() {
            @Override public String toString(Hotel h) {
                return h == null ? "" : h.getNome();
            }
            @Override public Hotel fromString(String s) { return null; }
        });

        slQuarto.setConverter(new StringConverter<>() {
            @Override public String toString(Quarto q) {
                return q == null ? "" : q.getNumero() + " - " + q.getCategoria()
                    + " (R$" + q.getCategoria().getValorDiaria() + "/noite)";
            }
            @Override public Quarto fromString(String s) { return null; }
        });

        slHotel.getSelectionModel().selectedItemProperty()
            .addListener((obs, old, novo) -> {
                if (novo != null) {
                    hotelSelecionado = novo;
                    slQuarto.getItems().clear();
                    slQuarto.getItems().addAll(novo.getQuartos());
                }
            });
    }

    @FXML
    void salvar(ActionEvent event) {
        Quarto quarto = slQuarto.getValue();
        LocalDate entrada = dtEntrada.getValue();
        LocalDate saida = dtSaida.getValue();

        if (quarto == null || entrada == null || saida == null
         || txNomeCliente.getText().isEmpty() || txCpfCliente.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,
                "Preencha todos os campos!").showAndWait();
            return;
        }

        if (!saida.isAfter(entrada)) {
            new Alert(Alert.AlertType.ERROR,
                "Data de saída deve ser depois da entrada!").showAndWait();
            return;
        }

        Cliente cliente = new Cliente(
            txNomeCliente.getText(),
            txCpfCliente.getText(),
            LocalDate.of(1990, 1, 1)
        );

        Reserva reserva = quarto.reservar(cliente, entrada, saida);

        if (reserva != null) {
            new Alert(Alert.AlertType.INFORMATION,
                String.format("Reserva feita!\nQuarto: %s\nCliente: %s\nEntrada: %s\nSaída: %s\nTotal: R$ %.2f",
                    quarto.getNumero(),
                    cliente.getNome(),
                    entrada,
                    saida,
                    reserva.getValorTotal())).showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR,
                "Quarto indisponível nessas datas!").showAndWait();
        }
    }
}
