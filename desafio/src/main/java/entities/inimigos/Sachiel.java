package entities.inimigos;

import entities.Acao;
import entities.Inimigo;
import entities.Piloto;

public class Sachiel extends Inimigo {

    private static final double LIMITE_EXPLOSAO = 0.50;
    private static final int MULTIPLICADOR = 4;

    public Sachiel(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                   String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                   String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima, ascii,
                pAtacarDefender1, pAtacarDefender2, descricao, estilo);
        setDescricaoEspecial("Autodestruição: quando a vida cai pela metade, ele explode causando dano alto em você, mas morre junto.");

    }

    //HABILIDADE ESPECIAL: Autodestruição
   //QUANDO A vida dele atinge metade, ele explode, dando muito dano mas morredno, que nem no anime

    @Override
    protected void agir(Piloto alvo) {
        if (getVida() <= getVidaMaxima() * LIMITE_EXPLOSAO) {
            System.out.println("            " + getNome() + " brilha em vermelho... AUTODESTRUIÇÃO!");
            alvo.tomarDano(getAtaque() * MULTIPLICADOR);
            setVida(0);
            System.out.println("            " + getNome() + " se desintegra na explosão!");
            return;
        }
        super.agir(alvo);
    }


}