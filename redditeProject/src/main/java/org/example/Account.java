package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.UUID;

public class Account {

    private String Email;

    private String passWord;



    private UUID uuid;

    private int HourTime;

    private int MinTime;

    public Account(String passWord,String email)
    {
        uuid= UUID.randomUUID();
        this.passWord=passWord;
        this.Email=email;
        LocalTime currentTime = LocalTime.now();
        HourTime= currentTime.getHour();
        MinTime=currentTime.getMinute();
    }

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

    public int getHourTime() {
        return HourTime;
    }

    public int getMinTime() {
        return MinTime;
    }

}
