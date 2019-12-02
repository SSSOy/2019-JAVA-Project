package JDBC;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Intro extends JFrame {
	MusicPlay m;
	
	public Intro() {
		setResizable(false); // 창 크기 변경 X
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		m = new MusicPlay("src/Music/IntroMusic.wav");

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
			// 버튼 이미지 크기 줄이기
			ImageIcon st = new ImageIcon("src/Image/start_btn.png");
			Image s1 = st.getImage();
			Image s2 = s1.getScaledInstance(270, 120, Image.SCALE_SMOOTH);
			st.setImage(s2);
			start_btn = new JButton(st);
		}
		JButton Introduce_btn;
		{
			// 버튼 이미지 크기 줄이기
			ImageIcon in = new ImageIcon("src/Image/Introduce_btn.png");
			Image i1 = in.getImage();
			Image i2 = i1.getScaledInstance(270, 120, Image.SCALE_SMOOTH);
			in.setImage(i2);
			Introduce_btn = new JButton(in);
		}

		start_btn.setBounds(1230, 600, 270, 120); // 절대위치 지정 (x, y, w, h)
		start_btn.setBorderPainted(false); // 버튼 외곽선 없애기
		start_btn.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		start_btn.setFocusPainted(false); // 버튼 클릭시 테두리 없애기
		start_btn.setOpaque(false); // 버튼 투명하게

		Introduce_btn.setBounds(1230, 750, 270, 120); // 절대위치 지정 (x, y, w, h)
		Introduce_btn.setBorderPainted(false); // 버튼 외곽선 없애기
		Introduce_btn.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		Introduce_btn.setFocusPainted(false); // 버튼 클릭시 테두리 없애기
		Introduce_btn.setOpaque(false); // 버튼 투명하게

		start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.stopMusic();
				new RunGame();
				dispose();
			}
		});
		Introduce_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Introduce();
			}
		});
		sbackGround.add(start_btn);
		sbackGround.add(Introduce_btn);

		JScrollPane SscrollPane = new JScrollPane(sbackGround);

		sbackGround.setLayout(null);
		setPreferredSize(new Dimension(1920, 1080));
		setContentPane(SscrollPane);
		pack();
		setLocationRelativeTo(null); // 창 화면 중앙에 위치
	}// start()
}