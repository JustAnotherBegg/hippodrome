import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void argumentNullConstructorTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void argumentEmptyListConstructorTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            horses.add(new Horse(String.valueOf(i), i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertArrayEquals(horses.toArray(Horse[]::new), hippodrome.getHorses().toArray(Horse[]::new));
    }

    @Test
    void moveTest() {

        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(()-> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            List<Horse> horses = new ArrayList<>();
            for (int i = 1; i < 51; i++) {
                horses.add(new Horse(String.valueOf(i), i));
            }
            Hippodrome hippodrome = new Hippodrome(horses);
            hippodrome.move();
            mockedStatic.verify(()-> Horse.getRandomDouble(0.2, 0.9), Mockito.times(50));
        }
    }

    @Test
    void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            horses.add(new Horse(String.valueOf(i), i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(30.0, hippodrome.getWinner().getDistance());
    }

}