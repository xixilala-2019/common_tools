# Kotlin内联函数

### *编译时会把内联函数编译成正常语句*

#### 1.run

会返回最后一行，不管有没有 return

#### 2.apply

val abc = "123"

val res = abc.apply{

​	// this 指 abc  不能有return 

}

会返回本身

#### 3.let

val abc = "123"

abc.apply{

​	// it 指 abc  

}

没有返回值

#### 4.also

val abc = "123"

val res = abc.apply{

​	// it 指 abc  

}

有返回值

#### 5.with 

val abc = "123"

val edf = with(abc) {

​	//this 指 abc 不能 return 

}

返回地址

