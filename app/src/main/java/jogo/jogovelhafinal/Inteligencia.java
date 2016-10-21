/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jogo.jogovelhafinal;

import java.util.ArrayList;

/**
 *
 * @author Chan
 */
public class Inteligencia {
    
    private int board[][] = new int[3][3];
    
    public static final int JOGADOR_BATATEIRO = 1;
    public static final int JOGADOR_COMPUTADOR = 2;
    
    private int numJogada;
    private int jogadorAtual;
    
    public Inteligencia(){
        // TODO configurar quem inicia na tela ??
        jogadorAtual = JOGADOR_BATATEIRO;
        
        resetBoard();
    }
    
    // int jogador é JOGADOR_BATATEIRO ou JOGADOR_COMPUTADOR
    public void set(int linha, int coluna, int jogador){
        board[linha][coluna] = jogador;
        numJogada++;
    }
    
    
    /*
     * FUNÇÕES DE TABULEIRO
    */
    private void resetBoard(){
        for(int c1 = 0; c1 < 3; c1++){
            for(int c2 = 0; c2 < 3; c2++){
                board[c1][c2] = 0;
            }
        }
        
        numJogada = 0;
    }
    
    private void checkBoard(){
        if(checkBoardForPlayer(JOGADOR_BATATEIRO)){
            System.out.println("BATATEIRO GANHOU !!!!");
            //TODO o que fazer aqui ??
        }else if(checkBoardForPlayer(JOGADOR_COMPUTADOR)){
            System.out.println("BATATEIRO PERDEU !!!!");
            //TODO o que fazer aqui ??
        }else if(checkDraw()){
            System.out.println("DEU BATATA VELHA!!!!");
            //TODO o que fazer aqui ??
        }
    }
    
    private boolean checkBoardForPlayer(int jogador){
        
        //Verifica linhas
        for(int c1 = 0; c1 < 3; c1++){
            if(board[c1][0] == JOGADOR_BATATEIRO && board[c1][1] == JOGADOR_BATATEIRO && board[c1][2] == JOGADOR_BATATEIRO){
                return true;
            }
        }
        //Verifica colunas
        for(int c1 = 0; c1 < 3; c1++){
            if(board[0][c1] == JOGADOR_BATATEIRO && board[1][c1] == JOGADOR_BATATEIRO && board[2][c1] == JOGADOR_BATATEIRO){
                return true;
            }
        }
        
        //Verifica cruzados
        if(board[0][0] == JOGADOR_BATATEIRO && board[1][1] == JOGADOR_BATATEIRO && board[2][2] == JOGADOR_BATATEIRO){
            return true;
        }
        if(board[0][2] == JOGADOR_BATATEIRO && board[1][1] == JOGADOR_BATATEIRO && board[2][0] == JOGADOR_BATATEIRO){
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
            board[linha][0] = JOGADOR_COMPUTADOR;
            numJogada++;
        }
        if(board[linha][1] == 0 ){
            board[linha][1] = JOGADOR_COMPUTADOR;
            numJogada++;
        }
        if(board[linha][2] == 0 ){
            board[linha][2] = JOGADOR_COMPUTADOR;
            numJogada++;
        }
    }
    
    private void eliminaPerigoColuna(int coluna){
        if(board[0][coluna] == 0 ){
            board[0][coluna] = JOGADOR_COMPUTADOR;
            numJogada++;
        }
        if(board[1][coluna] == 0 ){
            board[1][coluna] = JOGADOR_COMPUTADOR;
            numJogada++;
        }
        if(board[2][coluna] == 0 ){
            board[2][coluna] = JOGADOR_COMPUTADOR;
            numJogada++;
        }
    }
    
    private void computerMove(){
        
        if(numJogada == 1){
            if(board[1][1] == JOGADOR_BATATEIRO){
                board[0][1] = JOGADOR_COMPUTADOR;
            }else{
                board[1][1] = JOGADOR_COMPUTADOR;
            }
        }
        
        if(numJogada >= 3){
            if(! perigo()){
                //TODO VERIFICAR SE PODE GANHAR EM UMA JOGADA (igual a função PERIGO(), mas ao contrário)
                //Se não puder, jogar em um lugar randomico.
                
                numJogada++;
            }
        }
        
        
    }
}
