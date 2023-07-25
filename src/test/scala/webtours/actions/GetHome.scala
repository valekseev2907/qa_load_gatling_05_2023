package webtours.actions

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import webtours.config.Headers.headersMain

object GetHome {

  val getHome: HttpRequestBuilder = http("getHome")
    .get("/cgi-bin/welcome.pl")
    .headers(headersMain)
    .queryParam("page", "menus")
    .check(status is 200)

  val getNavPl: HttpRequestBuilder = http("getNavPl")
    .get("/cgi-bin/nav.pl")
    .headers(headersMain)
    .queryParam("page", "menu")
    .queryParam("in", "home")
    .check(status is 200)

  val getLoginPl: HttpRequestBuilder = http("getLoginPl")
    .get("/cgi-bin/login.pl")
    .headers(headersMain)
    .queryParam("intro", "true")
    .check(status is 200)
}