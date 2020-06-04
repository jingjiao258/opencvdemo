package com.jing.opencvdemo;

public class JavaJni {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    public static native String stringFromJNI();
    //获得Canny边缘
    public static native void getEdge(Object bitmap);


    public static native long nativeCreateObject(String cascadeName, int minFaceSize);
    public static native void nativeDestroyObject(long thiz);
    public static native void nativeStart(long thiz);
    public static native void nativeStop(long thiz);
    public static native void nativeSetFaceSize(long thiz, int size);
    public static native void nativeDetect(long thiz, long inputImage, long faces);
}
