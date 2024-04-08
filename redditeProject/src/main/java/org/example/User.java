package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends  Account{
    public ArrayList<Post>ownPost;


    public ArrayList<Post>massage;

    private int maassage=0;
    public ArrayList<SubReddit> ownSubreddit;

    public ArrayList<Post>action_post;
    private ArrayList<Post>Sava_post;
    private String userName;

    public ArrayList<User>Friend;

    public User(String User,String PassWord,String Email) {
        super(PassWord,Email);
        this.userName=User;
        ownSubreddit=new ArrayList<>();
        ownPost=new ArrayList<>();
        Friend=new ArrayList<>();
        massage=new ArrayList<>();
        Sava_post=new ArrayList<>();
    }

    public void SetPost(Post post) {
    this.ownPost.add(post);
    }



    public void getHour() {
        LocalTime currentTime = LocalTime.now();
        int HourTime= currentTime.getHour();
        if (super.getHourTime() - HourTime < 1){
            System.out.println("You free time is ended   ");
            System.out.println("pay for start premium  ");

        }
    }
    public ArrayList<Post> getPost(){
        return Sava_post;
    }
    public void setSava_post(Post  post){
        Sava_post.add(post);
    }

    public String Get_username(){
        return this.userName;
    }

    public void changeUsername (String pass ,String newUser){
        if(this.verifyPassWord(pass)){
            this.userName=newUser;
        }
    }

    public void setFriend(User user){
        this.Friend.add(user);
    }
    public void setMassage(Post post){
        this.massage.add(post);
    }


    public boolean verifyUsername(String user){
        if (user.equals(this.userName))return true;

        return false;
    }





}
