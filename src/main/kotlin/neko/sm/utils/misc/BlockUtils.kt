package neko.sm.utils.misc

import neko.sm.utils.extension.plus
import today.opai.api.dataset.BlockPosition

/**
 * @author yuchenxue
 * @date 2025/03/09
 */

object BlockUtils : Accessor {

    fun findBlock(name: String, range: Int): List<BlockPosition> {
        val position = API.localPlayer.position

        val pos = BlockPosition(position.x.toInt(), position.y.toInt(), position.z.toInt())

        val list = mutableListOf<BlockPosition>()

        for (x in -range..range) {
            for (y in -range..range) {
                for (z in -range..range) {
                    val new = pos + BlockPosition(x, y, z)
                    val block = API.world.getBlockFromPosition(new)
                    if (block.name.contains(name, true)) {
                        API.printMessage(block.toString())
                        list.add(new)
                    }
                }
            }
        }

        return list
    }
}