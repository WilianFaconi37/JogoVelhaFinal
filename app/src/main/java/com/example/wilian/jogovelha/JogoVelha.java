package com.example.wilian.jogovelha;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.internal.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.wilian.jogovelha.R.id.ivImage;
import static com.example.wilian.jogovelha.R.layout.activity_jogo_velha;


public class JogoVelha extends AppCompatActivity {

    Inteligencia inteligencia = new Inteligencia();

    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, txtDerrotas, txtEmpates, txtVitorias;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageView ivImage;
    private View viewImage;
    private String userChoosenTask;

    // Auth: Gabriel Andreatto
    // date: 23/11/2016 03:07 am
    // Usado para converter int para String enviada pelo metodo goShareResultGame()
    String derrotas;
    String empates;
    String vitorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_jogo_velha);
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


        ivImage = (ImageView) findViewById(R.id.ivImage);
        viewImage = (View) findViewById(R.id.viewImage);
        ivImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

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
                    getTextViewByPosition(c1, c2).setBackgroundResource(R.drawable.srbatata);
                }else if(board[c1][c2] == Inteligencia.JOGADOR_COMPUTADOR){
                    getTextViewByPosition(c1, c2).setBackgroundResource(R.drawable.srabatata);
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



    //Api Camera
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Seguranca.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Tirar Foto"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Escolher na galeria"))
                        galleryIntent();
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Tirar Foto", "Escolher na galeria" };

        AlertDialog.Builder builder = new AlertDialog.Builder(JogoVelha.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Seguranca.checkPermission(JogoVelha.this);

                if (items[item].equals("Tirar Foto")) {
                    userChoosenTask ="Tirar Foto";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Escolher na galeria")) {
                    userChoosenTask ="Escolher na galeria";
                    if(result)
                        galleryIntent();

                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecione o Arquivo"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewImage.setBackground(Drawable.createFromPath(destination.getPath()) );
    }

    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       // ivImage2.setImageBitmap(bm);
    }



}
