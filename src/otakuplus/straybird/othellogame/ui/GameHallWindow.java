package otakuplus.straybird.othellogame.ui;

import java.util.Date;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import otakuplus.straybird.othellogame.model.UserInformation;

public class GameHallWindow {
	protected Shell shell;
	protected Display display;

	public void createContents() {
		shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setText("游戏大厅");
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
		composite2.setLayout(composite2Layout);
		TableViewer tableViewer = new TableViewer(composite2, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setLabelProvider(new LabelProvider());
		UserInformation[] userInfo = new UserInformation[1];
		userInfo[0] = new UserInformation();
		userInfo[0].setUserInformationId(999);
		userInfo[0].setNickname("Jack");
		userInfo[0].setGameWins(100);
		userInfo[0].setGameDraws(100);
		userInfo[0].setGameLosts(50);
		userInfo[0].setBirthday(new Date());
		tableViewer.setInput(userInfo);
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
		gameHallWindow.open();
	}
}
