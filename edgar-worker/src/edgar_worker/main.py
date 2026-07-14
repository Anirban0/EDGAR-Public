"""Entry point for the edgar-worker Kafka consumer.

Scaffold only. The Kafka consumer + status guard land with roadmap task 2.1;
extraction mapping (items -> sections, standardized financials -> xbrl_facts,
raw text -> MinIO) lands with task 2.2.
"""

from __future__ import annotations

import os


def edgar_identity() -> str | None:
    """EDGAR politeness identity (CLAUDE.md hard rule 7). Format: 'Full Name email@example.com'."""
    return os.environ.get("EDGAR_IDENTITY")


def main() -> None:
    print(f"edgar-worker scaffold — EDGAR_IDENTITY set: {edgar_identity() is not None}")


if __name__ == "__main__":
    main()
