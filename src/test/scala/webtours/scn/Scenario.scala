package webtours.scn

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import webtours.config.Feeders
import webtours.scn.Groups._

object Scenario {

  val scn: ScenarioBuilder = scenario("Web tours load test")
    .feed(Feeders.users)
    .exec(mainPageGroup)
    .exec(loginGroup)
    .exec(flightsGroup)
    .exec(makeOrderGroup)
    .exec(getHomeGroup)
}