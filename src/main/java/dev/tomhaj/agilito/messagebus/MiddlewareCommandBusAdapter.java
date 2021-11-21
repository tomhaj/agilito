package dev.tomhaj.agilito.messagebus;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toMap;

final class MiddlewareCommandBusAdapter implements Middleware {

    private final Map<Class<?>, MessageHandler<Object>> messageHandlers;

    MiddlewareCommandBusAdapter(List<MessageHandler<?>> messageHandlers) {
        this.messageHandlers = messageHandlers
                .stream()
                .collect(toMap(
                        h -> HandlingMessageResolver.resolve(h),
                        h -> (MessageHandler<Object>) h
                ));
    }

    @Override
    public void dispatch(Object message, Consumer<Object> next) {
        messageHandlers
                .getOrDefault(message.getClass(), new EmptyMessageHandler())
                .handle(message);

        next.accept(message);
    }

    private static class EmptyMessageHandler implements MessageHandler<Object> {

        @Override
        public void handle(Object o) {

        }

    }

}
