package Teste2;

public class PDFDocument implements IDocument{

    private String file;

    
    public PDFDocument(String file) {
        this.file = file;
    }


    @Override
    public void process() {
        System.out.println("Opening PDF file...");
        System.out.println("Reading raw data from: "+file);
        System.out.println("Closing file");
    }


    @Override
    public String getDescrição() {
        return "";
    }

    
    
}
