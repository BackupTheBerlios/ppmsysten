
package visual.boot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;
import runtimes.boot.Boot;

public class BootScreen extends JFrame implements KeyListener, Runnable {

	private static final long serialVersionUID=1L;
	
	private Image back=getToolkit().getImage("rs/boot/bscrn.png");
	
	private Image label=getToolkit().getImage("rs/boot/label"+new SystenLib().getLng()+".png");

	private int count=10;
	
	public void loadBootScreen(){		
		try {		
			count=10;
			
			MediaTracker mt=new MediaTracker(this);
			
			mt.addImage(back, 0);		
			mt.waitForID(0);
			
			mt.addImage(label, 1);			
			mt.waitForID(1);
			
			setTitle("Systen_x32_(BOOTSCREEN)");
			setLocation(0, 0);
			setSize(640, 480);
			setAlwaysOnTop(true);			
			setResizable(false);
			setUndecorated(true);
			addKeyListener(this);
			setContentPane(new Jpnl(back, this, label));
			getContentPane().setLayout(null);
			setVisible(true);
		}
		catch(InterruptedException ie){
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("-40"), -40);
		}
	}

	public void keyPressed(KeyEvent key){
		if(key.getKeyCode()==KeyEvent.VK_ENTER){
			new Boot().setResume();
			
			setVisible(false);
			
			dispose();
			
			count=1;
		}
		else if(key.getKeyCode()==KeyEvent.VK_ESCAPE){			
			new Boot().setBootSetup(true);
			
			setVisible(false);
			
			dispose();
			
			count=1;
		}
	}
	
	public void keyTyped(KeyEvent key){}

	public void keyReleased(KeyEvent key){}

	public void run(){
		try {
			while(count>=1){
				Thread.sleep(1000);
				
				if(Thread.currentThread().isInterrupted())
			        break;
				
				count--;
				
				repaint();
			}
		}
		catch(InterruptedException ie){
			Thread.currentThread().interrupt();
		}		
	}
	
	private class Jpnl extends JPanel {
		
		private static final long serialVersionUID=1L;
		
		private Image jpnlback=null;
		
		private Image backlabel=null;
		
		private JFrame jfrm=null;
		
		public Jpnl(Image x, JFrame y, Image z){
			setOpaque(true);
			setBackground(Color.black);
			
			jpnlback=x;
			
			jfrm=y;
			
			backlabel=z;
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);

			g.drawImage(jpnlback, 0, 0, null);
			
			g.drawImage(backlabel, 15, 423, null);
				
			String cnt=Integer.toString(count);
				
			if(count<10){
				cnt="0"+cnt;
			}	

			g.setFont(new Font("Arial", Font.BOLD, 16));
				
			g.setColor(Color.black);	
				
			g.drawString(cnt, 440, 444);
				
			jfrm.requestFocus();
		}
	}
}
