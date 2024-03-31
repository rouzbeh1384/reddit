package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SubReddit extends Account{

    ArrayList<Post> posts;

    ArrayList<User> users;

    ArrayList<User> Owner_Addmin;
    String Name;

    int  notify=0 ;



    public SubReddit(String NameSub,User owner,String password) throws IOException {
        super(password);
        Name=NameSub;
        users=new ArrayList<>();
        posts=new ArrayList<>();
        FileWriter Name= new FileWriter("file.txt");
        Name.write("mt name is rouzbeh");
        Owner_Addmin=new ArrayList<>();
        Owner_Addmin.add(owner);
    }
    public void joind (User x){
        users.add(x);
    }

    public void setOwner_Addmin(){
        System.out.print("enter password :");
        Scanner scanner=new Scanner(System.in);
        if(verifyPassWord(scanner.next())){
            System.out.print("enter email of account ");
            for (User x:users){
                if (x.getEmail().equals(scanner.next())){
                    Owner_Addmin.add(x);
                    break;
                }
            }
        }
    }

    public void Set_post(Post post)
    {
        this.posts.add(post);
        this.notify++;
    }

    public int Show_notify(){
        if (notify!=0)
            return notify;
        return 0;
    }
    public Post newest()
    {
        return posts.get(posts.size()-1);

    }





}
