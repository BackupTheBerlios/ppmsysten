
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import moduls.jcorex32.lib.SystenLib;
import moduls.loader06.ErrorCode;
import moduls.log.Log;

public class BootFrm extends JFrame implements KeyListener, MouseListener, Runnable {
	
	private static final long serialVersionUID=1L;
	
	private static int line=0;
	
	private static int curLine=0;
	
	private static String[][] txt=null;
	
	private static int curPos=0;
	
	private static Log l=new Log();
	
	private JButton jbtn=new JButton();
	
	private JTextField jtxtfld=new JTextField();	
	
	private JProgressBar jpgbrFull=new JProgressBar();
	
	private JProgressBar jpgbrAction=new JProgressBar();
	
	private Image back=getToolkit().getImage("rs/boot/bootfrm.png");
	
	private MediaTracker mt=new MediaTracker(this);

	public void loadBootFrm(){
		try {		
			int i=Integer.parseInt(getBootFrm("fsize"));
			
			if(i<12){
				i=12;
			}
			
			line=(Integer.parseInt(getBootFrm("yepos"))-Integer.parseInt(getBootFrm("yapos")))/(i-1);

			txt=new String[line][3];
			
			mt.addImage(back, 0);			
			mt.waitForID(0);
			
			setTitle(getBootFrm("name")+" | (v"+new SystenLib().getVersion()+")");
			setLocation(Integer.parseInt(getBootFrm("xloc")), Integer.parseInt(getBootFrm("yloc")));
			setSize(Integer.parseInt(getBootFrm("xsiz")), Integer.parseInt(getBootFrm("ysiz")));
			setAlwaysOnTop(true);
			setResizable(false);
			setUndecorated(true);
			setContentPane(new Jpnl(back));
			getContentPane().setLayout(null);
			
			jtxtfld.setLocation(Integer.parseInt(getBootFrm("txtfldx")), Integer.parseInt(getBootFrm("txtfldy")));
			jtxtfld.setSize(Integer.parseInt(getBootFrm("txtfldxsiz")), Integer.parseInt(getBootFrm("txtfldysiz")));
			jtxtfld.setFont(new Font("Arial", 0, 12));
			jtxtfld.setEditable(false);
			jtxtfld.addKeyListener(this);
			jtxtfld.requestFocus();
			
			if(new SystenLib().getLng().equals(".de")){
				jbtn.setText("beenden");
			}
			else {
				jbtn.setText("shutdown");
			}
			
			jbtn.setLocation(Integer.parseInt(getBootFrm("btnx")), Integer.parseInt(getBootFrm("btny")));
			jbtn.setSize(Integer.parseInt(getBootFrm("btnxsiz")), Integer.parseInt(getBootFrm("btnysiz")));			
			jbtn.addKeyListener(this);
			jbtn.addMouseListener(this);
			
			jpgbrFull.setLocation(Integer.parseInt(getBootFrm("pgbrfx")), Integer.parseInt(getBootFrm("pgbrfy")));
			jpgbrFull.setSize(Integer.parseInt(getBootFrm("pgbrfxsiz")), Integer.parseInt(getBootFrm("pgbrfysiz")));
			jpgbrFull.setMinimum(0);
			jpgbrFull.setMaximum(100);
			
			jpgbrAction.setLocation(Integer.parseInt(getBootFrm("pgbrax")), Integer.parseInt(getBootFrm("pgbray")));
			jpgbrAction.setSize(Integer.parseInt(getBootFrm("pgbraxsiz")), Integer.parseInt(getBootFrm("pgbraysiz")));
			jpgbrAction.setMinimum(0);
			jpgbrAction.setMaximum(100);
			
			getContentPane().add(jtxtfld);
			getContentPane().add(jbtn);
			getContentPane().add(jpgbrFull);
			getContentPane().add(jpgbrAction);
			setVisible(true);
		}
		catch(InterruptedException ie){
			new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("-40"), -40);
		}
		catch(NumberFormatException nfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-38"), -38);
		}
	}
	
	public String getBootFrm(String code){
		Properties SY=new Properties();
		
		try {
			SY.load(new FileInputStream("cfg/visual/bootfrm"+new SystenLib().getLng()+".sys"));
			
			return SY.getProperty(code);
		}
		catch(FileNotFoundException fnfe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-37"), -37);
		}
		catch(IOException ioe){
			l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-38"), -38);
		}
		
		return "";
	}
	
	public void setTxt(String msg){
		try {
			int i=0, j=0;
			
			if(curLine>=line-1){
				for(i=0; i<line-1; i++){
					for(j=0; j<3; j++){
						txt[i][j]=txt[i+1][j];
					}
				}
				
				txt[line-1][0]=null;
				txt[line-1][1]=null;
				txt[line-1][2]=null;
				
				curLine=line-2;
			}
			
			if(msg.length()>3){
				String str="";
				
				if(msg.substring(0, 3).equals("##l")){
					if(txt[curLine][0]!=null){
						curLine++;
					}
					
					str=msg.substring(3, msg.length());
					
					i=0;
					
					curPos=0;
				}
				else if(msg.substring(0, 3).equals("#lj")){
					str=txt[curLine][0]+msg.substring(3, msg.length());
					
					i=0;
				}
				else if(msg.substring(0, 3).equals("##m")){
					str=msg.substring(3, msg.length());
					
					i=2;
					
					curPos=100;
				}
				else if(msg.substring(0, 3).equals("##r")){
					str=msg.substring(3, msg.length());
					
					i=1;
				}
				
				if(str.length()>3){
					if(str.substring(str.length()-3, str.length()).equals("##n")){
						txt[curLine][i]=str.substring(0, str.length()-3);
						txt[curLine+1][0]="";
							
						curLine++;
					}
					else {
						txt[curLine][i]=str;
						
						if(i==1){
							curLine++;
						}
					}
				}
				else {
					txt[curLine][i]=str;
						
					if(i==1){
						curLine++;
					}
				}
				
				repaint();
				
				if(curLine>=line-1){
					for(i=0; i<line-1; i++){
						for(j=0; j<3; j++){
							txt[i][j]=txt[i+1][j];
						}
					}
					
					txt[line-1][0]=null;
					txt[line-1][1]=null;
					txt[line-1][2]=null;
					
					curLine=line-2;
				}
			}
		}
		catch(StringIndexOutOfBoundsException sioobe){
		}
		catch(ArrayIndexOutOfBoundsException aioobe){
		}
	}
	
	public void lockField(boolean x){
		jtxtfld.setEditable(x);
		jtxtfld.requestFocus();
	}
	
	public void setFullPos(int x){
		jpgbrFull.setValue(x);
	}
	
	public void keyPressed(KeyEvent key){
		if((key.getKeyCode()==KeyEvent.VK_ENTER)&&(key.getSource().equals(jbtn))){			
			if(new SystenLib().getLng().equals(".de")){
				new BootExitFrm("SHUTDOWN ANFRAGE", "beenden oder weiter?");
			}
			else {
				new BootExitFrm("SHUTDOWN REQUEST", "shutdown or resume?");
			}
			
			if(jtxtfld.isEditable()){			
				jtxtfld.requestFocus();
			}
			else {
				jbtn.requestFocus();
			}
		}		
		else if((key.getKeyCode()==KeyEvent.VK_ENTER)&&(key.getSource().equals(jtxtfld))&&(jtxtfld.isEditable())){
			String cmd=jtxtfld.getText();
			
			if(cmd.length()>0){
				setTxt("##l"+cmd);
				
				jtxtfld.setText("");
				
				if((cmd.equals("exit"))||(cmd.equals("shutdown"))||(cmd.equals("leave"))){
					setTxt("##m setting request for SHUTDOWN");			
					
					new BootExitFrm("SHUTDOWN REQUEST", "shutdown or resume?");
				}
				else if(cmd.equals("shutdown /now")){
					new Log().log(this.getClass().getName(), new ErrorCode().getErrorCode("0"), 0);
				}
				else {
					setTxt("##m NOT IMPLEMENTED");
				}
				
				jtxtfld.requestFocus();
			}
			else {
				jtxtfld.requestFocus();
				
				return;
			}
		}
	}
	
	public void mouseClicked(MouseEvent mouse){
		if((mouse.getButton()==1)&&(mouse.getSource().equals(jbtn))){			
			if(new SystenLib().getLng().equals(".de")){
				new BootExitFrm("SHUTDOWN ANFRAGE", "beenden oder weiter?");
			}
			else {
				new BootExitFrm("SHUTDOWN REQUEST", "shutdown or resume?");
			}
		}
	}
	
	public void keyTyped(KeyEvent key){}
	
	public void keyReleased(KeyEvent key){}

	public void mousePressed(MouseEvent mouse){}
	
	public void mouseReleased(MouseEvent mouse){}
	
	public void mouseEntered(MouseEvent mouse){}
	
	public void mouseExited(MouseEvent mouse){}
	
	public void run(){
		while(true){
			curPos++;
			
			jpgbrAction.setValue(curPos);
			
			try {
				Thread.sleep(500);
			}
			catch(InterruptedException ie){
				curPos=0;
				
				Thread.currentThread().interrupt();
			}
		}		
	}
	
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
			
			int x=0, y=0, fontsize=Integer.parseInt(getBootFrm("fsize"));
			
			if(fontsize<12){
				fontsize=12;
			}
			try {
				g.setColor(Color.black);
				
				g.setFont(new Font(getBootFrm("font"), Font.PLAIN, Integer.parseInt(getBootFrm("pgbrfsiz"))));			
				g.drawString(getBootFrm("pgbrftxt"), Integer.parseInt(getBootFrm("pgbrftxtx")), Integer.parseInt(getBootFrm("pgbrftxty")));
				
				g.setFont(new Font(getBootFrm("font"), Font.PLAIN, Integer.parseInt(getBootFrm("pgbrasiz"))));			
				g.drawString(getBootFrm("pgbratxt"), Integer.parseInt(getBootFrm("pgbratxtx")), Integer.parseInt(getBootFrm("pgbratxty")));
			
				for(int i=0; i<=curLine; i++){
					for(int j=0; j<3; j++){
						String xpos="xapos", str=txt[i][j];
						
						if(j==0){
							g.setFont(new Font(getBootFrm("font"), Font.PLAIN, fontsize));
							
							g.setColor(Color.black);
						}
						else if(j==1){
							g.setFont(new Font(getBootFrm("font"), Font.BOLD, fontsize));
							
							g.setColor(Color.green);
							
							xpos="xrpos";
						}
						else if(j==2){
							g.setFont(new Font(getBootFrm("font"), Font.PLAIN, fontsize));
							
							g.setColor(Color.blue);
							
							xpos="xmpos";
						}
						
						y=Integer.parseInt(getBootFrm("yapos"))+(i*fontsize);			
						x=Integer.parseInt(getBootFrm(xpos));
						
						if(str!=null){
							g.drawString(str, x, y);
						}
					}
				}
			}
			catch(StringIndexOutOfBoundsException sioobe){
			}
			catch(NullPointerException npe){
			}
			catch(NumberFormatException nfe){
				l.log(this.getClass().getName(), new ErrorCode().getErrorCode("-38"), -38);
			}
		}
	}
}