package dev.tomhaj.agilito.messagebus;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

final class MessageBusMiddlewareAdapter implements MessageBus {

    private final List<Middleware> middlewares = new LinkedList<>();

    @Override
    public void dispatch(Object message) {
        callableForNextMiddleware(0).accept(message);
    }

    void append(Middleware middleware) {
        middlewares.add(middleware);
    }

    private Consumer<Object> callableForNextMiddleware(int index) {
        try {
            return callableForMiddleware(middlewares.get(index), index);
        } catch (IndexOutOfBoundsException exception) {
            return emptyCallable();
        }
    }

    private Consumer<Object> callableForMiddleware(Middleware middleware, int index) {
        return message -> middleware.dispatch(message, callableForNextMiddleware(index + 1));
    }

    private Consumer<Object> emptyCallable() {
        return o -> {
        };
    }

}
