import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


class Game{
	
	JFrame frame = new JFrame();
	int runm = 0;
	boolean run = true;
	ImageIcon character = new ImageIcon();
	Image charRun[] = new Image[2];
	JLabel chara = new JLabel();
	ImageIcon backG;
	JButton closeB;
	JScrollPane scrollPane;
	JPanel backGround;
	final int ALL_WIDTH = 1920; 	// ��ü frame�� �� 1920
	final int ALL_HEIGHT = 1080; 	// ��ü frame�� ���� 1080
	
	int bG = 0;
	int bX[] = {0, 1920};
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	void go() {
		
		backGround = new JPanel() {
			ImageIcon backG = new ImageIcon("src/Image/backGround1.jpg");
			public void paintComponent(Graphics g) {
				g.drawImage(backG.getImage(), 0, 0, null);
				setOpaque(false);
	            super.paintComponent(g);
			}
	   };
		   
		//goBackGround goBG = new goBackGround();
		//goBG.start();
		
		{
	    	// ��ư �̹��� ũ�� ���̱�
			ImageIcon close = new ImageIcon("src/Image/close.png");
		   	Image ci1 = close.getImage();
		   	Image ci2 = ci1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		   	close.setImage(ci2);
		   	closeB = new JButton(close);
	    }

		closeB.setBorderPainted(false); // ��ư �ܰ��� ���ֱ�
		closeB.setContentAreaFilled(false); // ��ư ���� ä��� ���ֱ�
		closeB.setFocusPainted(false); //��ư Ŭ���� �׵θ� ���ֱ�
		closeB.setOpaque(false); // ��ư �����ϰ�
		
		closeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
				{	System.exit(0);		}
		});
			
		closeB.setBounds(1840, 20, 50, 50); // ������ġ ���� (x, y, w, h)
			
		{
			try {
				charRun[0] = ImageIO.read(new File("src/Image/character_run1.png"));
				charRun[1] = ImageIO.read(new File("src/Image/character_run2.png"));
			} catch (Exception e) { e.printStackTrace(); }
				charRun[0] = charRun[0].getScaledInstance(165, 327, Image.SCALE_SMOOTH);
				charRun[1] = charRun[1].getScaledInstance(165, 333, Image.SCALE_SMOOTH);
		}
			
		goCharacter r = new goCharacter();
		r.start();
		
		chara.setBounds(150, 500, 190, 330);
		backGround.setLayout(null); // ������ġ �����ϱ� ���� ����� ��
		backGround.add(closeB);
		backGround.add(chara);
	
		scrollPane = new JScrollPane(backGround);
		
		frame.setContentPane(scrollPane);
		//frame.setContentPane(scrollPane);
		
		frame.repaint();
		frame.setVisible(true);
			
		//setUndecorated(true); //�޴� �� ���ֱ� - â �̵�, �ּ�ȭ, �ִ�ȭ, �ݱ� ���� ����
	}//go()
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public void start() {
		frame.setResizable(false); // â ũ�� ���� X
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		IntroMusic im = new IntroMusic("IntroMusic.mp3", true);
		im.start();
		
		JPanel sbackGround = new JPanel() { 
			ImageIcon backG = new ImageIcon("src/Image/start.jpg");
			public void paintComponent(Graphics g) {
				g.drawImage(backG.getImage(), 0, 0, null);
	            setOpaque(false);
	            super.paintComponent(g);
	        }
		};
		
		JButton start_btn;
		{
	    	// ��ư �̹��� ũ�� ���̱�
			ImageIcon st = new ImageIcon("src/Image/start_btn.png");
		   	Image s1 = st.getImage();
		   	Image s2 = s1.getScaledInstance(270, 120, Image.SCALE_SMOOTH);
		   	st.setImage(s2);
		   	start_btn = new JButton(st);
	    }
		JButton Introduce_btn;
		{
	    	// ��ư �̹��� ũ�� ���̱�
			ImageIcon in = new ImageIcon("src/Image/Introduce_btn.png");
		   	Image i1 = in.getImage();
		   	Image i2 = i1.getScaledInstance(270, 120, Image.SCALE_SMOOTH);
		   	in.setImage(i2);
		   	Introduce_btn = new JButton(in);
	    }
		
		start_btn.setBounds(1230, 600, 270, 120); // ������ġ ���� (x, y, w, h)
		start_btn.setBorderPainted(false); // ��ư �ܰ��� ���ֱ�
		start_btn.setContentAreaFilled(false); // ��ư ���� ä��� ���ֱ�
		start_btn.setFocusPainted(false); //��ư Ŭ���� �׵θ� ���ֱ�
		start_btn.setOpaque(false); // ��ư �����ϰ�
		
		
		Introduce_btn.setBounds(1230, 750, 270, 120); // ������ġ ���� (x, y, w, h)
		Introduce_btn.setBorderPainted(false); // ��ư �ܰ��� ���ֱ�
		Introduce_btn.setContentAreaFilled(false); // ��ư ���� ä��� ���ֱ�
		Introduce_btn.setFocusPainted(false); //��ư Ŭ���� �׵θ� ���ֱ�
		Introduce_btn.setOpaque(false); // ��ư �����ϰ�
		
		
		ActionListener startB = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				go();
			}
		};
		
		start_btn.addActionListener(startB);
		sbackGround.add(start_btn);
		sbackGround.add(Introduce_btn);
		
		JScrollPane SscrollPane = new JScrollPane(sbackGround);

		sbackGround.setLayout(null);
		frame.setPreferredSize(new Dimension(1920, 1080));
		frame.setContentPane(SscrollPane);
		frame.pack();
		frame.setLocationRelativeTo(null); // â ȭ�� �߾ӿ� ��ġ
	}//start()
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	class goCharacter extends Thread {
		public void run() {
			while(true) {
				character.setImage(charRun[runm]);
				chara.setIcon(character);		
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				runm++;
				if(runm > 1) 
					runm = 0;
	
				frame.repaint();
				frame.setVisible(true);
			}//while
		}//run()
	}//goCharacter
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/*class goBackGround extends Thread {
		public void run() {
			ImageIcon backG[] = new ImageIcon[2];
			backG[0] = new ImageIcon("src/Image/backGround1.jpg");
			backG[1] = new ImageIcon("src/Image/backGround2.jpg");
				
			backGround[0] = new JPanel() {
				public void paintComponent(Graphics g) {
					g.drawImage(backG[0].getImage(), 0, 0, null);
					setOpaque(false);
		            super.paintComponent(g);
				}
			};
			backGround[1] = new JPanel() {
				public void paintComponent(Graphics g) {
					g.drawImage(backG[1].getImage(), 1920, 0, null);
					setOpaque(false);
		            super.paintComponent(g);
				}
			};
				
				backGround[bG] = new JPanel() {
					public void paintComponent(Graphics g) {
						if(bX[bG] > 0) {
							g.drawImage(backG[bG].getImage(), bX[bG]--, 0, null);
							if(bG == 0) {
								backGround[bG + 1] = new JPanel() {
									public void paintComponent(Graphics g) {
										g.drawImage(backG[bG + 1].getImage(), bX[bG + 1]++, 0, null);
									}
								};
							}	
							else  {
								backGround[bG - 1] = new JPanel() {
									public void paintComponent(Graphics g) {
										g.drawImage(backG[bG - 1].getImage(), bX[bG - 1]++, 0, null);
									}
								};
							}	
						}
						else {
							
							g.drawImage(backG[bG].getImage(), bX[bG]++, 0, null);
							if(bG == 0) {
								backGround[bG + 1] = new JPanel() {
									public void paintComponent(Graphics g) {
										g.drawImage(backG[bG + 1].getImage(), bX[bG + 1]--, 0, null);
									}
								};
							}	
							else  {
								backGround[bG - 1] = new JPanel() {
									public void paintComponent(Graphics g) {
										g.drawImage(backG[bG - 1].getImage(), bX[bG - 1], 0, null);
									}
								};
							}	
						}
						
						if(bG == 0) 
							bG++;
						else
							bG--;
						scrollPane.add(backGround[0]);
						scrollPane.add(backGround[1]);
						frame.add(scrollPane);
						frame.repaint();
						frame.setVisible(true);
			        }
				
				scrollPane.add(backGround[0]);
				frame.repaint();
				frame.setVisible(true);
				
				
			//}//while
		}//run()
	}//class go BackGround 
	*/
}//class Game


