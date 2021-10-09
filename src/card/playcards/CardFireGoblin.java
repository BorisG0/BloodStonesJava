package card.playcards;

import card.Card;
import creature.Creature;
import creature.playcreatures.CreatureFireGoblin;
import main.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CardFireGoblin extends Card {
	
	public CardFireGoblin(){
		try {
			b = ImageIO.read(new File("res\\CardFireGoblin.png"));
		} catch (IOException e) {
			System.out.println("krinsch firegoblin");
		}
		
		cost = 2;
	}

	public void play(Player owner, Player enemy) {
		System.out.println("FireGoblin played");
		CreatureFireGoblin c = new CreatureFireGoblin(owner);
		c.setReady(false);
		owner.addCreature(c);
		
	}

	@Override
	public void target(Player pTarget, Creature cTarget) {
		// TODO Auto-generated method stub
		
	}

}
