import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Result extends JButton{
	public String url;
	public Result() {
//		setBounds(300,200,600,50);
		setVisible(false);
		setBackground(new Color(197,109,134));
		setForeground(Color.white);
//		setBorderPainted(false);
		setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.darkGray));
		setFont(new Font("微軟正黑體 Light", Font.PLAIN, 25));
	}
}
