package controllers

import javax.inject.{ Inject, Singleton }

import application.service.PlaceService
import controllers.model.{ PlaceRequest, PlaceUpdateRequest }
import play.api.data.validation.Invalid
import play.api.libs.json.Json
import play.api.mvc.{ Action, Controller }

import scala.concurrent.{ ExecutionContext, Future }
import scalaz.EitherT
import scalaz.Scalaz._

/**
 * Created by Fumiyasu on 2016/11/24.
 */
@Singleton
class PlaceController @Inject() (
    placeService: PlaceService,
    implicit val ec: ExecutionContext
) extends Controller with ControllerBase {

  def register() = Action.async(parse.json) { request =>
    val read = request.body.validate[PlaceRequest].fold(
      invalid => Invalid("invalid request").left,
      valid => valid.right
    )

    val result = for {
      valid <- EitherT(Future.successful(read))
      id <- EitherT(placeService.register(valid))
    } yield Json.obj("placeId" -> id.toString)

    result.toResult
  }

  def update = Action.async(parse.json) { request =>
    val read = request.body.validate[PlaceUpdateRequest].fold(
      invalid => Invalid("invalid request").left,
      valid => valid.right
    )

    val result = for {
      valid <- EitherT(Future.successful(read))
      place <- placeService.update(valid)
    } yield place

    result.toResult
  }
}
