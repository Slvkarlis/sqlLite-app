package com.example.myapplication.Controller;

import android.content.Context;

import com.example.myapplication.Model.AccesLocal;
import com.example.myapplication.Model.Profil;
import com.example.myapplication.outils.Serializer;

import java.util.Date;

public final class Controle {

    private static Controle instance=null;
    private static Profil profil;
    private static String nomFic="saveprofil";
    private static AccesLocal accesLocal;
    /**
     * contructeur private
     */
    private Controle(){
        super();
    }

    /**
     *
     * @return instance
     */
    public static final Controle getInstance(Context contexte){
        if(Controle.instance==null){
            Controle.instance=new Controle();
            accesLocal=new AccesLocal(contexte);
            profil=accesLocal.recupDernier();
            //recupSerialize(contexte);
        }
        return Controle.instance;
    }

    /**
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte){
        profil=new Profil(new Date(),poids,taille,age,sexe);
        accesLocal.ajout(profil);
        //Serializer.serialize(nomFic,profil,contexte);
    }

    /**
     *
     * @return recuperation img du profil
     */
    public float getImg(){
        return profil.getImg();
    }

    /**
     *
     * @return recuperation message du profil
     */
    public String getMessage(){
        return profil.getMessage();
    }

    /**
     * recuperation de l'objet serialisé(profil)
     * @param contexte
     */
    private static void recupSerialize(Context contexte){
        profil=(Profil) Serializer.deSerialize(nomFic,contexte);
    }

    public Integer getPoids(){
        if(profil==null){
            return null;
        }else{
            return profil.getPoids();
        }
    }
    public Integer getAge(){
        if(profil==null){
            return null;
        }else{
            return profil.getAge();
        }
    }
    public Integer getTaille(){
        if(profil==null){
            return null;
        }else{
            return profil.getTaille();
        }
    }
    public Integer getSexe(){
        if(profil==null){
            return null;
        }else{
            return profil.getSexe();
        }
    }
}
