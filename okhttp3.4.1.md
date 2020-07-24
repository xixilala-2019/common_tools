okHttp 源码   ------基于 3.4.1

1.基本用法

```kotlin
val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build()

val request = Request.Builder()
                        .url("request url")
                        .build()
val newCall = okHttpClient.newCall(request)
newCall.enqueue(callback)
```

2.开始分析

```java
public class OkHttpClient implements Cloneable, Call.Factory
```

构造方法是 private 的，使用的是 Builder 的方式新建对象

```java
public static final class Builder {
    ...
    public OkHttpClient build() {
      return new OkHttpClient(this);
    }
}
```

Builder 类的其他方法都是设置请求的参数



okHttpClient.newCall() 

```java
@Override public Call newCall(Request request) {
  return new RealCall(this, request);
}

final class RealCall implements Call {
    ...
}

/**
 * A call is a request that has been prepared for execution. A call can be canceled. As this object
 * represents a single request/response pair (stream), it cannot be executed twice.
 */
//个人理解的翻译：一个 call 是个即将被执行的请求，可以取消，它代表的是一个单一的请求/回复，不能被执行两次。
public interface Call {
    ...
}
```

RealCall 是 Call 的实现类，没有发现其他的实现类。



newCall.enqueue(callback)

```java
@Override public void enqueue(Callback responseCallback) {
  synchronized (this) {
    if (executed) throw new IllegalStateException("Already Executed");
    executed = true;
  }
  client.dispatcher().enqueue(new AsyncCall(responseCallback));
}
```



client.dispatcher()返回一个  Dispatcher，继承 Object 

```
public final class Dispatcher {
	...
	
    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
           							 new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return executorService;
    }
	
	synchronized void enqueue(AsyncCall call) {
        if (runningAsyncCalls.size() < maxRequests && runningCallsForHost(call) < maxRequestsPerHost) {
          runningAsyncCalls.add(call);
          executorService().execute(call);
        } else {
          readyAsyncCalls.add(call);
        }
  	}
}
```



首先看  AsyncCall , 它其实是一个 Runnable 

```
final class AsyncCall extends NamedRunnable

public abstract class NamedRunnable implements Runnable
```



client.dispatcher()