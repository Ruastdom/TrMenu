package trplugins.menu.module.internal.hook.impl

import net.skinsrestorer.api.SkinsRestorer
import net.skinsrestorer.api.SkinsRestorerProvider
import trplugins.menu.module.internal.hook.HookAbstract

/**
 * @author Arasple
 * @date 2021/1/27 14:12
 */
class HookSkinsRestorer : HookAbstract() {

    private val skinsRestorer: SkinsRestorer? =
        if (isHooked) {
            runCatching { SkinsRestorerProvider.get() }.getOrNull()
        } else {
            null
        }
        get() {
            if (field == null) reportAbuse()
            return field
        }

    fun getPlayerSkinTexture(name: String): String? {
        skinsRestorer?.let {
            val skinData = it.skinStorage.findOrCreateSkinData(name)
            if (skinData.isPresent) {
                return skinData.get().property.value
            }
        }
        return null
    }

}