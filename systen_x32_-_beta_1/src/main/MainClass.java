
package main;

import runtimes.MainRuntimeClass;

/**
 * <DL> 
 * (german) Ausführbare Klasse zum Systen x32. Sie ruft die MainRuntimeClass auf und übergibt die Bootparameter.
 * 
 * <DT><B>______________________________</B></DT><br>
 *  
 * <DT><B>title:</B></DT> 
 * <DD>Systen x32</DD><br>
 * 
 * <DT><B>version:</B></DT>
 * <DD>-.-.----b1x32</DD><br>
 * 
 * <DT><B>author:</B></DT>
 * <DD>Xi soft | Arikan Mustafa, Tobner Daniel</DD><br>
 * 
 * <DT><B>requirements:</B></DT> 
 * <DD>Java Runtime Environment (v5.0.05)</DD>
 * <DD>Windows XP (SP2)</DD>
 * <DD>Windows XP Professional x64 Edition</DD>
 * <DD>Windows Vista (Beta 1)</DD>
 * <DD>Linux based System</DD>
 * <DD>Intel or AMD CPU (1000MHz)</DD>
 * <DD>256MB RAM</DD>
 * <DD>DirectX compatible Soundcard, Graphiccard, Mouse, Keyboard (Windows)</DD>
 * <DD>20MB free HDD Space</DD><br>
 * 
 * <DT><B>description (german):</B></DT> 
 * <DD>Systen x32 ist die neueste Entwicklung der Xi soft Engine Familie. Systen x32 bietet die Fähigkeiten eines
 * eigenständigen Betriebssystems, welches die Ressourcen des so genannten Master-OS benötigt. ...</DD>
 * </DL>
 */
public class MainClass {

	public static void main(String[] args){
		new MainRuntimeClass().main(args);
	}
}
