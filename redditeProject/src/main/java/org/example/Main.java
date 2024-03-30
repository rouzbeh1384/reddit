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
            System.out.println("hi "+x.Get_username());
            while (true) {
                System.out.println("1-join to subreddit 2- creat sub 3-Show my subreddit 4-21-exit");
                Scanner scanner = new Scanner(System.in);
                int i = 1;
                int re=scanner.nextInt();
                if (re==21)
                    break;

                switch (re) {


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
                        Use_Sub_reddit(x,Sub);
                    }
                    break;
                }
            }



    }
    public static void Use_Sub_reddit(User x,ArrayList<SubReddit> Sub){
        Scanner scanner=new Scanner(System.in);
        int id = 0;
        try {
            for (SubReddit c : x.ownSubreddit) {
                System.out.print(++id + " --- " + c.Name +" |--->  "+c.Show_notify()+"  |-----| "+c.getHourTime()+
                        " |-----| "+ c.Owner_Addmin.get(0)+ "\n");
            }
            System.out.print("you can see post of sub please choose ");
            int a = scanner.nextInt() - 1;
            if(!Sub.get(a).posts.isEmpty()) {
                for (int w = 0; w < Sub.get(a).posts.size(); w++)
                    System.out.print(Sub.get(a).posts.get(w).getName()+"\n"
                            +Sub.get(a).posts.get(w).TimeH()+":"+Sub.get(a).posts.get(w).TimeM()+
                            "\n"+"By: "+Sub.get(a).posts.get(w).writer()+"\n\n");
            }

            System.out.println("Add  post  ? (pattern y|yes|1|ok post) ");
//            System.out.println("1- Add post  2- like or dislike 3-show comment 4-add comment   ");

            String str=scanner.next();
            if (str.equals("yes")||str.equals("y")||str.equals("1")||str.equals("ok")) {
                String srt;
                do {
                     srt= scanner.nextLine();
                    System.out.println("\n \n are you sure no ?"+ srt);
                }while (scanner.next().equals("no")||scanner.next().equals("0")||scanner.next().equals("n"));

                Post post = new Post(srt);

                Sub.get(a).Set_post(post);




            }

        } catch (Exception e) {
            System.out.print("no\n");
        }

    }





}
