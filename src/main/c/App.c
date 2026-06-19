#include "App.h"
#include <jni.h>

JNIEXPORT jint JNICALL Java_com_example_App_call(JNIEnv *env, jclass clazz, jint a, jint b) {
    return a + b;
}