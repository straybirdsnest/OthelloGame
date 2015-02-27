package otakuplus.straybird.othellogame.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class GameHallWindow {
	protected Shell shell;
	protected Display display;

	public void createContents() {
		shell = new Shell(SWT.SHELL_TRIM);
		GridLayout gameHallLayout = new GridLayout();
		gameHallLayout.numColumns = 10;
		shell.setLayout(gameHallLayout);

		SashForm sashForm = new SashForm(shell, SWT.HORIZONTAL | SWT.BORDER);
		GridLayout sashGridLayout = new GridLayout();
		sashForm.setLayout(sashGridLayout);

		Composite composite = new Composite(sashForm, SWT.NONE);
		GridLayout compositeLayout = new GridLayout();
		GridData compositeGridData = new GridData();
		compositeGridData.horizontalSpan = 7;
		composite.setLayout(compositeLayout);
		composite.setData(compositeGridData);
		new Text(composite, SWT.MULTI).setText("Windows1");

		Composite composite2 = new Composite(sashForm, SWT.NONE);
		GridLayout composite2Layout = new GridLayout();
		composite2.setLayout(new FillLayout());
		new Text(composite2, SWT.MULTI).setText("Windows2");
	}

	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public static void main(String[] args) {
		GameHallWindow gameHallWindow = new GameHallWindow();
	}
}
