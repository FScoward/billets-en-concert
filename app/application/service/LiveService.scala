package application.service

import java.time.LocalDateTime
import javax.inject.Inject

import controllers.model.LiveRequest
import domain.model.{ Live, Place }
import infrastructure.repository.LiveRepository
import play.api.data.validation.Invalid
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.driver.JdbcProfile
import util.{ Id64, Logging }

import scalaz.Scalaz
import Scalaz._
import scala.concurrent.ExecutionContext

/**
 * Created by Fumiyasu on 2016/11/21.
 */
class LiveService @Inject() (
    liveRepository: LiveRepository,
    val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] with Logging {
  def create(liveRequest: LiveRequest)(implicit ec: ExecutionContext) = {
    // TODO: validate start - end
    // TODO: live held date >= today
    logger.debug(s"=================> create: $liveRequest")
    val live = Live(Id64.nextAscId(), liveRequest.name, liveRequest.artistId, LocalDateTime.parse(new ArrayCharSequence(liveRequest.startTime.toCharArray)), LocalDateTime.parse(new ArrayCharSequence(liveRequest.endTime.toCharArray)), liveRequest.placeId)
    for {
      _ <- db.run(liveRepository.store(live)).map(_.right)
    } yield live.right[Invalid]
  }
}
