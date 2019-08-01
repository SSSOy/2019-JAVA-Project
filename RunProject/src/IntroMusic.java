import java.io.*;
import javax.sound.sampled.*;

public class IntroMusic {
	Clip clip = null;
	AudioInputStream audio = null;

	IntroMusic() {
		playAudio("src/Music/IntroMusic.wav");
	}

	private void playAudio(String pathName) {
		try {
			File audioFile = new File(pathName);
			audio = AudioSystem.getAudioInputStream(audioFile);

			clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void stopMusic() {
		clip.close();
		try {
			audio.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}