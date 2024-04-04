package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;
import static java.lang.System.mapLibraryName;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();

        ArrayList<SubReddit> subReddits = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("hi your welcome to rouzddit");

        while (true) {


            System.out.print("1-create Account 2-enter 3-exit : ");
            int a = scanner.nextInt();
            if (a == 3)
                exit(0);


            System.out.print("enter yor email :");
            String email = "";
            email = scanner.next();
            while (!check_pattenr_email(email) ){
                System.out.print("enter yor email : (with this patten ----@----.---)exit 0");
                 email = scanner.next();
                 if (email.equals("0")){
                     exit(Integer.parseInt(email));
                 }
            }



            System.out.print("enter your pass word: ");
            String pass = scanner.next();

            if (a == 1  ) {
                System.out.print("enter Username: ");
                String usename =scanner.next();
                while (check_uniqe_Username(usename,users)!=0) {
                System.out.println("you user name has been used"+"|----|my suggest : " +(usename+String.valueOf(check_uniqe_Username(usename,users))));
                usename=scanner.next();
                }
                User user = new User(usename, pass, email);
                users.add(user);
            } else {
                if (users.isEmpty()) {System.out.print("No account \n\n");}
                else {
                    for (User x:users) {
                        if(x.verifyPassWord(pass)&x.getEmail().equals(email))
                        runuser(x,subReddits,users);
                    }

                }



            }







        }
    }







    public static void   runuser(User x,ArrayList<SubReddit> Sub,ArrayList<User> users) {
        System.out.println("hi " + x.Get_username());
        int re = 0;
        while (true) {
            System.out.println("1-join to subreddit 2- creat sub 3-Show my subreddit 4-see profile 5-communication  6- Subreddit  21-exit");
            Scanner scanner = new Scanner(System.in);
            int i = 1;
            re = scanner.nextInt();
            if (re == 21)
                break;

            switch (re) {
                case 1: {
                    if (!Sub.isEmpty()) {
                        for (SubReddit xSubReddit : Sub) {

                            System.out.println(i++ + "-" + xSubReddit.Name);
                        }
                        System.out.println("inter Id ");
                        i = scanner.nextInt();

                        boolean r=false;
                        for (int i1=0;i1<x.ownSubreddit.size();i1++){
                            if (x.ownSubreddit.get(i1).equals(Sub.get(i-1))){
                                 r=true;
                            }
                        }
                        if(!r){
                            Sub.get(i - 1).joind(x);
                            x.ownSubreddit.add(Sub.get(i - 1));
                        }
                        else System.out.println("you are  in Subreddit  ");

                    } else System.out.println("No subreddit");
                }
                break;
                case 2: {
                    boolean b=false;
                    String name="",pass=null;

                    do {

                        if (b){
                            System.out.println("you can use this for name  "+Sugest(name));
                        }
                        b=false;
                        System.out.print("enter  name :");
                        name = scanner.next();
                        if(name.equals("0"))
                            break;
                        System.out.print("password: ");
                            pass = scanner.next();
                            try {
                                for (int k = 0; k < Sub.size(); k++) {
                                    if (Sub.get(k).Name.equals(name)) {
                                        b = true;
                                    }
                                }
                                if (!b){
                                SubReddit subReddit = new SubReddit(name, x, pass);
                                Sub.add(subReddit);
                                subReddit.setOwner_Addmin(x);
                                x.ownSubreddit.add(subReddit);
                                System.out.println("Successful\n\n ");
                                }else System.out.println("you name has been used   ");

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                    }while (b);
                }
                break;
                case 3: {
                    Use_Sub_reddit(x, Sub);
                }
                break;
                case 4: {
                    Run_profile(x, Sub);
                }
                break;
                case 6: {
                    if (!x.ownSubreddit.isEmpty() ) {
                        for (SubReddit xSubReddit : x.ownSubreddit) {

                            System.out.println(i++ + "-" + xSubReddit.Name +can_check(xSubReddit,x));
                        }
                        System.out.println("inter Id ");
                        i = scanner.nextInt();

                        }
                    Run_admin(users,Sub.get(i-1) , x);

                }
                break;
            }

        }

    }

    private static String can_check(SubReddit xSubReddit ,User x) {
        for (int i=0;i<xSubReddit.Owner_Addmin.size();i++) {
            if (x.equals(xSubReddit.Owner_Addmin.get(i)))
                return "\uD83D\uDE46";
        }
        return "  ⛔ \uD83D\uDE45 ";
    }

    private static String Sugest(String name) {

        String text ;
        int number ;

        String Suggest = "";





        String regex = "^.*\\D+(?=\\d)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        Suggest +="____"+ name + name.substring(name.length() - 1);
        boolean a=false;
        while (matcher.find()) {
            text = matcher.group(0);
            number = Integer.parseInt(name.substring(text.length()));
            Suggest+= "____" + text + (number + 1);
            Suggest+="____"+text+text.substring(text.length()-1);
            a=true;

        }
        if (a==false) {
            Suggest+="___"+name+"1";
        }
        return Suggest;
    }

    public static void Run_admin(ArrayList<User> users,SubReddit sub,User x){

       Scanner scanner=new Scanner(System.in);
        System.out.println("1-add admin 2-delete admin 3-delete post 21-exit");
        int re=scanner.nextInt();
        while (re!=21)
        {
            switch (re){
                case 1:
                {
                    System.out.println("enter user name ");
                    String u =scanner.next();
                    System.out.println("enter pass");
                    String pass=scanner.next();
                    for (User a:users){
                        if (a.Get_username().equals(u)){
                            if(sub.verifyPassWord(pass)){
                                sub.setOwner_Addmin(a);
                            }
                            break;
                        }
                    }


                }break;
                case 2:{
                    System.out.println("enter user name ");
                    String u =scanner.next(),pass;
                    for (User a:users){
                        if (a.Get_username().equals(u)){
                            System.out.println("enter passWord od Subreddit");
                            pass=scanner.next();
                            if(sub.verifyPassWord(pass)){
                                try {
                                sub.removeAdmin(a);
                                }catch (Exception e){
                                    System.out.println("not successful ");
                                }
                            }
                            break;
                        }
                    }
                }break;
                case 3: {
                    for (int i=0;i<sub.posts.size();i++) {
                        System.out.println((i + 1) + " " + sub.posts.get(i).getName() + "  ");
                    }
                    System.out.println("Id for remove ");
                        int number =scanner.nextInt();
                        try {
                            sub.posts.remove(number - 1);
                        }catch (Exception e){
                            System.out.println("Not successful");
                        }
                }break;
            }
            System.out.println("1-add admin 2-delete admin 3-delete post 21-exit");
            re=scanner.nextInt();
        }




    }





    public static void Use_Sub_reddit(User x,ArrayList<SubReddit> Sub) {
        Scanner scanner = new Scanner(System.in);
        int a=-1;
        boolean move = true;
        int id = 0;
        try {
            if(x.ownSubreddit.isEmpty()){
                System.out.println("you have no subreddit ");
                move=false;
            }else {
                for (SubReddit c : x.ownSubreddit) {
                    System.out.print(++id + " --- " + c.Name + " |--->  " + c.Show_notify() + "  |-----| " + c.getHourTime() +
                            " |-----| " + c.Owner_Addmin.get(0).Get_username() + "\n");
                }
                System.out.print("you can see post of sub please choose ");
                a = scanner.nextInt() - 1;
                if (!Sub.get(a).posts.isEmpty()) {
                    for (int w = 0; w < Sub.get(a).posts.size(); w++)
                        System.out.print((w+1)+" "+Sub.get(a).posts.get(w).getName() +" |\n" +
                                "| "+" \uD83D\uDE0A :"+Sub.get(a).posts.get(w).ShowLike()+"\t"+"  \uD83D\uDE12 :"+ Sub.get(a).posts.get(w).ShowDisLike()+"\n"
                                + Sub.get(a).posts.get(w).TimeH() + ":" + Sub.get(a).posts.get(w).TimeM() +
                                "\n" + "\u270D"+" By: " + Sub.get(a).posts.get(w).writer() + "\n\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Not Successful ");
            move = false;
        }

        if (move == true) {

        boolean b=false;
            while (b==false) {
                System.out.println("1- Add post  2- like or dislike 3-Add comment 4-show comment 5-save post  6-Refresh 7-information     21-exit   ");
                switch (scanner.nextInt()) {
                    case 1: {
                        try {
                            String srt, s;

                            do {
                                srt = scanner.nextLine();
                                System.out.println("\n \n are you sure no ?" + srt);
                                s = scanner.next();
                            } while (s.equals("no") || s.equals("0") || s.equals("n"));

                            Post post = new Post(srt, x);
                            Sub.get(a).Set_post(post);
                        } catch (Exception e) {
                            System.out.print("no\n");
                        }
                    }
                    break;
                    case 2: {
                        System.out.println("enter number of post like or dislike  ");
                        int number = scanner.nextInt();
                        System.out.println("like 1 dislike 0 ");
                        if (scanner.nextInt() == 1)
                            Sub.get(a).posts.get(number - 1).setLike();
                        else
                            Sub.get(a).posts.get(number - 1).setDis_Like();
                    }
                    break;
                    case 3: {
                        System.out.println("enter number of post for add  comment ");
                        int number = scanner.nextInt();
                        String string ;
                        do {
                            System.out.println("enter your comment :");
                            string= scanner.nextLine();
//                            Sub.get(a).posts.get(number-1).setComment(string, x);
                            System.out.println("Are you sure 1-sure ");
                        } while (scanner.nextInt() != 1);
                        Sub.get(a).posts.get(number-1).setComment(string, x);
                    }
                    break;
                    case 4: {
                        System.out.println("enter number of post for show comment ");
                        int number = scanner.nextInt();
                        Sub.get(a).posts.get(number - 1).ShowComment();
                    }
                    break;
                    case 5:{
                        int number =scanner.nextInt();
                        try {
                            x.setSava_post(Sub.get(a).posts.get(number - 1));
                        }catch (Exception e){
                            System.out.println("Not successful");
                        }
                    }break;
                    case 6: {
                        Use_Sub_reddit(x, Sub);

                    }break;
                    case 7:{
                            try {
                            System.out.println("|-----------------------------------------------------------------------|");
                            System.out.println("\uD83D\uDCAA: "+Sub.get(a).Owner_Addmin.get(0).Get_username());

                                for(int i=1;i<Sub.get(a).Owner_Addmin.size();i++ )
                                    System.out.println("\uD83D\uDC68\u200D\uD83D\uDCBC: "+Sub.get(a).Owner_Addmin.get(i).Get_username());
                            System.out.println("|-----------------------------------------------------------------------|");
                                for (int k=0;k<Sub.get(a).users.size();k++){
                                    System.out.println("✍: "+Sub.get(a).users.get(k).Get_username());
                                }
                            System.out.println("|-----------------------------------------------------------------------|");
                            System.out.println("⏲ :" +Sub.get(a).getTime());

                            System.out.println("|-----------------------------------------------------------------------|");
                            System.out.println("\uD83D\uDCEE"+" : "+Sub.get(a).Show_notify());

                            }catch (Exception e){
                                System.out.println("Not successful");
                            }
                    }break;

                    case 21:
                    {
                        b=true;
                    }break;


                }
            }

        }


    }

    public static void Run_profile(User x,ArrayList<SubReddit>Sub){
        ArrayList<Post> posts;
        try {
        posts= new ArrayList<>();
        posts.clear();
        for (int i=0;i<Sub.size();i++){
            posts.add(x.ownSubreddit.get(i).newest());
        }
        }catch (Exception e){
            System.out.println("1");
        }

        //sort
        try {
        for (int i = 0; i < posts.size() ; i++) {
            for (int j = 0; j < posts.size() - i ; j++) {

                if (posts.get(j).TimeH() > posts.get(j + 1).TimeM()) {

                    Post temp = posts.get(j);
                    posts.set(j, posts.get(j + 1));
                    posts.set(j + 1, temp);
                }
            }
        }
        if (!posts.isEmpty()) {
            System.out.println("|--------------------------------------------------------------------------------|\n" +
                    "|--------------------------new post in ------------------------------------------|\n" +
                    "|--------------------------your subreddit ---------------------------------------|\n" +
                    "|--------------------------that you follow---------------------------------------|");
            for (int i = 0; i < 4 && i < posts.size(); i++) {
                System.out.println("| " + posts.get(i).getName() + " | " + posts.get(i).writer() + " | " + posts.get(i).TimeH() + "|");
            }
            System.out.println("|--------------------------------------------------------------------------------|\n" +
                    "|--------------------------------------------------------------------------------|");
        }
        }catch (Exception e){
            System.out.println("in sorting ");
        }


        System.out.println("1-Show sava post   7-change passWord  8- change email  9-change username 21-exit");
        Scanner scanner=new Scanner(System.in);
        int number=scanner.nextInt();
        while (number!=21){
            switch (number) {
                case 1: {
                    posts=x.getPost();
                    if (!posts.isEmpty())
                    for (Post xPost:posts){
                        System.out.println("|--------------------------------------------------------------------------------|\n" +
                                "|--------------------------your save--------------------------------------------|\n" +
                                "|---------------------------post------------------------------------------------|\n" +
                                "|-------------------------------------------------------------------------------|");
                        System.out.println("| "+xPost.getName()+" | " +"By :"+ xPost.writer()+" | "+" "+xPost.TimeH()+":"+xPost.TimeM()+"|");
                        System.out.println("|--------------------------------------------------------------------------------|\n" +
                                           "|--------------------------------------------------------------------------------|");

                    }
                    else  {
                        System.out.println("NO post saved");
                    }
                }
                break;
                case 7:{
                    System.out.print("enter passWord : ");
                    String passWordlast= scanner.next();
                    System.out.print("enter new passWord :");
                    String passWordNew=scanner.next();
                    x.changepassword(passWordlast,passWordNew);
                }break;
                case 8:{
                    System.out.print("enter password : ");
                    String pass=scanner.next();
                    System.out.print("enter new email ");
                    String email=scanner.next();
                    if (check_pattenr_email(email))
                        x.changeEmail(pass,email);
                    else
                        System.out.println("your email not correct");
                }break;
                case 9:{
                    System.out.println("enter passWord ");
                    String password=scanner.next();
                    System.out.print("enter new username ");
                    String username=scanner.next();
                    x.changeUsername(password,username);
                }break;

            }
            System.out.println("1-Show sava post   7-change passWord  8- change email  9-change username 21-exit");
            number=scanner.nextInt();
        }




    }

    public static Boolean check_pattenr_email(String email){

        String regex = "\\w*@\\w*\\.\\w{1,4}\\b";  // TODO

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
//        if (matcher.find()) {
//            return true;
//        }
//        else{
//            return false;
//        }
        return true;

    }
    public static int  check_uniqe_Username (String user,ArrayList<User> x){
        int conert=0;
        if (x.isEmpty())
        return 0;
        else {
            for (int i=0;i<x.size();i++){
                if (x.get(i).Get_username().equals(user))
                    conert++;
            }
            return conert;
        }
    }







}
