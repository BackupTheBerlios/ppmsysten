package runtimes.loader06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import moduls.jcorex32.lib.JavaLib;
import moduls.jcorex32.lib.SystenLib;
import moduls.log.Log;

public class OsSupport {

	public String loadOsSupport(){
		SystenLib	sl=new SystenLib();
		Properties 	OS=new Properties();
		Log 		l=new Log();
			
		try {
			OS.load(new FileInputStream("cfg/os.sys"));
			
			String osname=new JavaLib().getOsName().toLowerCase().replaceAll(" ", "_");
			
			String str=OS.getProperty("supported");
			
			if(str.contains(osname)){
				if(osname.contains("windows")){
					sl.setOsType(0);
				}
				else if(osname.contains("linux")){
					sl.setOsType(1);
				}
				else {
					sl.setOsType(-1);
				}
					
				return osname;
			}
			
			return "unknown";
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), "could not find ERRORCODES.SYS", -1);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), "could not read ERRORCODES.SYS", -2);
		}
		
		return "unknown";
	}
}
