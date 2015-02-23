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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();
		Shell gameHallWindow = new Shell(display);
		GridLayout gameHallLayout = new GridLayout();
		gameHallLayout.numColumns = 10;
		gameHallWindow.setLayout(gameHallLayout);

		SashForm sashForm = new SashForm(gameHallWindow, SWT.HORIZONTAL
				| SWT.BORDER);
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

		gameHallWindow.open();
		while (!gameHallWindow.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
