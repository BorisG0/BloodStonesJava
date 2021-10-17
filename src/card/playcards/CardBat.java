package card.playcards;

import card.Card;
import creature.Creature;
import creature.playcreatures.CreatureBat;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Player;

public class CardBat extends Card {
    public CardBat(){
        try {
            b = ImageIO.read(new File("res\\CardBat.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cost = 4;
    }


    public void play(Player owner, Player enemy) {
        owner.spawnCreature(new CreatureBat(owner));
    }


    @Override
    public void target(Player pTarget, Creature cTarget) {
    }
}
