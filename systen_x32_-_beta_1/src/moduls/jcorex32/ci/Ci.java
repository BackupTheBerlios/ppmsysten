
package moduls.jcorex32.ci;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class Ci {
	
	private Log	l=new Log();

	public void loadCI(){
		Properties 	CI=new Properties();
		SystenLib	sl=new SystenLib();
		
		try {
			CI.load(new FileInputStream("cfg/moduls/ci.sys"));
			
			sl.setCi(0, CI.getProperty("name"));
			
			sl.setCi(1, CI.getProperty("version"));
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-35"), -36);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-36"), -36);
		}
	}
	
	public String getGlobalMod(int mod){
		Properties GM=new Properties();		
		
		try {
			GM.load(new FileInputStream("cfg/moduls/globaltxt.sys"));
			
			return GM.getProperty(Integer.toString(mod));
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-35"), -36);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-36"), -36);
		}
		
		return "/<";
	}
	
	public String getTxt(String mod){
		Properties TX=new Properties();		
		
		try {
			TX.load(new FileInputStream("cfg/moduls/txt"+new SystenLib().getLng()+".sys"));
			
			return TX.getProperty(mod);
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-43"), -43);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-44"), -44);
		}
		
		return "";
	}
}
