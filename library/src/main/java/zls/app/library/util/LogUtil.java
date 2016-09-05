package zls.app.library.util;

import android.util.Log;

public class LogUtil {

	private static boolean debug = true;
	public static void i(){
		if(debug){
			StackTraceElement element = new Exception().getStackTrace()[1];
			String fileName = element.getFileName();
			String methodName = element.getMethodName();
			int lineNumber = element.getLineNumber();
			System.out.println("in " + fileName + " -- " + methodName + " -- " + lineNumber);
		}
	}
	public static void i(String s){
		if(debug){
			StackTraceElement element = new Exception().getStackTrace()[1];
			String fileName = element.getFileName();
			String methodName = element.getMethodName();
			int lineNumber = element.getLineNumber();
			System.out.println("in " + fileName + " -- " + methodName + " -- " + lineNumber
					+ "\n" + s);
		}
	}

	public static void i(String tag, String s){
		if(debug){
			StackTraceElement element = new Exception().getStackTrace()[1];
			String fileName = element.getFileName();
			String methodName = element.getMethodName();
			int lineNumber = element.getLineNumber();
            System.out.println("in " + fileName + " -- " + methodName + " -- " + lineNumber);
			Log.i(tag, s);
		}
	}
}
