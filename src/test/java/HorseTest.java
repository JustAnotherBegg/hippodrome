import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void firstArgumentNullConstructorTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class,()-> new Horse(null, 1, 1));

        //Если первый тест не пройдет, получим AssertionFailedException и даже не дойдем до этой проверки
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    void whiteSpaceFirstArgumentConstructorTest(String argument) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 1, 1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void secondArgumentNegativeConstructorTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Blank", -1, 1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void thirdArgumentNegativeConstructorTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> new Horse("Blank", 1, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest() {
        Horse horse = new Horse("Blank", 1, 1);
        assertEquals("Blank", horse.getName());
    }

    @Test
    void getSpeedTest() {
        Horse horse = new Horse("Blank", 5, 1);
        assertEquals(5, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        Horse horse = new Horse("Blank", 2, 10);
        assertEquals(10, horse.getDistance());
        horse = new Horse("Blank",2);
        assertEquals(0, horse.getDistance());
    }

    @ParameterizedTest
    @CsvSource({"10.0, 100.0"})
    void moveTest(double speed, double distance) {
        try(MockedStatic<Horse> mockHorse = Mockito.mockStatic(Horse.class)){
            mockHorse.when(()-> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.7);
            Horse horse = new Horse("Blank", speed, distance);
            horse.move();
            Assertions.assertEquals(distance + speed * Horse.getRandomDouble(0.2, 0.9), horse.getDistance());
            mockHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9), Mockito.times(2));
        }

    }

}