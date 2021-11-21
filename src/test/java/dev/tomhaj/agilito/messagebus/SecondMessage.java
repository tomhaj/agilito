package dev.tomhaj.agilito.messagebus;

import java.util.List;

class SecondMessage {

    static class Handler extends CommonHandler<SecondMessage> {

        Handler(List<Object> handledMessages) {
            super(handledMessages);
        }

    }

}
