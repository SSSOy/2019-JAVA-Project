package JDBC;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Introduce extends JFrame{
	Introduce() {
		setResizable(false); // 창 크기 변경 X
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		ImageIcon Intro = new ImageIcon("src/Image/Introduce.jpg");

		JPanel Introduce = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(Intro.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		Introduce.setLayout(null);
		JButton closeIntro;
		{
			// 버튼 이미지 크기 줄이기
			ImageIcon in = new ImageIcon("src/Image/close.png");
			Image i1 = in.getImage();
			Image i2 = i1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			in.setImage(i2);
			closeIntro = new JButton(in);
		}

		closeIntro.setBounds(1400, 910, 50, 50); // 절대위치 지정 (x, y, w, h)
		closeIntro.setBorderPainted(false); // 버튼 외곽선 없애기
		closeIntro.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		closeIntro.setFocusPainted(false); // 버튼 클릭시 테두리 없애기
		closeIntro.setOpaque(false); // 버튼 투명하게
		closeIntro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // 창 닫기
			}
		});
		
		Introduce.add(closeIntro);
		add(Introduce);
		setPreferredSize(new Dimension(1920, 1080));
		pack();
		setLocationRelativeTo(null);
	}
}
