package Iterator_Behavioral_Pattern;

import java.util.List;

public class PlaylistIterator implements IteratorMusicas{

    private List<String> musicas;
    private int pos = 0;

    public PlaylistIterator(List<String> musicas) {
        this.musicas = musicas;
    }

    @Override
    public boolean hasNext() {
        return pos < musicas.size();
    }

    @Override
    public String next() {
        return musicas.get(pos++);
    }
    
}
