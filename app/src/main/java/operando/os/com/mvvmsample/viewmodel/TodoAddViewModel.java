package operando.os.com.mvvmsample.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

public class TodoAddViewModel extends BaseObservable {

    public ObservableBoolean isEnable;

    public TodoAddViewModel() {
        isEnable = new ObservableBoolean(false);
    }

//    @BindingAdapter("addTextWatcher")
//    public void addTextWatcher(TextView view) {
//        RxTextView.afterTextChangeEvents(view).map(new Func1<TextViewAfterTextChangeEvent, Boolean>() {
//            @Override
//            public Boolean call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {
//                return !TextUtils.isEmpty(textViewAfterTextChangeEvent.editable().toString());
//            }
//        }).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean enabled) {
//                isEnable.set(enabled);
//            }
//        });
//    }

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
                isEnable.set(!TextUtils.isEmpty(editable.toString()));
            }
        };
    }

//    @BindingAdapter("onClick")
//    public void setOnClick() {
//
////        binding.todoSubmit.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Todo todo = new Todo(binding.todoEdit.getText().toString(), false);
////                TodoDataRepository.add(todo);
////                setResult(RESULT_OK);
////                finish();
////            }
////        });
//    }
}
