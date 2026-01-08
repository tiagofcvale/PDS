package Teste1;

// NMEC: 125913
// NOME: Tiago Vale

public class SessaoPDS_AB {
    public static void main(String[] args) {
        // Parte A: Estrutura de Perguntas
        System.out.println("\n--- Part A: Exam Structure ---");
        
        QuestionGroup exam = new QuestionGroup("Final Exam");
        
        Question q1 = new MultipleChoice("What is PDS?", 2.0);
        Question q2 = new TrueFalse("Is Java OOP?", 1.0);
        
        QuestionGroup subGroup = new QuestionGroup("Design Patterns Section");
        subGroup.add(new MultipleChoice("Identify the pattern...", 3.0));
        
        exam.add(q1);
        exam.add(q2);
        exam.add(subGroup);
        
        exam.display();
        System.out.println("Total Exam Points: " + exam.getPoints());

        // Parte B: Estratégias de Avaliação
        System.out.println("\n--- Part B: Grading Strategies ---");
        
        GradingSystem grader = new GradingSystem();
        
        grader.setStrategy(new StrictGrading());
        System.out.println("Strict Grade: " + grader.evaluate(exam));
        
        grader.setStrategy(new FlexibleGrading());
        System.out.println("Flexible Grade: " + grader.evaluate(exam));
    }
}