import sbt._

/** Centralised dependency coordinates and pinned versions. */
object Dependencies {
  object V {
    val scalaTest   = "3.2.19"
    val slick       = "3.5.2"
    val postgres    = "42.7.4"
    val pekko       = "1.1.3"
    val pekkoKafka  = "1.1.0"
    val playTest    = "3.0.6"
  }

  val scalaTest = "org.scalatest" %% "scalatest" % V.scalaTest

  val slick       = "com.typesafe.slick" %% "slick"          % V.slick
  val slickHikari = "com.typesafe.slick" %% "slick-hikaricp" % V.slick
  val postgres    = "org.postgresql"      % "postgresql"     % V.postgres

  val pekkoStream          = "org.apache.pekko" %% "pekko-stream"           % V.pekko
  val pekkoConnectorsKafka = "org.apache.pekko" %% "pekko-connectors-kafka" % V.pekkoKafka

  // Play test helpers (ScalaTest + Play) for the api module.
  // (Play's own `guice` helper is auto-imported by the sbt-play plugin.)
  val playScalaTest = "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1"
}
