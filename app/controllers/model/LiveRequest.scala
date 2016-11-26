package controllers.model

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created by Fumiyasu on 2016/11/23.
 */
case class LiveRequest(name: String, artistId: Long, date: String, startTime: String, endTime: String, placeId: Long)
object LiveRequest {
  implicit val reads = (
    (__ \ "name").read[String] ~
    (__ \ "artistId").read[String].map(_.toLong) ~
    (__ \ "date").read[String] ~
    (__ \ "startTime").read[String] ~
    (__ \ "endTime").read[String] ~
    (__ \ "placeId").read[String].map(_.toLong)
  )(LiveRequest.apply _)
}
