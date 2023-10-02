package fr.nocsy.mcpets.listeners;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

import com.ticxo.modelengine.api.ModelEngineAPI;
import com.ticxo.modelengine.api.model.ModeledEntity;

public class VanillaDismountListener implements Listener {
	
	@EventHandler
	public void onDismount(EntityDismountEvent e) {
		var entity = e.getEntity();
		if (!(entity instanceof Player)) {
			return;
		}

		UUID petUUID = e.getDismounted().getUniqueId();

		ModeledEntity localModeledEntity = ModelEngineAPI.getModeledEntity(petUUID);
		if (localModeledEntity == null) {
			return;
		}
		localModeledEntity.getModels().forEach((s,m) -> m.getMountManager().ifPresent(mm -> {
			var driver = mm.getDriver();
			if (driver == null) {
				mm.dismountPassenger(entity);
				return;
			}
			if (driver.getUniqueId().equals(entity.getUniqueId())) {
				mm.dismountAll();
			} else {
				mm.dismountPassenger(entity);
			}
		}));

	}
}
