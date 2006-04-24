
package runtimes.shutdown;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import moduls.jcorex32.ecoderx32.ECoder;
import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class Shutdown {

	public void setShutdown(int code){
		SystenLib 	sl=new SystenLib();
		ECoder		e=new ECoder();
		
		try {
			String tmp=sl.getCurSession(0);
			
			if(sl.getCurSessionClose()){
				int id=Integer.parseInt(tmp.substring(0, tmp.length()-4))-1;
			
				sl.changeSession(id, 2, "c");
			}
			
			int sessions=sl.getSessionNumber();
			
			ObjectOutputStream objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(sl.getEFSxSource())));
			
			objOut.writeObject(e.performe(Integer.toString(sessions), 0, "stdsx32"));
			
			for(int i=0; i<sessions; i++){
				objOut.writeObject(e.performe(sl.getSession(i, 0), 0, "stdsx32"));				
				objOut.writeObject(e.performe(sl.getSession(i, 1), 0, "stdsx32"));					
				objOut.writeObject(e.performe(sl.getSession(i, 2), 0, "stdsx32"));
			}	
			
			objOut.close();
		}
		catch(IndexOutOfBoundsException ioobe){			
		}		
		catch(NumberFormatException nfe){
		}
		catch(NullPointerException npe){
		}
		catch(IOException ioe){
		}
		
		try {
			if(sl.getOsType()==0){
				Runtime.getRuntime().exec("cmd.exe /c del efsx\\"+sl.getCurSession(0)+"*.*");
			}
			else {
				// LINUX delete
			}
		}
		catch(IOException ioe){
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("0"), 0);
		}
		
		System.exit(code);
	}
	
	public void setRestart(){
		SystenLib 	sl=new SystenLib();
		ECoder		e=new ECoder();
		
		try {
			String tmp=sl.getCurSession(0);
			
			if(sl.getCurSessionClose()){
				int id=Integer.parseInt(tmp.substring(0, tmp.length()-4))-1;
			
				sl.changeSession(id, 2, "c");
			}
			
			int sessions=sl.getSessionNumber();
			
			ObjectOutputStream objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(sl.getEFSxSource())));
			
			objOut.writeObject(e.performe(Integer.toString(sessions), 0, "stdsx32"));
			
			for(int i=0; i<sessions; i++){
				objOut.writeObject(e.performe(sl.getSession(i, 0), 0, "stdsx32"));				
				objOut.writeObject(e.performe(sl.getSession(i, 1), 0, "stdsx32"));					
				objOut.writeObject(e.performe(sl.getSession(i, 2), 0, "stdsx32"));
			}
			
			objOut.close();
		}
		catch(IndexOutOfBoundsException ioobe){			
		}		
		catch(NumberFormatException nfe){
		}
		catch(NullPointerException npe){
		}
		catch(IOException ioe){
		}
		
		try {
			if(sl.getOsType()==0){
				Runtime.getRuntime().exec("cmd.exe /c del efsx\\"+sl.getCurSession(0)+"*.*");
			}
			else {
				// LINUX delete
			}
			
			Runtime.getRuntime().exec(sl.getLaParam3()+" "+sl.getLaParam1()+" "+sl.getLaParam2()+" "+sl.getLaParam3());
		}
		catch(IOException ioe){
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("0"), 0);
		}
		
		System.exit(-0);
	}
}
