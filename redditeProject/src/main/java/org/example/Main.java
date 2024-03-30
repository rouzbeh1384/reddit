package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<SubReddit> subReddits = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("hi your welcome to rouzddit");

        while (true) {


            System.out.print("1-create Account 2-enter ");
            int a = scanner.nextInt();
            System.out.print("enter yor email :");
            String emial = scanner.next();
            System.out.print("enter your pass word ");
            String pass = scanner.next();
            if (a == 2) {
                System.out.print("enter Username");
                User user = new User(scanner.next(), pass, emial);
                users.add(user);
            } else {
                if (users.isEmpty()) {System.out.print("No account ");}
                else {
                    for (User x:users) {
                        if(x.verifyPassWord(pass)&x.getEmail().equals(emial))
                        runuser(x,subReddits);
                    }



                }



            }







        }
    }







    public static void   runuser(User x,ArrayList<SubReddit> Sub)
    {
            System.out.println("hi "+x.userName);
            System.out.println("1-join to subreddit 2- creat sub 3-Show my subreddit 4-21-exit");
            Scanner scanner=new Scanner(System.in);
             int i=0;
            switch (scanner.nextInt()){


                case 1:{
                    if (!Sub.isEmpty()) {
                        for (SubReddit xSubReddit : Sub) {

                            System.out.println(i++ + "-" + xSubReddit.Name);
                        }
                        System.out.println("inter Id ");
                        Sub.get(scanner.nextInt()).joind(x);
                        x.ownSubreddit.add(Sub.get(scanner.nextInt()));
                    }
                    else System.out.println("No subreddit");
                }break;
                case 2:{
                    System.out.print("enter  name :");
                    String name=scanner.next();
                    System.out.print("password: ");
                    String pass=scanner.next();
                    try {
                        SubReddit subReddit=new SubReddit(name,x,pass);
                        Sub.add(subReddit);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }break;
                case 3:
                {
                    try {
                        for (SubReddit c : x.ownSubreddit) {
                            System.out.print(c.Name+"\n");
                        }
                    }catch (Exception e){
                        System.out.print("no");
                    }
                }

            }

    }





}
