package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class chat extends Account{
    public ArrayList<Post> chat ;

    public User user1;
    public User user2;



    public chat (User x,User y){
       chat=new ArrayList<>();
       user1=x;
       user2=y;
    }

    public void massage (String massage ,User user){
        Post post=new Post(massage,user);
        chat.add(post);

    }

    public void ShowMassage() {
        for (int i=0;i<chat.size();i++){
            System.out.print((i+1)+" \uD83D\uDD8E: "+chat.get(i).writer()+"|----|"+ chat.get(i).getName() +"\n"+ "\uD83D\uDD70 "+chat.get(i).TimeH()+":"+chat.get(i).TimeM() +"\n");
        }
    }
    public void delete (User x){

        System.out.println("|-----------------------------------------------------------------------------------------|");
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter massage :");
        int i =scanner.nextInt();
        if(chat.get(i-1).writer().equals(x.Get_username())){
            chat.remove(i-1);
        }
        else System.out.println("you can't delete this massage ");
    }
    public void reply (User x){
            System.out.println("inter number of massage -->");
            Scanner scanner=new Scanner(System.in);
            int i =scanner.nextInt();
            System.out.println("inter your comment (for end inter ~ then enter \" )");
            String str="";
            String string="";
            while (true){
                str=scanner.next();
                if (!str.equals("~"))
                string+=" "+str;
                else
                    break;

            }

            string= "|reply-->"+(i)+"|------|"+string;

            Post post=new Post(string,x);

            chat.add(post);
    }




}
