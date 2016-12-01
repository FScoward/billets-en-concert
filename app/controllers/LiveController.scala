package controllers

import javax.inject.Inject

import application.service.LiveService
import controllers.model.LiveRequest
import play.api.data.validation.Invalid
import play.api.mvc.{ Action, Controller }
import util.Logging

import scala.concurrent.{ ExecutionContext, Future }
import scalaz.EitherT
//import scalaz.syntax.std.all._
//import scalaz.syntax.std.ToEitherOps
import scalaz.Scalaz, Scalaz._

/**
 * Created by Fumiyasu on 2016/11/21.
 */
class LiveController @Inject() (
    liveService: LiveService,
    implicit val ec: ExecutionContext
) extends Controller with ControllerBase with Logging {
  def create() = Action.async(parse.json) { request =>
    val userName = request.session.get("userName")
    val result = for {
      valid <- EitherT(Future.successful(request.body.validate[LiveRequest].fold(invalid => Invalid(s"invalid request: ${invalid}").left, valid => valid.right)))
      _ <- EitherT(liveService.create(valid))
    } yield (valid)

    result.toResult
  }

  def find(liveId: Long) = Action { request =>

    Ok(s"${request.session}")
  }
}
