"""Smoke tests — fixtures only, never live EDGAR (CLAUDE.md hard rule 1)."""

import edgar_worker
from edgar_worker.main import edgar_identity


def test_package_version():
    assert edgar_worker.__version__ == "0.1.0"


def test_edgar_identity_reads_env(monkeypatch):
    monkeypatch.delenv("EDGAR_IDENTITY", raising=False)
    assert edgar_identity() is None
    monkeypatch.setenv("EDGAR_IDENTITY", "Test User test@example.com")
    assert edgar_identity() == "Test User test@example.com"
