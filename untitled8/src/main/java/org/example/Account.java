package org.example;

public class Account {
    private String UserName;
    private String passWord;
    private String email;

    private int karma;

    private int Hour;
    private String savePost;
    private String follower;

    public Account(String userName,String passWord,String email,int karma,int Hour,String follower,String SavePost){
        this.passWord=passWord;
        this.UserName=userName;
        this.email=email;
        this.Hour=Hour;
        this.karma=karma;
        this.follower=follower;
        this.savePost=SavePost;
    }

    private void setPassWord(String passWord){
        this.savePost=passWord;
    }
    public void changePassword(String pass,String newpass){
        if (checkPass(pass)){
            setPassWord(newpass);
        }
    }
    private boolean checkPass(String passWord){
        if(this.passWord.equals(passWord))
            return true;
        return false;
    }

    public void increaseKarma(){
            this.karma+=10;
    }
    public void dicreaseKarma(){
        this.karma-=10;
    }
    public void setEmail(String passWord,String email){
        if(checkPass(passWord)){
            this.email=email;
        }
    }

    public void setSavePost(String NamePost){
        this.savePost=this.savePost+" "+NamePost;
    }
    public void setFollower(String userName){
        this.follower=this.follower+" "+userName;
    }

    public String getUserName(){
        return this.UserName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassWord(){
        return this.passWord;
    }

    public String getFollower(){
        return this.follower;
    }

    public String getSavePost(){
        return this.savePost;
    }
    public int getHour(){
        return this.Hour;
    }

    public int getKarma(){
        return this.karma;
    }


}
