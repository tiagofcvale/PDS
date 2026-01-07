package FlyWeight;

import java.util.HashMap;
import java.util.Map;

public class ArvoreFactory { // FlyWeight Factory
    private static Map<String, TipoArvore> cache = new HashMap<>();

    public static TipoArvore getTipo(String nome, String cor, String textura) {
        String chave = nome + cor + textura;

        if (!cache.containsKey(chave)) {
            cache.put(chave, new TipoArvore(nome, cor, textura));
        }
        return cache.get(chave);
    }
}
