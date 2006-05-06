
package runtimes.loader06.windows;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import moduls.jcorex32.lib.AuralionLib;
import moduls.jcorex32.lib.CyraLib;
import moduls.jcorex32.lib.HyperTHRONELib;
import moduls.jcorex32.lib.JavaLib;
import moduls.jcorex32.lib.MasterOSLib;
import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class MasterOsWinProp extends Thread {
	
	public void run(){
		Log					l=new Log();
		MasterOSLib 			mwl=new MasterOSLib();
		SystenLib			sl=new SystenLib();
		AuralionLib		awl=new AuralionLib();
		CyraLib			cwl=new CyraLib();
		HyperTHRONELib	htwl=new HyperTHRONELib();
		
/*		try {
			long time=new GregorianCalendar().getTime().getTime();
			
			String path=new JavaLib().getPath();
			
			if(sl.getBootLvl()==-1){
				path=sl.getTmpPath()+"\\";
			}
			else {
				path+="\\"+sl.getTmpPath();
			}
			
			Process process=Runtime.getRuntime().exec("dxdiag /t "+path+"runtime.swp");
			
			process.waitFor();
			
			mwl.setTime(Double.toString((new GregorianCalendar().getTime().getTime()-time)/1000));
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-12"), -12);
		}
		catch(InterruptedException ie){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-13"), -13);
		}*/
		
		mwl.setTime("46");
		
		try {
			int i=0;
			
			String tmp="";
			
			RandomAccessFile br=new RandomAccessFile(sl.getTmpPath().replace("\\", "/")+"runtime.swp", "r");
			
			for(i=0; i<5; i++){
				tmp=br.readLine();
			}

			cwl.setMachineName(defrag(tmp.substring(21, tmp.length())));
			
			InetAddress iAddr=InetAddress.getByName(cwl.getMachineName());
			
			cwl.setIp(iAddr.getHostAddress());
			
			tmp=br.readLine();
			
			tmp=defrag(tmp.substring(21, tmp.length()));
			
			StringTokenizer st=new StringTokenizer(tmp, "(");
			
			tmp=st.nextElement().toString();
			
			tmp=tmp.substring(0, tmp.length()-1);
			
			mwl.setOs(1, tmp.replaceAll(" ", "_")+"_"+mwl.getOs(0));
			
			mwl.setOs(0, tmp);

			StringTokenizer st2=new StringTokenizer(st.nextElement().toString(), ")");
			
			StringTokenizer st3=new StringTokenizer(st2.nextElement().toString(), ",");
			
			mwl.setOs(2, st3.nextElement().toString());
			
			tmp=st3.nextElement().toString();
			
			mwl.setOs(3, tmp.substring(7, tmp.length()));
			
			try {
				tmp=st2.nextElement().toString();
				
				mwl.setOs(4, tmp.substring(1, tmp.length()-1));
			}
			catch(StringIndexOutOfBoundsException sioobe){
				mwl.setOs(4, "");
			}
			
			tmp=st.nextElement().toString();
			
			mwl.setOs(5, tmp.substring(0, tmp.length()-1));

			tmp=br.readLine();
			
			tmp=defrag(tmp.substring(21, tmp.length()-1));
			
			st=new StringTokenizer(tmp, " ");
			
			mwl.setLanguage(0, st.nextElement().toString());
			
			for(i=0; i<2; i++){
				st.nextElement();
			}
			
			mwl.setLanguage(1, st.nextElement().toString());
			
			tmp=br.readLine();
			
			mwl.setSystemManufacturer(defrag(tmp.substring(21, tmp.length())));
			
			tmp=br.readLine();
			
			mwl.setSystemModel(defrag(tmp.substring(21, tmp.length())));

			tmp=br.readLine();
			
			mwl.setBios(defrag(tmp.substring(21, tmp.length())));
			
			tmp=br.readLine();
			
			st=new StringTokenizer(defrag(tmp.substring(21, tmp.length())), " ");
			
			tmp=st.nextElement().toString()+" ";
			
			String tmp2="";
			
			while(st.hasMoreElements()){
				tmp2=st.nextElement().toString()+" ";
				
				if(tmp2.contains("Hz")){
					tmp2=tmp2.substring(0, tmp2.length()-1);
					
					break;
				}
				else {
					tmp+=tmp2;
				}			
			}
			
			if(tmp.charAt(tmp.length()-2)==','){
				mwl.setProcessor(0, tmp.substring(0, tmp.length()-2));
			}
			else {
				mwl.setProcessor(0, tmp.substring(0, tmp.length()-1));
			}
			
			mwl.setProcessor(1, tmp2.substring(0, tmp2.length()-3));
			
			mwl.setProcessor(2, tmp2.substring(tmp2.length()-3, tmp2.length()));
			
			tmp=br.readLine();
			
			st=new StringTokenizer(defrag(tmp.substring(21, tmp.length())), "M");

			mwl.setMemory(st.nextElement().toString());
			
			tmp=br.readLine();
			
			st=new StringTokenizer(defrag(tmp.substring(21, tmp.length())), "M");

			mwl.setPageFile(0, st.nextElement().toString());

			st=new StringTokenizer(st.nextElement().toString(), " ");
			
			for(i=0; i<2; i++){
				st.nextElement();
			}

			mwl.setPageFile(1, st.nextElement().toString());

			tmp=br.readLine();
			
			tmp=defrag(tmp.substring(21, tmp.length()));
			
			if(tmp.equals(mwl.getSystemRoot())){
				mwl.setSystemRoot(tmp);
			}
			else {
				l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-22"), -22);
			}

			tmp=br.readLine();

			mwl.setDirectXVersion(defrag(tmp.substring(21, tmp.length())));

			for(i=0; i<2; i++){
				tmp=br.readLine();
			}			
			
			do{
				tmp=br.readLine();
			}
			while(tmp.equalsIgnoreCase("Display Devices")==false);			
			
			for(i=0; i<2; i++){
				tmp=br.readLine();
			}
			
			htwl.setCardName(defrag(tmp.substring(19, tmp.length())));

			tmp=br.readLine();
			
			htwl.setManufacturer(defrag(tmp.substring(19, tmp.length())));

			tmp=br.readLine();

			htwl.setChipType(defrag(tmp.substring(19, tmp.length())));
			
			tmp=br.readLine();
			
			if((tmp.contains("("))&&(tmp.contains(")"))){
				st=new StringTokenizer(defrag(tmp.substring(21, tmp.length())), "(");
				
				st.nextElement();
				
				tmp=st.nextElement().toString();
				
				htwl.setDacType(tmp.substring(0, tmp.length()-4));
			}
			else {
				htwl.setDacType("");
			}
			
			for(i=0; i<2; i++){
				tmp=br.readLine();
			}
			
			st=new StringTokenizer(defrag(tmp.substring(19, tmp.length())), " ");
			
			htwl.setDisplayMemory(st.nextElement().toString());
			
			tmp=br.readLine();
			
			try {
				st=new StringTokenizer(defrag(tmp.substring(19, tmp.length())), " ");
							
				String width=st.nextElement().toString();
				
				st.nextElement();
							
				String height=st.nextElement().toString();
							
				String depth=st.nextElement().toString();
							
				depth=depth.substring(1, depth.length());
							
				st.nextElement();
							
				String rate=st.nextElement().toString();
							
				if(rate.length()==7){
					rate=rate.substring(1, 4);
				}
				else if(rate.length()==6){
					rate=rate.substring(1, 3);
				}
							
				if(width.length()<height.length()){
					l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-20"), -20);
				}
				
				htwl.setCurrentMode(width, height, depth);
				
				htwl.setRefreshRate(rate);
			}
			catch(NoSuchElementException nsee){
				l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-19"), -19);
			}
			
			for(i=0; i<3; i++){
				tmp=br.readLine();
			}

			htwl.setDriverName(defrag(tmp.substring(19, tmp.length())));
			
			tmp=br.readLine();

			st=new StringTokenizer(defrag(tmp.substring(19, tmp.length()-1)), " ");
			
			htwl.setDriverVersion(0, st.nextElement().toString());
			
			tmp=st.nextElement().toString();
			
			htwl.setDriverVersion(1, tmp.substring(1, tmp.length()));
			
			for(i=0; i<3; i++){
				tmp=br.readLine();
			}
			
			st=new StringTokenizer(defrag(tmp.substring(19, tmp.length())), " ");
			
			st2=new StringTokenizer(st.nextElement().toString(), "/");
			
			tmp2=st2.nextElement().toString();
			
			if(Integer.parseInt(tmp2)<10){
				tmp="0"+tmp2+".";
			}
			else {
				tmp=tmp2+".";
			}
			
			tmp2=st2.nextElement().toString();
			
			if(Integer.parseInt(tmp2)<10){
				tmp+="0"+tmp2+" ";
			}
			else {
				tmp+=tmp2+" ";
			}

			tmp2=st.nextElement().toString();
			
			htwl.setDriverVersion(2, st2.nextElement().toString()+"."+tmp+tmp2.substring(0, tmp2.length()-1));
			
			htwl.setDriverVersion(3, st.nextElement().toString());
			
			do {
				tmp=br.readLine();
			}
			while(tmp.equalsIgnoreCase("Sound Devices")==false);
			
			for(i=0; i<2; i++){
				tmp=br.readLine();
			}
			
			int j=0, k=0;
			
			try {
				String[][] str=new String[500][4];
				
				while(true){
					str[k][0]=defrag(tmp.substring(25, tmp.length()));
					
					for(i=0; i<7; i++){
						tmp=br.readLine();
					}
					
					str[k+1][0]=defrag(tmp.substring(25, tmp.length()));
		
					tmp=br.readLine();
	
					st=new StringTokenizer(defrag(tmp.substring(25, tmp.length()-1)), " ");
					
					str[k+2][0]=st.nextElement().toString();
					
					tmp=st.nextElement().toString();
					
					str[k+2][1]=tmp.substring(1, tmp.length());
					
					for(i=0; i<3; i++){
						tmp=br.readLine();
					}
				
					st=new StringTokenizer(defrag(tmp.substring(25, tmp.length())), " ");
					
					st2=new StringTokenizer(st.nextElement().toString(), "/");			
					
					tmp2=st2.nextElement().toString();
					
					if(Integer.parseInt(tmp2)<10){
						tmp="0"+tmp2+".";
					}
					else {
						tmp=tmp2+".";
					}
					
					tmp2=st2.nextElement().toString();
					
					if(Integer.parseInt(tmp2)<10){
						tmp+="0"+tmp2+" ";
					}
					else {
						tmp+=tmp2+" ";
					}
	
					tmp2=st.nextElement().toString();
					
					str[k+2][2]=st2.nextElement().toString()+"."+tmp+tmp2.substring(0, tmp2.length()-1);
					
					str[k+2][3]=st.nextElement().toString();
				
					for(i=0; i<2; i++){
						tmp=br.readLine();
					}
					
					str[k+3][0]=defrag(tmp.substring(25, tmp.length()));
					
					j++;
					
					k+=4;
					
					do {
						tmp=br.readLine();
					}
					while(!tmp.substring(0, 25).equalsIgnoreCase("      Sound Test Result: "));
					
					tmp=br.readLine();
					tmp=br.readLine();
					
					if(tmp.equals("---------------------")){
						break;
					}
				}
				
				awl.setCardNumber(j);
			
				int m=0;
	
				for(i=0; i<k; i+=4){
					awl.setDescription(m, str[i][0]);
					awl.setDriverName(m, str[i+1][0]);
					awl.setDriverVersion(m, 0, str[i+2][0]);
					awl.setDriverVersion(m, 1, str[i+2][1]);
					awl.setDriverVersion(m, 2, str[i+2][2]);
					awl.setDriverVersion(m, 3, str[i+2][3]);
					awl.setDriverProvider(m, str[i+3][0]);
					
					m++;
				}
			}
			catch(StringIndexOutOfBoundsException sioobe){
				awl.setCardNumber(1);
			
				awl.setDescription(0, "");
				awl.setDriverName(0, "");
				awl.setDriverVersion(0, 0, "");
				awl.setDriverVersion(0, 1, "");
				awl.setDriverVersion(0, 2, "");
				awl.setDriverVersion(0, 3, "");
				awl.setDriverProvider(0, "");
			}
			
			do {
				tmp=br.readLine();
			}
			while(!tmp.equalsIgnoreCase("DirectPlay Adapters"));

			tmp=br.readLine();
			
			i=0;
			
			String[] str2=new String[500];
			
			while(!tmp.equalsIgnoreCase("Disk & DVD/CD-ROM Drives")) {
				tmp=br.readLine();				
				
				if((tmp.contains("Service Provider:"))){
					str2[i]=tmp.substring(tmp.indexOf(":")+2, tmp.length());

					i++;
				}
			}
			
			cwl.initComAdaptersNumber(i);
			
			for(j=0; j<cwl.getComAdaptersNumber(); j++){
				tmp=str2[j];
				
				if(tmp.charAt(tmp.length()-1)==' '){
					cwl.setComAdapters(j, tmp.substring(0, tmp.length()-1));
				}
				else {				
					cwl.setComAdapters(j, tmp);
				}
			}
			
			for(i=0; i<2; i++){
				tmp=br.readLine();
			}
			
			i=1;
			
			while(!tmp.equalsIgnoreCase("--------------")){	
				tmp=tmp.substring(13, tmp.length());

				str2[i]=defrag(tmp);
				
				i++;
				
				tmp=br.readLine();
				
				if(!tmp.substring(0, 13).equals("      Model: ")){
					tmp=tmp.substring(13, tmp.length());
					str2[i]=defrag(tmp);
					i++;
					
					tmp=br.readLine();
					tmp=tmp.substring(13, tmp.length());
					str2[i]=defrag(tmp);
					i++;
					
					tmp=br.readLine();
					tmp=tmp.substring(13, tmp.length());
					str2[i]=defrag(tmp);
					i++;
					
					tmp=br.readLine();
					tmp=tmp.substring(13, tmp.length());
					str2[i]=defrag(tmp);
					i++;
					
					str2[i]="";
					i++;
				}
				else {
					str2[i]="";
					i++;
					
					str2[i]="";
					i++;
					
					str2[i]="";
					i++;
					
					tmp=tmp.substring(13, tmp.length());
					str2[i]=defrag(tmp);
					i++;
					
					tmp=br.readLine();
					tmp=tmp.substring(13, tmp.length());
					str2[i]=defrag(tmp);
					i++;
				}
				
				for(j=0; j<2; j++){
					tmp=br.readLine();
				}
			}			
		
			str2[0]=Integer.toString(i);
			
			mwl.initHdd(i);
			
			for(j=0; j<i; j++){
				mwl.setHdd(j, str2[j]);
			}
			
			tmp=br.readLine();
			
			i=0;
			
			while(!tmp.equalsIgnoreCase("DirectX Components")){
				tmp=br.readLine();
				
				if((tmp.contains("WLAN"))||(tmp.contains("NIC"))){
					str2[i]=tmp.substring(11, tmp.length());
					
					i++;
				}			
			}
			
			cwl.initNwDevicesNumber(i);
			
			for(j=0; j<i; j++){
				cwl.setNwDevices(j, str2[j]);
			}
			
			for(j=0; j<cwl.getNwDevicesNumber(); j++){
				tmp=cwl.getNwDevices(j);
				
				if(tmp.charAt(tmp.length()-1)==' '){
					cwl.setNwDevices(j, tmp.substring(0, tmp.length()-1));
				}
			}

			br.close();
			
//			new File(sl.getTmpPath().replace("\\", "/")+"runtime.swp").delete();
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-14"), -14);
		}
		catch(NullPointerException np){
			System.out.flush();
		}
		catch(StringIndexOutOfBoundsException sioobe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-27"), -27);
		}	
	}
	
	public String defrag(String str){
		String 	tmp="";
		int 	i=0;
		
		if(str.length()==0){
			return "";
		}
		
		while(i!=str.length()){
			if(str.charAt(i)==(char)39)
				i++;
			
			tmp+=str.charAt(i);
			
			i++;
		}
		
		while(tmp.charAt(tmp.length()-1)==' '){
			tmp=tmp.substring(0, tmp.length()-1);
		}
		
		return tmp;
	}
}
