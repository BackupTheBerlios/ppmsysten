
package visual.endavor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import moduls.jcorex32.lib.SystenLib;
import moduls.jcorex32.lib.windows.HyperTHRONEWinLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class Desktop extends JFrame implements MouseListener {

	private static final long serialVersionUID=1L;
	
	private static JButton jbtn=new JButton("shutdown /now");
	
	private static int x=0;
	
	private static int y=0;
	
	public void loadDesktop(){
		SystenLib sl=new SystenLib();
		
		setTitle(getParameter("name"));
		setLocation(Integer.parseInt(getParameter("xloc")), Integer.parseInt(getParameter("yloc")));
		
		if(sl.getOsType()==0){
			HyperTHRONEWinLib htwl=new HyperTHRONEWinLib();
			
			x=htwl.getCurrentMode(0);
			y=htwl.getCurrentMode(1);
		}
/*		else if(sl.getOsType()==1){
			HyperTHRONELinLib htwl=new HyperTHRONELinLib();
			
			x=setSize(htll.getCurrentMode(0);
			y=htll.getCurrentMode(1);
		}*/	
		
		setSize(x, y);
		setAlwaysOnTop(true);
		setResizable(false);
		setUndecorated(true);
		setContentPane(new Jpnl());
		getContentPane().setLayout(null);
		
		jbtn.setLocation(20, 20);
		jbtn.setSize(130, 22);
		jbtn.addMouseListener(this);
		
		getContentPane().add(jbtn);		
		setVisible(true);
	}
	
	public void mouseClicked(MouseEvent mouse){
		setVisible(false);
			
		dispose();
		
		System.exit(0);
	}

	public void mousePressed(MouseEvent mouse){}
	
	public void mouseReleased(MouseEvent mouse){}
	
	public void mouseEntered(MouseEvent mouse){}
	
	public void mouseExited(MouseEvent mouse){}
	
	private class Jpnl extends JPanel {
		
		private static final long serialVersionUID=1L;
		
		public Jpnl(){
			setOpaque(true);
			setBackground(Color.black);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			String txt=getParameter("devtxt");
			
			txt=txt.replace("V", new SystenLib().getVersion());
			txt=txt.replace("!", new SystenLib().getAuralion(1));
			txt=txt.replace("�", new SystenLib().getCyra(1));
			txt=txt.replace("%", new SystenLib().getEFSx(1));
			txt=txt.replace("&", new SystenLib().getEndavor(1));			
			txt=txt.replace("/", new SystenLib().getCi(1));
			txt=txt.replace("=", new SystenLib().getEc(1));
			
			double lx=x*(Integer.parseInt(getParameter("devxloc"))/100.0);			
			double ly=y*(Integer.parseInt(getParameter("devyloc"))/100.0);
			
			int fnt=Integer.parseInt(getParameter("fsize"));
			
			g.setColor(Color.white);
			g.setFont(new Font(getParameter("font"), Font.BOLD, fnt));
			
			StringTokenizer st=new StringTokenizer(txt, "#");			
			
			g.drawString(st.nextElement().toString(), (int)lx, (int)ly);
			
			g.setFont(new Font(getParameter("font"), Font.PLAIN, fnt));
			
			int i=1;
			
			while(st.hasMoreElements()){
				g.drawString(st.nextElement().toString(), (int)lx, (int)ly+((i*fnt)+10));
				
				i++;
			}
		}
	}
	
	public String getParameter(String param){
		Properties 	PF=new Properties();		
		Log			l=new Log();
		
		try {
			PF.load(new FileInputStream("cfg/visual/desktop.sys"));
			
			return PF.getProperty(param);
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-61"), -61);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-62"), -62);
		}
		
		return "";
	}
}