package domain.model

import java.time.LocalDateTime

/**
 * Created by Fumiyasu on 2016/11/21.
 */
case class Live(liveId: Long, name: String, artistId: Long, startDateTime: LocalDateTime, endDateTime: LocalDateTime, placeId: Long)
