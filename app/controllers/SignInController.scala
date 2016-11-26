package controllers

import javax.inject.Inject

import play.api.cache.CacheApi
import play.api.mvc.{ Action, Controller }
import twitter4j.TwitterFactory

/**
 * Created by Fumiyasu on 2016/11/21.
 */
class SignInController @Inject() (cacheApi: CacheApi) extends Controller {

  def signin = Action {
    //    val requestToken = twitter.getOAuthRequestToken
    val twitter = TwitterFactory.getSingleton
    twitter.setOAuthAccessToken(null)
    val url = twitter.getOAuthRequestToken.getAuthenticationURL
    //    val url = twitter.getOAuthRequestToken.getAuthorizationURL

    Redirect(url).withNewSession
  }

  def callback = Action { request =>
    val oauthToken = request.getQueryString("oauth_token")
    val oauthVerifier = request.getQueryString("oauth_verifier")

    oauthVerifier match {
      case Some(verifier) =>
        val twitter = TwitterFactory.getSingleton
        val accessToken = twitter.getOAuthAccessToken(verifier)
        Ok(s"access token: $accessToken").withSession("userName" -> s"${accessToken.getScreenName}")
      case None =>
        Ok(s"$oauthToken, $oauthVerifier")
    }

  }
}
