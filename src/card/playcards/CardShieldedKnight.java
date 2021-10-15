package card.playcards;

import card.Card;
import creature.Creature;
import creature.playcreatures.CreatureGoblin;
import creature.playcreatures.CreatureShieldedKnight;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Player;

public class CardShieldedKnight extends Card {
    public CardShieldedKnight(){
        try {
            b = ImageIO.read(new File("res\\CardShieldedKnight.png"));
        } catch (IOException e) {
            System.out.println("krinsch");
        }

        cost = 3;
    }


    public void play(Player owner, Player enemy) {
        System.out.println("Shielded Knight played");
        CreatureShieldedKnight c = new CreatureShieldedKnight(owner);
        c.setReady(false);
        owner.addCreature(c);

    }


    @Override
    public void target(Player pTarget, Creature cTarget) {
        // TODO Auto-generated method stub

    }
}
