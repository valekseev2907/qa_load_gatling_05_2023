package webtours

import scala.concurrent.duration._

object TestSetup {

  val maxUsers = 105 // максимальное количество пользователей
  val stepTestDuration: FiniteDuration = 20 minutes // длительность ступенчатого теста
  val reliabilityTestDuration: FiniteDuration = 60 minutes // длительность теста надежности
}
