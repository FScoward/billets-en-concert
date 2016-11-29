package controllers.model

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created by Fumiyasu on 2016/11/23.
 */
case class LiveRequest(name: String, artistName: String, startTime: String, endTime: String, placeName: String)
object LiveRequest {
  implicit val reads = (
    (__ \ "name").read[String] ~
    (__ \ "artistName").read[String] ~
    (__ \ "startTime").read[String] ~
    (__ \ "endTime").read[String] ~
    (__ \ "placeName").read[String]
  )(LiveRequest.apply _)
}
