package entities;

//Shinji == guerreiro
public class Shinji extends Personagem {
    private int contadorAtaqueEspecial; //3
    private static final int CUSTO_VIDA = 10;
    private static final int BONUS_DANO = 18;

    public Shinji(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                  int contadorAtaqueEspecial) {
        super(nome, vida, ataque, defesa, vidaMaxima);
        this.contadorAtaqueEspecial = contadorAtaqueEspecial;
    }

    public int getContadorAtaqueEspecial() {
        return contadorAtaqueEspecial;
    }

    // ataque especial
    public boolean temEspecial() {
        return contadorAtaqueEspecial > 0;
    }

    public boolean ataqueForte(Personagem alvo) {
        //ataque forte (+18) que gasta vida
        if (!temEspecial()) {
            System.out.println("Acabaram os ataques fortes!");
            System.out.println("Você ainda está no seu turno, tente novamente:");
            return false;
        }
        alvo.tomarDano(getAtaque() + BONUS_DANO);
        setVida(Math.max(1, getVida() - CUSTO_VIDA));
        System.out.println(getNome() + " usa ATAQUE FORTE e sacrifica " + CUSTO_VIDA + " de vida!");
        contadorAtaqueEspecial--;
        return true;
    }


    @Override
    public int getUsosEspecial() {
        return contadorAtaqueEspecial;
    }

    @Override
    public String getNomeEspecial() {
        return "Ataque Forte";
    }

    @Override
    public boolean usarEspecial(Personagem alvo) {
        return ataqueForte(alvo);
    }

    @Override
    public String getDescricaoEspecial() {
        return "Causa " + (getAtaque() + BONUS_DANO) + " de dano, mas sacrifica " + CUSTO_VIDA
                + " de vida (nunca te deixa com menos de 1).";
    }

    @Override
    public void adicionarUsoEspecial() {
        contadorAtaqueEspecial++;
    }

}
