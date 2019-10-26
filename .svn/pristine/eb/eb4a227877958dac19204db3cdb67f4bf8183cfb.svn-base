package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Scanner {
	private BufferedReader inStream;
	private static final int SIZE = 8;

	/**
	 * Constructor for the Scanner class.
	 * 
	 * @param myFile
	 *            a reference to a File object.
	 * @throws FileNotFoundException
	 *             if the file was not found, such exception will be thrown.
	 */
	public Scanner(File myFile) throws FileNotFoundException {
		try {
			this.inStream = new BufferedReader(new FileReader(myFile));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("No se ha encontrado el archivo");
		}
	}

	/**
	 * Getter for the stream.
	 * 
	 * @return the current stream.
	 */
	public BufferedReader getInStream() {
		return inStream;
	}

	/**
	 * Retrieves a line from the current stream.
	 * 
	 * @return a char array containing all the info in the line retrieved from
	 *         the current stream.
	 * @throws ScanException
	 *             in case an IOException occurs, a ScanException will be
	 *             thrown.
	 */
	private char[] getLine() throws ScanException {
		String line = null;
		try {
			line = getInStream().readLine();
		} catch (IOException e) {
			throw new ScanException(
					"Se ha producido un error en la lectura del archivo");
		}
		if (line == null) {
			return null;
		}
		line = line.replaceAll("\t", "");
		return line.toCharArray();
	}

	/**
	 * Constructs and initialize a Board object.
	 * 
	 * @return a Board object.
	 * @throws ScanException
	 *             in case an IOException occurs, a ScanException will be
	 *             thrown.
	 */
	public Board loadBoard() throws ScanException {
		Board board = new Board(SIZE, SIZE);
		char[] line = null;
		int i = 0;
		int j = 0;

		while ((line = getLine()) != null) {
			while (j < SIZE) {
				Blob blob = new Emptyness(board, i, j);
				Cell cell = new Cell(blob);
				if (line[j] == '1') {
					blob = new Player(board, i, j);
					board.add((Player) blob);
				} else if (line[j] == '2') {
					blob = new Computer(board, i, j);
					board.add((Computer) blob);
				}
				cell.setBlob(blob);
				board.put(cell);
				j++;
			}
			j = 0;
			i++;
		}
		return board;
	}
}