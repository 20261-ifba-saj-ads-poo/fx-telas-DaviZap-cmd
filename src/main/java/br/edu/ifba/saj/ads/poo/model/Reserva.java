package br.edu.ifba.saj.ads.poo.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Quarto quarto;
    private Cliente cliente;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public Reserva(Quarto quarto, LocalDate dataEntrada, LocalDate dataSaida) {
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public float getValorTotal() {
        long noites = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
        return noites * quarto.getCategoria().getValorDiaria();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quarto getQuarto() { return quarto; }
    public Cliente getCliente() { return cliente; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public LocalDate getDataSaida() { return dataSaida; }

    @Override
    public String toString() {
        return String.format("Reserva [quarto=%s, entrada=%s, saida=%s, total=R$%.2f]",
            quarto.getNumero(), dataEntrada, dataSaida, getValorTotal());
    }
}
