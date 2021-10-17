package creature.playcreatures;

import creature.Creature;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Player;

public class CreatureCrocodile extends Creature {
    public CreatureCrocodile(Player owner){
        super(owner);
        try {
            b = ImageIO.read(new File("res\\CreatureCrocodile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        health = 4;
        damage = 4;
        name = "Crocodile";
    }
}
