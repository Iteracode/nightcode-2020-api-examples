package org.nightcode.quarkus.service;

import org.nightcode.quarkus.dto.Message;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageService {
    public Message getMessage(String name) {
        return new Message(String.format("hello, %s", name));
    }
}