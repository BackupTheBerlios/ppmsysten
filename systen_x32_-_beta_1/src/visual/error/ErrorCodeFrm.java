
package visual.error;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class ErrorCodeFrm extends JDialog implements KeyListener, MouseListener {

	private static final long serialVersionUID=1L;
	
	private JButton jbtn=new JButton();
	
	private Image back=getToolkit().getImage("rs/errorcode/ecfrm.png");
	
	private MediaTracker mt=new MediaTracker(this);
	
	private String msg="";
	
	private String title="";

	public void ECFError(String message){
		SystenLib sl=new SystenLib();
		
		try {		
			msg=message;			
			
			if(new SystenLib().getLng().equals(".de")){
				if(sl.getErrorLvl()==0){
					title="Systen_x32:_#_"+sl.getErrorLvl();
				}
				else {
					title="Systen_x32_(FEHLER):_#_"+sl.getErrorLvl();
				}
			}
			else {
				if(sl.getErrorLvl()==0){
					title="Systen_x32:_#_"+sl.getErrorLvl();
				}
				else {
					title="Systen_x32_(ERROR):_#_"+sl.getErrorLvl();
				}				
			}
			
			mt.addImage(back, 0);
			mt.waitForID(0);
			
			setTitle(title);
			setLocation(0, 0);
			setSize(320, 100);
			setAlwaysOnTop(true);
			setModal(true);
			setResizable(false);
			setUndecorated(true);
			setContentPane(new Jpnl(back));
			getContentPane().setLayout(null);
			
			if(new SystenLib().getLng().equals(".de")){
				jbtn.setText("schlieﬂen");
			}
			else {
				jbtn.setText("close");
			}
			
			jbtn.setLocation(220, 70);
			jbtn.setSize(90, 22);		
			jbtn.addKeyListener(this);
			jbtn.addMouseListener(this);
			
			getContentPane().add(jbtn);
			setVisible(true);
		}
		catch(InterruptedException ie){
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("-40"), -40);
		}
	}

	public void ECFMsg(String message){
		try {		
			msg=message;			
			
			if(new SystenLib().getLng().equals(".de")){
				title="Systen_x32_(NACHRICHT)";
			}
			else {
				title="Systen_x32_(MESSAGE)";
			}
			
			mt.addImage(back, 0);			
			mt.waitForID(0);
			
			setTitle(title);
			setLocation(0, 0);
			setSize(320, 100);
			setAlwaysOnTop(true);
			setModal(true);
			setResizable(false);
			setUndecorated(true);
			setContentPane(new Jpnl(back));
			getContentPane().setLayout(null);
			
			if(new SystenLib().getLng().equals(".de")){
				jbtn.setText("weiter");
			}
			else {
				jbtn.setText("resume");
			}
			
			jbtn.setLocation(220, 70);
			jbtn.setSize(90, 22);			
			jbtn.addKeyListener(this);
			jbtn.addMouseListener(this);
			jbtn.requestFocus();
			
			getContentPane().add(jbtn);
			setVisible(true);
		}
		catch(InterruptedException ie){
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("-40"), -40);
		}
	}
	
	public void keyPressed(KeyEvent key){
		if((key.getKeyCode()==KeyEvent.VK_ENTER)&&(key.getSource().equals(jbtn))){
			setVisible(false);
			
			dispose();
		}		
	}
	
	public void mouseClicked(MouseEvent mouse){
		if((mouse.getButton()==1)&&(mouse.getSource().equals(jbtn))){
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
		
		private Image jpnlback=null;
		
		public Jpnl(Image x){
			setOpaque(true);
			setBackground(Color.black);
			
			jpnlback=x;
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			g.drawImage(jpnlback, 0, 0, null);
				
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 14));
			g.drawString(title, 10, 15);
				
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.BOLD, 12));		
			g.drawString(msg, 10, 53);
				
			jbtn.requestFocus();
		}
	}
}
