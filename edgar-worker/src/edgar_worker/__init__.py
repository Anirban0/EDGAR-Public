"""edgar-worker: edgartools-based fetch + parse consumer.

Consumes ``filings.discovered``, uses pinned edgartools for fetch + item
extraction + standardized financials, writes sections/facts, publishes
``filings.parsed``. See docs/architecture-v2.md §3 (edgar-worker).
"""

__version__ = "0.1.0"
