package com.bluesoft;

public class Comment {
    private String email;
    private String comment;

    public Comment(String email, String comment) {
        this.email = email;
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public String getComment() {
        return comment;
    }
}
