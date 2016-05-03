package operando.os.com.mvvmsample.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import operando.os.com.mvvmsample.R;
import operando.os.com.mvvmsample.databinding.ActivityTodoDetailBinding;
import operando.os.com.mvvmsample.model.Todo;

public class TodoDetailActivity extends AppCompatActivity {

    public static Intent createIntent(Context context, Todo todo) {
        Intent i = new Intent(context, TodoDetailActivity.class);
        i.putExtra("todo", todo);
        return i;
    }

    public static void start(Context context, Todo todo) {
        context.startActivity(createIntent(context, todo));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);
        ActivityTodoDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_detail);
        Todo todo = getIntent().getParcelableExtra("todo");
        binding.setTodo(todo);
    }
}
