package entities;
import java.util.Random;

public class Inimigo extends Personagem {
    //atributos
    private String ascii;
    private Acao[] pAtacarDefender1;
    private Acao[] pAtacarDefender2;

    private String descricao;
    private String estilo;

    //construtor
    public Inimigo(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                   String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                   String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima);
        this.ascii = ascii;
        this.pAtacarDefender1 = pAtacarDefender1;
        this.pAtacarDefender2 = pAtacarDefender2;
        this.descricao = descricao;
        this.estilo = estilo;

    }

    //getters
    public String getAscii() {
        return ascii;
    }

    public Acao[] getpAtacarDefender1() {
        return pAtacarDefender1;
    }

    public Acao[] getpAtacarDefender2() {
        return pAtacarDefender2;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEstilo() {
        return estilo;
    }

    //metodos
    protected Random random = new Random();

    public void turno(Piloto alvo) {
        iniciarTurno();
        agir(alvo);
    }
    protected void agir(Piloto alvo) {
        if (escolherAcao() == Acao.ATACAR) {
            System.out.println("            " + getNome() + " ataca!");
            atacar(alvo);
        } else {
            defender();
        }
    }
    protected Acao escolherAcao() {
        // vida alta usa a tabela 1, vida baixa usa a tabela 2
        Acao[] tabela = getVida() > getVidaMaxima() / 2 ? pAtacarDefender1 : pAtacarDefender2;
        return tabela[random.nextInt(tabela.length)];
    }
}
