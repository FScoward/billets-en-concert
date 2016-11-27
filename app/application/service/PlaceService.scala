package application.service

import java.sql.SQLIntegrityConstraintViolationException
import javax.inject.{ Inject, Singleton }

import controllers.model.{ PlaceRequest, PlaceUpdateRequest }
import domain.model.Place
import infrastructure.repository.PlaceRepository
import play.api.data.validation.Invalid
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.driver.JdbcProfile
import util.{ Id64, Logging }

import scala.concurrent.{ ExecutionContext, Future }
import scalaz.Scalaz._
import scalaz.{ EitherT, \/ }

/**
 * Created by Fumiyasu on 2016/11/24.
 */
@Singleton
class PlaceService @Inject() (
    placeRepository: PlaceRepository,
    val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] with Logging {
  def register(placeRequest: PlaceRequest)(implicit ec: ExecutionContext): Future[Invalid \/ Place] = {
    val place = Place(Id64.nextAscId(), placeRequest.name, placeRequest.address)
    db.run(placeRepository.register(place)).map(_ => place.right).recover {
      case e: SQLIntegrityConstraintViolationException => Invalid("duplicate").left
      case e => throw e
    }
  }

  def update(placeUpdateRequest: PlaceUpdateRequest)(implicit ec: ExecutionContext): EitherT[Future, Invalid, Place] = {
    for {
      place <- EitherT(db.run(placeRepository.findBy(placeUpdateRequest.id)).map(_ \/> Invalid("")))
      updated = place.update(placeUpdateRequest.name, placeUpdateRequest.address)
      _ <- EitherT(db.run(placeRepository.update(placeUpdateRequest.id, updated).map(_.right)))
    } yield updated
  }
}
