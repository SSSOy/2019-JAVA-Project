import javax.swing.*;
import java.awt.*;

class Test{
		
		JFrame frame = new JFrame();
		int runm = 0;
		boolean run = true;
		ImageIcon character = new ImageIcon();
		Image charRun[] = new Image[2];
		JLabel chara = new JLabel();
		ImageIcon backG;
		JButton closeB;
		JScrollPane scrollPane;
		JPanel backGround[] = new JPanel[2];
		final int ALL_WIDTH = 1920; 	// 전체 frame의 폭 1920
		final int ALL_HEIGHT = 1080; 	// 전체 frame의 높이 1080
		
		int bG = 0;
		int bX[] = {0, 1920};
		
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public static void main(String ar[]) {
			Test t = new Test();
			t.god();
		}
		
		public void god() {
			frame.setResizable(false); // 창 크기 변경 X
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			goBackGround g = new goBackGround();
			g.start();
			
			frame.setVisible(true);
			frame.setPreferredSize(new Dimension(1920, 1080));
			//frame.setContentPane(scrollPane);
		
			frame.pack();
			frame.setLocationRelativeTo(null);
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
				
				frame.setLayout(null);
				while(true) {
					backGround[0].setBounds(bX[0]--, 0, 1920, 1080);
					backGround[1].setBounds(bX[1]--, 0, 1920, 1080);
							
					if(bX[0] == -1920)
						bX[0] = 1920;
					if(bX[1] == -1920)
						bX[1] = 1920;
					
					frame.add(backGround[0]);
					frame.add(backGround[1]);
					
					frame.repaint();
					frame.setVisible(true);
				}
			}//run()
		}//class go BackGround 
}