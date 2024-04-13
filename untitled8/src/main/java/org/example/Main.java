package org.example;

import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
            out.println("Start");
        ArrayList<Post> posts=new ArrayList<>();
        ArrayList<User> Users=new ArrayList<>();
        ArrayList<Subreddit> subreddits=new ArrayList<>();

        insert(posts,Users,subreddits);



        Scanner scanner = new Scanner(System.in);

        System.out.println("hi your welcome to rouzddit");

        while (true) {


            System.out.print("1-create User \n2-enter \n3-exit \n: ");
            int a = scanner.nextInt();
            if (a == 3) {
                save(posts,Users,subreddits);
                exit(0);
            }

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
                while (check_uniqe_Username(usename,Users)!=0) {
                    System.out.println("you user name has been used"+"|----|my suggest : " +(usename+String.valueOf(check_uniqe_Username(usename,Users))));
                    usename=scanner.next();
                }
                LocalTime localTime=LocalTime.now();
                User   user = new User(usename, pass, email,0, localTime.getHour());
                Users.add(user);
            } else {
                if (Users.isEmpty()) {System.out.print("No account \n\n");}
                else {
                    for (User x:Users) {
                        if(x.checkPass(pass)&x.getEmail().equals(email))
                            runuser(x,subreddits,Users);
                    }

                }



            }







        }






    }

    private static void save(ArrayList<Post> posts, ArrayList<User> users, ArrayList<Subreddit> subreddits) {
       try {
           Class.forName("com.mysql.jdbc.Driver").newInstance();

           String url = "jdbc:mysql://localhost:3306/sqlreddit?user=root";
           Connection connect = DriverManager.getConnection(url);
           Statement state = connect.createStatement();

           String deleteQuery = "DELETE FROM " + "acconut";
           state.executeUpdate(deleteQuery);
           deleteQuery = "DELETE FROM " + "comment";
           state.executeUpdate(deleteQuery);
           deleteQuery = "DELETE FROM " + "post";
           state.executeUpdate(deleteQuery);
           deleteQuery = "DELETE FROM " + "subreddit";
           state.executeUpdate(deleteQuery);


           for (User user : users) {
               String username, passWord, email, SavePost = null, follower = null;
               int karma, Date;
               username = user.getUserName();
               passWord = user.getPassWord();
               email = user.getEmail();
               karma = user.getKarma();
               Date = user.getHour();
               for (Post post : user.getSavePost()) {
                   SavePost = SavePost + " " + post.getTitle();
               }
               String query = "insert into acconut (username,passWord,email,karam,Date,SavePost,follower) values ('%s','%s','%s',%s,%s,'%s','%s')";
               query = String.format(query, username, passWord, email, karma, Date, SavePost, follower);
               state.execute(query);
           }
           for (Subreddit subreddit:subreddits){
               String name=subreddit.getTitle(),username="",admin="";
                       int Time;

               name=subreddit.getTitle();
               for (int i=0;i<subreddit.userName.size();i++){
                   username= username+" "+subreddit.userName.get(i).getUserName();
               }
               for (int i=0;i<subreddit.admin.size();i++){
                   admin= admin +" "+ subreddit.admin.get(i).getUserName();
               }


               Time=subreddit.getTime();

               String query = "insert into subreddit (name,username,admin,Time) values ('%s','%s','%s',%s)";
               query = String.format(query,name ,username,admin,Time);
               state.execute(query);
           }
           for (Post post:posts){
               String 	Title,	test,	subReddit,	writer,Likeer="";
                     int   Time	,like_,dis_like;
                Title=post.getTitle();
                test=post.getText();
                subReddit=post.getSubreddit().getTitle();
                writer=post.getWriter().getUserName();
                for (User user:post.getAction()){
                    Likeer=Likeer+" ; "+user.getUserName();
                }
                    Time=post.getTime();
                like_=post.Like();
                dis_like= post.DisLike();

               String query = "insert into post (Title,test,subReddit,writer,Time,like_,dis_like,Likeer) values ('%s','%s','%s','%s',%s,%s,%s,'%s')";
               query = String.format(query,Title,test,subReddit,writer,Time,like_,dis_like);
               state.execute(query);
               String Cpost,Ctext,Cwriter;
               int CTime;
               int i=0;
               for (comment comment:post.comments){
                   query="insert into comment (post,text,writer,Time)values ('%s','%s','%s',%s)";
                   query=String.format(comment.getPost().getTitle(),comment.getText(),comment.getWriter().getUserName(),comment.getTime());
                   state.execute(query);
                           i++;
               }








           }









       }catch(IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e){
           e.printStackTrace();
       }



    }

    private static void insert(ArrayList<Post> posts, ArrayList<User> Users, ArrayList<Subreddit> subreddits){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/sqlreddit?user=root";
            Connection connect = DriverManager.getConnection(url);
            Statement state = connect.createStatement();
            String query="select * from acconut";
            ResultSet resultSet= state.executeQuery(query);
            while (resultSet.next()){
                User User=new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),Integer.parseInt(resultSet.getString(4)), Integer.parseInt(resultSet.getString(5)) );
                Users.add(User);
            }

            query="select * from subreddit";
            resultSet= state.executeQuery(query);
            while (resultSet.next()){
                Subreddit subreddit=new Subreddit(resultSet.getString(1),Integer.parseInt(resultSet.getString(4)));
                for(User User:Users){
                        subreddit.setUserName(User);
                    if(resultSet.getString(3).contains(User.getUserName()))
                        subreddit.setAdmin(User);
                }
                subreddits.add(subreddit);
            }
            query="select * from post";
            resultSet =state.executeQuery(query);
            while (resultSet.next()){
                Post post=new Post(resultSet.getString(1),resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(5)),Integer.parseInt(resultSet.getString(6)),Integer.parseInt(resultSet.getString(7)));

                for(User User:Users){
                    if(resultSet.getString(8).contains(User.getUserName())){
                        post.setAction(User);
                    }
                    if(resultSet.getString(4).equals(User.getUserName())){
                        post.setWriter(User);
                    }
                }
                for (Subreddit subreddit:subreddits){
                    if(resultSet.getString(3).equals(subreddit.getTitle()))
                        post.setSubreddit(subreddit);
                }
                posts.add(post);
            }
            int conter=0;
            query="select * from acconut";
            resultSet= state.executeQuery(query);
             conter=0;
             while (resultSet.next()){
                 for(Post post:posts){
                     if(resultSet.getString(6).contains(post.getTitle())){
                         Users.get(conter).getSavePost().add(post);
                     }
                     conter++;
                 }
             }
            query="select * from comment";
            resultSet= state.executeQuery(query);
            while (resultSet.next()) {
                comment comment=new comment(resultSet.getString(2),null,null,Integer.parseInt(resultSet.getString(4)));
                 for(Post post:posts){
                    if(resultSet.getString(1).equals(post.getTitle()))
                        post.setComments(comment);
                        comment.setPost(post);
                }
            }


            connect.close();
            state.close();

        }catch(IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

    public static void   runuser(User x,ArrayList<Subreddit> Sub,ArrayList<User> users) {
        System.out.println("hi " + x.getUserName());
        int re = 0;
        boolean isout=false;
        while (!isout) {
            System.out.println("1-join to subreddit \n2-creat sub  \n3-Show my subreddit \n4-see profile \n5-communication  \n6-Subreddit  \n21-exit");
            Scanner scanner = new Scanner(System.in);
            int i = 1;
            re = scanner.nextInt();
            if (re == 21)
                isout=true;

            switch (re) {
                case 1: {
                    if (!Sub.isEmpty()) {
                        for (Subreddit xSubReddit : Sub) {

                            System.out.println(i++ + "-" + xSubReddit.getTitle());
                        }
                        System.out.println("inter Id ");
                        i = scanner.nextInt();

                        boolean r=false;
                        for (int i1=0;i1<x.getOwnSubreddit().size();i1++){
                            if (x.OwnSubreddit.get(i1).equals(Sub.get(i-1))){
                                r=true;
                            }
                        }
                        if(!r){
                            Sub.get(i - 1).setUserName(x);
                            x.OwnSubreddit.add(Sub.get(i - 1));
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
                                if (Sub.get(k).getTitle().equals(name)) {
                                    b = true;
                                }
                            }
                            if (!b){
                                LocalTime localTime=LocalTime.now();
                                Subreddit subReddit = new Subreddit(name,localTime.getHour() );
                                Sub.add(subReddit);
                                subReddit.setAdmin(x);
                                subReddit.setUserName(x);
                                x.OwnSubreddit.add(subReddit);
                                System.out.println("Successful\n\n ");
                            }else System.out.println("you name has been used   ");

                        }catch (Exception e) {
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
                case 5:
                {
                    out.println("this item will comming soon");
                }break;
                case 6: {
                    try {


                        if (!x.OwnSubreddit.isEmpty() ) {
                            for (Subreddit xSubReddit : x.OwnSubreddit) {

                                System.out.println(i++ + "-" + xSubReddit.getTitle() +can_check(xSubReddit,x));
                            }
                            System.out.println("inter Id ");
                            i = scanner.nextInt();

                        }
                        Run_admin(users,Sub.get(i-1) , x);

                    }catch (Exception e){
                        System.out.println("not successfully");
                    }
                }break;
            }

        }

    }



    private static String can_check(Subreddit xSubReddit ,User x) {
        for (int i=0;i<xSubReddit.admin.size();i++) {
            if (x.equals(xSubReddit.admin.get(i)))
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

    public static void Run_admin(ArrayList<User> users,Subreddit sub,User x){

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
                        if (a.getUserName().equals(u)){
                                sub.admin.add(a);
                            break;
                        }
                    }


                }break;
                case 2:{
                    System.out.println("enter user name ");
                    String u =scanner.next(),pass;
                    for (User a:users){
                        if (a.getUserName().equals(u)){
                            System.out.println("enter passWord od Subreddit");
                            pass=scanner.next();
                                try {
                                    sub.admin.remove(a);
                                }catch (Exception e){
                                    System.out.println("not successful ");
                                }

                            break;
                        }
                    }
                }break;
                case 3: {
                    for (int i=0;i<sub.posts.size();i++) {
                        System.out.println((i + 1) + " " + sub.posts.get(i).getTitle() + "  ");
                    }
                    System.out.println("Id for remove ");
                    int number =scanner.nextInt();
                    try {
                        sub.posts.remove(number - 1);
                        sub.Number--;
                    }catch (Exception e){
                        System.out.println("Not successful");
                    }
                }break;
            }
            System.out.println("1-add admin 2-delete admin 3-delete post 21-exit");
            re=scanner.nextInt();
        }




    }





    public static void Use_Sub_reddit(User x,ArrayList<Subreddit> Sub) {
        Scanner scanner = new Scanner(System.in);
        int a=-1;
        boolean move = true;
        int id = 0;
        try {
            if(x.OwnSubreddit.isEmpty()){
                System.out.println("you have no subreddit ");
                move=false;
            }else {
                for (Subreddit c : x.OwnSubreddit) {
                    System.out.print(++id + " --- " + c.getTitle() + " |--->  " + c.Number + "  |-----| " + c.getTime() +
                            " |-----| " + c.admin.get(0).getUserName() + "\n");
                }
                System.out.print("you can see post of sub please choose ");
                a = scanner.nextInt() - 1;
                if (!Sub.get(a).posts.isEmpty()) {
                    for (int w = 0; w < Sub.get(a).posts.size(); w++)
                        System.out.print((w+1)+" "+Sub.get(a).posts.get(w).getTitle() +" |\n" +
                                "| "+" \uD83D\uDE0A :"+Sub.get(a).posts.get(w).Like()+"\t"+"  \uD83D\uDE12 :"+ Sub.get(a).posts.get(w).DisLike()+"\n"
                                + Sub.get(a).posts.get(w).getTime()  +
                                "\n" + "\u270D"+" By: " + Sub.get(a).posts.get(w).getWriter().getUserName() + "\n\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Not Successful ");
            move = false;
        }

        if (move == true) {

            boolean b=false;
            while (b==false) {
                System.out.println("1- Add post  2- like or dislike 3-Add comment 4-show comment 5-save post  6-Back step 7-information     21-exit   ");
                switch (scanner.nextInt()) {
                    case 1: {
                        try {
                            String srt, s,str="";
                            do {
                                System.out.println("enter your title of post");
                                srt = scanner.next();
                                Sub.get(a).setTitle(srt);

                                System.out.print("enter you post: (at the end inter ~ and space )\n");
                                srt=scanner.next();
                                while (!srt.equals("~"))
                                {
                                    str+=" "+srt;
                                    srt=scanner.next();
                                }
                                str= checkMassaage(str.toLowerCase(),x,"rouzbeh","farid");
                                System.out.println("Title: "+ Sub.get(a).getTitle()+"\nPost: "+str+"\n\nare you sure?   " ) ;
                                s = scanner.next();
                            } while (s.equals("no") || s.equals("0") || s.equals("n"));

                            LocalTime  localTime=LocalTime.now();
                            Post post = new Post(str,str,0,0,0);
                            Sub.get(a).setPosts(post);
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
                            Sub.get(a).posts.get(number - 1).DisLike();
                    }
                    break;
                    case 3: {
                        System.out.println("enter number of post for add  comment ");
                        int number = scanner.nextInt();
                        String string="",s="" ;
                        while (string.equals("~"))
                        {
                            string=scanner.next();
                            s=string+ " "+string;

                        }
                        s=checkMassaage(s.toLowerCase(),x,"israel","war");
                        LocalTime localTime=LocalTime.now();
                        comment comment=new comment(string, Sub.get(a).posts.get(number-1),x,localTime.getHour());
                        Sub.get(a).posts.get(number-1).setComments(comment);
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
                            x.setSavePost(Sub.get(a).posts.get(number - 1));
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
                            System.out.println("\uD83D\uDCAA: "+Sub.get(a).getAdmin().get(0).getUserName());

                            for(int i=1;i<Sub.get(a).admin.size();i++ )
                                System.out.println("\uD83D\uDC68\u200D\uD83D\uDCBC: "+Sub.get(a).admin.get(i).getUserName());
                            System.out.println("|-----------------------------------------------------------------------|");
                            for (int k=0;k<Sub.get(a).userName.size();k++){
                                System.out.println("✍: "+Sub.get(a).userName.get(k).getUserName());
                            }
                            System.out.println("|-----------------------------------------------------------------------|");
                            System.out.println("⏲ :" +Sub.get(a).getTime());

                            System.out.println("|-----------------------------------------------------------------------|");
                            System.out.println("\uD83D\uDCEE"+" : "+Sub.get(a).Number);

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

    public static void Run_profile(User x,ArrayList<Subreddit>Sub){
        ArrayList<Post> posts;
        posts= new ArrayList<>();
        try {
            posts.clear();
            for (int i=0;i<Sub.size();i++){
                posts.add(x.OwnSubreddit.get(i).newest());
            }
        }catch (Exception e){
            System.out.println("1");
        }

        //sort
        try {
            for (int i = 0; i < posts.size() ; i++) {
                for (int j = 0; j < posts.size() - i-1 ; j++) {

                    if (posts.get(j).getTime() >= posts.get(j + 1).getTime()) {

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
                    System.out.println("| " + posts.get(i).getTitle() + " | " + posts.get(i).getWriter().getUserName() + " | " + posts.get(i).getTime() + "|");
                }
                System.out.println("|--------------------------------------------------------------------------------|\n" +
                        "|--------------------------------------------------------------------------------|"+
                        "if you want see post inter number ");

            }
        }catch (Exception e){
            System.out.println("in sorting ");
        }


        System.out.println("1-Show sava post 2-see the newest post   7-change passWord  8- change email   21-exit");
        Scanner scanner=new Scanner(System.in);
        int number=scanner.nextInt();
        while (number!=21){
            switch (number) {
                case 1: {
                    posts=x.getSavePost();
                    if (!posts.isEmpty())
                        for (Post xPost:posts){
                            System.out.println("|--------------------------------------------------------------------------------|\n" +
                                    "|--------------------------your save--------------------------------------------|\n" +
                                    "|---------------------------post------------------------------------------------|\n" +
                                    "|-------------------------------------------------------------------------------|");
                            System.out.println("| "+xPost.getTitle()+" | " +"By :"+ xPost.getWriter().getUserName()+" | "+" "+xPost.getTime()+"|");
                            System.out.println("|--------------------------------------------------------------------------------|\n" +
                                    "|--------------------------------------------------------------------------------|");

                        }
                    else  {
                        System.out.println("NO post saved");
                    }
                }
                break;
                case 2:
                {
                    System.out.println("Title :|" + posts.get(0).getTitle() + " |\nPost :|"
                            +posts.get(0).getText()+"|\n"
                            + posts.get(0).getWriter().getUserName() + " | " + posts.get(0).getTime() + "|");

                }
                case 7:{
                    System.out.print("enter passWord : ");
                    String passWordlast= scanner.next();
                    System.out.print("enter new passWord :");
                    String passWordNew=scanner.next();
                    x.changePassword(passWordlast,passWordNew);
                }break;
                case 8:{
                    System.out.print("enter password : ");
                    String pass=scanner.next();
                    System.out.print("enter new email ");
                    String email=scanner.next();
                    if (check_pattenr_email(email))
                        x.setEmail(pass,email);
                    else
                        System.out.println("your email not correct");
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
                if (x.get(i).getUserName().equals(user))
                    conert++;
            }
            return conert;
        }
    }


    private static String  checkMassaage(String massage,User x,String ...wordsToCensor){
        String string=massage;
        for (String word : wordsToCensor) {
            massage = massage.replace(word,"*".repeat(word.length()));
        }
        if(!string.equals(massage))
            x.increaseKarma();

        return massage;
    }





}