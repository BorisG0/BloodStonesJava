package card.playcards;

import card.Card;
import creature.Creature;
import creature.playcreatures.CreatureGoblin;
import main.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CardGoblinGang extends Card {

	public CardGoblinGang(){
		try {
			b = ImageIO.read(new File("res\\CardGoblinGang.png"));
		} catch (IOException e) {
			System.out.println("krinsch");
		}
		
		cost = 5;
	}

	
	public void play(Player owner, Player enemy) {
		owner.spawnCreature(new CreatureGoblin(owner));
		owner.spawnCreature(new CreatureGoblin(owner));
		owner.spawnCreature(new CreatureGoblin(owner));
	}


	@Override
	public void target(Player pTarget, Creature cTarget) {
		// TODO Auto-generated method stub
		
	}

}
