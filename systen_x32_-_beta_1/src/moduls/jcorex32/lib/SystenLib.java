
package moduls.jcorex32.lib;

import java.util.ArrayList;

public class SystenLib {
	
	private static int osType=0;
	
	private static String laParam1="";
	
	private static String laParam2="";
	
	private static String laParam3="";
	
	private static String version="";
	
	private static String eFSxSource="efsx/index.efsx";
	
	private static String[] masterOs=new String[4];
	
	private static String[] ci=new String[2];
	
	private static String[] ec=new String[3];
	
	private static String[] eFSx=new String[2];
	
	private static String[] auralion=new String[2];
	
	private static String[] cyra=new String[2];
	
	private static String[] hyperTHRONE=new String[2];
	
	private static String[] endavor=new String[2];
	
	private static int bootLvl=0;
	
	private static int errorLvl=0;
	
	private static ArrayList session=new ArrayList();
	
	private static String[] curSession=new String[2];
	
	private static boolean curSessionClose=false;
	
	private static String tmpPath="swap\\";
	
	private static String lng=".en";
	
	public String getLaParam1(){
		return laParam1;
	}

	public String getLaParam2(){
		return laParam2;
	}
	
	public String getLaParam3(){
		return laParam3;
	}

	public String getEFSxSource(){
		return eFSxSource;
	}

	public String getVersion(){
		return version;
	}
	
	public String getMasterOs(int x){
		return masterOs[x];
	}
	
	public String getCi(int x){
		if(ci[x]==null){
			return "n/a";
		}
		else {
			return ci[x];
		}
	}
	
	public int getBootLvl(){
		return bootLvl;
	}
	
	public int getErrorLvl(){
		return errorLvl;
	}
	
	public String getEc(int x){
		if(ec[x]==null){
			return "n/a";
		}
		else {
			return ec[x];
		}
	}
	
	public String getSession(int x, int y){
		return session.get(((x*3)+y+1)).toString();
	}
	
	public int getSessionNumber(){
		return Integer.parseInt(session.get(0).toString());
	}
	
	public String getTmpPath(){
		return tmpPath;
	}
	
	public String getLng(){
		return lng;
	}
	
	public String getCurSession(int x){
		return curSession[x];
	}
	
	public boolean getCurSessionClose(){
		return curSessionClose;
	}
	
	public String getEFSx(int x){
		if(eFSx[x]==null){
			return "n/a";
		}
		else {
			return eFSx[x];
		}
	}
	
	public String getAuralion(int x){
		if(auralion[x]==null){
			return "n/a";
		}
		else {
			return auralion[x];
		}
	}
	
	public String getCyra(int x){
		if(cyra[x]==null){
			return "n/a";
		}
		else {
			return cyra[x];
		}
	}
	
	public String getHyperTHRONE(int x){
		if(hyperTHRONE[x]==null){
			return "n/a";
		}
		else {
			return hyperTHRONE[x];
		}
	}
	
	public String getEndavor(int x){
		if(endavor[x]==null){
			return "n/a";
		}
		else {
			return endavor[x];
		}
	}
	
	public int getOsType(){
		return osType;
	}
	
	public void setOsType(int x){
		osType=x;
	}
	
	public void setEndavor(int x, String y){
		endavor[x]=y;
	}
	
	public void setHyperTHRONE(int x, String y){
		hyperTHRONE[x]=y;
	}	
	
	public void setCyra(int x, String y){
		cyra[x]=y;
	}
	
	public void setAuralion(int x, String y){
		auralion[x]=y;
	}
	
	public void setEFSx(int x, String y){
		eFSx[x]=y;
	}
	
	public void setCurSessionClose(){
		curSessionClose=true;
	}
	
	public void changeSession(int x, int y, String z){
		session.set(((x*3)+y+1), z);
	}
	
	public void setCurSession(String x, String y){
		curSession[0]=x;
		curSession[1]=y;
	}
	
	public void setLng(String x){
		lng="."+x;
	}
	
	public void setTmpPath(String x){
		tmpPath=x;
	}
	
	public void initSession(int x){
		if(session.isEmpty()){
			session.add(Integer.toString(x));
		}
		else {
			session.set(0, Integer.toString(x));
		}
	}
	
	public void setSession(String x, String y, String z){
		session.add(x);
		session.add(y);
		session.add(z);
	}
	
	public void setErrorLvl(int x){
		errorLvl=x;
	}

	public void setBootLvl(int x){
		bootLvl=x;
	}

	public void setVersion(String x){
		version=x;
	}
	
	public void setLaParam1(String x){
		laParam1=x;
	}

	public void setLaParam2(String x){
		laParam2=x;
	}
	
	public void setLaParam3(String x){
		laParam3=x;
	}

	public void setMasterOs(int x, String y){
		masterOs[x]=y;
	}

	public void setCi(int x, String y){
		ci[x]=y;
	}
	
	public void setEc(int x, String y){
		ec[x]=y;
	}
}
