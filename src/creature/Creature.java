package creature;

import main.Player;

import java.awt.image.BufferedImage;

public abstract class Creature {
	protected Player owner;
	protected BufferedImage b;
	protected int health, damage;
	protected String name;
	protected boolean ready;
	
	public Creature(Player owner) {
		this.owner = owner;
	}
	
	public BufferedImage getImage() {
		return b;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void takeHit(int damageTaken) {
		health -= damageTaken;
		if(health <= 0) owner.getCreatures().remove(this);
	}
	
	public String getName() {
		return name;
	}
	
	public void setReady(boolean ready) {
		this.ready = ready;
	}
	
	public boolean isReady() {
		return ready;
	}
	
	public void attack(Creature attackedCreature) {
		attackedCreature.takeHit(damage);
		takeHit(attackedCreature.getDamage());
		setReady(false);
	}
	
	public void attack(Player attackedPlayer) {
		attackedPlayer.takeHit(damage);
		setReady(false);
	}

}
