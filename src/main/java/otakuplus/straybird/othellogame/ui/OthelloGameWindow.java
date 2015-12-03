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
import otakuplus.straybird.othellogame.applicationstates.game.*;
import otakuplus.straybird.othellogame.models.ChessBoard;
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
    protected Image blackSetImage;
    protected Image whiteSetImage;
    protected Image hintSetImage;

    protected Text gameChat;
    protected Text messageText;
    protected Button sendMessageButton;

    protected Button standByButton;
    protected Button fastMatchButton;

    protected Button takeBackButton;
    protected Button drawButton;
    protected Button giveUpButton;

    protected ChessBoard chessBoard;
    protected GameContext gameContext;

    public OthelloGameWindow() {
        gameContext = GameContextSigleton.getGameContextInstance();
        chessBoard = gameContext.getChessBoard();
    }

    protected void createContents() {
        shell = new Shell(display, SWT.SHELL_TRIM);
        shell.setText("奥赛罗棋");
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
        canvasChessBoardGridData.widthHint = 406;
        canvasChessBoardGridData.heightHint = 406;
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
        whiteLabel.setText("白方: 02");
        whiteLabel.setLayoutData(whiteLabelGridData);

        blackLabel = new Label(shell, SWT.CENTER);
        GridData blackLabelGridData = new GridData();
        blackLabelGridData.horizontalSpan = 3;
        blackLabel.setText("黑方: 02");
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

        chessBoardImage = new Image(Display.getDefault(), "chessboard_405px.png");
        blackSetImage = new Image(Display.getDefault(), "black_48px.png");
        whiteSetImage = new Image(Display.getDefault(), "white_48px_transparent.png");
        hintSetImage = new Image(Display.getDefault(), "hint_48px.png");

        chessBoardCanvas.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                // drawing chessboard
                e.gc.drawImage(chessBoardImage, 0, 0,
                        chessBoardImage.getBounds().width,
                        chessBoardImage.getBounds().height, 1, 1, 405, 405);
                // drawing chessmen
                int i = 0, chessmanStat = 0;
                i = 0;
                for (i = 0; i < 64; i++) {
                    chessmanStat = chessBoard.getChessman(i / 8, i % 8);
                    if (chessmanStat == ChessBoard.CHESSMAN_BLACK) {
                        e.gc.drawImage(blackSetImage, 0, 0, blackSetImage.getBounds().width, blackSetImage.getBounds().height, (i % 8) * 50 + 4, (i / 8) * 50 + 4, 48, 48);
                    } else if (chessmanStat == ChessBoard.CHESSMAN_WHITE) {
                        e.gc.drawImage(whiteSetImage, 0, 0, whiteSetImage.getBounds().width, whiteSetImage.getBounds().height, (i % 8) * 50 + 4, (i / 8) * 50 + 4, 48, 48);
                    }
                }

                // drawing suggested position
                ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
                Integer seatId = applicationContext.getCurrentSeatId();
                GameState gameState = gameContext.getGameState();
                if (applicationContext.getCurrentSeatId() != null && (gameState instanceof GameWhiteSetState || gameState instanceof GameBlackSetState)) {
                    int current = chessBoard.getCurrentChessman();
                    if ((current == ChessBoard.CHESSMAN_BLACK && seatId == 1) || (current == ChessBoard.CHESSMAN_WHITE && seatId == 0)) {
                        int suggestedPosition[] = chessBoard.getSuggestedPosition();
                        for (i = 0; i < suggestedPosition.length; i++) {
                            if (suggestedPosition[i] == 1) {
                                e.gc.drawImage(hintSetImage, 0, 0, hintSetImage.getBounds().width, hintSetImage.getBounds().height, (i % 8) * 50 + 4, (i / 8) * 50 + 4, 48, 48);
                            }
                        }
                    }
                }
            }
        });

        chessBoardCanvas.addMouseListener(new MouseListener() {

            public void mouseDoubleClick(MouseEvent e) {
            }

            public void mouseDown(MouseEvent e) {
                int x = e.x - 3;
                int y = e.y - 3;

                GameOperation gameOperation = new GameOperation();
                gameOperation.setSetX(y / 50);
                gameOperation.setSetY(x / 50);

                ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
                SocketIOClient socketIOClient = applicationContext.getSocketIOClient();
                GameContext gameContext = GameContextSigleton.getGameContextInstance();
                GameState gameState = gameContext.getGameState();
                if (chessBoard.checkHasNext()) {
                    int suggest[] = chessBoard.getSuggestedPosition();
                    if (suggest[(y / 50) * 8 + (x / 50)] == 0) {
                        showWrongPositionMessage();
                        return;
                    }
                    if (applicationContext.getCurrentSeatId() == 0 && gameState instanceof GameWhiteSetState) {
                        gameOperation.setOperation(GameOperation.WHITE_SET);
                        socketIOClient.doGameOperation(gameOperation);
                    }
                    if (applicationContext.getCurrentSeatId() == 1 && gameState instanceof GameBlackSetState) {
                        gameOperation.setOperation(GameOperation.BLACK_SET);
                        socketIOClient.doGameOperation(gameOperation);
                    }
                }
            }

            public void mouseUp(MouseEvent e) {
            }

        });

        takeBackButton = new Button(shell, SWT.PUSH | SWT.FILL);
        GridData takeBackGridData = new GridData();
        takeBackGridData.horizontalSpan = 3;
        takeBackButton.setEnabled(false);
        takeBackButton.setText("悔棋");
        takeBackButton.setLayoutData(takeBackGridData);
        takeBackButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent selectionEvent) {
                takeBack();
            }

            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

        gameChat = new Text(shell, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData gameHallGridData = new GridData();
        gameHallGridData.horizontalSpan = 3;
        gameHallGridData.widthHint = 200;
        gameHallGridData.heightHint = 200;
        gameChat.setLayoutData(gameHallGridData);

        drawButton = new Button(shell, SWT.PUSH | SWT.FILL);
        GridData drawGridData = new GridData();
        drawGridData.horizontalSpan = 3;
        drawButton.setEnabled(false);
        drawButton.setText("和棋");
        drawButton.setLayoutData(drawGridData);
        drawButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent selectionEvent) {
                draw();
            }

            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

        standByButton = new Button(shell, SWT.PUSH | SWT.FILL);
        GridData standByGridData = new GridData();
        standByGridData.horizontalSpan = 2;
        standByButton.setLayoutData(standByGridData);
        standByButton.setText("准备");
        standByButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent selectionEvent) {
                standByOrCancel();
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
        giveUpButton.setEnabled(false);
        giveUpButton.setText("认输");
        giveUpButton.setLayoutData(giveUpGridData);

        giveUpButton.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent selectionEvent) {
                giveUp();
            }

            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

        shell.addListener(SWT.Close, new Listener() {

            public void handleEvent(Event event) {
                event.doit = false;
                GameContext gameContext = GameContextSigleton.getGameContextInstance();
                ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
                GameState gameState = gameContext.getGameState();
                if (gameState instanceof GameWhiteSetState || gameState instanceof GameBlackSetState) {
                    MessageBox messageBox = new MessageBox(shell,
                            SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
                    messageBox.setText("确认离开");
                    messageBox.setMessage("游戏中离开会被视为认输，确定要离开？");
                    int result = messageBox.open();
                    if (result == SWT.YES) {
                        applicationContext.leaveGameTable(applicationContext.getCurrentTableId(),
                                applicationContext.getCurrentSeatId());
                        giveUp();
                    }
                } else {
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
        redrawChessBoard();
    }

    public void sendMessage() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        if (messageText.getText().length() > 0) {
            applicationContext.getSocketIOClient().sendeMessage(
                    SocketIOClient.GAME_TABLE_ROOM + applicationContext.getCurrentTableId(), messageText.getText());
            messageText.setText("");
            messageText.redraw();
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
        if (whiteSetImage != null) {
            whiteSetImage.dispose();
        }
        if (blackSetImage != null) {
            blackSetImage.dispose();
        }
        if (hintSetImage != null) {
            hintSetImage.dispose();
        }
    }

    public void standByOrCancel() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        SocketIOClient socketIOClient = applicationContext.getSocketIOClient();
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        GameState gameState = gameContext.getGameState();
        GameOperation gameOperation = new GameOperation();
        if (gameState instanceof GameBlackReadyState && applicationContext.getCurrentSeatId() == 1) {
            standByButton.setText("准备");
            gameOperation.setOperation(GameOperation.STAND_BY_CANCLE);
            socketIOClient.doGameOperation(gameOperation);
            shell.pack();
            shell.redraw();
        } else if (gameState instanceof GameWhiteReadyState && applicationContext.getCurrentSeatId() == 0) {
            standByButton.setText("准备");
            gameOperation.setOperation(GameOperation.STAND_BY_CANCLE);
            socketIOClient.doGameOperation(gameOperation);
            shell.pack();
            shell.redraw();
        } else {
            standByButton.setText("取消准备");
            gameOperation.setOperation(GameOperation.STAND_BY);
            socketIOClient.doGameOperation(gameOperation);
            shell.pack();
            shell.redraw();
        }
    }

    public void giveUp() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        SocketIOClient socketIOClient = applicationContext.getSocketIOClient();
        GameOperation gameOperation = new GameOperation();
        gameOperation.setOperation(GameOperation.GIVE_UP);
        socketIOClient.doGameOperation(gameOperation);
        applicationContext.giveUp();
    }

    public void draw() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        SocketIOClient socketIOClient = applicationContext.getSocketIOClient();
        GameOperation gameOperation = new GameOperation();
        gameOperation.setOperation(GameOperation.DRAW);
        socketIOClient.doGameOperation(gameOperation);
    }

    public void takeBack() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        SocketIOClient socketIOClient = applicationContext.getSocketIOClient();
        GameOperation gameOperation = new GameOperation();
        gameOperation.setOperation(GameOperation.TAKE_BACK);
        socketIOClient.doGameOperation(gameOperation);
        applicationContext.giveUp();
    }

    public void redrawChessBoard() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        GameState gameState = gameContext.getGameState();
        if (gameState instanceof GameBlackSetState || gameState instanceof GameWhiteSetState) {
            standByButton.setEnabled(false);
            giveUpButton.setEnabled(true);
            drawButton.setEnabled(true);
            if (gameState instanceof GameBlackSetState && applicationContext.getCurrentSeatId() == 1) {
                takeBackButton.setEnabled(true);
            } else if (gameState instanceof GameWhiteSetState && applicationContext.getCurrentSeatId() == 0) {
                takeBackButton.setEnabled(true);
            } else {
                takeBackButton.setEnabled(false);
            }
        } else {
            standByButton.setEnabled(true);
            standByButton.setText("准备");
            giveUpButton.setEnabled(false);
            drawButton.setEnabled(false);
            takeBackButton.setEnabled(false);
        }
        if (chessBoard.getBlackNumber() < 10) {
            blackLabel.setText("黑方: 0" + chessBoard.getBlackNumber());
        } else {
            blackLabel.setText("黑方: " + chessBoard.getBlackNumber());
        }
        if (chessBoard.getWhiteNumber() < 10) {
            whiteLabel.setText("白方: 0" + chessBoard.getWhiteNumber());
        } else {
            whiteLabel.setText("白方: " + chessBoard.getWhiteNumber());
        }
        whiteLabel.redraw();
        blackLabel.redraw();
        chessBoardCanvas.redraw();
        shell.pack();
        shell.redraw();
    }

    public void syncGameReadyState() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        SocketIOClient socketIOClient = applicationContext.getSocketIOClient();
        GameState gameState = gameContext.getGameState();
        GameOperation gameOperation = new GameOperation();
        if (gameState instanceof GameBlackReadyState) {
            gameOperation.setOperation(GameOperation.STAND_BY);
            socketIOClient.doGameOperation(gameOperation);
        } else if (gameState instanceof GameWhiteReadyState) {
            gameOperation.setOperation(GameOperation.STAND_BY);
            socketIOClient.doGameOperation(gameOperation);
        }
    }

    public void showSkipMessage() {
        MessageBox messageBox = new MessageBox(shell,
                SWT.APPLICATION_MODAL | SWT.OK);
        messageBox.setText("游戏提示");
        messageBox.setMessage("您当前无子可下，系统将跳过您的回合。");
        messageBox.open();
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        SocketIOClient socketIOClient = applicationContext.getSocketIOClient();
        GameOperation gameOperation = new GameOperation();
        gameOperation.setOperation(GameOperation.SKIP_SET);
        socketIOClient.doGameOperation(gameOperation);
    }

    public void showWrongPositionMessage() {
        MessageBox messageBox = new MessageBox(shell,
                SWT.APPLICATION_MODAL | SWT.OK);
        messageBox.setText("游戏提示");
        messageBox.setMessage("请将棋子下在提示的位置内。");
        messageBox.open();
    }

    public void showWinMessage() {
        MessageBox messageBox = new MessageBox(shell,
                SWT.APPLICATION_MODAL | SWT.OK);
        messageBox.setText("游戏结束");
        messageBox.setMessage("恭喜您获得本次游戏的胜利!");
        messageBox.open();
    }

    public void showDrawGameMessage() {
        MessageBox messageBox = new MessageBox(shell,
                SWT.APPLICATION_MODAL | SWT.OK);
        messageBox.setText("游戏结束");
        messageBox.setMessage("经过艰苦的一局，双方不分胜负，辛苦了。");
        messageBox.open();
    }

    public void showLostMessage() {
        MessageBox messageBox = new MessageBox(shell,
                SWT.APPLICATION_MODAL | SWT.OK);
        messageBox.setText("游戏结束");
        messageBox.setMessage("经过艰苦的对垒您还是输了，太可惜了。");
        messageBox.open();
    }

    public void showGiveUpMessage() {
        MessageBox messageBox = new MessageBox(shell,
                SWT.APPLICATION_MODAL | SWT.OK);
        messageBox.setText("玩家认输");
        messageBox.setMessage("对面玩家认输，恭喜您获胜");
        messageBox.open();
    }

    public void showDrawMessage() {
        MessageBox messageBox = new MessageBox(shell,
                SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
        messageBox.setText("玩家求和");
        messageBox.setMessage("对面玩家请求和棋，请问您同意吗？");
        int buttonID = messageBox.open();
        switch (buttonID) {
            case SWT.YES:
                ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
                SocketIOClient socketIOClient = applicationContext.getSocketIOClient();
                GameOperation gameOperation = new GameOperation();
                gameOperation.setOperation(GameOperation.DRAW_AGREE);
                socketIOClient.doGameOperation(gameOperation);
                applicationContext.draw();
                break;
            case SWT.NO:
                break;
        }
    }

    public void showTakeBackMessage() {
        MessageBox messageBox = new MessageBox(shell,
                SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
        messageBox.setText("玩家请求悔棋");
        messageBox.setMessage("对面玩家请求悔棋，请问您同意吗？");
        int buttonID = messageBox.open();
        switch (buttonID) {
            case SWT.YES:
                ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
                SocketIOClient socketIOClient = applicationContext.getSocketIOClient();
                GameOperation gameOperation = new GameOperation();
                gameOperation.setOperation(GameOperation.TAKE_BACK_AGREE);
                socketIOClient.doGameOperation(gameOperation);
                break;
            case SWT.NO:
                break;
        }
    }
}
