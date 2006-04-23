
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

public class JavaLib {
	
	private static String path="";
	
	private static String jVersion="";
	
	private static String jVendor="";
	
	private static String jPath="";
	
	private static String jVmSpVersion="";
	
	private static String jVmSpVendor="";
	
	private static String jVmSpName="";
	
	private static String jVmVersion="";
	
	private static String jVmVendor="";
	
	private static String jVmName="";
	
	private static String jSpVersion="";
	
	private static String jSpVendor="";
	
	private static String jSpName="";
	
	private static String jClVersion="";
	
	private static String jClPath="";
	
	private static String jLiPath="";
	
	private static String jIoTempDir="";
	
	private static String jExDirs="";
	
	private static String osName="";
	
	private static String osArch="";
	
	private static String osVersion="";
	
	private static String fiSeperator="";
	
	private static String usName="";
	
	private static String usHome="";
	
	public String getFiSeperator(){
		return fiSeperator;
	}

	public String getJClPath(){
		return jClPath;
	}

	public String getJClVersion(){
		return jClVersion;
	}

	public String getJExDirs(){
		return jExDirs;
	}

	public String getJIoTempDir(){
		return jIoTempDir;
	}

	public String getJLiPath(){
		return jLiPath;
	}

	public String getJPath(){
		return jPath;
	}

	public String getJSpName(){
		return jSpName;
	}

	public String getJSpVendor(){
		return jSpVendor;
	}

	public String getJSpVersion(){
		return jSpVersion;
	}

	public String getJVendor(){
		return jVendor;
	}

	public String getJVersion(){
		return jVersion;
	}

	public String getJVmName(){
		return jVmName;
	}

	public String getJVmSpName(){
		return jVmSpName;
	}

	public String getJVmSpVendor(){
		return jVmSpVendor;
	}

	public String getJVmSpVersion(){
		return jVmSpVersion;
	}

	public String getJVmVendor(){
		return jVmVendor;
	}

	public String getJVmVersion(){
		return jVmVersion;
	}

	public String getOsArch(){
		return osArch;
	}

	public String getOsName(){
		return osName;
	}

	public String getOsVersion(){
		return osVersion;
	}

	public String getPath(){
		return path;
	}

	public String getUsHome(){
		return usHome;
	}

	public String getUsName(){
		return usName;
	}

