package webtours.actions

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import webtours.config.Headers.headersMain

object GetMainPage {

  val getMainPageWelcome: HttpRequestBuilder = http("getMainPageWelcome")
    .get("/cgi-bin/welcome.pl")
    .headers(headersMain)
    .queryParam("signOff", "true")
    .check(status is 200)

  val getMainPageNav: HttpRequestBuilder = http("getMainPageNav")
    .get("/cgi-bin/nav.pl")
    .headers(headersMain)
    .queryParam("in", "home")
    .check(css("input[name='userSession']", "value").saveAs("userSession"))
    .check(status is 200)
}