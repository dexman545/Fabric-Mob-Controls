package dex.mobcontrols;

import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Reloadable;

@Config.HotReload(type = Config.HotReloadType.ASYNC) //set value = X for interval of X seconds. Default: 5
@Config.Sources({"file:${configDir}"})
public interface MobControlsConfig extends Config, Reloadable, Accessible {
    @Separator(",")
    @DefaultValue("minecraft:cow, minecraft:pig, minecraft:sheep, minecraft:chicken")
    String[] invulnerable();

    @Separator(",")
    @DefaultValue("minecraft:cow, minecraft:pig, minecraft:sheep, minecraft:chicken")
    String[] silent();

    @Separator(",")
    @DefaultValue("minecraft:cow, minecraft:pig, minecraft:sheep, minecraft:chicken")
    String[] glowing();

    @Separator(",")
    @DefaultValue("minecraft:cow, minecraft:pig, minecraft:sheep, minecraft:chicken")
    String[] invisible();

    @Separator(",")
    @DefaultValue("minecraft:cow, minecraft:pig, minecraft:sheep, minecraft:chicken")
    String[] gravity();

    @Separator(",")
    @DefaultValue("minecraft:cow, minecraft:pig, minecraft:sheep, minecraft:chicken")
    String[] ai();

    /*@Separator(",")
    @DefaultValue("minecraft:cow, minecraft:pig, minecraft:sheep, minecraft:chicken")
    String[] displayname();*/

    @Separator(",")
    @DefaultValue("minecraft:cow, minecraft:pig, minecraft:sheep, minecraft:chicken")
    String[] pickuploot();

    @Separator(",")
    @DefaultValue("minecraft:cow, minecraft:pig, minecraft:sheep, minecraft:chicken")
    String[] lefthand();

    @Separator(",")
    @DefaultValue("minecraft:cow, minecraft:pig, minecraft:sheep, minecraft:chicken")
    String[] noclip();
}
