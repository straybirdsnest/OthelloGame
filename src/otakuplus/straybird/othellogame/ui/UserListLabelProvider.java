package otakuplus.straybird.othellogame.ui;

import org.eclipse.jface.viewers.OwnerDrawLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import otakuplus.straybird.othellogame.model.UserInformation;

public class UserListLabelProvider extends OwnerDrawLabelProvider {

	@Override
	protected void measure(Event event, Object element) {
		UserInformation userInformation = (UserInformation) element;
		event.setBounds(new Rectangle(event.x, event.y, 400, 20));
	}

	@Override
	protected void paint(Event event, Object element) {
		UserInformation userInformation = (UserInformation) element;
		event.gc.setForeground(Display.getDefault().getSystemColor(
				SWT.COLOR_BLACK));
		switch (event.index) {
		case 0:
			event.gc.drawText("" + userInformation.getUserInformationId(), 0, 0);
		case 1:
			event.gc.drawText(userInformation.getNickname(), 60, 0);
			break;
		case 2:
			event.gc.drawText("" + userInformation.getGameWins(), 140, 0);
			break;
		case 3:
			event.gc.drawText("" + userInformation.getGameDraws(), 190, 0);
			break;
		case 4:
			event.gc.drawText("" + userInformation.getGameLosts(), 240, 0);
			break;
		case 5:
			event.gc.drawText(userInformation.getBirthday().toString(), 290, 0);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
