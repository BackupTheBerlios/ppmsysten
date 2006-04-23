
package moduls.hyperthrone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class HyperTHRONE {
	
	private static Log l=new Log();

	public void loadHyperTHRONE(){
		Properties 	CF=new Properties();
		SystenLib	sl=new SystenLib();
		
		try {
			CF.load(new FileInputStream("cfg/moduls/hyperthrone.sys"));
			
			sl.setHyperTHRONE(0, CF.getProperty("name"));
			
			sl.setHyperTHRONE(1, CF.getProperty("version"));
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-57"), -57);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-58"), -58);
		}
	}
}
