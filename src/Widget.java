import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class Widget extends JTextArea{
	
	public ArrayList<BookMark> bms;
	public int bookMarkCount = 0;
	
	public Widget() {
		bms = new ArrayList<BookMark>();
		new JTextArea();
		setVisible(true);
		setOpaque(true);
		setLineWrap(true);
		setBackground(new Color(26,51,101));
		setEditable(false);
		setBounds( -210, 0, 210, 792);
		setBorder(new EtchedBorder());
		setBorder(BorderFactory.createMatteBorder(4, 0, 35, 4, Color.white));
	}
	
	public void addBookMark(String url,String title) {
		if(bms.size()>=5) {
		}
		BookMark b1 = new BookMark(url,title);
		b1.setBounds(0, bookMarkCount*128, 210, 128);
		getMainPage().addBm.setBounds(0, (bookMarkCount+1)*128, 210, 128);
		bookMarkCount++;
		add(b1);
		bms.add(b1);
	}
	public void deleteBookMark(BookMark bm) {
		bookMarkCount--;
		remove(bm);
		bms.remove(bm);
		for(BookMark b:bms) {
			b.setBounds(0, bms.indexOf(b)*128, 210, 128);
		}
		getMainPage().addBm.setBounds(0, bookMarkCount*128, 210, 128);
	}
	
	public void open() {
		setBounds( 0, 0, 210, 792);
	}
	
	public void close() {
		setBounds( -210, 0, 210, 792);
	}
	public MainPage getMainPage() {
		for(Frame frame: JFrame.getFrames()) {
		if(frame.getTitle().equals("Seekin'Tern")) {
			MainPage mp = (MainPage) frame;
			return mp;
		}
		}
		return null;
		}
}
