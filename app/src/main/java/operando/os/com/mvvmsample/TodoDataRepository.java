package operando.os.com.mvvmsample;

import java.util.ArrayList;
import java.util.List;

import operando.os.com.mvvmsample.model.Todo;

public class TodoDataRepository {

    private static List<Todo> todoList = new ArrayList<Todo>() {
        {
            add(new Todo("test", false));
            add(new Todo("test1", false));
            add(new Todo("test2", true));
            add(new Todo("test3", false));
        }
    };

    public static void add(Todo todo) {
        todoList.add(todo);
    }

    public static List<Todo> getTodoList() {
        return todoList;
    }
}
