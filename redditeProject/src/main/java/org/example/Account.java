package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.UUID;

public class Account {

    private String Email;

    private String passWord;



    private final UUID uuid;

    private final int HourTime;

    private final int MinTime;

    public Account(String passWord,String email)
    {
        uuid= UUID.randomUUID();
        this.passWord=passWord;
        this.Email=email;
        LocalTime currentTime = LocalTime.now();
        HourTime= currentTime.getHour();
        MinTime=currentTime.getMinute();
    }
    public Account(String passWord)
    {
        uuid= UUID.randomUUID();
        this.passWord=passWord;
        LocalTime currentTime = LocalTime.now();
        HourTime= currentTime.getHour();
        MinTime=currentTime.getMinute();

    }

   public boolean verifyPassWord(String passWord) {
       return this.passWord.equals(passWord);
   }

   public void changepassword(String LastpassWord,String newPassWord) {
       if(verifyPassWord(LastpassWord))
       {
           this.passWord=newPassWord;
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

    public String getEmail()
    {
        return this.Email;
    }
}
