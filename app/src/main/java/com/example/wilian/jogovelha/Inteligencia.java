package velha;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Chan
 */
public class Inteligencia {



    private int board[][] = new int[3][3];

    public static final int JOGADOR_BATATEIRO = 1;
    public static final int JOGADOR_COMPUTADOR = 2;
    public static final int RESULTADO_VELHA = 50;

    private int quantidadeEmpates = 0;
    private int quantidadeVitorias = 0;
    private int quantidadeDerrotas = 0;

    private int numJogada;
    private int jogadorAtual;
    private int jogadorInicial;

    public Inteligencia(){
        jogadorInicial = JOGADOR_BATATEIRO;
        jogadorAtual = JOGADOR_BATATEIRO;
        resetBoard();
    }
    
    public Inteligencia(int jogadorInicial){
        this.jogadorInicial = jogadorInicial;
        jogadorAtual = jogadorInicial;
        resetBoard();
    }

    // int jogador é JOGADOR_BATATEIRO ou JOGADOR_COMPUTADOR
    public void set(int linha, int coluna, int jogador){
        board[linha][coluna] = jogador;
        numJogada++;
        if(jogadorAtual == JOGADOR_BATATEIRO){
            jogadorAtual = JOGADOR_COMPUTADOR;
        }else{
            jogadorAtual = JOGADOR_BATATEIRO;
        }
    }


    /*
     * FUNÇÕES DE TABULEIRO
    */
    public void resetBoard(){
        for(int c1 = 0; c1 < 3; c1++){
            for(int c2 = 0; c2 < 3; c2++){
                board[c1][c2] = 0;
            }
        }

        numJogada = 0;
        jogadorAtual = jogadorInicial;
    }

    public int checkBoard(){
        if(checkBoardForPlayer(JOGADOR_BATATEIRO)){
            quantidadeVitorias++;
            return JOGADOR_BATATEIRO;

        }else if(checkBoardForPlayer(JOGADOR_COMPUTADOR)){
            quantidadeDerrotas++;
            return JOGADOR_COMPUTADOR;

        }else if(checkDraw()){
            quantidadeEmpates++;
            return RESULTADO_VELHA;

        }

        return 0;
    }

    private boolean checkBoardForPlayer(int jogador){

        //Verifica linhas
        for(int c1 = 0; c1 < 3; c1++){
            if(board[c1][0] == jogador && board[c1][1] == jogador && board[c1][2] == jogador){
                return true;
            }
        }
        //Verifica colunas
        for(int c1 = 0; c1 < 3; c1++){
            if(board[0][c1] == jogador && board[1][c1] == jogador && board[2][c1] == jogador){
                return true;
            }
        }

        //Verifica cruzados
        if(board[0][0] == jogador && board[1][1] == jogador && board[2][2] == jogador){
            return true;
        }
        if(board[0][2] == jogador && board[1][1] == jogador && board[2][0] == jogador){
            return true;
        }
        return false;
    }

    private boolean checkDraw(){

        boolean velha = true;

        for(int c1 = 0; c1 < 3; c1++){
            for(int c2 = 0; c2 < 3; c2++){
                if(board[c1][c2] == 0){
                    velha = false;
                }
            }
        }

        return velha;
    }

