![](src/main/resources/yandex-mini.jpg)
# YandexStation Binding

Binding для локального управления устройствами Яндекс:

- Яндекс Станция
- Яндекс Станция мини
- Яндекс Станция мини 2
- Яндекс Станция лайт
- Яндекс Станци Макс
- Яндекс Модуль
- Яндекс Модуль - 2
- JBL Link Music
- JBL Link Portable

## Discovery

Пока не доступно, но планируется

## Binding Configuration

Для работы биндинга через локальное API требуется знать токен устройства `device_token`.

Токен устройства получается автоматически биндингом.

Для того, чтобы все это заработало, но заполнить следующие поля:

- IP адрес устройства
- Идентификатор устройства (можно взять в приложении Яндекс Умный Дом). Может выглядеть как `LP0000001232134200124`
- выбрать тип устройства из выпадающего списка
- яндекс-токен (oAuth token) (это токен от яндекс музыки)
- 
![](src/main/resources/config-01.png)

Как получить яндекс-токен

Вариант1:
- использовать https://music-yandex-bot.ru
- на страничке ввести логин и пароль
- в старых чужих инструкциях информация отличается, на текущий момент, надо жать кнопку Войти, но не переходит в бота, так же появиться кнопка Скопировать токен

