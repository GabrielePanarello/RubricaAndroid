package com.example.gabrielepanarello.rubrica.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.gabrielepanarello.rubrica.R;
import com.example.gabrielepanarello.rubrica.data.Contact;
import com.example.gabrielepanarello.rubrica.data.Main_Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriele Panarello on 15/02/2018.
 */

public class ArrayUtils {

    public static void init(){
        List<Contact> lsTmp = new ArrayList<Contact>();
        Main_Singleton.getInstance().setContactList(lsTmp);
    }

    public static List<Contact> getContactsList(){
        return Main_Singleton.getInstance().getItemList();
    }

    public static void addContact (Contact contact){
        Main_Singleton.getInstance().getItemList().add(contact);
    }

    public static void removeContact (int contactPosition){
        Main_Singleton.getInstance().getItemList().remove(contactPosition);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int getColorForPosition(Context context, int position) {
        if (position % 2 == 0) {
            return context.getColor(R.color.light_blue);
        } else {
            return context.getColor(R.color.light_gray);
        }
    }

    public static void writeOnSharedPreferences(String number, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("favourites",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("favourites",number);
        editor.commit();
    }

    public static String readOnSharedPreferences(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("favourites",Context.MODE_PRIVATE);
        return sharedPref.getString("favourites","");
    }
}
