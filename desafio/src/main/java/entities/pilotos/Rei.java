package entities.pilotos;

import entities.Personagem;
import entities.Piloto;

//Rei == mago
public class Rei extends Piloto {

    private static final int BONUS_DANO = 5;

    //construtor
    public Rei (String nome, int vida, int ataque, int defesa,int vidaMaxima,
                int usosEspecial) {
        super(nome,vida ,ataque, defesa, vidaMaxima, usosEspecial );

    }

    public boolean ataqueInfalivel(Personagem alvo) {
        // ataque que ignora a defesa do alvo
        if (!temEspecial()) {
            System.out.println("            Acabaram os ataques infalíveis!");
            System.out.println("            Você ainda está no seu turno, tente novamente:");
            return false;
        }
        System.out.println("            "+getNome() + " usa ATAQUE INFALÍVEL, a defesa não adianta!");
        alvo.tomarDanoDireto(getAtaque() + BONUS_DANO);
        gastarUsoEspecial();
        return true;
    }

    //métodos sobrescritos
    @Override
    public String getNomeEspecial() { return "Ataque Infalível"; }

    @Override
    public boolean usarEspecial(Personagem alvo) { return ataqueInfalivel(alvo); }

    @Override
    public String getDescricaoEspecial() {
        return "Causa " + (getAtaque() + BONUS_DANO) + " de dano ignorando a defesa do alvo, mais um adicional de 5 no ataque.";
    }

}
