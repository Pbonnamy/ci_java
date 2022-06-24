import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TodoList {
    private final ArrayList<Item> list = new ArrayList<>();
    private final EmailSenderService service;

    public TodoList(EmailSenderService service) {
        this.service = service;
    }

    public boolean add(Item item) throws Exception {

        if (!list.isEmpty()) {
            if (this.list.size() >= 10) {
                throw new Exception("Too many items");
            }

            for (Item value : this.list) {
                if (item.getName().equals(value.getName())) {
                    throw new Exception("Already exist");
                }
            }

            if (ChronoUnit.MINUTES.between(list.get(list.size()-1).getDate(), item.getDate()) < 30) {
                throw new Exception("You have to wait more");
            }

            if(this.list.size() == 7){
                this.service.sendMail();
            }
        }

        this.list.add(item);
        return true;
    }

}
