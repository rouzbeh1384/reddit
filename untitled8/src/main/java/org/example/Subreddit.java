package org.example;

public class Subreddit {

    private String Title;

    private String text;

    private String writer;

    private int Time;


    public Subreddit(String Title,String text,String writer,int Time){
        this.writer=writer;
        this.Title=Title;
        this.text=text;
        this.Time=Time;
    }
    public void setTitle(String Title){
        this.Title=Title;
    }
    public void setText(String text){
        this.text=text;
    }
    public void setWriter(String writer){
        this.writer=writer;
    }
    public void setTime(int Hour ){
        this.Time=Hour;
    }

    public String getTitle(){
        return this.Title;
    }

    public String getWriter(){
        return this.writer;
    }

    public String getText(){
        return this.text;
    }

    public int getTime(){
        return this.Time;
    }





}
