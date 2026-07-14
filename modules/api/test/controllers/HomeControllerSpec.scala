package controllers

import org.scalatestplus.play.*
import org.scalatestplus.play.guice.*
import play.api.test.*
import play.api.test.Helpers.*

class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting:

  "HomeController" should {

    "return the service banner on GET /" in {
      val home = route(app, FakeRequest(GET, "/")).get
      status(home) mustBe OK
      contentAsString(home) must include("EDGAR Intelligence Platform")
    }

    "report healthy on GET /health" in {
      val health = route(app, FakeRequest(GET, "/health")).get
      status(health) mustBe OK
      contentType(health) mustBe Some("application/json")
      contentAsString(health) must include("\"status\":\"ok\"")
    }
  }
