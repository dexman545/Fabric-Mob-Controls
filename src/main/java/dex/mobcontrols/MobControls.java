package dex.mobcontrols;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.NameTagItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;
import org.aeonbits.owner.ConfigFactory;

import java.io.FileOutputStream;
import java.io.IOException;

public class MobControls implements ModInitializer {
	private boolean entityInList(Entity entity, String[] entityList) {
		boolean isIn = false;
		if (entityList == null) {
			return false;
		}
		for (String entityID : entityList) {
			isIn = isIn || Registry.ENTITY_TYPE.getId(entity.getType()).toString().equals(entityID.toLowerCase());
		}
		return isIn;
	}

	@Override
	public void onInitialize() {
		//configuration
		String config = FabricLoader.getInstance().getConfigDirectory().toString() + "/mobControls.cfg";
		ConfigFactory.setProperty("configDir", config);
		MobControlsConfig cfg = ConfigFactory.create(MobControlsConfig.class);

		//generate config file; removes incorrect values from existing one as well
		try {
			cfg.store(new FileOutputStream(config), "Mob Controls Configuration File");
		} catch (IOException e) {
			e.printStackTrace();
		}



		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			//check that player has a nametag in hand and get the name
			String tag = null;
			if (player.getStackInHand(hand).getItem() instanceof NameTagItem) {
				tag = player.getMainHandStack().getName().asFormattedString().toLowerCase();

			} else {
				return ActionResult.PASS;
			}

			//Logic
			if (entity instanceof LivingEntity) {
				switch(tag) {
					case "invulnerable":
						if (entityInList(entity, cfg.invulnerable())) {
							entity.setInvulnerable(!entity.isInvulnerable());
						}
						break;
					case "silent":
						if (entityInList(entity, cfg.silent())) {
							entity.setSilent(!entity.isSilent());
						}
						break;
					case "glowing":
						if (entityInList(entity, cfg.glowing())) {
							entity.setGlowing(!entity.isGlowing());
						}
						break;
					case "invisible":
						if (entityInList(entity, cfg.invisible())) {
							entity.setInvisible(!entity.isInvisible());
						}
						break;
					case "gravity":
						if (entityInList(entity, cfg.gravity())) {
							entity.setNoGravity(!entity.hasNoGravity());
						}
						break;
					case "ai":
						if (entityInList(entity, cfg.ai())) {
							if (entity instanceof MobEntity) {
								((MobEntity) entity).setAiDisabled(!((MobEntity) entity).isAiDisabled());
							}
						}
						break;
					/*case "displayname":
						if (entityInList(entity, cfg.displayname())) {
							//if (entity instanceof MobEntity) {
								entity.setCustomNameVisible(!entity.isCustomNameVisible());
							//}
						}*/
					case "pickuploot":
						if (entityInList(entity, cfg.pickuploot())) {
							if (entity instanceof MobEntity) {
								((MobEntity) entity).setCanPickUpLoot(!((MobEntity) entity).canPickUpLoot());
							}
						}
						break;
					case "lefthand":
						if (entityInList(entity, cfg.lefthand())) {
							if (entity instanceof MobEntity) {
								((MobEntity) entity).setLeftHanded(!((MobEntity) entity).isLeftHanded());
							}
						}
						break;
					case "noclip":
						if (entityInList(entity, cfg.noclip())) {
							entity.noClip = !entity.noClip;
						}
						break;
					default:
						break;
				}
/*

				//entity.setNoGravity(true);
				entity.setInvulnerable(true); //only effects noncreative players
				entity.setSilent(true);
				//entity.setGlowing(true);
				//entity.setInvisible(true);

				if (entity instanceof MobEntity) {
					((MobEntity) entity).setAiDisabled(true);
					entity.setCustomNameVisible(true);
					((MobEntity) entity).setCanPickUpLoot(false);
					((MobEntity) entity).setLeftHanded(true);
					((MobEntity) entity).setPersistent();
					entity.noClip = true;
					entity.setSwimming(true);
				}*/

			}

			return ActionResult.PASS;
		});
	}
}
