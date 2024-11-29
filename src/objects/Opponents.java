package objects;
public class Opponents extends SuperObject {
    public boolean inBattle = false; // Tracks if the opponent is currently being fought
    public boolean defeated = false; // Tracks if the opponent has been defeated

    public Opponents(String name) {
        this.name = name; // Example constructor for the opponent
    }
}
