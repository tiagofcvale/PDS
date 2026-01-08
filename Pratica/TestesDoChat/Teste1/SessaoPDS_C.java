package Teste1;

// NMEC: 125913
// NOME: Tiago Vale

public class SessaoPDS_C {
    public static void main(String[] args) {
        System.out.println("\n--- Part C: Exam Monitoring ---");

        StudentSession session = new StudentSession("Tiago Vale", 125913);
        
        ExamProctor prof = new ExamProctor("Prof. PDS");
        ExamProctor logs = new ExamProctor("Central Log System");

        session.registerObserver(prof);
        session.registerObserver(logs);

        session.updateProgress("Answering Part A...");
        session.updateProgress("Submitting Exam.");
    }
}