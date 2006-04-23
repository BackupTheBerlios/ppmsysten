
package runtimes.boot;

import java.io.File;

import moduls.auralion.Auralion;
import moduls.cyra.Cyra;
import moduls.efsx.EFSx;
import moduls.hyperthrone.HyperTHRONE;
import moduls.hyperthrone.endavor.Endavor;
import moduls.jcorex32.ci.Ci;
import moduls.jcorex32.ecoderx32.ECoder;
import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;
import visual.blvlchooser.BLvlChooser;
import visual.boot.BootFrm;
import visual.boot.BootScreen;
import visual.boot.BootSetup;

public class Boot {
	
	private static boolean bootSetup=false;
	
	private static boolean resume=false;
	
	private Log l=new Log();
	
	public void loadLevel(String[] args){
		SystenLib 	sl=new SystenLib();
		Ci			ci=new Ci();
		BootFrm		bf=new BootFrm();
		Thread 		bft=new Thread(bf);
		EFSx		efsx=new EFSx();
		
		try {
			BootScreen bs=new BootScreen();
			
			Thread bst=new Thread(bs);
			
			while(!resume){
				bs.loadBootScreen();
				
				bst.start();

				bst.join();
				
				if(bootSetup){
					bst.interrupt();
					
					new BootSetup().loadBootSetup();
					
					bst=new Thread(bs);
				}
				else if(!resume){
					bs.dispose();
					
					break;
				}
			}
			
			// Methode vordefinierten START
			
			Thread.sleep(750);
			
			new ErrorCode().loadErrorCode();
			
			ci.loadCI();
			
			bf.loadBootFrm();
			
			bft.start();
			
			bf.setTxt("##l"+ci.getGlobalMod(1)+" "+ci.getTxt("001"));
			bf.setFullPos(5);
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000")+"##n");
			bf.setFullPos(7);
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("002"));
			bf.setFullPos(10);
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000"));
			bf.setFullPos(12);
			
			Thread.sleep(150);			
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("003"));
			bf.setFullPos(15);
			
			setLauncherParameter(args);
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000"));
			bf.setFullPos(17);
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("023"));
			bf.setFullPos(18);
			
			new ECoder().loadECoder();
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000"));
			bf.setFullPos(19);
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("018")+"##n");
			bf.setFullPos(20);		
			
			Thread.sleep(200);
			
			if(sl.getBootLvl()!=-1){		
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("020"));
				bf.setFullPos(21);
				
				efsx.loadEFSx();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000"));
				bf.setFullPos(23);
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("021"));
				bf.setFullPos(24);
				
				efsx.checkSession();
				
				Thread.sleep(500);
				
				bf.setTxt("##r"+ci.getTxt("000")+"##n");
				bf.setFullPos(25);
				
				Thread.sleep(150);
				
				bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("024"));
				bf.setFullPos(27);
				
				boolean isOpen=efsx.checkSession();
				
				if(isOpen){
					bf.setTxt("##m"+ci.getTxt("sop")+"##n");
					
					Thread.sleep(100);
					
					new BLvlChooser().loadBLvlChooser();
				}
				else {
					bf.setTxt("##m"+ci.getTxt("scl")+"##n");
				}
				
				Thread.sleep(500);
			}
			else if(sl.getBootLvl()==-1){
				sl.setTmpPath(System.getProperty("java.io.tmpdir"));
				
				bf.setTxt("##l"+ci.getGlobalMod(3)+" "+ci.getTxt("022"));
				bf.setFullPos(22);
				bf.setTxt("##m"+ci.getTxt("bldis")+"##n");
				
				Thread.sleep(500);
			}
			
			bf.setTxt("##l"+ci.getGlobalMod(3)+" "+ci.getTxt("019"));
			bf.setFullPos(28);
			bf.setTxt("##m"+ci.getTxt("bl"+sl.getBootLvl())+"##n");	
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("004")+" "+sl.getBootLvl()+"##n");
			bf.setFullPos(30);
			
			Thread.sleep(200);
			
			if((sl.getBootLvl()==0)||(sl.getBootLvl()==-1)){				
				new BootZero().bootZero(bf);
			}
			else if(sl.getBootLvl()==1){
				new BootOne().bootOne(bf);
			}
			else {
				bft.interrupt();
				
				bf.setFullPos(0);
				
				l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-39"), -39);
			}
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("031"));
			bf.setFullPos(95);
			
			new Auralion().loadAuralion();
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000"));
			bf.setFullPos(96);
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("032"));
			bf.setFullPos(96);
			
			new Cyra().loadCyra();
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000"));
			bf.setFullPos(97);
			
			Thread.sleep(150);			
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("033"));
			bf.setFullPos(96);
			
			new HyperTHRONE().loadHyperTHRONE();
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000"));
			bf.setFullPos(97);
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("034"));
			bf.setFullPos(96);
			
			new Endavor().loadEndavor();
			
			Thread.sleep(500);
			
			bf.setTxt("##r"+ci.getTxt("000"));
			bf.setFullPos(97);
			
			Thread.sleep(150);
			
			bf.setTxt("##l"+ci.getGlobalMod(2)+" "+ci.getTxt("035"));
			bf.setFullPos(96);
			
			Thread.sleep(1500);
			
			bft.interrupt();

			bf.setVisible(false);
			bf.dispose();
		}
		catch(InterruptedException ie){}
	}
	
	public void setLauncherParameter(String[] args){
		SystenLib sl=new SystenLib();
		
		try {
			sl.setLaParam1(args[0]);
		
			sl.setLaParam2(args[1]);
			
			sl.setLaParam3(args[2]);			
			
			if(!new File(sl.getLaParam3()).exists()){
				l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-4"), -4);
			}
		}
		catch(ArrayIndexOutOfBoundsException aioobe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-4"), -4);
		}
	}
	
	public void setBootSetup(boolean x){
		bootSetup=x;
	}
	
	public void setResume(){
		resume=true;
	}
}
