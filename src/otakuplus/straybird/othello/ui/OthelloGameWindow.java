package otakuplus.straybird.othello.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class OthelloGameWindow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();
		Shell othelloGameWindow = new Shell(display);
		
		othelloGameWindow.open();
		while (!othelloGameWindow.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
