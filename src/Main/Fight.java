package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fight extends JFrame {
    private JLabel resultLabel;
    private JPanel cardPanel;
    private Card playerCard;
    private Card opponentCard;
    private int playerHealth = 20; // Points de vie du joueur
    private int opponentHealth = 20; // Points de vie de l'adversaire
    public boolean winSituation; // Indique le résultat : 1 pour victoire, 0 pour défaite
    private FightListener fightListener;

    public void setFightListener(FightListener listener) {
        this.fightListener = listener;
    }
    public Fight() {
        setTitle("Card Battle Game");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        resultLabel = new JLabel("Cliquez sur le bouton pour tirer des cartes !", SwingConstants.CENTER);
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(1, 2));
        

        // Initialiser la bataille en créant les boutons
        JButton battleButton = new JButton("Tirer des cartes");
        battleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battle();
            }
        });

        // Ajout des composants à la fenêtre
        add(resultLabel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        add(battleButton, BorderLayout.SOUTH);
    }

    private Card drawCard() {
        Card[] cards = {
                new Card("Dragon", 8),
                new Card("Knight", 5),
                new Card("Wizard", 7),
                new Card("Goblin", 3),
                new Card("Troll", 6)
        };
        return cards[(int) (Math.random() * cards.length)];
    }

    private void battle() {
        playerCard = drawCard();
        opponentCard = drawCard();

        String message = "Vous avez tiré : " + playerCard.getName() + " (Attaque : " + playerCard.getDamage() + ")\n" +
                "L'adversaire a tiré : " + opponentCard.getName() + " (Attaque : " + opponentCard.getDamage() + ")\n";

        // Calcul des dégâts
        opponentHealth -= playerCard.getDamage();
        playerHealth -= opponentCard.getDamage();

        // Affichage des points de vie
        message += "Points de vie - Vous : " + Math.max(playerHealth, 0) + ", Adversaire : " + Math.max(opponentHealth, 0) + "\n";

        // Vérification de la fin du jeu
        if (playerHealth <= 0 && opponentHealth <= 0) {
            winSituation = false; // Match nul traité comme une défaite
            if (fightListener != null) fightListener.onBattleResult(false);
            this.dispose();
        } else if (playerHealth <= 0) {
            winSituation = false; // Défaite
            if (fightListener != null) fightListener.onBattleResult(false);
            this.dispose();
        } else if (opponentHealth <= 0) {
            winSituation = true; // Victoire
            if (fightListener != null) fightListener.onBattleResult(true);
            this.dispose();
        }

        resultLabel.setText("<html>" + message.replaceAll("\n", "<br>") + "</html>");
        cardPanel.removeAll();
        cardPanel.add(createCardButton(playerCard));
        cardPanel.add(createCardButton(opponentCard));
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private JButton createCardButton(Card card) {
        JButton button = new JButton(card.getName());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDamage(card);
            }
        });
        return button;
    }

    private void showDamage(Card card) {
        resultLabel.setText(card.getName() + " inflige " + card.getDamage() + " dégâts !");
    }
    public interface FightListener {
        void onBattleResult(boolean victory);
    }


    class Card {
        private final String name;
        private final int damage;

        public Card(String name, int damage) {
            this.name = name;
            this.damage = damage;
        }

        public int getDamage() {
            return damage;
        }

        public String getName() {
            return name;
        }
    }

}