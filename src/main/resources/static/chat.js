// Функция для автоматического прокручивания вниз при добавлении новых сообщений
function scrollToBottom() {
    var chatMessages = document.getElementById("chat-messages");
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

// Функция для автоматического расширения сообщений вниз
function autoExpandMessage() {
    var messageInput = document.getElementById("message-input");
    messageInput.style.height = "auto";
    messageInput.style.height = messageInput.scrollHeight + "px";
}

// Вызываем функцию автоматической прокрутки вниз при загрузке страницы
window.onload = scrollToBottom;

// Обработчик отправки формы
document.querySelector(".chat-footer form").addEventListener("submit", function () {
    // Ваш код для обработки отправки сообщения (POST запрос и т.д.)

    // Очистка поля ввода после отправки сообщения


    // Прокрутка вниз после отправки сообщения
    scrollToBottom();
});

// Обработчик изменения текста в поле ввода сообщения
document.getElementById("message-input").addEventListener("input", function () {
    autoExpandMessage();
});