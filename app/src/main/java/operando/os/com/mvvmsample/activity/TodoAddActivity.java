package operando.os.com.mvvmsample.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import operando.os.com.mvvmsample.R;
import operando.os.com.mvvmsample.TodoDataRepository;
import operando.os.com.mvvmsample.databinding.ActivityTodoAddBinding;
import operando.os.com.mvvmsample.model.Todo;

public class TodoAddActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, TodoAddActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityTodoAddBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_add);
        binding.todoSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo todo = new Todo(binding.todoEdit.getText().toString(), false);
                TodoDataRepository.add(todo);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
