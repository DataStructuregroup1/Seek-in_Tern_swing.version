import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class Widget extends JTextArea{
	
	public ArrayList<BookMark> bm;
	public int bookMarkCount = 0;
	
	public Widget() {
		new JTextArea();
		setVisible(true);
		setOpaque(true);
		setLineWrap(true);
		setBackground(new Color(255,255,255,90));
		setEditable(false);
		setBounds( -210, 0, 210, 792);
		setBorder(new EtchedBorder());
		setFont(new Font("微軟正黑體 Light", Font.PLAIN, 45));
		setForeground(Color.black);
		setBorder(BorderFactory.createMatteBorder(4, 0, 35, 4, Color.white));
	}
	
	public void addBookMark(String url,String title) {
		BookMark b1 = new BookMark(url,title);
		b1.setBounds(0, bookMarkCount*128, 210, 128);;
		bookMarkCount++;
		add(b1);
	}
	
	public void open() {
		setBounds( 0, 0, 210, 792);
	}
	
	public void close() {
		setBounds( -210, 0, 210, 792);
	}
}
