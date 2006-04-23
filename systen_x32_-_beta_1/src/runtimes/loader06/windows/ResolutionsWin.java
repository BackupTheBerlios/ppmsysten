
package runtimes.loader06.windows;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;

import moduls.jcorex32.lib.SystenLib;
import moduls.jcorex32.lib.windows.HyperTHRONEWinLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class ResolutionsWin extends Thread {
	
	public void run(){
		Properties 			RN=new Properties();
		HyperTHRONEWinLib 	htwl=new HyperTHRONEWinLib();
		SystenLib			sl=new SystenLib();
		Log					l=new Log();

/*		try {
			Process process=Runtime.getRuntime().exec("cfg/windows/resolution.syse "+sl.getTmpPath());

			process.waitFor();
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-9"), -9);
		}
		catch(InterruptedException ie){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-10"), -10);
		}*/
		
		try {
			FileInputStream file=new FileInputStream(sl.getTmpPath().replace("\\", "/")+"resolution.swp");
			
			RN.load(file);
			
			int i=Integer.parseInt(RN.getProperty("number"));
			
			htwl.initResolution(i);
			
			for(int j=1; j<=i; j++){
				try {
					StringTokenizer st=new StringTokenizer(RN.getProperty(Integer.toString(j)), "x");
								
					String width=st.nextElement().toString();
								
					String height=st.nextElement().toString();
								
					String depth=st.nextElement().toString();
								
					if(width.length()<height.length()){
						l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-20"), -20);
					}
					
					htwl.setResolution(j, width, height, depth);
				}
				catch(NoSuchElementException nsee){
					l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-19"), -19);
				}				
			}
			
			file.close();
			
//			new File(sl.getTmpPath().replace("\\", "/")+"resolution.swp").delete();
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-11"), -11);
		}		
	}
}
