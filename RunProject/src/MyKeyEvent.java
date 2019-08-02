import java.awt.event.*;


class MyKeyEvent implements KeyListener {
	MyKeyEvent() {
		System.out.println("dd");
		
	}
	public void keyTyped(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyPressed(java.awt.event.KeyEvent e) {
		System.out.println("gd");
		int key = e.getKeyCode();
		if (key == 38) { // 위 화살표
			System.out.println("점프!");
		} else if (key == 40) { // 아래 화살표
			System.out.println("슬라이드!");
		} else {

		}
	}
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
