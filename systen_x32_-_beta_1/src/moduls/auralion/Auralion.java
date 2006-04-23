
package moduls.auralion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class Auralion {
	
	private static Log l=new Log();

	public void loadAuralion(){
		Properties 	AF=new Properties();
		SystenLib	sl=new SystenLib();
		
		try {
			AF.load(new FileInputStream("cfg/moduls/auralion.sys"));
			
			sl.setAuralion(0, AF.getProperty("name"));
			
			sl.setAuralion(1, AF.getProperty("version"));
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-53"), -53);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-54"), -54);
		}
	}
}
