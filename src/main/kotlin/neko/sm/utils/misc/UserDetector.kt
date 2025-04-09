package neko.sm.utils.misc

import today.opai.api.interfaces.game.entity.Player

/**
 * @author yuchenxue
 * @date 2025/03/05
 */

object UserDetector : Accessor {
    private val players = mutableListOf<UserPlayer>()

    fun getUserPlayer(player: Player): UserPlayer {
        players.find { player.name == it.name }?.let {
            return it
        }

        val userPlayer = UserPlayer.of(player)
        players.add(userPlayer)
        return userPlayer
    }

    class UserPlayer(player: Player) {
        companion object {
            fun of(player: Player): UserPlayer {
                return UserPlayer(player)
            }
        }

        val name: String = player.name
        val username: String = API.irc.getUsername(name).orElse("")

        private fun isUser(): Boolean {
            return username.isNotEmpty()
        }
    }
}