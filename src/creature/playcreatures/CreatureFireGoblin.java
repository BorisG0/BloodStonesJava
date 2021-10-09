package creature.playcreatures;

import creature.Creature;
import main.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CreatureFireGoblin extends Creature {

	public CreatureFireGoblin(Player owner){
		super(owner);
		try {
			b = ImageIO.read(new File("res\\CreatureFireGoblin.png"));
		} catch (IOException e) {
			System.out.println("krinsch");
		}
		
		health = 1;
		damage = 3;
		
		name = "firegoblin";
	}
	
}
