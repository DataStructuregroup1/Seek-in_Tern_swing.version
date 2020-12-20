import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class MainPage extends JFrame{
	public JButton rButton;
	public Timer t;
	public int timeCount=0;
	public Timer t2;
	public int timeCount2=0;
	public Timer t3;
	public int timeCount3=0;
	public JLabel title;
	public JLabel subTitle;
	public JTextArea enter;
	public JTextArea test;
	public JButton search;
	public JButton javaB;
	public JButton pythonB;
	public JButton cB;
	public JButton oc;
	public JButton addBm,delBm;
	public Widget bookMark;
	boolean open;
	BookMarkCreateArea b;
	
	public MainPage() {
		createT();
		createFont();
		createOpenAndClose();
		createTitle();
		createBookMark();
		createAddAndDelete();
		createSearch();
		createButton();
		createReturn();
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
		class ConfirmBMListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				b.warning1.setVisible(false);
				b.warning2.setVisible(false);
				if(b.url.getText().equals("")&&b.bTitle.getText().equals("")) {
					b.warning1.setVisible(true);
					b.warning2.setVisible(true);
					return;
				}
				if(b.url.getText().equals("")) {
					b.warning2.setVisible(true);
					return;
				}
				if(b.bTitle.getText().equals("")) {
					b.warning1.setVisible(true);
					return;
				}
				bookMark.addBookMark(b.url.getText(),b.bTitle.getText());
				b.bTitle.setText("");
				b.url.setText("");
				b.setVisible(false);
				javaB.setEnabled(true);
				pythonB.setEnabled(true);
				cB.setEnabled(true);
				b.warning1.setVisible(false);
				b.warning2.setVisible(false);
			}
			
		}
		class CancelBMListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				b.bTitle.setText("");
				b.url.setText("");
				b.setVisible(false);
				javaB.setEnabled(true);
				pythonB.setEnabled(true);
				cB.setEnabled(true);
				b.warning1.setVisible(false);
				b.warning2.setVisible(false);
			}
			
		}
		ConfirmBMListener c = new ConfirmBMListener();
		CancelBMListener c2 = new CancelBMListener();
		b = new BookMarkCreateArea();
		b.confirm.addActionListener(c);
		b.cancel.addActionListener(c2);
		bookMark = new Widget();
		add(b);
		add(bookMark);
	}
	public void createAddAndDelete() {
		class AddListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				b.setVisible(true);
				javaB.setEnabled(false);
				pythonB.setEnabled(false);
				cB.setEnabled(false);
			}
			
		}
		addBm = new JButton();
		addBm.setIcon(new ImageIcon(getClass().getResource("/resources/plus.png")));
		addBm.setBackground(new Color(38,66,90));
		addBm.setBounds(0, 0, 210, 128);
		addBm.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 4, Color.white));
		AddListener a = new AddListener();
		addBm.addActionListener(a);
		bookMark.add(addBm);
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
					open = true;
					t2.start();
				}
				else {
					open = false;
					t3.start();
				}
			}
			
		}
		OcListener o = new OcListener();
		oc.addActionListener(o);
		add(oc);
	}
	
	public class T2Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timeCount2++;
			if(timeCount2>0&&timeCount2<211) {
				oc.setBounds(10+timeCount2, 355, 50, 50);
				bookMark.setBounds(-210+timeCount2, 0, 210, 792);
			}
			else if(timeCount2>211) {
				t2.stop();
				timeCount2 = 0;
				oc.setIcon(new ImageIcon(getClass().getResource("/resources/close.png")));
			}	
		}
		
	}
	public class T3Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timeCount3++;
			if(timeCount3>0&&timeCount3<211) {
				oc.setBounds(220-timeCount3, 355, 50, 50);
				bookMark.setBounds(0-timeCount3, 0, 210, 792);
			}
			else if(timeCount3>211) {
				t3.stop();
				timeCount3 = 0;
				oc.setIcon(new ImageIcon(getClass().getResource("/resources/open.png")));
			}
			
		}
		
	}
	///////////////////////////////////////
	
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
		title = new JLabel("Google");
		title.setForeground(Color.white);
		title.setFont(new Font("Futurist Fixed-width", Font.BOLD, 100));
		title.setBounds(290, 90, 630, 130);
		add(title);
		subTitle = new JLabel("Google");
		subTitle.setForeground(Color.white);
		subTitle.setFont(new Font("Futurist Fixed-width", Font.BOLD, 30));
		subTitle.setBounds(510, 20, 190, 50);
		subTitle.setVisible(false);
		add(subTitle);
	}
	public void createSearch() {
		enter = new JTextArea();
		enter.setVisible(true);
		enter.setOpaque(true);
		enter.setLineWrap(true);
//		enter.setBackground(new Color(255,255,255,90));
		enter.setEditable(true);
		enter.setBounds( 300, 350, 600, 65);
		enter.setBorder(new EtchedBorder());
		enter.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 40));
		enter.setForeground(Color.black);
		enter.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255,117,130)));
		add(enter);
		
		class SListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(enter.getText().equals("")) {
					return;
				}
				title.setVisible(false);
				pythonB.setVisible(false);
				cB.setVisible(false);
				javaB.setVisible(false);
				enter.setBounds( 300, 100, 600, 65);
				search.setBounds(920, 105, 64, 64);
				subTitle.setVisible(true);
				rButton.setVisible(true);
//				try {
//					GoogleQuery g = new GoogleQuery(enter.getText());
//					g.query();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
			
		}
		
		search = new JButton();
		search.setIcon(new ImageIcon(MainPage.class.getResource("/resources/search.png")));
		search.setBackground(new Color(1,1,1,0));
		search.setOpaque(false);
		search.setBounds(920, 355, 64, 64);
		search.setBorderPainted(false);
		search.addActionListener(new SListener());
		add(search);
	}
	public void createT() {
		TListener a = new TListener();
		t = new Timer(1,a);
		T2Listener t2l = new T2Listener();
		t2 = new Timer(1,t2l);
		T3Listener t3l = new T3Listener();
		t3 = new Timer(1,t3l);
	}
	
	class TListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timeCount++;
			if(timeCount>0&&timeCount<250) {
				rButton.setBounds(-timeCount+120, 80, 99, 99);
			}
			if(timeCount>250) {
				t.stop();
				timeCount = 0;
				rButton.setVisible(false);
				rButton.setBounds(120, 80, 99, 99);
				title.setVisible(true);
				pythonB.setVisible(true);
				cB.setVisible(true);
				javaB.setVisible(true);
				enter.setBounds( 300, 350, 600, 65);
				search.setBounds(920, 355, 64, 64);
				subTitle.setVisible(false);
				
			}
		}
		
	}
	public void createReturn() {
		rButton = new JButton();
		rButton.setIcon(new ImageIcon(getClass().getResource("/resources/return_arrow.png")));
		rButton.setBackground(new Color(1,1,1,0));
		rButton.setOpaque(false);
		rButton.setBounds(120, 80, 99, 99);
		rButton.setBorderPainted(false);
		ReturnListener r = new ReturnListener();
		rButton.addActionListener(r);
		rButton.setVisible(false);
		getContentPane().add(rButton);
		
	}
	public class ReturnListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			t.start();
		}
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
