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
		if (key == 38) { // �� ȭ��ǥ
			System.out.println("����!");
		} else if (key == 40) { // �Ʒ� ȭ��ǥ
			System.out.println("�����̵�!");
		} else {

		}
	}
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
