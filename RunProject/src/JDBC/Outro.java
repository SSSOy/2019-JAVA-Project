package JDBC;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.imageio.*;
import javax.swing.*;

class Outro extends JFrame {
	public Outro() {
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel backGround = new JPanel() {
			ImageIcon icon = new ImageIcon("src/Image/end.jpg");
			Image img = icon.getImage(); 
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, this);
			}
		};


		JLabel wordEng = new JLabel();
		JLabel wordKor = new JLabel();
		
		wordEng.setFont(new Font("배달의민족 도현", Font.BOLD, 100));
		wordKor.setFont(new Font("배달의민족 도현", Font.BOLD, 100));
		wordEng.setForeground(Color.WHITE);
		wordKor.setForeground(Color.WHITE);
		wordEng.setHorizontalAlignment(JLabel.CENTER);
		wordKor.setHorizontalAlignment(JLabel.CENTER);
		wordEng.setBounds(570, 360, 500, 200);
		wordKor.setBounds(570, 500, 500, 200);
		wordEng.setText(RunGame.word[0]);
		wordKor.setText(RunGame.word[1]);
		backGround.add(wordEng);
		backGround.add(wordKor);
		
		String music = null;
		
		//DB연동
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			con = DriverManager.getConnection("JDBC:mysql://localhost:3306/turtlegame", "root", "mirim2");

		}catch(SQLException ex) {
			System.out.println("SQLException : " + ex);
		}catch(Exception ex) {
			System.out.println("Exception : " + ex);
		}

		String sql = "select audio from words where english = '" + RunGame.word[0] + "';";
		try {
			ps = con.prepareStatement(sql);
			ResultSet srs = ps.executeQuery();
			while(srs.next()) 
				music = srs.getString("audio");
		}catch(Exception e) {
			System.out.println("Exception : " + e);
		}
		
		ImageIcon gC = new ImageIcon();
		Image gameC = null;
		JLabel gameclear = new JLabel();
		try {
			gameC = ImageIO.read(new File("src/Image/gameClear.png"));
		} catch (Exception e) { e.printStackTrace(); }
		gameC = gameC.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
		gC.setImage(gameC);
		gameclear.setIcon(gC);
		gameclear.setBounds(770, 150, 400, 200);
		backGround.add(gameclear);
		
		JButton close_btn = new JButton();
		{
			// 버튼 이미지 크기 줄이기
			ImageIcon in = new ImageIcon("src/Image/clear_btn.png");
			Image i1 = in.getImage();
			Image i2 = i1.getScaledInstance(230, 100, Image.SCALE_SMOOTH);
			in.setImage(i2);
			close_btn = new JButton(in);
		}
		close_btn.setBounds(740, 850, 230, 100); // 절대위치 지정 (x, y, w, h)
		close_btn.setBorderPainted(false); // 버튼 외곽선 없애기
		close_btn.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		close_btn.setFocusPainted(false); // 버튼 클릭시 테두리 없애기
		close_btn.setOpaque(false); // 버튼 투명하게

		close_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		backGround.add(close_btn);
		
		JButton replay_btn = new JButton();
		{
			// 버튼 이미지 크기 줄이기
			ImageIcon in = new ImageIcon("src/Image/replay_btn.png");
			Image i1 = in.getImage();
			Image i2 = i1.getScaledInstance(230, 100, Image.SCALE_SMOOTH);
			in.setImage(i2);
			replay_btn = new JButton(in);
		}
		replay_btn.setBounds(990, 850, 230, 100); // 절대위치 지정 (x, y, w, h)
		replay_btn.setBorderPainted(false); // 버튼 외곽선 없애기
		replay_btn.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		replay_btn.setFocusPainted(false); // 버튼 클릭시 테두리 없애기
		replay_btn.setOpaque(false); // 버튼 투명하게

		replay_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Intro();
				dispose();
			}
		});
		backGround.add(replay_btn);
		
		JButton audioPlay_btn = new JButton();
		{
			// 버튼 이미지 크기 줄이기
			ImageIcon in = new ImageIcon("src/Image/englishAudio_btn.png");
			Image i1 = in.getImage();
			Image i2 = i1.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			in.setImage(i2);
			audioPlay_btn = new JButton(in);
		}
		audioPlay_btn.setBounds(1150, 470, 150, 150); // 절대위치 지정 (x, y, w, h)
		audioPlay_btn.setBorderPainted(false); // 버튼 외곽선 없애기
		audioPlay_btn.setContentAreaFilled(false); // 버튼 영역 채우기 없애기
		audioPlay_btn.setFocusPainted(false); // 버튼 클릭시 테두리 없애기
		audioPlay_btn.setOpaque(false); // 버튼 투명하게
		
		final String music2 = music;
		System.out.println(music2);
		audioPlay_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusicPlay m = new MusicPlay(music2);
			}
		});
		backGround.add(audioPlay_btn);
		
		JLabel Message = new JLabel();
		
		Message.setFont(new Font("배달의민족 도현", Font.BOLD, 45));
		Message.setForeground(Color.WHITE);
		Message.setHorizontalAlignment(JLabel.CENTER);
		Message.setBounds(470, 670, 1000, 200);
		
		if(RunGame.wordIndexCnt == RunGame.wordLength) {
			Message.setText("거북이가 단어 획득에 성공했습니다! 축하해요!");
		}
		else 
			Message.setText("단어 획득에 실패했어요. 다시 도전해보세요!");
		backGround.add(Message);
		
		JScrollPane SscrollPane = new JScrollPane(backGround);

		backGround.setLayout(null);
		setPreferredSize(new Dimension(1920, 1080));
		setContentPane(SscrollPane);
		pack();
		setLocationRelativeTo(null); // 창 화면 중앙에 위치
	}
}
