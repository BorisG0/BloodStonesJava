package card.playcards;

import card.Card;
import creature.Creature;
import main.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CardShield extends Card {

    public CardShield(){
        try {
            b = ImageIO.read(new File("res\\CardShield.png"));
        } catch (IOException e) {
            System.out.println("krinsch");
        }

        cost = 1;
        targeted = true;

    }


    @Override
    public void play(Player owner, Player enemy) {

    }

    @Override
    public void target(Player pTarget, Creature cTarget) {
        if(cTarget != null) cTarget.setShielded(true);
    }
}
