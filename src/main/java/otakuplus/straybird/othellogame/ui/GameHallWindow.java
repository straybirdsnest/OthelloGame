package otakuplus.straybird.othellogame.ui;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import otakuplus.straybird.othellogame.ApplicationContextSingleton;

public class GameHallWindow {

	protected Shell shell;
	protected Display display;

	protected Text gameHallChat;
	protected Text messageText;
	protected Canvas gameTableCanvas;
	protected Table userListTable;

	protected Image tableMiniUserIcon;

	public GameHallWindow() {
		
	}

	public void createContents() {
		shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setText("游戏大厅");
		shell.setLayout(new GridLayout());

		SashForm sashForm = new SashForm(shell, SWT.HORIZONTAL | SWT.BORDER);
		GridLayout sashGridLayout = new GridLayout();
		sashGridLayout.numColumns = 6;
		sashForm.setLayout(sashGridLayout);

		Composite composite = new Composite(sashForm, SWT.NONE);
		GridData compositeGridData = new GridData();
		compositeGridData.horizontalSpan = 3;
		compositeGridData.widthHint = 420;
		compositeGridData.heightHint = 420;
		composite.setLayout(new GridLayout());
		composite.setData(compositeGridData);

		tableMiniUserIcon = new Image(Display.getDefault(), "Dog-icon.png");

		gameTableCanvas = new Canvas(composite, SWT.V_SCROLL | SWT.FILL);
		GridData tableCanvasGridData = new GridData();
		tableCanvasGridData.horizontalSpan = 3;
		tableCanvasGridData.widthHint = 420;
		tableCanvasGridData.heightHint = 420;
		gameTableCanvas.setLayoutData(tableCanvasGridData);
		gameTableCanvas.addPaintListener(new PaintListener() {

			public void paintControl(PaintEvent event) {
				/*
				ArrayList<GameTable> gameTableList = mainApplication
						.getGameTableList();
				if (gameTableList.size() > 0) {
					Iterator<GameTable> gameTableIterator = gameTableList
							.iterator();
					GameTable tempGameTable = null;
					int index = 0;
					int widthOffset, heightOffset = 0;
					while (gameTableIterator.hasNext()) {
						tempGameTable = gameTableIterator.next();
						widthOffset = (index % 2) * 208;
						heightOffset = index / 2 * 208;
						event.gc.setForeground(Display.getDefault()
								.getSystemColor(SWT.COLOR_BLACK));
						event.gc.setBackground(Display.getDefault()
								.getSystemColor(SWT.COLOR_WHITE));
						event.gc.drawString(
								"" + tempGameTable.getGameTableId(),
								60 + widthOffset, 156 + heightOffset);
						event.gc.setBackground(Display.getDefault()
								.getSystemColor(SWT.COLOR_DARK_YELLOW));
						event.gc.drawRectangle(56 + widthOffset,
								56 + heightOffset, 96, 96);
						event.gc.fillRectangle(57 + widthOffset,
								57 + heightOffset, 95, 95);
						if (tempGameTable.getPlayerAId() != null) {
							event.gc.drawImage(tableMiniUserIcon, 0, 0,
									tableMiniUserIcon.getBounds().width,
									tableMiniUserIcon.getBounds().height,
									4 + widthOffset, 80 + heightOffset, 48, 48);
						} else {
							event.gc.drawImage(tableMiniUserIcon, 0, 0,
									tableMiniUserIcon.getBounds().width,
									tableMiniUserIcon.getBounds().height,
									4 + widthOffset, 80 + heightOffset, 48, 48);
						}
						if (tempGameTable.getPlayerBId() != null) {
							event.gc.drawImage(tableMiniUserIcon, 0, 0,
									tableMiniUserIcon.getBounds().width,
									tableMiniUserIcon.getBounds().height,
									156 + widthOffset, 80 + heightOffset, 48,
									48);
						} else {
							event.gc.drawImage(tableMiniUserIcon, 0, 0,
									tableMiniUserIcon.getBounds().width,
									tableMiniUserIcon.getBounds().height,
									156 + widthOffset, 80 + heightOffset, 48,
									48);
						}
						index++;
					}
				}
				*/
			}
		});

		gameTableCanvas.addMouseListener(new MouseListener() {

			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseDown(MouseEvent e) {
			/*
					int x = e.x;
				int y = e.y;
				// index from 0
				int index = x / 208 + (y / 208) * 2;
				int widthOffset = (index % 2) * 208;
				int heightOffset = (index / 2) * 208;
				if (x >= 4 + widthOffset && x <= 56 + widthOffset
						&& y <= 128 + heightOffset && y >= 80 + heightOffset) {
					if (mainApplication.getCurrentGameTable() == null) {
						// index from 0, so it should plus 1
						mainApplication.takeGameTable(index + 1, 1);
					}
				}
				if (x >= 156 + widthOffset && x <= 204 + widthOffset
						&& y <= 128 + heightOffset && y >= 80 + heightOffset) {
					if (mainApplication.getCurrentGameTable() == null) {
						// index from 0, so it should plus 1
						mainApplication.takeGameTable(index + 1, 2);
					}
				}
				*/
			}

			public void mouseDoubleClick(MouseEvent e) {
				
			}
		});

		Composite composite2 = new Composite(sashForm, SWT.NONE);
		GridLayout composite2Layout = new GridLayout();
		composite2Layout.numColumns = 3;
		composite2.setLayout(composite2Layout);
		GridData composite2GridData = new GridData(GridData.FILL_BOTH);
		composite2GridData.horizontalSpan = 3;
		composite2GridData.widthHint = 400;
		composite2GridData.heightHint = 200;
		
		userListTable = new Table(composite2, SWT.CENTER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		userListTable.setHeaderVisible(true);
		
		TableColumn tableColumn[] = new TableColumn[6];
		for (int i = 0; i < 6; i++) {
			tableColumn[i] = new TableColumn(userListTable,
					SWT.CENTER);
			switch (i) {
			case 0:
				tableColumn[i].setText("用户ID");
				break;
			case 1:
				tableColumn[i].setText("昵称");
				break;
			case 2:
				tableColumn[i].setText("胜利");
				break;
			case 3:
				tableColumn[i].setText("平局");
				break;
			case 4:
				tableColumn[i].setText("失败");
				break;
			case 5:
				tableColumn[i].setText("积分");
				break;
			}
		}
		
		for(int i=0;i<6;i++){
			userListTable.getColumn(i).pack();
		}
		
		userListTable.setBounds(25, 25, 400, 200);
		/*
		userListTableViewer = new TableViewer(composite2, SWT.CENTER
				| SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION
				| SWT.BORDER);
		Table table = userListTableViewer.getTable();
		GridData tableViewerGridData = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL | GridData.FILL_BOTH);
		tableViewerGridData.horizontalSpan = 3;
		tableViewerGridData.widthHint = 400;
		tableViewerGridData.heightHint = 200;
		userListTableViewer.getControl().setLayoutData(tableViewerGridData);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		userListTableViewer.setContentProvider(ArrayContentProvider
				.getInstance());

		TableViewerColumn tableViewerColumn[] = new TableViewerColumn[6];
		for (int i = 0; i < 6; i++) {
			tableViewerColumn[i] = new TableViewerColumn(userListTableViewer,
					SWT.CENTER, i);
			switch (i) {
			case 0:
				tableViewerColumn[i].getColumn().setText("用户ID");
				break;
			case 1:
				tableViewerColumn[i].getColumn().setText("昵称");
				break;
			case 2:
				tableViewerColumn[i].getColumn().setText("胜利");
				break;
			case 3:
				tableViewerColumn[i].getColumn().setText("平局");
				break;
			case 4:
				tableViewerColumn[i].getColumn().setText("失败");
				break;
			case 5:
				tableViewerColumn[i].getColumn().setText("积分");
				break;
			}
		}

		TableLayout tableLayout = new TableLayout();
		tableLayout.addColumnData(new ColumnPixelData(60));
		tableLayout.addColumnData(new ColumnPixelData(80));
		tableLayout.addColumnData(new ColumnPixelData(50));
		tableLayout.addColumnData(new ColumnPixelData(50));
		tableLayout.addColumnData(new ColumnPixelData(50));
		tableLayout.addColumnData(new ColumnPixelData(50));
		table.setLayout(tableLayout);

		userListTableViewer.setLabelProvider(new UserListLabelProvider());
		userListTableViewer.setInput(mainApplication.getUserInformationList());
		userListTableViewer.setSelection(new StructuredSelection(
				mainApplication.getUserInformationList()));
		*/

		gameHallChat = new Text(composite2, SWT.MULTI | SWT.V_SCROLL);
		GridData gameHallGridData = new GridData();
		gameHallGridData.horizontalSpan = 3;
		gameHallGridData.widthHint = 400;
		gameHallGridData.heightHint = 200;
		gameHallChat.setLayoutData(gameHallGridData);

		messageText = new Text(composite2, SWT.NONE);
		GridData messageTextGridData = new GridData();
		messageTextGridData.horizontalSpan = 2;
		messageTextGridData.widthHint = 200;
		messageText.setText("请输入聊天信息");
		messageText.setLayoutData(messageTextGridData);

		Button sendMessageButton = new Button(composite2, SWT.PUSH);
		GridData sendMessageGridData = new GridData();
		sendMessageGridData.horizontalSpan = 1;
		sendMessageButton.setLayoutData(sendMessageGridData);
		sendMessageButton.setText("发送");
		sendMessageButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				sendMessage();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event event) {
                ApplicationContextSingleton.getInstance().leaveGameHall();
			}
		});

		shell.pack();
	}

	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
	}

	public void hide() {
		shell.setVisible(false);
	}

	public void show() {
		if (shell == null) {
			System.out.println("Warning!");
			System.out.println("Shell is null!");
		}
		shell.setVisible(true);
	}

	public void destoryResources() {
		if (tableMiniUserIcon != null) {
			tableMiniUserIcon.dispose();
		}
	}

	public void close() {
		shell.close();
	}

	public void notifyUserListUpdate() {
		if (userListTable != null) {
			userListTable.redraw();
		}
	}

	public void notifyGameTableListUpdate() {
		if (gameTableCanvas != null) {
			gameTableCanvas.redraw();
		}
	}

	public void sendMessage() {
		/*
		String message = messageText.getText();
		if (message != null && message.length() > 0) {
			mainApplication.sendMessage(message);
		}
		*/
	}
/*
	public void receiveMessage(SendMessage sendMessage) {
		LocalDateTime localMessageTime = LocalDateTime.ofInstant(
				Instant.ofEpochMilli(sendMessage.getMessageTime().getTime()),
				ZoneId.systemDefault());
		gameHallChat.append(localMessageTime.format(DateTimeFormatter
				.ofPattern("hh:mm:ss"))
				+ " "
				+ sendMessage.getNickname()
				+ " : " + sendMessage.getMessage() + "\n");
		gameHallChat.redraw();
	}
*/
	public static void main(String[] args) {

	}
}
