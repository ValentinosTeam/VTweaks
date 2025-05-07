package gg.valentinos.alexjoo.tweaks.swimming_animals;

import gg.valentinos.alexjoo.api.AbstractTweakListener;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.bukkit.attribute.Attribute;


public class SwimmingAnimalsListener extends AbstractTweakListener {
    private final SwimmingAnimalsTweak tweak;

    public SwimmingAnimalsListener(SwimmingAnimalsTweak tweak) {
        super();
        this.tweak = tweak;
    }

    @EventHandler
    public void onVehicleInWater(PlayerMoveEvent event) {
        System.out.println();
        Entity vehicle = event.getPlayer().getVehicle();
        Player player = event.getPlayer();

        if (vehicle != null) {
            if (tweak.isAnimalEnabled(vehicle.getType().name()) && vehicle.isInWater()) {


                Vector direction = player.getLocation().getDirection(); // Get player direction (vector)
                direction.setY(0); // ignore y component
                direction.normalize(); // Nomralise to get unit vector

                // Get speed of the mounted animal
                double speed = 0.2; // Default value if anything fails
                if (vehicle instanceof LivingEntity living) {
                    AttributeInstance speedAttr = living.getAttribute(Attribute.MOVEMENT_SPEED);
                    if (speedAttr != null) {
                        speed = speedAttr.getValue();
                    }
                }

                // Apply horizontal velocity
                Vector motion = direction.multiply(speed);

                // Apply vertical velocity so that the vehicle floats to the surface
                motion.setY(0.01);

                // Apply the new vector
                vehicle.setVelocity(motion);
            }
        }
    }

    @Override
    protected void onRegister() {
        // Idk bro
    }

    @Override
    protected void onUnregister() {
        // Idk bro
    }
}
