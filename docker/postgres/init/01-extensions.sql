-- Enable pgvector on first boot. Slick evolutions own all table DDL (CLAUDE.md hard rule 4).
CREATE EXTENSION IF NOT EXISTS vector;
