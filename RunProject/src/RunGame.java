import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

class RunGame extends JFrame {

	boolean run = true;
	ImageIcon character = new ImageIcon();
	Image charRun[] = new Image[2];
	JLabel chara = new JLabel();
	ImageIcon backG;
	JButton closeB;
	Container contentPane;
	JPanel backGround[] = new JPanel[2];
	JPanel p = new JPanel();
	final int ALL_WIDTH = 1920; // 전체 frame의 폭 1920
	final int ALL_HEIGHT = 1080; // 전체 frame의 높이 1080
	int bX[] = { 0, 1920 };

	
	public RunGame() {
		setResizable(false); // 창 크기 변경 X
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setTitle("Run");
		setSize(1920, 1080);
		contentPane = getContentPane();
		
		goBackGround goB = new goBackGround();
		goB.start();
		MusicPlay music = new MusicPlay("src/Music/GameMusic.wav");

		{
	    	// 버튼 이미지 크기 줄이기
			ImageIcon close = new ImageIcon("src/Image/close.png");
		   	Image ci1 = close.getImage();
		   	Image ci2 = ci1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		   	close.setImage(ci2);
		   	closeB = new JButton(close);
	    }

		closeB.setBorderPainted(false); // 버튼 외곽선 없애기
		closeB.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		closeB.setFocusPainted(false); //버튼 클릭시 테두리 없애기
		closeB.setOpaque(false); // 버튼 투명하게
		
		closeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
				{	System.exit(0);		}
		});
			
		closeB.setBounds(1840, 20, 50, 50); // 절대위치 지정 (x, y, w, h)
			
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
		contentPane.setLayout(null); // 절대위치 지정하기 위해 해줘야 함
		contentPane.add(closeB);
		contentPane.add(chara);
	
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
			
			backGround[0].requestFocus();
			backGround[0].addKeyListener(new MyKeyEvent());
			
			while (true) {
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
	
				repaint();
			}//while
		}//run()
	}//goCharacter
}