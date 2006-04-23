
package moduls.jcorex32.ecoderx32;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class ECoder {
	
	private Log l=new Log();
	
	private SystenLib sl=new SystenLib();	
	
	public void loadECoder(){
		Properties EC=new Properties();			
		
		try {
			EC.load(new FileInputStream("cfg/moduls/ec.sys"));
			
			sl.setEc(0, EC.getProperty("name"));
			
			sl.setEc(1, EC.getProperty("version"));
			
			sl.setEc(2, EC.getProperty("modi"));
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-29"), -29);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-30"), -30);
		}
	}
	
	public String performe(String code, int type, String modus){
		String str="";
		
		if((sl.getEc(0)==null)||(sl.getEc(1)==null)){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-45"));
			
			return "";
		}
		
		if(!sl.getEc(2).contains(modus)){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-47"));
			
			return str;
		}
		
		if(type==0){
			int len=code.length()/12, rest=0;
				
			if((len*12)<code.length()){
				len++;
			}

			rest=code.length()-((len-1)*12);
				
			str=Integer.toString(len);
			
			for(int i=0; i<len; i++){
				int j=12;
					
				if(i==len-1){
					j=rest;
				}
				
				str+=new ECCode().code(code.substring(i*12, (i*12)+j))+"#/#";				
			}
			
			str+=modus;
		}
		else if(type==1){
			try {
				int len=Integer.parseInt(code.charAt(0)+"");
				
				code=code.substring(1, code.length());
				
				String mod=code.substring(code.length()-7, code.length());
				
				if(!mod.equals(modus)){
					
					l.print(mod+" "+modus);
					
					l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-47"));
					
					return str;
				}
				
				for(int i=0; i<len; i++){			
					str+=new ECDecode().decode(code.substring(0, code.indexOf("#/#")));
					
					code=code.substring(code.indexOf("#/#")+3, code.length());
				}
			}
			catch(NumberFormatException nfe){
				l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-47"));
			}
		}	
		
		return str;
	}
}
