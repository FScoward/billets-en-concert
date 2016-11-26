package infrastructure.repository

import javax.inject.Singleton

import domain.model.Artist
import Tables._
import Tables.profile.api._

import scala.concurrent.ExecutionContext
/**
 * Created by Fumiyasu on 2016/11/23.
 */

class ArtistRepositoryJDBC {
  def register(artist: Artist)(implicit ec: ExecutionContext): DBIO[Int] = {
    Artists += ArtistsRow(artist.id, artist.name)
  }

  // TODO: limit offset
  def list(implicit ec: ExecutionContext): DBIO[Seq[Artist]] = {
    Artists
      .result
      .map(_.map(row => Artist(row.artistId, row.artistName)))
  }
}
