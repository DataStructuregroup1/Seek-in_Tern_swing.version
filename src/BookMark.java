import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class BookMark extends JButton{
	public String url;
	public String title;
	
	public BookMark(String url,String title) {
		this.url = url;
		new JButton();
		this.setText(title);
		setVisible(true);
		setOpaque(true);
		setBackground(new Color(53,92,125));
		setBorder(new EtchedBorder());
		setFont(new Font("微軟正黑體 Light", Font.PLAIN, 30));
		setForeground(new Color(220,220,220));
		setBorder(BorderFactory.createMatteBorder(0, 0, 4, 4, Color.white));
	}
}
