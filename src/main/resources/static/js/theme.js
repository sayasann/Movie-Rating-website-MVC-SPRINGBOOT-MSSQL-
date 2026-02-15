(function () {
  const STORAGE_KEY = "theme"; // "light" | "dark"
  const body = document.body;

  function applyTheme(theme) {
    body.classList.toggle("theme-dark", theme === "dark");
    body.classList.toggle("theme-light", theme === "light");

    // Navbar / Footer bootstrap classlarını da çevir
    const nav = document.querySelector("nav.navbar");
    if (nav) {
      nav.classList.toggle("navbar-dark", theme === "dark");
      nav.classList.toggle("bg-dark", theme === "dark");
      nav.classList.toggle("navbar-light", theme === "light");
      nav.classList.toggle("bg-light", theme === "light");
    }

    const footer = document.querySelector("footer");
    if (footer) {
      footer.classList.toggle("bg-dark", theme === "dark");
      footer.classList.toggle("text-light", theme === "dark");
      footer.classList.toggle("bg-light", theme === "light");
      footer.classList.toggle("text-dark", theme === "light");
    }

    // Buton yazısı
    const btn = document.getElementById("themeToggleBtn");
    if (btn) btn.textContent = theme === "dark" ? "☀️ Light" : "🌙 Dark";
  }

  // init
  const saved = localStorage.getItem(STORAGE_KEY);
  const theme = saved === "dark" ? "dark" : "light";
  applyTheme(theme);

  // click
  document.addEventListener("click", (e) => {
    const btn = e.target.closest("#themeToggleBtn");
    if (!btn) return;

    const currentIsDark = body.classList.contains("theme-dark");
    const next = currentIsDark ? "light" : "dark";
    localStorage.setItem(STORAGE_KEY, next);
    applyTheme(next);
  });
})();
