package frontend;


import java.io.*;

import sun.audio.*;


/**
 * 
 * Class originally created for the Object Oriented Programming course Special Project
 * 
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         READ: NO EVALUAR.
 *         
 *         Manages sound files.
 */
public class SoundManager {
	private InputStream in;
	private AudioStream as;
	private String fileName;
	/**
	 * Creates a new sound manager for a specific audio file.
	 * 
	 * @param s
	 *            A string representing the sound file path.
	 */
	public SoundManager(String s) {
		fileName = s;
	}

	private void resetStreams(){
		try {
			in = new FileInputStream(fileName);
			as = new AudioStream(in);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Plays the sound.
	 */
	public void play() {
		resetStreams();
		AudioPlayer.player.start(as);

	}
	

	/**
	 * Plays the sound.
	 */
	public void stop() {
		AudioPlayer.player.stop(as);
	}
}