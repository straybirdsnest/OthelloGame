package otakuplus.straybird.othello.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LoginWindow {
	protected Shell shell;

	public void open() {
		final Display display = Display.getDefault();
		createContents();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	protected void createContents() {
		shell = new Shell(SWT.SHELL_TRIM);
		shell.setText("Login");
		//shell.setSize(480, 320);
		GridLayout loginWindowLayout = new GridLayout();
		loginWindowLayout.numColumns = 7;
		loginWindowLayout.makeColumnsEqualWidth = false;
		shell.setLayout(loginWindowLayout);

		Canvas canvas = new Canvas(shell, SWT.NONE);
		GridData canvasGridData = new GridData(GridData.FILL_BOTH);
		canvasGridData.horizontalSpan = 7;
		canvas.setLayoutData(canvasGridData);
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Image image = new Image(Display.getDefault(), "Othello.png");
				e.gc.drawImage(image, 0, 0, 391, 192, 0, 0, 391, 192);
				image.dispose();
			}
		});

		new Label(shell, SWT.CENTER).setLayoutData(new GridData(SWT.LEFT,
				SWT.CENTER, false, false, 1, 1));

		Label userNameLabel = new Label(shell, SWT.CENTER);
		userNameLabel.setText("UserName: ");
		userNameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1));
		Text userNameText = new Text(shell, SWT.CENTER);
		userNameText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 3, 1));
		Link registerLink = new Link(shell, SWT.CENTER);
		registerLink.setText("Register");
		new Label(shell, SWT.CENTER).setLayoutData(new GridData(SWT.LEFT,
				SWT.CENTER, false, false, 1, 1));
		new Label(shell, SWT.CENTER).setLayoutData(new GridData(SWT.LEFT,
				SWT.CENTER, false, false, 1, 1));

		Label passWordLabel = new Label(shell, SWT.CENTER);
		passWordLabel.setText("PassWord: ");
		Text passWordText = new Text(shell, SWT.CENTER);
		passWordText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 3, 1));
		Link forgetLink = new Link(shell, SWT.CENTER);
		forgetLink.setText("Forget Password");
		new Label(shell, SWT.CENTER).setLayoutData(new GridData(SWT.LEFT,
				SWT.CENTER, false, false, 1, 1));

		Button loginButton = new Button(shell, SWT.CENTER | SWT.PUSH);
		loginButton.setText("Login");
		Button canceButton = new Button(shell, SWT.CENTER | SWT.PUSH);
		canceButton.setText("Cance");
	}

	public static void main(String[] args) {
		try {
			LoginWindow loginWindow = new LoginWindow();
			loginWindow.open();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
