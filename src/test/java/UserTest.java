
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    EmailSenderService service = new EmailSenderService();

    @Test
    public void checkFalseEmail() {

        User user = new User(
                "dzavdazvgdvgaz",
                "test",
                "test",
                LocalDate.now().minusYears(21),
                "abcdefgh"
        );

        Exception exception = assertThrows(Exception.class, user::isValid);
        assertEquals("Wrong email format!", exception.getMessage());
    }

    @Test
    public void checkEmptyFirstname() {
        User user = new User(
                "test@test.com",
                "",
                "test",
                LocalDate.now().minusYears(21),
                "abcdefgh"
        );

        Exception exception = assertThrows(Exception.class, user::isValid);
        assertEquals("Firstname is empty!", exception.getMessage());
    }


    @Test
    public void checkEmptySurname() {
        User user = new User(
                "test@test.com",
                "test",
                "",
                LocalDate.now().minusYears(21),
                "abcdefgh"
        );

        Exception exception = assertThrows(Exception.class, user::isValid);
        assertEquals("Surname is empty!", exception.getMessage());
    }

    @Test
    public void checkNullBirthDate() {
        User user = new User(
                "test@test.com",
                "test",
                "test",
                null,
                "abcdefgh"
        );

        Exception exception = assertThrows(Exception.class, user::isValid);
        assertEquals("A birthdate is required", exception.getMessage());
    }

    @Test
    public void checkMinusThirteen() {
        User user = new User(
                "test@test.com",
                "test",
                "test",
                LocalDate.now().minusYears(10),
                "abcdefgh"
        );

        Exception exception = assertThrows(Exception.class, user::isValid);
        assertEquals("User is minus thirteen!", exception.getMessage());
    }

    @Test
    public void checkValidUser() throws Exception {
        User user = new User(
                "test@test.com",
                "test",
                "test",
                LocalDate.now().minusYears(18),
                "abcdefghij"
        );

        assertTrue(user.isValid());
    }

    @Test
    public void checkPasswordMinusEight () {
        User user = new User(
                "test@test.com",
                "test",
                "test",
                LocalDate.now().minusYears(21),
                "a".repeat(3)
        );

        Exception exception = assertThrows(Exception.class, user::isValid);
        assertEquals("Password is not between 8 and 40", exception.getMessage());
    }

    @Test
    public void checkPasswordAboveForty () {
        User user = new User(
                "test@test.com",
                "test",
                "test",
                LocalDate.now().minusYears(21),
                "a".repeat(50)
        );

        Exception exception = assertThrows(Exception.class, user::isValid);
        assertEquals("Password is not between 8 and 40", exception.getMessage());
    }

    @Test
    public void checkSetToDoList() throws Exception {
        User user = new User(
                "test@test.com",
                "test",
                "test",
                LocalDate.now().minusYears(21),
                "abcdefghij"
        );

        TodoList todolist = new TodoList(service);
        assertTrue(user.setTodolist(todolist));
    }

    @Test
    public void checkDuplicateToDoList() throws Exception {
        User user = new User(
                "test@test.com",
                "test",
                "test",
                LocalDate.now().minusYears(21),
                "abcdefghij"
        );



        TodoList todolist = new TodoList(service);
        TodoList todolist2 = new TodoList(service);
        user.setTodolist(todolist);

        Exception exception = assertThrows(Exception.class, () -> user.setTodolist(todolist2));
        assertEquals("A todoList is already set", exception.getMessage());
    }
}

