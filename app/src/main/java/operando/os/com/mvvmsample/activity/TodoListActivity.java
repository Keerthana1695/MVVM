package operando.os.com.mvvmsample.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import operando.os.com.mvvmsample.R;
import operando.os.com.mvvmsample.TodoDataRepository;
import operando.os.com.mvvmsample.adapter.TodoListAdapter;
import operando.os.com.mvvmsample.databinding.ActivityMainBinding;
import operando.os.com.mvvmsample.model.Todo;

public class TodoListActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.todoList.setAdapter(new TodoListAdapter(this, TodoDataRepository.getTodoList()));
        binding.todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Todo todo = (Todo) parent.getAdapter().getItem(position);
                TodoDetailActivity.start(TodoListActivity.this, todo);
            }
        });
        binding.todoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = TodoAddActivity.createIntent(TodoListActivity.this);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            binding.todoList.setAdapter(new TodoListAdapter(this, TodoDataRepository.getTodoList()));
        }
    }
}
