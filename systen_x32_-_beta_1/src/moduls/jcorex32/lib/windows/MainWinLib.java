
package moduls.jcorex32.lib.windows;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import moduls.jcorex32.ecoderx32.ECoder;
import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;
import runtimes.shutdown.Shutdown;

public class MainWinLib {
	
	private static String systemDrive="";
	
	private static String homeDrive="";
	
	private static String systemRoot="";
	
	private static String temp="";
	
	private static String homePath="";
	
	private static String numberOfProcessors="";
	
	private static String processorArchitecture="";
	
	private static String processorIdentifier="";

	private static String time="";
	
	private static String[] os=new String[7];
	
	private static String[] language=new String[2];
	
	private static String systemManufacturer="";
	
	private static String systemModel="";
	
	private static String bios="";
	
	private static String[] processor=new String[3];
	
	private static String memory="";
	
	private static String[] pageFile=new String[2];	
	
	private static String directXVersion="";
	
	private static String[] hdd=null;

	public String getTime(){
		return time;
	}
	
	public String getHdd(int x){
		return hdd[x+1];
	}
	
	public int getHddNumber(){
		return Integer.parseInt(hdd[0]);
	}
	
	public String getBios(){
		return bios;
	}

	public String getDirectXVersion(){
		return directXVersion;
	}

	public String getLanguage(int x){
		return language[x];
	}

	public String getMemory(){
		return memory;
	}

	public String getOs(int x){
		return os[x];
	}

	public String getPageFile(int x){
		return pageFile[x];
	}

	public String getProcessor(int x){
		return processor[x];
	}

	public String getSystemManufacturer(){
		return systemManufacturer;
	}

	public String getSystemModel(){
		return systemModel;
	}
	
	public String getHomeDrive(){
		return homeDrive;
	}

	public String getHomePath(){
		return homePath;
	}

	public String getNumberOfProcessors(){
		return numberOfProcessors;
	}

	public String getProcessorArchitecture(){
		return processorArchitecture;
	}

	public String getProcessorIdentifier(){
		return processorIdentifier;
	}

	public String getSystemDrive(){
		return systemDrive;
	}

	public String getSystemRoot(){
		return systemRoot;
	}

	public String getTemp(){
		return temp;
	}

