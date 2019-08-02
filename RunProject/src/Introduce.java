import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Introduce extends JFrame{
	Introduce() {
		setResizable(false); // â ũ�� ���� X
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
			// ��ư �̹��� ũ�� ���̱�
			ImageIcon in = new ImageIcon("src/Image/close.png");
			Image i1 = in.getImage();
			Image i2 = i1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			in.setImage(i2);
			closeIntro = new JButton(in);
		}

		closeIntro.setBounds(1445, 910, 50, 50); // ������ġ ���� (x, y, w, h)
		closeIntro.setBorderPainted(false); // ��ư �ܰ��� ���ֱ�
		closeIntro.setContentAreaFilled(false); // ��ư ���� ä��� ���ֱ�
		closeIntro.setFocusPainted(false); // ��ư Ŭ���� �׵θ� ���ֱ�
		closeIntro.setOpaque(false); // ��ư �����ϰ�
		closeIntro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // â �ݱ�
			}
		});
		
		Introduce.add(closeIntro);
		add(Introduce);
		setPreferredSize(new Dimension(1920, 1080));
		pack();
		setLocationRelativeTo(null);
	}
}
