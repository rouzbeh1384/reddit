package org.example;

import java.util.ArrayList;

public class Subreddit {

    private String Title;



    public ArrayList<User> userName;

    public ArrayList<User> admin;
    private int Time;
    public ArrayList<Post> posts;


    public int Number;


    public Subreddit(String Title,int Time){
        this.Title=Title;
        this.Time=Time;
        userName=new ArrayList<>();
        admin=new  ArrayList<>();
        posts=new ArrayList<>();

    }
    public void setTitle(String Title){
        this.Title=Title;
    }

    public void setUserName(User userName){
        this.userName.add(userName);
    }

    public ArrayList<User> getAdmin() {
        return admin;
    }

    public ArrayList<User> getUserName() {
        return userName;
    }

    public void setTime(int Hour ){
        this.Time=Hour;
    }

    public String getTitle(){
        return this.Title;
    }

    public int getTime(){
        return this.Time;
    }

    public void setAdmin(User admin) {
        this.admin.add(admin);
    }

    public void setPosts(Post post) {
        this.posts.add(post);
    }

    public Post newest() {
        return posts.get(posts.size()-1);
    }
}
