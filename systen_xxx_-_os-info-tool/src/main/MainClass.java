
package main;

/**
 * <DL> 
 * (german) Dies ist die ausführbare Klasse des Programms, welche die ausführbare Methode enthält.
 * 
 * <DT><B>______________________________</B></DT><br>
 * 
 * <DT><B>title:</B></DT> 
 * <DD>Systen xXX: os-info-tool</DD><br>
 * 
 * <DT><B>version:</B></DT>
 * <DD>1.0.0001</DD><br>
 * 
 * <DT><B>author:</B></DT>
 * <DD>(c) 2006 Xi soft | Daniel Tobner. All rights reserved.</DD><br>
 * 
 * <DT><B>requirements:</B></DT> 
 * <DD>Java Runtime Environment</DD>
 * <DD>OS Konsole</DD><br>
 * 
 * <DT><B>description (german):</B></DT> 
 * <DD>Dieses auf Java basierende Tool liest Informationen, welche von Systen xXX benötigt werden vom System aus 
 * und zeigt diese 'nur' auf der Konsole an.</DD>
 * </DL>
 */
public class MainClass {

	/**
	 * (german) Die ausführbare Methode des Programms, welche die Daten ausliest und anzeigt.
	 * 
	 * @param args command line parameters (not implemented)
	 */
	public static void main(String[] args){
		System.out.println("(c) 2006 Xi soft.\n\t\t\tSysten xXX: 'OS-Info-Tool'\n");		
		
		System.out.println("os-name:\t "+System.getProperty("os.name"));
		System.out.println("os-version:\t "+System.getProperty("os.version"));
		System.out.println("os-architecture: "+System.getProperty("os.arch"));
	}
}
