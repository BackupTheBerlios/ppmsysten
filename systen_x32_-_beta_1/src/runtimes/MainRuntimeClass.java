
package runtimes;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

import org.apache.log4j.PropertyConfigurator;

import runtimes.boot.Boot;
import visual.endavor.Desktop;

public class MainRuntimeClass {

	public void main(String[] args){
		PropertyConfigurator.configure("conf/log4j.properties");
		
		if(args.length!=0){
			new Boot().loadLevel(args);
			
			System.gc();
			
			new Desktop().loadDesktop();
		}
		else {
			new Log().log(this.getClass().getName(), "BOOT PARAMETER: error detected", -3);
		}
	}
}
