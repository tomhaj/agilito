package dev.tomhaj.agilito.messagebus;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
abstract class CommonHandler<Message> implements MessageHandler<Message> {

    private final List<Object> handledMessages;

    @Override
    public final void handle(Message message) {
        handledMessages.add(message);
    }

}
