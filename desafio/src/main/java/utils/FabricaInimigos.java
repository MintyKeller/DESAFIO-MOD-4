package utils;
import entities.Inimigo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static utils.DadosAnjos.*;

public class FabricaInimigos {
    public static List<Inimigo> criarTodos() {
        List<Inimigo> inimigos = new ArrayList<>();

        inimigos.add(new Inimigo("Sachiel",    30,  6, 1, 30, SACHIEL_ASCII,   new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}, SACHIEL_DESCRICAO, SACHIEL_ESTILO));
        inimigos.add(new Inimigo("Ramiel",     35,  7, 1, 35, RAMIEL_ASCII,    new int[]{0, 0, 0, 0, 1}, new int[]{0, 0, 1, 1, 1}, RAMIEL_DESCRICAO, RAMIEL_ESTILO));
        inimigos.add(new Inimigo("Gaghiel",    40,  8, 2, 40, GAGHIEL_ASCII,   new int[]{0, 0, 0, 0, 1}, new int[]{0, 0, 0, 1, 1}, GAGHIEL_DESCRICAO, GAGHIEL_ESTILO));
        inimigos.add(new Inimigo("Israfel",    50,  9, 3, 50,  ISRAFEL_ASCII,   new int[]{0, 0, 0, 0, 1}, new int[]{0, 0, 0, 0, 1}, ISRAFEL_DESCRICAO, ISRAFEL_ESTILO ));
        inimigos.add(new Inimigo("Matarael",   55, 11, 3, 55, MATARAEL_ASCII,  new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}, MATARAEL_DESCRICAO, MATARAEL_ESTILO));
        inimigos.add(new Inimigo("Sahaquiel",  60, 12, 4, 60,SAHAQUIEL_ASCII, new int[]{0, 0, 0, 1, 1}, new int[]{0, 0, 1, 1, 1}, SAHAQUIEL_DESCRICAO, SAHAQUIEL_ESTILO));
        inimigos.add(new Inimigo("Leliel",     80, 12, 4, 80, LELIEL_ASCII,    new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 1}, LELIEL_DESCRICAO, LELIEL_ESTILO ));
        inimigos.add(new Inimigo("Bardiel",    90, 13, 5, 90, BARDIEL_ASCII,   new int[]{0, 0, 0, 0, 1}, new int[]{0, 0, 0, 1, 1}, BARDIEL_DESCRICAO, BARDIEL_ESTILO));
        inimigos.add(new Inimigo("Kaworu",    100, 11, 8, 100, KAWORU_ASCII,    new int[]{0, 0, 0, 1, 1}, new int[]{0, 0, 1, 1, 1}, KAWORU_DESCRICAO, KAWORU_ESTILO ));
// embaralha dentro de cada faixa de dificuldade
        Collections.shuffle(inimigos.subList(0, 3)); // Sachiel, Ramiel, Gaghiel
        Collections.shuffle(inimigos.subList(3, 6)); // Israfel, Matarael, Sahaquiel
        Collections.shuffle(inimigos.subList(6, 9)); // Leliel, Bardiel, Kaworu

// chefe final sempre por último
        inimigos.add(new Inimigo("Lilith",    150, 16, 6, 150, LILITH_ASCII,    new int[]{0, 0, 0, 0, 1}, new int[]{0, 0, 0, 1, 1}, LILITH_DESCRICAO, LILITH_ESTILO));
        return inimigos;
    }

}
