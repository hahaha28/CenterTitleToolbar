# Android 标题居中的 Toolbar

CenterTitleToolbar 继承自 Toolbar，隐藏了默认的左侧标题，在中央添加了一个标题。

之所以做这个封装是因为每次都在 Toolbar 里面添加 TextView 再引用很烦人。

## 导入

[![](https://jitpack.io/v/hahaha28/CenterTitleToolbar.svg)](https://jitpack.io/#hahaha28/CenterTitleToolbar)

首先在工程的build.gradle添加
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
然后再项目的build.gradle添加
```groovy
dependencies {
    implementation 'com.github.hahaha28:CenterTitleToolbar:1.0.0'
}
```

## 使用

```xml
<fun.inaction.centertitletoolbar.CenterTitleToolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
       
        app:centerTitle="标题"
        
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>
```

```java
...
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    // CenterTitleToolbar 是 Toolbar 的子类
    CenterTitleToolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
}
```

## xml属性

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="CenterTitleToolbar">
        <attr name="centerTitle" format="string|reference"/>
        <attr name="centerTitleSize" format="dimension|reference"/>
        <attr name="centerTitleColor" format="color|reference"/>
        <attr name="centerTitleTextAppearance" format="reference"/>
    </declare-styleable>
</resources>
```

