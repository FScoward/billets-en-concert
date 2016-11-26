package controllers

import javax.inject.{ Inject, Singleton }

import application.service.ArtistService
import controllers.model.{ ArtistRequest, ArtistResponse }
import play.api.data.validation.Invalid
import play.api.mvc.{ Action, Controller }

import scalaz.{ EitherT, Scalaz }
import Scalaz._
import scala.concurrent.{ ExecutionContext, Future }

/**
 * Created by Fumiyasu on 2016/11/23.
 */
@Singleton
class ArtistController @Inject() (
    artistService: ArtistService,
    implicit val ec: ExecutionContext
) extends Controller with ControllerBase {
  def register() = Action.async(parse.json) { implicit request =>
    val read = request.body.validate[ArtistRequest].fold(
      invalid => Invalid("invalid request").left,
      valid => valid.right
    )
    val result =
      for {
        valid <- EitherT(Future.successful(read))
        artist <- EitherT(artistService.register(valid))
      } yield (ArtistResponse(artist.id, artist.name))

    result.toResult
  }
}