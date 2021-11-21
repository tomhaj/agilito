package dev.tomhaj.agilito.messagebus;

public interface MessageHandler<Message> {

    void handle(Message message);

}
