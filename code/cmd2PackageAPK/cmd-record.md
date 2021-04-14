参考: https://www.jianshu.com/p/3f89b4478770



```
配置
C:\Users\14501\AppData\Local\Android\Sdk\build-tools\30.0.2\aapt2.exe 
C:\Users\14501\AppData\Local\Android\Sdk\platforms\android-30\android.jar
C:\Users\14501\AppData\Local\Android\Sdk\build-tools\30.0.2\dx.bat
C:\Users\14501\AppData\Local\Android\Sdk\build-tools\30.0.2\appsigner.bat

C:\Users\14501\AppData\Local\Android\Sdk\build-tools\30.0.2\aapt2.exe compile src\main\res\layout\activity_main.xml -o compiled
C:\Users\14501\AppData\Local\Android\Sdk\build-tools\30.0.2\aapt2.exe compile src\main\res\drawable\ic_launcher.png -o compiled

C:\Users\14501\AppData\Local\Android\Sdk\build-tools\30.0.2\aapt2.exe link -o resources.ap_ -I C:\Users\14501\AppData\Local\Android\Sdk\platforms\android-30\android.jar compiled\layout_activity_main.xml.flat compiled\drawable_ic_launcher.png.flat --java src\main\java --manifest src\main\AndroidManifest.xml

javac -d build -cp C:\Users\14501\AppData\Local\Android\Sdk\platforms\android-30\android.jar src\main\java\com\a\bbb\*.java

C:\Users\14501\AppData\Local\Android\Sdk\build-tools\30.0.2\dx.bat --dex --output=clases.dex build
C:\Users\14501\AppData\Local\Android\Sdk\build-tools\30.0.2\d8.bat C:\androidWorkSpace\test\sdkAppX\build\com\a\bbb\*.class --lib C:\Users\14501\AppData\Local\Android\Sdk\platforms\android-30\android.jar
Failure [INSTALL_FAILED_INVALID_APK: Scanning Failed.: Package /data/app/ base.apk code is missing


jarsigner -verbose -keystore C:\hc\test.jks -signedjar  C:\Users\14501\Desktop\appR.apk C:\androidWorkSpace\test\sdkAppX\app.apk 111111

C:\Users\14501\AppData\Local\Android\Sdk\build-tools\30.0.2\apksigner.bat verify -v C:\Users\14501\Desktop\appR.apk

// apksigner sign --ks (签名地址) --ks-key-alias (别名) --out (签名后的apk地址) (待签名apk地址)
C:\Users\14501\AppData\Local\Android\Sdk\build-tools\30.0.2\apksigner.bat sign --ks C:\hc\test.jks --ks-key-alias 111111 --out C:\Users\14501\Desktop\appR.apk C:\androidWorkSpace\test\sdkAppX\app.apk
```

