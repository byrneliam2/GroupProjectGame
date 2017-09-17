package npc;

import javax.swing.Timer;

public class NPC extends Player {
	private static final int SPEED = 100;// rate in milliseconds that NPC is updated

	private Timer timer = new Timer(SPEED, (e) -> update());
	private Player p;
	private ControlScheme control;

	public NPC(Player p, ControlScheme cs) {
		this.p = p;
		this.control = cs;
	}

	/**
	 * Pauses the timer which controls the NPC, which causes the NPC to simply stand still.
	 */
	public void pause() {
		timer.stop();
	}

	/**
	 * Starts the timer which moves and controls the NPC. NPC will start moving around.
	 */
	public void start() {
		timer.start();
	}

	private void update() {
		control.doBestAction(p, this);
	}

}
