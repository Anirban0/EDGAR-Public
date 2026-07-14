"""P0 spike (roadmap 0.2) — THROWAWAY.

Goal: prove edgartools does the two things the whole pipeline leans on, for
3 different companies:
  1. Item 1A (Risk Factors) text extraction from a 10-K.
  2. Standardized income statement (the deterministic financials path).

Run:  EDGAR_IDENTITY="Name email@example.com" uv run python edgar_spike.py
"""

from __future__ import annotations

import os
import sys

from edgar import Company, set_identity

TICKERS = ["AAPL", "MSFT", "NVDA"]


def probe(ticker: str) -> None:
    print("=" * 72)
    print(f"COMPANY: {ticker}")
    print("=" * 72)

    company = Company(ticker)
    filing = company.latest("10-K")
    print(f"Latest 10-K: {filing.accession_no}  filed {filing.filing_date}")

    tenk = filing.obj()

    # 1. Item 1A — Risk Factors text.
    risk = tenk["Item 1A"]
    risk_text = str(risk).strip()
    print("\n--- Item 1A · Risk Factors (first 600 chars) ---")
    print(risk_text[:600] if risk_text else "<EMPTY>")
    print(f"[Item 1A length: {len(risk_text):,} chars]")

    # 2. Standardized income statement (deterministic numbers path).
    print("\n--- Standardized Income Statement ---")
    income = tenk.financials.income_statement()
    print(income)


def main() -> int:
    identity = os.environ.get("EDGAR_IDENTITY")
    if not identity:
        print("ERROR: set EDGAR_IDENTITY='Full Name email@example.com'", file=sys.stderr)
        return 1
    set_identity(identity)

    for ticker in TICKERS:
        try:
            probe(ticker)
        except Exception as exc:  # spike: surface, don't swallow
            print(f"!! {ticker} failed: {type(exc).__name__}: {exc}")
        print()
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
