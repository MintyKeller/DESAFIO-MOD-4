package entities.inimigos;

import entities.Acao;
import entities.Inimigo;
import entities.Piloto;

public class ReiLilith extends Inimigo {

    private int turnos = 0;

    public ReiLilith(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                     String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                     String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima, ascii,
                pAtacarDefender1, pAtacarDefender2, descricao, estilo);
        setDescricaoEspecial("Instrumentalidade: a cada 2 turnos absorve sua vida, causando dano e se curando no mesmo valor. Defender no turno certo reduz a cura.");
    }

    //HABILIDADE ESPECIAL: DÁ DANO E ABSORVE ESSA VIDA TIRADA COMO SUA PRÓPRIA VIDA
    @Override
    protected void agir(Piloto alvo) {
        turnos++;
        if (turnos % 2 == 0) {
            System.out.println("            Rei abre as asas.");
            System.out.println("            Sua vida é absorvida!");

            int vidaAntes = alvo.getVida();
            alvo.tomarDano(getAtaque());
            int absorvido = vidaAntes - alvo.getVida();   // dano que realmente causou
            curar(absorvido);                             // se cura no mesmo valor
            System.out.println("            " + getNome() + " absorveu " + absorvido + " de vida!");
        } else {
            super.agir(alvo);
        }
    }
}