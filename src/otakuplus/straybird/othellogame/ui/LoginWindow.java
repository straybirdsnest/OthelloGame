package otakuplus.straybird.othellogame.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import otakuplus.straybird.othellogame.MainApplication;

public class LoginWindow {
	protected MainApplication mainApplication;
	protected Shell shell;
	protected Display display;
	protected Text userNameText;
	protected Text passWordText;

	public LoginWindow(MainApplication mainApplication) {
		this.mainApplication = mainApplication;
	}

	protected void createContents() {
		Monitor monitor = Display.getDefault().getPrimaryMonitor();

		shell = new Shell(SWT.SHELL_TRIM);
		shell.setText("奥赛罗棋");
		shell.setSize(430, 325);
		shell.setLocation((monitor.getBounds().width - 430) / 2,
				(monitor.getBounds().height - 325) / 2);
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
				e.gc.drawImage(image, 0, 0, image.getBounds().width,
						image.getBounds().height, 0, 0, 313, 162);
				image.dispose();
			}
		});

		Canvas userImage = new Canvas(shell, SWT.NONE);
		GridData userImageData = new GridData(GridData.FILL_BOTH);
		userImageData.horizontalIndent = 45;
		userImageData.widthHint = 80;
		userImageData.heightHint = 80;
		userImageData.verticalSpan = 3;
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

		userNameText = new Text(shell, SWT.LEFT);
		userNameText.setText("帐号");
		userNameText.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (userNameText.getText().equals("帐号")) {
					userNameText.setText("");
				} else if (userNameText.getText().equals("")) {
					userNameText.setText("帐号");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (userNameText.getText().equals("帐号")) {
					userNameText.setText("");
				} else if (userNameText.getText().equals("")) {
					userNameText.setText("帐号");
				}
			}
		});

		GridData usernameGridData = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 3, 1);
		usernameGridData.widthHint = 194;
		userNameText.setLayoutData(usernameGridData);
		Link registerLink = new Link(shell, SWT.CENTER);
		registerLink.setText("注册帐号");

		passWordText = new Text(shell, SWT.LEFT);
		passWordText.setText("密码");
		passWordText.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (passWordText.getText().equals("密码")) {
					passWordText.setText("");
					passWordText.setEchoChar('*');
				} else if (passWordText.getText().equals("")) {
					passWordText.setText("密码");
					passWordText.setEchoChar('\0');
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (passWordText.getText().equals("密码")) {
					passWordText.setText("");
					passWordText.setEchoChar('*');
				} else if (passWordText.getText().equals("")) {
					passWordText.setText("密码");
					passWordText.setEchoChar('\0');
				}
			}
		});
		GridData passwordGridData = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 3, 1);
		passwordGridData.widthHint = 194;
		passWordText.setLayoutData(passwordGridData);
		Link forgetLink = new Link(shell, SWT.CENTER);
		forgetLink.setText("找回密码");

		Button remeberButton = new Button(shell, SWT.CHECK);
		GridData remeberButtonGridData = new GridData();
		remeberButton.setLayoutData(remeberButtonGridData);

		Label remeberLabel = new Label(shell, SWT.CENTER);
		remeberLabel.setText("记住密码");

		Button autoButton = new Button(shell, SWT.CHECK);

		Label autoLabel = new Label(shell, SWT.CENTER);
		autoLabel.setText("自动登陆");
		GridData autoLabelGridData = new GridData();
		autoLabelGridData.horizontalIndent = -80;
		autoLabel.setLayoutData(autoLabelGridData);

		new Label(shell, SWT.NONE);
		Button loginButton = new Button(shell, SWT.CENTER | SWT.PUSH);
		loginButton.setText("登陆");
		loginButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
			}

			@Override
			public void mouseDown(MouseEvent e) {
				String username = userNameText.getText();
				String password = passWordText.getText();
				if (username.length() >0 && password.length() > 0) {
					mainApplication.login(username,password);
				}
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});

		GridData loginGridData = new GridData();
		loginGridData.horizontalSpan = 3;
		loginGridData.widthHint = 194;
		loginButton.setLayoutData(loginGridData);

		shell.pack();
	}
	
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
	}

	public void hide(){
		shell.setVisible(false);
	}
	
	public void show(){
		shell.setVisible(true);
	}
	
	public void close(){
		shell.close();
	}
	
	public static void main(String[] args) {

	}
}
