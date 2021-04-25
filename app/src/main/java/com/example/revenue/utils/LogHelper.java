package com.example.revenue.utils;

import android.util.Log;

public class LogHelper {

    private static final boolean IS_LOGGIN_ENABLED = true;
    private static final String LOG_TAG = "REVENUE";
    private static final int STACK_TRACE_LEVEL_UP = 5;

    public static void logEvent(String message){
        if(IS_LOGGIN_ENABLED){
            Log.d(LOG_TAG, getClassNameMethodNameAndLineNumber() + message);
        }
    }

    public static void logEvent(Throwable t){
        if(IS_LOGGIN_ENABLED){
            String message = t.getMessage();
            if(message == null){
                message = t.getLocalizedMessage();
            }
            Log.d(LOG_TAG, getClassNameMethodNameAndLineNumber() + message);
        }
    }

    private static int getLineNumber(){
        return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVEL_UP].getLineNumber();
    }

    private static String getClassName(){
        String fileName = Thread.currentThread().getStackTrace()[STACK_TRACE_LEVEL_UP].getFileName();

        return fileName.substring(0,fileName.length() - 5);
    }

    private static String getMethodName(){
        return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVEL_UP].getMethodName();
    }

    private static String getClassNameMethodNameAndLineNumber(){
        return "[" + getClassName() + "." + getMethodName() + "()-" + getLineNumber() + "]: ";
    }
}
