package com.example.wilian.jogovelha;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class ResultActivity extends AppCompatActivity {

    TextView userName;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    ShareDialog shareDialog;
    //ShareButton shareButton;
    String nomeCompelto;
    Button buttonFbShr;

    String derrotas;
    String empates;
    String vitorias;
    String resultado;

    TextView txtDerrota;
    TextView txtEmpate;
    TextView txtVitoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        callbackManager = CallbackManager.Factory.create();

        userName = (TextView) findViewById( R.id.textViewUserName );
        //shareButton = (ShareButton) findViewById( R.id.fb_share_button );
        buttonFbShr = ( Button ) findViewById( R.id.fb_share_button );
        txtDerrota = ( TextView ) findViewById( R.id.textViewResDerrota );
        txtEmpate = ( TextView ) findViewById( R.id.textViewResEmpate);
        txtVitoria = ( TextView ) findViewById( R.id.textViewResVitoria );


        shareDialog = new ShareDialog(this);
        Bundle result = getIntent().getExtras();
        if( result != null ) {
            derrotas = result.getString( "derrotas" );
            empates = result.getString( "empates" );
            vitorias = result.getString( "vitorias" );

        }

        initFbData();
        setDataUser();
        fnctBtnShare();
        setResPlacar( derrotas, empates, vitorias );


    }

    private void setResPlacar(String d, String e, String v) {

        txtDerrota.setText( derrotas );
        txtEmpate.setText( empates );
        txtVitoria.setText( vitorias );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult( requestCode, resultCode, data );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    private void fnctBtnShare() {
        buttonFbShr.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse("https://developers.facebook.com"))
                            //®.setContentUrl(Uri.parse("https://www.facebook.com/Sr-Batata-Jogo-da-Velha-1633334963352758/") )
                            .setContentTitle("Jogo da Velha # - Sr Batata ")
                            .setImageUrl( Uri.parse("http://nteck.com.br/projeto/logoJogo.png") )
                            .setContentDescription("Placar: Derrota: " + derrotas + " Empate: " + empates + " Vitoria: " + vitorias )
                            .build();
                    //buttonFbShr.setShareContent(content);
                    shareDialog.show(content);
                }
            }

        });

    }

    private void initFbData() {
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access token is available already assign it.
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                // App code

            }
        };
    }


    private void setDataUser() {
        //Profile profile = Profile.getCurrentProfile();
        nomeCompelto = Profile.getCurrentProfile().getName();
        userName.setText( nomeCompelto );
        ((ProfilePictureView)findViewById(R.id.profilePicture)).setProfileId(
                Profile.getCurrentProfile().getId()
        );
    }

    public void redirecionar(View view) {

        goMainScreen();

    }

    private void goMainScreen() {
        Intent intent = new Intent( this, MainActivity.class );
        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );

        startActivity( intent );

    }

    // Inserir novos metodos abaixo PLS 4x0

}
