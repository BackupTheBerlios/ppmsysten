
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

public class AuralionLib {
	
	private static String cardNumber="";

	private static String[] description=null;
	
	private static String[] driverName=null;
	
	private static String[][] driverVersion=null;
	
	private static String[] driverProvider=null;
	
	public String getDescription(int x){
		return description[x];
	}

	public String getDriverName(int x){
		return driverName[x];
	}

	public String getDriverProvider(int x){
		return driverProvider[x];
	}

	public String getDriverVersion(int x, int y){
		return driverVersion[x][y];
	}
	
	public int getCardNumber(){
		return Integer.parseInt(cardNumber);
	}
	
	public void setDescription(int x, String y){
		if(new SystenLib().getBootLvl()==-1){		
			description[x]=y;
		}
		else if(new SystenLib().getBootLvl()==0){
			description[x]=y;
			
			performeEFSxArrayOut(x, "snddes.efsx", description);
		}
		else if(new SystenLib().getBootLvl()==1){
			description=performeEFSxArrayIn("snddes.efsx");
		}
	}

	public void setDriverName(int x, String y){
		if(new SystenLib().getBootLvl()==-1){		
			driverName[x]=y;
		}
		else if(new SystenLib().getBootLvl()==0){
			driverName[x]=y;
			
			performeEFSxArrayOut(x, "snddri.efsx", driverName);
		}
		else if(new SystenLib().getBootLvl()==1){
			driverName=performeEFSxArrayIn("snddri.efsx");
		}
	}

	public void setDriverProvider(int x, String y){
		if(new SystenLib().getBootLvl()==-1){		
			driverProvider[x]=y;
		}
		else if(new SystenLib().getBootLvl()==0){
			driverProvider[x]=y;
			
			performeEFSxArrayOut(x, "snddrip.efsx", driverProvider);
		}
		else if(new SystenLib().getBootLvl()==1){
			driverProvider=performeEFSxArrayIn("snddrip.efsx");
		}
	}

	public void setDriverVersion(int x, int y, String z){
		if(new SystenLib().getBootLvl()==-1){		
			driverVersion[x][y]=z;
		}
		else if(new SystenLib().getBootLvl()==0){
			driverVersion[x][y]=z;
			
			performeEFSxArrayFieldOut(x, y, "snddriv.efsx", driverVersion);
		}
		else if(new SystenLib().getBootLvl()==1){
			driverVersion=performeEFSxArrayFieldIn("snddriv.efsx");
		}
	}
	
	public void setCardNumber(int x){
		if(new SystenLib().getBootLvl()==-1){		
			cardNumber=Integer.toString(x);
			
			description=new String[x];		
			driverName=new String[x];		
			driverVersion=new String[x][4];		
			driverProvider=new String[x];
		}
		else if(new SystenLib().getBootLvl()==0){
			cardNumber=Integer.toString(x);
			
			description=new String[x];		
			driverName=new String[x];		
			driverVersion=new String[x][4];		
			driverProvider=new String[x];
			
			performeEFSx(0, "sndcnm.efsx", Integer.toString(x));
		}
		else if(new SystenLib().getBootLvl()==1){
			x=Integer.parseInt(performeEFSx(1, "sndcnm.efsx", ""));
			
			cardNumber=Integer.toString(x);
			
			description=new String[x];		
			driverName=new String[x];		
			driverVersion=new String[x][4];		
			driverProvider=new String[x];
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
	
	public String[] performeEFSxArrayIn(String file){
		Log 		l=new Log();
		String[] 	parameter=new String[getCardNumber()];
		
		try {
			ObjectInputStream objIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("efsx/"+new SystenLib().getCurSession(0)+file)));

			for(int i=0; i<getCardNumber(); i++){
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
	
	public void performeEFSxArrayOut(int length, String file, String[] parameter){
		Log l=new Log();
		
		try {
			ObjectOutputStream objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("efsx/"+new SystenLib().getCurSession(0)+file)));

			for(int i=0; i<=length; i++){
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
	
	public void performeEFSxArrayFieldOut(int length, int sub, String file, String[][] parameter){
		Log l=new Log();
		
		try {
			ObjectOutputStream objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("efsx/"+new SystenLib().getCurSession(0)+file)));

			for(int i=0; i<=length; i++){
				for(int j=0; j<=sub; j++){
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
	
	public String[][] performeEFSxArrayFieldIn(String file){
		Log 		l=new Log();
		String[][] 	parameter=new String[getCardNumber()][4];
		
		try {
			ObjectInputStream objIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("efsx/"+new SystenLib().getCurSession(0)+file)));

			for(int i=0; i<getCardNumber(); i++){
				for(int j=0; j<4; j++){
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
}
