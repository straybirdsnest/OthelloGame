package otakuplus.straybird.othello.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LoginWindow {

	public static void main(String[] args) {
		Display display = new Display();
		Shell loginWindow = new Shell(display);
		loginWindow.setText("Login");
		loginWindow.setSize(480, 320);
		GridLayout loginWindowLayout = new GridLayout();
		loginWindowLayout.numColumns = 2;
		loginWindow.setLayout(loginWindowLayout);
		Label userNameLabel = new Label(loginWindow, SWT.NONE);
		userNameLabel.setText("UserName: ");
		Text userNameText = new Text(loginWindow, SWT.NONE);
		Label passWordLabel = new Label(loginWindow, SWT.NONE);
		passWordLabel.setText("PassWord: ");
		Text passWordText = new Text(loginWindow, SWT.NONE);
		Button loginButton = new Button(loginWindow, SWT.PUSH);
		loginButton.setText("Login");
		Button canceButton = new Button(loginWindow, SWT.PUSH);
		canceButton.setText("Cance");

		loginWindow.open();
		while (!loginWindow.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
