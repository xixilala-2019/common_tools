https://github.com/fengjundev/Android-Skin-Loader 

在基类中使用 

```
getLayoutInflater().setFactory(mSkinInflaterFactory);
```

1.自己的 Factory 把xml中所有 @ 开头的属性放到一个list中

2.在切换皮肤时，把解析皮肤文件(实质是一个apk)，反射得到它的 Resource 文件

3.然后遍历 1 中的 list ，使用 2 中的 Resource 加载资源



优点：

```
1.动态的，可以减小 apk 大小
2.不只能换颜色，还可以换图片
```



缺点：

```
1.只支持 xml 的布局
2.旧工程改造量大
```

