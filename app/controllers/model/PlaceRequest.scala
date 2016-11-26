package controllers.model

import play.api.libs.json.Json
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created by Fumiyasu on 2016/11/24.
 */
case class PlaceRequest(name: String, address: String)
object PlaceRequest {
  implicit val reads = Json.reads[PlaceRequest]
}

case class PlaceUpdateRequest(id: Long, name: String, address: String)
object PlaceUpdateRequest {
  implicit val reads = (
    (__ \ "id").read[String].map(_.toLong) ~
    (__ \ "name").read[String] ~
    (__ \ "address").read[String]
  )(PlaceUpdateRequest.apply _)
}

