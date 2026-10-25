package entities;
//Rei == mago
public class Rei extends Personagem {
    private int contadorAtaqueEspecial; // 5
    private static final int BONUS_DANO = 5;


    public Rei (String nome, int vida, int ataque, int defesa,int vidaMaxima,
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

    public boolean ataqueInfalivel(Personagem alvo) {
        // ataque que ignora a defesa do alvo
        if (!temEspecial()) {
            System.out.println("Acabaram os ataques infalíveis!");
            System.out.println("Você ainda está no seu turno, tente novamente:");
            return false;
        }
        System.out.println(getNome() + " usa ATAQUE INFALÍVEL, a defesa não adianta!");
        alvo.tomarDanoDireto(getAtaque() + BONUS_DANO);
        contadorAtaqueEspecial--;
        return true;
    }



    @Override
    public int getUsosEspecial() { return contadorAtaqueEspecial; }

    @Override
    public String getNomeEspecial() { return "Ataque Infalível"; }

    @Override
    public boolean usarEspecial(Personagem alvo) { return ataqueInfalivel(alvo); }

    @Override
    public String getDescricaoEspecial() {
        return "Causa " + (getAtaque() + BONUS_DANO) + " de dano ignorando a defesa do alvo, mais um adicional de 5 no ataque.";
    }

    @Override
    public void adicionarUsoEspecial() { contadorAtaqueEspecial++; }

}
