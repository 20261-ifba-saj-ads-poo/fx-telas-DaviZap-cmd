package br.edu.ifba.saj.ads.poo.model;

public enum CategoriaQuarto {
    STANDARD, LUXO, SUITE;

    public float getValorDiaria() {
        return switch (this) {
            case STANDARD -> 150.00f;
            case LUXO     -> 300.00f;
            case SUITE    -> 600.00f;
        };
    }
}
