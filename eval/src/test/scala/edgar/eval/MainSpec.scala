package edgar.eval

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MainSpec extends AnyFlatSpec with Matchers:
  "eval harness" should "reach the rag + retrieval modules" in {
    edgar.rag.Module.name shouldBe "rag"
    edgar.retrieval.Module.name shouldBe "retrieval"
  }
