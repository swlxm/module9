package com.epam.java.selenium.entities;

public class EmailBuilder {
    private String subject = null;
    private String to = null;
    private String body = null;

    public EmailBuilder setTo(String to) {
        this.to = to;
        return this;
    }

    public EmailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public Email build() {
        return new Email(to, subject, body);
    }
}
