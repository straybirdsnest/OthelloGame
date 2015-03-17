package otakuplus.straybird.othellogame.ui;

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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import otakuplus.straybird.othellogame.ApplicationState;
import otakuplus.straybird.othellogame.MainApplication;
import otakuplus.straybird.othellogame.model.ChessBoard;
import otakuplus.straybird.othellogame.model.Chessman;

public class OthelloGameWindow {
	protected MainApplication mainApplication;

	protected Display display;
	protected Shell shell;

	protected Label whiteLabel;
	protected Label blackLabel;
	protected Label whiteTimer;
	protected Label blackTimer;

	protected ChessBoard chessBoard;

	public OthelloGameWindow(MainApplication mainApplication) {
		if (mainApplication != null) {
			this.mainApplication = mainApplication;
		}
		if (chessBoard == null) {
			chessBoard = new ChessBoard();
			chessBoard.searchSuggestedChessmanPosition();
		}
	}

	protected void createContents() {
		shell = new Shell(SWT.SHELL_TRIM);
		shell.setText("OthelloGame");
		shell.setLocation((Display.getDefault().getBounds().width - 400) / 2,
				(Display.getDefault().getBounds().height - 400) / 2);
		GridLayout othellGameLayout = new GridLayout();
		othellGameLayout.numColumns = 5;
		shell.setLayout(othellGameLayout);

		final Canvas canvas = new Canvas(shell, SWT.NONE);
		GridData canvasGridData = new GridData(GridData.FILL_BOTH);
		canvasGridData.widthHint = 321;
		canvasGridData.heightHint = 321;
		canvasGridData.verticalSpan = 4;
		canvas.setLayoutData(canvasGridData);

		Label whiteUserlabel = new Label(shell, SWT.CENTER);
		whiteUserlabel.setText("白方");
		whiteLabel = new Label(shell, SWT.CENTER);
		whiteLabel.setText("White: 02");
		blackLabel = new Label(shell, SWT.CENTER);
		blackLabel.setText("Black: 02");

		whiteTimer = new Label(shell, SWT.CENTER);
		whiteTimer.setText("15:00");
		blackTimer = new Label(shell, SWT.CENTER);
		blackTimer.setText("15:00");

		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				// drawing chessboard
				Image chessboardImage = new Image(Display.getDefault(),
						"Chessboard.png");
				e.gc.drawImage(chessboardImage, 0, 0,
						chessboardImage.getBounds().width,
						chessboardImage.getBounds().height, 0, 0, 320, 320);
				chessboardImage.dispose();
				// drawing chessmen
				int i = 0, chessmanStat = 0;
				i = 0;
				for (i = 0; i < 64; i++) {
					chessmanStat = chessBoard.getChessman(i / 8, i % 8)
							.getChessman();
					if (chessmanStat == Chessman.CHESSMAN_BLACK) {
						e.gc.setBackground(Display.getDefault().getSystemColor(
								SWT.COLOR_GREEN));
						e.gc.drawOval((i % 8) * 40 + 4, (i / 8) * 40 + 4, 32,
								32);
						e.gc.fillOval((i % 8) * 40 + 5, (i / 8) * 40 + 5, 31,
								31);
					} else if (chessmanStat == Chessman.CHESSMAN_WHITE) {
						e.gc.setBackground(Display.getDefault().getSystemColor(
								SWT.COLOR_BLUE));
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
			}
		});

		canvas.addMouseListener(new MouseListener() {

			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}

			@Override
			public void mouseDown(MouseEvent e) {
				int x = e.x;
				int y = e.y;
				if (chessBoard.checkHasNext() == true) {
					if (chessBoard.setChessman(y / 40, x / 40) == true) {
						chessBoard.turnEnd();
						chessBoard.searchSuggestedChessmanPosition();
					} else {
						System.out.println("You must set your chessman.");
					}
				} else {
					System.out.println("The current player have to pass.");
					chessBoard.turnEnd();
					chessBoard.searchSuggestedChessmanPosition();
				}

				blackLabel.setText("Black: " + chessBoard.getBlackNumber());
				whiteLabel.setText("White: " + chessBoard.getWhiteNumber());
				blackLabel.redraw();
				whiteLabel.redraw();

				canvas.redraw();
			}

			@Override
			public void mouseUp(MouseEvent e) {
			}

		});

		shell.addListener(SWT.Close, new Listener() {

			@Override
			public void handleEvent(Event event) {
				if (mainApplication.getApplicationState().getState() != ApplicationState.DESTORY) {
					MessageBox messageBox = new MessageBox(shell,
							SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
					messageBox.setText("确认离开");
					messageBox.setMessage("游戏中离开会被视为认输，确定要离开？");
					int result = messageBox.open();
					if (result == SWT.YES) {
						mainApplication.leaveGameTable();
					}
					event.doit = false;
				} else {
					event.doit = true;
				}
			}
		});

		shell.pack();
	}

	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
	}

	public void close() {
		shell.close();
	}

	public void hide() {
		shell.setVisible(false);
	}

	public void show() {
		shell.setVisible(true);
	}

	public static void main(String[] args) {

	}
}
