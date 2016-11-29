package infrastructure.repository

import Tables._
import domain.model.Place
import slick.dbio.DBIO
import util.Id64
import Tables.profile.api._

import scala.concurrent.ExecutionContext

/**
 * Created by Fumiyasu on 2016/11/24.
 */
class PlaceRepository {
  def register(place: Place)(implicit ec: ExecutionContext): DBIO[Int] = {
    Places += PlacesRow(place.id, place.name, place.address)
  }

  def update(id: Long, place: Place)(implicit ec: ExecutionContext): DBIO[Int] = {
    Places.filter(_.placeId === id.bind)
      .map(row => (row.name, row.address))
      .update((place.name, place.address))
  }

  def findBy(placeId: Long)(implicit ec: ExecutionContext): DBIO[Option[Place]] = {
    Places
      .filter(_.placeId === placeId.bind)
      .result
      .headOption
      .map(_.map(p => Place(p.placeId, p.name, p.address)))
  }

  def findBy(placeName: String)(implicit ec: ExecutionContext): DBIO[Option[Place]] = {
    Places
      .filter(_.name === placeName.bind)
      .result
      .headOption
      .map(_.map(p => Place(p.placeId, p.name, p.address)))
  }

}
