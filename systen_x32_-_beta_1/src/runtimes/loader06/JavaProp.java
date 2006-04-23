
package runtimes.loader06;

import moduls.jcorex32.lib.JavaLib;

public class JavaProp {
	
	public void loadJavaProperties(){
		JavaLib jl=new JavaLib();
		
		jl.setPath(System.getProperty("user.dir"));		
		jl.setJVersion(System.getProperty("java.version"));
		jl.setJVendor(System.getProperty("java.vendor"));
		jl.setJPath(System.getProperty("java.home"));
		jl.setJVmSpVersion(System.getProperty("java.vm.specification.version"));
		jl.setJVmSpVendor(System.getProperty("java.vm.specification.vendor"));
		jl.setJVmSpName(System.getProperty("java.vm.specification.name"));
		jl.setJVmVersion(System.getProperty("java.vm.version"));
		jl.setJVmVendor(System.getProperty("java.vm.vendor"));
		jl.setJVmName(System.getProperty("java.vm.name"));
		jl.setJSpVersion(System.getProperty("java.specification.version"));
		jl.setJSpVendor(System.getProperty("java.specification.vendor"));
		jl.setJSpName(System.getProperty("java.specification.name"));
		jl.setJClVersion(System.getProperty("java.class.version"));
		jl.setJClPath(System.getProperty("java.class.path"));
		jl.setJLiPath(System.getProperty("java.library.path"));
		jl.setJIoTempDir(System.getProperty("java.io.tmpdir"));
		jl.setJExDirs(System.getProperty("java.ext.dirs"));
		jl.setOsName(System.getProperty("os.name"));
		jl.setOsArch(System.getProperty("os.arch"));
		jl.setOsVersion(System.getProperty("os.version"));		
		jl.setFiSeperator(System.getProperty("file.separator"));
		jl.setUsName(System.getProperty("user.name"));
		jl.setUsHome(System.getProperty("user.home"));
	}
}
