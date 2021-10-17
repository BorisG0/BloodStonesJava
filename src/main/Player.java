package main;

import card.Card;
import card.playcards.*;
import creature.Creature;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
	
	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	private ArrayList<Creature> creatures;
	private BufferedImage b;
	private int health;
	private String name;
	private int activeStones, maxStones;
	
	Player(String name, BufferedImage b){
		this.b = b;
		this.name = name;
		deck = new ArrayList<Card>();
		for(int i = 0; i < 4; i++) {
			//deck.add(new Blank());
			deck.add(new CardGoblin());
			deck.add(new CardGoblinGang());
			deck.add(new CardFireGoblin());
			deck.add(new CardFireBall());
			deck.add(new CardArmoredOgre());
			deck.add(new CardShieldedKnight());
			deck.add(new CardUndeadKnight());
			deck.add(new CardBook());
			deck.add(new CardShield());
			deck.add(new CardGolem());
		}
		
		Collections.shuffle(deck);
		
		hand = new ArrayList<Card>();
		for(int i = 0; i < 5; i++) {
			drawCard();
		}
		
		creatures = new ArrayList<Creature>();
		
		health = 30;
		activeStones = 0;
		maxStones = 0;
		
	}
	
	public void nextTurn() {
		drawCard();
		setCreaturesReady();
		maxStones++;
		activeStones = maxStones;
	}
	
	public Card drawCard() {
		if(deck.size() == 0) return null;
		
		Card c = deck.get(deck.size() - 1);
		deck.remove(deck.size() - 1);
		
		hand.add(c);
		
		return c;
		
	}
	
	public void setCreaturesReady() {
		for(Creature c : creatures) {
			c.setReady(true);
		}
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public ArrayList<Creature> getCreatures(){
		return creatures;
	}
	
	public void addCreature(Creature c) {
		creatures.add(c);
	}
	
	public void setImage(BufferedImage image) {
		b = image;
	}
	
	public BufferedImage getImage() {
		return b;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void takeHit(int damage) {
		health -= damage;
		if(health <= 0) {
			System.out.println(name + " died");
			System.exit(0);
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getActiveStones() {
		return activeStones;
	}
	
	public void setActiveStones(int activeStones) {
		this.activeStones = activeStones;
	}
	
	public int getMaxStones() {
		return maxStones;
	}
	
	public void setMaxStones(int maxStones) {
		this.maxStones = maxStones;
	}

	public void spawnCreature(Creature c){
		c.setReady(false);
		this.addCreature(c);
	}

}
