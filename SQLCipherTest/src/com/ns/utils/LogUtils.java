package com.ns.utils;

import android.util.Log;

/**
 * ��־���������
 * 
 * @author wll
 * 
 */
public class LogUtils {
	
    private static int log_level = 0;
    /**verbose����*/
    private static int log_level_v = 0;
    /**debug����*/
    private static int log_level_d = 1;
    /**info����*/
    private static int log_level_i = 2;
    /**warn����*/
    private static int log_level_w = 3;
    /**error����*/
    private static int log_level_e = 4;

	/**
	 * ��ӡ�쳣��Ϣ
	 * 
	 * @param clazz
	 * @param message
	 */
	public static void logError(Class clazz, Object message) {
	    logError(clazz.toString(), message);
	}
	
	/**
     * ��ӡ�쳣��Ϣ
     * 
     * @param clazz
     * @param message
     */
    public static void logError(String tag, Object message) {
        if (log_level <= log_level_e) {
            Log.e(tag, "ERROR-->" + message);
        }
    }

	/**
	 * ��ӡ���������е�debug��Ϣ
	 * 
	 * @param clazz
	 * @param message
	 */
	public static void logDebug(Class clazz, Object message) {
	    logDebug(clazz.toString(),message);
	}
	/**
     * ��ӡ���������е�debug��Ϣ
     * 
     * @param tag
     * @param message
     */
    public static void logDebug(String tag, Object message) {

        if (log_level <= log_level_d) {
             Log.d(tag, "DEBUG-->" + message);
        }
    }
    
    /**
     * ��ӡ���������е�verbose��Ϣ
     * 
     * @param clazz
     * @param message
     */
    public static void logVerbose(Class clazz, Object message) {
        logVerbose(clazz.toString(),message);
    }
    /**
     * ��ӡ���������е�info��Ϣ
     * 
     * @param tag
     * @param message
     */
    public static void logVerbose(String tag, Object message) {

        if (log_level <= log_level_v) {
            Log.v(tag, "VERBOSE-->" + message);
        }
    }
    /**
     * ��ӡ���������е�info��Ϣ
     * 
     * @param clazz
     * @param message
     */
    public static void logInfo(Class clazz, Object message) {
        logInfo(clazz.toString(),message);
    }
    /**
     * ��ӡ���������е�info��Ϣ
     * 
     * @param tag
     * @param message
     */
    public static void logInfo(String tag, Object message) {

        if (log_level <= log_level_i) {
            Log.i(tag, "INFO-->" + message);
        }
    }
    /**
     * ��ӡ���������е�warning��Ϣ
     * 
     * @param clazz
     * @param message
     */
    public static void logWarn(Class clazz, Object message) {
        logWarn(clazz.toString(),message);
    }
    /**
     * ��ӡ���������е�warning��Ϣ
     * 
     * @param tag
     * @param message
     */
    public static void logWarn(String tag, Object message) {

        if (log_level <= log_level_w) {
            Log.w(tag, "WARNING-->" + message);
        }
    }
    
}
