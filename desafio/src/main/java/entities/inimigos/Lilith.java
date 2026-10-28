package entities.inimigos;

import entities.Acao;
import entities.Inimigo;
import entities.Piloto;

public class Lilith extends Inimigo {

    private int turnos = 0;

    public Lilith(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                  String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                  String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima, ascii,
                pAtacarDefender1, pAtacarDefender2, descricao, estilo);
        setDescricaoEspecial("Regeneração: a cada 2 turnos, em vez de atacar, recupera uma parte da vida.");

    }

    //HABILIDADE ESPECIAL: REGENERAÇÃO,
    //CURA 10% DA VIDA A CADA 2 TURNOS
    @Override
    protected void agir(Piloto alvo) {
        turnos++;
        // a cada 2 turnos, se estiver ferida, regenera em vez de atacar
        if (turnos % 2 == 0 && getVida() < getVidaMaxima()) {
            int antes = getVida();
            curar(getVidaMaxima() / 10);   // 10% da vida máxima = 15
            System.out.println("            " + getNome() + " pulsa e suas feridas se fecham: REGENERAÇÃO! (+"
                    + (getVida() - antes) + " de vida)");
        } else {
            super.agir(alvo);
        }
    }
}