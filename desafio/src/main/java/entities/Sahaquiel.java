package entities;

public class Sahaquiel extends Inimigo {

    private static final int RECUO = 8;
    private int turnos = 0;

    public Sahaquiel(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                     String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                     String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima, ascii,
                pAtacarDefender1, pAtacarDefender2, descricao, estilo);
    }

    //HABILIDADE ESPECIAL:
    // A cada 3 turnos ele se joga em cima de você, com dano enorme, mas leva um dano de recuo.
    @Override
    protected void agir(Piloto alvo) {
        turnos++;
        if (turnos % 3 == 0) {   //TD TERCEIRO TURNO
            System.out.println("            " + getNome() + " despenca do céu em cima de você: QUEDA ORBITAL!");
            alvo.tomarDano(getAtaque() +10 );
            System.out.println("            O impacto também machuca " + getNome() + "!");
            tomarDanoDireto(RECUO);
        } else {
            super.agir(alvo);    // nos outros turnos, comportamento padrão
        }
    }
}