package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import backend.Board;
import backend.Game;

public class GameFrame extends CommonFrame {

	private static final long serialVersionUID = 1L;
	private BoardPanel bp;
	private Game game;
	private Board board;
	private JMenuBar bar;

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 *         GameFrame's constructor.
	 * 
	 */
	public GameFrame() {
		super();
		setSize(cols() * cellSize() + fixWidth(), rows() * cellSize()
				+ fixHeight() + 25);
		try {
			bar = new JMenuBar();
			setBar(bar);
			add(bar);
			bar.setVisible(true);
			bp = new BoardPanel(rows(), cols(), cellSize());
			board = Board.defaultBoard();
			bp.setBoard(board);
			add(bp);
			game = new Game(board);
			// por parametro...
			play(game);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 *         All the game progress, we decided there is no tie, because this
	 *         version of the game is about a Virus and an Anti-virus if the
	 *         amount of virus equals the amount of anti-viruses the anti-virus
	 *         wins. This also makes the game more difficult for the player.
	 * 
	 * @param game
	 *            the game to be played.
	 * 
	 */
	public void play(Game game) {
		bp.setListener(new BoardPanelListener() {
			boolean locked = false;
			int initialRow;
			int initialColumn;

			@Override
			public void cellClicked(int row, int column) {
				if (board.at(row, column).containsPlayer()) {
					if (locked) {
						bp.lightOff(initialRow, initialColumn);
					}
					locked = true;
					bp.lightOn(row, column);
					initialRow = row;
					initialColumn = column;
				} else {
					if (locked) {
						bp.lightOff(initialRow, initialColumn);
						locked = false;
						if (GameFrame.this.game.move(initialRow, initialColumn,
								row, column)) {
							bp.setBoard(GameFrame.this.game.board());
							GameFrame.this.bp.paintImmediately(0, 0, cols()
									* cellSize(), rows() * cellSize());
							bp.disableListener();
							Sounds.MOVE.play();
							GameFrame.this.game.calculateNextMovement();
							Sounds.MOVE.play();
							if (!GameFrame.this.game.gameOver()) {
								bp.setBoard(GameFrame.this.game.board());
								GameFrame.this.bp.paintImmediately(0, 0, cols()
										* cellSize(), rows() * cellSize());
								bp.enableListener();
							} else {
								String img = (GameFrame.this.game
										.playerHasWon()) ? "win.png"
										: "lose.png";
								EndFrame end = new EndFrame(img);
								setVisible(false);
								end.setVisible(true);
							}
						}
					}
				}
				bp.repaint();
			}
		});
	}

	/**
	 * @author German Romarion & Gabriel Zanzotti
	 * 
	 *         sets the bar with buttons and details.
	 * 
	 * @param b
	 *            bar to be setted.
	 * 
	 */
	private void setBar(JMenuBar b) {
		JMenu file = new JMenu("file");
		final JMenuItem restart = new JMenuItem("Restart");
		JMenuItem quit = new JMenuItem("Quit");

		restart.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GameFrame game = new GameFrame();
				restart.removeMouseListener(this);
				setVisible(false);
				game.setVisible(true);
			}
		});

		quit.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		b.setBounds(0, 0, cols() * cellSize() + fixWidth(), 25);
		file.setForeground(Color.RED);
		restart.setForeground(Color.DARK_GRAY);
		quit.setForeground(Color.DARK_GRAY);
		b.add(file);
		file.add(restart);
		file.add(quit);
	}
}