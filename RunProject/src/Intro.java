import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Intro extends JFrame {
	IntroMusic m;
	
	public Intro() {
		setResizable(false); // â ũ�� ���� X
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		m = new IntroMusic();

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
		start_btn.setFocusPainted(false); // ��ư Ŭ���� �׵θ� ���ֱ�
		start_btn.setOpaque(false); // ��ư �����ϰ�

		Introduce_btn.setBounds(1230, 750, 270, 120); // ������ġ ���� (x, y, w, h)
		Introduce_btn.setBorderPainted(false); // ��ư �ܰ��� ���ֱ�
		Introduce_btn.setContentAreaFilled(false); // ��ư ���� ä��� ���ֱ�
		Introduce_btn.setFocusPainted(false); // ��ư Ŭ���� �׵θ� ���ֱ�
		Introduce_btn.setOpaque(false); // ��ư �����ϰ�

		ActionListener startB = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.stopMusic();
				new runGame();
			}
		};

		start_btn.addActionListener(startB);
		sbackGround.add(start_btn);
		sbackGround.add(Introduce_btn);

		JScrollPane SscrollPane = new JScrollPane(sbackGround);

		sbackGround.setLayout(null);
		setPreferredSize(new Dimension(1920, 1080));
		setContentPane(SscrollPane);
		pack();
		setLocationRelativeTo(null); // â ȭ�� �߾ӿ� ��ġ
	}// start()
}