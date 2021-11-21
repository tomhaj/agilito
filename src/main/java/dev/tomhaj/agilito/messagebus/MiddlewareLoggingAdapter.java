package dev.tomhaj.agilito.messagebus;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import java.util.function.Consumer;

@RequiredArgsConstructor
final class MiddlewareLoggingAdapter implements Middleware {

    private final Logger logger;

    @Override
    public void dispatch(Object message, Consumer<Object> next) {
        logger.debug("Started handling message: {}", message.getClass().getName());

        next.accept(message);

        logger.debug("Finished handling message: {}", message.getClass().getName());
    }

}
