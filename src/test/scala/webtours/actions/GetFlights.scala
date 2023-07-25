package webtours.actions

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import webtours.config.Headers.headersMain

object GetFlights {

  val getMainPageWelcome: HttpRequestBuilder = http("getMainPageWelcome")
    .get("/cgi-bin/welcome.pl")
    .headers(headersMain)
    .queryParam("page", "search")
    .check(status is 200)

  val getMainPageNav: HttpRequestBuilder = http("getMainPageNav")
    .get("/cgi-bin/nav.pl")
    .headers(headersMain)
    .queryParam("page", "menu")
    .queryParam("in", "flights")
    .check(status is 200)

val getReservations: HttpRequestBuilder = http("getReservations")
    .get("/cgi-bin/reservations.pl")
    .headers(headersMain)
    .queryParam("page", "welcome")
    .check(
      css("select[name='depart'] option", "value").findAll.saveAs("departCities"),
      css("select[name='arrive'] option", "value").findAll.saveAs("arriveCities")
    )
    .check(status is 200)
}