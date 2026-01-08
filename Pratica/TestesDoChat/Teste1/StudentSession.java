package Teste1;

import java.util.ArrayList;
import java.util.List;

public class StudentSession {
    private String name;
    private int nMec;

    private List<ExamObserver> professores;

    public StudentSession(String name, int nMec) {
        this.name = name;
        this.nMec = nMec;

        this.professores = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getnMec() {
        return nMec;
    }

    public void setnMec(int nMec) {
        this.nMec = nMec;
    }

    public List<ExamObserver> getProfessores() {
        return professores;
    }

    public void registerObserver(ExamObserver professor) {
        this.professores.add(professor);
    }

    public void updateProgress(String message) {
        for (ExamObserver examObserver : professores) {
            examObserver.update(message, this);
        }
    }
}
