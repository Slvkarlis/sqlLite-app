package com.example.myapplication.Vue;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Controller.Controle;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    //propriétes
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView IBIIMG;
    private ImageView imgSmiley;
    private Controle controle;

    /**
     * initialisation des liens avec les objets graphiques
     */
    private void init (){
        txtPoids=(EditText)findViewById(R.id.txtPoids);
        txtTaille=(EditText)findViewById(R.id.txtTaille);
        txtAge=(EditText)findViewById(R.id.txtAge);
        rdHomme=(RadioButton)findViewById(R.id.rdHomme);
        rdFemme=(RadioButton)findViewById(R.id.rdFemme);
        IBIIMG=(TextView)findViewById(R.id.IBIIMG);
        imgSmiley=(ImageView)findViewById(R.id.imgSmiley);
        ecouteCalcul();
        this.controle=Controle.getInstance(this);
        recupProfil();
    }

    /**
     * ecoute evenement sur boutoun calcul
     */

    private void ecouteCalcul() {
        ((Button) findViewById(R.id.btnCalcul)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"test",Toast.LENGTH_SHORT).show();
                Integer poids=0;
                Integer taille=0;
                Integer age=0;
                Integer sexe=0;
                //recuperation des données saisies
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e) {};
                if(rdHomme.isChecked())
                {
                    sexe=1;
                }
                //controle des données saisies
                if(poids==0 || taille==0 || age==0)
                {
                    Toast.makeText(MainActivity.this,"Saisie incorrecte",Toast.LENGTH_SHORT).show();
                }else{
                    affichResult(poids,taille,age,sexe);
                }
            }
        });
    }

    /**
     * Affichage de l'img , du message et de l'image
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void affichResult(Integer poids, Integer taille, Integer age, Integer sexe){
        //creation du profil et récupération  des informations
        this.controle.creerProfil(poids,taille,age,sexe,this);
        float img=this.controle.getImg();
        String message=this.controle.getMessage();
        //affichage
        if(message=="normal")
        {
            imgSmiley.setImageResource(R.drawable.happy);
            IBIIMG.setTextColor(Color.GREEN);
        }else{
            IBIIMG.setTextColor(Color.RED);
            if(message=="trop faible")
            {
                imgSmiley.setImageResource(R.drawable.neutre);
            }else{
                imgSmiley.setImageResource(R.drawable.sad);
            }
        }
        IBIIMG.setText(message);
    };

    /**
     * recuperation du profil s'il y a été serialisé
     */
    private void recupProfil(){
        if(controle.getPoids()!=null){
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString());
            rdFemme.setChecked(true);
            if(controle.getSexe()==1){
                rdHomme.setChecked(true);
            }
            //simule le click sur le bouton calcul
            ((Button)findViewById(R.id.btnCalcul)).performClick();
        }
    }

}