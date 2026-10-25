package entities;

public class Inimigo extends Personagem {
    private String ascii;
    private int[] pAtacarDefender1;
    private int[] pAtacarDefender2;
    private String descricao;
    private String estilo;

    public Inimigo(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                   String ascii, int[] pAtacarDefender1, int[] pAtacarDefender2,
                   String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima);
        this.ascii = ascii;
        this.pAtacarDefender1 = pAtacarDefender1;
        this.pAtacarDefender2 = pAtacarDefender2;
        this.descricao = descricao;
        this.estilo = estilo;

    }

    public String getAscii() {
        return ascii;
    }

    public int[] getpAtacarDefender1() {
        return pAtacarDefender1;
    }

    public int[] getpAtacarDefender2() {
        return pAtacarDefender2;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEstilo() {
        return estilo;
    }
}
