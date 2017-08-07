package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature{

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;
    /** energy loss rate*/
    private double repEnergyRetain = 0.5;
    /** energy pass to child*/
    private double repEnergyPass = 0.5;
    /** move probability*/
    private double moveProb = 0.5;
    /** creates clorus with energy equal to E. */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /** creates a clorus with energy equal to 1. */
    public Clorus() {
        this(1);
    }

    /** Should return a color with red = 99, blue = 76, and green that varies
     *  linearly based on the energy of the Plip. If the plip has zero energy,
     *  it should have a green value of 63. If it has max energy, it should
     *  have a green value of 255. The green value should vary with energy
     *  linearly in between these two extremes. It's not absolutely vital
     *  that you get this exactly correct.
     */
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    /** Absorb C.energy(). */
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    /** Clorus should lose 0.03 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    public void move() {
        this.energy -= 0.03;
    }


    /** Clorus loss 0.01 energy when stay. */
    public void stay() {
        this.energy -= 0.01;
    }

    /** Clorus and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Clorus.
     */
    public Clorus replicate() {
        double childEnergy = this.energy * repEnergyPass;
        this.energy *= repEnergyRetain;
        return new Clorus(childEnergy);
    }

    /** Clorus take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY. (Even if there are Plips nearby they could attack)
     *  2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     *  3. Otherwise, if the Clorus has energy greater than or equal to 1, it will REPLICATE to a random empty square.
     *  4. Otherwise, the Clorus will MOVE to a random empty square.
     *
     *  Returns an object of type Action. See Action.java for the
     *  scoop on how Actions work. See SampleCreature.chooseAction()
     *  for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.size() == 0){
            return new Action(Action.ActionType.STAY);
        }
        List<Direction> PlipsDir = getNeighborsOfType(neighbors,"plip");
        if (PlipsDir.size() > 0){
            Direction AttackDir = HugLifeUtils.randomEntry(PlipsDir);
            return new Action(Action.ActionType.ATTACK,AttackDir);
        }
        if (this.energy() >= 1){
            Direction repDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE,repDir);
        }
        Direction MoveDir = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE,MoveDir);
    }
}
