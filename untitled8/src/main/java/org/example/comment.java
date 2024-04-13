package org.example;

public class comment {

    private String text;
    private Post post;
    private User writer;

    private int Time;


    public comment(String text,Post post,User account,int time){
        this.post=post;
        this.Time=time;
        this.writer=account;
        this.text=text;
    }

    public User getWriter() {
        return writer;
    }

    public int getTime() {
        return Time;
    }

    public Post getPost() {
        return post;
    }

    public String getText() {
        return text;
    }
    public void setPost(Post post){
        this.post=post;
    }

}
