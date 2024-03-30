package org.example;

public class Post {

    private String Name;

    private int Like;


    private User Owner;
    public Post(String string){
        this.Name=string;

    }

    public String getName()
    {
        return this.Name;
    }

    public void setLike(int i)
    {
        this.Like+=i;
    }



}