	public void setHomeDrive(String x){
		if(new SystenLib().getBootLvl()==-1){		
			homeDrive=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			homeDrive=x;
			
			performeEFSx(0, "homedr.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			homeDrive=performeEFSx(1, "homedr.efsx", "");
		}
	}

	public void setHomePath(String x){
		if(new SystenLib().getBootLvl()==-1){		
			homePath=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			homePath=x;
			
			performeEFSx(0, "homepa.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			homePath=performeEFSx(1, "homepa.efsx", "");
		}
	}

	public void setNumberOfProcessors(String x){
		if(new SystenLib().getBootLvl()==-1){		
			numberOfProcessors=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			numberOfProcessors=x;
			
			performeEFSx(0, "numpro.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			numberOfProcessors=performeEFSx(1, "numpro.efsx", "");
		}
	}

	public void setProcessorArchitecture(String x){
		if(new SystenLib().getBootLvl()==-1){		
			processorArchitecture=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			processorArchitecture=x;
			
			performeEFSx(0, "proarc.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			processorArchitecture=performeEFSx(1, "proarc.efsx", "");
		}
	}

	public void setProcessorIdentifier(String x){
		if(new SystenLib().getBootLvl()==-1){		
			processorIdentifier=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			processorIdentifier=x;
			
			performeEFSx(0, "proide.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			processorIdentifier=performeEFSx(1, "proide.efsx", "");
		}
	}

	public void setSystemDrive(String x){
		if(new SystenLib().getBootLvl()==-1){		
			systemDrive=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			systemDrive=x;
			
			performeEFSx(0, "sysdri.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			systemDrive=performeEFSx(1, "sysdri.efsx", "");
		}
	}

	public void setSystemRoot(String x){
		if(new SystenLib().getBootLvl()==-1){		
			systemRoot=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			systemRoot=x;
			
			performeEFSx(0, "sysroo.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			systemRoot=performeEFSx(1, "sysroo.efsx", "");
		}
	}

	public void setTemp(String x){
		if(new SystenLib().getBootLvl()==-1){		
			temp=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			temp=x;
			
			performeEFSx(0, "temp.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			temp=performeEFSx(1, "temp.efsx", "");
		}
	}

	public void setBios(String x){
		if(new SystenLib().getBootLvl()==-1){		
			bios=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			bios=x;
			
			performeEFSx(0, "bios.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			bios=performeEFSx(1, "bios.efsx", "");
		}
	}

	public void setDirectXVersion(String x){
		if(new SystenLib().getBootLvl()==-1){		
			directXVersion=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			directXVersion=x;
			
			performeEFSx(0, "dxvers.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			directXVersion=performeEFSx(1, "dxvers.efsx", "");
		}
	}

	public void setLanguage(int x, String y){
		if(new SystenLib().getBootLvl()==-1){		
			language[x]=y;
		}
		else if(new SystenLib().getBootLvl()==0){
			language[x]=y;
			
			performeEFSxArrayOut(x+1, "langua.efsx", language);
		}
		else if(new SystenLib().getBootLvl()==1){
			language=performeEFSxArrayIn(2, "langua.efsx");
		}
	}

	public void setMemory(String x){
		if(new SystenLib().getBootLvl()==-1){		
			memory=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			memory=x;
			
			performeEFSx(0, "memory.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			memory=performeEFSx(1, "memory.efsx", "");
		}
	}

	public void setOs(int x, String y){
		if(new SystenLib().getBootLvl()==-1){		
			os[x]=y;
		}
		else if(new SystenLib().getBootLvl()==0){
			os[x]=y;
			
			performeEFSxArrayOut(x+1, "os.efsx", os);
		}
		else if(new SystenLib().getBootLvl()==1){
			os=performeEFSxArrayIn(7, "os.efsx");
		}
	}

	public void setPageFile(int x, String y){
		if(new SystenLib().getBootLvl()==-1){		
			pageFile[x]=y;
		}
		else if(new SystenLib().getBootLvl()==0){
			pageFile[x]=y;
			
			performeEFSxArrayOut(x+1, "pagefi.efsx", pageFile);
		}
		else if(new SystenLib().getBootLvl()==1){
			pageFile=performeEFSxArrayIn(2, "pagefi.efsx");
		}
	}

	public void setProcessor(int x, String y){
		if(new SystenLib().getBootLvl()==-1){		
			processor[x]=y;
		}
		else if(new SystenLib().getBootLvl()==0){
			processor[x]=y;

			performeEFSxArrayOut(x+1, "proces.efsx", processor);
		}
		else if(new SystenLib().getBootLvl()==1){
			processor=performeEFSxArrayIn(3, "proces.efsx");
		}
	}

	public void setSystemManufacturer(String x){
		if(new SystenLib().getBootLvl()==-1){		
			systemManufacturer=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			systemManufacturer=x;
			
			performeEFSx(0, "sysman.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			systemManufacturer=performeEFSx(1, "sysman.efsx", "");
		}
	}

	public void setSystemModel(String x){
		if(new SystenLib().getBootLvl()==-1){		
			systemModel=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			systemModel=x;
			
			performeEFSx(0, "sysmod.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			systemModel=performeEFSx(1, "sysmod.efsx", "");
		}
	}
	
	public void setTime(String x){
		if(new SystenLib().getBootLvl()==-1){		
			time=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			time=x;
			
			performeEFSx(0, "time.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			time=performeEFSx(1, "time.efsx", "");
		}
	}
	
	public void initHdd(int x){
		if(new SystenLib().getBootLvl()==-1){		
			hdd=new String[x+1];
			
			hdd[0]=Integer.toString(x);
		}
		else if(new SystenLib().getBootLvl()==0){
			hdd=new String[x+1];
			
			hdd[0]=Integer.toString(x);
			
			performeEFSx(0, "hddn.efsx", Integer.toString(x));
		}
		else if(new SystenLib().getBootLvl()==1){
			x=Integer.parseInt(performeEFSx(1, "hddn.efsx", ""));
			
			hdd=new String[x+1];
			
			hdd[0]=Integer.toString(x);
		}
	}
	
	public void setHdd(int x, String y){
		if(new SystenLib().getBootLvl()==-1){		
			hdd[x+1]=y;
		}
		else if(new SystenLib().getBootLvl()==0){
			hdd[x+1]=y;
			
			performeEFSxArrayOutSp(x+1, "hdd.efsx", hdd);
		}
		else if(new SystenLib().getBootLvl()==1){
			hdd=performeEFSxArrayInSp(getHddNumber(), "hdd.efsx");
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
			
			for(int i=0; i<number; i++){
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
	
	public String[] performeEFSxArrayInSp(int number, String file){
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
			
			for(int i=0; i<number; i++){
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
	
	public void performeEFSxArrayOutSp(int number, String file, String[] parameter){
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
