package dev.tomhaj.agilito.messagebus;

import java.util.List;

class FirstMessage {

    static class Handler extends CommonHandler<FirstMessage> {

        Handler(List<Object> handledMessages) {
            super(handledMessages);
        }

    }

    static class FirstSubscriber extends CommonHandler<FirstMessage> {

        FirstSubscriber(List<Object> handledMessages) {
            super(handledMessages);
        }

    }

    static class SecondSubscriber extends CommonHandler<FirstMessage> {

        SecondSubscriber(List<Object> handledMessages) {
            super(handledMessages);
        }

    }

}
