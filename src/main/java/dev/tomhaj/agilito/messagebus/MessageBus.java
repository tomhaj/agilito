package dev.tomhaj.agilito.messagebus;

public interface MessageBus {

    void dispatch(Object message);

}
