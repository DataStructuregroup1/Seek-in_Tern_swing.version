import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class MainPage extends JFrame{
	
	public JLabel title;
//	public JLabel subTitle;
	public JTextArea enter;
	public JTextArea test;
	public JButton search;
	public JButton javaB;
	public JButton pythonB;
	public JButton cB;
	public JButton oc;
	public JButton close;
	public Widget bookMark;
	boolean open;
	
	public MainPage() {
		createFont();
		createOpenAndClose();
		createTitle();
		createBookMark();
		createSearch();
		createButton();
		setTitle("Boogle");
		Container ct = this.getContentPane();
		BackgroundPanel bgp = new BackgroundPanel((new ImageIcon(MainPage.class.getResource("resources/bg.jpg")).getImage()));
		bgp.setBounds(0,0,1200,800);
		ct.add(bgp);
		ct.setVisible(true);
		setSize(1200,800);	
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void createBookMark() {
		//test
		bookMark = new Widget();
		add(bookMark);
		bookMark.addBookMark("qqq","aaaaa");
		bookMark.addBookMark("qqq","bbbbb");
		bookMark.addBookMark("qqq","ccccc");
	}
	
	public void createOpenAndClose() {
		open = false;
		oc = new JButton();
		oc.setIcon(new ImageIcon(getClass().getResource("/resources/open.png")));
		oc.setBackground(new Color(1,1,1,0));
		oc.setOpaque(false);
		oc.setBounds(10, 355, 50, 50);
		oc.setBorderPainted(false);
		class OcListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(open==false) {
					oc.setBounds(220, 355, 50, 50);
					bookMark.setBounds( 0, 0, 210, 792);
					open = true;
					oc.setIcon(new ImageIcon(getClass().getResource("/resources/close.png")));
				}
				else {
					oc.setBounds(10, 355, 50, 50);
					bookMark.setBounds( -210, 0, 210, 792);
					open = false;
					oc.setIcon(new ImageIcon(getClass().getResource("/resources/open.png")));
				}
			}
			
		}
		OcListener o = new OcListener();
		oc.addActionListener(o);
		add(oc);
	}
	
	public void createFont() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("fonts/FUTRFW.otf")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,this.getClass().getResourceAsStream("fonts/ARIALN.ttf")));
        } catch (FontFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}

	public void createTitle() {
		title = new JLabel("Boogle");
		title.setForeground(Color.white);
		title.setFont(new Font("Futurist Fixed-width", Font.BOLD, 100));
		title.setBounds(290, 90, 630, 130);
		add(title);
//		subTitle = new JLabel("For Informatoin Intern");
//		subTitle.setForeground(Color.white);
//		subTitle.setFont(new Font("Arial Narrow", Font.BOLD, 20));
//		subTitle.setBounds(290, 200, 500, 80);
//		add(subTitle);
	}
	public void createSearch() {
		enter = new JTextArea();
		enter.setVisible(true);
		enter.setOpaque(true);
		enter.setLineWrap(true);
//		enter.setBackground(new Color(255,255,255,90));
		enter.setEditable(true);
		enter.setBounds( 300, 350, 600, 73);
		enter.setBorder(new EtchedBorder());
		enter.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 45));
		enter.setForeground(Color.black);
		enter.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255,117,130)));
		add(enter);
		
		search = new JButton();
		search.setIcon(new ImageIcon(MainPage.class.getResource("/resources/search.png")));
		search.setBackground(new Color(1,1,1,0));
		search.setOpaque(false);
		search.setBounds(920, 355, 64, 64);
		search.setBorderPainted(false);
		add(search);
	}
	public void createButton() {
		pythonB = new JButton("Python");
		pythonB.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 25));
		pythonB.setBackground(new Color(114,90,122));
		pythonB.setForeground(Color.white);
		pythonB.setBounds(300, 550, 200, 100);
		add(pythonB);
		javaB = new JButton("Java");
		javaB.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 30));
		javaB.setBackground(new Color(197,109,134));
		javaB.setForeground(Color.white);
		javaB.setBounds(500, 550, 200, 100);
		add(javaB);
		cB = new JButton("C#");
		cB.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 30));
		cB.setBackground(new Color(255,117,130));
		cB.setForeground(Color.white);
		cB.setBounds(700, 550, 200, 100);
		add(cB);
	}
}
