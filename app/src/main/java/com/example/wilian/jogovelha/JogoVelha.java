package com.example.wilian.jogovelha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;




public class JogoVelha extends AppCompatActivity {

    Inteligencia inteligencia = new Inteligencia();

    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, txtDerrotas, txtEmpates, txtVitorias;

    // Auth: Gabriel Andreatto
    // date: 23/11/2016 03:07 am
    // Usado para converter int para String enviada pelo metodo goShareResultGame()
    String derrotas;
    String empates;
    String vitorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_velha);
        tv1 = (TextView) findViewById(R.id.a1);
        tv2 = (TextView) findViewById(R.id.a2);
        tv3 = (TextView) findViewById(R.id.a3);
        tv4 = (TextView) findViewById(R.id.b1);
        tv5 = (TextView) findViewById(R.id.b2);
        tv6 = (TextView) findViewById(R.id.b3);
        tv7 = (TextView) findViewById(R.id.c1);
        tv8 = (TextView) findViewById(R.id.c2);
        tv9 = (TextView) findViewById(R.id.c3);

        txtDerrotas = (TextView) findViewById(R.id.txtDerrotas);
        txtEmpates = (TextView) findViewById(R.id.txtEmpates);
        txtVitorias = (TextView) findViewById(R.id.txtVitorias);

    }

    public void atualizarBotao(View view){

        int qual = Integer.parseInt(view.getTag().toString());
        int resultado = 0;

        switch(qual){
            case 1:

                if(inteligencia.getBoard()[0][0] == 0){

                    inteligencia.set(0, 0, Inteligencia.JOGADOR_BATATEIRO);
                    desenhaTabuleiro();
                    resultado = inteligencia.checkBoard();
                }else{
                    Toast.makeText(this,"Campo Preenchido!",Toast.LENGTH_SHORT).show();
                }

                break;
            case 2:

                if(inteligencia.getBoard()[0][1] == 0){
                    inteligencia.set(0, 1, Inteligencia.JOGADOR_BATATEIRO);
                    desenhaTabuleiro();
                    resultado = inteligencia.checkBoard();
                }else{
                    Toast.makeText(this,"Campo Preenchido!",Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if(inteligencia.getBoard()[0][2] == 0){

                    inteligencia.set(0, 2, Inteligencia.JOGADOR_BATATEIRO);
                    desenhaTabuleiro();
                    resultado = inteligencia.checkBoard();
                }else{
                    Toast.makeText(this,"Campo Preenchido!",Toast.LENGTH_SHORT).show();
                }

                break;
            case 4:
                if(inteligencia.getBoard()[1][0] == 0) {
                    inteligencia.set(1, 0, Inteligencia.JOGADOR_BATATEIRO);
                    desenhaTabuleiro();
                    resultado = inteligencia.checkBoard();
                }else{
                    Toast.makeText(this,"Campo Preenchido!",Toast.LENGTH_SHORT).show();
                }

                break;
            case 5:
                if(inteligencia.getBoard()[1][1] == 0){

                    inteligencia.set(1, 1, Inteligencia.JOGADOR_BATATEIRO);
                    desenhaTabuleiro();
                    resultado = inteligencia.checkBoard();
                }else{
                    Toast.makeText(this,"Campo Preenchido!",Toast.LENGTH_SHORT).show();
                }

                break;
            case 6:
                if(inteligencia.getBoard()[1][2] == 0){
                    inteligencia.set(1, 2, Inteligencia.JOGADOR_BATATEIRO);
                    desenhaTabuleiro();
                    resultado = inteligencia.checkBoard();
                }else{
                    Toast.makeText(this,"Campo Preenchido!",Toast.LENGTH_SHORT).show();
                }

                break;
            case 7:

                if(inteligencia.getBoard()[2][0] == 0){
                    inteligencia.set(2, 0, Inteligencia.JOGADOR_BATATEIRO);
                    desenhaTabuleiro();
                    resultado = inteligencia.checkBoard();
                }else{
                    Toast.makeText(this,"Campo Preenchido!",Toast.LENGTH_SHORT).show();
                }
                break;
            case 8:

                if(inteligencia.getBoard()[2][1] == 0){
                    inteligencia.set(2, 1, Inteligencia.JOGADOR_BATATEIRO);
                    desenhaTabuleiro();
                    resultado = inteligencia.checkBoard();
                }else{
                    Toast.makeText(this,"Campo Preenchido!",Toast.LENGTH_SHORT).show();
                }
                break;

            case 9:
                if(inteligencia.getBoard()[2][2] == 0){
                    inteligencia.set(2, 2, Inteligencia.JOGADOR_BATATEIRO);
                    desenhaTabuleiro();
                    resultado = inteligencia.checkBoard();

                }else{
                    Toast.makeText(this,"Campo Preenchido!",Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                System.out.println("Nenhum!");
        }


        if(resultado != 0){
            restart(resultado);
        }else {
            if(inteligencia.getJogadorAtual() == Inteligencia.JOGADOR_COMPUTADOR){
//                try{
//                    Thread.sleep(1000);
//                }catch (InterruptedException e){
//
//                }
                inteligencia.computerMove();
                desenhaTabuleiro();

                resultado = inteligencia.checkBoard();
                if (resultado != 0) {
                    restart(resultado);
                }
            }
        }
    }

    private void desenhaTabuleiro(){
        int board[][] = inteligencia.getBoard();

        for(int c1 = 0; c1 < 3; c1++){
            for(int c2 = 0; c2 < 3; c2++){

                if(board[c1][c2] == Inteligencia.JOGADOR_BATATEIRO){
                    getTextViewByPosition(c1, c2).setBackgroundResource(R.drawable.java);
                }else if(board[c1][c2] == Inteligencia.JOGADOR_COMPUTADOR){
                    getTextViewByPosition(c1, c2).setBackgroundResource(R.drawable.droid);
                }else{
                    getTextViewByPosition(c1, c2).setBackgroundResource(0);
                }
            }
        }
    }

    private View getTextViewByPosition(int linha, int coluna){
        if(linha == 0 && coluna == 0 ) return tv1;
        if(linha == 0 && coluna == 1 ) return tv2;
        if(linha == 0 && coluna == 2 ) return tv3;
        if(linha == 1 && coluna == 0 ) return tv4;
        if(linha == 1 && coluna == 1 ) return tv5;
        if(linha == 1 && coluna == 2 ) return tv6;
        if(linha == 2 && coluna == 0 ) return tv7;
        if(linha == 2 && coluna == 1 ) return tv8;
        if(linha == 2 && coluna == 2 ) return tv9;

        return null;
    }

    private void restart(int resultado){
        if(resultado == Inteligencia.RESULTADO_VELHA){
            Toast.makeText(this,"Deu Velha", Toast.LENGTH_SHORT).show();
        }

        if(resultado == Inteligencia.JOGADOR_BATATEIRO){
            Toast.makeText(this,"Batateiro Ganhou !! Essa foi batata !!!", Toast.LENGTH_SHORT).show();
        }

        if(resultado == Inteligencia.JOGADOR_COMPUTADOR){
            Toast.makeText(this,"Você perdeu. Vá plantar batatas !", Toast.LENGTH_SHORT).show();
        }


        txtDerrotas.setText(""+inteligencia.getQuantidadeDerrotas());
        txtEmpates.setText(""+inteligencia.getQuantidadeEmpates());
        txtVitorias.setText(""+inteligencia.getQuantidadeVitorias());

//        try{
//            Thread.sleep(2000);
//        }catch (InterruptedException e){
//
//        }

        inteligencia.resetBoard();
        desenhaTabuleiro();
    }


    // Auth: Gabriel Andreatto
    // date: 23/11/2016 03:07 am
    // Botao encerrar da activity
    // este metodo chama a Activity Compartilhar resultados
    // passando parametros do resultado final do Jogo
    public void goShareResultGame( View view ) {
        Intent intent = new Intent( this, ResultActivity.class );
        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );

        derrotas = String.valueOf( inteligencia.getQuantidadeDerrotas() );
        empates = String.valueOf( inteligencia.getQuantidadeEmpates() );
        vitorias = String.valueOf( inteligencia.getQuantidadeVitorias() );

        intent.putExtra("derrotas", derrotas );
        intent.putExtra("empates", empates );
        intent.putExtra("vitorias", vitorias );
        startActivity( intent );
    }
}
