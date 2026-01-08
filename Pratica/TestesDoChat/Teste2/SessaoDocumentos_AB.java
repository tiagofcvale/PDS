package Teste2;

// NMEC: 125913
// NOME: Tiago Vale

public class SessaoDocumentos_AB {
    public static void main(String[] args) {
        // Parte A: Template Method & Proxy
        System.out.println("\n--- Part A: Document Processing & Proxy ---");

        User admin = new User("Tiago", "ADMIN");
        User guest = new User("Joao", "GUEST");

        // O Proxy deve controlar o acesso ao documento real
        IDocument doc1 = new DocumentProxy(new PDFDocument("PDS_Slides.pdf"), admin);
        IDocument doc2 = new DocumentProxy(new WordDocument("Relatorio.docx"), guest);

        System.out.println("Admin reading doc1:");
        doc1.process(); 

        System.out.println("\nGuest reading doc2:");
        doc2.process(); // Deve mostrar erro de permissão

        // Parte B: Decorator Pattern
        System.out.println("\n--- Part B: Document Decorators ---");

        IDocument simpleDoc = new PDFDocument("Contrato.pdf");
        IDocument signedDoc = new DigitalSignature(simpleDoc);
        IDocument encryptedAndSignedDoc = new Encryption(signedDoc);

        System.out.println("Processing Encrypted and Signed Document:");
        encryptedAndSignedDoc.process();
    }
}