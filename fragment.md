Android fragment的生命周期

1.**普通的单Fragment的生命周期** （使用的为 android.app.Fragment ）
```
E: F1 msg = onAttach Activity 
E: F1 msg = onCreate  
E: F1 msg = onViewCreated  
E: F1 msg = onActivityCreated  
E: F1 msg = onViewStateRestored  
E: F1 msg = onStart  
E: F1 msg = onResume  
```

2.多个Fragment，add添加，show/hide 切换

F1与F2切换
此时的步骤为 add(F1) - hide(F1) - show(F2) - hide(F2) - show(F1)

生命周期变化如下
```
E: F1 msg = onAttach Activity 
E: F1 msg = onCreate  
E: F1 msg = onViewCreated  
E: F1 msg = onActivityCreated  
E: F1 msg = onViewStateRestored  
E: F1 msg = onStart  
E: F1 msg = onResume  
E: F1 msg = onHiddenChanged true
E: F2 msg = onAttach Activity 
E: F2 msg = onCreate  
E: F2 msg = onViewCreated  
E: F2 msg = onActivityCreated  
E: F2 msg = onViewStateRestored  
E: F2 msg = onStart  
E: F2 msg = onResume  
E: F2 msg = onHiddenChanged true
E: F1 msg = onHiddenChanged false

```

当点击 home 键，再回到应用，有如下变化
```
E: F1 msg = onPause  
E: F2 msg = onPause  
E: F1 msg = onSaveInstanceState  
E: F2 msg = onSaveInstanceState  
E: F1 msg = onStop  
E: F2 msg = onStop  
E: F1 msg = onStart  
E: F2 msg = onStart  
E: F1 msg = onResume  
E: F2 msg = onResume  

```

3.使用 replace 替换 Fragment 

当F1 跳转到 F2 ,F1会销毁
```
E: F1 msg = onAttach Activity 
E: F1 msg = onCreate  
E: F1 msg = onViewCreated  
E: F1 msg = onActivityCreated  
E: F1 msg = onViewStateRestored  
E: F1 msg = onStart  
E: F1 msg = onResume  
E: F1 msg = onPause  
E: F1 msg = onStop  
E: F1 msg = onDestroyView  
E: F1 msg = onDestroy  
E: F1 msg = onDetach  
E: F2 msg = onAttach Activity 
E: F2 msg = onCreate  
E: F2 msg = onViewCreated  
E: F2 msg = onActivityCreated  
E: F2 msg = onViewStateRestored  
E: F2 msg = onStart  
E: F2 msg = onResume  

```
当点击 Home 键，再回到应用，生命周期变化如下 
```
E: F2 msg = onPause  
E: F2 msg = onSaveInstanceState  
E: F2 msg = onStop  
E: F2 msg = onStart  
E: F2 msg = onResume  

```




---------
使用AndroidX 的Fragment

1.add/show/hide 切换 (add(F1) - hide(F1) - show(F2) - hide(F2) - show(F1))
步骤如下，相较于support版的Fragment,多了一个 onAttach(context)
```
E: F1 msg = onAttach Activity 
E: F1 msg = onAttach  Context
E: F1 msg = onCreate  
E: F1 msg = onViewCreated  
E: F1 msg = onActivityCreated  
E: F1 msg = onViewStateRestored  
E: F1 msg = onStart  
E: F1 msg = onResume  
E: F2 msg = onAttach Activity 
E: F2 msg = onAttach  Context
E: F2 msg = onCreate  
E: F1 msg = onHiddenChanged true
E: F2 msg = onViewCreated  
E: F2 msg = onActivityCreated  
E: F2 msg = onViewStateRestored  
E: F2 msg = onStart  
E: F2 msg = onResume  
E: F2 msg = onHiddenChanged true
E: F1 msg = onHiddenChanged false

```

点击 Home 键再回到应用，变化如下，和 support 版本一致

```
E: F1 msg = onPause  
E: F2 msg = onPause  
E: F2 msg = onSaveInstanceState  
E: F1 msg = onSaveInstanceState  
E: F1 msg = onStop  
E: F2 msg = onStop  
E: F1 msg = onStart  
E: F2 msg = onStart  
E: F1 msg = onResume  
E: F2 msg = onResume 
```

2.使用 replace 切换 Fragment 
F1 切换到 F2 
相比于 support ，有类似于Activity的生命周期的切换， 先 Pause A ,  Create B , Start B, Stop A 

```
E: F1 msg = onAttach Activity 
E: F1 msg = onAttach  Context
E: F1 msg = onCreate  
E: F1 msg = onViewCreated  
E: F1 msg = onActivityCreated  
E: F1 msg = onViewStateRestored  
E: F1 msg = onStart  
E: F1 msg = onResume  
E: F2 msg = onAttach Activity 
E: F2 msg = onAttach  Context
E: F2 msg = onCreate  
E: F1 msg = onPause  
E: F1 msg = onStop  
E: F1 msg = onDestroyView  
E: F1 msg = onDestroy  
E: F1 msg = onDetach  
E: F2 msg = onViewCreated  
E: F2 msg = onActivityCreated  
E: F2 msg = onViewStateRestored  
E: F2 msg = onStart  
E: F2 msg = onResume  

```

点击 Home 键，变化如下

```
E: F2 msg = onPause  
E: F2 msg = onSaveInstanceState  
E: F2 msg = onStop  
E: F2 msg = onStart  
E: F2 msg = onResume  

```







