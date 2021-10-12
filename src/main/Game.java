package main;

import card.Card;
import creature.Creature;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel implements MouseListener, KeyListener{

	JFrame frame;
	BufferedImage selectedImage, tauntImage, selectingTargetImage, notReadyImage, backSide, endTurnImage, nextTurnImage,
	stoneImage, emptyStoneImage, image, player1Image, player2Image;
	Player player1;
	Player player2;
	Player activePlayer, passivePlayer;
	boolean player1Active = true, isTurnEnded = false;
	boolean enemyHasTaunt = false;
	int mx, my, fw, fh;
	int selectedHandCard = -1, selectedCreature = -1, selectedEnemyCreature = -1;
	ArrayList<Card> activeHand, passiveHand;
	ArrayList<Creature> activeCreatures, passiveCreatures;
	
	Card inspectedCard = null;
	Creature inspectedCreature = null;

	int handImageSizeX = 130;
	int handImageSizeY = (int)(((double)handImageSizeX) * Math.sqrt(2));
	int handCardHorizontalGap = 20;
	int handCardVerticalGap = 30;
	int handNumberSizeX = (int)(((double)handImageSizeX / 512) * 124);
	int handNumberSizeY = (int)(((double)handImageSizeX / 512) * 164);

	int creatureImageSizeX = 160;
	int creatureImageSizeY = (int)(((double)creatureImageSizeX) * Math.sqrt(2));
	int creatureHorizontalGap = 20;
	int creatureVerticalGap = 30;
	int creatureNumberSizeX = (int)(((double)creatureImageSizeX / 512) * 124);
	int creatureNumberSizeY = (int)(((double)creatureImageSizeX / 512) * 164);
	
	int stoneSize = 60;
	
	boolean selectingTarget = false;
	Card selectingTargetFor;

	Game() {

		System.out.println(handImageSizeX);
		System.out.println(handImageSizeY);

		try {
			selectedImage = ImageIO.read(new File("res\\Selected.png"));
			tauntImage = ImageIO.read(new File("res\\Taunt.png"));
			selectingTargetImage = ImageIO.read(new File("res\\selectingTarget.png"));
			notReadyImage = ImageIO.read(new File("res\\notReady.png"));
			backSide = ImageIO.read(new File("res\\BackSide.png"));
			player1Image = ImageIO.read(new File("res\\CharDemonMaster.png"));
			player2Image = ImageIO.read(new File("res\\CharMage.png"));
			stoneImage = ImageIO.read(new File("res\\Stone.png"));
			emptyStoneImage = ImageIO.read(new File("res\\EmptyStone.png"));
			endTurnImage = ImageIO.read(new File("res\\endTurn.png"));
			nextTurnImage = ImageIO.read(new File("res\\nextTurn.png"));
		} catch (IOException e) {
			System.out.println("krinsch");
		}

		player1 = new Player("Garrosh", player1Image);
		player2 = new Player("Ryze", player2Image);

		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(0, 0, 1800, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setVisible(true);
		setBackground(Color.gray);
		addMouseListener(this);
		setLayout(null);
		frame.addKeyListener(this);

		
		frame.add(this);


		nextTurn();

		frame.revalidate();


		System.out.println(frame.getHeight());
		System.out.println(frame.getWidth());


		frame.repaint();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//		g.drawImage(b,
		//				0, 0, 512, 724,
		//				0, 0, 512, 724,
		//				null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 35));
		
		fh = frame.getHeight();
		fw = frame.getWidth();
		
		if(isTurnEnded) {
			g.drawImage(nextTurnImage,
					0,
					fh/2 - stoneSize/2,
					stoneSize * 2,
					fh/2 + stoneSize/2,
					0, 0, 200, 100,
					null);
		}else {
			drawPassiveHand(g, passiveHand);

			drawPassiveCreatures(g, passiveCreatures);
			
			
			drawActiveHand(g, activeHand);

			drawActiveCreatures(g, activeCreatures);
			
			drawStones(g);

			if(selectedHandCard != -1) selectHandCard(g, selectedHandCard);
			if(selectedCreature != -1) selectCreature(g, selectedCreature);
			
			if(inspectedCard != null) drawInspectedCard(g, inspectedCard);
			if(inspectedCreature != null) drawInspectedCreature(g, inspectedCreature);
		}
		
	}
	
	public void endTurn() {
		isTurnEnded = true;
	}

	public void nextTurn() {
		player1Active = !player1Active;

		if(player1Active) {
			activePlayer = player1;

			passivePlayer = player2;
		}else {
			activePlayer = player2;

			passivePlayer = player1;
		}

		activePlayer.nextTurn();

		activeHand = activePlayer.getHand();
		passiveHand = passivePlayer.getHand();

		activeCreatures = activePlayer.getCreatures();
		passiveCreatures = passivePlayer.getCreatures();

		selectedHandCard = -1;
		selectedCreature = -1;

		//activePlayer.setCreaturesReady();
		
		//activePlayer.setMaxStones(activePlayer.getMaxStones() + 1);
		//activePlayer.setActiveStones(activePlayer.getMaxStones());
		
		System.out.println("\nnext turn\n");
		isTurnEnded = false;

	}
	
	void drawStones(Graphics g) {
		
		g.drawImage(endTurnImage,
				0,
				fh/2 - stoneSize/2,
				stoneSize * 2,
				fh/2 + stoneSize/2,
				0, 0, 200, 100,
				null);
		
		for(int i = 0; i < activePlayer.getMaxStones(); i++) {
			g.drawImage(emptyStoneImage,
					stoneSize * 2 + stoneSize * i,
					fh/2 - stoneSize/2,
					stoneSize * 3 + stoneSize * i,
					fh/2 + stoneSize/2,
					0, 0, 100, 100,
					null);
		}
		
		for(int i = 0; i < activePlayer.getActiveStones(); i++) {
			g.drawImage(stoneImage,
					stoneSize * 2 + stoneSize * i,
					fh/2 - stoneSize/2,
					stoneSize * 3 + stoneSize * i,
					fh/2 + stoneSize/2,
					0, 0, 100, 100,
					null);
		}
		
	}
	
	public void drawInspectedCard(Graphics g, Card c) {
		g.drawImage(c.getImage(),
				100,
				100,
				100 + 512,
				100 + 724,
				0, 0, 512, 724,
				null);
		
		g.drawImage(getNumberImage(c.getCost()),
				100 + 512 - 124,
				100 + 724 - 164,
				100 + 512,
				100 + 724,
				0, 0, 124, 164,
				null);
	}
	
	public void drawInspectedCreature(Graphics g, Creature c) {
		g.drawImage(c.getImage(),
				100,
				100,
				100 + 512,
				100 + 724,
				0, 0, 512, 724,
				null);
		
		g.drawImage(getNumberImage(c.getDamage()),
				100,
				100 + 724 - 164,
				100 + 124,
				100 + 724,
				0, 0, 124, 164,
				null);
		
		g.drawImage(getNumberImage(c.getHealth()),
				100 + 512 - 124,
				100 + 724 - 164,
				100 + 512,
				100 + 724,
				0, 0, 124, 164,
				null);
		
	}

	public void drawActiveHand(Graphics g, ArrayList<Card> hand) {

		g.drawImage(activePlayer.getImage(),
				0,
				fh - handImageSizeY - handCardVerticalGap,
				handImageSizeX,
				fh - handCardVerticalGap,
				0, 0, 512, 724,
				null);
		
		g.drawImage(getNumberImage(activePlayer.getHealth()),
				0 + (handImageSizeX / 2) - (handNumberSizeX / 2),
				fh - handImageSizeY - handCardVerticalGap + (handImageSizeY / 2) - (handNumberSizeY / 2) + handImageSizeY / 3,
				handImageSizeX / 2 + handNumberSizeX/2,
				fh - handCardVerticalGap - handImageSizeY / 2 + handNumberSizeY/2 + handImageSizeY / 3,
				0, 0, 124, 164,
				null);
		
		if(selectingTarget) {
			g.drawImage(selectingTargetImage,
					0,
					fh - handImageSizeY - handCardVerticalGap,
					handImageSizeX,
					fh - handCardVerticalGap,
					0, 0, 512, 724,
					null);
		}

		for(int i = 0; i < hand.size(); i++) {
			image = hand.get(i).getImage();

			int x = handImageSizeX + handCardHorizontalGap + i * (handImageSizeX + handCardHorizontalGap);
			int y = fh - handImageSizeY - handCardVerticalGap;
			
			g.drawImage(image,
					x,
					y,
					x + handImageSizeX,
					y + handImageSizeY,
					0, 0, 512, 724,
					null);
			
			g.drawImage(getNumberImage(hand.get(i).getCost()),
					x + handImageSizeX - handNumberSizeX,
					y + handImageSizeY - handNumberSizeY,
					x + handImageSizeX,
					y + + handImageSizeY,
					0, 0, 124, 164,
					null);
		}
	}

	public void drawPassiveHand(Graphics g, ArrayList<Card> hand) {

		g.drawImage(passivePlayer.getImage(),
				0,
				handCardVerticalGap,
				handImageSizeX,
				handCardVerticalGap + handImageSizeY,
				0, 0, 512, 724,
				null);
		
		g.drawImage(getNumberImage(passivePlayer.getHealth()),
				0  + (handImageSizeX / 2) - (handNumberSizeX / 2),
				handCardVerticalGap + (handImageSizeY / 2) - (handNumberSizeY / 2) + handImageSizeY / 3,
				handImageSizeX / 2 + handNumberSizeX/2,
				handCardVerticalGap + handImageSizeY - handImageSizeY / 2 + handNumberSizeY/2 + handImageSizeY / 3,
				0, 0, 124, 164,
				null);

		image = backSide;
		for(int i = 0; i < hand.size(); i++) {

			g.drawImage(image,
					handImageSizeX + handCardHorizontalGap + i * (handImageSizeX + handCardHorizontalGap),
					handCardVerticalGap,
					handImageSizeX * 2 + handCardHorizontalGap + i * (handImageSizeX + handCardHorizontalGap),
					handCardVerticalGap + handImageSizeY,
					0, 0, 512, 724,
					null);
		}
	}


	public void selectHandCard(Graphics g,int i) {
		g.drawImage(selectedImage,
				handCardHorizontalGap + handImageSizeX + i * (handImageSizeX + handCardHorizontalGap),
				fh - handImageSizeY - handCardVerticalGap,
				handImageSizeX + handImageSizeX + handCardHorizontalGap + i * (handImageSizeX + handCardHorizontalGap),
				fh - handCardVerticalGap,
				0, 0, 512, 724,
				null);
	}

	public void selectCreature(Graphics g,int i) {
		g.drawImage(selectedImage,
				creatureHorizontalGap + i * (creatureImageSizeX + creatureHorizontalGap),
				fh - handImageSizeY - handCardVerticalGap - creatureImageSizeY - creatureVerticalGap,
				creatureImageSizeX + creatureHorizontalGap + i * (creatureImageSizeX + creatureHorizontalGap),
				fh - handCardVerticalGap - handImageSizeY - creatureVerticalGap,
				0, 0, 512, 724,
				null);
	}

	public void drawCreature(Graphics g, Creature c, int x, int y){
		image = c.getImage();
		g.drawImage(image,
				x,
				y,
				x + creatureImageSizeX,
				y + creatureImageSizeY,
				0, 0, 512, 724,
				null);

		g.drawImage(getNumberImage(c.getDamage()),
				x,
				y + creatureImageSizeY - creatureNumberSizeY,
				x + creatureNumberSizeX,
				y + + creatureImageSizeY,
				0, 0, 124, 164,
				null);

		g.drawImage(getNumberImage(c.getHealth()),
				x + creatureImageSizeX - creatureNumberSizeX,
				y + creatureImageSizeY - creatureNumberSizeY,
				x + creatureImageSizeX,
				y + + creatureImageSizeY,
				0, 0, 124, 164,
				null);

		if(!c.isReady()) {
			g.drawImage(notReadyImage,
					x,
					y,
					x + creatureImageSizeX,
					y + creatureImageSizeY,
					0, 0, 512, 724,
					null);
		}

		if(c.isTaunt()) {
			g.drawImage(tauntImage,
					x,
					y,
					x + creatureImageSizeX,
					y + creatureImageSizeY,
					0, 0, 512, 724,
					null);
		}
	}

	public void drawActiveCreatures(Graphics g, ArrayList<Creature> creatures) {
		
		Creature c;

		for(int i = 0; i < creatures.size(); i++) {
			c = creatures.get(i);
			
			int x = creatureHorizontalGap + i * (creatureImageSizeX + creatureHorizontalGap);
			int y = fh - handImageSizeY - handCardVerticalGap - creatureImageSizeY - creatureVerticalGap;

			drawCreature(g, c, x, y);

		}
	}

	public void drawPassiveCreatures(Graphics g, ArrayList<Creature> creatures) {
		
		Creature c;

		for(int i = 0; i < creatures.size(); i++) {
			c = creatures.get(i);
			
			int x = creatureHorizontalGap + i * (creatureImageSizeX + creatureHorizontalGap);
			int y = handCardVerticalGap + handImageSizeY + creatureVerticalGap;

			drawCreature(g, c, x, y);
		}
	}
	
	BufferedImage getNumberImage(int number) {
		BufferedImage b = null;
		String path = "res\\num" + number + ".png";
		
		try {
			b = ImageIO.read(new File(path));
			
		} catch (IOException e) {
			System.out.println("krinsch");
		}
		return b;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

		mx = e.getX();
		my = e.getY();
		
		if(inspectedCard != null || inspectedCreature != null) {
			inspectedCard = null;
			inspectedCreature = null;
			frame.repaint();
			return;
		}

		enemyHasTaunt = false;			//tauntcheck
		for(Creature c : passiveCreatures){
			if(c.isTaunt()) enemyHasTaunt = true;
		}

		if(my <= fh - handCardVerticalGap && my >= fh - handCardVerticalGap - handImageSizeY &&  //hand clicked
				mx >= handImageSizeX && !selectingTarget) {
			int selectedHandCard = (mx - handImageSizeX) / (handImageSizeX + handCardHorizontalGap);
			//System.out.println("selctedHandCard: " + selectedHandCard);

			try {
				activeHand.get(selectedHandCard);
			}catch(IndexOutOfBoundsException xx) {
				selectedHandCard = -1;
				blankClicked();
			}

			if(e.getButton() == e.BUTTON3 && selectedHandCard != -1) {

				inspectedCard = activeHand.get(selectedHandCard);
				
			}else {
				if(selectedHandCard == this.selectedHandCard) this.selectedHandCard = -1;
				else if(selectedHandCard != -1) {
					this.selectedHandCard = selectedHandCard;
					selectedCreature = -1;
				}
			}



		}else if(my <= fh - handCardVerticalGap - handImageSizeY - creatureVerticalGap &&  //active creatures clicked
				my >= fh - handCardVerticalGap - handImageSizeY - creatureVerticalGap - creatureImageSizeY){
			int selectedCreature = mx / (creatureImageSizeX + creatureHorizontalGap);
			//System.out.println("selectedCreature: " + selectedCreature);

			try {
				activeCreatures.get(selectedCreature);
			}catch(IndexOutOfBoundsException xx) {
				selectedCreature = -1;
				blankClicked();
			}
			
			if(e.getButton() == e.BUTTON3 && selectedCreature != -1) {
				
				inspectedCreature = activeCreatures.get(selectedCreature);
				
			}else {
				
				if(selectedCreature == this.selectedCreature) this.selectedCreature = -1;
				else if(selectedCreature != -1) {
					if(selectingTarget) { //selecting target for spell
						selectingTargetFor.target(null, activeCreatures.get(selectedCreature));
						selectingTarget = false;
						selectingTargetFor = null;
					}else {
						this.selectedCreature = selectedCreature;
						selectedHandCard = -1;
						if(!activeCreatures.get(selectedCreature).isReady()) {
							this.selectedCreature = -1;
						}
					}
					
				}
				
			}


		}else if(my >= handCardVerticalGap + handImageSizeY + creatureVerticalGap && //passive creatures clicked
				my <= handCardVerticalGap + handImageSizeY + creatureVerticalGap + creatureImageSizeY){
			int selectedEnemyCreature = mx / (creatureImageSizeX + creatureHorizontalGap);
			//System.out.println("selectedEnemyCreature: " + selectedEnemyCreature);

			try {
				passiveCreatures.get(selectedEnemyCreature);
			}catch(IndexOutOfBoundsException xx) {
				selectedEnemyCreature = -1;
				//blankClicked();
			}

			if(selectedEnemyCreature != -1){
				if(e.getButton() == e.BUTTON3){
					inspectedCreature = passiveCreatures.get(selectedEnemyCreature);
				}else{
					Creature targetedCreature = passiveCreatures.get(selectedEnemyCreature);
					if(selectingTarget) { //selecting target for spell
						selectingTargetFor.target(null, targetedCreature);
						selectingTarget = false;
						selectingTargetFor = null;
					}else if((!enemyHasTaunt) || targetedCreature.isTaunt()){ //selecting target for creature to attack
						Creature attackingCreature = activeCreatures.get(selectedCreature);

						attackingCreature.attack(targetedCreature);
						selectedCreature = -1;
					}else blankClicked();
				}
			}else blankClicked();

			
		}else if(my <= handCardVerticalGap + handImageSizeY && my >= handCardVerticalGap && mx <= handImageSizeX) { //enemy character clicked
			System.out.println("Enemy Character clicked");
			if(selectedCreature != -1 && !enemyHasTaunt) { //selecting target for creature to attack
				activeCreatures.get(selectedCreature).attack(passivePlayer);
				selectedCreature = -1;
			}
			if(selectingTarget) { //selecting target for spell
				selectingTargetFor.target(passivePlayer, null);
				selectingTarget = false;
				selectingTargetFor = null;
			}
			
		}else if(my <= fh - handCardVerticalGap && my >= fh - handCardVerticalGap - handImageSizeY && mx <= handImageSizeX) { //character clicked
			System.out.println("Character clicked");
			if(selectingTarget) { //selecting target for spell
				selectingTargetFor.target(activePlayer, null);
				selectingTarget = false;
				selectingTargetFor = null;
			}
			
		}else if(my >= (fh/2) - (stoneSize/2) && my <= (fh/2) + (stoneSize/2) && mx <= stoneSize*2) { //end turn clicked
			System.out.println("end turn clicked");
			if(isTurnEnded) {
				nextTurn();
			}else {
				endTurn();
			}
			frame.repaint();
			

		}else { //nothing clicked
			blankClicked();
		}


		frame.repaint();
		System.out.println("mx: " + mx + "\nmy:" + my + "\nselectingTarget: " + selectingTarget + "\n");

	}

	public void blankClicked() {
		//System.out.println("blank clicked");
		if(this.selectedHandCard != -1) {
			Card c = activeHand.get(selectedHandCard);
			
			if(activePlayer.getActiveStones() >= c.getCost()) {
				activePlayer.setActiveStones(activePlayer.getActiveStones() - c.getCost());
				c.play(activePlayer, passivePlayer);
				if(c.isTargeted()) {
					selectingTarget = true;
					selectingTargetFor = c;
				}
				activeHand.remove(c);
			}
			
			selectedHandCard = -1;
			
			
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("end turn pressed");
		if(isTurnEnded) {
			nextTurn();
		}else {
			endTurn();
		}
		frame.repaint();
//		nextTurn();
//		frame.repaint();
//		System.out.println("key pressed");

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


}
