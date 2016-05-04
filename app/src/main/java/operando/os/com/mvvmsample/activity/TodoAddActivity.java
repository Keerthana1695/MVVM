package operando.os.com.mvvmsample.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import operando.os.com.mvvmsample.R;
import operando.os.com.mvvmsample.databinding.ActivityTodoAddBinding;
import operando.os.com.mvvmsample.messenger.TodoAddCompleteMessage;
import operando.os.com.mvvmsample.viewmodel.TodoAddViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class TodoAddActivity extends AppCompatActivity {

    private CompositeSubscription subscriptions = new CompositeSubscription();

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, TodoAddActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityTodoAddBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_add);
        TodoAddViewModel viewModel = new TodoAddViewModel();

        subscriptions.add(
                viewModel.messenger.register(TodoAddCompleteMessage.class)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<TodoAddCompleteMessage>() {
                            @Override
                            public void call(TodoAddCompleteMessage messenger) {
                                setResult(RESULT_OK);
                                finish();
                            }
                        })
        );

        binding.setViewModel(viewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptions.unsubscribe();
    }
}
