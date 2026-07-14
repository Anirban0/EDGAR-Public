# P0 spike (roadmap 0.2) — THROWAWAY

De-risks the edgartools mapping the whole pipeline leans on, before any real
investment. Proves, for 3 different companies, that edgartools does:

1. **Item 1A (Risk Factors)** text extraction from a 10-K, and
2. the **standardized income statement** (the deterministic financials path).

## Run

```sh
EDGAR_IDENTITY="Full Name email@example.com" uv run python edgar_spike.py
```

`EDGAR_IDENTITY` is required — EDGAR politeness (CLAUDE.md hard rule 7).

This directory is disposable; delete it once task 0.2 is signed off. The real
fetch+parse path lives in `edgar-worker/` (roadmap 2.x).
