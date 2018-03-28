const left = document.querySelector(".left");
const right = document.querySelector(".right");
const splitcontainer = document.querySelector(".splitcontainer");

left.addEventListener("mouseenter", () => {
  splitcontainer.classList.add("hover-left");
});

left.addEventListener("mouseleave", () => {
  splitcontainer.classList.remove("hover-left");
});

right.addEventListener("mouseenter", () => {
  splitcontainer.classList.add("hover-right");
});

right.addEventListener("mouseleave", () => {
  splitcontainer.classList.remove("hover-right");
});
