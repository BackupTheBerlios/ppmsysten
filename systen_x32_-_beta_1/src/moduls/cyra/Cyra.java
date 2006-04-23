
package moduls.cyra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class Cyra {

	private static Log l=new Log();

	public void loadCyra(){
		Properties 	CF=new Properties();
		SystenLib	sl=new SystenLib();
		
		try {
			CF.load(new FileInputStream("cfg/moduls/cyra.sys"));
			
			sl.setCyra(0, CF.getProperty("name"));
			
			sl.setCyra(1, CF.getProperty("version"));
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-55"), -55);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-56"), -56);
		}
	}
}
