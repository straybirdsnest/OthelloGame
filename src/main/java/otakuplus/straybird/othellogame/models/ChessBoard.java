package otakuplus.straybird.othellogame.models;

public class ChessBoard {

    /**
     * ChessBoard stands for one game's chessboard. each player should set one
     * chessman on the chessboard in his/her turn. This class should check that
     * if a chessman is legal to be set to one position.
     */
    public static final int BOARDWIDTH = 8;
    public static final int STEP_SIZE = 64;

    public static final int CHESSMAN_NONE = 0;
    public static final int CHESSMAN_BLACK = 1;
    public static final int CHESSMAN_WHITE = 2;

    private static final int DIRECTION_TOP = 1;
    private static final int DIRECTION_LEFT = 2;
    private static final int DIRECTION_RIGHT = 3;
    private static final int DIRECTION_BUTTOM = 4;
    private static final int DIRECTION_LEFTTOP = 5;
    private static final int DIRECTION_RIGHTTOP = 6;
    private static final int DIRECTION_LEFTBUTTOM = 7;
    private static final int DIRECTION_RIGHTBUTTOM = 8;

    private int chessmen[][][] = null;
    private int currentStep = 1;

    private int suggestedPosition[] = null;
    private int currentChessman = CHESSMAN_BLACK;

    private int blackNumber = 0;
    private int whiteNumber = 0;

    public ChessBoard() {
        if (suggestedPosition == null) {
            suggestedPosition = new int[64];
        }
        if (chessmen == null) {
            chessmen = new int[STEP_SIZE][BOARDWIDTH][BOARDWIDTH];
        }
        initChessboard();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.searchSuggestedChessmanPosition();
        chessBoard.setChessman(2, 4);
        chessBoard.turnEnd();
        chessBoard.searchSuggestedChessmanPosition();
        int i = 0, j = 0;
        int[] suggestedPosition = chessBoard.getSuggestedPosition();
        System.out.println("The chessboard:");
        for (i = 0; i < ChessBoard.BOARDWIDTH; i++) {
            for (j = 0; j < ChessBoard.BOARDWIDTH; j++) {
                if (chessBoard.getChessman(i, j) == CHESSMAN_NONE) {
                    if (suggestedPosition[i * 8 + j] == 1) {
                        System.out.print("SUGGESTED ");
                    } else {
                        System.out.print(chessBoard.getChessman(i, j) + " ");
                    }
                } else {
                    System.out.print(chessBoard.getChessman(i, j) + " ");
                }
            }
            System.out.println("");
        }
    }

    /**
     * initChessboard funtion resets the chessboard.
     */
    public void initChessboard() {
        int i = 0, j = 0, k=0;
        currentStep = 1;

        if (chessmen == null) {
            System.err.println("Chessboard initialization failed!");
            return;
        }
        // Clean the chessboard
        for(k=0;k<STEP_SIZE;k++){
            for (i = 0; i < BOARDWIDTH; i++) {
                for (j = 0; j < BOARDWIDTH; j++) {
                    chessmen[k][i][j] = CHESSMAN_NONE;
                }
            }
        }
        // Set four chessmen
        chessmen[0][3][3] = CHESSMAN_WHITE;
        chessmen[0][3][4] = CHESSMAN_BLACK;
        chessmen[0][4][3] = CHESSMAN_BLACK;
        chessmen[0][4][4] = CHESSMAN_WHITE;

        chessmen[1][3][3] = CHESSMAN_WHITE;
        chessmen[1][3][4] = CHESSMAN_BLACK;
        chessmen[1][4][3] = CHESSMAN_BLACK;
        chessmen[1][4][4] = CHESSMAN_WHITE;

        // Set Player's current chessman to black
        currentChessman = CHESSMAN_BLACK;

        searchSuggestedChessmanPosition();
        calculateChessmenNumber();
    }

    private void calculateChessmenNumber() {
        int i = 0, j = 0;
        blackNumber = 0;
        whiteNumber = 0;
        for (i = 0; i < ChessBoard.BOARDWIDTH; i++) {
            for (j = 0; j < ChessBoard.BOARDWIDTH; j++) {
                if (chessmen[currentStep][i][j] == CHESSMAN_BLACK) {
                    blackNumber++;
                } else if (chessmen[currentStep][i][j] == CHESSMAN_WHITE) {
                    whiteNumber++;
                }
            }
        }
    }

    /**
     * checkChessmanPosition function is used to check if a chessman is able to
     * be set to the position. This function should be called after
     * searchSuggestedChessmanPosition.
     *
     * @param x the row position of the chessman, from 0 to BOARDWIDTH - 1
     * @param y the column position of the chessman, from 0 to BOARDWIDTH - 1
     * @return boolean value stands for if it is ok to set a chessman
     */
    private boolean checkChessmanPosition(int x, int y) {
        if (0 <= x && x < 8 && 0 <= y && y < 8) {
            int position = x * 8 + y;
            if (suggestedPosition[position] == 1) {
                return true;
            }
        }
        return false;
    }

