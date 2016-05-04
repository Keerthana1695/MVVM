package operando.os.com.mvvmsample.viewmodel;

import android.view.View;
import android.widget.AdapterView;

import operando.os.com.mvvmsample.messenger.Messenger;
import operando.os.com.mvvmsample.messenger.TodoAddMessage;
import operando.os.com.mvvmsample.messenger.TodoListClickMessage;
import operando.os.com.mvvmsample.model.Todo;

public class TodoListViewModel {

    public Messenger messenger = new Messenger();

    public AdapterView.OnItemClickListener getTodoListItemClickListener() {
        // これだけの処理ならViewに書いていいのでは？？
        // 仕様変更でクリックした後に何かして通知したいみたいな時には柔軟に対応できるけど...
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Todo todo = (Todo) parent.getAdapter().getItem(position);
                messenger.send(new TodoListClickMessage(todo));
            }
        };
    }

    public void addTodo(View v) {
        messenger.send(new TodoAddMessage());
    }
}
