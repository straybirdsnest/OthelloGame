package otakuplus.straybird.othellogame.ui;

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
		shell.setText("奥赛罗棋");
		shell.setSize(430, 325);
		GridLayout loginWindowLayout = new GridLayout();
		loginWindowLayout.numColumns = 5;
		loginWindowLayout.makeColumnsEqualWidth = false;
		shell.setLayout(loginWindowLayout);

		Canvas backgroundImage = new Canvas(shell, SWT.NONE);
		GridData canvasGridData = new GridData(GridData.FILL_BOTH);
		canvasGridData.horizontalSpan = 5;
		canvasGridData.horizontalIndent = 40;
		canvasGridData.verticalSpan = 2;
		canvasGridData.widthHint = 313;
		canvasGridData.heightHint = 162;
		backgroundImage.setLayout(new GridLayout());
		backgroundImage.setLayoutData(canvasGridData);
		backgroundImage.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Image image = new Image(Display.getDefault(), "Othello.png");
				e.gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, 313, 162);
				image.dispose();
			}
		});

		Canvas userImage = new Canvas(shell, SWT.NONE);
		GridData userImageData = new GridData(GridData.FILL_BOTH);
		userImageData.horizontalIndent = 45;
		userImageData.widthHint = 80;
		userImageData.heightHint = 80;
		userImageData.verticalSpan = 2;
		userImage.setLayout(new GridLayout());
		userImage.setLayoutData(userImageData);
		userImage.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Image image = new Image(Display.getDefault(), "Dog-icon.png");
				e.gc.drawImage(image, 0, 0, 128, 128, 0, 0, 80, 80);
				image.dispose();
			}
		});

		Text userNameText = new Text(shell, SWT.LEFT);
		userNameText.setText("帐号");
		GridData usernameGridData = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 3, 1);
		usernameGridData.widthHint = 194;
		userNameText.setLayoutData(usernameGridData);
		Link registerLink = new Link(shell, SWT.CENTER);
		registerLink.setText("注册帐号");

		Text passWordText = new Text(shell, SWT.LEFT);
		passWordText.setText("密码");
		GridData passwordGridData = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 3, 1);
		passwordGridData.widthHint = 194;
		passWordText.setLayoutData(passwordGridData);
		Link forgetLink = new Link(shell, SWT.CENTER);
		forgetLink.setText("找回密码");

		new Label(shell, SWT.NONE);
		Button loginButton = new Button(shell, SWT.CENTER | SWT.PUSH);
		loginButton.setText("登陆");
		GridData loginGridData = new GridData();
		loginGridData.horizontalSpan = 3;
		loginGridData.widthHint = 194;
		loginButton.setLayoutData(loginGridData);

		shell.pack();
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
