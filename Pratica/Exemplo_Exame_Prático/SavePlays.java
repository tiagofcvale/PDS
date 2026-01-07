import java.io.FileWriter;
import java.io.IOException;

public class SavePlays {
    private String file;
    FileWriter fWriter;
    public SavePlays(String file) throws IOException {
        this.file = file;
        this.fWriter = new FileWriter(file);
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void visit(Robot robot, String classe) {
        try {
            fWriter.write(classe + " " + robot.getName() + " has " + robot.getMoves() + " moves and " + robot.getKicks() + " kicks\n");

            System.out.println("File writen with the number of plays from each robot");
        } catch (IOException e) {
            System.out.println("Erro");
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        fWriter.close();
    }
}
