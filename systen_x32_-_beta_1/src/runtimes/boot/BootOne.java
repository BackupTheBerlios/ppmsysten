
package runtimes.boot;

import runtimes.loader06.OsSupport;
import runtimes.loader06.SystenProp;
import moduls.jcorex32.ci.Ci;
import moduls.jcorex32.lib.JavaLib;
import moduls.jcorex32.lib.SystenLib;
import moduls.jcorex32.lib.windows.AuralionWinLib;
import moduls.jcorex32.lib.windows.CyraWinLib;
import moduls.jcorex32.lib.windows.HyperTHRONEWinLib;
import moduls.jcorex32.lib.windows.MainWinLib;
import moduls.loader06.ErrorCode;
import moduls.loader06.windows.CheckWinLib;
import moduls.log.Log;
import visual.boot.BootFrm;

public class BootOne {
	
	public void bootOne(BootFrm bf){
		Log			l=new Log();
		Ci			ci=new Ci();
		JavaLib		jl=new JavaLib();
		SystenLib	sl=new SystenLib();
		
		sl.setCurSession("01dddf", "");				// zeile nur zur entwicklungszwecken - session muss existieren
		
		try {
			jl.setOsName("");
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("026"));
			bf.setFullPos(33);
			
			String os=new OsSupport().loadOsSupport();
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000"));
			bf.setFullPos(35);			
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("025"));
			bf.setFullPos(36);
			
			jl.setPath("");
			jl.setJVersion("");
			jl.setJVendor("");
			jl.setJPath("");
			jl.setJVmSpVersion("");
			jl.setJVmSpVendor("");
			jl.setJVmSpName("");
			jl.setJVmVersion("");
			jl.setJVmVendor("");
			jl.setJVmName("");
			jl.setJSpVersion("");
			jl.setJSpVendor("");
			jl.setJSpName("");
			jl.setJClVersion("");
			jl.setJClPath("");
			jl.setJLiPath("");
			jl.setJIoTempDir("");
			jl.setJExDirs("");
			jl.setOsArch("");
			jl.setOsVersion("");	
			jl.setFiSeperator("");
			jl.setUsName("");
			jl.setUsHome("");
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000")+"##n");
			bf.setFullPos(38);			
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(3)+" "+ci.getTxt("009"));
			bf.setFullPos(41);				
			bf.setTxt("##m"+os.toUpperCase().replaceAll("_", " ")+"##n");
			
			Thread.sleep(150);
			
			sl.setMasterOs(0, os);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("006"));
			bf.setFullPos(45);			
			
			new SystenProp().loadSystenProperties();
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000")+"##n");
			bf.setFullPos(50);
			
			Thread.sleep(150);
			
			if(os.toLowerCase().contains("windows")){
				MainWinLib 			mwl=new MainWinLib();
				AuralionWinLib 		awl=new AuralionWinLib();
				CyraWinLib			cwl=new CyraWinLib();
				HyperTHRONEWinLib 	htwl=new HyperTHRONEWinLib();
				CheckWinLib			chwl=new CheckWinLib();
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("027"));
				bf.setFullPos(52);
				
				mwl.setSystemDrive("");
				mwl.setHomeDrive("");
				mwl.setSystemRoot("");
				mwl.setTemp("");
				mwl.setHomePath("");
				mwl.setNumberOfProcessors("");
				mwl.setProcessorArchitecture("");
				mwl.setProcessorIdentifier("");
				mwl.setTime("");
				mwl.setSystemManufacturer("");
				mwl.setSystemModel("");
				mwl.setBios("");
				mwl.setDirectXVersion("");
				mwl.setMemory("");
				mwl.setOs(0, "");
				mwl.setLanguage(0, "");
				mwl.setProcessor(0, "");
				mwl.setPageFile(0, "");
				mwl.initHdd(0);
				mwl.setHdd(0, "");
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(56);			
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("028"));
				bf.setFullPos(60);
				
				awl.setCardNumber(0);
				awl.setDescription(0, "");
				awl.setDriverName(0, "");
				awl.setDriverProvider(0, "");
				awl.setDriverVersion(0, 0, "");
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(65);			
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("029"));
				bf.setFullPos(68);
				
				cwl.setIp("");			
				cwl.setMachineName("");			
				cwl.initComAdaptersNumber(0);			
				cwl.setComAdapters(0, "");			
				cwl.initNwDevicesNumber(0);			
				cwl.setNwDevices(0, "");
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(70);			
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("030"));
				bf.setFullPos(75);
				
				htwl.setCardName("");
				htwl.setChipType("");				
				htwl.setDacType("");
				htwl.setDisplayMemory("");
				htwl.setDriverName("");
				htwl.setDriverVersion(0,"");
				htwl.setManufacturer("");
				htwl.setRefreshRate("");			
				htwl.initResolution(0);			
				htwl.setResolution(0, "", "", "");
				htwl.setCurrentMode("", "", "");
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000")+"##n");
				bf.setFullPos(80);		
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("013")+"##n");
				bf.setFullPos(82);
				
				Thread.sleep(200);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("014"));
				bf.setFullPos(83);				
				
				chwl.checkHyperTHRONE();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(84);
				
				Thread.sleep(150);			
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("015"));
				bf.setFullPos(85);			
				
				chwl.checkWinLib();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000")+"##n");
				bf.setFullPos(86);
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(3)+" "+ci.getTxt("016"));
				bf.setFullPos(89);
				
				bf.setTxt("##m"+sl.getMasterOs(1).replace("_", " ")+"##n");
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("017"));
				bf.setFullPos(90);
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000")+"##n");
				bf.setFullPos(95);
				
				Thread.sleep(150);
			}
			else if(os.toLowerCase().contains("linux")){
				// LINUX Code für Bibliotheken einfügen			
			}
			else {
				new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("-25"), -25);
			}
		}
		catch(InterruptedException ie){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-27"), -27);
		}
	}
}
