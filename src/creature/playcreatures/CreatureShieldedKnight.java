package creature.playcreatures;

import creature.Creature;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Player;

public class CreatureShieldedKnight extends Creature {
    public CreatureShieldedKnight(Player owner){
        super(owner);
        try {
            b = ImageIO.read(new File("res\\CreatureShieldedKnight.png"));
        } catch (IOException e) {
            System.out.println("krinsch");
        }

        health = 2;
        damage = 2;
        isShielded = true;

        name = "Shielded Knight";
    }
}
