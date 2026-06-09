package br.edu.ifba.saj.ads.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private LocalDate nascimento;
    private List<Reserva> reservas;

    public Cliente(String nome, String cpf, LocalDate nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.reservas = new ArrayList<>();
    }

    public void addReserva(Reserva reserva) { reservas.add(reserva); }
    public List<Reserva> getReservas() { return List.copyOf(reservas); }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public LocalDate getNascimento() { return nascimento; }

    @Override
    public String toString() { return nome + " (" + cpf + ")"; }
}
