package webtours.config

object Headers {

  val headersMain: Map[String, String] = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
    "Accept-Encoding" -> "gzip, deflate",
    "Accept-Language" -> "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
    "Upgrade-Insecure-Requests" -> "1"
  )

  val headersRedirect: Map[String, String] = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
    "Accept-Encoding" -> "gzip, deflate",
    "Accept-Language" -> "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
    "Cache-Control" -> "max-age=0",
    "Origin" -> "http://webtours.load-test.ru:1080",
    "Upgrade-Insecure-Requests" -> "1"
  )
}