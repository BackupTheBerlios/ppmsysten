
package visual.boot;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;
import runtimes.boot.Boot;

public class BootSetup extends JDialog implements KeyListener, MouseListener {

	private static final long serialVersionUID=1L;
	
	private JButton jbtnAccept=new JButton();

	public void loadBootSetup(){
		new Boot().setBootSetup(false);
		
		setTitle(getParameter("name"));
		setLocation(Integer.parseInt(getParameter("xloc")), Integer.parseInt(getParameter("yloc")));
		setSize(Integer.parseInt(getParameter("xsiz")), Integer.parseInt(getParameter("ysiz")));
		setAlwaysOnTop(true);
		setModal(true);
		setResizable(false);
		setUndecorated(true);
		setContentPane(new Jpnl());
		getContentPane().setLayout(null);
		
		jbtnAccept.setText(getParameter("btnaname"));
		jbtnAccept.setLocation(Integer.parseInt(getParameter("btnaxpos")), Integer.parseInt(getParameter("btnaypos")));
		jbtnAccept.setSize(90, 22);		
		jbtnAccept.addKeyListener(this);
		jbtnAccept.addMouseListener(this);
		jbtnAccept.requestFocus();

		getContentPane().add(jbtnAccept);		
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent key){
	}

	public void mouseClicked(MouseEvent mouse){
		if(mouse.getSource().equals(jbtnAccept)){
			setVisible(false);
			
			dispose();
		}
	}

	public void keyTyped(KeyEvent key){}	

	public void keyReleased(KeyEvent key){}
	
	public void mousePressed(MouseEvent mouse){}

	public void mouseReleased(MouseEvent mouse){}

	public void mouseEntered(MouseEvent mouse){}

	public void mouseExited(MouseEvent mouse){}
	
	private class Jpnl extends JPanel {
		
		private static final long serialVersionUID=1L;
		
		public Jpnl(){
			setOpaque(true);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
		}
	}
	
	public String getParameter(String param){
		Properties 	PF=new Properties();		
		Log			l=new Log();
		
		try {
			PF.load(new FileInputStream("cfg/visual/bootstp"+new SystenLib().getLng()+".sys"));
			
			return PF.getProperty(param);
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-65"), -65);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-66"), -66);
		}
		
		return "";
	}
}
