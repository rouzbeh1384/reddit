package org.example;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;

public class Account {

    private String Email;

    private String passWord;

    private  final String Time= String.valueOf(LocalDate.now());;

    private UUID uuid= UUID.randomUUID();


   public boolean verifyPassWord(String passWord) {


       return false;
   }

   public void changepassword(String LastpassWord,String passWord) {
       if(verifyPassWord(LastpassWord)==true)
       {
           this.passWord=passWord;
       }
   }

    public UUID getUuid() {
        return uuid;
    }





}
