package webtours.scn

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import webtours.actions._

import scala.util.Random

object Groups {

  val mainPageGroup: ChainBuilder = group("get main page") {
    exec(GetMainPage.getMainPageWelcome)
      .exec(GetMainPage.getMainPageNav)

  }

  val loginGroup: ChainBuilder = group("login") {
    exec(PostLogin.postLoginPl)
      .exec(PostLogin.getNavPl)
      .exec(PostLogin.getLoginPl)
  }

  val flightsGroup: ChainBuilder = group("get flights") {
    exec(GetFlights.getMainPageWelcome)
      .exec(GetFlights.getMainPageNav)
      .exec(GetFlights.getReservations)
      .exec { session =>
        val departCities = session("departCities").as[Seq[String]]
        val arriveCities = session("arriveCities").as[Seq[String]]

        var randomDepartCity = ""
        var randomArriveCity = ""

        val random = new Random()
        do {
          randomDepartCity = departCities(random.nextInt(departCities.size))
          randomArriveCity = arriveCities(random.nextInt(arriveCities.size))
        } while (randomDepartCity == randomArriveCity)

        session
          .set("departCity", randomDepartCity)
          .set("arriveCity", randomArriveCity)
      }
  }

  val makeOrderGroup: ChainBuilder = group("make order") {
    exec(PostOrder.postOrder)
      .exec { session =>
        val outboundFlights = session("outboundFlights").as[Seq[String]]

        val random = new Random()
        val randomOutboundFlight = outboundFlights(random.nextInt(outboundFlights.size))

        session
          .set("outboundFlight", randomOutboundFlight)
      }
      .exec(PostOrder.postTicket)
      .exec(PostOrder.postComplete)
  }

  val getHomeGroup: ChainBuilder = group("click home") {
    exec(GetHome.getHome)
      .exec(GetHome.getNavPl)
      .exec(GetHome.getLoginPl)
  }
}