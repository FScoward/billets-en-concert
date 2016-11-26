package application.service

import java.sql.SQLIntegrityConstraintViolationException
import javax.inject.{ Inject, Singleton }

import controllers.model.{ ArtistRequest, ArtistResponse }
import domain.model.Artist
import infrastructure.repository.ArtistRepositoryJDBC
import play.api.data.validation.Invalid
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.driver.JdbcProfile
import util.Id64

import scala.concurrent.{ ExecutionContext, Future }
import scalaz.{ Scalaz, \/ }
import Scalaz._

/**
 * Created by Fumiyasu on 2016/11/23.
 */
@Singleton
class ArtistService @Inject() (
    artistRepositoryJDBC: ArtistRepositoryJDBC,
    val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {
  def register(artistReq: ArtistRequest)(implicit ec: ExecutionContext): Future[Invalid \/ Artist] = {
    val artist = Artist(Id64.nextAscId(), artistReq.name)
    db.run(artistRepositoryJDBC.register(artist)).map(_ => artist.right).recover {
      case e: SQLIntegrityConstraintViolationException => Invalid("duplicate").left
      case e => throw e
    }
  }

  def list()(implicit ec: ExecutionContext) = {
    // TODO responseへの変換はcontroller ?
    db.run(artistRepositoryJDBC.list).map(_.map(a => ArtistResponse(a.id.toString, a.name)).right[Invalid])
  }
}
