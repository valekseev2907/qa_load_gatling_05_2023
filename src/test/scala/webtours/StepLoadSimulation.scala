package webtours

import io.gatling.core.Predef._
import io.gatling.core.structure.PopulationBuilder
import TestSetup.{maxUsers, stepTestDuration}
import io.gatling.core.controller.inject.closed.ClosedInjectionStep
import webtours.config.httpProtocol
import webtours.scn.Scenario.scn

import scala.concurrent.duration.FiniteDuration

class StepLoadSimulation extends Simulation {

  // определение числа шагов теста и продолжительности каждого шага
  val numSteps: Int = 10
  val stepDuration: FiniteDuration = stepTestDuration / numSteps

  // генерация списка шагов теста
  val steps: Seq[ClosedInjectionStep] = (1 to numSteps).map { step =>
    val usersAtThisStep = maxUsers * step / numSteps
    constantConcurrentUsers(usersAtThisStep) during (stepDuration)
  }

  val stepLoadScenario: PopulationBuilder = scn.inject(steps)
    .protocols(httpProtocol)

  setUp(
    stepLoadScenario
  ).maxDuration(stepTestDuration)
}