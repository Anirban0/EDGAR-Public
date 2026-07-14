import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "EDGAR Intelligence Platform",
  description: "Cited answers over SEC filings via hybrid-retrieval agentic RAG.",
};

export default function RootLayout({
  children,
}: Readonly<{ children: React.ReactNode }>) {
  return (
    <html lang="en">
      <body className="antialiased">{children}</body>
    </html>
  );
}
