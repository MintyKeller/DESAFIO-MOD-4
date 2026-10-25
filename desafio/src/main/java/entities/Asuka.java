package entities;
import java.util.Random;
//Asuka == arqueiro
public class Asuka extends Personagem {
    private int contadorAtaqueEspecial; // 4
    private static final int CHANCE_ATAQUE = 50;
    private Random random = new Random();

    public Asuka (String nome, int vida, int ataque, int defesa,int vidaMaxima,
                  int contadorAtaqueEspecial) {
        super(nome,vida ,ataque, defesa, vidaMaxima );
        this.contadorAtaqueEspecial = contadorAtaqueEspecial;
    }

    public int getContadorAtaqueEspecial() {
        return contadorAtaqueEspecial;
    }

    //ataque especial

    public boolean temEspecial() {
        return contadorAtaqueEspecial > 0;
    }

    public boolean ataqueDuplo(Personagem alvo) {
        //chance de causar dano dobrado
        if (!temEspecial()) {
            System.out.println("Acabaram os ataques duplos!");
            System.out.println("Você ainda está no seu turno, tente novamente:");
            return false;
        }
        if (random.nextInt(100) < CHANCE_ATAQUE) {
            System.out.println(getNome() + " acerta um ATAQUE DUPLO!");
            alvo.tomarDano(getAtaque() * 2);
        } else {
            System.out.println(getNome() + " tenta o ataque duplo, mas acerta só um tiro!");
            alvo.tomarDano(getAtaque());
        }
        contadorAtaqueEspecial--;
        return true;
    }


    @Override
    public int getUsosEspecial() { return contadorAtaqueEspecial; }

    @Override
    public String getNomeEspecial() { return "Ataque Duplo"; }

    @Override
    public boolean usarEspecial(Personagem alvo) { return ataqueDuplo(alvo); }

    @Override
    public String getDescricaoEspecial() {
        return CHANCE_ATAQUE + "% de chance de causar " + (getAtaque() * 2)
                + " de dano. Se falhar, acerta um tiro normal de " + getAtaque() + ".";
    }
    @Override
    public void adicionarUsoEspecial() { contadorAtaqueEspecial++; }
}

