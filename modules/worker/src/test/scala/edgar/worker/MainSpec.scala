package edgar.worker

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MainSpec extends AnyFlatSpec with Matchers:
  "worker" should "depend on the ingest/embed/price modules" in {
    Seq(edgar.ingest.Module.name, edgar.embed.Module.name, edgar.price.Module.name) should
      contain allOf ("ingest", "embed", "price")
  }
