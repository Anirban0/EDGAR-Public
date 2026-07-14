import Dependencies._

ThisBuild / scalaVersion := "3.3.4"
ThisBuild / organization := "com.edgar"
ThisBuild / version      := "0.1.0-SNAPSHOT"

// Shared settings for every plain (non-Play) module.
lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Wunused:all"
  ),
  libraryDependencies += scalaTest % Test,
  Test / fork := true
)

// domain — case classes, IDs, errors. Zero external deps.
lazy val domain = (project in file("modules/domain"))
  .settings(commonSettings)
  .settings(name := "edgar-domain")

// store — Slick tables + repositories; raw SQL for pgvector/FTS.
lazy val store = (project in file("modules/store"))
  .dependsOn(domain)
  .settings(commonSettings)
  .settings(
    name := "edgar-store",
    libraryDependencies ++= Seq(slick, slickHikari, postgres)
  )

// ingest — EDGAR feed discovery (Pekko Streams graph + Kafka publish).
lazy val ingest = (project in file("modules/ingest"))
  .dependsOn(store)
  .settings(commonSettings)
  .settings(
    name := "edgar-ingest",
    libraryDependencies ++= Seq(pekkoStream, pekkoConnectorsKafka)
  )

// embed — chunker, embedding client, pgvector indexer.
lazy val embed = (project in file("modules/embed"))
  .dependsOn(store)
  .settings(commonSettings)
  .settings(name := "edgar-embed")

// retrieval — hybrid search + RRF + rerank.
lazy val retrieval = (project in file("modules/retrieval"))
  .dependsOn(store)
  .settings(commonSettings)
  .settings(name := "edgar-retrieval")

// rag — agent loop, tools, prompts, citation validator.
lazy val rag = (project in file("modules/rag"))
  .dependsOn(store, retrieval)
  .settings(commonSettings)
  .settings(name := "edgar-rag")

// price — daily price sync + valuation ratios.
lazy val price = (project in file("modules/price"))
  .dependsOn(store)
  .settings(commonSettings)
  .settings(name := "edgar-price")

// worker — main() wiring the Scala Kafka consumers (discovery + embed + price).
lazy val worker = (project in file("modules/worker"))
  .dependsOn(ingest, embed, price)
  .settings(commonSettings)
  .settings(
    name := "edgar-worker-scala",
    Compile / mainClass := Some("edgar.worker.Main")
  )

// api — the Play application (SSE/WS, controllers, auth).
lazy val api = (project in file("modules/api"))
  .enablePlugins(PlayScala)
  .dependsOn(rag, retrieval, store)
  .settings(
    name := "edgar-api",
    libraryDependencies ++= Seq(guice, playScalaTest % Test),
    scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")
  )

// eval — RAG eval harness (golden set runner + CI gate). `sbt eval/run`.
lazy val eval = (project in file("eval"))
  .dependsOn(rag, retrieval)
  .settings(commonSettings)
  .settings(
    name := "edgar-eval",
    Compile / mainClass := Some("edgar.eval.Main")
  )

lazy val root = (project in file("."))
  .aggregate(domain, store, ingest, embed, retrieval, rag, price, worker, api, eval)
  .settings(
    name := "edgar-platform",
    publish / skip := true
  )
