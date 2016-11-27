package domain.service

import java.time.LocalDateTime

import controllers.model.LiveRequest
import domain.model.{ Artist, Live }
import util.Id64

/**
 * Created by Fumiyasu on 2016/11/27.
 */
class LiveService {

  def create(liveRequest: LiveRequest, place: Option[Long], artist: Option[Artist]) = {
    val placeId = place match {
      case None => Id64.nextAscId()
      case _ => place.get
    }
    val artistModel = artist match {
      case None => Artist(Id64.nextAscId(), liveRequest.artistName.getOrElse("unknown"))
      case Some(a) => a
    }

    Live(
      Id64.nextAscId(),
      liveRequest.name,
      artistModel.id,
      LocalDateTime.parse(new ArrayCharSequence(liveRequest.startTime.toCharArray)),
      LocalDateTime.parse(new ArrayCharSequence(liveRequest.endTime.toCharArray)),
      placeId
    )
  }
}
