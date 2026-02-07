package testData;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

import static constants.TextConstants.*;

public class ParameterizedTestData {
    private static Stream<Arguments> dropdownListData() {
        return Stream.of(
                Arguments.of(PRICE_QUESTION, PRICE_ANSWER),
                Arguments.of(MANY_SCOOTERS_QUESTION, MANY_SCOOTERS_ANSWER),
                Arguments.of(RENT_TIME_QUESTION, RENT_TIME_ANSWER),
                Arguments.of(TODAY_ORDER_QUESTION, TODAY_ORDER_ANSWER),
                Arguments.of(PROLONGATION_QUESTION, PROLONGATION_ANSWER),
                Arguments.of(CHARGER_QUESTION, CHARGER_ANSWER),
                Arguments.of(ORDER_CANCELLATION_QUESTION, ORDER_CANCELLATION_ANSWER),
                Arguments.of(DISTANCE_QUESTION, DISTANCE_ANSWER)
        );
    }

    private static Stream<Arguments> userTestData() {
        return Stream.of(
                Arguments.of(new TestUser("Иван", "Иванов", "ул. Пушкина, д. 1", "+79998887766")),
                Arguments.of(new TestUser("Владимир", "Попов", "ул. Гоголя, д. 5", "+79997778855"))
        );
    }

}
