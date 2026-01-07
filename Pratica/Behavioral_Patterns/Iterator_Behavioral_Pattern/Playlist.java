package Iterator_Behavioral_Pattern;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements ColecaoMusicas{
    private List<String> musicas = new ArrayList<>();

    public void adicionar(String musica){
        this.musicas.add(musica);
    }

    @Override
    public IteratorMusicas criarIterator() {
        return new PlaylistIterator(this.musicas);
    }
}
