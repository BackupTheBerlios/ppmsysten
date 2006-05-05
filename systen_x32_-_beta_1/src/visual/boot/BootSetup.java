
package visual.boot;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;
import runtimes.boot.Boot;
import runtimes.boot.Prestart;

public class BootSetup extends JDialog implements KeyListener, MouseListener {

	private static final long serialVersionUID=1L;
	
	private JButton jbtnAccept=new JButton();
	
	private JButton jbtnExit=new JButton();
	
	private JButton jbtnAcSave=new JButton();
	
	private JLabel jlbl=new JLabel();
	
	private JCheckBox jchkbxAuralion=new JCheckBox();
	
	private JCheckBox jchkbxECoder=new JCheckBox();
	
	private JCheckBox jchkbxEFSx=new JCheckBox();

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
		
		jbtnAccept.setLocation(Integer.parseInt(getParameter("btnaxpos")), Integer.parseInt(getParameter("btnaypos")));
		jbtnAccept.setSize(90, 22);
		jbtnAccept.setText(getParameter("btnaname"));
		jbtnAccept.addKeyListener(this);
		jbtnAccept.addMouseListener(this);
		jbtnAccept.requestFocus();
		
		jbtnAcSave.setLocation(Integer.parseInt(getParameter("btnasxpos")), Integer.parseInt(getParameter("btnasypos")));
		jbtnAcSave.setSize(90, 22);	
		jbtnAcSave.setText(getParameter("btnasname"));
		jbtnAcSave.addKeyListener(this);
		jbtnAcSave.addMouseListener(this);
		
		jbtnExit.setLocation(Integer.parseInt(getParameter("btnexpos")), Integer.parseInt(getParameter("btneypos")));
		jbtnExit.setSize(90, 22);
		jbtnExit.setText(getParameter("btnename"));
		jbtnExit.addKeyListener(this);
		jbtnExit.addMouseListener(this);
		
		jlbl.setLocation(Integer.parseInt(getParameter("mdlsxpos"))+15, Integer.parseInt(getParameter("mdlsypos"))+10);
		jlbl.setSize(Integer.parseInt(getParameter("mdlsxsiz")), Integer.parseInt(getParameter("fsize")));		
		jlbl.setText(getParameter("mdlstxt"));
		
		jchkbxAuralion.setLocation(Integer.parseInt(getParameter("mdlsAurxpos")), Integer.parseInt(getParameter("mdlsAurypos")));
		jchkbxAuralion.setSize(Integer.parseInt(getParameter("mdlsAurxsiz")), Integer.parseInt(getParameter("mdlsAurysiz")));
		jchkbxAuralion.setText(getParameter("mdlsAurtxt"));
		
		jchkbxECoder.setLocation(Integer.parseInt(getParameter("mdlsEcxpos")), Integer.parseInt(getParameter("mdlsEcypos")));
		jchkbxECoder.setSize(Integer.parseInt(getParameter("mdlsEcxsiz")), Integer.parseInt(getParameter("mdlsEcysiz")));
		jchkbxECoder.setText(getParameter("mdlsEctxt"));
		
		jchkbxEFSx.setLocation(Integer.parseInt(getParameter("mdlsEfsxxpos")), Integer.parseInt(getParameter("mdlsEfsxypos")));
		jchkbxEFSx.setSize(Integer.parseInt(getParameter("mdlsEfsxxsiz")), Integer.parseInt(getParameter("mdlsEfsxysiz")));
		jchkbxEFSx.setText(getParameter("mdlsEfsxtxt"));
		
		getContentPane().add(jbtnAccept);
		getContentPane().add(jbtnExit);
		getContentPane().add(jbtnAcSave);
		getContentPane().add(jlbl);
		getContentPane().add(jchkbxAuralion);
		getContentPane().add(jchkbxECoder);
		getContentPane().add(jchkbxEFSx);
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent key){
		if((key.getSource().equals(jbtnExit))&&(key.getKeyCode()==KeyEvent.VK_ENTER)){
			setVisible(false);
			
			dispose();
		}
		if((key.getSource().equals(jbtnAcSave))&&(key.getKeyCode()==KeyEvent.VK_ENTER)){
			new Prestart().setPrestart(1);
			
			repaint();
		}
		if((key.getSource().equals(jbtnAccept))&&(key.getKeyCode()==KeyEvent.VK_ENTER)){
			new Prestart().setPrestart(1);
			
			setVisible(false);
			
			dispose();
		}
		else if((key.getSource().equals(jbtnExit))&&(key.getKeyCode()==KeyEvent.VK_RIGHT)){
			jbtnAcSave.requestFocus();
		}
		else if((key.getSource().equals(jbtnAccept))&&(key.getKeyCode()==KeyEvent.VK_LEFT)){
			jbtnAcSave.requestFocus();
		}
		else if((key.getSource().equals(jbtnAcSave))&&(key.getKeyCode()==KeyEvent.VK_LEFT)){
			jbtnExit.requestFocus();
		}
		else if((key.getSource().equals(jbtnAcSave))&&(key.getKeyCode()==KeyEvent.VK_RIGHT)){
			jbtnAccept.requestFocus();
		}
	}

	public void mouseClicked(MouseEvent mouse){
		if(mouse.getSource().equals(jbtnExit)){
			setVisible(false);
			
			dispose();
		}
		else if(mouse.getSource().equals(jbtnAcSave)){			
			new Prestart().setPrestart(1);
			
			repaint();
		}
		else if(mouse.getSource().equals(jbtnAccept)){			
			new Prestart().setPrestart(1);
			
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
			
//			setBackground(Color.white);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			g.setFont(new Font(getParameter("font"), Font.BOLD, Integer.parseInt(getParameter("fsize"))));
			g.setColor(Color.black);
			
			g.drawString(getParameter("text"), Integer.parseInt(getParameter("xtloc")), Integer.parseInt(getParameter("ytloc")));
		
			g.drawRect(Integer.parseInt(getParameter("mdlsxpos")), Integer.parseInt(getParameter("mdlsypos")), Integer.parseInt(getParameter("mdlsxsiz")), Integer.parseInt(getParameter("mdlsysiz")));
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
