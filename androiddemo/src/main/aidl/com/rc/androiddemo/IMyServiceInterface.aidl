// IMyServiceInterface.aidl
package com.rc.androiddemo;

// Declare any non-default types here with import statements

interface IMyServiceInterface {

    void stopService();
    void startService(String id);
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
