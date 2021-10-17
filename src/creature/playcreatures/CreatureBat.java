package creature.playcreatures;

import creature.Creature;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Player;

public class CreatureBat extends Creature {
    public CreatureBat(Player owner){
        super(owner);
        try {
            b = ImageIO.read(new File("res\\CreatureBat.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        health = 1;
        damage = 1;
        isFlying = true;
        name = "Bat";
    }
}
