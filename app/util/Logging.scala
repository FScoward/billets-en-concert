package util

import play.api.Logger

/**
 * Created by Fumiyasu on 2016/11/23.
 */
trait Logging {
  val logger = Logger.apply(this.getClass)
}
