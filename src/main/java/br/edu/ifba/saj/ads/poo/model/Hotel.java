package br.edu.ifba.saj.ads.poo.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nome;
    private String endereco;
    private List<Quarto> quartos;

    public Hotel(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.quartos = new ArrayList<>();
    }

    public void addQuarto(Quarto quarto) {
        if (!this.equals(quarto.getHotel())) {
            quarto.setHotel(this);
        }
        if (!quartos.contains(quarto)) {
            quartos.add(quarto);
        }
    }

    public List<Quarto> getQuartos() { return List.copyOf(quartos); }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }

    @Override
    public String toString() { return nome; }

    @Override
    public int hashCode() { return nome.hashCode(); }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Hotel h) {
            return h.getNome().equals(nome);
        }
        return false;
    }
}
