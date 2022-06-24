import java.time.LocalDate;

public class User {
    private final String email;
    private final String firstname;
    private final String surname;
    private final LocalDate birthdate;
    private final String password;
    private TodoList todolist = null;

    public User(String email, String firstname, String surname, LocalDate birthdate, String password) {
        this.email = email;
        this.firstname = firstname;
        this.surname = surname;
        this.password = password;
        this.birthdate = birthdate;
    }

    public boolean setTodolist(TodoList todolist) throws Exception {
        if (this.todolist != null) {
            throw new Exception("A todoList is already set");
        } else {
            this.todolist = todolist;
            return true;
        }
    }

    public boolean isValid() throws Exception {

        if (!this.email.matches("^(.+)@(.+)$")) {
            throw new Exception("Wrong email format!");
        }

        if (this.firstname.isEmpty()) {
            throw new Exception("Firstname is empty!");
        }

        if (this.surname.isEmpty()) {
            throw new Exception("Surname is empty!");
        }

        if (this.birthdate == null) {
            throw new Exception("A birthdate is required");
        }

        if (this.password.length() < 8 || this.password.length() > 40) {
            throw new Exception("Password is not between 8 and 40");
        }

        if (LocalDate.now().minusYears(13).isBefore(this.birthdate)) {
            throw new Exception("User is minus thirteen!");
        }

        return true;
    }


}
