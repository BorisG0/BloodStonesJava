package card.playcards;

import card.Card;
import creature.Creature;
import creature.playcreatures.CreatureArmoredOgre;
import main.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CardArmoredOgre extends Card {
	
	public CardArmoredOgre(){
		try {
			b = ImageIO.read(new File("res\\CardArmoredOgre.png"));
		} catch (IOException e) {
			System.out.println("krinsch");
		}
		cost = 5;
	}

	@Override
	public void play(Player owner, Player enemy) {
		System.out.println("ArmoredOgre played");
		owner.spawnCreature(new CreatureArmoredOgre(owner));
	}

	@Override
	public void target(Player pTarget, Creature cTarget) {
		// TODO Auto-generated method stub
		
	}

}
