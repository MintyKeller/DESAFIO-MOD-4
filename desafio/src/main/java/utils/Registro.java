package utils;

public class Registro {
    private String nome;
    private String piloto;
    private int pontuacao;

    public Registro(String nome, String piloto, int pontuacao) {
        this.nome = nome;
        this.piloto = piloto;
        this.pontuacao = pontuacao;
    }

    public String getNome() { return nome; }
    public String getPiloto() { return piloto; }
    public int getPontuacao() { return pontuacao; }
}