import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class BookMarkCreateArea extends JTextArea{
	public JLabel title,t,u,warning1,warning2;
	public JTextArea bTitle,url;
	public JButton confirm,cancel;
	
	public BookMarkCreateArea() {
		new JTextArea();
		createTitle();
		createConfirm();
		createTextArea();
		createWarning();
		setVisible(true);
		setLineWrap(true);
		setBackground(new Color(53,92,125));
		setEditable(false);
		setBounds( 350, 230, 500, 340);
		setBorder(new EtchedBorder());
		setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.white));
		setVisible(false);
	}
	
	public void createTitle() {
		title = new JLabel("Add");
		title.setForeground(Color.white);
		title.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 30));
		title.setBounds(200, 0, 100, 80);
		add(title);
		t = new JLabel("Title:");
		t.setForeground(Color.white);
		t.setFont(new Font("Futurist Fixed-width", Font.BOLD, 20));
		t.setBounds(60, 70, 380, 50);
		add(t);
		u = new JLabel("URL:");
		u.setForeground(Color.white);
		u.setFont(new Font("Futurist Fixed-width", Font.BOLD, 20));
		u.setBounds(60, 150, 380, 50);
		add(u);
	}
	
	public void createTextArea() {
		bTitle = new JTextArea();
		bTitle.setBounds( 60, 120, 380, 30);
		bTitle.setBorder(new EtchedBorder());
		bTitle.setFont(new Font("微軟正黑體", Font.BOLD, 15));
//		bTitle.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.white));
		add(bTitle);
		url = new JTextArea();
		url.setBounds( 60, 200, 380, 30);
		url.setBorder(new EtchedBorder());
		url.setFont(new Font("微軟正黑體", Font.BOLD, 15));
//		url.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.white));
		add(url);
	}
	
	public void createConfirm() {
		confirm = new JButton("Confirm");
		confirm.setBackground(new Color(38,66,90));
		confirm.setBorder(new EtchedBorder());
		confirm.setFont(new Font("Futurist Fixed-width", Font.BOLD, 18));
		confirm.setForeground(new Color(220,220,220));
		confirm.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		confirm.setBounds(60, 260, 180, 45);
		add(confirm);
		cancel = new JButton("Cancel");
		cancel.setBackground(new Color(38,66,90));
		cancel.setBorder(new EtchedBorder());
		cancel.setFont(new Font("Futurist Fixed-width", Font.BOLD, 18));
		cancel.setForeground(new Color(220,220,220));
		cancel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
		cancel.setBounds(260, 260, 180, 45);
		add(cancel);
	}
	
	public void createWarning() {
		warning1 = new JLabel(new ImageIcon(MainPage.class.getResource("/resources/warning.png")));
		warning1.setBounds(234, 80, 32, 32);
		add(warning1);
		warning2 = new JLabel(new ImageIcon(MainPage.class.getResource("/resources/warning.png")));
		warning2.setBounds(234, 160, 32, 32);
		add(warning2);
		warning1.setVisible(false);
		warning2.setVisible(false);
	}
}
