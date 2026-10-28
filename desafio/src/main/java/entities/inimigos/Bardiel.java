package entities.inimigos;

import entities.Acao;
import entities.Inimigo;
import entities.Piloto;

public class Bardiel extends Inimigo {

    private static final int DURACAO = 2;
    private int turnos = 0;

    public Bardiel(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                   String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                   String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima, ascii,
                pAtacarDefender1, pAtacarDefender2, descricao, estilo);
        setDescricaoEspecial("Estrangulamento: a cada 3 turnos agarra você com dano direto e continua apertando por mais 2 turnos, ignorando sua defesa.");

    }

    //habilidade especial:
    @Override
    protected void agir(Piloto alvo) {
        turnos++;
        if (turnos % 3 == 0) {   // td 3º turno
            System.out.println("            " + getNome() + " estica os braços do Eva-03 e agarra seu pescoço: ESTRANGULAMENTO!");
            int dano = 9;
            alvo.tomarDanoDireto(dano);                   // aperto inicial, ignora defesa
            alvo.aplicarDanoContinuo(dano, DURACAO);      // e continua apertando
        } else {
            super.agir(alvo);
        }
    }
}