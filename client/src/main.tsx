import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import * as s from "./styles/main.tsx";
import App from "./App.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <s.Wrapper>
      <App />
    </s.Wrapper>
  </StrictMode>
);
