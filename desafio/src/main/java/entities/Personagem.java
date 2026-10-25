package entities;

public abstract class Personagem {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;
    private boolean defendendo = false;
    private int vidaMaxima;
    private int nroPocoes = 3;


    //construtor
    public Personagem(String nome, int vida, int ataque, int defesa, int vidaMaxima) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.vidaMaxima = vidaMaxima;
    }


    //getters
    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getNroPocoes() {
        return nroPocoes;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public String getDescricaoEspecial() {
        return "";
    }

    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    //metodos


    // chamado no começo do turno de cada um (jogador e inimigo)
    public void iniciarTurno() {
        defendendo = false;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void tomarDano(int ataqueRecebido) {
        int dano = ataqueRecebido;
        if (defendendo) {
            dano = Math.max(1, ataqueRecebido - defesa);
        }
        vida = Math.max(0, vida - dano);
        System.out.println(nome + " levou " + dano + " de dano!");

        if (!estaVivo()) {
            System.out.println(nome + " foi derrotado!");
        }
    }

    public void atacar(Personagem alvo) {
        alvo.tomarDano(this.ataque);
    }

    // dano que ignora a defesa (usado pelo ataque infalível da Rei)
    public void tomarDanoDireto(int dano) {
        vida = Math.max(0, vida - dano);
        System.out.println(nome + " levou " + dano + " de dano!");

        if (!estaVivo()) {
            System.out.println(nome + " foi derrotado!");
        }
    }

    public void defender() {
        defendendo = true;
        System.out.println(nome + "  se prepara para defender!");
    }

    public boolean tomarPocao() {
        if (nroPocoes == 0) {
            System.out.println("Não há mais poções disponiveis!");
            System.out.println("Você ainda está no seu turno, tente novamente:");
            return false;
        }

        if (this.vida == vidaMaxima) {
            System.out.println("Você está com a vida máxima, e portanto não pode curar!");
            System.out.println("Você ainda está no seu turno, tente novamente:");
            return false;
        }

        this.nroPocoes--;
        int antes = this.vida;
        this.vida = Math.min(vidaMaxima, this.vida + 20);
        System.out.println("Você usou uma poção e recuperou " + (this.vida - antes) + " de vida (Poções restantes: " + nroPocoes + ")");
        return true;


    }

    public void adicionarPocao() {
        nroPocoes++;
    }

    public void curar(int quantidade) {
        vida = Math.min(vidaMaxima, vida + quantidade);
    }

    //ataques especiais dos pilotos
    public int getUsosEspecial() {
        return 0;
    }

    public String getNomeEspecial() {
        return "Nenhum";
    }

    public boolean usarEspecial(Personagem alvo) {
        return false;
    }

    public void adicionarUsoEspecial() { }

}



