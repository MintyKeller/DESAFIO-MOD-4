package entities;

public abstract class Piloto extends Personagem {
    //atributos
    private static final int CURA_POCAO = 20;
    private int nroPocoes = 3;
    private int usosEspecial;

    //construtor
    public Piloto(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                  int usosEspecial) {
        super(nome, vida, ataque, defesa, vidaMaxima);
        this.usosEspecial = usosEspecial;
    }

    //getters
    public int getNroPocoes() {
        return nroPocoes;
    }
    public int getUsosEspecial() {
        return usosEspecial;
    }
    public boolean temEspecial() {
        return usosEspecial > 0;
    }

    // adders/ setters
    public void adicionarPocao() {
        nroPocoes++;
    }
    public void adicionarUsoEspecial() {
        usosEspecial++;
    }
    public void gastarUsoEspecial() {
        usosEspecial--;
    }

    public boolean tomarPocao() {
        if (nroPocoes == 0) {
            System.out.println("            Não há mais poções disponíveis!");
            System.out.println("            Você ainda está no seu turno, tente novamente:");
            return false;
        }
        if (getVida() == getVidaMaxima()) {
            System.out.println("            Você está com a vida máxima, e portanto não pode curar!");
            System.out.println("            Você ainda está no seu turno, tente novamente:");
            return false;
        }
        nroPocoes--;
        int antes = getVida();
        curar(CURA_POCAO);
        System.out.println("            Você usou uma poção e recuperou " + (getVida() - antes)
                + " de vida (Poções restantes: " + nroPocoes + ")");
        return true;
    }




    public abstract boolean usarEspecial(Personagem alvo);
    public abstract String getNomeEspecial();
    public abstract String getDescricaoEspecial();

}