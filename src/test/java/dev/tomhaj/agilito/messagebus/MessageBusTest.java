package dev.tomhaj.agilito.messagebus;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MessageBusTest {

    private final List<Object> handledMessages = new ArrayList<>();

    private static Stream<Arguments> handleOnlySingleMessageSource() {
        return Stream.of(
                Arguments.of(new FirstMessage(), 2),
                Arguments.of(new SecondMessage(), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("handleOnlySingleMessageSource")
    void handleOnlySingleMessage(Object givenMessage) {
        // given
        var messageBus = MessageBusFactory.commandBus(List.of(
                new FirstMessage.Handler(handledMessages),
                new SecondMessage.Handler(handledMessages)
        ));

        // when
        messageBus.dispatch(givenMessage);

        // then
        assertThat(handledMessages).containsOnly(givenMessage);
    }

    @ParameterizedTest
    @MethodSource("handleOnlySingleMessageSource")
    void handleMultipleTimes(Object givenMessage, int handledBy) {
        // given
        var messageBus = MessageBusFactory.eventBus(List.of(
                new FirstMessage.FirstSubscriber(handledMessages),
                new FirstMessage.SecondSubscriber(handledMessages)
        ));

        // when
        messageBus.dispatch(givenMessage);

        // then
        assertThat(handledMessages).hasSize(handledBy);

        var expectedHandledMessages = new ArrayList<>();
        for (int i = 0; i < handledBy; i++) {
            expectedHandledMessages.add(givenMessage);
        }

        assertThat(handledMessages).containsExactlyInAnyOrderElementsOf(expectedHandledMessages);
    }

}
