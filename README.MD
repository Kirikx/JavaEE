#Java EE

##Lesson 1

###Deploy pruf:
![Alt text](md-image/deploy.png?raw=true "Deploy")

##Lesson 2
1. Сервлеты будут выполнять роль контроллеров в разрабатываемом приложении, поэтому необходимо создать:
    * a. Сервлет для главной страницы /main;
    * b. Сервлет для каталога товаров /catalog;
    * c. Сервлет для товара /product;
    * d. Сервлет для корзины /cart;
    * e. Сервлет для оформления заказа /order.

2. В каждый сервлет добавить:
    * a. Заголовок с именем страницы (товары, товар, корзина и т.д.);
    * b. Список (навигационное меню), с помощью которого можно перемещаться между созданными сервлетами.

3. Создать фильтр для всех сервлетов, который будет добавлять к заголовку HTTP-ответа кодировку UTF-8.

##Lesson3
1. Создать jsp-страницы для отдельного товара, корзины, оформления заказа, страницы о компании.
2. В созданных в предыдущем ДЗ сервлетах использовать объект RequestDispatcher для перехода на созданные JSP-страницы.
3. Добавить html-верстку на JSP-страницы
4. На страницу с каталогом добавить 9 статических товаров.

##Lesson4
В домашнем задании создается заготовка панели администратора разрабатываемого приложения. В панели администрирования пользователь получит возможность добавлять, удалять, обновлять товары, категории, а также просматривать заказы и изменять их статус.

1. Создать представления для редактирования и просмотра следующих компонентов:
a. Товары;
b. Категории;
c. Заказы.

2. Создать управляемые бины с методами для удаления, добавления и изменения товаров, категорий.
3. Создать CDI-бины с методами для удаления, добавления и изменения товаров и категорий.

##Lesson5
1. Создать сущности для:
a. Товаров;
b. Категорий;
c. Заказов.

2. Создать DAO классы для:
a. Добавления, обновления, удаления, выборки всех товаров, выборки товара по идентификатору;
b. Добавления, обновления, удаления, выборки всех категорий, выборки категории по идентификатору;
c. Добавления, обновления, удаления, выборки всех заказов, выборки заказа по идентификатору.

3. Создать именованные запросы, запросы с помощью JPQL и с помощью Criteria API.

##Lesson6
1. Создать сервисный слой, который будет взаимодействовать с контроллером и вызывать DAO-методы модели. Сервисный слой должен обращаться к DAO-методам, которые осуществляют выборку из БД.

2. Создать statless-бины для каталога товаров, для выборки товаров, для одного товара.

3. Создать statfull-бин для реализации корзины.

4. Создать перехватчики, которые будут логировать действия пользователей на сайте.

##Lesson7
1. Разработать сервисы JAX-WS и JAX-RS, которые позволяют:
    a. Добавлять товар в БД;
    b. Удалять товар из БД;
    c. Добавлять категорию товара;
    d. Получить товар по Id;
    e. Получить товар по имени;
    f. Получить все товары;
    g. Получить товары по Id категории.
2. К JAX-WS сервису написать клиента.