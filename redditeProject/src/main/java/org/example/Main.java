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

        ArrayList<SubReddit> subReddits = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("hi your welcome to rouzddit");

        while (true) {


            System.out.print("1-create Account 2-enter ");
            int a = scanner.nextInt();
            System.out.print("enter yor email :");
            String emial = scanner.next();
            System.out.print("enter your pass word: ");
            String pass = scanner.next();
            if (a == 1) {
                System.out.print("enter Username: ");
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
                System.out.println("1-join to subreddit 2- creat sub 3-Show my subreddit 4-see profile   21-exit");
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
                    case 4:
                        Run_profile(x,Sub);
                }break;
            }



    }
    public static void Use_Sub_reddit(User x,ArrayList<SubReddit> Sub) {
        Scanner scanner = new Scanner(System.in);
        int a=-1;
        boolean move = true;
        int id = 0;
        try {
            for (SubReddit c : x.ownSubreddit) {
                System.out.print(++id + " --- " + c.Name + " |--->  " + c.Show_notify() + "  |-----| " + c.getHourTime() +
                        " |-----| " + c.Owner_Addmin.get(0) + "\n");
            }
            System.out.print("you can see post of sub please choose ");
            a = scanner.nextInt() - 1;
            if (!Sub.get(a).posts.isEmpty()) {
                for (int w = 0; w < Sub.get(a).posts.size(); w++)
                    System.out.print((w+1)+" "+Sub.get(a).posts.get(w).getName() + "\n"
                            + Sub.get(a).posts.get(w).TimeH() + ":" + Sub.get(a).posts.get(w).TimeM() +
                            "\n" + "By: " + Sub.get(a).posts.get(w).writer() + "\n\n");
            }
        } catch (Exception e) {
            System.out.println("Not Successful ");
            move = false;
        }

        if (move == true) {

            System.out.println("1- Add post  2- like or dislike 3-Add comment 4-show comment   ");
            switch (scanner.nextInt()) {
                case 1: {
                    try {
                        String srt,s;

                        do {
                            srt = scanner.nextLine();
                            System.out.println("\n \n are you sure no ?" + srt);
                            s=scanner.next();
                        } while (s.equals("no") || s.equals("0") || s.equals("n"));

                        Post post = new Post(srt,x);
                        Sub.get(a).Set_post(post);
                    } catch (Exception e) {
                        System.out.print("no\n");
                    }
                }
                break;
                case 2:{
                System.out.println("enter number of post like or dislike  ");
                int number =scanner.nextInt();
                System.out.println("like 1 dislike 0 ");
                    if (scanner.nextInt()==1)
                        Sub.get(a).posts.get(number-1).setLike();
                    else
                        Sub.get(a).posts.get(number-1).setDis_Like();
                }break;
                case 3:
                {  System.out.println("enter number of post for add  comment ");
                    int number =scanner.nextInt();
                        do {
                        System.out.println("enter your comment :");
                        String string=scanner.nextLine();
                        Sub.get(a).posts.get(number).setComment(string,x);
                        System.out.println("Are you sure 1-sure ");
                    }while (scanner.nextInt()!=1);
                }break;
                case 4:{
                    System.out.println("enter number of post for show comment ");
                    int number =scanner.nextInt();
                    Sub.get(a).posts.get(number-1).ShowComment();
                }break;

            }


        }


    }

    public static void Run_profile(User x,ArrayList<SubReddit>Sub){

        ArrayList<Post> posts = new ArrayList<>();
        posts.clear();
        for (int i=0;i<Sub.size();i++){
            posts.add(x.ownSubreddit.get(i).newest());
        }

        //sort
        for (int i = 0; i < posts.size() - 1; i++) {
            for (int j = 0; j < posts.size() - i - 1; j++) {

                if (posts.get(j).TimeH() > posts.get(j + 1).TimeM()) {

                    Post temp = posts.get(j);
                    posts.set(j, posts.get(j + 1));
                    posts.set(j + 1, temp);
                }
            }
        }

        System.out.println("|--------------------------------------------------------------------------------|\n" +
                           "|--------------------------new post in ------------------------------------------|\n" +
                           "|--------------------------your subreddit ---------------------------------------|\n" +
                           "|--------------------------that you follow---------------------------------------|");
        for(int i=0;i<4 && i <posts.size();i++){
            System.out.println("| "+posts.get(i).getName()+" | " + posts.get(i).writer()+" | "+posts.get(i).TimeH()+"|");
        }
        System.out.println("|--------------------------------------------------------------------------------|\n" +
                           "|--------------------------------------------------------------------------------|");




        System.out.println("1-save post    7-change passWord  8- change email  9-change username 21-exit");
    }







}
