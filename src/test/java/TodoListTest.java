import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;

public class TodoListTest {

    EmailSenderService service;

    @BeforeEach
    public void setUp() {
        this.service = Mockito.mock(EmailSenderService.class);
    }

    @Test
    public void checkAboveTenItems() throws Exception {
        TodoList todoList = new TodoList(service);
        Item item = new Item(
                "new name",
                LocalDateTime.now(),
                "new content"
        );

        for (int i = 1; i <= 10; i++) {
            Item item_for = new Item(
                    "new name"+i,
                    LocalDateTime.now().plusDays(i),
                    "new content"
            );
            todoList.add(item_for);
        }

        Exception exception = assertThrows(Exception.class, () -> todoList.add(item));
        assertEquals("Too many items", exception.getMessage());
    }

    @Test
    public void checkNotUniqueItem() throws Exception {
        TodoList todoList = new TodoList(service);
        Item item1 = new Item(
                "new name",
                LocalDateTime.now(),
                "new content"
        );
        todoList.add(item1);

        Item item2 = new Item(
                "new name",
                LocalDateTime.now().minusDays(1),
                "new content"
        );

        Exception exception = assertThrows(Exception.class, () -> todoList.add(item2));
        assertEquals("Already exist", exception.getMessage());
    }

    @Test
    public void checkMinusThirtyDate() throws Exception {
        TodoList todoList = new TodoList(service);
        Item item1 = new Item(
                "new name",
                LocalDateTime.now(),
                "new content"
        );
        todoList.add(item1);

        Item item2 = new Item(
                "new name2",
                LocalDateTime.now(),
                "new content"
        );

        Exception exception = assertThrows(Exception.class, () -> todoList.add(item2));
        assertEquals("You have to wait more", exception.getMessage());
    }

    @Test
    public void checkAddItems() throws Exception {
        TodoList todoList = new TodoList(service);
        Item item1 = new Item(
                "new name",
                LocalDateTime.now(),
                "new content"
        );

        assertTrue(todoList.add(item1));

        Item item2 = new Item(
                "new name2",
                LocalDateTime.now().plusHours(1),
                "new content"
        );

        assertTrue(todoList.add(item2));
    }

    @Test
    public void checkMailSend() throws Exception {
        TodoList todoList = new TodoList(service);
        for (int i = 1; i <= 7; i++) {
            Item item_for = new Item(
                    "new name"+i,
                    LocalDateTime.now().plusHours(i),
                    "new content"
            );
            todoList.add(item_for);
        }

        Mockito.verify(service, Mockito.never()).sendMail();

        Item item = new Item(
                "new name 7",
                LocalDateTime.now().plusHours(8),
                "new content"
        );
        todoList.add(item);

        Mockito.verify(service, Mockito.times(1)).sendMail();

        Item item2 = new Item(
                "new name 8",
                LocalDateTime.now().plusHours(9),
                "new content"
        );
        todoList.add(item2);

        Item item3 = new Item(
                "new name 9",
                LocalDateTime.now().plusHours(10),
                "new content"
        );
        todoList.add(item3);

        Mockito.verify(service, Mockito.times(1)).sendMail();
    }
}
