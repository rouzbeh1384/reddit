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

        while (!out){
            System.out.println("1-create a Subreddit \n2-join \n3-Show your subreddit   \n4-profile \n5-communication 21-exit");
            {
               int re=scanner.nextInt();
               switch (re){

               }
            }
        }
    }

}