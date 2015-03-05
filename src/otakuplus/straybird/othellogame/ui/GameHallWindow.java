package otakuplus.straybird.othellogame.ui;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import otakuplus.straybird.othellogame.MainApplication;
import otakuplus.straybird.othellogame.model.UserInformation;
import otakuplus.straybird.othellogame.network.SendMessage;

public class GameHallWindow {
	protected MainApplication mainApplication;
	protected Shell shell;
	protected Display display;

	protected Text gameHallChat;
	protected Text messageText;

	public GameHallWindow(MainApplication mainApplication) {
		this.mainApplication = mainApplication;
	}

	public void createContents() {
		shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setText("游戏大厅");
		shell.setLayout(new GridLayout());

		SashForm sashForm = new SashForm(shell, SWT.HORIZONTAL | SWT.BORDER);
		GridLayout sashGridLayout = new GridLayout();
		sashGridLayout.numColumns = 10;
		sashForm.setLayout(sashGridLayout);

		Composite composite = new Composite(sashForm, SWT.NONE);
		GridData compositeGridData = new GridData();
		compositeGridData.horizontalSpan = 7;
		compositeGridData.widthHint = 400;
		compositeGridData.heightHint = 400;
		composite.setLayout(new GridLayout());
		composite.setData(compositeGridData);
		Canvas tableCanvas = new Canvas(composite, SWT.FILL);
		GridData tableCanvasGridData = new GridData();
		tableCanvasGridData.horizontalSpan = 7;
		tableCanvasGridData.widthHint = 400;
		tableCanvasGridData.heightHint = 400;
		tableCanvas.setLayoutData(tableCanvasGridData);
		tableCanvas.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent event) {
				event.gc.drawString("桌面", 0, 0);
				event.gc.setBackground(Display.getDefault().getSystemColor(
						SWT.COLOR_CYAN));
				event.gc.fillRectangle(0, 50, 200, 200);

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
		TableViewer tableViewer = new TableViewer(composite2, SWT.CENTER
				| SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION
				| SWT.BORDER);
		Table table = tableViewer.getTable();
		GridData tableViewerGridData = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL | GridData.FILL_BOTH);
		tableViewerGridData.horizontalSpan = 3;
		tableViewerGridData.widthHint = 400;
		tableViewerGridData.heightHint = 200;
		tableViewer.getControl().setLayoutData(tableViewerGridData);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());

		UserInformation userInfo[] = new UserInformation[3];
		userInfo[0] = new UserInformation();
		userInfo[0].setUserId(999);
		userInfo[0].setNickname("Jack");
		userInfo[0].setGameWins(100);
		userInfo[0].setGameDraws(100);
		userInfo[0].setGameLosts(50);
		userInfo[0].setBirthday(new Date(Instant.now().toEpochMilli()));

		userInfo[1] = new UserInformation();
		userInfo[1].setUserId(1000);
		userInfo[1].setNickname("Brown");
		userInfo[1].setGameWins(10);
		userInfo[1].setGameDraws(4);
		userInfo[1].setGameLosts(2);
		userInfo[1].setBirthday(new Date(Instant.now().toEpochMilli()));

		userInfo[2] = new UserInformation();
		userInfo[2].setUserId(1001);
		userInfo[2].setNickname("Arash");
		userInfo[2].setGameWins(78);
		userInfo[2].setGameDraws(30);
		userInfo[2].setGameLosts(87);
		userInfo[2].setBirthday(new Date(Instant.now().toEpochMilli()));

		TableViewerColumn tableViewerColumn[] = new TableViewerColumn[5];
		for (int i = 0; i < 5; i++) {
			tableViewerColumn[i] = new TableViewerColumn(tableViewer,
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
			}
		}

		TableLayout tableLayout = new TableLayout();
		tableLayout.addColumnData(new ColumnPixelData(60));
		tableLayout.addColumnData(new ColumnPixelData(80));
		tableLayout.addColumnData(new ColumnPixelData(50));
		tableLayout.addColumnData(new ColumnPixelData(50));
		tableLayout.addColumnData(new ColumnPixelData(50));
		table.setLayout(tableLayout);

		tableViewer.setLabelProvider(new UserListLabelProvider());
		tableViewer.setInput(userInfo);
		tableViewer.setSelection(new StructuredSelection(userInfo[0]));

		gameHallChat = new Text(composite2, SWT.MULTI | SWT.V_SCROLL);
		GridData gameHallGridData = new GridData();
		gameHallGridData.horizontalSpan = 3;
		gameHallGridData.widthHint = 400;
		gameHallGridData.heightHint = 200;
		gameHallChat.setLayoutData(gameHallGridData);

		messageText = new Text(composite2, SWT.NONE);
		GridData messageTextGridData = new GridData();
		messageTextGridData.horizontalSpan = 2;
		messageTextGridData.widthHint = 150;
		messageText.setText("请输入聊天信息");
		messageText.setLayoutData(messageTextGridData);

		Button sendMessageButton = new Button(composite2, SWT.PUSH);
		GridData sendMessageGridData = new GridData();
		sendMessageGridData.horizontalSpan = 1;
		sendMessageButton.setLayoutData(sendMessageGridData);
		sendMessageButton.setText("发送");
		sendMessageButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String message = messageText.getText();
				if (message != null && message.length() > 0) {
					mainApplication.sendMessage(message);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
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

	public void close() {
		shell.close();
	}

	public void sendMessage() {

	}

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

	public static void main(String[] args) {

	}
}
