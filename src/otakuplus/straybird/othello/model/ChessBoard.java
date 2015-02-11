package otakuplus.straybird.othello.model;

import java.util.Vector;

public class ChessBoard {
	
	public static final int BOARDWIDTH = 8;
	
	private Chessman chessmen[][] = null;
	
	public ChessBoard(){
		if(chessmen == null){
			int i = 0,j = 0;
			for(i=0;i<BOARDWIDTH;i++){
				for(j = 0;j <BOARDWIDTH;j++)
				{
					chessmen[i][j] = new Chessman();
				}
			}
		}
		initChessboard();
	}
	
	public void initChessboard(){
		int i = 0,j = 0;
		if(chessmen == null){
			System.err.println("Chessboard initialization fail!");
			return;
		}
		// Clean the chessboard
		for(i = 0;i<BOARDWIDTH;i++){
			for(j = 0;j< BOARDWIDTH; j++){
				chessmen[i][j].setChessman(Chessman.CHESSMAN_NONE);
			}
		}
		// Set four chessmen
		chessmen[3][3].setChessman(Chessman.CHESSMAN_WHITE);
		chessmen[3][4].setChessman(Chessman.CHESSMAN_BLACK);
		chessmen[4][3].setChessman(Chessman.CHESSMAN_BLACK);
		chessmen[4][4].setChessman(Chessman.CHESSMAN_WHITE);
	}
	
	public void set(int x, int y){
		if(chessmen != null){
			if(0<= x && x < BOARDWIDTH && 0<=y && y< BOARDWIDTH && chessmen[x][y].getChessman() == Chessman.CHESSMAN_NONE){
				
			}
		}else{
			System.err.println("Chessboard intilization failed!.");
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