	public void setFiSeperator(String x){
		if(new SystenLib().getBootLvl()==-1){		
			fiSeperator=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			fiSeperator=x;
			
			performeEFSx(0, "fisepe.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			fiSeperator=performeEFSx(1, "fisepe.efsx", x);
		}
	}

	public void setJClPath(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jClPath=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jClPath=x;
			
			performeEFSx(0, "jclpat.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jClPath=performeEFSx(1, "jclpat.efsx", x);
		}
	}

	public void setJClVersion(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jClVersion=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jClVersion=x;
			
			performeEFSx(0, "jclver.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jClVersion=performeEFSx(1, "jclver.efsx", x);
		}
	}

	public void setJExDirs(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jExDirs=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jExDirs=x;
			
			performeEFSx(0, "jexdir.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jExDirs=performeEFSx(1, "jexdir.efsx", x);
		}
	}

	public void setJIoTempDir(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jIoTempDir=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jIoTempDir=x;
			
			performeEFSx(0, "jiotem.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jIoTempDir=performeEFSx(1, "jiotem.efsx", x);
		}
	}

	public void setJLiPath(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jLiPath=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jLiPath=x;
			
			performeEFSx(0, "jlipat.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jLiPath=performeEFSx(1, "jlipat.efsx", x);
		}
	}

	public void setJPath(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jPath=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jPath=x;
			
			performeEFSx(0, "jpath.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jPath=performeEFSx(1, "jpath.efsx", x);
		}
	}

	public void setJSpName(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jSpName=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jSpName=x;
			
			performeEFSx(0, "jspnam.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jSpName=performeEFSx(1, "jspnam.efsx", x);
		}
	}

	public void setJSpVendor(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jSpVendor=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jSpVendor=x;
			
			performeEFSx(0, "jspven.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jSpVendor=performeEFSx(1, "jspven.efsx", x);
		}
	}

	public void setJSpVersion(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jSpVersion=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jSpVersion=x;
			
			performeEFSx(0, "jspver.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jSpVersion=performeEFSx(1, "jspver.efsx", x);
		}
	}

	public void setJVendor(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jVendor=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jVendor=x;
			
			performeEFSx(0, "jvendo.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jVendor=performeEFSx(1, "jvendo.efsx", x);
		}
	}

	public void setJVersion(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jVersion=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jVersion=x;
			
			performeEFSx(0, "jversi.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jVersion=performeEFSx(1, "jversi.efsx", x);
		}
	}

	public void setJVmName(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jVmName=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jVmName=x;
			
			performeEFSx(0, "jvmnam.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jVmName=performeEFSx(1, "jvmnam.efsx", x);
		}
	}

	public void setJVmSpName(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jVmSpName=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jVmSpName=x;
			
			performeEFSx(0, "jvmspn.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jVmSpName=performeEFSx(1, "jvmspn.efsx", x);
		}
	}

	public void setJVmSpVendor(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jVmSpVendor=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jVmSpVendor=x;
			
			performeEFSx(0, "jvmspv.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jVmSpVendor=performeEFSx(1, "jvmspv.efsx", x);
		}
	}

	public void setJVmSpVersion(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jVmSpVersion=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jVmSpVersion=x;
			
			performeEFSx(0, "jvmspve.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jVmSpVersion=performeEFSx(1, "jvmspve.efsx", x);
		}
	}

	public void setJVmVendor(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jVmVendor=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jVmVendor=x;
			
			performeEFSx(0, "jvmven.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jVmVendor=performeEFSx(1, "jvmven.efsx", x);
		}
	}

	public void setJVmVersion(String x){
		if(new SystenLib().getBootLvl()==-1){		
			jVmVersion=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			jVmVersion=x;
			
			performeEFSx(0, "jvmver.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			jVmVersion=performeEFSx(1, "jvmver.efsx", x);
		}
	}

	public void setOsArch(String x){
		if(new SystenLib().getBootLvl()==-1){		
			osArch=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			osArch=x;
			
			performeEFSx(0, "osarch.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			osArch=performeEFSx(1, "osarch.efsx", x);
		}
	}

	public void setOsName(String x){
		if(new SystenLib().getBootLvl()==-1){		
			osName=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			osName=x;
			
			performeEFSx(0, "osname.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			osName=performeEFSx(1, "osname.efsx", x);
		}
	}

	public void setOsVersion(String x){
		if(new SystenLib().getBootLvl()==-1){		
			osVersion=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			osVersion=x;
			
			performeEFSx(0, "osvers.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			osVersion=performeEFSx(1, "osvers.efsx", x);
		}
	}

	public void setPath(String x){
		if(new SystenLib().getBootLvl()==-1){		
			path=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			path=x;
			
			performeEFSx(0, "path.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			path=performeEFSx(1, "path.efsx", x);
		}
	}

	public void setUsHome(String x){
		if(new SystenLib().getBootLvl()==-1){		
			usHome=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			usHome=x;
			
			performeEFSx(0, "ushome.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			usHome=performeEFSx(1, "ushome.efsx", x);
		}
	}

	public void setUsName(String x){
		if(new SystenLib().getBootLvl()==-1){		
			usName=x;
		}
		else if(new SystenLib().getBootLvl()==0){
			usName=x;
			
			performeEFSx(0, "usname.efsx", x);
		}
		else if(new SystenLib().getBootLvl()==1){
			usName=performeEFSx(1, "usname.efsx", x);
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
}
