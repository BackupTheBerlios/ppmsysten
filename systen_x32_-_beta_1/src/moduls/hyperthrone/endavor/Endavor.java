
package moduls.hyperthrone.endavor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class Endavor {

	private static Log l=new Log();

	public void loadEndavor(){
		Properties 	EF=new Properties();
		SystenLib	sl=new SystenLib();
		
		try {
			EF.load(new FileInputStream("cfg/moduls/endavor.sys"));
			
			sl.setEndavor(0, EF.getProperty("name"));
			
			sl.setEndavor(1, EF.getProperty("version"));
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-59"), -59);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-60"), -60);
		}
	}
}
