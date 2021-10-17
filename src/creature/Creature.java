package creature;

import main.Player;

import java.awt.image.BufferedImage;

public abstract class Creature {
	protected Player owner;
	protected BufferedImage b;
	protected int health, damage;
	protected String name;
	protected boolean ready;

	/**
	 * Special abilities.
	 * */
	protected boolean isTaunt = false; //Protects other creatures and the player, has to be attacked first.
	protected boolean isShielded = false; //Shield absorbs first hit taken, then the shield is lost.
	protected boolean isUndead = false; //When dying, re-enters the battlefield with 1 Health and without Undead.
	protected boolean isFlying = false; //Can not be attacked by other creatures that don't have flying. Can attack anything regardless of taunt.
	
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
		if(this.isShielded){
			this.isShielded = false;
		} else {
			health -= damageTaken;
		}
		if(health <= 0){
			owner.getCreatures().remove(this);
			if(this.isUndead){
				owner.spawnCreature(this);
				this.health = 1;
				this.isUndead = false;
			}
		}
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

	public boolean isTaunt(){
		return isTaunt;
	}

	public boolean isUndead(){
		return this.isUndead;
	}

	public boolean isShielded(){
		return this.isShielded;
	}

	public boolean isFlying(){
		return this.isFlying;
	}

	public void setShielded (boolean isShielded){
		this.isShielded = isShielded;
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
