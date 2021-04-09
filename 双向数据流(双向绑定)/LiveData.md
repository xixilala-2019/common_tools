LiveData 源码分析

androidx.lifecycle.LiveData（2.0.0 版本）

```
class ViewModel {
	val textNum = MutableLiveData<Int>()（1）
	textNum.value = 1（2）
}

class MyActivity {
	mainViewModel.textNum.observe(this@MainActivity, object :Observer<Int>{
        override fun onChanged(t: Int?) {
			（3）
    	}
    })
}

```

第一步，声明一个 liveData

第二步，注册这个 liveData 的观察者 A

第三步，改变这个 liveData 的值，这时候在 A 处就可以接收到更新后的值



```
public abstract class LiveData<T> {

	private SafeIterableMap<Observer<? super T>, ObserverWrapper> mObservers =
            new SafeIterableMap<>();

	
    protected void setValue(T value) {
        ...
        dispatchingValue(null);//分发
    }
    
    void dispatchingValue(@Nullable ObserverWrapper initiator) {
    	...
    	for (Iterator<Map.Entry<Observer<? super T>, ObserverWrapper>> iterator =
            mObservers.iteratorWithAdditions(); iterator.hasNext(); ) {
            considerNotify(iterator.next().getValue());
            if (mDispatchInvalidated) {
            	break;
        	}
	    }
	    ...
	}
	
	private void considerNotify(ObserverWrapper observer) {
		...
		observer.mObserver.onChanged((T) mData);//观察者回调
		...
	}
}
```

在（1）初始化 liveData 的时候，会在内部初始化一个 SafeIterableMap（实质是一个LinkedList，用来存储这个 liveData 的各个观察者）

在（3）注册后，SafeIterableMap 会添加这个观察者到里面

当（2）发生时，liveData 内会更新这个值，并且遍历 SafeIterableMap ，通知各个观察者

