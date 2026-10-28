package entities.inimigos;

import entities.Acao;
import entities.Inimigo;
import entities.Piloto;

public class Ramiel extends Inimigo {

    private int turnos = 0;
    private boolean recarregando = false;

    public Ramiel(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                  String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                  String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima, ascii,
                pAtacarDefender1, pAtacarDefender2, descricao, estilo);
        setDescricaoEspecial("Raio de Partículas: depois de 2 turnos, dispara um raio de dano alto, e no turno seguinte fica recarregando, sem agir.");

    }

    //ATAQUE ESPECIAL = ATAQUE FORTE QUE TEM QUE RECARREGAR,
    // depois de 1 turnos ataca mais forte e fica um turno sem poder atacar.
    @Override
    protected void agir(Piloto alvo) {
        // turno de recarga: não faz nada
        if (recarregando) {
            System.out.println("            " + getNome() + " está recarregando o raio...");
            recarregando = false;
            return;
        }
        if (escolherAcao() == Acao.DEFENDER) {
            defender();
            return;
        }
        turnos++;
        if (turnos == 2) {   // 1 ataques normais, o 2 é o forte
                System.out.println("            " + getNome() + " dispara o RAIO DE PARTÍCULAS!");
                alvo.tomarDano(getAtaque() + 15);
                turnos = 0;
                recarregando = true;
            } else {
                super.agir(alvo);
            }
        }
    }

