package com.event.qr.scheduler.eventQRScheduler.dto;

import com.event.qr.scheduler.eventQRScheduler.model.MessageElement;

import java.util.List;

public class SmsSendRequest {

    private List<MessageElement> messages;

    public List<MessageElement> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageElement> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "SmsSendRequest{" +
                "messages=" + messages +
                '}';
    }
}
