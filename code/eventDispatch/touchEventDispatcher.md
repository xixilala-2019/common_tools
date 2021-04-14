### Android事件分发

```
Android事件分发的大致流程是：
Activity --->>> ViewGroup --->>> View
```

```
主要是三个分发来处理事件分发，分别为分发、拦截、触摸
1.dispatchTouchEvent()
2.onInterceptTouchEvent()(ViewGroup特有)
3.onTouchEvent()

分发事件：
	-ViewGroup：
		if onInterceptTouchEvent() == false
			则遍历子控件的dispatchTouchEvent()
		else 
			走到onTouchEvent()
			if onTouchEvent() == false
				返回到Activity的onTouchEvent()
			else 
				自己消耗
	-View：
    	if onTouchEvent() == false
    		不处理
    	else 
    		自己消耗
    		
拦截事件：
	-ViewGroup：
		true:自己处理，不遍历子控件
		false:默认处理，仍然遍历
		
触摸事件：
	不区分：
		true:自己消耗
		false:不处理
```













````
Activity.java

//调用以处理触摸屏事件。 您可以重写此方法，以在将所有触摸屏事件发送到窗口之前对其进行拦截。 对于应正常处理的触摸屏事件，请务必调用此实现。
public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
        //每当将按键，触摸或轨迹球事件调度到活动时调用。 如果您想知道用户在活动运行期间已经以某种方式与设备进行了交互，请实施此方法。 该回调和onUserLeaveHint旨在帮助活动智能地管理状态栏通知。 具体来说，是为了帮助活动确定取消通知的适当时间。
对活动的onUserLeaveHint回调的所有调用都将伴随对onUserInteraction的调用。 这样可以确保您的活动将被告知相关的用户活动，例如下拉通知窗格和触摸那里的项目。
请注意，此回调将为开始触摸手势的触摸动作调用，但可能不会为随后的触摸移动和触摸动作调用。
        onUserInteraction();
    }
    //这里会调用到Window的分发事件，而Android中Window是一个抽象类，它的唯一子类是PhoneWindow
    if (getWindow().superDispatchTouchEvent(ev)) {
        return true;
    }
    return onTouchEvent(ev);
}
````

```
PhoneWindow.java

@Override
public boolean superDispatchTouchEvent(MotionEvent event) {
    return mDecor.superDispatchTouchEvent(event);
}
```

```
DecorView.java

public boolean superDispatchTouchEvent(MotionEvent event) {
    return super.dispatchTouchEvent(event);
}
```

```
ViewGroup.java

@Override
public boolean dispatchTouchEvent(MotionEvent ev) {

    boolean handled = false;
    if (onFilterTouchEventForSecurity(ev)) {

        // 检查是否拦截这次事件.
        final boolean intercepted;
        if (actionMasked == MotionEvent.ACTION_DOWN
                || mFirstTouchTarget != null) {
            final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
            if (!disallowIntercept) {
                intercepted = onInterceptTouchEvent(ev);//是否自己处理拦截
                ev.setAction(action); // restore action in case it was changed
            } else {
                intercepted = false;
            }
        } else {
            intercepted = true;
        }
    }
    
   
    ...
    dispatchTransformedTouchEvent()
    ...
     //好些代码处理后，得到 handled 的值
     //
    return handled;
}


//传递事件给子View或者View，获得返回值
private boolean dispatchTransformedTouchEvent(MotionEvent event, boolean cancel,
        View child, int desiredPointerIdBits) {
    final boolean handled;
    ...
    //这个方法中执行，获得返回值
    if (child == null) {
    	...
    	handled = super.dispatchTouchEvent(event);
    	...
    } else {
    	...
        handled = child.dispatchTouchEvent(event);
        ...
    }
    ...
    return handled;
}
```

