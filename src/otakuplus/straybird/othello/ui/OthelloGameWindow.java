package otakuplus.straybird.othello.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import otakuplus.straybird.othello.model.ChessBoard;
import otakuplus.straybird.othello.model.Chessman;

public class OthelloGameWindow {
	protected Shell shell;
	protected ChessBoard chessBoard;

	public OthelloGameWindow() {
		if (chessBoard == null) {
			chessBoard = new ChessBoard();
			chessBoard.searchSuggestedChessmanPosition();
		}
	}

	protected void updateGame() {
		chessBoard.searchSuggestedChessmanPosition();
	}

	public void open() {
		final Display display = Display.getDefault();
		createContents();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	protected void createContents() {
		shell = new Shell(SWT.SHELL_TRIM);
		shell.setText("OthelloGame");
		Image image = new Image(Display.getDefault(), "Othello.png");
		shell.setBackgroundImage(image);
		image.dispose();
		GridLayout othellGameLayout = new GridLayout();
		shell.setLayout(othellGameLayout);

		final Canvas canvas = new Canvas(shell, SWT.NONE);
		GridData canvasGridData = new GridData(GridData.FILL_BOTH);
		canvas.setLayoutData(canvasGridData);
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Image image = new Image(Display.getDefault(), "Othello.png");
				e.gc.drawImage(image, 0, 0, 391, 192, 0, 0, 391, 192);

				// drawing chessboard
				e.gc.setForeground(Display.getDefault().getSystemColor(
						SWT.COLOR_BLACK));
				e.gc.setBackground(Display.getDefault().getSystemColor(
						SWT.COLOR_GRAY));
				e.gc.drawRectangle(0, 0, 320, 320);
				e.gc.fillRectangle(1, 1, 319, 319);

				int i = 0, chessmanStat = 0;
				for (i = 1; i < 8; i++) {
					e.gc.drawLine(40 * i, 0, 40 * i, 320);
					e.gc.drawLine(0, 40 * i, 320, 40 * i);
				}

				// drawing chessmen
				i = 0;
				for (i = 0; i < 64; i++) {
					chessmanStat = chessBoard.getChessman(i / 8, i % 8)
							.getChessman();
					if (chessmanStat == Chessman.CHESSMAN_BLACK) {
						e.gc.setBackground(Display.getDefault().getSystemColor(
								SWT.COLOR_BLACK));
						e.gc.drawOval((i % 8) * 40 + 4, (i / 8) * 40 + 4, 32,
								32);
						e.gc.fillOval((i % 8) * 40 + 5, (i / 8) * 40 + 5, 31,
								31);
					} else if (chessmanStat == Chessman.CHESSMAN_WHITE) {
						e.gc.setBackground(Display.getDefault().getSystemColor(
								SWT.COLOR_WHITE));
						e.gc.drawOval((i % 8) * 40 + 4, (i / 8) * 40 + 4, 32,
								32);
						e.gc.fillOval((i % 8) * 40 + 5, (i / 8) * 40 + 5, 31,
								31);
					}
				}

				// drawing suggested position
				int suggestedPosition[] = chessBoard.getSuggestedPosition();
				e.gc.setBackground(Display.getDefault().getSystemColor(
						SWT.COLOR_CYAN));
				for (i = 0; i < suggestedPosition.length; i++) {
					if (suggestedPosition[i] == 1) {
						e.gc.drawRectangle((i % 8) * 40 + 4, i / 8 * 40 + 4,
								32, 32);
						e.gc.fillRectangle((i % 8) * 40 + 5, i / 8 * 40 + 5,
								31, 31);
					}
				}

				image.dispose();
			}
		});
		
		canvas.addMouseListener(new MouseListener(){

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseDown(MouseEvent e) {
				int x = e.x;
				int y = e.y;
				if(chessBoard.setChessman( y / 40, x / 40) == true)
				{
					chessBoard.turnEnd();
					chessBoard.searchSuggestedChessmanPosition();
				}
				canvas.redraw();
			}

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	public static void main(String[] args) {
		try {
			OthelloGameWindow othelloGameWindow = new OthelloGameWindow();
			othelloGameWindow.open();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
