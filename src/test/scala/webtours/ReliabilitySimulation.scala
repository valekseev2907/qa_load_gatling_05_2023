package webtours

import io.gatling.core.Predef._
import io.gatling.core.structure.PopulationBuilder
import TestSetup.{maxUsers, reliabilityTestDuration}
import webtours.config.httpProtocol
import webtours.scn.Scenario.scn
import scala.concurrent.duration._

import scala.concurrent.duration.FiniteDuration

class ReliabilitySimulation extends Simulation {

  val rampUpDuration: FiniteDuration = 5 minutes // время разгона до 80% профиля

  val reliabilityScenario: PopulationBuilder = scn.inject(
    rampConcurrentUsers(0) to ((maxUsers * 0.8).toInt) during (rampUpDuration), // разгон до 80% профиля
    constantConcurrentUsers((maxUsers * 0.8).toInt) during (reliabilityTestDuration - rampUpDuration) // удержание нагрузки
  ).protocols(httpProtocol)

  setUp(reliabilityScenario)
    .maxDuration(reliabilityTestDuration)
}