
package runtimes.boot;

import moduls.jcorex32.ci.Ci;
import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.loader06.linux.CheckLinLib;
import moduls.loader06.windows.CheckWinLib;
import moduls.log.Log;
import runtimes.loader06.JavaProp;
import runtimes.loader06.OsSupport;
import runtimes.loader06.SystenProp;
import runtimes.loader06.linux.CmdLinProp;
import runtimes.loader06.linux.MasterOsLinProp;
import runtimes.loader06.linux.ResolutionsLin;
import runtimes.loader06.windows.CmdWinProp;
import runtimes.loader06.windows.MasterOsWinProp;
import runtimes.loader06.windows.ResolutionsWin;
import visual.boot.BootFrm;

public class BootZero {
	
	public void bootZero(BootFrm bf){
		CheckWinLib cwl=new CheckWinLib();
		CheckLinLib cll=new CheckLinLib();
		SystenLib	sl=new SystenLib();
		Ci			ci=new Ci();
		Log 		l=new Log();
		
		try {
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("005"));
			bf.setFullPos(33);			
			
			new JavaProp().loadJavaProperties();
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000"));
			bf.setFullPos(35);
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("006"));
			bf.setFullPos(37);			
			
			new SystenProp().loadSystenProperties();
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000"));
			bf.setFullPos(39);
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("007"));
			bf.setFullPos(40);			
			
			String os=new OsSupport().loadOsSupport();
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000"));
			bf.setFullPos(43);
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("008"));
			bf.setFullPos(45);			
			
			if(os.contains("windows")){				
				cwl.checkLaunchParameter();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000")+"##n");
				bf.setFullPos(47);
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(3)+" "+ci.getTxt("009"));
				bf.setFullPos(49);				
				bf.setTxt("##m"+os.toUpperCase().replaceAll("_", " ")+"##n");
				
				Thread.sleep(150);
				
				sl.setMasterOs(0, os);
				
				CmdWinProp 		cwp=new CmdWinProp();
				ResolutionsWin 	rw=new ResolutionsWin();
				MasterOsWinProp	mwp=new MasterOsWinProp();
				
				cwp.setName("CmdWinProp");
				rw.setName("ResolutionsWin");
				mwp.setName("MasterOsWinProp");
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("010"));
				bf.setFullPos(52);			
				
				cwp.start();
				cwp.join();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(54);
				
				Thread.sleep(150);				
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("011"));
				bf.setFullPos(54);			
				
				rw.start();
				rw.join();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(57);
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("012"));
				bf.setFullPos(58);			
				
				mwp.start();				
				mwp.join();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(75);
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("013")+"##n");
				bf.setFullPos(77);
				
				Thread.sleep(200);

				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("014"));
				bf.setFullPos(79);			
				
				cwl.checkHyperTHRONE();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(81);
				
				Thread.sleep(150);				
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("015"));
				bf.setFullPos(83);			
				
				cwl.checkWinLib();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000")+"##n");
				bf.setFullPos(85);
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(3)+" "+ci.getTxt("016"));
				bf.setFullPos(86);			
				bf.setTxt("##m"+sl.getMasterOs(1).replace("_", " ")+"##n");
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("017"));
				bf.setFullPos(90);
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000")+"##n");
				bf.setFullPos(95);
				
				Thread.sleep(150);
			}
			else if(os.contains("linux")){
				cll.checkLaunchParameter();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000")+"##n");
				bf.setFullPos(47);
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(3)+" "+ci.getTxt("009"));
				bf.setFullPos(49);				
				bf.setTxt("##m"+os.toUpperCase().replaceAll("_", " ")+"##n");
				
				Thread.sleep(150);
				
				sl.setMasterOs(0, os);
				
				CmdLinProp 		clp=new CmdLinProp();
				ResolutionsLin 	rl=new ResolutionsLin();
				MasterOsLinProp	mlp=new MasterOsLinProp();
				
				clp.setName("CmdLinProp");
				rl.setName("ResolutionsLin");
				mlp.setName("MasterOsLinProp");
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("010"));
				bf.setFullPos(52);			
				
				clp.start();
				clp.join();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(54);
				
				Thread.sleep(150);				
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("011"));
				bf.setFullPos(54);			
				
				rl.start();
				rl.join();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(57);
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("012"));
				bf.setFullPos(58);			
				
				mlp.start();
				mlp.join();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(75);
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("013")+"##n");
				bf.setFullPos(77);
				
				Thread.sleep(200);

				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("014"));
				bf.setFullPos(79);			
				
				cll.checkHyperTHRONE();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(81);
				
				Thread.sleep(150);				
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("015"));
				bf.setFullPos(83);			
				
				cll.checkLinLib();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000")+"##n");
				bf.setFullPos(85);
				
				Thread.sleep(150);
				
				sl.setMasterOs(1, "Linux");
				
				bf.setTxt("##l"+ci.getGlobalMod(3)+" "+ci.getTxt("016"));
				bf.setFullPos(86);			
				bf.setTxt("##m"+sl.getMasterOs(1).replace("_", " ")+"##n");
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("017"));
				bf.setFullPos(90);
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(95);
				
				Thread.sleep(150);
			}
			else {
				l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-25"), -25);
			}
		}
		catch(NullPointerException npe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-26"), -26);
		}
		catch(InterruptedException ie){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-27"), -27);
		}
	}
}
