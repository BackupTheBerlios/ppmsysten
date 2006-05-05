
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
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
	
	private JButton jbtnDefault=new JButton();
	
	private JLabel jlblMdls=new JLabel();
	
	private JLabel jlblSns=new JLabel();
	
	private JLabel jlblSnsC=new JLabel();
	
	private JLabel jlblBl=new JLabel();
	
	private JLabel jlblLng=new JLabel();
	
	private JCheckBox jchkbxAuralion=new JCheckBox();
	
	private JCheckBox jchkbxCyra=new JCheckBox();
	
	private JCheckBox jchkbxECoder=new JCheckBox();
	
	private JCheckBox jchkbxEFSx=new JCheckBox();
	
	private JCheckBox jchkbxEndavor=new JCheckBox();
	
	private JCheckBox jchkbxHyperTHRONE=new JCheckBox();
	
	private JRadioButton jrbtnSnsYes=new JRadioButton();
	
	private JRadioButton jrbtnSnsNo=new JRadioButton();
	
	private JRadioButton jrbtnSnsCYes=new JRadioButton();
	
	private JRadioButton jrbtnSnsCNo=new JRadioButton();
	
	private JRadioButton jrbtnBlRaw=new JRadioButton();
	
	private JRadioButton jrbtnBlZero=new JRadioButton();
	
	private JRadioButton jrbtnLngE=new JRadioButton();
	
	private JRadioButton jrbtnLngG=new JRadioButton();
	
	private String msg="msgTxt";

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
		
		initComponents();
		
		getContentPane().add(jbtnAccept);
		getContentPane().add(jbtnExit);
		getContentPane().add(jbtnAcSave);
		getContentPane().add(jbtnDefault);
		getContentPane().add(jlblMdls);
		getContentPane().add(jchkbxAuralion);
		getContentPane().add(jchkbxCyra);
		getContentPane().add(jchkbxECoder);
		getContentPane().add(jchkbxEFSx);
		getContentPane().add(jchkbxEndavor);
		getContentPane().add(jchkbxHyperTHRONE);		
		getContentPane().add(jlblSns);		
		getContentPane().add(jrbtnSnsYes);		
		getContentPane().add(jrbtnSnsNo);
		getContentPane().add(jlblSnsC);		
		getContentPane().add(jrbtnSnsCYes);		
		getContentPane().add(jrbtnSnsCNo);		
		getContentPane().add(jlblBl);		
		getContentPane().add(jrbtnBlRaw);		
		getContentPane().add(jrbtnBlZero);		
		getContentPane().add(jlblLng);		
		getContentPane().add(jrbtnLngE);		
		getContentPane().add(jrbtnLngG);
		
		getCfg();
		
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
		else if(mouse.getSource().equals(jbtnDefault)){		
			new Prestart().setPrestart(0);
			
			getCfg();
			
			msg="msgDeftxt";
			
			initComponents();
			
			repaint();
		}
		else if(mouse.getSource().equals(jbtnAcSave)){
			putCfg();
			
			new Prestart().setPrestart(2);
			
			msg="msgSavtxt";
			
			initComponents();
			
			repaint();
		}
		else if(mouse.getSource().equals(jbtnAccept)){
			putCfg();

			setVisible(false);
			
			dispose();
		}
		else if((mouse.getSource().equals(jchkbxECoder))&&(!jchkbxECoder.isSelected())){
			jchkbxEFSx.setSelected(false);
			jrbtnBlRaw.setSelected(true);
			jrbtnBlZero.setSelected(false);
		}
		else if((mouse.getSource().equals(jchkbxEFSx))&&(!jchkbxEFSx.isSelected())){
			jrbtnBlRaw.setSelected(true);
			jrbtnBlZero.setSelected(false);
		}
		else if((mouse.getSource().equals(jchkbxEFSx))&&(jchkbxEFSx.isSelected())){
			jchkbxECoder.setSelected(true);
			jrbtnBlRaw.setSelected(false);
			jrbtnBlZero.setSelected(true);
		}
		else if((mouse.getSource().equals(jrbtnBlZero))&&(!jrbtnBlZero.isSelected())){
			jrbtnBlRaw.setSelected(true);
			jrbtnBlZero.setSelected(false);
		}
		else if((mouse.getSource().equals(jrbtnBlZero))&&(jrbtnBlZero.isSelected())){
			jrbtnBlRaw.setSelected(false);
			jrbtnBlZero.setSelected(true);
			jchkbxEFSx.setSelected(true);
			jchkbxECoder.setSelected(true);
		}
		else if((mouse.getSource().equals(jrbtnBlRaw))&&(!jrbtnBlRaw.isSelected())){
			jrbtnBlRaw.setSelected(false);
			jrbtnBlZero.setSelected(true);
		}
		else if((mouse.getSource().equals(jrbtnBlRaw))&&(jrbtnBlRaw.isSelected())){
			jrbtnBlRaw.setSelected(true);
			jrbtnBlZero.setSelected(false);
			jchkbxEFSx.setSelected(false);
		}		
		else if((mouse.getSource().equals(jchkbxHyperTHRONE))&&(!jchkbxHyperTHRONE.isSelected())){
			jchkbxEndavor.setSelected(false);
			jchkbxHyperTHRONE.setSelected(false);
		}
		else if((mouse.getSource().equals(jchkbxEndavor))&&(jchkbxEndavor.isSelected())){
			jchkbxEndavor.setSelected(true);
			jchkbxHyperTHRONE.setSelected(true);
		}
		else if((mouse.getSource().equals(jrbtnLngE))&&(jrbtnLngE.isSelected())){
			jrbtnLngE.setSelected(true);
			jrbtnLngG.setSelected(false);
		}
		else if((mouse.getSource().equals(jrbtnLngE))&&(!jrbtnLngE.isSelected())){
			jrbtnLngE.setSelected(false);
			jrbtnLngG.setSelected(true);
		}
		else if((mouse.getSource().equals(jrbtnLngG))&&(jrbtnLngG.isSelected())){
			jrbtnLngE.setSelected(false);
			jrbtnLngG.setSelected(true);
		}
		else if((mouse.getSource().equals(jrbtnLngG))&&(!jrbtnLngG.isSelected())){
			jrbtnLngE.setSelected(true);
			jrbtnLngG.setSelected(false);
		}
		else if((mouse.getSource().equals(jrbtnSnsYes))&&(jrbtnSnsYes.isSelected())){
			jrbtnSnsYes.setSelected(true);
			jrbtnSnsNo.setSelected(false);
		}
		else if((mouse.getSource().equals(jrbtnSnsYes))&&(!jrbtnSnsYes.isSelected())){
			jrbtnSnsYes.setSelected(false);
			jrbtnSnsNo.setSelected(true);
		}
		else if((mouse.getSource().equals(jrbtnSnsNo))&&(jrbtnSnsNo.isSelected())){
			jrbtnSnsYes.setSelected(false);
			jrbtnSnsNo.setSelected(true);
		}
		else if((mouse.getSource().equals(jrbtnSnsNo))&&(!jrbtnSnsNo.isSelected())){
			jrbtnSnsYes.setSelected(true);
			jrbtnSnsNo.setSelected(false);
		}
		else if((mouse.getSource().equals(jrbtnSnsCYes))&&(jrbtnSnsCYes.isSelected())){
			jrbtnSnsCYes.setSelected(true);
			jrbtnSnsCNo.setSelected(false);
		}
		else if((mouse.getSource().equals(jrbtnSnsCYes))&&(!jrbtnSnsCYes.isSelected())){
			jrbtnSnsCYes.setSelected(false);
			jrbtnSnsCNo.setSelected(true);
		}
		else if((mouse.getSource().equals(jrbtnSnsCNo))&&(jrbtnSnsCNo.isSelected())){
			jrbtnSnsCYes.setSelected(false);
			jrbtnSnsCNo.setSelected(true);
		}
		else if((mouse.getSource().equals(jrbtnSnsCNo))&&(!jrbtnSnsCNo.isSelected())){
			jrbtnSnsCYes.setSelected(true);
			jrbtnSnsCNo.setSelected(false);
		}
	}

	public void keyTyped(KeyEvent key){}	

	public void keyReleased(KeyEvent key){}
	
	public void mousePressed(MouseEvent mouse){}

	public void mouseReleased(MouseEvent mouse){}

	public void mouseEntered(MouseEvent mouse){}

	public void mouseExited(MouseEvent mouse){}
	
	public void getCfg(){
		ArrayList stngs=new SystenLib().getSettings();
		
		if(stngs.get(0).equals("1")){
			jchkbxAuralion.setSelected(true);
		}
		else {
			jchkbxAuralion.setSelected(false);
		}
		
		if(stngs.get(1).equals("1")){
			jchkbxCyra.setSelected(true);
		}
		else {
			jchkbxCyra.setSelected(false);
		}
		
		if(stngs.get(2).equals("1")){
			jchkbxECoder.setSelected(true);
		}
		else {
			jchkbxECoder.setSelected(false);
		}
		
		if(stngs.get(3).equals("1")){
			jchkbxEFSx.setSelected(true);
		}
		else {
			jchkbxEFSx.setSelected(false);
		}
		
		if(stngs.get(4).equals("1")){
			jchkbxEndavor.setSelected(true);
		}
		else {
			jchkbxEndavor.setSelected(false);
		}
		
		if(stngs.get(5).equals("1")){
			jchkbxHyperTHRONE.setSelected(true);
		}
		else {
			jchkbxHyperTHRONE.setSelected(false);
		}
		
		if(stngs.get(6).equals("0")){
			jrbtnSnsYes.setSelected(true);
			jrbtnSnsNo.setSelected(false);
		}
		else {
			jrbtnSnsYes.setSelected(false);
			jrbtnSnsNo.setSelected(true);
		}
		
		if(stngs.get(7).equals("0")){
			jrbtnSnsCYes.setSelected(true);
			jrbtnSnsCNo.setSelected(false);
		}
		else {
			jrbtnSnsCYes.setSelected(false);
			jrbtnSnsCNo.setSelected(true);
		}

		if(stngs.get(8).equals("0")){
			jrbtnBlRaw.setSelected(true);
			jrbtnBlZero.setSelected(false);
		}
		else {
			jrbtnBlRaw.setSelected(false);
			jrbtnBlZero.setSelected(true);
		}
		
		
		if(stngs.get(9).equals("0")){
			jrbtnLngE.setSelected(true);
			jrbtnLngG.setSelected(false);
		}
		else {
			jrbtnLngE.setSelected(false);
			jrbtnLngG.setSelected(true);
		}
	}
	
	public void putCfg(){
		ArrayList stngs=new ArrayList();
		
		if(jchkbxAuralion.isSelected()){
			stngs.add("1");
		}
		else {
			stngs.add("0");
		}
		
		if(jchkbxCyra.isSelected()){
			stngs.add("1");
		}
		else {
			stngs.add("0");
		}
		
		if(jchkbxECoder.isSelected()){
			stngs.add("1");
		}
		else {
			stngs.add("0");
		}
		
		if(jchkbxEFSx.isSelected()){
			stngs.add("1");
		}
		else {
			stngs.add("0");
		}
		
		if(jchkbxEndavor.isSelected()){
			stngs.add("1");
		}
		else {
			stngs.add("0");
		}
		
		if(jchkbxHyperTHRONE.isSelected()){
			stngs.add("1");
		}
		else {
			stngs.add("0");
		}
		
		if(jrbtnSnsYes.isSelected()){
			stngs.add("0");
		}
		else {
			stngs.add("1");
		}
		
		if(jrbtnSnsCYes.isSelected()){
			stngs.add("0");
		}
		else {
			stngs.add("1");
		}
		
		if(jrbtnBlRaw.isSelected()){
			stngs.add("0");
		}
		else {
			stngs.add("1");
		}
		
		if(jrbtnLngE.isSelected()){
			stngs.add("0");
		}
		else {
			stngs.add("1");
		}
		
		new SystenLib().setSettings(stngs);	
	}
	
	public void initComponents(){
		jbtnAccept.setLocation(Integer.parseInt(getParameter("btnaxpos")), Integer.parseInt(getParameter("btnaypos")));
		jbtnAccept.setSize(90, 22);
		jbtnAccept.setText(getParameter("btnaname"));
		jbtnAccept.addKeyListener(this);
		jbtnAccept.addMouseListener(this);
		jbtnAccept.requestFocus();
		
		jbtnAcSave.setLocation(Integer.parseInt(getParameter("btnasxpos")), Integer.parseInt(getParameter("btnasypos")));
		jbtnAcSave.setSize(100, 22);	
		jbtnAcSave.setText(getParameter("btnasname"));
		jbtnAcSave.addKeyListener(this);
		jbtnAcSave.addMouseListener(this);
		
		jbtnExit.setLocation(Integer.parseInt(getParameter("btnexpos")), Integer.parseInt(getParameter("btneypos")));
		jbtnExit.setSize(90, 22);
		jbtnExit.setText(getParameter("btnename"));
		jbtnExit.addKeyListener(this);
		jbtnExit.addMouseListener(this);
		
		jbtnDefault.setLocation(Integer.parseInt(getParameter("btndefxpos")), Integer.parseInt(getParameter("btndefypos")));
		jbtnDefault.setSize(90, 22);	
		jbtnDefault.setText(getParameter("btndefname"));
		jbtnDefault.addKeyListener(this);
		jbtnDefault.addMouseListener(this);
		
		jlblMdls.setLocation(Integer.parseInt(getParameter("mdlsxpos"))+15, Integer.parseInt(getParameter("mdlsypos"))+10);
		jlblMdls.setSize(Integer.parseInt(getParameter("mdlsxsiz")), Integer.parseInt(getParameter("fsize")));		
		jlblMdls.setText(getParameter("mdlstxt"));
		
		jchkbxAuralion.setLocation(Integer.parseInt(getParameter("mdlsAurxpos")), Integer.parseInt(getParameter("mdlsAurypos")));
		jchkbxAuralion.setSize(Integer.parseInt(getParameter("mdlsAurxsiz")), Integer.parseInt(getParameter("mdlsAurysiz")));
		jchkbxAuralion.setText(getParameter("mdlsAurtxt"));
		jchkbxAuralion.addKeyListener(this);
		jchkbxAuralion.addMouseListener(this);
		
		jchkbxCyra.setLocation(Integer.parseInt(getParameter("mdlsCyxpos")), Integer.parseInt(getParameter("mdlsCyypos")));
		jchkbxCyra.setSize(Integer.parseInt(getParameter("mdlsCyxsiz")), Integer.parseInt(getParameter("mdlsCyysiz")));
		jchkbxCyra.setText(getParameter("mdlsCytxt"));
		jchkbxCyra.addKeyListener(this);
		jchkbxCyra.addMouseListener(this);
		
		jchkbxECoder.setLocation(Integer.parseInt(getParameter("mdlsEcxpos")), Integer.parseInt(getParameter("mdlsEcypos")));
		jchkbxECoder.setSize(Integer.parseInt(getParameter("mdlsEcxsiz")), Integer.parseInt(getParameter("mdlsEcysiz")));
		jchkbxECoder.setText(getParameter("mdlsEctxt"));
		jchkbxECoder.addKeyListener(this);
		jchkbxECoder.addMouseListener(this);
		
		jchkbxEFSx.setLocation(Integer.parseInt(getParameter("mdlsEfsxxpos")), Integer.parseInt(getParameter("mdlsEfsxypos")));
		jchkbxEFSx.setSize(Integer.parseInt(getParameter("mdlsEfsxxsiz")), Integer.parseInt(getParameter("mdlsEfsxysiz")));
		jchkbxEFSx.setText(getParameter("mdlsEfsxtxt"));
		jchkbxEFSx.addKeyListener(this);
		jchkbxEFSx.addMouseListener(this);
		
		jchkbxEndavor.setLocation(Integer.parseInt(getParameter("mdlsEndxpos")), Integer.parseInt(getParameter("mdlsEndypos")));
		jchkbxEndavor.setSize(Integer.parseInt(getParameter("mdlsEndxsiz")), Integer.parseInt(getParameter("mdlsEndysiz")));
		jchkbxEndavor.setText(getParameter("mdlsEndtxt"));
		jchkbxEndavor.addKeyListener(this);
		jchkbxEndavor.addMouseListener(this);
		
		jchkbxHyperTHRONE.setLocation(Integer.parseInt(getParameter("mdlsHTexpos")), Integer.parseInt(getParameter("mdlsHTeypos")));
		jchkbxHyperTHRONE.setSize(Integer.parseInt(getParameter("mdlsHTexsiz")), Integer.parseInt(getParameter("mdlsHTeysiz")));
		jchkbxHyperTHRONE.setText(getParameter("mdlsHTetxt"));
		jchkbxHyperTHRONE.addKeyListener(this);
		jchkbxHyperTHRONE.addMouseListener(this);
		
		jlblSns.setLocation(Integer.parseInt(getParameter("snsxpos"))+15, Integer.parseInt(getParameter("snsypos"))+10);
		jlblSns.setSize(Integer.parseInt(getParameter("snsxsiz")), Integer.parseInt(getParameter("fsize")));		
		jlblSns.setText(getParameter("snstxt"));
		
		jrbtnSnsYes.setLocation(Integer.parseInt(getParameter("snsYesxpos")), Integer.parseInt(getParameter("snsYesypos")));
		jrbtnSnsYes.setSize(Integer.parseInt(getParameter("snsYesxsiz")), Integer.parseInt(getParameter("snsYesysiz")));
		jrbtnSnsYes.setText(getParameter("snsYestxt"));
		jrbtnSnsYes.addKeyListener(this);
		jrbtnSnsYes.addMouseListener(this);
		
		jrbtnSnsNo.setLocation(Integer.parseInt(getParameter("snsNoxpos")), Integer.parseInt(getParameter("snsNoypos")));
		jrbtnSnsNo.setSize(Integer.parseInt(getParameter("snsNoxsiz")), Integer.parseInt(getParameter("snsNoysiz")));
		jrbtnSnsNo.setText(getParameter("snsNotxt"));
		jrbtnSnsNo.addKeyListener(this);
		jrbtnSnsNo.addMouseListener(this);
		
		jlblSnsC.setLocation(Integer.parseInt(getParameter("snsCxpos"))+15, Integer.parseInt(getParameter("snsCypos"))+10);
		jlblSnsC.setSize(Integer.parseInt(getParameter("snsCxsiz")), Integer.parseInt(getParameter("fsize")));		
		jlblSnsC.setText(getParameter("snsCtxt"));
		
		jrbtnSnsCYes.setLocation(Integer.parseInt(getParameter("snsCYesxpos")), Integer.parseInt(getParameter("snsCYesypos")));
		jrbtnSnsCYes.setSize(Integer.parseInt(getParameter("snsCYesxsiz")), Integer.parseInt(getParameter("snsCYesysiz")));
		jrbtnSnsCYes.setText(getParameter("snsCYestxt"));
		jrbtnSnsCYes.addKeyListener(this);
		jrbtnSnsCYes.addMouseListener(this);
		
		jrbtnSnsCNo.setLocation(Integer.parseInt(getParameter("snsCNoxpos")), Integer.parseInt(getParameter("snsCNoypos")));
		jrbtnSnsCNo.setSize(Integer.parseInt(getParameter("snsCNoxsiz")), Integer.parseInt(getParameter("snsCNoysiz")));
		jrbtnSnsCNo.setText(getParameter("snsCNotxt"));
		jrbtnSnsCNo.addKeyListener(this);
		jrbtnSnsCNo.addMouseListener(this);
		
		jlblBl.setLocation(Integer.parseInt(getParameter("blxpos"))+15, Integer.parseInt(getParameter("blypos"))+10);
		jlblBl.setSize(Integer.parseInt(getParameter("blxsiz")), Integer.parseInt(getParameter("fsize")));		
		jlblBl.setText(getParameter("bltxt"));
		
		jrbtnBlRaw.setLocation(Integer.parseInt(getParameter("blRawxpos")), Integer.parseInt(getParameter("blRawypos")));
		jrbtnBlRaw.setSize(Integer.parseInt(getParameter("blRawxsiz")), Integer.parseInt(getParameter("blRawysiz")));
		jrbtnBlRaw.setText(getParameter("blRawtxt"));
		jrbtnBlRaw.addKeyListener(this);
		jrbtnBlRaw.addMouseListener(this);
		
		jrbtnBlZero.setLocation(Integer.parseInt(getParameter("blZeroxpos")), Integer.parseInt(getParameter("blZeroypos")));
		jrbtnBlZero.setSize(Integer.parseInt(getParameter("blZeroxsiz")), Integer.parseInt(getParameter("blZeroysiz")));
		jrbtnBlZero.setText(getParameter("blZerotxt"));
		jrbtnBlZero.addKeyListener(this);
		jrbtnBlZero.addMouseListener(this);
		
		jlblLng.setLocation(Integer.parseInt(getParameter("lngxpos"))+15, Integer.parseInt(getParameter("lngypos"))+10);
		jlblLng.setSize(Integer.parseInt(getParameter("lngxsiz")), Integer.parseInt(getParameter("fsize")));		
		jlblLng.setText(getParameter("lngtxt"));
		
		jrbtnLngE.setLocation(Integer.parseInt(getParameter("lngExpos")), Integer.parseInt(getParameter("lngEypos")));
		jrbtnLngE.setSize(Integer.parseInt(getParameter("lngExsiz")), Integer.parseInt(getParameter("lngEysiz")));
		jrbtnLngE.setText(getParameter("lngEtxt"));
		jrbtnLngE.addKeyListener(this);
		jrbtnLngE.addMouseListener(this);
		
		jrbtnLngG.setLocation(Integer.parseInt(getParameter("lngGxpos")), Integer.parseInt(getParameter("lngGypos")));
		jrbtnLngG.setSize(Integer.parseInt(getParameter("lngGxsiz")), Integer.parseInt(getParameter("lngGysiz")));
		jrbtnLngG.setText(getParameter("lngGtxt"));
		jrbtnLngG.addKeyListener(this);
		jrbtnLngG.addMouseListener(this);
	}	
	
	private class Jpnl extends JPanel {
		
		private static final long serialVersionUID=1L;
		
		public Jpnl(){
			setOpaque(true);			
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			g.setFont(new Font(getParameter("font"), Font.BOLD, Integer.parseInt(getParameter("fsize"))));
			g.setColor(Color.black);
			
			g.drawString(getParameter("text"), Integer.parseInt(getParameter("xtloc")), Integer.parseInt(getParameter("ytloc")));
		
			g.drawRect(Integer.parseInt(getParameter("mdlsxpos")), Integer.parseInt(getParameter("mdlsypos")), Integer.parseInt(getParameter("mdlsxsiz")), Integer.parseInt(getParameter("mdlsysiz")));
			
			g.drawRect(Integer.parseInt(getParameter("snsxpos")), Integer.parseInt(getParameter("snsypos")), Integer.parseInt(getParameter("snsxsiz")), Integer.parseInt(getParameter("snsysiz")));
		
			g.drawRect(Integer.parseInt(getParameter("snsCxpos")), Integer.parseInt(getParameter("snsCypos")), Integer.parseInt(getParameter("snsCxsiz")), Integer.parseInt(getParameter("snsCysiz")));
			
			g.drawRect(Integer.parseInt(getParameter("blxpos")), Integer.parseInt(getParameter("blypos")), Integer.parseInt(getParameter("blxsiz")), Integer.parseInt(getParameter("blysiz")));
		
			g.drawRect(Integer.parseInt(getParameter("lngxpos")), Integer.parseInt(getParameter("lngypos")), Integer.parseInt(getParameter("lngxsiz")), Integer.parseInt(getParameter("lngysiz")));
			
			g.drawRect(Integer.parseInt(getParameter("msgxpos")), Integer.parseInt(getParameter("msgypos")), Integer.parseInt(getParameter("msgxsiz")), Integer.parseInt(getParameter("msgysiz")));
		
			g.drawString(getParameter(msg), Integer.parseInt(getParameter("msgxpos"))+10, Integer.parseInt(getParameter("msgypos"))+15);
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
