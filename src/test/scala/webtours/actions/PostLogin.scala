package webtours.actions

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import webtours.config.Headers.{headersRedirect, headersMain}

object PostLogin {

  val postLoginPl: HttpRequestBuilder = http("postLoginPl")
    .post("/cgi-bin/login.pl")
    .headers(headersRedirect)
    .formParam("userSession", "#{userSession}")
    .formParam("username", "#{username}")
    .formParam("password", "#{password}")
    .formParam("login.x", "60")
    .formParam("login.y", "7")
    .formParam("JSFormSubmit", "off")
    .check(status is 200)
    .check(regex("User password was correct").exists)

  val getLoginPl: HttpRequestBuilder = http("getLoginPl")
    .get("/cgi-bin/login.pl")
    .headers(headersMain)
    .queryParam("intro", "true")
    .check(status is 200)

  val getNavPl: HttpRequestBuilder = http("getNavPl")
    .get("/cgi-bin/nav.pl")
    .headers(headersMain)
    .queryParam("page", "menu")
    .queryParam("in", "home")
    .check(status is 200)
}