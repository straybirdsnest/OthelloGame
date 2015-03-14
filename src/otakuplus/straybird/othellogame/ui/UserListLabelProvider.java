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
			event.gc.drawText("" + userInformation.getUserId(), event.x + 2,
					event.y + 2);
			break;
		case 1:
			event.gc.drawText(userInformation.getNickname(), event.x + 2,
					event.y + 2);
			break;
		case 2:
			event.gc.drawText("" + userInformation.getGameWins(), event.x + 2,
					event.y + 2);
			break;
		case 3:
			event.gc.drawText("" + userInformation.getGameDraws(), event.x + 2,
					event.y + 2);
			break;
		case 4:
			event.gc.drawText("" + userInformation.getGameLosts(), event.x + 2,
					event.y + 2);
			break;
		case 5:
			event.gc.drawText("" + userInformation.getRankPoints(),
					event.x + 2, event.y + 2);
			break;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
