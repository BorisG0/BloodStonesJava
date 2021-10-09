package card.playcards;

import card.Card;
import creature.Creature;
import main.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Blank extends Card {
	public Blank(){
		try {
			b = ImageIO.read(new File("res\\Layout.png"));
		} catch (IOException e) {
			System.out.println("krinsch");
		}
		cost = 0;
	}


	public void play(Player owner, Player enemy) {
		
	}


	@Override
	public void target(Player pTarget, Creature cTarget) {
		// TODO Auto-generated method stub
		
	}
	
	

}
