
package visual.boot;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

import runtimes.boot.Boot;

public class BootSetup extends JDialog implements KeyListener, MouseListener {

	private static final long serialVersionUID=1L;
	
	private JDesktopPane jdp=new JDesktopPane();
	
	private JButton jbtn=new JButton();

	public void loadBootSetup(){
		new Boot().setBootSetup(false);
		
		setLocation(0, 0);
		setSize(640, 480);
		setTitle("Systen_x32_(BOOTSETUP)");
		setUndecorated(true);
		setResizable(false);
		setAlwaysOnTop(true);
		setModal(true);
		addKeyListener(this);
		requestFocus();
		
		jbtn.setLocation(220, 70);
		jbtn.setSize(90, 22);
		jbtn.setText("exit");
		jbtn.addKeyListener(this);
		jbtn.addMouseListener(this);
		
		jdp.setBackground(Color.black);		
		
		getContentPane().add(jdp);
		
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent key){
		if(key.getKeyCode()==KeyEvent.VK_ESCAPE){
			setVisible(false);
			
			dispose();
		}
	}

	public void mouseClicked(MouseEvent mouse){
	}

	public void keyTyped(KeyEvent key){}	

	public void keyReleased(KeyEvent key){}
	
	public void mousePressed(MouseEvent mouse){}

	public void mouseReleased(MouseEvent mouse){}

	public void mouseEntered(MouseEvent mouse){}

	public void mouseExited(MouseEvent mouse){}
}
