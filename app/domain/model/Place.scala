package domain.model

import play.api.libs.json.Json

/**
 * Created by Fumiyasu on 2016/11/21.
 */
case class Place(name: String, address: String) {
  def update(name: String, address: String) = this.copy(name = name, address = address)
}

object Place {
  implicit val writes = Json.writes[Place]
}
