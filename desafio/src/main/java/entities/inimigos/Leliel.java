package entities.inimigos;

import entities.Acao;
import entities.Inimigo;
import entities.Piloto;

public class Leliel extends Inimigo {

    private int turnos = 0;
    private boolean engolindo = false;

    public Leliel(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                  String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                  String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima, ascii,
                pAtacarDefender1, pAtacarDefender2, descricao, estilo);
        setDescricaoEspecial("Mar de Dirac: a cada 3 turnos engole você com um dano leve, e você perde o próximo turno.");

    }

    //HABILIDADE ESPECIAL: mar de escuridao
    //absorve o piloto e nao deixa ele jogar no próximo turno
    @Override
    protected void agir(Piloto alvo) {
        // no turno seguinte ao engolir, o piloto já perdeu a vez e a sombra se rasga
        if (engolindo) {
            engolindo = false;
            System.out.println("            A sombra se rasga e a luz volta. Você saiu de dentro do Anjo da Noite.");
        }

        turnos++;
        if (turnos % 3 == 0) {   // td 3º turno
            System.out.println("            " + getNome() + " engole você: MAR DE DIRAC!");
            alvo.tomarDano(getAtaque() / 2);   // dano leve
            alvo.prender(1);
            engolindo = true;
            System.out.println("            Preso na sombra, você perde o próximo turno!");
        } else {
            super.agir(alvo);
        }
    }
}