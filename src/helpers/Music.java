package helpers;

import javax.sound.sampled.*;

public class Music {
	private Clip clip;
	public Music(String s) {
		//this.play = true;
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s)); 
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
			AudioFormat.Encoding.PCM_SIGNED,
			baseFormat.getSampleRate(),
			16,
			baseFormat.getChannels(),
			baseFormat.getChannels() * 2,
			baseFormat.getSampleRate(),
			false	
			);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat,ais);		
			clip = AudioSystem.getClip();

			clip.open(dais);

			
		} catch (Exception e) {
		
		}
	}
	
	public void Play(float vol) {
		if (clip == null) return ;
		stop();
		clip.setFramePosition(0);
		FloatControl gainControl = 
			    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(vol);
		clip.start();
		
	}
	
	public void stop() {
		if (clip.isRunning()) {
			clip.stop();
		}
		else {
			clip.start();
		}
	}
	
	public void close() {
		stop();
		clip.close();
	}
	
}
