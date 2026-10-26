package entities;

public class Israfel extends Inimigo{


    public Israfel(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                   String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                   String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima, ascii,
                pAtacarDefender1, pAtacarDefender2, descricao, estilo);
    }

    //HABILIDADE ESPECIAL: ATAQUE ESPECIAL = chance de ⅔ (66,6%) de ataque duplo.
    // ( de 3 ataques, 2 tem a chance de ser ataque duplo)
    //Ataque Sincronizado

    @Override
    protected void agir(Piloto alvo) {
        if (escolherAcao() == Acao.DEFENDER) {
            defender();
            return;
        }
        if (random.nextInt(3) < 2) {
            System.out.println("            Kou e Otsu atacam em sincronia!");
            atacar(alvo); //ataque eh dobrado!
            atacar(alvo);
        } else {
            System.out.println("            " + getNome() + " ataca!");
            atacar(alvo);
        }
    }

}