    private int stepDirection(int x, int y, boolean findDifferent,
                              int direction, boolean flip) {
        int result = 0;
        if (0 <= x && x < BOARDWIDTH && 0 <= y && y < BOARDWIDTH) {
            if (chessmen[currentStep][x][y] == CHESSMAN_NONE) {
                return 0;
            } else {
                if (chessmen[currentStep][x][y] != currentChessman) {
                    if (direction == DIRECTION_TOP) {
                        result = stepDirection(x - 1, y, true, DIRECTION_TOP,
                                flip);
                        if (result == 1 && flip == true) {
                            chessmen[currentStep][x][y] = (chessmen[currentStep][x][y] == CHESSMAN_BLACK) ? CHESSMAN_WHITE: CHESSMAN_BLACK;
                        }
                        return result;
                    }
                    if (direction == DIRECTION_LEFT) {
                        result = stepDirection(x, y - 1, true, DIRECTION_LEFT,
                                flip);
                        if (result == 1 && flip == true) {
                            chessmen[currentStep][x][y] = (chessmen[currentStep][x][y] == CHESSMAN_BLACK) ? CHESSMAN_WHITE: CHESSMAN_BLACK;
                        }
                        return result;
                    }
                    if (direction == DIRECTION_RIGHT) {
                        result = stepDirection(x, y + 1, true, DIRECTION_RIGHT,
                                flip);
                        if (result == 1 && flip == true) {
                            chessmen[currentStep][x][y] = (chessmen[currentStep][x][y] == CHESSMAN_BLACK) ? CHESSMAN_WHITE: CHESSMAN_BLACK;
                        }
                        return result;
                    }
                    if (direction == DIRECTION_BUTTOM) {
                        result = stepDirection(x + 1, y, true,
                                DIRECTION_BUTTOM, flip);
                        if (result == 1 && flip == true) {
                            chessmen[currentStep][x][y] = (chessmen[currentStep][x][y] == CHESSMAN_BLACK) ? CHESSMAN_WHITE: CHESSMAN_BLACK;
                        }
                        return result;
                    }
                    if (direction == DIRECTION_LEFTTOP) {
                        result = stepDirection(x - 1, y - 1, true,
                                DIRECTION_LEFTTOP, flip);
                        if (result == 1 && flip == true) {
                            chessmen[currentStep][x][y] = (chessmen[currentStep][x][y] == CHESSMAN_BLACK) ? CHESSMAN_WHITE: CHESSMAN_BLACK;
                        }
                        return result;
                    }
                    if (direction == DIRECTION_RIGHTTOP) {
                        result = stepDirection(x - 1, y + 1, true,
                                DIRECTION_RIGHTTOP, flip);
                        if (result == 1 && flip == true) {
                            chessmen[currentStep][x][y] = (chessmen[currentStep][x][y] == CHESSMAN_BLACK) ? CHESSMAN_WHITE: CHESSMAN_BLACK;
                        }
                        return result;
                    }
                    if (direction == DIRECTION_LEFTBUTTOM) {
                        result = stepDirection(x + 1, y - 1, true,
                                DIRECTION_LEFTBUTTOM, flip);
                        if (result == 1 && flip == true) {
                            chessmen[currentStep][x][y] = (chessmen[currentStep][x][y] == CHESSMAN_BLACK) ? CHESSMAN_WHITE: CHESSMAN_BLACK;
                        }
                        return result;
                    }
                    if (direction == DIRECTION_RIGHTBUTTOM) {
                        result = stepDirection(x + 1, y + 1, true,
                                DIRECTION_RIGHTBUTTOM, flip);
                        if (result == 1 && flip == true) {
                            chessmen[currentStep][x][y] = (chessmen[currentStep][x][y] == CHESSMAN_BLACK) ? CHESSMAN_WHITE: CHESSMAN_BLACK;
                        }
                        return result;
                    }
                }
            }
            if (findDifferent == true
                    && chessmen[currentStep][x][y] == currentChessman) {
                return 1;
            }
        } else {
            return 0;
        }
        return 0;
    }

    private void testAllDirection(int x, int y, boolean flip) {
        int position = x * 8 + y;
        suggestedPosition[position] = 0;
        if (stepDirection(x - 1, y, false, DIRECTION_TOP, flip) == 1) {
            suggestedPosition[position] = 1;
        }
        if (stepDirection(x, y - 1, false, DIRECTION_LEFT, flip) == 1) {
            suggestedPosition[position] = 1;
        }
        if (stepDirection(x, y + 1, false, DIRECTION_RIGHT, flip) == 1) {
            suggestedPosition[position] = 1;
        }
        if (stepDirection(x + 1, y, false, DIRECTION_BUTTOM, flip) == 1) {
            suggestedPosition[position] = 1;
        }
        if (stepDirection(x - 1, y - 1, false, DIRECTION_LEFTTOP, flip) == 1) {
            suggestedPosition[position] = 1;
        }
        if (stepDirection(x - 1, y + 1, false, DIRECTION_RIGHTTOP, flip) == 1) {
            suggestedPosition[position] = 1;
        }
        if (stepDirection(x + 1, y - 1, false, DIRECTION_LEFTBUTTOM, flip) == 1) {
            suggestedPosition[position] = 1;
        }
        if (stepDirection(x + 1, y + 1, false, DIRECTION_RIGHTBUTTOM, flip) == 1) {
            suggestedPosition[position] = 1;
        }
    }

