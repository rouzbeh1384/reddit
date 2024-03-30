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
    public SubReddit(String NameSub,User owner,String password) throws IOException {
        super(password);
        Name=NameSub;
        users=new ArrayList<>();
        posts=new ArrayList<>();
        FileWriter Name= new FileWriter("file.txt");
        Name.write("mt name is rouzbeh");
        Owner_Addmin=new ArrayList<>();
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





}
