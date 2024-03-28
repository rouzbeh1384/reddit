package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SubReddit extends Account{

    ArrayList<Post> posts;

    ArrayList<User> users;

    String Name;
    public SubReddit(String NameSub,User owner,String password) throws IOException {
        super(password);
        Name=NameSub;
        users=new ArrayList<>();
        posts=new ArrayList<>();
        FileWriter Name= new FileWriter("file.txt");
        Name.write("mt name is rouzbeh");
    }
    public void joind (User x){
        users.add(x);
    }




}
