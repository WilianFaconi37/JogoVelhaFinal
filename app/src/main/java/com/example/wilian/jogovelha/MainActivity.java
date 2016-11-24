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

        import com.facebook.AccessToken;
        import com.facebook.login.LoginManager;

        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //Toast.makeText(getApplicationContext(), "Testando redirect ", Toast.LENGTH_SHORT).show();
        // verifica se user esta logado ou nao
        if( AccessToken.getCurrentAccessToken() == null ) {
            goLoginScreen();
        }
    }

    // Auth: Gabriel Andreatto
    // date: 23/11/2016 02:12 am
    // Botao Jogar da activity
    // este metodo chama a Activity Jogo da velha para jogar
    // sem passar parametros
    public void goPlayGame( View view ) {
        Intent intent = new Intent( this, JogoVelha.class );
        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity( intent );
    }

    // Auth: Gabriel Andreatto
    // date: 23/11/2016 02:12 am
    // Botao Jogar da activity
    // este metodo chama a Activity login novamente caso o usuario esteja null
    // sem passar parametros
    private void goLoginScreen() {
        Intent intent = new Intent( this, LoginActivity.class );
        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity( intent );
    }

    // Auth: Gabriel Andreatto
    // date: 23/11/2016 02:12 am
    // Botao Jogar da activity
    // este metodo faz logout facebook e app
    // sem passar parametros e encaminha para Activity login
    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }



}
