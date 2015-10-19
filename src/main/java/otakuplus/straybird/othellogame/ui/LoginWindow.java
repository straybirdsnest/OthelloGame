package otakuplus.straybird.othellogame.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;

public class LoginWindow {
	protected Shell shell;
	protected Display display;
	protected Text userNameText;
	protected Text passWordText;

	public LoginWindow() {
	}

	protected void createContents() {
		Monitor monitor = Display.getDefault().getPrimaryMonitor();

		shell = new Shell(display, SWT.SHELL_TRIM);
		ApplicationContextSingleton.getInstance().setLoginWindowShell(shell);
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
			public void paintControl(PaintEvent e) {
				Image image = new Image(Display.getDefault(), "Dog-icon.png");
				e.gc.drawImage(image, 0, 0, 128, 128, 0, 0, 80, 80);
				image.dispose();
			}
		});

		userNameText = new Text(shell, SWT.LEFT);
		userNameText.setText("帐号");
		userNameText.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				if (userNameText.getText().equals("帐号")) {
					userNameText.setText("");
				} else if (userNameText.getText().equals("")) {
					userNameText.setText("帐号");
				}
			}

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

			public void focusLost(FocusEvent e) {
				if (passWordText.getText().equals("密码")) {
					passWordText.setText("");
					passWordText.setEchoChar('*');
				} else if (passWordText.getText().equals("")) {
					passWordText.setText("密码");
					passWordText.setEchoChar('\0');
				}
			}

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
		loginButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
                String username = userNameText.getText();
                String password = passWordText.getText();
                if (username.length() > 0 && password.length() > 0) {
                    ApplicationContextSingleton.getInstance().login();
                }
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		GridData loginGridData = new GridData();
		loginGridData.horizontalSpan = 3;
		loginGridData.widthHint = 194;
		loginButton.setLayoutData(loginGridData);

		shell.addListener(SWT.Close, new Listener() {
            public void handleEvent(Event event) {
                event.doit = false;
                MessageBox messageBox = new MessageBox(shell,
                        SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
                messageBox.setText("确认退出");
                messageBox.setMessage("确实要退出Othello游戏吗？");
                int result = messageBox.open();
                if (result == SWT.YES) {
                    ApplicationContextSingleton.getInstance().disconnect();
                    event.doit = true;
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
		shell.setVisible(false);
	}

	public void show() {
		shell.setVisible(true);
	}

    public String getUsername(){
        return userNameText.getText();
    }

    public String getPassword(){
        return passWordText.getText();
    }
	
}
