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
            if (a == 1) {
                System.out.print("enter Username");
                User user = new User(scanner.next(), pass, emial);
                users.add(user);
            } else {
                if (users.isEmpty()) {System.out.print("No account \n\n");}
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
            while (true) {
                System.out.println("1-join to subreddit 2- creat sub 3-Show my subreddit 4-21-exit");
                Scanner scanner = new Scanner(System.in);
                int i = 1;
                switch (scanner.nextInt()) {


                    case 1: {
                        if (!Sub.isEmpty()) {
                            for (SubReddit xSubReddit : Sub) {

                                System.out.println(i++ + "-" + xSubReddit.Name);
                            }
                            System.out.println("inter Id ");
                            i=scanner.nextInt();

                            Sub.get(i-1).joind(x);
                            x.ownSubreddit.add(Sub.get(i-1));
                        } else System.out.println("No subreddit");
                    }
                    break;
                    case 2: {
                        System.out.print("enter  name :");
                        String name = scanner.next();
                        System.out.print("password: ");
                        String pass = scanner.next();
                        try {
                            SubReddit subReddit = new SubReddit(name, x, pass);
                            Sub.add(subReddit);
                            System.out.println("Successful\n\n ");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                    case 3: {
                        int id = 0;
                        try {
                            for (SubReddit c : x.ownSubreddit) {
                                System.out.print(++id + " " + c.Name + "\n");
                            }
                            System.out.print("you can see post of sub please choose ");
                            int a = scanner.nextInt() - 1;
                            if(!Sub.get(a).posts.isEmpty()) {
                                for (int w = 0; w < Sub.get(a).posts.size(); w++)
                                    System.out.print(Sub.get(a).posts.get(w));
                            }
                            System.out.print("see post in sub reddit ?  TRUE 0R FALSE ");
//                            if (scanner.next().equals( "true")) {
//                                i = scanner.nextInt();
//                                for (Post p : Sub.get(i - 1).posts) {
//                                    System.out.println(p.getName() + " ");//TODO --> time and writer
//                                }
//                            }
                            System.out.print("Add  post  ?  TRUE 0R FALSE ");
                            if (scanner.next().equals( "true")) {
                                String srt = scanner.next();
                                Post post = new Post(srt);
                                Sub.get(i - 1).posts.add(post);

                            }


                        } catch (Exception e) {
                            System.out.print("no\n");
                        }
                    }

                }
            }



    }





}
