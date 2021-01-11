import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.PriorityQueue;

import javax.net.ssl.SSLHandshakeException;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class MainPage extends JFrame{
	public JButton rButton;
	public Timer t;
	public int tcount;
	public int timeCount;
	public Timer t2;
	public int timeCount2;
	public Timer t3,t4;
	public int timeCount3;
	public JLabel title;
	public JLabel subTitle,border;
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
	public BookMarkCreateArea b;
	public int rb = 200;
	public Result r1,r2,r3,r4,r5,r6,r7;
	public GoogleQuery g;
	public PriorityQueue<WebNode> q;
	
	public MainPage() {
//		addKeyListener(new KeyAdapter() {
		createT();
		createFont();
		createBorder();
		createOpenAndClose();
		createTitle();
		createBookMark();
		createAddAndDelete();
		createSearch();
		createButton();
		createReturn();
		createR();
		createTimer();
		setTitle("Seekin'Tern");
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
	
	public void createTimer() {
		tcount = 0;
		TimerListener a = new TimerListener();
		t4 = new Timer(1,a);
		t4.start();
		
	}
	
	class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			tcount++;
			if(tcount<=70&&tcount>0) {
				title.setText("S");
			}
			else if(tcount<=140&&tcount>70) {
				title.setText("Se");
			}
			else if(tcount<=210&&tcount>140) {
				title.setText("See");
			}
			else if(tcount<=280&&tcount>210) {
				title.setText("Seek");
			}
			else if(tcount<=350&&tcount>280) {
				title.setText("Seeki");
			}
			else if(tcount<=420&&tcount>350) {
				title.setText("Seekin'");
			}
			else if(tcount<=490&&tcount>420) {
				title.setText("Seekin'T");
			}
			else if(tcount<=560&&tcount>490) {
				title.setText("Seekin'Te");
			}
			else if(tcount<=630&&tcount>560) {
				title.setText("Seekin'Ter");
			}
			else if(tcount<=700&&tcount>630) {
				title.setText("Seekin'Tern");
			}
			else if(tcount>770) {
				t4.stop();
				tcount = 0;
			}
		}
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
				search.setEnabled(true);
				javaB.setEnabled(true);
				pythonB.setEnabled(true);
				cB.setEnabled(true);
				r1.setEnabled(true);
				r2.setEnabled(true);
				r3.setEnabled(true);
				r4.setEnabled(true);
				r5.setEnabled(true);
				r6.setEnabled(true);
				r7.setEnabled(true);
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
				r1.setEnabled(true);
				r2.setEnabled(true);
				r3.setEnabled(true);
				r4.setEnabled(true);
				r5.setEnabled(true);
				r6.setEnabled(true);
				r7.setEnabled(true);
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
				r1.setEnabled(false);
				r2.setEnabled(false);
				r3.setEnabled(false);
				r4.setEnabled(false);
				r5.setEnabled(false);
				r6.setEnabled(false);
				r7.setEnabled(false);
				search.setEnabled(false);
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
		title = new JLabel("");
		title.setForeground(Color.white);
		title.setFont(new Font("Futurist Fixed-width", Font.BOLD, 55));
		title.setBounds(290, 90, 630, 130);
		add(title);
		subTitle = new JLabel("Seekin'Tern");
		subTitle.setForeground(Color.white);
		subTitle.setFont(new Font("Futurist Fixed-width", Font.BOLD, 30));
		subTitle.setBounds(420, 20, 400, 50);
		subTitle.setVisible(false);
		add(subTitle);
	}
	
	public void createBorder() {
		border = new JLabel(new ImageIcon(getClass().getResource("/resources/border.png")));
		border.setBounds(284, 210, 632, 450);
		border.setVisible(false);
		add(border);
	}
	
	public void createR() {
		class R1l implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Desktop desktop = Desktop.getDesktop();   
				URI uri;
				try {
					uri = new URI(r1.url);
					desktop.browse(uri);
				} catch (URISyntaxException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}	
		}
		class R2l implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Desktop desktop = Desktop.getDesktop();   
				URI uri;
				try {
					uri = new URI(r2.url);
					desktop.browse(uri);
				} catch (URISyntaxException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}	
		}
		class R3l implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Desktop desktop = Desktop.getDesktop();   
				URI uri;
				try {
					uri = new URI(r3.url);
					desktop.browse(uri);
				} catch (URISyntaxException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}	
		}
		class R4l implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Desktop desktop = Desktop.getDesktop();   
				URI uri;
				try {
					uri = new URI(r4.url);
					desktop.browse(uri);
				} catch (URISyntaxException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}	
		}
		class R5l implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Desktop desktop = Desktop.getDesktop();   
				URI uri;
				try {
					uri = new URI(r5.url);
					desktop.browse(uri);
				} catch (URISyntaxException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}	
		}
		class R6l implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Desktop desktop = Desktop.getDesktop();   
				URI uri;
				try {
					uri = new URI(r6.url);
					desktop.browse(uri);
				} catch (URISyntaxException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}	
		}
		class R7l implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Desktop desktop = Desktop.getDesktop();   
				URI uri;
				try {
					uri = new URI(r7.url);
					desktop.browse(uri);
				} catch (URISyntaxException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}	
		}
		r1 = new Result();
		r2 = new Result();
		r3 = new Result();
		r4 = new Result();
		r5 = new Result();
		r6 = new Result();
		r7 = new Result();
		r1.setBounds(300,230,600,50);
		r2.setBounds(300,290,600,50);
		r3.setBounds(300,350,600,50);
		r4.setBounds(300,410,600,50);
		r5.setBounds(300,470,600,50);
		r6.setBounds(300,530,600,50);
		r7.setBounds(300,590,600,50);
		r1.addActionListener(new R1l());
		r2.addActionListener(new R2l());
		r3.addActionListener(new R3l());
		r4.addActionListener(new R4l());
		r5.addActionListener(new R5l());
		r6.addActionListener(new R6l());
		r7.addActionListener(new R7l());
		
		add(r1);
		add(r2);
		add(r3);
		add(r4);
		add(r5);
		add(r6);
		add(r7);
		
		
	}
	
	
	
	public void createSearch() {
		enter = new JTextArea();
		enter.setVisible(true);
		enter.setOpaque(true);
		enter.setLineWrap(true);
//		enter.setBackground(new Color(255,255,255,90));
		enter.setEditable(true);
		enter.setBounds( 300, 330, 600, 65);
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
				border.setVisible(true);
				title.setVisible(false);
				pythonB.setVisible(false);
				cB.setVisible(false);
				javaB.setVisible(false);
				enter.setBounds( 300, 100, 600, 65);
				search.setBounds(920, 105, 64, 64);
				subTitle.setVisible(true);
				rButton.setVisible(true);
				r1.setVisible(true);
				r2.setVisible(true);
				r3.setVisible(true);
				r4.setVisible(true);
				r5.setVisible(true);
				r6.setVisible(true);
				r7.setVisible(true);
				r1.setText("");
				r2.setText("");
				r3.setText("");
				r4.setText("");
				r5.setText("");
				r6.setText("");
				r7.setText("");
				try {
					String e1 = enter.getText().replaceAll("\r|\n", "");
					g = new GoogleQuery(e1+"實習");
					q = g.query();
					WebNode w;
					w = q.poll();
					r1.setText(w.toString());
					r1.url = w.webPage.url;
					w = q.poll();
					r2.setText(w.toString());
					r2.url = w.webPage.url;
					w = q.poll();
					r3.setText(w.toString());
					r3.url = w.webPage.url;
					w = q.poll();
					r4.setText(w.toString());
					r4.url = w.webPage.url;
					w = q.poll();
					r5.setText(w.toString());
					r5.url = w.webPage.url;
					w = q.poll();
					r6.setText(w.toString());
					r6.url = w.webPage.url;
					w = q.poll();
					r7.setText(w.toString());
					r7.url = w.webPage.url;
	
				} catch (IOException e1) {
//					 TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
			
		}
		
		search = new JButton();
		search.setIcon(new ImageIcon(MainPage.class.getResource("/resources/search.png")));
		search.setBackground(new Color(1,1,1,0));
		search.setOpaque(false);
		search.setBounds(920, 335, 64, 64);
		search.setBorderPainted(false);
		search.addActionListener(new SListener());
		add(search);
	}
	public void createT() {
		timeCount=0;
		timeCount2=0;
		timeCount3=0;
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
				border.setVisible(false);
				rButton.setVisible(false);
				rButton.setBounds(120, 80, 99, 99);
				title.setVisible(true);
				pythonB.setVisible(true);
				cB.setVisible(true);
				javaB.setVisible(true);
				enter.setBounds( 300, 330, 600, 65);
				search.setBounds(920, 335, 64, 64);
				subTitle.setVisible(false);
				enter.setText("");
				r1.setVisible(false);
				r2.setVisible(false);
				r3.setVisible(false);
				r4.setVisible(false);
				r5.setVisible(false);
				r6.setVisible(false);
				r7.setVisible(false);
				r1.setText("");
				r2.setText("");
				r3.setText("");
				r4.setText("");
				r5.setText("");
				r6.setText("");
				r7.setText("");
				
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
		pythonB.setBounds(300, 530, 200, 100);
		pythonB.addActionListener(new PBListener());
		add(pythonB);
		javaB = new JButton("Java");
		javaB.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 30));
		javaB.setBackground(new Color(197,109,134));
		javaB.setForeground(Color.white);
		javaB.setBounds(500, 530, 200, 100);
		javaB.addActionListener(new JBListener());
		add(javaB);
		cB = new JButton("JS");
		cB.setFont(new Font("Futurist Fixed-width", Font.PLAIN, 30));
		cB.setBackground(new Color(255,117,130));
		cB.setForeground(Color.white);
		cB.setBounds(700, 530, 200, 100);
		cB.addActionListener(new JsBListener());
		add(cB);
	}
	public class JBListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			title.setVisible(false);
			pythonB.setVisible(false);
			cB.setVisible(false);
			javaB.setVisible(false);
			enter.setBounds( 300, 100, 600, 65);
			search.setBounds(920, 105, 64, 64);
			subTitle.setVisible(true);
			rButton.setVisible(true);
			r1.setVisible(true);
			r2.setVisible(true);
			r3.setVisible(true);
			r4.setVisible(true);
			r5.setVisible(true);
			r6.setVisible(true);
			r7.setVisible(true);
			border.setVisible(true);
			try {
				g = new GoogleQuery("java實習");
				q = g.query();
				WebNode w;
				w = q.poll();
				r1.setText(w.toString());
				r1.url = w.webPage.url;
				w = q.poll();
				r2.setText(w.toString());
				r2.url = w.webPage.url;
				w = q.poll();
				r3.setText(w.toString());
				r3.url = w.webPage.url;
				w = q.poll();
				r4.setText(w.toString());
				r4.url = w.webPage.url;
				w = q.poll();
				r5.setText(w.toString());
				r5.url = w.webPage.url;
				w = q.poll();
				r6.setText(w.toString());
				r6.url = w.webPage.url;
				w = q.poll();
				r7.setText(w.toString());
				r7.url = w.webPage.url;
			
			} catch (IOException e1) {
//				 TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		
	}
	public class PBListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			title.setVisible(false);
			pythonB.setVisible(false);
			cB.setVisible(false);
			javaB.setVisible(false);
			enter.setBounds( 300, 100, 600, 65);
			search.setBounds(920, 105, 64, 64);
			subTitle.setVisible(true);
			rButton.setVisible(true);
			r1.setVisible(true);
			r2.setVisible(true);
			r3.setVisible(true);
			r4.setVisible(true);
			r5.setVisible(true);
			r6.setVisible(true);
			r7.setVisible(true);
			border.setVisible(true);
			try {
				g = new GoogleQuery("python實習");
				q = g.query();
				WebNode w;
				w = q.poll();
				r1.setText(w.toString());
				r1.url = w.webPage.url;
				w = q.poll();
				r2.setText(w.toString());
				r2.url = w.webPage.url;
				w = q.poll();
				r3.setText(w.toString());
				r3.url = w.webPage.url;
				w = q.poll();
				r4.setText(w.toString());
				r4.url = w.webPage.url;
				w = q.poll();
				r5.setText(w.toString());
				r5.url = w.webPage.url;
				w = q.poll();
				r6.setText(w.toString());
				r6.url = w.webPage.url;
				w = q.poll();
				r7.setText(w.toString());
				r7.url = w.webPage.url;
			
			} catch (IOException e1) {
//				 TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		
	}
	public class JsBListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			title.setVisible(false);
			pythonB.setVisible(false);
			cB.setVisible(false);
			javaB.setVisible(false);
			enter.setBounds( 300, 100, 600, 65);
			search.setBounds(920, 105, 64, 64);
			subTitle.setVisible(true);
			rButton.setVisible(true);
			r1.setVisible(true);
			r2.setVisible(true);
			r3.setVisible(true);
			r4.setVisible(true);
			r5.setVisible(true);
			r6.setVisible(true);
			r7.setVisible(true);
			border.setVisible(true);
			try {
				g = new GoogleQuery("javascript實習");
				q = g.query();
				WebNode w;
				w = q.poll();
				r1.setText(w.toString());
				r1.url = w.webPage.url;
				w = q.poll();
				r2.setText(w.toString());
				r2.url = w.webPage.url;
				w = q.poll();
				r3.setText(w.toString());
				r3.url = w.webPage.url;
				w = q.poll();
				r4.setText(w.toString());
				r4.url = w.webPage.url;
				w = q.poll();
				r5.setText(w.toString());
				r5.url = w.webPage.url;
				w = q.poll();
				r6.setText(w.toString());
				r6.url = w.webPage.url;
				w = q.poll();
				r7.setText(w.toString());
				r7.url = w.webPage.url;
			
			} catch (IOException e1) {
//				 TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		
	}
	
	
}
