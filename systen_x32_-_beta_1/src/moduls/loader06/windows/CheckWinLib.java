
package moduls.loader06.windows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import moduls.jcorex32.lib.HyperTHRONELib;
import moduls.jcorex32.lib.JavaLib;
import moduls.jcorex32.lib.MasterOSLib;
import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class CheckWinLib {
	
	private Log l=new Log();
	
	public void checkLaunchParameter(){
		SystenLib 			sl=new SystenLib();
		SimpleDateFormat 	sd=new SimpleDateFormat("yyyyMMdd");
		
		if(sl.getLaParam2().equalsIgnoreCase(sl.getVersion())==false){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-16"), 16);
		} 
		
		String param1=sd.format(new Date());

		if(sl.getLaParam1().length()!=param1.length()){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-17"), -17);
		}		
		
		if(Integer.parseInt(sl.getLaParam1())>Integer.parseInt(param1)){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-18"), -18);
		}
	}
	
	public void checkHyperTHRONE(){
		HyperTHRONELib htwl=new HyperTHRONELib();
		
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		
		int width=(int)d.getWidth();
		int height=(int)d.getHeight();
		
		boolean isIn=false;
		
		try {
			if((htwl.getCurrentMode(0)!=width)||(htwl.getCurrentMode(1)!=height)){
				l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-21"), -21);
			}
		}
		catch(NumberFormatException nfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-21"), -21);
		}
		
		String curStr=htwl.getCurrentMode(0)+" "+htwl.getCurrentMode(1)+" "+htwl.getCurrentMode(2);
		
		int resNumb=htwl.getResolutionNumber();
		
		for(int i=0; i<resNumb; i++){
			String str=htwl.getResolution(i, 0)+" "+htwl.getResolution(i, 1)+" "+htwl.getResolution(i, 2);
			
			if(str.equals(curStr)){
				isIn=true;
			}
		}
		
		if(!isIn){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-21"), -21);
		}
	}
	
	public void checkWinLib(){
		Properties 	OS=new Properties();
		SystenLib 	sl=new SystenLib();
		MasterOSLib	mwl=new MasterOSLib();
		JavaLib		jl=new JavaLib();
		
		try {
			OS.load(new FileInputStream("cfg/os.sys"));
			
			StringTokenizer st=new StringTokenizer(OS.getProperty(sl.getMasterOs(0)), "%");
			
			while(st.hasMoreElements()){
				if(mwl.getOs(1).toLowerCase().contains(st.nextElement().toString())){
					mwl.setOs(6, st.nextElement().toString());
				}
				else {
					st.nextElement().toString();
				}
			}
			
			if(mwl.getOs(6).length()==0){
				l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-26"), -26);
			}
			
			String tmp=OS.getProperty(jl.getOsArch()+mwl.getOs(6));
			
			tmp=jl.getOsArch()+tmp;

			if(mwl.getOs(2).equals(OS.getProperty(tmp))){
				sl.setMasterOs(1, OS.getProperty(tmp+mwl.getOs(2)));
				
				sl.setMasterOs(2, jl.getOsArch());
						
				sl.setMasterOs(3, mwl.getOs(2));
				
				if(sl.getMasterOs(1).equals("null")){
					l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-25"), -25);
				}
				
				String up=OS.getProperty(jl.getOsArch()+"up"+mwl.getOs(6));
						
				if((up.contains("oh"))&&(up.contains("Service Pack"))){
					up=up.substring(up.length()-3, up.length()-2);
													
					if(Integer.parseInt(up)<=Integer.parseInt(mwl.getOs(4).substring(mwl.getOs(4).length()-1, mwl.getOs(4).length()))){
						return;
					}				
				}
				else if(up.contains("Service Pack")){
					up=up.substring(up.length()-1, up.length());
													
					if(Integer.parseInt(up)==Integer.parseInt(mwl.getOs(4).substring(mwl.getOs(4).length()-1, mwl.getOs(4).length()))){
						return;
					}					
				}
				else {
					return;
				}
			}
			
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-25"), -25);
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-23"), -23);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-24"), -24);
		}
		catch(NullPointerException npe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-25"), -25);
		}
		catch(NumberFormatException nfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-26"), -26);
		}	
	}
}
