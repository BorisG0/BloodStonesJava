package creature.playcreatures;

import creature.Creature;
import main.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CreatureGoblin extends Creature {
	
	
	public CreatureGoblin(Player owner){
		super(owner);
		try {
			b = ImageIO.read(new File("res\\CreatureHodenKobold.png"));
		} catch (IOException e) {
			System.out.println("krinsch");
		}
		
		health = 2;
		damage = 1;
		
		name = "hodenkobold";
	}

}
