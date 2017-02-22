package edu.virginia.lab4test;

import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class QuestManager implements IEventListener {
	
	private Sprite item;

	public QuestManager(Sprite item) {
		this.item = item;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getEventType().equals(PickedUpEvent.COIN_PICKED_UP) && event.getSource() == item) {
			System.out.print("Quest is complete!");
		}
	}

}
