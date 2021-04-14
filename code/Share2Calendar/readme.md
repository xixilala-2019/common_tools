以前搞过这样的，但是忘了。。。

长按菜单里添加一个自己的菜单项，这里加的是分享到日历，其实就是新建一个日历事件

找了好久，终于找到，参考 https://blog.csdn.net/chuyangchangxi/article/details/83715052



主要是用的 intent-filter

action系统用来判断你有这个处理文本的功能

data 判断处理类型

```xml
<intent-filter>

    <action android:name="android.intent.action.PROCESS_TEXT" />

    <data android:mimeType="text/plain" />

</intent-filter>
```