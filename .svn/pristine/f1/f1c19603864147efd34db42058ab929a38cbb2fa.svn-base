package frontend;

import java.io.File;
import java.io.FileNotFoundException;

import backend.Game;
import backend.ScanException;
import backend.Scanner;

public class Main {

	private static int depthOrTime = -1;
	private static int depthOrTimeLocation;
	private static boolean time = false;
	private static boolean prune = false;

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * Here is where everything starts. 
	 * 
	 * @param args 
	 *			arguments received for the execution of the program.
	 * 
	 */
	public static void main(String[] args) {
		if (args.length == 6 || args.length == 7) {
			depthOrTimeLocation = 5;
		} else {
			depthOrTimeLocation = 2;
		}
		checkDepthOrTime(Integer.parseInt(args[depthOrTimeLocation]));
		time = args[depthOrTimeLocation - 1].equals("-maxtime");
		if (args.length == 4 || args.length == 7) {
			prune = args[depthOrTimeLocation + 1].equals("-prune");
		}
		if (depthOrTime == -1) {
			throw new IllegalArgumentException(
					"Error: incompatible depth value.");
		}
		if (args[0].equals("-visual")) {
			visualMode(checkDepthOrTime(depthOrTime), time, prune);
		} else {
			consoleMode(args[1], args[3], depthOrTime, time, prune);
		}
	}

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * controls if the AI will check for time or depth. 
	 * 
	 * @param n 
	 * 			amount of depth/time.
	 * 
	 */
	private static int checkDepthOrTime(int n) {
		if (n > 0) {
			depthOrTime = n;
		}
		return depthOrTime;
	}

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * executes the visual mode. 
	 * 
	 * @param n
	 * 			depth/time amount.
	 * @param time
	 * 			boolean to know if we work with time or depth. 
	 * @param prune
	 * 			determines if the algorithm will use alpha beta prune.
	 * 
	 */
	private static void visualMode(int n, boolean time, boolean prune) {
		Game.setProperties(n, time, prune, false);
		final MenuFrame menu = new MenuFrame();
		Sounds.MENUMUSIC.play();
		menu.setVisible(true);
	}

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 * execute console mode. 
	 * 
	 * @param fileName
	 * 			name of the board to calculate.
	 * @param playerTurn
	 * 			used to validate if its player's turn.
	 * @param n
	 * 			amount of depth or time.
	 * @param time
	 * 			says if it's time.
	 * @param prune
	 * 			says if it has prune.
	 *  
	 */
	private static void consoleMode(String fileName, String playerTurn, int n,
			boolean time, boolean prune) {
		try {
			Scanner scanner = new Scanner(new File(fileName));
			try {
				Game game = new Game(scanner.loadBoard());
				Game.setProperties(n, time, prune, playerTurn.equals("1"));
				System.out.println(game.calculateNextMovement());
			} catch (ScanException e) {
				System.out
						.println("ERROR: The board file sintaxis is incorrect...");
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: The file " + fileName
					+ " has not been found...");
		}
	}
}