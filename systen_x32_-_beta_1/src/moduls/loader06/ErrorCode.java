
package moduls.loader06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import moduls.jcorex32.lib.SystenLib;
import moduls.log.Log;

public class ErrorCode {
	
	private static Properties EC=new Properties();
	
	public void loadErrorCode(){
		SystenLib 	sl=new SystenLib();
		Log 		l=new Log();
		
		try {
			EC.load(new FileInputStream("cfg/errorcodes"+sl.getLng()+".sys"));
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), "ERRORCODES"+sl.getLng().toUpperCase()+".SYS: not found", -1);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), "ERRORCODES"+sl.getLng().toUpperCase()+".SYS: error detected", -2);
		}
	}
	
	public String getErrorCode(String code){
		return EC.getProperty(code);
	}
}
