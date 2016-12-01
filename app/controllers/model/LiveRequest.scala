package controllers.model

import java.time.LocalDateTime

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created by Fumiyasu on 2016/11/23.
 */
case class LiveRequest(name: String, artistName: String, startTime: LocalDateTime, endTime: LocalDateTime, placeName: String)
object LiveRequest {
  implicit val formats = (
    (__ \ "name").format[String] ~
    (__ \ "artistName").format[String] ~
    (__ \ "start").format[LocalDateTime] ~
    (__ \ "end").format[LocalDateTime] ~
    (__ \ "placeName").format[String]
  )(LiveRequest.apply _, unlift(LiveRequest.unapply))
}
