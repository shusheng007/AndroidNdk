//
// Created by Administrator on 2017/6/30.
//

#include "my-lib.h"
#include <jni.h>

extern "C"
JNIEXPORT jint JNICALL
Java_com_ss007_myfirstndkapp_MainActivity_sum(JNIEnv *env, jobject instance, jint a, jint b) {

    return a+b;
}