    public void searchSuggestedChessmanPosition() {
        int i = 0, j = 0, position = 0;
        for (i = 0; i < BOARDWIDTH; i++) {
            for (j = 0; j < BOARDWIDTH; j++) {
                position = i * 8 + j;
                if (chessmen[currentStep][i][j] != CHESSMAN_NONE) {
                    suggestedPosition[position] = 0;
                } else {
                    testAllDirection(i, j, false);
                }
            }
        }
    }

    /**
     * set current player's chessman to the position (x,y) of chessboard. This
     * fucntion should be called after searchSuggestedChessmanPosition.
     *
     * @param x the row position of the chessman, from 0 to BOARDWIDTH - 1
     * @param y the column position of the chessman, from 0 to BOARDWIDTH - 1
     */
    public boolean setChessman(int x, int y) {
        if (chessmen != null) {
            if (0 <= x && x < BOARDWIDTH && 0 <= y && y < BOARDWIDTH
                    && chessmen[currentStep][x][y] == CHESSMAN_NONE) {
                if (checkChessmanPosition(x, y)) {
                    testAllDirection(x, y, true);
                    chessmen[currentStep][x][y]=currentChessman;
                    return true;
                }
            }
        } else {
            System.err.println("Chessboard initialization failed!.");
        }
        return false;
    }

    /**
     * check if the current player can set chessman
     *
     * @return if the current player can set chessman
     */
    public boolean checkHasNext() {
        int i = 0;
        boolean flag = false;
        for (i = 0; i < 64; i++) {
            if (suggestedPosition[i] != 0) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean checkGameOver(){
        int backup = currentChessman;
        boolean flag = true;
        searchSuggestedChessmanPosition();
        if(checkHasNext()){
            flag = false;
        }
        if (backup == CHESSMAN_BLACK){
            currentChessman = CHESSMAN_WHITE;
        }
        if(backup == CHESSMAN_WHITE){
            currentChessman = CHESSMAN_BLACK;
        }
        searchSuggestedChessmanPosition();
        if(checkHasNext()){
            flag = false;
        }
        currentChessman = backup;
        searchSuggestedChessmanPosition();
        return flag;
    }

    public void takeBack(){
        if (currentStep < 3){
            // 最开始的第一步没下不能悔棋
            return;
        }
        currentStep -= 2;
        int j = 0, k = 0;
        for(j = 0; j <BOARDWIDTH;j++) {
            for (k = 0; k < BOARDWIDTH; k++) {
                chessmen[currentStep][j][k] = chessmen[currentStep-1][j][k];
            }
        }

        calculateChessmenNumber();
        searchSuggestedChessmanPosition();
    }

    public void turnEnd() {
        if (checkHasNext() == true) {
            currentChessman = (currentChessman == CHESSMAN_BLACK) ? CHESSMAN_WHITE: CHESSMAN_BLACK;
            currentStep++;
            for(int i=0;i<BOARDWIDTH;i++){
                for(int j=0;j<BOARDWIDTH;j++){
                    chessmen[currentStep][i][j] = chessmen[currentStep-1][i][j];
                }
            }
        } else {
            currentChessman = (currentChessman == CHESSMAN_BLACK) ? CHESSMAN_WHITE: CHESSMAN_BLACK;
            currentStep++;
            for(int i=0;i<BOARDWIDTH;i++){
                for(int j=0;j<BOARDWIDTH;j++){
                    chessmen[currentStep][i][j] = chessmen[currentStep-1][i][j];
                }
            }
        }
        calculateChessmenNumber();
    }

    /**
     * get the chessman of position (x,y).
     *
     * @param x the row position of the chessman, from 0 to BOARDWIDTH - 1
     * @param y the column position of the chessman, from 0 to BOARDWIDTH - 1
     * @return the chessman of position (x,y);
     */
    public int getChessman(int x, int y) {
        if (0 <= x && x < 8 && 0 <= y && y < 8) {
            return chessmen[currentStep][x][y];
        } else {
            System.err.println("out of index.");
            return -1;
        }
    }

    public int getBlackNumber() {
        return blackNumber;
    }

    public int getWhiteNumber() {
        return whiteNumber;
    }

    public int[] getSuggestedPosition() {
        return suggestedPosition;
    }

    public int getCurrentChessman(){
        return currentChessman;
    }

    public int getCurrentStep(){
        return currentStep;
    }

}
