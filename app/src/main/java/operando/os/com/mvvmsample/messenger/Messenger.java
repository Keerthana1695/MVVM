package operando.os.com.mvvmsample.messenger;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class Messenger {
    private final Subject<Message, Message> _bus =
            new SerializedSubject<>(PublishSubject.<Message>create());

    public void send(Message message) {
        _bus.onNext(message);
    }

    public <T extends Message> Observable<T> register(final Class<? extends T> messageClazz) {
        return _bus
                .ofType(messageClazz)
                .map(new Func1<Message, T>() {
                    @Override
                    public T call(Message message) {
                        return (T) message;
                    }
                });
    }
}
