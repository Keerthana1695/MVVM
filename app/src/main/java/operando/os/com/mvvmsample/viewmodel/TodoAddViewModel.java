package operando.os.com.mvvmsample.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import operando.os.com.mvvmsample.TodoDataRepository;
import operando.os.com.mvvmsample.messenger.Messenger;
import operando.os.com.mvvmsample.messenger.TodoAddCompleteMessenger;
import operando.os.com.mvvmsample.model.Todo;

public class TodoAddViewModel extends BaseObservable {

    public Messenger messenger = new Messenger();

    public ObservableBoolean isEnable;
    public ObservableField<String> title;

    public TodoAddViewModel() {
        isEnable = new ObservableBoolean(false);
        title = new ObservableField<>("");
    }

    public TextWatcher getTodoEditTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String title = editable.toString();
                TodoAddViewModel.this.title.set(title);
                isEnable.set(!TextUtils.isEmpty(title));
            }
        };
    }

    public void addTodo(View v) {
        Todo todo = new Todo(title.get(), false);
        TodoDataRepository.add(todo);
        messenger.send(new TodoAddCompleteMessenger());
    }
}
