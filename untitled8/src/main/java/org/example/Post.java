package org.example;

import java.util.ArrayList;

public class Post {
    private String Title;

    private String text;

    private Subreddit subreddit;

    private User writer;
    private int Like;
    private int DisLike;

    private ArrayList<User>Action;
    public ArrayList<comment>comments;

    private int Time;
    public Post(String Title,String text,int Time,int like,int disLike){
        this.Like=like;
        this.DisLike=disLike;
        Action=new ArrayList<>();
        this.Time=Time;
        this.text=text;
        this.Title=Title;
        this.comments=new ArrayList<>();
    }

    public void setTitle(String Title){
        this.Title=Title;
    }
    public void setText(String text){
        this.text=text;
    }
    public void setSubreddit(Subreddit subreddit){
        this.subreddit=subreddit;
    }
    public void setTime(int Hour ){
        this.Time=Hour;
    }

    public String getTitle(){
        return this.Title;
    }

    public Subreddit getSubreddit(){
        return this.subreddit;
    }

    public User getWriter() {
        return writer;
    }

    public void setAction(User action) {
        Action.add(action);
    }

    public ArrayList<User> getAction() {
        return Action;
    }

    public String getText(){
        return this.text;
    }

    public int getTime(){
        return this.Time;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
    public void setComments(comment comments){
        this.comments.add(comments);
    }
    public  void ShowComment(){
        for (comment comment:comments){
            System.out.println(comment.getText()+"|________|"+comment.getTime());
        }
    }

    public int Like() {
        return Like;
    }
    public int DisLike() {
        return DisLike;
    }

    public void setDisLike() {
         DisLike++;
    }
    public void setLike() {
        Like++;
    }

}
