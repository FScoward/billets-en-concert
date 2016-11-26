package controllers.model

import play.api.libs.json.Json

/**
 * Created by Fumiyasu on 2016/11/23.
 */
case class ArtistRequest(name: String)
case class ArtistResponse(id: String, name: String)

object ArtistRequest {
  implicit val reads = Json.reads[ArtistRequest]
}
object ArtistResponse {
  implicit val writes = Json.writes[ArtistResponse]
}