package dev.tomhaj.agilito.messagebus;

import lombok.experimental.UtilityClass;
import org.springframework.core.ResolvableType;

@UtilityClass
final class HandlingMessageResolver {

    static Class<?> resolve(MessageHandler<?> messageHandler) {
        return ResolvableType
                .forClass(MessageHandler.class, messageHandler.getClass())
                .getGeneric(0)
                .resolve();
    }

}
