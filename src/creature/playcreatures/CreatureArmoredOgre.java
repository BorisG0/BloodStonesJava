package creature.playcreatures;

import creature.Creature;
import main.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CreatureArmoredOgre extends Creature {

	public CreatureArmoredOgre(Player owner) {
		super(owner);
		try {
			b = ImageIO.read(new File("res\\CreatureArmoredOgre.png"));
		} catch (IOException e) {
			System.out.println("krinsch");
		}
		
		health = 7;
		damage = 3;
		isTaunt = true;
		
		name = "armored ogre";
	}

}
