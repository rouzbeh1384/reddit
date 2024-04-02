package org.example;

import java.time.LocalTime;
import java.util.ArrayList;

public class Post {

    private String Name;

    private int Like;
    private int dis_Like;


    private final int  HourTime;
    private final int MinTime;
    private User Owner;

    public ArrayList<Post>comment;



    public Post(String string,User x){
        this.Name=string;
        LocalTime currentTime = LocalTime.now();
        HourTime= currentTime.getHour();
        MinTime=currentTime.getMinute();
        comment=new ArrayList<>();
        this.Owner=x;


    }

    public String getName()
    {
        return this.Name;
    }

    public void setLike()
    {
        this.Like+=1;
    }

    public void setDis_Like()
    {
        this.dis_Like-=1;
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
    public void  setComment(String string,User x){
        Post comment=new Post(string,x);
        this.comment.add(comment);
    }
    public void ShowComment()
    {
        int e=1;
        for (int i=0;i<this.comment.size();i++)
        {
            System.out.println((i+1) +"   " +this.comment.get(i).getName() +" |---|  " +this.comment.get(i).TimeH()+" : "+this.comment.get(i).TimeM()+"  "+"\u270D  "+this.comment.get(i).Owner.Get_username());
        }
    }
    public String ShowLike(){
        return String.valueOf(Like);
    }
    public String ShowDisLike(){
        return String.valueOf(this.dis_Like);
    }


}
