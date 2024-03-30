package org.example;

import java.time.LocalTime;

public class Post {

    private String Name;

    private int Like;

    private int  HourTime;
    private int MinTime;
    private User Owner;
    public Post(String string){
        this.Name=string;
        LocalTime currentTime = LocalTime.now();
        HourTime= currentTime.getHour();
        MinTime=currentTime.getMinute();

    }

    public String getName()
    {
        return this.Name;
    }

    public void setLike(int i)
    {
        this.Like+=i;
    }

    public int  TimeH(){
        return this.HourTime;
    }
    public int TimeM(){
        return this.MinTime;
    }
    public String writer(){
        return this.Owner.Get_username();
    }


}
