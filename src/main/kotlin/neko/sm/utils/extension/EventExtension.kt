package neko.sm.utils.extension

import today.opai.api.events.EventCancelable

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

fun EventCancelable.cancel() {
    this.isCancelled = true
}