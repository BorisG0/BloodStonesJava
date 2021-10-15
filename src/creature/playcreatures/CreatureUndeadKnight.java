package creature.playcreatures;

import creature.Creature;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Player;

public class CreatureUndeadKnight extends Creature {
    public CreatureUndeadKnight(Player owner){
        super(owner);
        try {
            b = ImageIO.read(new File("res\\CreatureUndeadKnight.png"));
        } catch (IOException e) {
            System.out.println("krinsch");
        }

        health = 2;
        damage = 2;
        isUndead = true;

        name = "Undead Knight";
    }
}
