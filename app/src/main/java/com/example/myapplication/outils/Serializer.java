package com.example.myapplication.outils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public abstract class Serializer {

    /**
     *
     * @param filename
     * @param object
     * @param context
     */
    public static void serialize(String filename, Object object, Context context){
        try{
            FileOutputStream file=context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos;
            try {
                oos=new ObjectOutputStream(file);
                oos.writeObject(object);
                oos.flush();
                oos.close();
            } catch (IOException e) {
                //erreur de serialisation
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            //fichier non trouve
            e.printStackTrace();
        }
    }

    /**
     *
     * @param filename
     * @param context
     * @return
     */
    public static Object deSerialize(String filename, Context context){
        try {
            FileInputStream file=context.openFileInput(filename);
            ObjectInputStream ois;
            try {
                ois=new ObjectInputStream(file);
                try {
                    Object object=ois.readObject();
                    ois.close();
                    return object;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }catch(StreamCorruptedException e){
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
