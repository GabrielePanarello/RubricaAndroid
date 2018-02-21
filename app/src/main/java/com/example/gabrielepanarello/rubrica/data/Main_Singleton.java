package com.example.gabrielepanarello.rubrica.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriele Panarello on 15/02/2018.
 */

public class Main_Singleton {

    private static Main_Singleton ourInstance = new Main_Singleton();
    private static List<Contact> contactsList;

    public static Main_Singleton getInstance(){
        return ourInstance;
    }

    private Main_Singleton(){
        List<Contact> contactList = new ArrayList<Contact>();
    }

    public static List<Contact> getItemList() {
        return contactsList;
    }

    public static void setContactList(List<Contact> singleContactList) {
        contactsList = singleContactList;
    }


}
