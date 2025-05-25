package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.server.SPacket3ETeams

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketTeams(
    packet: SPacket3ETeams
) : Packet<SPacket3ETeams>(packet), SPacket3ETeams {
    override fun getName(): String {
        return packet.name
    }

    override fun getDisplayName(): String {
        return packet.displayName
    }

    override fun getPrefix(): String {
        return packet.prefix
    }

    override fun getSuffix(): String {
        return packet.suffix
    }

    override fun getNametagVisibility(): String {
        return packet.nametagVisibility
    }

    override fun getPlayerList(): MutableCollection<String> {
        return packet.playerList
    }

    override fun getAction(): Int {
        return packet.action
    }

    override fun getFriendlyFlags(): Int {
        return packet.friendlyFlags
    }

    override fun getColor(): Int {
        return packet.color
    }

    override fun setName(name: String) {
        packet.name = name
    }

    override fun setDisplayName(displayName: String) {
        packet.displayName = displayName
    }

    override fun setPrefix(prefix: String) {
        packet.prefix = prefix
    }

    override fun setSuffix(suffix: String) {
        packet.suffix = suffix
    }

    override fun setNametagVisibility(nametagVisibility: String) {
        packet.nametagVisibility = nametagVisibility
    }

    override fun setPlayerList(playerList: MutableCollection<String>) {
        packet.playerList = playerList
    }

    override fun setAction(action: Int) {
        packet.action = action
    }

    override fun setFriendlyFlags(flags: Int) {
        packet.friendlyFlags = flags
    }

    override fun setColor(color: Int) {
        packet.color = color
    }
}