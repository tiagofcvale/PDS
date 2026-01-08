package Teste2;

import java.util.ArrayList;
import java.util.List;

public class DocumentManager implements IDocument{
    private String file;
    private List<DocumentObserver> observers;

    public DocumentManager(String file) {
        this.file = file;
        this.observers = new ArrayList<>();
    }

    public void subscribe(DocumentObserver o) {
        this.observers.add(o);
    }

    public void executeProcess() {
        for (DocumentObserver documentObserver : observers) {
            documentObserver.update(file);
        }
        process();
    }

    @Override
    public void process() {
        System.out.println("Opening file...");
        System.out.println("Reading content...");
        System.out.println("Closing file...");
    }

    @Override
    public String getDescrição() {
        return file;
    }
}
