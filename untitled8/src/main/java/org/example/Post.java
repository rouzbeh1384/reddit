package org.example;

public class Post {
    private String Title;

    private String text;

    private String subreddit;

    private int Time;
    public Post(String Title,String text,String subreddit,int Time){
        this.subreddit=subreddit;
        this.Time=Time;
        this.text=text;
        this.Title=Title;
    }

    public void setTitle(String Title){
        this.Title=Title;
    }
    public void setText(String text){
        this.text=text;
    }
    public void setSubreddit(String subreddit){
        this.subreddit=subreddit;
    }
    public void setTime(int Hour ){
        this.Time=Hour;
    }

    public String getTitle(){
        return this.Title;
    }

    public String getSubreddit(){
        return this.subreddit;
    }

    public String getText(){
        return this.text;
    }

    public int getTime(){
        return this.Time;
    }
}
