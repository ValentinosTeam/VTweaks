package gg.valentinos.alexjoo.tweaks.swimming_animals;

import gg.valentinos.alexjoo.api.AbstractTweak;

import java.util.HashSet;
import java.util.Set;

public class SwimmingAnimalsTweak extends AbstractTweak {
    private final SwimmingAnimalsListener listener = new SwimmingAnimalsListener(this);
    private final Set<String> enabledAnimals = new HashSet<>();

    public SwimmingAnimalsTweak() {
        super("swimming-animals");
    }

    @Override
    public void onEnable() {
        // Load the animal list from config
        enabledAnimals.addAll(config.getStringList("animals"));

        listener.register();
        plugin.getLogger().info("SwimmingAnimalsTweak: " + enabledAnimals);
    }

    @Override
    public void onDisable() {
        listener.unregister();
        plugin.getLogger().info("SwimmingAnimalsTweak disabled");
    }

    public boolean isAnimalEnabled(String entityTypeName) {
        return enabledAnimals.contains(entityTypeName.toLowerCase());
    }

    public Set<String> getEnabledAnimals() {
        return enabledAnimals;
    }
}