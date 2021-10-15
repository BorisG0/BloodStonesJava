package card.playcards;

import card.Card;
import creature.Creature;
import creature.playcreatures.CreatureUndeadKnight;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Player;

public class CardUndeadKnight extends Card {
    public CardUndeadKnight(){
        try {
            b = ImageIO.read(new File("res\\CardUndeadKnight.png"));
        } catch (IOException e) {
            System.out.println("krinsch");
        }

        cost = 3;
    }

    public void play(Player owner, Player enemy) {
        System.out.println("Undead Knight played");
        owner.spawnCreature(new CreatureUndeadKnight(owner));
    }


    @Override
    public void target(Player pTarget, Creature cTarget) {
    }
}
