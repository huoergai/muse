#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_huoergai_muse_tools_TmdbUtil_hello(JNIEnv *env, jobject) {
    std::string hello = "hello";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring  JNICALL
Java_com_huoergai_muse_tools_TmdbUtil_hi(JNIEnv *env, jobject) {
    std::string hi = "hi";
    return env->NewStringUTF(hi.c_str());
}