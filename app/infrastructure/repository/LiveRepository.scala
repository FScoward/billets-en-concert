package infrastructure.repository

import java.sql.Timestamp
import javax.inject.Singleton

import domain.model.Live
import Tables._

import scalaz.Scalaz
import Scalaz._
import scala.concurrent.ExecutionContext
import Tables.profile.api._

/**
 * Created by Fumiyasu on 2016/11/21.
 */
class LiveRepository {
  def store(live: Live)(implicit ec: ExecutionContext): DBIO[Int] = {
    Lives += LivesRow(
      live.liveId,
      live.name,
      Timestamp.valueOf(live.startDateTime).some,
      Timestamp.valueOf(live.endDateTime).some,
      live.placeId
    )
  }
}
