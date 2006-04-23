
package visual.blvlchooser;

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
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class BLvlChooser extends JDialog implements KeyListener, MouseListener {

	private static final long serialVersionUID=1L;
	
	private JButton jbtnExit=new JButton();
	
	private JButton jbtnAccept=new JButton();
	
	private JScrollPane jsp=null;
	
	private JList jlst=null;
	
	public void loadBLvlChooser(){
		SystenLib sl=new SystenLib();
		
		setTitle(getParameter("name"));
		setLocation(Integer.parseInt(getParameter("xloc")), Integer.parseInt(getParameter("yloc")));
		setSize(Integer.parseInt(getParameter("xsiz")), Integer.parseInt(getParameter("ysiz")));
		setAlwaysOnTop(true);
		setModal(true);
		setResizable(false);
		setUndecorated(true);
		setContentPane(new Jpnl());
		getContentPane().setLayout(null);
		
		int i=sl.getSessionNumber();
		
		String[] tmpsns=new String[i];
		
		for(int j=0; j<i-1; j++){
			if(sl.getSession(j, 2).equals("o")){
				tmpsns[j]="    "+sl.getSession(j, 0)+"    |    "+sl.getSession(j, 1);
			}			
		}
		
		jlst=new JList(tmpsns);
		
		jsp=new JScrollPane(jlst);
		jsp.setLocation(Integer.parseInt(getParameter("lstxloc")), Integer.parseInt(getParameter("lstyloc")));
		jsp.setSize(Integer.parseInt(getParameter("lstxsiz")), Integer.parseInt(getParameter("lstysiz")));

		jbtnExit.setText(getParameter("btnename"));
		jbtnExit.setLocation(Integer.parseInt(getParameter("btnexpos")), Integer.parseInt(getParameter("btneypos")));
		jbtnExit.setSize(90, 22);		
		jbtnExit.addKeyListener(this);					
		jbtnExit.addMouseListener(this);
		
		jbtnAccept.setText(getParameter("btnaname"));
		jbtnAccept.setLocation(Integer.parseInt(getParameter("btnaxpos")), Integer.parseInt(getParameter("btnaypos")));
		jbtnAccept.setSize(90, 22);		
		jbtnAccept.addKeyListener(this);
		jbtnAccept.addMouseListener(this);

		getContentPane().add(jsp);
		getContentPane().add(jbtnExit);
		getContentPane().add(jbtnAccept);
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent key){
	}
	
	public void mouseClicked(MouseEvent mouse){
		if(mouse.getSource().equals(jbtnExit)){
			setVisible(false);
			
			dispose();
		}
		else if((mouse.getSource().equals(jbtnAccept))&&(jlst.getSelectedIndex()!=-1)){
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
			
			g.setColor(Color.black);
			g.setFont(new Font(getParameter("font"), Font.PLAIN, Integer.parseInt(getParameter("fsize"))));		
			g.drawString(getParameter("title"), Integer.parseInt(getParameter("txpos")), Integer.parseInt(getParameter("typos")));
		}
	}

	public String getParameter(String param){
		Properties 	PF=new Properties();
		Log			l=new Log();

		try {
			PF.load(new FileInputStream("cfg/visual/blvl"+new SystenLib().getLng()+".sys"));

			return PF.getProperty(param);
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-63"), -63);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-64"), -64);
		}

		return "";
	}
}
