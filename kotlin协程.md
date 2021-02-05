## 协程-用起来真不错

协程是轻量级的线程。即自己内部实现类似线程的切换，这样不用像线程一样被cpu支配，而由用户自己操作。

#### 一、注释文档（机器翻译）

```kotlin
1.GlobalScope.launch(
	context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    ***
}
    在不阻塞当前线程的情况下启动新的协程，并将对协程的引用作为[Job]返回。当结果作业被[取消] [作业取消]时，协程被取消。
	协程上下文是从[CoroutineScope]继承的。可以使用[context]参数指定其他上下文元素。如果上下文没有任何调度程序或任何其他[ContinuationInterceptor]，则使用[Dispatchers.Default]。父作业也继承自[CoroutineScope]，但也可以用相应的[context]元素覆盖。
	默认情况下，协程将立即安排执行。其他启动选项可以通过`start`参数指定。有关详细信息，请参见[CoroutineStart]。可以将可选的[start]参数设置为[CoroutineStart.LAZY]以启动协程_lazily_。在这种情况下，协程[Job]以_new_状态创建。可以使用[start] [Job.start]函数显式启动它，并将在第一次调用[join] [Job.join]时隐式启动。
	默认情况下，此协程中未捕获的异常会取消上下文中的父作业（除非显式指定了[CoroutineExceptionHandler]），这意味着当将`launch`与另一个协程的上下文一起使用时，任何未捕获的异常都将导致取消亲子协程。请参阅[newCoroutineContext]以获取可用于新创建的协程的调试工具的描述。
	@param context 协程的[CoroutineScope.coroutineContext]上下文的附加功能。
	@param start 协程启动选项。默认值为[CoroutineStart.DEFAULT]。
	@param block 在提供的范围的上下文中将调用的协程代码。



2.GlobalScope.async(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    ***
}
	创建协程并返回其将来的结果，以实现[Deferred]。当延迟的结果为[cancelled] [Job.cancel]时，正在运行的协程将被取消。与其他语言和框架中的相似基元相比，生成的协程有一个关键差异：在未能执行*结构化并发*范式时，它取消了父作业（或外部作用域）。要更改该行为，可以使用监督父级（[SupervisorJob]或[supervisorScope]）。
	协程上下文是从[CoroutineScope]继承的，可以使用[context]参数指定其他上下文元素。如果上下文没有任何调度程序或任何其他[ContinuationInterceptor]，则使用[Dispatchers.Default]。父作业也继承自[CoroutineScope]，但也可以用相应的[context]元素覆盖。
	默认情况下，协程将立即安排执行。其他选项可以通过`start`参数指定。有关详细信息，请参见[CoroutineStart]。可以将可选的[start]参数设置为[CoroutineStart.LAZY]以启动协程_lazily_。在这种情况下，将以_new_状态创建结果[Deferred]。可以使用[start] [Job.start]函数显式启动它，并将在首次调用[join] [Job.join]，[await] [Deferred.await]或[awaitAll]时隐式启动。
	@param context 协程的[CoroutineScope.coroutineContext]上下文的附加功能。
	@param start 协程启动选项。默认值为[CoroutineStart.DEFAULT]。
	@param block 在提供的范围的上下文中将调用的协程代码。

3.withContext(
    context: CoroutineContext,
    block: suspend CoroutineScope.() -> T
): T {
    ***
}
	使用给定的协程上下文调用指定的阻断块，暂停直到完成，然后返回结果。
	通过使用coroutineContext + context（请参阅[CoroutineContext.plus]）将当前的[coroutineContext]与指定的[context]合并，可以得出[block]的结果上下文。该挂起功能是可以取消的。它立即检查结果上下文的取消，如果不是[active] [CoroutineContext.isActive]，则抛出[CancellationException]。
	此函数使用新上下文中的调度程序，如果指定了新的调度程序，则将[block]的执行转移到另一个线程中，并在完成时将其转移回原始调度程序。请注意，withContext调用的结果以可取消的方式分派到原始上下文中，这意味着如果调用了withContext的原始[coroutineContext]在其分派器开始执行代码时被取消了，它会丢弃`withContext`的结果并抛出[CancellationException]。
```

























参考

-http://www.kotlincn.net/docs/reference/coroutines/basics.html

-https://www.jianshu.com/p/76d2f47b900d

