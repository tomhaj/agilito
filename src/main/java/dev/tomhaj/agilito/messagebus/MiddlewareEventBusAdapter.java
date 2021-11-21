package dev.tomhaj.agilito.messagebus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

final class MiddlewareEventBusAdapter implements Middleware {

    private final Map<Class<?>, List<MessageHandler<Object>>> messageSubscribers;

    MiddlewareEventBusAdapter(List<MessageHandler<?>> subscribers) {
        messageSubscribers = register(subscribers);
    }

    @Override
    public void dispatch(Object message, Consumer<Object> next) {
        messageSubscribers
                .getOrDefault(message.getClass(), Collections.emptyList())
                .forEach(subscriber -> subscriber.handle(message));
    }

    private Map<Class<?>, List<MessageHandler<Object>>> register(List<MessageHandler<?>> subscribers) {
        var subscribersMap = new HashMap<Class<?>, List<MessageHandler<Object>>>();

        subscribers.forEach(subscriber -> {
            var subscribingMessage = HandlingMessageResolver.resolve(subscriber);
            var messageSpecificSubscribers = subscribersMap.getOrDefault(subscribingMessage, new ArrayList<>());

            if (messageSpecificSubscribers.contains(subscriber)) {
                return;
            }

            messageSpecificSubscribers.add((MessageHandler<Object>) subscriber);

            subscribersMap.put(subscribingMessage, messageSpecificSubscribers);
        });

        return Collections.unmodifiableMap(subscribersMap);
    }

}
