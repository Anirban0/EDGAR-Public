package edgar.worker

/** Entry point that wires the Scala Kafka consumers (discovery, embed, price).
  * Consumer wiring lands with roadmap tasks 1.3 / 3.x / 7.x; this is the scaffold shell.
  */
object Main:
  def main(args: Array[String]): Unit =
    println(s"edgar worker (scala) — modules: ${edgar.ingest.Module.name}, " +
      s"${edgar.embed.Module.name}, ${edgar.price.Module.name}")
