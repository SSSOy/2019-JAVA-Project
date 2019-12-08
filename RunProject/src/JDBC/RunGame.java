package JDBC;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.sql.*;

class RunGame extends JFrame {

	ImageIcon character = new ImageIcon();
	Image charRun[] = new Image[3];
	JLabel chara = new JLabel();
	ImageIcon obsImg = new ImageIcon();
	Image obst[] = new Image[2];
	JLabel obs = new JLabel();
	ImageIcon backG;
	Container contentPane;
	JPanel backGround[] = new JPanel[2];
	JPanel p = new JPanel();
	int bX[] = { 0, 1920 };
	int life = 2;
	JLabel lifeImg[] = new JLabel[3];
	JLabel item = new JLabel();
	JLabel wordP = new JLabel();
	Connection con = null;
	PreparedStatement ps = null;
	int DBcnt;
	static String word[] = new String[2];
	static int wordLength = 0;
	static int wordIndexCnt = 0;
	goBackGround goB = new goBackGround();
	obstacle ob = new obstacle();
	goCharacter run = new goCharacter();
	boolean jump = true;
	MusicPlay m = null;

	
	public RunGame() {
		setResizable(false); // 창 크기 변경 X
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setTitle("Run");
		setSize(1920, 1080);
		contentPane = getContentPane();
		
		JButton closeB, goBtn;
		m = new MusicPlay("src/Music/GameMusic.wav");
		
		//DB연동
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			con = DriverManager.getConnection("JDBC:mysql://localhost:3306/turtlegame", "root", "mirim2");

		}catch(SQLException ex) {
			System.out.println("SQLException : " + ex);
		}catch(Exception ex) {
			System.out.println("Exception : " + ex);
		}

		String sql = "select count(*) as cnt from words;";
		try {
			ps = con.prepareStatement(sql);
			ResultSet srs = ps.executeQuery();
			while(srs.next()) 
				DBcnt = Integer.parseInt(srs.getString("cnt"));
		}catch(Exception e) {
			System.out.println("Exception : " + e);
		}
		
		Random r = new Random();
		int random = r.nextInt(DBcnt) + 1;

		sql = "select english, korean from words where num = " + random + ";";
		try {
			ps = con.prepareStatement(sql);
			ResultSet srs = ps.executeQuery();
			while(srs.next()) { 
				word[0] = srs.getString("english");
				word[1] = srs.getString("korean");
			}
		}catch(Exception e) {
			System.out.println("Exception : " + e);
		}
		wordLength = word[0].length();
		wordP.setVisible(true);

		wordP.setFont(new Font("배달의민족 도현", Font.BOLD, 100));
		wordP.setHorizontalAlignment(JLabel.CENTER);
		wordP.setForeground(Color.RED);
		wordP.setVisible(false);
		contentPane.add(wordP);
		
		wordP.setBounds(790, 100, 400, 100);
		
		{
			// 버튼 이미지 크기 줄이기
			ImageIcon goBt = new ImageIcon("src/Image/go_btn.png");
			Image ci1 = goBt.getImage();
			Image ci2 = ci1.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
			goBt.setImage(ci2);
			goBtn = new JButton(goBt);
		}
		
		addKeyListener(new KeyEventListener());
		
		{
	    	// 버튼 이미지 크기 줄이기
			ImageIcon close = new ImageIcon("src/Image/close.png");
		   	Image ci1 = close.getImage();
		   	Image ci2 = ci1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		   	close.setImage(ci2);
		   	closeB = new JButton(close);
	    }
		goBtn.setBorderPainted(false); // 버튼 외곽선 없애기
		goBtn.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		goBtn.setFocusPainted(false); //버튼 클릭시 테두리 없애기
		goBtn.setOpaque(false); // 버튼 투명하게
		
		closeB.setBorderPainted(false); // 버튼 외곽선 없애기
		closeB.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		closeB.setFocusPainted(false); //버튼 클릭시 테두리 없애기
		closeB.setOpaque(false); // 버튼 투명하게
		
		goBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				requestFocus();
				goBtn.setVisible(false);
				run.start();
				goB.start();
				ob.start();}
		});
		closeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
				{	System.exit(0);		}
		});
			
		closeB.setBounds(1840, 20, 50, 50); // 절대위치 지정 (x, y, w, h)
		goBtn.setBounds(0, 0, 1920, 1080);
		add(goBtn);
		
		{
			Image lifeImage = null;
			ImageIcon life = new ImageIcon();
			
			try {lifeImage = ImageIO.read(new File("src/Image/life.png"));}
			catch(Exception e) { e.printStackTrace(); }
			lifeImage = lifeImage.getScaledInstance(60, 80, Image.SCALE_SMOOTH);
			
			for(int i = 2; i >= 0; i--) {
				life.setImage(lifeImage);
				lifeImg[i] = new JLabel();
				lifeImg[i].setIcon(life);
				lifeImg[i].setBounds(1830 - (75 * i), 940, 60, 80);
				contentPane.add(lifeImg[i]);
			}
		}
			
		{
			try {
				charRun[0] = ImageIO.read(new File("src/Image/character_run1.png"));
				charRun[1] = ImageIO.read(new File("src/Image/character_run2.png"));
				charRun[2] = ImageIO.read(new File("src/Image/character_jump.png"));
			} catch (Exception e) { e.printStackTrace(); }
			charRun[0] = charRun[0].getScaledInstance(165, 327, Image.SCALE_SMOOTH);
			charRun[1] = charRun[1].getScaledInstance(165, 333, Image.SCALE_SMOOTH);
			charRun[2] = charRun[2].getScaledInstance(200, 320, Image.SCALE_SMOOTH);
		}

		{
			try {
				obst[0] = ImageIO.read(new File("src/Image/obstacle_tree.png"));
				obst[1] = ImageIO.read(new File("src/Image/obstacle_bug.png"));
			} catch (Exception e) { e.printStackTrace(); }
			obst[0] = obst[0].getScaledInstance(300, 160, Image.SCALE_SMOOTH);
			obst[1] = obst[1].getScaledInstance(300, 90, Image.SCALE_SMOOTH);
		}
		{
			Image itemImg = null;
			try {
				itemImg = ImageIO.read(new File("src/Image/item.png"));
			} catch (Exception e) { e.printStackTrace(); }
			itemImg = itemImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
			ImageIcon itemImage = new ImageIcon();
			itemImage.setImage(itemImg);
			item.setIcon(itemImage);
		}
		
		chara.setBounds(170, 500, 200, 330);
		contentPane.setLayout(null); // 절대위치 지정하기 위해 해줘야 함
		contentPane.add(closeB);
		contentPane.add(chara);
		contentPane.add(item);
		
		
		setVisible(true);
		setPreferredSize(new Dimension(1920, 1080));
		pack();
		setLocationRelativeTo(null);
	}

	class goBackGround extends Thread {
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
					g.drawImage(backG[1].getImage(), 0, 0, null);
					setOpaque(false);
					super.paintComponent(g);
				}
			};
			
			
			while (!Thread.currentThread().isInterrupted()) {
				backGround[0].setBounds(bX[0] -= 2, 0, 1920, 1080);
				backGround[1].setBounds(bX[1] -= 2, 0, 1920, 1080);

				if (bX[0] == -1920)
					bX[0] = 1920;
				if (bX[1] == -1920)
					bX[1] = 1920;

				add(backGround[0]);
				add(backGround[1]);

				repaint();
				setVisible(true);
			}
		}// run()
	}// class go BackGround
	
	class goCharacter extends Thread {
		int runm = 0;
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				character.setImage(charRun[runm]);
				chara.setIcon(character);		
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					stop();
				}
				runm++;
				if(runm > 1) 
					runm = 0;
	
				repaint();
			}//while
		}//run()
	}//goCharacter
	
	class KeyEventListener extends KeyAdapter {
		public void keyPressed(KeyEvent event) {
			if(event.getKeyCode() == KeyEvent.VK_UP) {
				JumpCharacter jc = new JumpCharacter();
				jc.start();
			}
		}
	}
	class JumpCharacter extends Thread {
		public void run() {
			int location = 500;
			if(jump) {
				jump = false;
				for(int i = 0; i < 30; i++) {
					chara.setBounds(170, location -= 10, 200, 330);
					try {Thread.sleep(30);} 
					catch (InterruptedException e1) {e1.printStackTrace();}
					repaint();
				}

				for(int i = 0; i < 30; i++) {
					chara.setBounds(170, location += 10, 200, 330);					
					try {Thread.sleep(30);} 
					catch (InterruptedException e1) {e1.printStackTrace();}
					repaint();
				}
				jump = true;
			}
		}//run()
	}//JumpCharacter
	
	class obstacle extends Thread {
		public void run() {
			Random r = new Random();
			int location = 1920;
			int itemLocation = 2050;
			try {
				while(!Thread.currentThread().isInterrupted()) {
					int random = r.nextInt(2);
					obsImg.setImage(obst[random]);
					obs.setIcon(obsImg);	

					int itemtf = r.nextInt(2);

					int time = r.nextInt(2) + 1;
					try {
						Thread.sleep(time * 1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					boolean crash = false;
					boolean itemCrash = false;

					while(true) {

						obs.setBounds(location -= 5, 620, 400, 250);
						if(itemtf == 1) {
							item.setBounds(itemLocation -= 5, 450, 100, 100);
						}
						try {
							Thread.sleep(3);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						
						if(!crash) {
							if((chara.getX() + chara.getWidth() > location &&
									chara.getX() + chara.getWidth() < location + obs.getWidth())
									&& chara.getY() > 490) {
								crash = true;
								MusicPlay crashMusic = new MusicPlay("src/Music/crash.wav");
								if(life >= 0) lifeImg[life].setVisible(false);
								life--; 
							}
						}
						if(!itemCrash) {
							if((chara.getX() + chara.getWidth() > location &&
									chara.getX() + chara.getWidth() < location + obs.getWidth())
									&& chara.getY() < 500) {
								if(itemtf == 1) {
									itemCrash = true;
									item.setVisible(false);
									MusicPlay itemMusic = new MusicPlay("src/Music/item.wav");
									
									wordP.setText(word[0].substring(wordIndexCnt, wordIndexCnt + 1));
									wordP.setVisible(true);
									
									wordIndexCnt++;
								}
							}
						}

						if (location == -500) {
							location = 1920;
							itemLocation = 2050;
							crash = false;
							itemCrash = false;
							item.setVisible(true);
							wordP.setVisible(false);
							if(life < 0 || wordIndexCnt >= wordLength) {
								run.interrupt();
								goB.interrupt();
								ob.interrupt();
							}
							break;
						}
						add(obs);
						repaint();
					}
				}//while
			}//try
			catch(Exception e) { }
			finally {
				m.stopMusic();
				new Outro();
				dispose();
			}
		}//run()
	}//RunGame()
}//class RunGame