package card.playcards;

import card.Card;
import creature.Creature;
import creature.playcreatures.CreatureCrocodile;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Player;

public class CardCrocodile extends Card {
    public CardCrocodile(){
        try {
            b = ImageIO.read(new File("res\\CardCrocodile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cost = 5;
    }


    public void play(Player owner, Player enemy) {
        owner.spawnCreature(new CreatureCrocodile(owner));
    }


    @Override
    public void target(Player pTarget, Creature cTarget) {
    }
}
