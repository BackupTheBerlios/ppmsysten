
package runtimes.boot;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import moduls.jcorex32.lib.SystenLib;
import moduls.log.Log;

public class Prestart {
	
	public void setPrestart(int modus){
		SystenLib sl=new SystenLib();
		
		try {
			if(modus==0){
				ObjectInputStream objIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("cfg/settings.syse")));
			
				ArrayList stngs=new ArrayList();
				
				for(int i=0; i<10; i++){
					stngs.add(objIn.readObject().toString());
				}
				
				objIn.close();
				
				sl.setSettings(stngs);
				
				new File("cfg/settings.sys").delete();
			}
			else if(modus==1){
				ObjectInputStream objIn=null;
				
				if(new File("cfg/settings.sys").exists()){
					objIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("cfg/settings.sys")));
				}
				else {
					objIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("cfg/settings.syse")));
				}
				
				ArrayList stngs=new ArrayList();
				
				for(int i=0; i<10; i++){
					stngs.add(objIn.readObject().toString());
				}
				
				objIn.close();
				
				sl.setSettings(stngs);
			}
			else if(modus==2){
				ObjectOutputStream objOut=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("cfg/settings.sys")));
				
				ArrayList stngs=new SystenLib().getSettings();
				
				for(int i=0; i<10; i++){
					objOut.writeObject(stngs.get(i));
				}
				
				objOut.close();
			}
			
			if(sl.getSettings().get(9).equals("0")){
				sl.setLng("en");
			}
			else if(sl.getSettings().get(9).equals("1")){
				sl.setLng("de");
			}
			
			if(sl.getSettings().get(8).equals("0")){
				sl.setBootLvl(-1);
			}
			else if(sl.getSettings().get(8).equals("1")){
				sl.setBootLvl(0);
			}
			
			if(sl.getSettings().get(7).equals("0")){
				sl.setCurSessionClose(false);
			}
			else if(sl.getSettings().get(7).equals("1")){
				sl.setCurSessionClose(true);
			}
		}
		catch(IOException ioe){
			new Log().log(this.getClass().getName(), "SETTINGS: unable to load", -72);
		}
		catch(ClassNotFoundException cnfe){
			new Log().log(this.getClass().getName(), "SETTINGS: unable to load", -72);
		}
	}
}
