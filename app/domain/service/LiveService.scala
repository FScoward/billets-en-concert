package domain.service

import java.time.LocalDateTime

import controllers.model.LiveRequest
import domain.model.{ Artist, Live, Place }
import util.Id64

/**
 * Created by Fumiyasu on 2016/11/27.
 */
class LiveService {

  def create(liveRequest: LiveRequest, placeId: Long, artistId: Long) = {
    Live(
      Id64.nextAscId(),
      liveRequest.name,
      artistId,
      liveRequest.startTime,
      liveRequest.endTime,
      //      LocalDateTime.parse(new ArrayCharSequence(liveRequest.startTime.toCharArray)),
      //      LocalDateTime.parse(new ArrayCharSequence(liveRequest.endTime.toCharArray)),
      placeId
    )
  }
}
