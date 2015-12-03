package otakuplus.straybird.othellogame.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContext;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.models.GameTable;
import otakuplus.straybird.othellogame.models.UserInformation;
import otakuplus.straybird.othellogame.network.socketio.SendMessage;
import otakuplus.straybird.othellogame.network.socketio.SocketIOClient;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class GameHallWindow {

    protected Shell shell;
    protected Display display;

    protected Text gameHallChat;
    protected Text messageText;
    protected Canvas gameTableCanvas;
    protected Table userListTable;

    protected Image tableMiniUserIcon;
    protected Image tableEmpty;
    protected Image tableWaiting;
    protected Image tablePlaying;
    protected Image tableTaken;

    public GameHallWindow() {

    }

    public void createContents() {
        Monitor monitor = Display.getDefault().getPrimaryMonitor();

        shell = new Shell(display, SWT.SHELL_TRIM);
        shell.setText("游戏大厅");
        shell.setSize(640, 480);
        shell.setLocation((monitor.getBounds().width - 430) / 2,
                (monitor.getBounds().height - 325) / 2);
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
        tableEmpty = new Image(Display.getDefault(), "player_48px.png");
        tableWaiting = new Image(Display.getDefault(), "table-waiting-96px_brighter.png");
        tablePlaying = new Image(Display.getDefault(), "table-playing-96px_brighter.png");
        tableTaken = new Image(Display.getDefault(), "player-boarder-48px.png");

        gameTableCanvas = new Canvas(composite, SWT.V_SCROLL | SWT.FILL);
        GridData tableCanvasGridData = new GridData();
        tableCanvasGridData.horizontalSpan = 3;
        tableCanvasGridData.widthHint = 420;
        tableCanvasGridData.heightHint = 420;
        gameTableCanvas.setLayoutData(tableCanvasGridData);

        gameTableCanvas.addPaintListener(new PaintListener() {

            public void paintControl(PaintEvent event) {
                ArrayList<GameTable> gameTableList = ApplicationContextSingleton.getInstance()
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
                        event.gc.drawString(
                                "" + tempGameTable.getGameTableId(),
                                100 + widthOffset, 156 + heightOffset);
                        event.gc.drawImage(tableWaiting, 0, 0, tableWaiting.getBounds().width, tableWaiting.getBounds().height, 56 + widthOffset, 56 + heightOffset, 96, 96);
                        event.gc.drawImage(tableEmpty, 0, 0, tableEmpty.getBounds().width, tableEmpty.getBounds().height, 4+widthOffset, 80 + heightOffset, 48, 48);
                        event.gc.drawImage(tableEmpty, 0, 0, tableEmpty.getBounds().width, tableEmpty.getBounds().height, 156 + widthOffset, 80 + heightOffset, 48, 48);
                        if (tempGameTable.getPlayerA() != null) {
                            event.gc.drawImage(tableTaken, 0, 0, tableTaken.getBounds().width, tableTaken.getBounds().height, 4 + widthOffset, 80 + heightOffset, 48, 48);
                            event.gc.drawImage(tableMiniUserIcon, 0, 0,
                                    tableMiniUserIcon.getBounds().width,
                                    tableMiniUserIcon.getBounds().height,
                                    12 + widthOffset, 88 + heightOffset, 32, 32);
                        }
                        if (tempGameTable.getPlayerB() != null) {
                            event.gc.drawImage(tableTaken, 0, 0, tableTaken.getBounds().width, tableTaken.getBounds().height, 156 + widthOffset, 80 + heightOffset, 48, 48);
                            event.gc.drawImage(tableMiniUserIcon, 0, 0,
                                    tableMiniUserIcon.getBounds().width,
                                    tableMiniUserIcon.getBounds().height,
                                    164 + widthOffset, 88 + heightOffset, 32,
                                    32);
                        }
                        index++;
                    }
                }
            }
        });

        gameTableCanvas.addMouseListener(new MouseListener() {

            public void mouseUp(MouseEvent e) {

            }

            public void mouseDown(MouseEvent e) {
                ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
                int x = e.x;
                int y = e.y;
                // index from 0
                int index = x / 208 + (y / 208) * 2;
                int widthOffset = (index % 2) * 208;
                int heightOffset = (index / 2) * 208;
                if (x >= 4 + widthOffset && x <= 56 + widthOffset
                        && y <= 128 + heightOffset && y >= 80 + heightOffset) {
                    if (applicationContext.getCurrentTableId() == null) {
                        applicationContext.enterGameTable(index + 1, 0);
                    }
                }
                if (x >= 156 + widthOffset && x <= 204 + widthOffset
                        && y <= 128 + heightOffset && y >= 80 + heightOffset) {
                    if (applicationContext.getCurrentTableId() == null) {
                        applicationContext.enterGameTable(index + 1, 1);
                    }
                }
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
        for (int i = 0; i < 5; i++) {
            tableColumn[i] = new TableColumn(userListTable,
                    SWT.CENTER);
            switch (i) {
                case 0:
                    tableColumn[i].setText("昵称");
                    break;
                case 1:
                    tableColumn[i].setText("胜利");
                    break;
                case 2:
                    tableColumn[i].setText("平局");
                    break;
                case 3:
                    tableColumn[i].setText("失败");
                    break;
                case 4:
                    tableColumn[i].setText("积分");
                    break;
            }
        }

        for (int i = 0; i < 5; i++) {
            userListTable.getColumn(i).pack();
        }

        userListTable.setBounds(25, 25, 400, 200);

        gameHallChat = new Text(composite2, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
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
        messageText.setFocus();
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
                event.doit = false;
                MessageBox messageBox = new MessageBox(shell,
                        SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
                messageBox.setText("确认注销");
                messageBox.setMessage("注销并返回登录界面？");
                int result = messageBox.open();
                if (result == SWT.YES) {
                    ApplicationContextSingleton.getInstance().leaveGameHall();
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

    public void hide() {
        // reset resource
        gameHallChat.setText("");
        shell.setVisible(false);
    }

    public void show() {
        shell.setVisible(true);
    }

    public void destoryResources() {
        if (tableMiniUserIcon != null) {
            tableMiniUserIcon.dispose();
        }
        if (tableEmpty != null) {
            tableEmpty.dispose();
        }
        if (tableWaiting != null) {
            tableWaiting.dispose();
        }
        if (tablePlaying != null) {
            tablePlaying.dispose();
        }
    }

    public void close() {
        destoryResources();
        shell.dispose();
    }

    public void notifyUserListUpdate() {
        if (userListTable != null) {
            ArrayList<UserInformation> userInformationList = ApplicationContextSingleton.getInstance().getUserInformationList();
            if (userInformationList != null && !userInformationList.isEmpty()) {
                userListTable.clearAll();
                TableItem tableItem = null;
                UserInformation userInformation = null;
                for (int i = 0; i < userInformationList.size(); i++) {
                    tableItem = new TableItem(userListTable, SWT.CENTER);
                    userInformation = userInformationList.get(i);
                    tableItem.setText(0, userInformation.getNickname());
                    tableItem.setText(1, "" + userInformation.getGameWins());
                    tableItem.setText(2, "" + userInformation.getGameDraws());
                    tableItem.setText(3, "" + userInformation.getGameLosts());
                    tableItem.setText(4, "" + userInformation.getRankPoints());
                }
            }
            shell.pack();
            shell.redraw();
        }
    }

    public void notifyGameTableListUpdate() {
        if (gameTableCanvas != null) {
            gameTableCanvas.redraw();
        }
    }

    public void sendMessage() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        if (messageText.getText().length() > 0) {
            applicationContext.getSocketIOClient().sendeMessage(SocketIOClient.GAME_HALL_ROOM, messageText.getText());
            messageText.setText("");
            messageText.redraw();
        }
    }

    public void receiveMessage(SendMessage sendMessage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        ZonedDateTime sendTime = ZonedDateTime.parse(sendMessage.getSendTime());
        gameHallChat.append(
                sendTime.format(formatter)
                        + " "
                        + sendMessage.getNickname()
                        + " : " + sendMessage.getMessage() + "\n");
        gameHallChat.redraw();
    }

}
