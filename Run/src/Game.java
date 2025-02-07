/*import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

class Game extends JFrame {
	int runm = 0;
	boolean run = true;
	ImageIcon character = new ImageIcon();
	Image charRun[] = new Image[2];
	JLabel chara = new JLabel();
	
	Game() {
		ImageIcon backG;
		JButton closeB;
		JScrollPane scrollPane;
	
		setPreferredSize(new Dimension(1920, 1080)); 
		backG = new ImageIcon("src/Image/backGround.jpg");
		
		// 배경이미지 설정
		JPanel backGround = new JPanel() { 
			public void paintComponent(Graphics g) {
				g.drawImage(backG.getImage(), 0, 0, null);
	            setOpaque(false);
	            super.paintComponent(g);
	        }
	     };
	    
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
			charRun[0] = charRun[0].getScaledInstance(190, 330, Image.SCALE_SMOOTH);
			charRun[1] = charRun[1].getScaledInstance(175, 330, Image.SCALE_SMOOTH);
		}
		
		Thread Run = new Thread( ){
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
				}
			}
		};
		Run.start();
		
		chara.setBounds(100, 550, 190, 330);
		backGround.setLayout(null); // 절대위치 지정하기 위해 해줘야 함
		backGround.add(closeB);
		backGround.add(chara);
		
		scrollPane = new JScrollPane(backGround);
		setContentPane(scrollPane);
		pack();
		
		
		//setUndecorated(true); //메뉴 바 없애기 - 창 이동, 최소화, 최대화, 닫기 별도 구현
		setResizable(false); // 창 크기 변경 X
		setVisible(true);
		setLocationRelativeTo(null); // 창 화면 중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Game();
	}
}*/