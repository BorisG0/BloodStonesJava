package creature.playcreatures;

import creature.Creature;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Player;

public class CreatureGolem extends Creature {
    public CreatureGolem(Player owner){
        super(owner);
        try {
            b = ImageIO.read(new File("res\\CreatureGolem.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        health = 6;
        damage = 6;

        name = "Golem";
    }

}
