package controllers.model

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created by Fumiyasu on 2016/11/23.
 */
case class LiveRequest(name: String, artistId: Option[Long], artistName: Option[String], startTime: String, endTime: String, placeId: Option[Long], placeName: Option[String])
object LiveRequest {
  implicit val reads = (
    (__ \ "name").read[String] ~
    (__ \ "artistId").readNullable[String].map(_.map(_.toLong)) ~
    (__ \ "artistName").readNullable[String] ~
    (__ \ "startTime").read[String] ~
    (__ \ "endTime").read[String] ~
    (__ \ "placeId").readNullable[String].map(_.map(_.toLong)) ~
    (__ \ "placeName").readNullable[String]
  )(LiveRequest.apply _)
}
