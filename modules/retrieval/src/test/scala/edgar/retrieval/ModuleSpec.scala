package edgar.retrieval

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ModuleSpec extends AnyFlatSpec with Matchers:
  "retrieval module" should "be wired into the build" in {
    Module.name shouldBe "retrieval"
  }
