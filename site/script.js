// ====== Configuração ======
// E-mail que receberá os contatos do formulário (abre o cliente de e-mail do visitante).
// Para envio direto sem abrir o e-mail, troque por um serviço como Formspree e ajuste o submit abaixo.
const CONTACT_EMAIL = "contato@verticecto.com.br";

(function () {
  const nav = document.querySelector(".nav");
  const toggle = document.querySelector(".nav__toggle");

  // Nav: fundo ao rolar
  const onScroll = () => nav.classList.toggle("is-scrolled", window.scrollY > 20);
  onScroll();
  window.addEventListener("scroll", onScroll, { passive: true });

  // Menu mobile
  toggle.addEventListener("click", () => {
    const open = nav.classList.toggle("is-open");
    toggle.setAttribute("aria-expanded", String(open));
  });
  document.querySelectorAll(".nav__links a").forEach((a) =>
    a.addEventListener("click", () => {
      nav.classList.remove("is-open");
      toggle.setAttribute("aria-expanded", "false");
    })
  );

  // Animações de entrada
  const reveals = document.querySelectorAll(".reveal");
  if ("IntersectionObserver" in window) {
    const io = new IntersectionObserver(
      (entries) => {
        entries.forEach((e) => {
          if (e.isIntersecting) {
            e.target.classList.add("is-visible");
            io.unobserve(e.target);
          }
        });
      },
      { threshold: 0.12, rootMargin: "0px 0px -40px 0px" }
    );
    reveals.forEach((el, i) => {
      el.style.transitionDelay = `${(i % 4) * 80}ms`;
      io.observe(el);
    });
  } else {
    reveals.forEach((el) => el.classList.add("is-visible"));
  }

  // Contadores animados
  const reduce = window.matchMedia("(prefers-reduced-motion: reduce)").matches;
  const counters = document.querySelectorAll("[data-count]");
  if (!reduce && "IntersectionObserver" in window) {
    const co = new IntersectionObserver((entries) => {
      entries.forEach((e) => {
        if (!e.isIntersecting) return;
        const el = e.target;
        const end = +el.dataset.count;
        const pre = el.dataset.prefix || "";
        const suf = el.dataset.suffix || "";
        const start = performance.now();
        const dur = 1400;
        const tick = (t) => {
          const p = Math.min((t - start) / dur, 1);
          const v = Math.round(end * (1 - Math.pow(1 - p, 3)));
          el.textContent = pre + v + suf;
          if (p < 1) requestAnimationFrame(tick);
        };
        requestAnimationFrame(tick);
        co.unobserve(el);
      });
    }, { threshold: 0.5 });
    counters.forEach((c) => co.observe(c));
  }

  // Botões de plano pré-selecionam o interesse
  const select = document.querySelector('select[name="plano"]');
  document.querySelectorAll("[data-plan]").forEach((b) =>
    b.addEventListener("click", () => { select.value = b.dataset.plan; })
  );

  // Formulário
  const form = document.getElementById("contact-form");
  const status = form.querySelector(".form__status");
  form.addEventListener("submit", (ev) => {
    ev.preventDefault();
    let ok = true;
    form.querySelectorAll("[required]").forEach((f) => {
      const bad = !f.value.trim() || (f.type === "email" && !/^\S+@\S+\.\S+$/.test(f.value));
      f.classList.toggle("is-invalid", bad);
      if (bad) ok = false;
    });
    if (!ok) {
      status.className = "form__status err";
      status.textContent = "Preencha nome, e-mail válido e o seu desafio.";
      return;
    }
    const d = Object.fromEntries(new FormData(form));
    const body =
      `Nome: ${d.nome}\nEmpresa: ${d.empresa || "-"}\nE-mail: ${d.email}\nTelefone: ${d.telefone || "-"}\n` +
      `Interesse: ${d.plano || "Ainda não sei"}\n\nDesafio:\n${d.mensagem}`;
    window.location.href =
      `mailto:${CONTACT_EMAIL}?subject=${encodeURIComponent("Diagnóstico gratuito — " + (d.empresa || d.nome))}` +
      `&body=${encodeURIComponent(body)}`;
    status.className = "form__status ok";
    status.textContent = "Obrigado! Abrimos o seu e-mail para concluir o envio.";
  });

  document.getElementById("year").textContent = new Date().getFullYear();
})();
