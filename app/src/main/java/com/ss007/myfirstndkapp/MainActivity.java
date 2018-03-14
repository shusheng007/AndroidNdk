package com.ss007.myfirstndkapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity
{
    //加载'native-lib' 和'my-lib'两个库
    static
    {
        System.loadLibrary("native-lib");
        System.loadLibrary("my-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(String.format("调用native-lib（从源码构建的库） 库中的stringFromJNI函数返回值》%s%n%n调用my-lib(预构建的库)sum函数返回值》%s ",
                 stringFromJNI(),sum(30,20)));
    }

    /**
     * native-lib 中实现的方法
     * @return
     */
    public native String stringFromJNI();

    /**
     * my-lib 中实现的方法
     * @param a
     * @param b
     * @return
     */
    public native int sum(int a,int b);
}
