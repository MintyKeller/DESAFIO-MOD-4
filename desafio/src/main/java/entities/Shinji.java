package entities;

//Shinji == guerreiro
public class Shinji extends Piloto {

    private static final int CUSTO_VIDA = 10;
    private static final int BONUS_DANO = 18;

    //construtor
    public Shinji(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                  int usosEspecial) {
        super(nome, vida, ataque, defesa, vidaMaxima, usosEspecial);
    }

    // ataque especial
    public boolean ataqueForte(Personagem alvo) {
        //ataque forte (+18) que gasta vida
        if (!temEspecial()) {
            System.out.println("            Acabaram os ataques fortes!");
            System.out.println("            Você ainda está no seu turno, tente novamente:");
            return false;
        }
        alvo.tomarDano(getAtaque() + BONUS_DANO);
        setVida(Math.max(1, getVida() - CUSTO_VIDA));
        System.out.println(""            +getNome() + " usa ATAQUE FORTE e sacrifica " + CUSTO_VIDA + " de vida!");
        gastarUsoEspecial();
        return true;
    }

    //métodos sobrescritos
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
        return "            Causa " + (getAtaque() + BONUS_DANO) + " de dano, mas sacrifica " + CUSTO_VIDA
                + " de vida (nunca te deixa com menos de 1).";
    }


}