    private boolean perigo(){
        int quantidadeBatateiro = 0;
        int quantidadeComputador = 0;
        //Verifica linhas
        for(int c1 = 0; c1 < 3; c1++){
            quantidadeBatateiro = 0;
            quantidadeComputador = 0;
            for(int c2 = 0; c2 < 3; c2++){
                if(board[c1][c2] == JOGADOR_BATATEIRO){
                    quantidadeBatateiro++;
                }
                if(board[c1][c2] == JOGADOR_COMPUTADOR){
                    quantidadeComputador++;
                }
            }
            if(quantidadeBatateiro ==2 && quantidadeComputador == 0){
                eliminaPerigoLinha(c1);
                return true;
            }
        }

        //Verifica linhas
        for(int c1 = 0; c1 < 3; c1++){
            quantidadeBatateiro = 0;
            quantidadeComputador = 0;
            for(int c2 = 0; c2 < 3; c2++){
                if(board[c2][c1] == JOGADOR_BATATEIRO){
                    quantidadeBatateiro++;
                }
                if(board[c2][c1] == JOGADOR_COMPUTADOR){
                    quantidadeComputador++;
                }
            }
            if(quantidadeBatateiro ==2 && quantidadeComputador == 0){
                eliminaPerigoColuna(c1);
                return true;
            }
        }

        //Verifica cruzados

        if(board[0][0] == JOGADOR_BATATEIRO && board[1][1] == JOGADOR_BATATEIRO && board[2][2] == 0){
            set(2,2,JOGADOR_COMPUTADOR);
            return true;
        }
        if(board[0][0] == JOGADOR_BATATEIRO && board[1][1] == 0 && board[2][2] == JOGADOR_BATATEIRO){
            set(1,1,JOGADOR_COMPUTADOR);
            return true;
        }
        if(board[0][0] == 0 && board[1][1] == JOGADOR_BATATEIRO && board[2][2] == JOGADOR_BATATEIRO){
            set(0,0,JOGADOR_COMPUTADOR);
            return true;
        }

        if(board[0][2] == JOGADOR_BATATEIRO && board[1][1] == JOGADOR_BATATEIRO && board[2][0] == 0){
            set(2,0,JOGADOR_COMPUTADOR);
            return true;
        }
        if(board[0][2] == JOGADOR_BATATEIRO && board[1][1] == 0 && board[2][0] == JOGADOR_BATATEIRO){
            set(1,1,JOGADOR_COMPUTADOR);
            return true;
        }
        if(board[0][2] == 0 && board[1][1] == JOGADOR_BATATEIRO && board[2][0] == JOGADOR_BATATEIRO){
            set(0,2,JOGADOR_COMPUTADOR);
            return true;
        }

        return false;
    }

    private void eliminaPerigoLinha(int linha){
        if(board[linha][0] == 0 ){
            set(linha, 0, JOGADOR_COMPUTADOR);
        }
        if(board[linha][1] == 0 ){
            set(linha, 1, JOGADOR_COMPUTADOR);
        }
        if(board[linha][2] == 0 ){
            set(linha, 2, JOGADOR_COMPUTADOR);
        }
    }

    private void eliminaPerigoColuna(int coluna){
        if(board[0][coluna] == 0 ){
            set(0, coluna, JOGADOR_COMPUTADOR);
        }
        if(board[1][coluna] == 0 ){
            set(1, coluna, JOGADOR_COMPUTADOR);
        }
        if(board[2][coluna] == 0 ){
            set(2, coluna, JOGADOR_COMPUTADOR);
        }
    }



    /*
     * Funções para ganhar o jogo
    */
    private boolean tentaGanhar(){
        int quantidadeBatateiro = 0;
        int quantidadeComputador = 0;
        //Verifica linhas
        for(int c1 = 0; c1 < 3; c1++){
            quantidadeBatateiro = 0;
            quantidadeComputador = 0;
            for(int c2 = 0; c2 < 3; c2++){
                if(board[c1][c2] == JOGADOR_BATATEIRO){
                    quantidadeBatateiro++;
                }
                if(board[c1][c2] == JOGADOR_COMPUTADOR){
                    quantidadeComputador++;
                }
            }
            if(quantidadeBatateiro ==0 && quantidadeComputador == 2){
                ganhaJogoLinha(c1);
                return true;
            }
        }

        //Verifica linhas
        for(int c1 = 0; c1 < 3; c1++){
            quantidadeBatateiro = 0;
            quantidadeComputador = 0;
            for(int c2 = 0; c2 < 3; c2++){
                if(board[c2][c1] == JOGADOR_BATATEIRO){
                    quantidadeBatateiro++;
                }
                if(board[c2][c1] == JOGADOR_COMPUTADOR){
                    quantidadeComputador++;
                }
            }
            if(quantidadeBatateiro ==0 && quantidadeComputador == 2){
                ganhaJogoColuna(c1);
                return true;
            }
        }

        //Verifica cruzados

        if(board[0][0] == JOGADOR_COMPUTADOR && board[1][1] == JOGADOR_COMPUTADOR && board[2][2] == 0){
            set(2,2,JOGADOR_COMPUTADOR);
            return true;
        }
        if(board[0][0] == JOGADOR_COMPUTADOR && board[1][1] == 0 && board[2][2] == JOGADOR_COMPUTADOR){
            set(1,1,JOGADOR_COMPUTADOR);
            return true;
        }
        if(board[0][0] == 0 && board[1][1] == JOGADOR_COMPUTADOR && board[2][2] == JOGADOR_COMPUTADOR){
            set(0,0,JOGADOR_COMPUTADOR);
            return true;
        }

        if(board[0][2] == JOGADOR_COMPUTADOR && board[1][1] == JOGADOR_COMPUTADOR && board[2][0] == 0){
            set(2,0,JOGADOR_COMPUTADOR);
            return true;
        }
        if(board[0][2] == JOGADOR_COMPUTADOR && board[1][1] == 0 && board[2][0] == JOGADOR_COMPUTADOR){
            set(1,1,JOGADOR_COMPUTADOR);
            return true;
        }
        if(board[0][2] == 0 && board[1][1] == JOGADOR_COMPUTADOR && board[2][0] == JOGADOR_COMPUTADOR){
            set(0,2,JOGADOR_COMPUTADOR);
            return true;
        }

        return false;
    }

