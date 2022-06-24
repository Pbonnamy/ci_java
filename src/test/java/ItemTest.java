import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    @Test
    public void checkNullDate() {
        Item item = new Item(
              "new name",
                null,
                "aaaaaaaaa"
        );

        Exception exception = assertThrows(Exception.class, item::isValid);
        assertEquals("A date is required", exception.getMessage());
    }

    @Test
    public void checkEmptyName() {
        Item item = new Item(
                "aaaaa",
                LocalDateTime.now(),
                "aaaaaaaaa"
        );

        Exception exception = assertThrows(Exception.class, item::isValid);
        assertEquals("A name is required", exception.getMessage());
    }

    @Test
    public void checkLongContent() {
        Item item = new Item(
                "test",
                LocalDateTime.now(),
                "a".repeat(1200)
        );

        Exception exception = assertThrows(Exception.class, item::isValid);
        assertEquals("Content is too long", exception.getMessage());
    }

    @Test
    public void checkIsValid() throws Exception {
        Item item = new Item(
                "test",
                LocalDateTime.now(),
                "new content"
        );

        assertTrue(item.isValid());
    }
}
