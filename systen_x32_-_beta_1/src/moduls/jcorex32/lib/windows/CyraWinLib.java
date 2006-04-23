
package moduls.jcorex32.lib.windows;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import runtimes.shutdown.Shutdown;

import moduls.jcorex32.ecoderx32.ECoder;
import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class CyraWinLib {

	private static String machineName="";
	
	private static String ip="";
	
	private static String[] comAdapters=null;
	
	private static String[] nwDevices=null;
	
	public String getIp(){
		return ip;
	}
	
	public String getMachineName(){
		return machineName;
	}
	
	public int getNwDevicesNumber(){
		return Integer.parseInt(nwDevices[0]);
	}
	
	public String getNwDevices(int x){
		return nwDevices[x+1];
	}
	
	public int getComAdaptersNumber(){
		return Integer.parseInt(comAdapters[0]);
	}
	
	public String getComAdapters(int x){
		return comAdapters[x+1];
	}
	
	public void setIp(String x){
		if(new SystenLib().getBootLvl()==-1){		
			ip=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			ip=x;
			
			performeEFSx(0, "ip.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			ip=performeEFSx(1, "ip.efsx", x);
		}
	}
	
	public void setMachineName(String x){
		if(new SystenLib().getBootLvl()==-1){		
			machineName=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			machineName=x;
			
			performeEFSx(0, "machin.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			machineName=performeEFSx(1, "machin.efsx", x);
		}
	}
	
	public void initComAdaptersNumber(int x){
		if(new SystenLib().getBootLvl()==-1){		
			comAdapters=new String[x+1];
			
			comAdapters[0]=Integer.toString(x);
		}
		else if(new SystenLib().getBootLvl()==0){
			comAdapters=new String[x+1];
			
			comAdapters[0]=Integer.toString(x);
			
			performeEFSx(0, "comadan.efsx", Integer.toString(x));
		}
		else if(new SystenLib().getBootLvl()==1){
			x=Integer.parseInt(performeEFSx(1, "comadan.efsx", ""));
			
			comAdapters=new String[x+1];
			
			comAdapters[0]=Integer.toString(x);
		}
	}
	
	public void setComAdapters(int x, String y){
		if(new SystenLib().getBootLvl()==-1){		
			comAdapters[x+1]=y;
		}
		else if(new SystenLib().getBootLvl()==0){
			comAdapters[x+1]=y;
			
			performeEFSxArrayOut(x+1, "comada.efsx", comAdapters);
		}
		else if(new SystenLib().getBootLvl()==1){
			comAdapters=performeEFSxArrayIn(getComAdaptersNumber(), "comada.efsx");
		}
	}
	
	public void initNwDevicesNumber(int x){
		if(new SystenLib().getBootLvl()==-1){		
			nwDevices=new String[x+1];
			
			nwDevices[0]=Integer.toString(x);
		}
		else if(new SystenLib().getBootLvl()==0){
			nwDevices=new String[x+1];
			
			nwDevices[0]=Integer.toString(x);
			
			performeEFSx(0, "nwdevin.efsx", Integer.toString(x));
		}
		else if(new SystenLib().getBootLvl()==1){
			x=Integer.parseInt(performeEFSx(1, "nwdevin.efsx", ""));

			nwDevices=new String[x+1];
			
			nwDevices[0]=Integer.toString(x);
		}
	}
	
	public void setNwDevices(int x, String y){
		if(new SystenLib().getBootLvl()==-1){		
			nwDevices[x+1]=y;
		}
		else if(new SystenLib().getBootLvl()==0){
			nwDevices[x+1]=y;
			
			performeEFSxArrayOut(x+1, "nwdevi.efsx", nwDevices);
		}
		else if(new SystenLib().getBootLvl()==1){
			nwDevices=performeEFSxArrayIn(getNwDevicesNumber(), "nwdevi.efsx");
		}
	}
	
	public String performeEFSx(int modus, String file, String parameter){
		Log l=new Log();
		
		try {
			if(modus==0){
				ObjectOutputStream objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("efsx/"+new SystenLib().getCurSession(0)+file)));

				objOut.writeObject(new ECoder().performe(parameter, 0, "stdsx32"));
				
				objOut.close();
			}
			else if(modus==1){
				ObjectInputStream objIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("efsx/"+new SystenLib().getCurSession(0)+file)));
				
				parameter=new ECoder().performe(objIn.readObject().toString(), 1, "stdsx32");

				objIn.close();
			}
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-50"));
			
			new Shutdown().setRestart();
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-50"));
			
			new Shutdown().setRestart();
		}
		catch(ClassNotFoundException cnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-50"));
			
			new Shutdown().setRestart();
		}
		
		return parameter;
	}
	
	public String[] performeEFSxArrayIn(int number, String file){
		Log 		l=new Log();
		String[] 	parameter=new String[number+1];
		
		try {
			ObjectInputStream objIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("efsx/"+new SystenLib().getCurSession(0)+file)));
			
			parameter[0]=Integer.toString(number);
			
			for(int i=1; i<=number; i++){
				parameter[i]=new ECoder().performe(objIn.readObject().toString(), 1, "stdsx32");
			}
				
			objIn.close();
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-50"));
			
			new Shutdown().setRestart();
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-50"));
			
			new Shutdown().setRestart();
		}
		catch(ClassNotFoundException cnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-50"));
			
			new Shutdown().setRestart();
		}
		
		return parameter;
	}
	
	public void performeEFSxArrayOut(int number, String file, String[] parameter){
		Log l=new Log();
		
		try {
			ObjectOutputStream objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("efsx/"+new SystenLib().getCurSession(0)+file)));
			
			for(int i=1; i<=number; i++){
				objOut.writeObject(new ECoder().performe(parameter[i], 0, "stdsx32"));
			}
				
			objOut.close();
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-50"));
			
			new Shutdown().setRestart();
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-50"));
			
			new Shutdown().setRestart();
		}
	}
}
