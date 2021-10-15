package card.playcards;

import card.Card;
import creature.Creature;
import creature.playcreatures.CreatureGoblin;
import main.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CardGoblin extends Card {
	
	public CardGoblin(){
		try {
			b = ImageIO.read(new File("res\\CardGoblin.png"));
		} catch (IOException e) {
			System.out.println("krinsch");
		}
		
		cost = 2;
	}

	
	public void play(Player owner, Player enemy) {
		System.out.println("Goblin played");
		owner.spawnCreature(new CreatureGoblin(owner));
	}


	@Override
	public void target(Player pTarget, Creature cTarget) {
		// TODO Auto-generated method stub
		
	}

}
