package operando.os.com.mvvmsample.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import operando.os.com.mvvmsample.R;
import operando.os.com.mvvmsample.databinding.RowTodoItemBinding;
import operando.os.com.mvvmsample.model.Todo;

public class TodoListAdapter extends BindableAdapter<Todo> {

    public TodoListAdapter(Context context, List<Todo> episodeList) {
        super(context, episodeList);
    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        RowTodoItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.row_todo_item, null, false);
        View v = binding.getRoot();
        v.setTag(binding);
        return v;
    }

    @Override
    public void bindView(Todo item, int position, View view) {
        RowTodoItemBinding binding = (RowTodoItemBinding) view.getTag();
        binding.setTodo(item);
    }
}
