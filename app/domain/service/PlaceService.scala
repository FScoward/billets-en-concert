package domain.service

import javax.inject.Inject

import domain.model.Place
import infrastructure.repository.PlaceRepository
import util.Id64
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext

/**
 * Created by Fumiyasu on 2016/12/01.
 */
class PlaceService @Inject() (placeRepository: PlaceRepository) {
  def find(placeName: String)(implicit ec: ExecutionContext): DBIO[Place] = {
    for {
      place <- placeRepository.findBy(placeName)
      p <- place.fold {
        val newPlace = Place(Id64.nextAscId(), placeName, "")
        placeRepository.register(newPlace).map(_ => newPlace)
      }(p => DBIO.successful(p))
    } yield p
  }
}
