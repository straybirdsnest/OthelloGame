package otakuplus.straybird.othellogame.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContext;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.models.ChessBoard;
import otakuplus.straybird.othellogame.models.Chessman;
import otakuplus.straybird.othellogame.network.socketio.GameOperation;
import otakuplus.straybird.othellogame.network.socketio.SendMessage;
import otakuplus.straybird.othellogame.network.socketio.SocketIOClient;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OthelloGameWindow {

    private static final Logger logger = LoggerFactory.getLogger(OthelloGameWindow.class);

    protected Display display;
    protected Shell shell;

    protected Label whiteLabel;
    protected Label blackLabel;
    protected Label whiteTimer;
    protected Label blackTimer;

    protected Canvas chessBoardCanvas;
    protected Image chessBoardImage;

    protected Text gameChat;
    protected Text messageText;
    protected Button sendMessageButton;

    protected Button standByButton;
    protected Button fastMatchButton;

    protected Button takeBackButton;
    protected Button drawButton;
    protected Button giveUpButton;

    protected ChessBoard chessBoard;

    public OthelloGameWindow() {
        if (chessBoard == null) {
            chessBoard = new ChessBoard();
            chessBoard.searchSuggestedChessmanPosition();
        }
    }

    protected void createContents() {
        shell = new Shell(display, SWT.SHELL_TRIM);
        shell.setText("OthelloGame");
        shell.setLocation((Display.getDefault().getBounds().width - 400) / 2,
                (Display.getDefault().getBounds().height - 400) / 2);
        GridLayout othellGameLayout = new GridLayout();
        othellGameLayout.numColumns = 10;
        shell.setLayout(othellGameLayout);

        Label whiteUserLabel = new Label(shell, SWT.CENTER);
        GridData whiteUserGridData = new GridData();
        whiteUserGridData.horizontalSpan = 3;
        whiteUserLabel.setText("白方");
        whiteUserLabel.setLayoutData(whiteUserGridData);

        chessBoardCanvas = new Canvas(shell, SWT.NONE);
        GridData canvasChessBoardGridData = new GridData(GridData.FILL_BOTH);
        canvasChessBoardGridData.widthHint = 321;
        canvasChessBoardGridData.heightHint = 321;
        canvasChessBoardGridData.horizontalSpan = 4;
        canvasChessBoardGridData.verticalSpan = 4;
        chessBoardCanvas.setLayoutData(canvasChessBoardGridData);

        Label blackUserLabel = new Label(shell, SWT.CENTER);
        GridData blackUserGridData = new GridData();
        blackUserGridData.horizontalSpan = 3;
        blackUserLabel.setText("黑方");
        blackUserLabel.setLayoutData(blackUserGridData);

        whiteLabel = new Label(shell, SWT.CENTER);
        GridData whiteLabelGridData = new GridData();
        whiteLabelGridData.horizontalSpan = 3;
        whiteLabel.setText("White: 02");
        whiteLabel.setLayoutData(whiteLabelGridData);

        blackLabel = new Label(shell, SWT.CENTER);
        GridData blackLabelGridData = new GridData();
        blackLabelGridData.horizontalSpan = 3;
        blackLabel.setText("Black: 02");
        blackLabel.setLayoutData(blackLabelGridData);

        whiteTimer = new Label(shell, SWT.CENTER);
        GridData whiteTimerGridData = new GridData();
        whiteTimerGridData.horizontalSpan = 3;
        whiteTimer.setText("15:00");
        whiteTimer.setLayoutData(whiteTimerGridData);

        blackTimer = new Label(shell, SWT.CENTER);
        GridData blackTimerGridData = new GridData();
        blackTimerGridData.horizontalSpan = 3;
        blackTimer.setText("15:00");
        blackTimer.setLayoutData(blackTimerGridData);

        chessBoardImage = new Image(Display.getDefault(), "Chessboard.png");
        chessBoardCanvas.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                // drawing chessboard
                e.gc.drawImage(chessBoardImage, 0, 0,
                        chessBoardImage.getBounds().width,
                        chessBoardImage.getBounds().height, 1, 1, 320, 320);
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

        chessBoardCanvas.addMouseListener(new MouseListener() {

            public void mouseDoubleClick(MouseEvent e) {
            }

            public void mouseDown(MouseEvent e) {
                int x = e.x;
                int y = e.y;
                if (chessBoard.checkHasNext()) {
                    if (chessBoard.setChessman(y / 40, x / 40)) {
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

                chessBoardCanvas.redraw();
            }

            public void mouseUp(MouseEvent e) {
            }

        });

        takeBackButton = new Button(shell, SWT.PUSH | SWT.FILL);
        GridData takeBackGridData = new GridData();
        takeBackGridData.horizontalSpan = 3;
        takeBackButton.setText("悔棋");
        takeBackButton.setLayoutData(takeBackGridData);

        gameChat = new Text(shell, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData gameHallGridData = new GridData();
        gameHallGridData.horizontalSpan = 3;
        gameHallGridData.widthHint = 200;
        gameHallGridData.heightHint = 200;
        gameChat.setLayoutData(gameHallGridData);

        drawButton = new Button(shell, SWT.PUSH | SWT.FILL);
        GridData drawGridData = new GridData();
        drawGridData.horizontalSpan = 3;
        drawButton.setText("和棋");
        drawButton.setLayoutData(drawGridData);

        standByButton = new Button(shell, SWT.PUSH | SWT.FILL);
        GridData standByGridData = new GridData();
        standByGridData.horizontalSpan = 2;
        standByButton.setLayoutData(standByGridData);
        standByButton.setText("准备");
        standByButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent selectionEvent) {
                standBy();
            }

            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

        fastMatchButton = new Button(shell, SWT.PUSH | SWT.FILL);
        GridData fastMatchGridData = new GridData();
        fastMatchGridData.horizontalSpan = 2;
        fastMatchButton.setText("快速匹配");
        fastMatchButton.setLayoutData(fastMatchGridData);

        messageText = new Text(shell, SWT.NONE);
        GridData messageTextGridData = new GridData();
        messageTextGridData.horizontalSpan = 2;
        messageTextGridData.widthHint = 150;
        messageText.setText("请输入聊天信息");
        messageText.setFocus();
        messageText.setLayoutData(messageTextGridData);

        sendMessageButton = new Button(shell, SWT.PUSH);
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

        giveUpButton = new Button(shell, SWT.PUSH | SWT.FILL);
        GridData giveUpGridData = new GridData();
        giveUpGridData.horizontalSpan = 3;
        giveUpButton.setText("认输");
        giveUpButton.setLayoutData(giveUpGridData);

        shell.addListener(SWT.Close, new Listener() {

            public void handleEvent(Event event) {
                event.doit = false;
                MessageBox messageBox = new MessageBox(shell,
                        SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
                messageBox.setText("确认离开");
                messageBox.setMessage("游戏中离开会被视为认输，确定要离开？");
                int result = messageBox.open();
                if (result == SWT.YES) {
                    ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
                    applicationContext.leaveGameTable(applicationContext.getCurrentTableId(),
                            applicationContext.getCurrentSeatId());
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
        destoryResource();
        shell.dispose();
    }

    public void hide() {
        // reset resource
        chessBoard.initChessboard();
        chessBoard.searchSuggestedChessmanPosition();
        gameChat.setText("");
        shell.setVisible(false);
    }

    public void show() {
        shell.setVisible(true);
    }

    public void sendMessage() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        if (messageText.getText() != null) {
            applicationContext.getSocketIOClient().sendeMessage(
                    SocketIOClient.GAME_TABLE_ROOM + applicationContext.getCurrentTableId(), messageText.getText());
        }
    }

    public void receiveMessage(SendMessage sendMessage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        ZonedDateTime sendTime = ZonedDateTime.parse(sendMessage.getSendTime());
        gameChat.append(
                sendTime.format(formatter)
                        + " "
                        + sendMessage.getNickname()
                        + " : " + sendMessage.getMessage() + "\n");
        gameChat.redraw();
    }

    public void destoryResource() {
        if (chessBoardImage != null) {
            chessBoardImage.dispose();
        }
    }

    public void standBy() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        SocketIOClient socketIOClient = applicationContext.getSocketIOClient();
        GameOperation gameOperation = new GameOperation();
        gameOperation.setOperation(GameOperation.STAND_BY);
        socketIOClient.doGameOperation(gameOperation);
    }
}
