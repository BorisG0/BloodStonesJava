package card.playcards;

import card.Card;
import creature.Creature;
import creature.playcreatures.CreatureGolem;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Player;

public class CardGolem extends Card {
    public CardGolem(){
        try {
            b = ImageIO.read(new File("res\\CardGolem.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cost = 8;
    }


    public void play(Player owner, Player enemy) {
        owner.spawnCreature(new CreatureGolem(owner));
    }


    @Override
    public void target(Player pTarget, Creature cTarget) {
    }
}
