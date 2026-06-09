package br.edu.ifba.saj.ads.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Quarto {
    private String numero;
    private CategoriaQuarto categoria;
    private Hotel hotel;
    private List<Reserva> reservas;

    public Quarto(String numero, CategoriaQuarto categoria) {
        this.numero = numero;
        this.categoria = categoria;
        this.reservas = new ArrayList<>();
    }

    public Reserva reservar(Cliente cliente, LocalDate entrada, LocalDate saida) {
        if (!isDisponivel(entrada, saida)) return null;

        Reserva reserva = new Reserva(this, entrada, saida);
        reserva.setCliente(cliente);
        cliente.addReserva(reserva);
        reservas.add(reserva);
        return reserva;
    }

    public boolean isDisponivel(LocalDate entrada, LocalDate saida) {
        for (Reserva r : reservas) {
            // Checa sobreposição de datas
            boolean sobrepos = entrada.isBefore(r.getDataSaida())
                            && saida.isAfter(r.getDataEntrada());
            if (sobrepos) return false;
        }
        return true;
    }

    public void setHotel(Hotel hotel) { this.hotel = hotel; }
    public Hotel getHotel() { return hotel; }
    public String getNumero() { return numero; }
    public CategoriaQuarto getCategoria() { return categoria; }
    public List<Reserva> getReservas() { return List.copyOf(reservas); }

    @Override
    public String toString() {
        return String.format("Quarto %s (%s)", numero, categoria);
    }

    @Override
    public int hashCode() {
        return numero.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Quarto q) {
            return q.getNumero().equals(numero);
        }
        return false;
    }
}
