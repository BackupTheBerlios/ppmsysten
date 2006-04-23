
package runtimes.loader06.windows;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import moduls.jcorex32.lib.SystenLib;
import moduls.jcorex32.lib.windows.MainWinLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class CmdWinProp extends Thread {
	
	public void run(){
		Log			l=new Log();
		SystenLib	sl=new SystenLib();
		MainWinLib 	mwl=new MainWinLib();
		
		try {			
/*			Process process=Runtime.getRuntime().exec("cmd.exe /c echo %SYSTEMDRIVE%> "+sl.getTmpPath()+"environment.swp");			
						
			process.waitFor();	
			
			process=Runtime.getRuntime().exec("cmd.exe /c echo %HOMEDRIVE%>> "+sl.getTmpPath()+"environment.swp");			
						
			process.waitFor();			
			
			process=Runtime.getRuntime().exec("cmd.exe /c echo %SYSTEMROOT%>> "+sl.getTmpPath()+"environment.swp");			
						
			process.waitFor();
			
			process=Runtime.getRuntime().exec("cmd.exe /c echo %TEMP%>> "+sl.getTmpPath()+"environment.swp");			
						
			process.waitFor();
			
			process=Runtime.getRuntime().exec("cmd.exe /c echo %HOMEPATH%>> "+sl.getTmpPath()+"environment.swp");			
						
			process.waitFor();
			
			process=Runtime.getRuntime().exec("cmd.exe /c echo %OS%>> "+sl.getTmpPath()+"environment.swp");			
						
			process.waitFor();
			
			process=Runtime.getRuntime().exec("cmd.exe /c echo %NUMBER_OF_PROCESSORS% >> "+sl.getTmpPath()+"environment.swp");			
						
			process.waitFor();
			
			process=Runtime.getRuntime().exec("cmd.exe /c echo %PROCESSOR_ARCHITECTURE%>> "+sl.getTmpPath()+"environment.swp");			
						
			process.waitFor();
			
			process=Runtime.getRuntime().exec("cmd.exe /c echo %PROCESSOR_IDENTIFIER%>> "+sl.getTmpPath()+"environment.swp");			
						
			process.waitFor();*/
			
			RandomAccessFile file=new RandomAccessFile(sl.getTmpPath().replace("\\", "/")+"environment.swp", "r");

			mwl.setSystemDrive(file.readLine());
			mwl.setHomeDrive(file.readLine());
			mwl.setSystemRoot(file.readLine());
			mwl.setTemp(file.readLine());
			mwl.setHomePath(mwl.getHomeDrive()+file.readLine());
			mwl.setOs(0, file.readLine());

			String str=file.readLine();
			
			int tmp=0;

			if(str.charAt(str.length()-1)==' '){
				tmp=Integer.parseInt(str.substring(0, str.length()-1));
			}
			else {
				tmp=Integer.parseInt(str.substring(0, str.length()));
			}
			
			mwl.setNumberOfProcessors(Integer.toString(tmp));
			mwl.setProcessorArchitecture(file.readLine());			
			mwl.setProcessorIdentifier(file.readLine());

			file.close();
			
//			new File(sl.getTmpPath().replace("\\", "/")+"environment.swp").delete();
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-7"), -7);
		}
/*		catch(InterruptedException ie){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-8"), -8);			
		}*/
		catch(NumberFormatException nfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-28"), -28);			
		}
		catch(NullPointerException npe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-28"), -28);			
		}
	}
}
