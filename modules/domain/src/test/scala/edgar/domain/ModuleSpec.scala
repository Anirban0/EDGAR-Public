package edgar.domain

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ModuleSpec extends AnyFlatSpec with Matchers:
  "domain module" should "be wired into the build" in {
    Module.name shouldBe "domain"
  }
