package entities.inimigos;

import entities.Acao;
import entities.Inimigo;
import entities.Piloto;

public class Matarael extends Inimigo {

    private static final int DANO_ACIDO = 8;
    private static final int DURACAO = 2;   //  2 ou 3 turnos de dano continui
    private int turnos = 0;

    public Matarael(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                    String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                    String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima, ascii,
                pAtacarDefender1, pAtacarDefender2, descricao, estilo);
        setDescricaoEspecial("Chuva Corrosiva: a cada 3 turnos, em vez de atacar, joga ácido que queima por 2 turnos seguidos, ignorando sua defesa.");

    }

    //habilidade especial: dano continuo de 8 por 2 turnos
    @Override
    protected void agir(Piloto alvo) {
        turnos++;
        if (turnos % 3 == 0) {   // td 3º turno
            System.out.println("            " + getNome() + " libera seu líquido corrosivo: CHUVA CORROSIVA!");
            System.out.println("            O ácido gruda no seu Eva e continua queimando por " + DURACAO + " turnos!");
            alvo.aplicarDanoContinuo(DANO_ACIDO, DURACAO);
        } else {
            super.agir(alvo);
        }
    }
}