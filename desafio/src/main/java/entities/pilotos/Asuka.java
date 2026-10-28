package entities.pilotos;
import entities.Personagem;
import entities.Piloto;

import java.util.Random;
//Asuka == arqueiro
public class Asuka extends Piloto {

    private static final int CHANCE_ATAQUE = 50;
    private Random random = new Random();

    //construtor
    public Asuka (String nome, int vida, int ataque, int defesa,int vidaMaxima,
                  int usosEspecial) {
        super(nome,vida ,ataque, defesa, vidaMaxima, usosEspecial );

    }
    //ataque especial
    public boolean ataqueDuplo(Personagem alvo) {
        //chance de causar dano dobrado
        if (!temEspecial()) {
            System.out.println("            Acabaram os ataques duplos!");
            System.out.println("            Você ainda está no seu turno, tente novamente:");
            return false;
        }
        if (random.nextInt(100) < CHANCE_ATAQUE) {
            System.out.println("            "+getNome() + " acerta um ATAQUE DUPLO!");
            alvo.tomarDano(getAtaque() * 2);
        } else {
            System.out.println("            "+getNome() + " tenta o ataque duplo, mas acerta só um tiro!");
            alvo.tomarDano(getAtaque());
        }
        gastarUsoEspecial();
        return true;
    }

    //métodos sobrescritos
    @Override
    public String getNomeEspecial() {
        return "Ataque Duplo";
    }

    @Override
    public boolean usarEspecial(Personagem alvo) {
        return ataqueDuplo(alvo);
    }

    @Override
    public String getDescricaoEspecial() {
        return ""+CHANCE_ATAQUE + "% de chance de causar " + (getAtaque() * 2)
                + " de dano. Se falhar, acerta um tiro normal de " + getAtaque() + ".";
    }
}

