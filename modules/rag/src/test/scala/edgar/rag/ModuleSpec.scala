package edgar.rag

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ModuleSpec extends AnyFlatSpec with Matchers:
  "rag module" should "be wired into the build" in {
    Module.name shouldBe "rag"
  }
