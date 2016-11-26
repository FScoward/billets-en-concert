package controllers

import play.api.data.validation.Invalid
import play.api.libs.json.{ Json, Writes }
import play.api.mvc.{ Result, Results }

import scalaz.EitherT

/**
 * Created by Fumiyasu on 2016/11/23.
 */
trait ControllerBase {
  implicit class EitherT2Result[F[_], A, B](eitherT: EitherT[F, Invalid, B]) {
    def toResult(implicit F: scalaz.Functor[F], writes: Writes[B]): F[Result] = eitherT.fold(
      left => Results.BadRequest(Json.obj("message" -> left.errors.map(_.message).mkString(","))),
      right => Results.Ok(Json.toJson(right))
    )
  }

}
