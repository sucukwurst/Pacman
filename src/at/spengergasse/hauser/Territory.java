package at.spengergasse.hauser;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Territory extends JPanel {
	private int columns, rows;
	private int[][] fields;
	private static final int MAXPOINTS = 14;
	private static final int MAXPACS = 5;
	private ArrayList<Pacman> pacs;

	private int width, height;
	private static final int imsize = 32;
	private JFrame frame;
	private BufferedImage tileim, wallim, pacim[] = new BufferedImage[MAXPACS],
			pointim[] = new BufferedImage[MAXPOINTS];
	private static final int SLEEPTIME = 200;
	private Territory terr;
	private Pacman pac;
	private Pacman pac1;
	private Pacman pac2;

	public Territory() {

	}

	public Territory(int columns, int rows) {
		makeTerritory(columns, rows);
	}

	public Territory(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(openFile("territories/" + fileName)));
			String line = reader.readLine();
			columns = Integer.parseInt(line);
			line = reader.readLine();
			rows = Integer.parseInt(line);
			makeTerritory(columns, rows);
			ArrayList<int[]> cornPosition = new ArrayList<int[]>();
			int pacx = -1;
			int pacy = -1;
			Direction hamDir = Direction.NONE;
			for (int i = 0; i < rows; i++) {
				line = reader.readLine();
				for (int j = 0; j < columns; j++) {
					switch (line.charAt(j)) {
					case '#':
						setWall(j, i);
						break;
					case ' ':
						break;
					case '*':
						cornPosition.add(new int[] { j, i });
						break;
					case '^':
						cornPosition.add(new int[] { j, i });
						pacx = j;
						pacy = i;
						hamDir = Direction.NORTH;
						break;
					case '>':
						cornPosition.add(new int[] { j, i });
						pacx = j;
						pacy = i;
						hamDir = Direction.EAST;
						break;
					case 'v':
						cornPosition.add(new int[] { j, i });
						pacx = j;
						pacy = i;
						hamDir = Direction.SOUTH;
						break;
					case '<':
						cornPosition.add(new int[] { j, i });
						pacx = j;
						pacy = i;
						hamDir = Direction.WEST;
						break;
					default:
						throw new RuntimeException("Territory error.");
					}
				}
			}
			for (int i = 0; i < cornPosition.size(); i++) {
				int[] p = cornPosition.get(i);
				line = reader.readLine();
				int count = 1;
				setCorns(p[0], p[1], count);
			}

			repaint();

		} catch (FileNotFoundException fnf) {
			throw new IllegalArgumentException("file " + fileName + " not found", fnf);
		} catch (IOException io) {
			throw new IllegalArgumentException("file " + fileName + " has wrong format", io);
		}
	}

	private void makeTerritory(int columns, int rows) {
		if (columns < 2 || rows < 2)
			throw new IllegalArgumentException("wrong size for territory: " + columns + " columns, " + rows + " rows");
		this.columns = columns;
		this.rows = rows;
		fields = new int[columns][rows];
		pacs = new ArrayList<Pacman>();

		width = columns * imsize;
		height = rows * imsize;
		loadImages();
		frame = new JFrame("PacMan");
		frame.add(this);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(d.width / 2 - frame.getWidth() / 2, d.height / 2 - frame.getHeight() / 2);
		frame.setVisible(true);
		// workaround for possible BlueJ bug? Frame starts in iconified state
		// !!!
		frame.setExtendedState(Frame.ICONIFIED);
		frame.setExtendedState(Frame.NORMAL);
	}

	public void addPacman(Pacman h) {
		if (pacs.size() >= MAXPACS)
			throw new IllegalArgumentException("max number of hamsteres reached");
		pacs.add(h);
		repaint();
	}

	public Pacman getPacman(int i) {
		if (i < 0 || i > pacs.size())
			throw new IllegalArgumentException("Pacman " + i + " doesn't exist");
		return pacs.get(i);
	}

	public int getColumns() {
		return this.columns;
	}

	public int getRows() {
		return this.rows;
	}

	public void checkArgs(int column, int row) {
		if (column < 0 || row < 0 || column >= this.columns || row >= this.rows)
			throw new IllegalArgumentException(
					"Attempt to access field outside territory: column = " + column + " row= " + row);
	}

	public boolean isFree(int column, int row) {
		checkArgs(column, row);
		return fields[column][row] >= 0;
	}

	public void setWall(int column, int row) {
		checkArgs(column, row);
		fields[column][row] = -1;
		repaint();
	}

	public void setFree(int column, int row) {
		checkArgs(column, row);
		fields[column][row] = 0;
		repaint();
	}

	public int getCorns(int column, int row) {
		checkArgs(column, row);
		if (fields[column][row] < 0)
			throw new IllegalArgumentException("There is a wall here: column = " + column + " row= " + row);
		return fields[column][row];
	}

	public void setCorns(int column, int row, int corns) {
		checkArgs(column, row);
		if (fields[column][row] < 0)
			throw new IllegalArgumentException("There is a wall here: column = " + column + " row= " + row);
		if (fields[column][row] >= MAXPOINTS)
			throw new IllegalArgumentException("max number of corns at: column = " + column + " row= " + row);
		fields[column][row] = corns;
		repaint();
	}

	public void decrementPoints(int column, int row) {
		checkArgs(column, row);
		if (fields[column][row] < 0)
			throw new IllegalArgumentException("There is a wall here: column = " + column + " row= " + row);
		if (fields[column][row] == 0)
			throw new IllegalArgumentException("no corns at: column = " + column + " row= " + row);
		fields[column][row]--;
	}

	public void draw() {
		try {
			Thread.sleep(185);
		} catch (InterruptedException e) {
		}
		repaint();
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < columns; i++)
			for (int j = 0; j < rows; j++) {
				if (fields[i][j] < 0)
					g2.drawImage(wallim, i * imsize, j * imsize, null);
				else {
					g2.drawImage(tileim, i * imsize, j * imsize, null);
					if (fields[i][j] > 0)
						g2.drawImage(pointim[fields[i][j] - 1], i * imsize, j * imsize, null);
				}
			}
		for (int i = 0; i < pacs.size(); i++) {
			Pacman h = pacs.get(i);
			AffineTransform trans = new AffineTransform();
			trans.translate(h.getColumn() * imsize, h.getRow() * imsize);
			trans.translate(imsize / 2, imsize / 2);
			switch (h.getDirection()) {
			case NORTH:
				trans.rotate(-Math.PI / 2.0);
				break;
			case WEST:
				trans.rotate(Math.PI);
				break;
			case SOUTH:
				trans.rotate(Math.PI / 2.0);
				break;
			case EAST:
				break;
			}
			trans.translate(-imsize / 2, -imsize / 2);
			g2.drawImage(pacim[i], trans, null);
		}
	}

	private void loadImages() {
		try {
			tileim = getImage("images/tile.jpg");
			wallim = getImage("images/wall.png");
			for (int i = 0; i < MAXPACS; i++)
				pacim[i] = getImage("images/pacman(" + i + ").png");
			for (int i = 0; i < MAXPOINTS; i++)
				pointim[i] = getImage("images/point" + (i + 1) + ".png");
		} catch (IOException e) {
			System.err.println("unable to open image files");
		}
	}

	private BufferedImage getImage(String fileName) throws IOException {
		URL imageURL = getClass().getClassLoader().getResource(fileName);
		if (imageURL == null)
			return null;
		return ImageIO.read(imageURL);
	}

	private InputStream openFile(String fileName) throws IOException {
		URL url = getClass().getClassLoader().getResource(fileName);
		if (url == null)
			throw new IOException("resource not found: " + fileName);
		return url.openStream();
	}

	public Frame getFrame() {
		return frame;
	}

	public int getFields(int x, int y) {
		return fields[x][y];
	}
}