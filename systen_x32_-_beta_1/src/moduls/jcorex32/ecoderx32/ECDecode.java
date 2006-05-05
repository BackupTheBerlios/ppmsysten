
package moduls.jcorex32.ecoderx32;

import java.util.StringTokenizer;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class ECDecode {

	public String decode(String txt){
		Log			l=new Log();
		SystenLib	sl=new SystenLib();
		String 		endcode="", tmp="", str="";
		int 		i=0, min=0, j=0, m=0;
		long[]		code=null;
		long		mult=1, k=0;
		
		if((sl.getEc(0).equals("n/a"))||(sl.getEc(1).equals("n/a"))){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-45"), -45);
			
			return "";
		}
		
		try {
			StringTokenizer st=new StringTokenizer(txt, "#");
			
			if(txt.charAt(0)!='#'){
				tmp=st.nextElement().toString();
			}
			
			str=st.nextElement().toString();
			
			for(i=0; i<str.length(); i++){
				if(str.charAt(i)!='#'){
					endcode+=Integer.toString((int)str.charAt(i));
				}
			}
			
			m=Integer.parseInt(endcode);
			
			endcode="";
	
			str=st.nextElement().toString();
			
			for(i=0; i<str.length(); i++){
				if(str.charAt(i)!='#'){
					endcode+=Integer.toString((int)str.charAt(i));
				}
			}
			
			min=Integer.parseInt(endcode)/10;
			
			code=new long[m];
			
			endcode="";
			
			while(st.hasMoreElements()){
				str=st.nextElement().toString();
				
				for(i=0; i<str.length(); i++){
					if(str.charAt(i)!='#'){
						endcode+=Integer.toString((int)str.charAt(i));
					}
				}
				
				code[j]=Long.parseLong(endcode);
				
				j++;
				
				endcode="";
			}
			
			i=m;
	
			for(j=0; j<i; j+=2){
				if(j+2>i){
					break;
				}
				
				k=code[j];
					
				code[j]=code[j+1];
					
				code[j+1]=k;
			}
			
			for(j=0; j<i; j++){			
				for(m=j; m<i; m++){
					mult*=10;
				}
				
				code[j]=((code[j]/mult)+min)/32;
				
				mult=1;
			}
			
			for(j=0; j<i; j++){			
				endcode+=(char)code[j];
			}	
			
			return endcode+tmp;
		}
		catch(NumberFormatException nfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-48"));
			
			return "";
		}
	}
}
