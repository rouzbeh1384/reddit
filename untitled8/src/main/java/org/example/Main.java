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
                    boolean find=true,w=false;

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    String url = "jdbc:mysql://localhost:3306/sqlreddit?user=root";
                    Connection connect = DriverManager.getConnection(url);
                    Statement state = connect.createStatement();

                    String query="select * from acconut";
                    ResultSet result=state.executeQuery(query);

                    String user="",pass="",email="";

                    while (find) {
                        System.out.println("username: ");
                         user = scanner.next();
                        System.out.println("passWord: ");
                        pass=scanner.next();
                        System.out.println("email");
                        email=scanner.next();
                        find=false;
                        while (result.next()){
                            if (result.getString(1).equals(user)){
                                find=true;
                                break;
                            }
                        }
                    }
                    LocalTime currentTime = LocalTime.now();
                    String query1 = "insert into acconut(username,password,email,karam,date) values('%s','%s','%s',%s,%s)";
                    query1 = String.format(query1, user, pass, email, 50, currentTime.getHour());
                    state.execute(query1);
                    state.close();
                    connect.close();
                    out.println("Successful");
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

                           System.out.println("inter name of subreddit");
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

                       String query="insert into subreddit(name,username,admin,Time)values('%s','%s','%s',%s)";
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
                                       query="update subreddit set name='%s', username='%s',admin='%s',Time=%s  where name='%s'";
                                       String s= result.getString(2)+" "+username;
                                       query=String.format(query,result.getString(1),s,result.getString(3),result.getString(4),result.getString(1));
                                       state.execute(query);
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
                           if(result.getString(2).contains(name))
                                System.out.println(conter +" - "+result.getString(1)+"  |_____|  "+result.getString(4));
                       }
                       System.out.println("inter name of subreddit  or \n\"exit\"");
                       String Sub=scanner.next();
                       boolean b=true,have=false;
                       while (b && !Sub.equals("exit")) {

                           query = "select * from post where ='"+ Sub+"'";
                           result = state.executeQuery(query);
                           conter = 0;
                           //1 Title	2 test	3 subReddit	4 writer	5 Time	6 like_	 7 dis_like
                           while (result.next()) {
                               if (result.getString(3).equals(Sub)) {
                                   conter++;
                                   System.out.println(
                                           conter + "- Title :" + result.getString(1) + "\n" + "post: " + result.getString(2) + "\n" +
                                                   "Writer : " + result.getString(4) + "|----|"+"Time : " + result.getString(5) +
                                                   "|-----|" +"Like: "+ result.getString(6) + "|-----|"+"Dis_like: " + result.getString(7)
                                   );
                                   conter++;
                                   have = true;



                               }
                           }
                         if(have||conter==0){
                           System.out.println("1-add post \n2-add comment  \n3-show comment \n4-change post(own) \n5-delete post\n6-Like or dis_like  21-exit ");
                           switch (scanner.nextInt()) {
                               case 1: {
                                   String title = "", text = "";
                                   query = "select *from post";
                                   result = state.executeQuery(query);
                                   boolean return_title = true;

                                   while (return_title == true) {
                                       System.out.println("inter your title ");
                                       title = scanner.next();
                                       return_title=false;
                                       while (result.next()) {
                                           if (result.getString(1).equals(title)) {
                                                return_title=true;
                                                break;
                                           }
                                       }
                                   }


                                   System.out.println("inter your post (for end enter ~)");
                                   String str = "";
                                   while (!str.equals("~")) {
                                       text = text + " " + str;
                                       str = scanner.next();
                                   }

                                   LocalTime localTime = LocalTime.now();
                                   query = "insert into post (Title,test,subReddit,writer,Time,like_, dis_like,Likeer) values ('%s','%s','%s','%s',%s,%s,%s,'%s')";
                                   query = String.format(query, title, text, Sub, name, localTime.getHour(), 0, 0,"");
                                   state.execute(query);
                                   System.out.println("Successful");
                               }
                               break;
//                               //1-post	2-text	3-writer	4-Time
                               case 2: {
                                   System.out.println("title of post : ");
                                   String string = scanner.next();
                                   query = "select * from post";
                                   result = state.executeQuery(query);
                                   {
                                       while (result.next()) {
                                           if (result.getString(1).equals(string)) {
                                               System.out.println("inert your comment (for end ~): ");
                                               String str = "", text = "";

                                               while (str.equals("~")) {
                                                   text = text + " " + str;
                                                   str = scanner.next();
                                               }
                                               query = "insert into comment(psot,test,writer,Time) values ('%s','%s','%s',%s)";
                                               LocalTime localTime = LocalTime.now();
                                               query = String.format(query, string, text, name, localTime.getHour());
                                               state.execute(query);
                                           }
                                       }
                                   }
                               }
                               break;
                               //1-post	2-text	3-writer	4-Time
                               case 3: {
                                   System.out.println("title of post : ");
                                   String string = scanner.next();
                                   query = "select * from comment";
                                   result = state.executeQuery(query);
                                   conter = 0;
                                   while (result.next()) {
                                       if (result.getString(1).equals(string)) {
                                           conter++;
                                           System.out.println(conter + " Title post: " + result.getString(1) + "\n" +
                                                   "comment : " + result.getString(2) + "\n" +
                                                   "writer :" + result.getString(3) + "\n" +
                                                   "Time : " + result.getString(4)
                                           );
                                       }
                                   }

                               }
                               break;
                               //4-change post(own)
                               case 4: {
                                   System.out.println("title of post : ");
                                   String string = scanner.next();
                                   //Title	test	subReddit	writer	Time	like_	dis_like
                                   System.out.println("Title");
                                   String titel = scanner.next();
                                   String str = "", string1 = "";
                                   System.out.println("inter your post ");
                                   while (!str.equals("~")) {
                                       string1 = string1 + " " + str;
                                       str = scanner.next();
                                   }
                                   query = "select * from post";
                                   result = state.executeQuery(query);

                                   while (result.next()) {
                                       if (result.getString(1).equals(titel) && result.getString(4).equals(name)) {
                                           query = "update post set Title='%s', test='%s',subreddit='%s' ,writer='%s',Time=%s,like_=%s,dis_like=%s where Title='%s'";
                                           LocalTime localTime = LocalTime.now();
                                           query = String.format(query, titel, string1, Sub, name, localTime.getHour(),
                                                   Integer.parseInt(result.getString(6)), Integer.parseInt(result.getString(7)), titel);
                                           state.execute(query);
                                           break;
                                       }
                                   }

                               }
                               break;
                               case 5: {
                                   //Title	test	subReddit	writer	Time	like_	dis_like
                                   System.out.println("Title");
                                   String titel = scanner.next();

                                   query = "select * from post";
                                   result = state.executeQuery(query);

                                   while (result.next()) {
                                       if (result.getString(1).equals(titel) && result.getString(4).equals(name)) {
                                           query = "delete from post where Title='%s'AND subreddit='%s' AND writer='%s'";
                                           System.out.println(name);
                                           query = String.format(query, titel, Sub, name);
                                           state.execute(query);
                                           break;
                                       }
                                   }
                               }break;
                               case 6: {
                                   //Title	test	subReddit	writer	Time	like_	dis_like
                                   System.out.println("Title: ");
                                   String titel = scanner.next();
                                   boolean actin=false;
                                   query = "SELECT * FROM post WHERE Title='" + titel + "'";
                                   result=state.executeQuery(query);
                                   while (result.next())
                                       if(result.getString(8).contains(name))
                                           actin=false;

                                   if(actin){
                                   int number1=0,number2=0;
                                   System.out.println("like 1 dis_like -1");
                                   if(scanner.nextInt()>0){
                                       number1++;
                                   }
                                   else
                                       number2--;
                                        String liker=result.getString(8);
                                        liker=liker+" "+name;
                                       query = "update  post set like_ =%s,dis_like=%s ,Likeer='%s'=name where Title='%s'AND subreddit='%s'";
                                       query = String.format(query,number1,number2,liker,titel,Sub);
                                       state.execute(query);
                                    }
                                    else System.out.println("you cant like ot dis_like ");
                               }
                               break;

                               case 21: {
                                   b=false;
                               }
                           }
                       }
                       }
                   }break;
                   case 21:
                       out=true;

               }
            }
        }
        }catch(IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

}