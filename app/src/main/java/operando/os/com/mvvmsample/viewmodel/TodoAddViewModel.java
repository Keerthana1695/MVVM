package operando.os.com.mvvmsample.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

public class TodoAddViewModel extends BaseObservable {

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
}
