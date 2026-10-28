package utils;
import entities.Inimigo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static utils.DadosAnjos.*;
import entities.Acao;
import static entities.Acao.*;

import entities.inimigos.*;

public class FabricaInimigos {
    public static List<Inimigo> criarTodos() {
        List<Inimigo> inimigos = new ArrayList<>();

        inimigos.add(new Sachiel("Sachiel", 30, 6, 1, 30, SACHIEL_ASCII,
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, ATACAR},
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, ATACAR},
                SACHIEL_DESCRICAO, SACHIEL_ESTILO));

        inimigos.add(new Ramiel("Ramiel", 60, 7, 1, 35, RAMIEL_ASCII,
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, DEFENDER},
                new Acao[]{ATACAR, ATACAR, DEFENDER, DEFENDER, DEFENDER},
                RAMIEL_DESCRICAO, RAMIEL_ESTILO));

        inimigos.add(new Gaghiel("Gaghiel", 40, 8, 2, 40, GAGHIEL_ASCII,
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, DEFENDER},
                new Acao[]{ATACAR, ATACAR, ATACAR, DEFENDER, DEFENDER},
                GAGHIEL_DESCRICAO, GAGHIEL_ESTILO));

        inimigos.add(new Israfel("Israfel", 55, 9, 3, 50, ISRAFEL_ASCII,
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, DEFENDER},
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, DEFENDER},
                ISRAFEL_DESCRICAO, ISRAFEL_ESTILO));

        inimigos.add(new Matarael("Matarael", 60, 11, 3, 55, MATARAEL_ASCII,
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, ATACAR},
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, ATACAR},
                MATARAEL_DESCRICAO, MATARAEL_ESTILO));

        inimigos.add(new Sahaquiel("Sahaquiel", 65, 12, 4, 60, SAHAQUIEL_ASCII,
                new Acao[]{ATACAR, ATACAR, ATACAR, DEFENDER, DEFENDER},
                new Acao[]{ATACAR, ATACAR, DEFENDER, DEFENDER, DEFENDER},
                SAHAQUIEL_DESCRICAO, SAHAQUIEL_ESTILO));

        inimigos.add(new Leliel("Leliel", 80, 12, 4, 80, LELIEL_ASCII,
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, ATACAR},
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, DEFENDER},
                LELIEL_DESCRICAO, LELIEL_ESTILO));

        inimigos.add(new Bardiel("Bardiel", 90, 13, 5, 90, BARDIEL_ASCII,
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, DEFENDER},
                new Acao[]{ATACAR, ATACAR, ATACAR, DEFENDER, DEFENDER},
                BARDIEL_DESCRICAO, BARDIEL_ESTILO));

        inimigos.add(new Kaworu("Kaworu", 100, 11, 8, 100, KAWORU_ASCII,
                new Acao[]{ATACAR, ATACAR, ATACAR, DEFENDER, DEFENDER},
                new Acao[]{ATACAR, ATACAR, DEFENDER, DEFENDER, DEFENDER},
                KAWORU_DESCRICAO, KAWORU_ESTILO));

// embaralha dentro de cada faixa de dificuldade
        Collections.shuffle(inimigos.subList(0, 3)); // Sachiel, Ramiel, Gaghiel
        Collections.shuffle(inimigos.subList(3, 6)); // Israfel, Matarael, Sahaquiel
        Collections.shuffle(inimigos.subList(6, 9)); // Leliel, Bardiel, Kaworu

// chefe final sempre por último
        inimigos.add(new Lilith("Lilith", 150, 16, 6, 150, LILITH_ASCII,
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, DEFENDER}, //p1
                new Acao[]{ATACAR, ATACAR, ATACAR, DEFENDER, DEFENDER}, //p2
                LILITH_DESCRICAO, LILITH_ESTILO));

        return inimigos;
    }

    public static Inimigo criarLilithFase2() {
        return new ReiLilith("Rei Lilith", 100, 18, 6, 100, REI_LILITH_ASCII,
                new Acao[]{ATACAR, ATACAR, ATACAR, DEFENDER, DEFENDER},
                new Acao[]{ATACAR, ATACAR, ATACAR, ATACAR, DEFENDER},
                REI_LILITH_DESCRICAO, REI_LILITH_ESTILO);
    }

}
