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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LoginWindow {

	public static void main(String[] args) {
		final Display display = new Display();
		Shell loginWindow = new Shell(display);

		loginWindow.setText("Login");
		loginWindow.setSize(480, 320);
		GridLayout loginWindowLayout = new GridLayout();
		loginWindowLayout.numColumns = 5;
		loginWindow.setLayout(loginWindowLayout);

		Canvas canvas = new Canvas(loginWindow, SWT.NONE);
		GridData canvasGridData = new GridData(GridData.FILL_HORIZONTAL);
		canvasGridData.horizontalSpan = 5;
		canvas.setLayoutData(canvasGridData);
		canvas.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				Image image = new Image(display, "89.png");
				e.gc.drawImage(image, 0, 0, 200, 50, 0, 0, 200, 50);
				image.dispose();
			}
		});

		Label userNameLabel = new Label(loginWindow, SWT.CENTER);
		userNameLabel.setText("UserName: ");
		userNameLabel.setLayoutData(new GridData());
		Text userNameText = new Text(loginWindow, SWT.CENTER);
		Label passWordLabel = new Label(loginWindow, SWT.CENTER);
		passWordLabel.setText("PassWord: ");
		Text passWordText = new Text(loginWindow, SWT.CENTER);
		Button loginButton = new Button(loginWindow, SWT.CENTER | SWT.PUSH);
		loginButton.setText("Login");
		Button canceButton = new Button(loginWindow, SWT.CENTER | SWT.PUSH);
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
