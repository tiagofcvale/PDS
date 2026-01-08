package Teste2;

public class WordDocument implements IDocument{
    
    private String file;

    public WordDocument(String file) {
        this.file = file;
    }

    @Override
    public void process() {
        System.out.println("Opening Word File...");
        System.out.println("Reading raw data from Word: "+file);
        System.out.println("Closing file.");
    }

    @Override
    public String getDescrição() {
        return file;
    }

    
}
