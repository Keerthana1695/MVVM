package operando.os.com.mvvmsample.messenger;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import operando.os.com.mvvmsample.model.Todo;

@Getter
@RequiredArgsConstructor
public class TodoListClickMessage implements Message {

    private final Todo clickTodo;
}
