
package runtimes.loader06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import moduls.jcorex32.lib.JavaLib;
import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class SystenProp {

	public void loadSystenProperties(){
		Properties 	BF=new Properties();
		Log 		l=new Log();
		SystenLib 	sl=new SystenLib();	
		
		try {
			BF.load(new FileInputStream("cfg/boot.sys"));
			
			sl.setVersion(BF.getProperty("version"));
			
			StringTokenizer st=new StringTokenizer(new JavaLib().getJVersion().replaceAll("_", "."), ".");
			
			String tmp="";
			
			while(st.hasMoreElements()){
				tmp+=st.nextElement().toString();
			}
			
			if(Integer.parseInt(tmp)<Integer.parseInt(BF.getProperty("javaversion"))){
				l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-31"), -31);
			}
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-5"), -5);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-6"), -6);
		}
		catch(NumberFormatException nfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-32"), -32);
		}
	}
}
