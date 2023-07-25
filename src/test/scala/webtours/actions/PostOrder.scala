package webtours.actions

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import webtours.config.Headers.headersRedirect

object PostOrder {

  val postOrder: HttpRequestBuilder = http("postOrder")
    .post("/cgi-bin/reservations.pl")
    .headers(headersRedirect)
    .formParam("advanceDiscount", "0")
    .formParam("depart", "#{departCity}")
    .formParam("departDate", "07/22/2023")
    .formParam("arrive", "#{arriveCity}")
    .formParam("returnDate", "07/23/2023")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "38")
    .formParam("findFlights.y", "7")
    .formParam(".cgifields", "roundtrip")
    .formParam(".cgifields", "seatType")
    .formParam(".cgifields", "seatPref")
    .check(css("input[name='outboundFlight']", "value").findAll.saveAs("outboundFlights"))
    .check(status is 200)

  val postTicket: HttpRequestBuilder = http("postTicket")
    .post("/cgi-bin/reservations.pl")
    .headers(headersRedirect)
    .formParam("outboundFlight", "#{outboundFlight}")
    .formParam("numPassengers", "1")
    .formParam("advanceDiscount", "0")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("reserveFlights.x", "44")
    .formParam("reserveFlights.y", "4")
    .check(status is 200)

  val postComplete: HttpRequestBuilder = http("postComplete")
    .post("/cgi-bin/reservations.pl")
    .headers(headersRedirect)
    .formParam("firstName", "#{username}")
    .formParam("lastName", "#{password}")
    .formParam("address1", "street")
    .formParam("address2", "city")
    .formParam("pass1", "qaload")
    .formParam("creditCard", "123456789")
    .formParam("expDate", "")
    .formParam("saveCC", "on")
    .formParam("oldCCOption", "on")
    .formParam("numPassengers", "1")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("outboundFlight", "#{outboundFlight}")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "54")
    .formParam("buyFlights.y", "8")
    .formParam(".cgifields", "saveCC")
    .check(status is 200)
}