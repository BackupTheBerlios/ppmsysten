
package moduls.efsx;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import moduls.jcorex32.ecoderx32.ECoder;
import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class EFSx implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	private SystenLib sl=new SystenLib();
	
	public void loadEFSx(){
		Properties 	FS=new Properties();
		SystenLib	sl=new SystenLib();
		ECoder		e=new ECoder();
		Log			l=new Log();
		boolean 	isError=false;		
		
		try {
			if((sl.getEc(0).equals("n/a"))||(sl.getEc(1).equals("n/a"))){
				new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("-45"), -45);
			}
			
			FS.load(new FileInputStream("cfg/moduls/efsx.sys"));
				
			sl.setEFSx(0, FS.getProperty("name"));
				
			sl.setEFSx(1, FS.getProperty("version"));
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-51"), -51);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-52"), -52);
		}
			
		try {
			ObjectInputStream objIn=null;
			ObjectOutputStream objOut=null;
			int number=1;
			
			if(!new File(sl.getEFSxSource()).exists()){
				objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(sl.getEFSxSource())));
			}
			
			SimpleDateFormat sd=new SimpleDateFormat("yyyyMMddHHmmss");
				
			String time=sd.format(new Date());
				
			String id=createSID(), status="o";
				
			try {
				objIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream(sl.getEFSxSource())));
			}
			catch(EOFException eofe){
				id="01"+id;
					
				isError=true;
					
				objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(sl.getEFSxSource())));
					
				objOut.writeObject(e.performe(Integer.toString(number), 0, "stdsx32"));				
				objOut.writeObject(e.performe(id, 0, "stdsx32"));				
				objOut.writeObject(e.performe(time, 0, "stdsx32"));					
				objOut.writeObject(e.performe(status, 0, "stdsx32"));
				objOut.close();
					
				sl.initSession(number);
					
				sl.setSession(id, time, status);
					
				sl.setCurSession(id, time);
			}
				
			if(!isError){
				number=Integer.parseInt(e.performe(objIn.readObject().toString(), 1, "stdsx32"))+1;
					
				sl.initSession(number);
					
				if(number<10){
					id="0"+Integer.toString(number)+id;
				}
				else {
					id=Integer.toString(number)+id;
				}
					
				sl.setCurSession(id, time);			
					
				for(int i=0; i<number-1; i++){
					sl.setSession(e.performe(objIn.readObject().toString(), 1, "stdsx32"), new ECoder().performe(objIn.readObject().toString(), 1, "stdsx32"), new ECoder().performe(objIn.readObject().toString(), 1, "stdsx32"));
				}
					
				objIn.close();				
				
				if(sl.getBootLvl()!=1){
					sl.setSession(id, time, status);
					
					objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(sl.getEFSxSource())));
					
					objOut.writeObject(new ECoder().performe(Integer.toString(number), 0, "stdsx32"));
						
					for(int i=0; i<number; i++){
						objOut.writeObject(e.performe(sl.getSession(i, 0), 0, "stdsx32"));				
						objOut.writeObject(e.performe(sl.getSession(i, 1), 0, "stdsx32"));					
						objOut.writeObject(e.performe(sl.getSession(i, 2), 0, "stdsx32"));
					}
						
					objOut.close();
				}
			}
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-46"), -46);
		}
		catch(ClassNotFoundException cnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-46"), -46);
		}
		catch(NullPointerException npe){}
	}
	
	public String createSID(){
		String str="";
		
		if((new SystenLib().getEFSx(0).equals("n/a"))||(new SystenLib().getEFSx(1).equals("n/a"))){
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("-70"), -70);
			
			return "";
		}
		
		for(int i=0; i<4; i++){
			int a=(int)(Math.random()*100)+48;
			
			if((a>57)&&(a<97)){
				a=100;
			}
			else if(a>122){
				a=111;
			}
			
			str+=(char)a;
		}
		
		return str;
	}
	
	public boolean checkSession(){
		boolean isOpen=false;
		
		if((new SystenLib().getEFSx(0).equals("n/a"))||(new SystenLib().getEFSx(1).equals("n/a"))){
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("-70"), -70);
			
			return false;
		}
		
		for(int i=0; i<sl.getSessionNumber()-1; i++){
			if(sl.getSession(i, 2).equals("o")){
				isOpen=true;
			}
		}
		
		return isOpen;
	}
	
	public void clearSns(String sns, String time){
		ECoder	e=new ECoder();
		Log		l=new Log();
		
		try {
			if((new SystenLib().getEFSx(0).equals("n/a"))||(new SystenLib().getEFSx(1).equals("n/a"))){
				new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("-70"), -70);
				
				return;
			}
			
			sl.initSession(sl.getSessionNumber()-1);
			
			sl.setCurSession(sns, time);
			
			ObjectOutputStream objOut=objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(sl.getEFSxSource())));
				
			objOut.writeObject(new ECoder().performe(Integer.toString(sl.getSessionNumber()), 0, "stdsx32"));
					
			for(int i=0; i<sl.getSessionNumber(); i++){
				objOut.writeObject(e.performe(sl.getSession(i, 0), 0, "stdsx32"));				
				objOut.writeObject(e.performe(sl.getSession(i, 1), 0, "stdsx32"));					
				objOut.writeObject(e.performe(sl.getSession(i, 2), 0, "stdsx32"));
			}
					
			objOut.close();
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-46"), -46);
		}
		catch(NullPointerException npe){}
	}
}
