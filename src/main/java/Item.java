import java.time.LocalDate;
import java.time.LocalDateTime;

public class Item {
    private final String name;
    private final LocalDateTime date;
    private final String content;

    public Item(String name, LocalDateTime date, String content) {
        this.name = name;
        this.date = date;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isValid() throws Exception {
        if (this.name.isEmpty()) {
            throw new Exception("A name is required");
        }

        if (this.date == null) {
            throw new Exception("A date is required");
        }

        if (this.content.length() > 1000) {
            throw new Exception("Content is too long");
        }

        return true;
    }
}
