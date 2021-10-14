package card;

import creature.Creature;
import main.Player;

import java.awt.image.BufferedImage;

public abstract class Card {
	protected BufferedImage b;
	protected int cost;
	protected boolean targeted = false;
	private String text;
	
	public BufferedImage getImage() {
		return b;
	}
	
	public int getCost() {
		return cost;
	}
	
	public boolean isTargeted() {
		return targeted;
	}
	
	abstract public void play(Player owner, Player enemy);
	
	abstract public void target(Player pTarget, Creature cTarget);

}