    private void ganhaJogoLinha(int linha){
        if(board[linha][0] == 0 ){
            set(linha, 0, JOGADOR_COMPUTADOR);
        }
        if(board[linha][1] == 0 ){
            set(linha, 1, JOGADOR_COMPUTADOR);
        }
        if(board[linha][2] == 0 ){
            set(linha, 2, JOGADOR_COMPUTADOR);
        }
    }

    private void ganhaJogoColuna(int coluna){
        if(board[0][coluna] == 0 ){
            set(0, coluna, JOGADOR_COMPUTADOR);
        }
        if(board[1][coluna] == 0 ){
            set(1, coluna, JOGADOR_COMPUTADOR);
        }
        if(board[2][coluna] == 0 ){
            set(2, coluna, JOGADOR_COMPUTADOR);
        }
    }

    private void jogaRandomico(){
        int linha = -1;
        int coluna = -1;
        for(int c1 = 0; c1 < 3; c1++){
            for(int c2 = 0; c2 < 3; c2++){
                if(board[c1][c2] == 0 ){

                    linha = c1;
                    coluna = c2;
                }
            }
        }

        set(linha, coluna, JOGADOR_COMPUTADOR);
    }

    public void computerMove(){
        
        /*
        * Se computador começou, jogada deve ser adiantada
        */
        int jogadaAtual = numJogada;
        if (jogadorInicial == JOGADOR_COMPUTADOR) jogadaAtual++;
        
        if(jogadaAtual == 1){
            if(board[1][1] == JOGADOR_BATATEIRO){
                set(0, 0, JOGADOR_COMPUTADOR);
            }else{
                set(1, 1, JOGADOR_COMPUTADOR);
            }
        }else if(jogadaAtual == 3){
            if(! perigo()) {
                
                // Tenta escapar de Jogada em 'L' ou 'triângulo'
                if((board[0][0] == JOGADOR_BATATEIRO && board[2][2] == JOGADOR_BATATEIRO)
                        || (board[0][2] == JOGADOR_BATATEIRO && board[2][0] == JOGADOR_BATATEIRO)){
                    if(board[1][0] == 0){
                        set(1, 0, JOGADOR_COMPUTADOR);
                    }else if(board[1][2] == 0){
                        set(1, 2, JOGADOR_COMPUTADOR);
                    }else if(board[0][1] == 0){
                        set(0, 1, JOGADOR_COMPUTADOR);
                    }else if(board[2][1] == 0){
                        set(2, 1, JOGADOR_COMPUTADOR);
                    }
                }else{
                    if (board[0][0] == 0) {
                        set(0, 0, JOGADOR_COMPUTADOR);
                    } else if (board[0][2] == 0) {
                        set(0, 2, JOGADOR_COMPUTADOR);
                    } else if (board[2][0] == 0) {
                        set(0, 2, JOGADOR_COMPUTADOR);
                    } else {
                        set(2, 2, JOGADOR_COMPUTADOR);
                    }
                }
            }
        }else if(jogadaAtual > 3){
            if(! tentaGanhar()){
                if(! perigo()){
                    jogaRandomico();
                }
            }
        }
    }



    /*
    * GETTERS e SETTERS
    */

    public int[][] getBoard(){
        return this.board;
    }

    public int getJogadorAtual(){
        return this.jogadorAtual;
    }

    public int getQuantidadeEmpates(){
        return this.quantidadeEmpates;
    }
    public int getQuantidadeVitorias(){
        return this.quantidadeVitorias;
    }

    public int getQuantidadeDerrotas(){
        return this.quantidadeDerrotas;
    }

    public int getNumJogada(){
        return this.numJogada;
    }
}