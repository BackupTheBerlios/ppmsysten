
package moduls.log;

import moduls.jcorex32.lib.SystenLib;

import org.apache.log4j.Category;

import runtimes.shutdown.Shutdown;
import visual.error.ErrorCodeFrm;

public class Log {

	public void log(Object clas, String message, int code){
		SystenLib sl=new SystenLib();
		
		Category.getInstance(Log.class.getName()).info(clas+": '"+message+"'");
		
		sl.setErrorLvl(code);
		
		sl.setCurSessionClose();
		
		new ErrorCodeFrm().ECFError(message);		

		new Shutdown().setShutdown(code);
	}
	
	public void log(Object clas, String message){
		Category.getInstance(Log.class.getName()).info(clas+": '"+message+"'");
		
		new ErrorCodeFrm().ECFMsg(message);
	}
	
	public void print(String message){
		System.out.println(message);
	}
	
	public void print(int message){
		System.out.println(Integer.toString(message));
	}
}