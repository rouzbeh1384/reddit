package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends  Account{
    public ArrayList<Post>ownPost;

    public ArrayList<SubReddit> ownSubreddit;

    private String userName;


    public User(String User,String PassWord,String Email) {
        super(PassWord,Email);
        this.userName=User;
        ownSubreddit=new ArrayList<>();
        ownPost=new ArrayList<>();
    }

    public void SetPost(Post post) {
    this.ownPost.add(post);
    }
    public void Like(Post post)
    {
        int coneter =0;
        for(Post x:ownPost)
        {
            System.out.println(coneter++ +" "+x.getName());
        }
        Scanner scanner=new Scanner(System.in);
        int number =scanner.nextInt();
        System.out.println("Like or lis like Y / N ");
        if(scanner.next().equals("Y"))
            this.ownPost.get(number).setLike();
        else
            this.ownPost.get(number).setDis_Like();
    }


    public void getHour() {
        LocalTime currentTime = LocalTime.now();
        int HourTime= currentTime.getHour();
        if (super.getHourTime() - HourTime < 1){
            System.out.println("You free time is ended   ");
            System.out.println("pay for start premium  ");

        }
    }


    public String Get_username(){
        return this.userName;
    }
}
