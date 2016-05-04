package operando.os.com.mvvmsample.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import operando.os.com.mvvmsample.R;
import operando.os.com.mvvmsample.TodoDataRepository;
import operando.os.com.mvvmsample.adapter.TodoListAdapter;
import operando.os.com.mvvmsample.databinding.ActivityMainBinding;
import operando.os.com.mvvmsample.messenger.TodoAddMessage;
import operando.os.com.mvvmsample.messenger.TodoListClickMessage;
import operando.os.com.mvvmsample.viewmodel.TodoListViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class TodoListActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        TodoListViewModel viewModel = new TodoListViewModel();

        subscriptions.add(
                viewModel.messenger.register(TodoAddMessage.class)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<TodoAddMessage>() {
                            @Override
                            public void call(TodoAddMessage todoAddMessage) {
                                Intent i = TodoAddActivity.createIntent(TodoListActivity.this);
                                startActivityForResult(i, 1);
                            }
                        })
        );

        subscriptions.add(
                viewModel.messenger.register(TodoListClickMessage.class)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<TodoListClickMessage>() {
                            @Override
                            public void call(TodoListClickMessage todoListClickMessage) {
                                TodoDetailActivity.start(TodoListActivity.this, todoListClickMessage.getClickTodo());
                            }
                        })
        );

        binding.setViewModel(viewModel);
        binding.todoList.setAdapter(new TodoListAdapter(this, TodoDataRepository.getTodoList()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            binding.todoList.setAdapter(new TodoListAdapter(this, TodoDataRepository.getTodoList()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptions.unsubscribe();
    }
}