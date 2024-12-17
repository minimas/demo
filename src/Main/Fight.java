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
    private int playerHealth = 20; // Player's health points
    private int opponentHealth = 20; // Opponent's health points
    public boolean winSituation; // Indicates the result: true for victory, false for defeat
    private FightListener fightListener;
    public void setFightListener(FightListener listener) {
        this.fightListener = listener;
    }
    public Fight() {
        setTitle("Card Battle Game");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        resultLabel = new JLabel("Choose a card to battle!", SwingConstants.CENTER);
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(1, 5));
        // Add card selection buttons for the player
        Card[] cards = {
                new Card("Dragon", 8),
                new Card("Knight", 5),
                new Card("Wizard", 7),
                new Card("Goblin", 3),
                new Card("Troll", 6)
        };
        for (Card card : cards) {
            JButton cardButton = new JButton(card.getName());
            cardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerCard = card;
                    battle();
                }
            });
            cardPanel.add(cardButton);
        }
        // Add components to the window
        add(resultLabel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }
    private Card drawOpponentCard() {
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
        opponentCard = drawOpponentCard();
        String message = "You chose: " + playerCard.getName() + " (Attack: " + playerCard.getDamage() + ")\n" +
                "Opponent chose: " + opponentCard.getName() + " (Attack: " + opponentCard.getDamage() + ")\n";
        // Calculate damage
        opponentHealth -= playerCard.getDamage();
        playerHealth -= opponentCard.getDamage();
        // Display health points
        message += "Health Points - You: " + Math.max(playerHealth, 0) + ", Opponent: " + Math.max(opponentHealth, 0) + "\n";
        // Check for game end
        if (playerHealth <= 0 && opponentHealth <= 0) {
            winSituation = false; // Draw treated as a defeat
            if (fightListener != null) fightListener.onBattleResult(false);
            this.dispose();
        } else if (playerHealth <= 0) {
            winSituation = false; // Defeat
            if (fightListener != null) fightListener.onBattleResult(false);
            this.dispose();
        } else if (opponentHealth <= 0) {
            winSituation = true; // Victory
            if (fightListener != null) fightListener.onBattleResult(true);
            this.dispose();
        }
        resultLabel.setText("<html>" + message.replaceAll("\n", "<br>") + "</html>");
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