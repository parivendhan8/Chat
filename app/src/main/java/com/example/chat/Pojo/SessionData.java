package com.example.chat.Pojo;

public  class SessionData {

    private static SessionData session = null;

    public static SessionData get()
    {
        if (session == null) session = new SessionData();
        return session;
    }


    String userName = "";
    String password = "";
    String chatWith = "";




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChatWith() {
        return chatWith;
    }

    public void setChatWith(String chatWith) {
        this.chatWith = chatWith;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
