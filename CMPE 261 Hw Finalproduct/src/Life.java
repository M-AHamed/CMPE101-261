public class Life {
    // life class holds the life of the player and all the methods to increase and decrease the life of the player

    private int life = 10; // life of player = 10 at start of game

    public int getLife() {
        return life;
    }

    public Life(){}

    public void ballClickedLifeIncrement(){
    life = life + 1; // incrases the life by a set interval if ball was clikced on
        System.out.println(life + " because you clicked ball");

    }
    public void dropletClickedLifeIncrement(){
    life = life +3;
        System.out.println(life  + " because you clicked droplet");

    }
    public void dropletHitFloorDecrement(){
        life = life - 3;
        System.out.println(life + " droplet hit the floor brother watch out");
    }
    public void ballHitDropletDecrement(){
        life = life -5;
    }
}
