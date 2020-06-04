package com.jing.opencvdemo;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;

public class DetectionBasedTracker
{
    public DetectionBasedTracker(String cascadeName, int minFaceSize) {
        mNativeObj = JavaJni.nativeCreateObject(cascadeName, minFaceSize);
    }

    public void start() {
        JavaJni.nativeStart(mNativeObj);
    }

    public void stop() {
        JavaJni.nativeStop(mNativeObj);
    }

    public void setMinFaceSize(int size) {
        JavaJni.nativeSetFaceSize(mNativeObj, size);
    }

    public void detect(Mat imageGray, MatOfRect faces) {
        JavaJni.nativeDetect(mNativeObj, imageGray.getNativeObjAddr(), faces.getNativeObjAddr());
    }

    public void release() {
        JavaJni.nativeDestroyObject(mNativeObj);
        mNativeObj = 0;
    }

    private long mNativeObj = 0;

}
