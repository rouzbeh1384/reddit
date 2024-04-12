package org.example;

import java.sql.*;
import java.time.LocalTime;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        System.out.println("Strat");
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("1-Create Account 2-enter 3-exit ");
            int a=scanner.nextInt();
            if (a==3)
                exit(0);
            else if(a==1){
                try {
                    boolean b=true,w=false;
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    String url = "jdbc:mysql://localhost:3306/sqlreddit?user=root";
                    Connection connect = DriverManager.getConnection(url);
                    Statement state = connect.createStatement();

                    String query="select * from acconut";
                    ResultSet result=state.executeQuery(query);


                    String user="",pass="",email="";

                    while (b) {
                        System.out.println("username: ");
                         user = scanner.next();
                        System.out.println("passWord: ");
                        pass=scanner.next();
                        System.out.println("email");
                        email=scanner.next();
                        while (result.next() ){
                            if (result.getString(1).equals(user)){
                                w=true;
                                break;
                            }
                        }
                        if(w==false)
                            b=false;
                    }
                    LocalTime currentTime = LocalTime.now();
                    String query1 = "insert into acconut(username,password,email,karam,date) values('%s','%s','%s',%s,%s)";
                    query1 = String.format(query1, user, pass, email, 50, currentTime.getHour());
                    state.execute(query1);
                    state.close();
                    connect.close();
                }catch(IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e){
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("username: ");
                String user=scanner.next();
                System.out.println("pass: ");
                String pass=scanner.next();
                boolean check=false;
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    String url = "jdbc:mysql://localhost:3306/sqlreddit?user=root";
                    Connection connect = DriverManager.getConnection(url);
                    Statement state = connect.createStatement();

                    String query = "select * from acconut";
                    ResultSet result = state.executeQuery(query);
                    while (result.next()){
                        if(result.getString(1).equals(user) && result.getString(2).equals(pass))
                        {
                            user=result.getString(1);
                            check=true;
                            break;
                        }
                    }
                    if(check){
                        run(user);
                    }
                }catch(IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e){
                    e.printStackTrace();
                }

            }


        }
    }
    private static void run(String name ){
        System.out.println("hi "+name);
        Scanner scanner=new Scanner(System.in);

        boolean out=false;
        try {
            while (!out){
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String url = "jdbc:mysql://localhost:3306/sqlreddit?user=root";
                Connection connect = DriverManager.getConnection(url);
                Statement state = connect.createStatement();

            System.out.println("1-create a Subreddit \n2-join \n3-Show your subreddit   \n4-profile \n5-communication 21-exit");
            {
               int re=scanner.nextInt();
               switch (re){
                   case 1:
                   {
                       boolean b=true,w=false;
                       String Subname="";
                       while (b) {
                           String query="select * from subreddit";
                           ResultSet result=state.executeQuery(query);
                           Subname=scanner.next();

                           while (result.next() ){
                               if (result.getString(1).equals(Subname)){
                                   w=true;
                                   break;
                               }
                           }
                           if(w==false)
                               b=false;
                       }

                       String query="insert into subreddit(name,username,admin,Time)values('%s','%s','%s',%s,%s)";
                       LocalTime currentTime=LocalTime.now();
                       query = String.format(query, Subname, name , name , currentTime.getHour());
                       state.execute(query);
                       state.close();
                       connect.close();
                       System.out.println("Successful");
                   }break;
                   case 2:
                   {
                           String query="select * from subreddit";
                           ResultSet result=state.executeQuery(query);
                           System.out.println("inter Subreddit");
                           String Subname=scanner.next();

                           while (result.next() ){
                               if (result.getString(1).equals(Subname)){
                                   String username=result.getString(2);
                                   if(!username.contains(name)) {
                                       username = username + " ; " + name;
                                       query="update user set name='%s', username='%s',admin='%s',Time=%s  where name='%s'";
                                       query=String.format(query,result.getString(1),username,result.getString(3),result.getString(4));
                                       state.execute(query);
                                       state.close();
                                       connect.close();
                                   }
                                   else System.out.println("you have been in subreddit ");
                               }
                           }


                   }break;
                   case 3:
                   {
                       int conter=0;
                       String query="select * from subreddit";
                       ResultSet result=state.executeQuery(query);
                       while (result.next()){
                           conter++;
                           System.out.println(conter +" - "+result.getString(1)+"|_____|"+result.getString(4));
                       }
                       System.out.println("inter name of subreddit  or \n\"exit\"");
                       String Sub=scanner.next();
                       while (true) {
                       if (Sub.equals("exit"))
                            break;

                           query = "select * from post";
                           result = state.executeQuery(query);
                           conter = 0;
                            //1 Title	2 test	3 subReddit	4 writer	5 Time	6 like_	 7 dis_like
                           while (result.next()) {
                               if (result.getString(3).equals(Sub)) {
                                   conter++;
                                   System.out.println(
                                           conter + "- Title :" + result.getString(1) + ":\n" + "post: " + result.getString(2) + "\n" +
                                                   "Writer : " + result.getString(4) + "|Time|" + result.getString(5) +
                                                   "| Like :|" + result.getString(6) + "|Dis_like|" + result.getString(7)
                                   );

                               }
                           }
                           System.out.println("1-add post \n2-add comment  \n3- show comment 4-change post(own) 21-exit ");
                           switch (scanner.nextInt()) {
                               case 1: {
                                   String title = "", text = "";
                                   boolean b=true,w=false;

                                   while (b) {
                                        query="select * from post";
                                        result=state.executeQuery(query);
                                        System.out.println("inter your title ");
                                        title=scanner.next();

                                       while (result.next() ){
                                           if (result.getString(1).equals(title)){
                                               w=true;
                                               break;
                                           }
                                       }
                                       if(w==false) {
                                           b = false;
                                           System.out.println("inter your post (for end enter ~)");
                                           String str="";
                                           while (str.equals("~")){
                                               text=text+str;
                                               str=scanner.next();
                                           }
                                       }
                                   }

                                   LocalTime localTime = LocalTime.now();
                                   query = "insert into post (Title,test,subReddit,writer,Time,like, dis_like) values ('%s','%s','%s','%s',%s,%s,%s)";
                                   query = String.format(title, text, name, localTime.getHour(), 0, 0);
                                   state.execute(query);
                                   System.out.println("Successful");


                               }break;
                               case 2: {
                               }break;
                               case 3:{

                               }break;
                               case 21:{
                                   Sub="exit";
                               }

                           }

                       }
                   }break;

               }
            }
        }
        }catch(IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

}