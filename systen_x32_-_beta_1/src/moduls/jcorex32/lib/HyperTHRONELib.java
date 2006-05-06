
package moduls.jcorex32.lib;

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
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class HyperTHRONELib {

	private static String[][] resolution=null;
	
	private static String cardName="";
	
	private static String manufacturer="";
	
	private static String chipType="";
	
	private static String dacType="";
	
	private static String displayMemory="";
	
	private static String[] currentMode=new String[4];
	
	private static String refreshRate="";
	
	private static String driverName="";
	
	private static String[] driverVersion=new String[4];
	
	public String getResolution(int x, int y){
		return resolution[x+1][y];
	}
	
	public int getResolutionNumber(){
		return Integer.parseInt(resolution[0][0]);
	}
	
	public String getCardName(){
		return cardName;
	}

	public String getChipType(){
		return chipType;
	}

	public int getCurrentMode(int x){
		return Integer.parseInt(currentMode[x]);
	}

	public String getDacType(){
		return dacType;
	}

	public String getDisplayMemory(){
		return displayMemory;
	}

	public String getDriverName(){
		return driverName;
	}

	public String getDriverVersion(int x){
		return driverVersion[x];
	}

	public String getManufacturer(){
		return manufacturer;
	}
	
	public int getRefreshRate(){
		return Integer.parseInt(refreshRate);
	}

	public void setCardName(String x){
		if(new SystenLib().getBootLvl()==-1){		
			cardName=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			cardName=x;
			
			performeEFSx(0, "gpucar.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			cardName=performeEFSx(1, "gpucar.efsx", x);
		}
	}

	public void setChipType(String x){
		if(new SystenLib().getBootLvl()==-1){		
			chipType=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			chipType=x;
			
			performeEFSx(0, "gpuchi.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			chipType=performeEFSx(1, "gpuchi.efsx", x);
		}
	}

	public void setCurrentMode(String x, String y, String z){
		if(new SystenLib().getBootLvl()==-1){		
			currentMode[0]=x;
			currentMode[1]=y;
			currentMode[2]=z;
		}
		else if(new SystenLib().getBootLvl()==0){
			currentMode[0]=x;
			currentMode[1]=y;
			currentMode[2]=z;
			
			performeEFSxArrayOut(3, "gpucur.efsx", currentMode);
		}
		else if(new SystenLib().getBootLvl()==1){
			currentMode=performeEFSxArrayIn(3, "gpucur.efsx");
		}
	}

	public void setDacType(String x){
		if(new SystenLib().getBootLvl()==-1){		
			dacType=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			dacType=x;
			
			performeEFSx(0, "gpudac.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			dacType=performeEFSx(1, "gpudac.efsx", x);
		}
	}

	public void setDisplayMemory(String x){
		if(new SystenLib().getBootLvl()==-1){		
			displayMemory=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			displayMemory=x;
			
			performeEFSx(0, "gpudis.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			displayMemory=performeEFSx(1, "gpudis.efsx", x);
		}
	}

	public void setDriverName(String x){
		if(new SystenLib().getBootLvl()==-1){		
			driverName=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			driverName=x;
			
			performeEFSx(0, "gpudri.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			driverName=performeEFSx(1, "gpudri.efsx", x);
		}
	}

	public void setDriverVersion(int x, String y){
		if(new SystenLib().getBootLvl()==-1){		
			driverVersion[x]=y;
		}
		else if(new SystenLib().getBootLvl()==0){
			driverVersion[x]=y;

			performeEFSxArrayOut(x+1, "gpudriv.efsx", driverVersion);
		}
		else if(new SystenLib().getBootLvl()==1){
			driverVersion=performeEFSxArrayIn(4, "gpudriv.efsx");
		}
	}

	public void setManufacturer(String x){
		if(new SystenLib().getBootLvl()==-1){		
			manufacturer=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			manufacturer=x;
			
			performeEFSx(0, "gpuman.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			manufacturer=performeEFSx(1, "gpuman.efsx", x);
		}
	}

	public void setRefreshRate(String x){
		if(new SystenLib().getBootLvl()==-1){		
			refreshRate=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			refreshRate=x;
			
			performeEFSx(0, "gpuref.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			refreshRate=performeEFSx(1, "gpuref.efsx", x);
		}
	}
	
	public void initResolution(int x){
		if(new SystenLib().getBootLvl()==-1){		
			resolution=new String[x+1][3];
			
			resolution[0][0]=Integer.toString(x);
		}
		else if(new SystenLib().getBootLvl()==0){
			resolution=new String[x+1][3];
			
			resolution[0][0]=Integer.toString(x);
			
			performeEFSx(0, "gpuresn.efsx", Integer.toString(x));
		}
		else if(new SystenLib().getBootLvl()==1){
			x=Integer.parseInt(performeEFSx(1, "gpuresn.efsx", ""));
			
			resolution=new String[x+1][3];
			
			resolution[0][0]=Integer.toString(x);
		}
	}
	
	public void setResolution(int i, String x, String y, String z){
		if(new SystenLib().getBootLvl()==-1){		
			resolution[i][0]=x;
			resolution[i][1]=y;
			resolution[i][2]=z;
		}
		else if(new SystenLib().getBootLvl()==0){
			resolution[i][0]=x;
			resolution[i][1]=y;
			resolution[i][2]=z;
			
			performeEFSxArrayOutSp(i, "gpures.efsx", resolution);
		}
		else if(new SystenLib().getBootLvl()==1){
			resolution=performeEFSxArrayInSp(getResolutionNumber(), "gpures.efsx");
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
		String[] 	parameter=new String[number];
		
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
	
	public String[][] performeEFSxArrayInSp(int number, String file){
		Log 		l=new Log();
		String[][] 	parameter=new String[number+1][3];
		
		try {
			ObjectInputStream objIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("efsx/"+new SystenLib().getCurSession(0)+file)));
			
			parameter[0][0]=Integer.toString(number);
			
			for(int i=1; i<=number; i++){
				for(int j=0; j<3; j++){
					parameter[i][j]=new ECoder().performe(objIn.readObject().toString(), 1, "stdsx32");
				}
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
	
	public void performeEFSxArrayOutSp(int number, String file, String[][] parameter){
		Log l=new Log();
		
		try {
			ObjectOutputStream objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("efsx/"+new SystenLib().getCurSession(0)+file)));
			
			for(int i=1; i<=number; i++){
				for(int j=0; j<3; j++){
					objOut.writeObject(new ECoder().performe(parameter[i][j], 0, "stdsx32"));
				}
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
