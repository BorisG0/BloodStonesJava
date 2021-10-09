package card.playcards;

import card.Card;
import creature.Creature;
import main.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CardFireBall extends Card {
	
	int damage = 3;
	
	public CardFireBall(){
		try {
			b = ImageIO.read(new File("res\\CardFireBall.png"));
		} catch (IOException e) {
			System.out.println("krinsch");
		}
		
		cost = 3;
		targeted = true;
		
	}

	public void play(Player owner, Player enemy) {
		
	}
	
	public void target(Player pTarget, Creature cTarget) {
		if(cTarget != null) cTarget.takeHit(damage);
		if(pTarget != null) pTarget.takeHit(damage);
	}

}
