/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.battlefield.actors;

import java.awt.Color;
import java.util.List;
import model.battlefield.army.ArmyManager;
import model.builders.actors.ActorBuilder;
import tools.LogUtil;

/**
 *
 * @author Benoît
 */
public class ParticleActor extends Actor {
    public enum Facing{Horizontal, Velocity, Camera}
    
    public final String spritePath;
    public final int nbCol;
    public final int nbRow;
    public final String emissionBone;
    public final String directionBone;
    
    public final double velocity;
    public final double fanning;
    public final boolean randomSprite;
    public final int maxCount;
    public final int perSecond;
    public final double duration;
    public final double startSize;
    public final double endSize;
    public final Color startColor;
    public final Color endColor;
    public final double minLife;
    public final double maxLife;
    public final double rotationSpeed;
    public final boolean gravity;
    public final Facing facing;
    public final boolean add;
    public final double startVariation;
    
    public boolean launched = false;
    public long startTime = 0;

    public ParticleActor(String spritePath,
            int nbCol,
            int nbRow,
            String emissionBone,
            String directionBone,
            double velocity,
            double fanning,
            boolean randomSprite,
            int maxCount,
            int perSecond,
            double duration,
            double startSize,
            double endSize,
            Color startColor,
            Color endColor,
            double minLife,
            double maxLife,
            double rotationSpeed,
            boolean gravity,
            Facing facing,
            boolean add,
            double startVariation,
            Actor parent,
            String trigger,
            List<String> childrenTriggers,            
            List<ActorBuilder> childrenBuilders,
            ArmyManager armyManager) {
        super(parent, trigger, childrenTriggers, childrenBuilders, armyManager);
        this.spritePath = spritePath;
        this.nbCol = nbCol;
        this.nbRow = nbRow;
        this.emissionBone = emissionBone;
        this.directionBone = directionBone;
        this.velocity = velocity;
        this.fanning = fanning;
        this.randomSprite = randomSprite;
        this.maxCount = maxCount;
        this.perSecond = perSecond;
        this.duration = duration;
        this.startSize = startSize;
        this.endSize = endSize;
        this.startColor = startColor;
        this.endColor = endColor;
        this.minLife = minLife;
        this.maxLife = maxLife;
        this.rotationSpeed = rotationSpeed;
        this.gravity = gravity;
        this.facing = facing;
        this.add = add;
        this.startVariation = startVariation;
    }

    public ModelActor getParentModelActor(){
        Actor parent = this;
        do {
            parent = parent.getParent();
            if(parent == null)
                throw new RuntimeException(this.getClass().getSimpleName()+" seems to miss a modelActor parent");
            
        } while(!parent.containsModel());
        return (ModelActor)parent;
    }

    @Override
    protected void act() {
        launched = false;
        startTime = 0;
        super.act();
    }
    
    public void updateDuration(){
        if(duration == 0)
            interrupt();
        else if(startTime == 0)
            startTime = System.currentTimeMillis();
        else if(duration != Double.MAX_VALUE && startTime+duration < System.currentTimeMillis())
            interrupt();
    }

    @Override
    public String getType() {
        return "particle";
    }
    
    
}
