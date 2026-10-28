package entities.inimigos;

import entities.Acao;
import entities.Inimigo;
import entities.Piloto;

public class Kaworu extends Inimigo {

    private static final int REDUCAO = 10;   // dano a menos enquanto a barreira estiver ativa
    private int ataquesFeitos = 0;
    private int turnosBarreira = 0;

    public Kaworu(String nome, int vida, int ataque, int defesa, int vidaMaxima,
                  String ascii, Acao[] pAtacarDefender1, Acao[] pAtacarDefender2,
                  String descricao, String estilo) {
        super(nome, vida, ataque, defesa, vidaMaxima, ascii,
                pAtacarDefender1, pAtacarDefender2, descricao, estilo);
        setDescricaoEspecial("AT Field: depois de 2 ataques, levanta uma barreira que reduz em 10 o dano dos seus próximos 2 golpes.");

    }

    // com a barreira ativa, td golpe causa 10 a menos (mínimo 1)
    @Override
    public void tomarDano(int ataqueRecebido) {
        if (turnosBarreira > 0) {
            int reduzido = Math.max(1, ataqueRecebido - REDUCAO); //nunca 0, pq da pelo menso1 de ataque
            System.out.println("            O AT Field de " + getNome() + " absorve parte do golpe! (-"
                    + (ataqueRecebido - reduzido) + ")");
            ataqueRecebido = reduzido;
        }
        super.tomarDano(ataqueRecebido);
    }

    //HABILIDADE ESPECIAL: barreira
    //depois de 2 ataques, ergue uma barreira que tira -10 do ataque do piloto, que dura dois turnos
    @Override
    protected void agir(Piloto alvo) {
        // a barreira dura 2 golpes seus: ela perde 1 turno no começo de cada vez dele
        if (turnosBarreira > 0) {
            turnosBarreira--;
            if (turnosBarreira == 0) {
                System.out.println("            O AT Field de " + getNome() + " se dissipa.");
            }
        }
        if (escolherAcao() == Acao.DEFENDER) {
            defender();
            return;
        }
        System.out.println("            " + getNome() + " ataca!");
        atacar(alvo);
        // só conta ataques quando a barreira está desligada
        if (turnosBarreira == 0) {
            ataquesFeitos++;
            if (ataquesFeitos == 2) {
                System.out.println("            " + getNome() + " ergue o AT Field: dano recebido -" + REDUCAO + " por 2 turnos!");
                turnosBarreira = 2;
                ataquesFeitos = 0;
            }
        }
    }
}