package dev.tomhaj.agilito.messagebus;

import java.util.function.Consumer;

interface Middleware {

    void dispatch(Object message, Consumer<Object> next);

}
