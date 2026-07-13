# EDGAR Intelligence Platform

> Everything companies are legally forced to disclose — searchable, comparable,
> and answerable in seconds, with every claim cited to the exact filing paragraph.

Full-stack, data-heavy RAG platform over SEC EDGAR filings.
Scala 3 · Play · Pekko Streams · Kafka · Postgres + pgvector · Next.js · Claude.

**Status:** in development — see [`docs/dev-roadmap.md`](docs/dev-roadmap.md).

## What it does

- Ingests 10-K / 10-Q / 8-K filings from EDGAR in near-real-time (streaming pipeline)
- Parses sections (Risk Factors, MD&A) + XBRL financial facts
- Hybrid retrieval (lexical + vector + rerank) feeding an agentic RAG loop
- Every answer claim carries paragraph-level citations; abstains when evidence is absent
- Risk-factor diffs across years, company dashboards, watchlist alerts on new 8-Ks

## Architecture

Two deployables from one codebase: a **worker** (ingest → parse → embed, linked by
Kafka) and an **api** (retrieval → agent → Play, streamed over SSE).
Full design: [`docs/architecture-v2.md`](docs/architecture-v2.md) ·
Agent internals: [`docs/rag-agent-deep-dive.md`](docs/rag-agent-deep-dive.md)

## Quickstart (dev)

```bash
docker compose up -d      # postgres+pgvector, redpanda, minio
sbt test                  # build + tests (no network: fixtures only)
```

## Data & trust

Official sources only: all filing content comes from SEC EDGAR (public domain).
The sole third-party feed is daily prices for valuation ratios — it never enters
the RAG path. Financial figures are read from structured XBRL data, never
generated from prose.

**Disclaimer:** research tool, not investment advice.
