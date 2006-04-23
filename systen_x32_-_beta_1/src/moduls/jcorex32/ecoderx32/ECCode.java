
package moduls.jcorex32.ecoderx32;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class ECCode {

	public String code(String txt){
		SystenLib	sl=new SystenLib();
		int 		i=0, k=0, len=0;
		int 		min=(int)(Math.random()*1000);
		String 		endcode="", tmp="", signs="0123456789", str="";
		long[] 		code=new long[txt.length()];
		long 		j=1, l=1;
		
		if((sl.getEc(0)==null)||(sl.getEc(1)==null)){
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("-45"));
			
			return "";
		}
		
		len=txt.length();
		
		if(txt.length()>12){
			tmp=txt.substring(12, len);
			
			len=12;
		}
		
		for(i=0; i<len; i++){
			code[i]=(((int)txt.charAt(i))*32)-min;	
		}
			
		for(i=len-1; i>=0; i--){
			for(k=0; k<j; k++){
				l*=10;
			}
				
			code[i]*=l;
				
			j++;
				
			l=1;
		}
			
		for(i=0; i<len; i+=2){
			if(i+2>len){
				break;
			}
			
			j=code[i];
				
			code[i]=code[i+1];
				
			code[i+1]=j;
		}
			
		for(i=0; i<len; i++){
			endcode+=code[i]+"#";
		}
		
		str="#"+len+"#"+(min*10)+"#"+endcode.substring(0, endcode.length()-1);
		
		endcode="";
		
		boolean isIn=false;
		
		for(i=0; i<str.length(); i++){
			for(k=0; k<signs.length(); k++){
				if(str.charAt(i)==signs.charAt(k)){
					isIn=true;
				}
			}
			
			if(isIn){
				endcode+=(char)Integer.parseInt(str.charAt(i)+"");
				
				isIn=false;
			}
			else {
				endcode+=str.charAt(i);
			}
		}
		
		return tmp+endcode;
	}
}
