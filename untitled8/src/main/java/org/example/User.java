package org.example;

import java.util.ArrayList;

public class User {
    private String UserName;
    private String passWord;
    private String email;

    private int karma;

    private int Hour;
    private ArrayList<Post> savePost;


    public ArrayList<Subreddit> OwnSubreddit;

    public User(String userName,String passWord,String email,int karma,int Hour){
        this.passWord=passWord;
        this.UserName=userName;
        this.email=email;
        this.Hour=Hour;
        this.karma=karma;
        this.savePost=new ArrayList<>();
//        this.follower=new ArrayList<>();
        this.OwnSubreddit=new ArrayList<>();

    }

    private void setPassWord(String passWord){
        this.passWord=passWord;
    }
    public void changePassword(String pass,String newpass){
        if (checkPass(pass)){
            setPassWord(newpass);
        }
    }
    public boolean checkPass(String passWord){
        if(this.passWord.equals(passWord))
            return true;
        return false;
    }

    public void increaseKarma(){
            this.karma+=10;
    }
    public void dicreaseKarma(){
        this.karma-=10;
    }
    public void setEmail(String passWord,String email){
        if(checkPass(passWord)){
            this.email=email;
        }
    }

    public void setSavePost(Post NamePost){
        this.savePost.add(NamePost);
    }


    public String getUserName(){
        return this.UserName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassWord(){
        return this.passWord;
    }


    public ArrayList<Post> getSavePost(){
        return this.savePost;
    }
    public int getHour(){
        return this.Hour;
    }

    public int getKarma(){
        return this.karma;
    }

    public ArrayList<Subreddit> getOwnSubreddit() {
        return OwnSubreddit;
    }
    public void setOwnSubreddit(Subreddit subreddit){
        this.OwnSubreddit.add(subreddit);
    }



}
