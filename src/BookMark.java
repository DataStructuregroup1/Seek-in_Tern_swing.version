import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class BookMark extends JButton{
	public String url;
	public String title;
	public JButton x;
	
	public BookMark(String url,String title) {
		createX();
		this.url = url;
		new JButton();
		this.setText(title);
		setVisible(true);
		setOpaque(true);
		setBackground(new Color(38,66,90));
		setBorder(new EtchedBorder());
		setFont(new Font("微軟正黑體 Light", Font.PLAIN, 30));
		setForeground(new Color(220,220,220));
		setBorder(BorderFactory.createMatteBorder(0, 0, 4, 4, Color.white));
	}
	
	public void createX() {
		class XListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getMainPage().bookMark.deleteBookMark(BookMark.this);
			}
			
		}
		x = new JButton(new ImageIcon(MainPage.class.getResource("/resources/x.png")));
		x.setOpaque(false);
		x.setBackground(Color.black);
		x.setBorderPainted(false);
		XListener xl = new XListener();
		x.addActionListener(xl);
		add(x);
	}
	public MainPage getMainPage() {
		for(Frame frame: JFrame.getFrames()) {
		if(frame.getTitle().equals("Boogle")) {
			MainPage mp = (MainPage) frame;
			return mp;
		}
		}
		return null;
		}
}
