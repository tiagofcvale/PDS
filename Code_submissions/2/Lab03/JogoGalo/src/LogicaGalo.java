package Lab03.JogoGalo.src;

public class LogicaGalo implements JGaloInterface {


    private char actualPlayer;
    private char[][] tabuleiro;
    private int jogadas;
    
    public LogicaGalo(char actualPlayer){
        if (actualPlayer != 'X' && actualPlayer != 'O') { // por omissão o jogador é o X
            actualPlayer = 'X';
        }

        this.actualPlayer = actualPlayer;
        this.jogadas = 0;
        this.tabuleiro = new char[3][3];
    }

    @Override
    public char getActualPlayer() {
        return this.actualPlayer;
    }

    @Override
    public boolean setJogada(int lin, int col) {
        lin --; // ajusta de acordo com o indice do array
        col --;
        
        if (lin < 0 || lin > 2 || col < 0 || col > 2 || isFinished()) {
            return false;
        }
        
        if (this.tabuleiro[lin][col] != 'X' && this.tabuleiro[lin][col] != 'O') {
            
            this.tabuleiro[lin][col] = getActualPlayer();

            if (this.actualPlayer =='X'){
            
                this.actualPlayer = 'O';
            } else {
                this.actualPlayer = 'X';
            }
            
            this.jogadas++;
            return true;

        } else {
            return false;
        } 
    }


    @Override 
    public boolean isFinished() {
        if (this.jogadas == 9 || checkResult() != ' ')
            return true;
        return false;
    }

    @Override
        public char checkResult() {

            for (int i = 0; i < 3; i++) {

                //verificar linhas
                if ((this.tabuleiro[i][0] == this.tabuleiro[i][1] && this.tabuleiro[i][0] == this.tabuleiro[i][2]) && this.tabuleiro[i][0]!=0) 
                    return this.tabuleiro[i][0];
                
                //verificar colunas
                if ((this.tabuleiro[0][i] == this.tabuleiro[1][i] && this.tabuleiro[0][i] == this.tabuleiro[2][i] && this.tabuleiro[0][i]!=0)) 
                    return this.tabuleiro[0][i];
            }
            
            //verificar diagonais esquerda-direita
            if ((this.tabuleiro[0][0] == this.tabuleiro[1][1] && this.tabuleiro[0][0] == this.tabuleiro[2][2] && this.tabuleiro[0][0]!=0)) 
                return this.tabuleiro[0][0];
                
            //verificar diagonais direita-esquerda
            if ((this.tabuleiro[0][2] == this.tabuleiro[1][1] && this.tabuleiro[0][2] == this.tabuleiro[2][0] && this.tabuleiro[0][2]!=0)) 
                return this.tabuleiro[0][2];

            return ' ';
    }

}
