
package visual.boot;

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

public class BootExitFrm extends JDialog implements KeyListener, MouseListener {

	private static final long serialVersionUID=1L;

	private JButton jbtnResume=new JButton();

	private JButton jbtnExit=new JButton();
	
	private Image back=getToolkit().getImage("rs/boot/befrm.png");
	
	private String msg="";
	
	private String title="";

	public BootExitFrm(String owner, String message){
		try {	
			msg=message;
			
			title=owner;
			
			MediaTracker mt=new MediaTracker(this);
			
			mt.addImage(back, 0);			
			mt.waitForID(0);

			setLocation(0, 0);
			setSize(320, 100);
			setTitle(title);
			setAlwaysOnTop(true);			
			setResizable(false);
			setUndecorated(true);
			setContentPane(new Jpnl(back));
			getContentPane().setLayout(null);			
			
			if(new SystenLib().getLng().equals(".de")){
				jbtnExit.setText("beenden");
			}
			else {
				jbtnExit.setText("shutdown");
			}
			
			jbtnExit.setLocation(220, 70);
			jbtnExit.setSize(90, 22);			
			jbtnExit.addKeyListener(this);
			jbtnExit.addMouseListener(this);
			
			if(new SystenLib().getLng().equals(".en")){
				jbtnResume.setText("resume");
			}
			else if(new SystenLib().getLng().equals(".de")){
				jbtnResume.setText("weiter");
			}
			
			jbtnResume.setLocation(110, 70);
			jbtnResume.setSize(90, 22);			
			jbtnResume.addKeyListener(this);
			jbtnResume.addMouseListener(this);		
			
			getContentPane().add(jbtnExit);
			getContentPane().add(jbtnResume);			
			setVisible(true);
		}
		catch(InterruptedException ie){
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("-40"), -40);
		}
	}

	public void keyPressed(KeyEvent key){
		if((key.getKeyCode()==KeyEvent.VK_ENTER)&&(key.getSource().equals(jbtnResume))){
			setVisible(false);
			
			dispose();
		}
		else if((key.getKeyCode()==KeyEvent.VK_ENTER)&&(key.getSource().equals(jbtnExit))){
			setVisible(false);
			
			dispose();
			
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("0"), 0);
		}
		else if((key.getKeyCode()==KeyEvent.VK_LEFT)&&(key.getSource().equals(jbtnExit))){
			jbtnResume.requestFocus();
		}
		else if((key.getKeyCode()==KeyEvent.VK_RIGHT)&&(key.getSource().equals(jbtnResume))){
			jbtnExit.requestFocus();
		}
	}

	public void mouseClicked(MouseEvent mouse){
		if((mouse.getButton()==1)&&(mouse.getSource().equals(jbtnResume))){
			setVisible(false);
			
			dispose();
		}
		else if((mouse.getButton()==1)&&(mouse.getSource().equals(jbtnExit))){			
			setVisible(false);
			
			dispose();
			
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("0"), 0);
		}
	}	

	public void keyReleased(KeyEvent key){}
	
	public void keyTyped(KeyEvent key){}
	
	public void mouseReleased(MouseEvent mouse){}
	
	public void mousePressed(MouseEvent mouse){}
	
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

			jbtnExit.requestFocus();
		}
	}
}
