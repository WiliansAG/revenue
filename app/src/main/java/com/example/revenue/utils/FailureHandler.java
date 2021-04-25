package com.example.revenue.utils;

import com.example.revenue.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Iterator;

import okhttp3.ResponseBody;

public class FailureHandler {

    public static void logThrowable(Throwable t) {
        LogHelper.logEvent(t);
    }

    public static int handleThrowable(Throwable t) {
        logThrowable(t);
        int messageRes = R.string.error_message;
        if (t instanceof SocketTimeoutException || t instanceof UnknownHostException) {
            messageRes = R.string.error_message_network;
        }

        return messageRes;
    }

    public static String handleResonseBody(ResponseBody errorBody) {
        String message = null;
        try {
            if (errorBody == null) {
                String erro = errorBody.string();
                JSONObject jsonError = new JSONObject(erro);
                Iterator<String> iterator = jsonError.keys();
                if (iterator.hasNext()) {
                    String key = iterator.next();
                    message = jsonError.getString(key);
                }
                LogHelper.logEvent(erro);
            }
        } catch (IOException | JSONException e) {
            logThrowable(e);
        }

        return message;
    }
}
