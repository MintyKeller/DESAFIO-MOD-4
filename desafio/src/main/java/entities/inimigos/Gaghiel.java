package entities.inimigos;

import entities.Acao;
import entities.Inimigo;
import entities.Piloto;

public class Gaghiel extends Inimigo {

    private boolean submerso = false;
    private boolean jaSubmergiu = false;

    public Gaghiel(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                   String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                   String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima, ascii,
                pAtacarDefender1, pAtacarDefender2, descricao, estilo);
        setDescricaoEspecial("Mergulho: ao chegar na metade da vida, submerge por um turno sem atacar e sem poder ser atingido (nem pelo Ataque Infalível). Volta curado em 25% da vida total.");

    }


    //HABILIDADE ESPECIAL: SE ESCONDE DEBAIXO DA ÁGUA ( DEPOIS DE 50% DE VIDA TIRADO)
    // NÃO LEVANDO DANO, NEM ATACANDO, E VOLTA RECUPERANDO 25% DA VIDA

    // submerso = imune a qualquer ataque
    @Override
    public void tomarDano(int ataqueRecebido) {
        if (submerso) {
            System.out.println("            Gaghiel está de baixo d'água e não leva dano!");
            return;
        }
        super.tomarDano(ataqueRecebido);
    }

    // o Ataque Infalível da Rei também não pode acertar
    @Override
    public void tomarDanoDireto(int dano) {
        if (submerso) {
            System.out.println("           Gaghiel está de baixo d'água e nem mesmo com ataque infalível é atingido!");
            return;
        }
        super.tomarDanoDireto(dano);
    }

    @Override
    protected void agir(Piloto alvo) {
        if (submerso) {
            // volta do mergulho: cura 25% da vida total e age normalmente
            submerso = false;
            int antes = getVida();
            curar(getVidaMaxima() / 4);
            System.out.println("            " + getNome() + " emerge recuperado! (+" + (getVida() - antes) + " de vida)");
        } else if (!jaSubmergiu && getVida() <= getVidaMaxima() / 2) {
            // primeira vez que cai pra 50%: mergulha e fica sem atacar
            submerso = true;
            jaSubmergiu = true;
            System.out.println("            " + getNome() + " mergulha e some de vista...");
            return;
        }
        super.agir(alvo);
    }
}