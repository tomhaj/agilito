package dev.tomhaj.agilito.messagebus;

import lombok.experimental.UtilityClass;
import org.slf4j.LoggerFactory;

import java.util.List;

@UtilityClass
public final class MessageBusFactory {

    public static MessageBus commandBus(List<MessageHandler<?>> messageHandlers) {
        var messageBus = commonMessageBus("command-bus");

        messageBus.append(new MiddlewareCommandBusAdapter(messageHandlers));

        return messageBus;
    }

    public static MessageBus eventBus(List<MessageHandler<?>> messageHandlers) {
        var messageBus = commonMessageBus("event-bus");

        messageBus.append(new MiddlewareEventBusAdapter(messageHandlers));

        return messageBus;
    }

    private static MessageBusMiddlewareAdapter commonMessageBus(String busName) {
        var messageBus = new MessageBusMiddlewareAdapter();

        messageBus.append(new MiddlewareLoggingAdapter(LoggerFactory.getLogger(busName)));

        return messageBus;
    }

}
