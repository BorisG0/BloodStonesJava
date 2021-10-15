package card.playcards;

import card.Card;
import creature.Creature;
import main.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CardBook extends Card {

    private int amount;

    public CardBook(){
        try {
            b = ImageIO.read(new File("res\\CardBook.png"));
        } catch (IOException e) {
            System.out.println("krinsch");
        }

        cost = 5;
        amount = 3;

    }


    public void play(Player owner, Player enemy) {
        for(int i = 0; i < amount; i++){
            owner.drawCard();
        }

    }


    public void target(Player pTarget, Creature cTarget) {

    }
}
