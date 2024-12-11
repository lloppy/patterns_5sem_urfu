## Оглавление 
- [Задание 1](#задание-1) 
- [Задание 2. Singleton](#задание-2-singleton) 
- [Задание 3. Prototype](#задание-3-prototype) 
- [Задание 4. Static factory method](#задание-4-static-factory-method)
- [Задание 5. Builder](#задание-5-builder)
- [Задание 6. Factory method](#задание-6-factory-method)
- [Задание 7. Abstract factory](#задание-7-abstract-factory)
- [Задание 8. Adapter](#задание-8-adapter)
- [Задание 9. Bridge](#задание-9-bridge)
- [Задание 10. Composite](#задание-10-composite)
- [Задание 11. Decorator](#задание-11-decorator)
- [Задание 12. Facade](#задание-12-facade)
- [Задание 13. Flyweight](#задание-13-flyweight)
- [Задание 14. Proxy](#задание-14-proxy)
- [Задание 15. Сhain of responsibility](#задание-15-chain-of-responsibility)
- [Задание 16. Сommand](#задание-16-command)
- [Задание 17. Interpreter](#задание-17-interpreter)
- [Задание 18. Iterator](#задание-18-iterator)
- [Задание 19. Mediator](#задание-19-mediator)
- [Задание 20. Memento](#задание-20-memento)
- [Задание 21. Observer](#задание-21-observer)
- [Задание 22. State](#задание-22-state)
- [Задание 23. Strategy](#задание-23-strategy)
- [Задание 24. Visitor](#задание-24-visitor)
- [Задание 25. Template method](#задание-25-template-method)


## **Задание 1**

[Проект Asnova here](https://github.com/lloppy/My-Asnova)
<br>

## **Задание 2. Singleton** 

```java
// Паттерн Singleton
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/7509f16037e45bc3f5d1cfc59e79a83001fc110b) 

1. UserManager реализован как синглтон, тк:
	- У нас один юзер, поэтому каждый раз мне нужно ссылаться на одно и того же юзера (объекта)
	- UserManager можно легко получить из любого места приложения
	
2. Segments реализован как синглтон, тк:
	-  Мне нужен всего один экземпляр класса
	- Удобно обращаться к константам по их названиям, удобно получать сразу все константы методом в Segments
	- Segments можно легко получить из любого места приложения
	- Я еще летом переписывала этот кусок кода, потому что изначально у меня всё хранилось в строках, которые дублировались. Сейчас дублирования переменных нет
	
3. Объекты в DataModule и NetworkModule (но не сами классы). В классах используется аннотация @Singleton в Dagger Hilt тк:
	- Hilt по умолчанию при каждом обращении будет создавать новые экземпляры, а нам нужен только один экземпляр. С аннотацией @Singleton будет создаваться один экземпляр (например в DataModule мы с помощью Retrofit создаем единственный экземпляр GroupsApi, к которому потом каждый раз будет обращаться Апи для групп во Вконтакте)

Протупила с работой в классе, пусть будет тут https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/lessons/singleton.md


<br>

### На доп баллы, потерянные на работе в классе
> [!NOTE] На доп баллы, потерянные на работе в классе
> Что значит "Экземпляр должен расширяться путем порождения подклассов **(1)**, и клиентам нужно иметь возможность работать с расширенным экземпляром без модификации своего кода **(2)**"

**(1)** *"Экземпляр должен расширяться путем порождения подклассов ..."*

Пусть есть главный класс - **Экземпляр**, ребенок1 и ребенок2 расширяют (extends в котлин) функционал класса-родителя. 

<br>

Схема "Родитель-Экземпляр расширяется путем порождения детей-подклассов":

Экземпляр -> ребенок1 (расширение) Экземпляра

Экземпляр -> ребенок2 (расширение) Экземпляра

<br>

Пример: Сюда можно привести геом фигуры и вычисление их площади (**Shape**-родитель и *Circle: Shape()* *Square : Shape()*), или класс животные и звуки которые разные животные издают (**Animal**-родитель и *Dog : Animal()* *Cat : Animal()*).

<br>

**(2)** Пусть клиент - это функция fun Клиент() 
Пусть *Клиенту()* мы можем в аргументы "скармливать" только животных Animal. 

Вторая часть тезиса звучала как *"...Клиентам нужно иметь возможность работать с расширенным экземпляром без модификации своего кода"* - значит, что *Клиенту()*  мы должны уметь подать и Animal, и Dog, и Cat. 

Предполагаю, это нужно для фабрик в будущем

<br>

## **Задание 3. Prototype** 

```java
// Паттерн Prototype
```
[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/a4404a617fc81b1b6e8462e41d7b58ff7c54f3b2)

Не могла придумать какую уже реализованную фичу можно было бы переделать под Prototype, поэтому делала всё с нуля.

У центра есть учебные группы. Для моего приложения учебные группы нужны для фильтрации расписания (по учебной группе).
Списка со всеми группами у заказчика нет, но есть расписание, в котором коряво написаны когда и у какой группы занятия (у заказчика просто нет шаблона по которому он составляет внутреннее расписание, поэтому *коряво*).

Prototype я реализовала для роли Админа, который сможет редактировать (делается **отредактированная копия**) название учебной группы. 


***Почему Prototype?***

У нас есть колонка со всеми спарсенными item\`ами групп, которые нужно будет редактировать Админу вручную. По нажатию на item можно его дублировать/редактировать. После сохранения, дкблированный и/или измененный item добавляется в общий лист. 

[See video of duplicating](https://drive.google.com/file/d/15a0De5vueUIzXMAtgxLqwAo6rvLtTvgC/view?usp=drive_link)

<br>

## **Задание 4. Static factory method** 

```java
// Паттерн Static factory method
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/872964fa6d01a9523a1cc6c75051ee041b7ccbeb)

Изначально у меня было два раздельных класса для расписаний: с сайта ([Расписание- ASNOVA учебно-экспертный центр](https://asnova.pro/raspisanie)) и приватное (парсится из mail calendar). Чтобы создать экземпляр класса нужно было вспоминать названия этих классов, я постоянно путалась какое слово идет первым. 

Я переделала свой прошлый код, теперь создание упрощено: есть класс Schedule, к которому мы обращаемся через точку и сразу видим, какие экземпляры мы можем создать - Schedule.createPrivateSchedule() или Schedule.createSiteSchedule().

Вот так это выглядит:
- сайт:  [gcKczrEZflA.jpg](https://sun9-58.userapi.com/impg/s94rnDHbnoEU4X-FvgiZMLXCpN-p0CxKdJVRCw/gcKczrEZflA.jpg?size=997x2160&quality=96&sign=aef03a06f62a582167d6da79d90081c1&type=album)
- main calendar: [w6hTD5RImt4.jpg](https://sun9-16.userapi.com/impg/Mwzfkq43VFNBBLJBKxbMhHa3si7fyuppNLBfTQ/w6hTD5RImt4.jpg?size=997x2160&quality=96&sign=34dd928213b6122deec47561acada29b&type=album)


Также со Static factory method удобно будет дальше делать приложение, тк у меня планируется еще один вид расписания - из Firebase, который потом также добавлю в класс Schedule и сделаю под него метод Schedule.createFirebaseSchedule()

<br>

## **Задание 5. Builder** 

```java
// Паттерн Builder
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/0d745bdb8c153aff62db4c517df595da9b794fb5) 

Билдер уже был реализован в моем приложении, потому что это ***эталон*** создания сложных объектов в андроиде со множеством параметров.

- В `CalDavClient.kt`  используется `Request.Builder` для создания HTTP-запросов. В доп параметрах я добавляю 
.url(baseUrl)  
.header("Authorization", Credentials.basic(username, password))  
и делаю билд: .build()

- `IntentSenderRequest.Builder` нужен для создания запроса на вход в аккаунт (без необходимости создания множества конструкторов)
- В `NetworkModule.kt`  используется `Retrofit.Builder` для настройки сетевых запросов. С ним легче менять параметры для запроса ретрофита.

- и тд.


<br>

## **Задание 6. Factory method** 

```java
// Паттерн Factory method
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/ba9f3b6defc7af90892d4019ba6e2aea0b580d2e)

Структура:

> [!картинка-схема]
> [Tu0vc1XwF50.jpg](https://sun9-5.userapi.com/impg/dcDfZ7ksjxD5hUqR7hG7JhbES-DyIxEXsMjUAQ/Tu0vc1XwF50.jpg?size=2560x1920&quality=96&sign=2fdcf714be6e850988881cc07d3637f6&type=album)


Выбрала фабричный метод для Android для обработки пуш-уведомлений с помощью Firebase Cloud Messaging (FCM) по статье [«Фабричный метод» в разработке под Android. Лучший способ обработки пушей / Хабр](https://habr.com/ru/articles/332006/)
В первый раз их подключала.


Мне нужно было сделать уведомления для ролей (учащийся, админ, гость, сотрудник).
Есть абстрактные классы `CoreNotification`(абстрактный продукт) и `CoreNotificationCreator`(абстрактный создатель) и классы реализации конкретных создателей и классы реализации конкретных продуктов (см картинку-схему)


После добавления стало легче добавлять новые типы уведомлений. 
В зависимости от типа уведомления будут создаются соответствующие классы. 
Я сдеала реализацию не для всех ролей и не для всех конкретных случаев, но теперь чтобы их добавить не нужно изменять уже имеющийся код


> [!как это выглядит]
> [UbqA2q5mH1c.jpg](https://sun9-6.userapi.com/impg/2ARfZJ7Q27Y6L6QRgSrZRnqGZgemRtiUSgIGAg/UbqA2q5mH1c.jpg?size=997x2160&quality=96&sign=b42e01422703b4aef1551bc450f3d119&type=album) 

<br>

## **Задание 7. Abstract factory** 

```java
// Паттерн Abstract factory
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/92ac0d755228548d11e8e7ea2c4b228cb326cff3)

реализовала `Chat` Abstract factory для чатов разных ролей.
```kotlin
@Provides  
fun provideChatFactory(): ChatFactory {  
    // Паттерн Abstract factory  
    // в зависимости от выбранной роли отображаем чат    return when (UserManager.getRole()) {  
        Role.ADMIN -> return AdminChatFactory()  
        Role.STUDENT -> return StudentChatFactory()  
        Role.WORKER -> return StudentChatFactory()  
        Role.GUEST -> return GuestChatFactory()  
        else -> return GuestChatFactory()  
    }  
}
```
Для загрузки `Channels` и `Messages` для каждой роли должна быть своя логика получения из Firebase.

```kotlin
interface Chat {  
    fun getChannels(): List<Channel>  
    fun getMessages(channelId: Long): List<Message>  
}
```

Теперь в моей `ChatScreenViewModel` не нужно явно указывать для какой роли мы создаем фабрику чатов (без if-else). 
просто вызываем метод *createChat()*

```kotlin
private val chat: Chat = chatFactory.createChat()
```

Вот так работает:
[Видео смены ролей без реализации firebase](https://drive.google.com/file/d/160jvHvDBN4ECUdFFmdRwyXDciUtY5Vul/view?usp=drive_link)

теперь для каждой роли код инкапсулируется в соответствующей фабрике, а для создании самой фабрики вызывается единственный и постоянный метод *createChat()* который не требует указывать специально роль, для которой мы строим нашу фабрику. А функции getChannels() и getMessages() позволяет иметь разные реализации интерфейса `Chat` для каждой роли (данные с файербейса будут доставаться по разному)

<br>

## **Задание 8. Adapter** 

```java
// Паттерн Adapter
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/da31ee3b47486224659671bb1dd15b0d7115e1b4)

делала по гайду [Adam Świderski - Adapter Pattern in Kotlin](https://swiderski.tech/kotlin-adapter-pattern/)
изменила структуру получения внутреннего расписания CalDav на паттерн адаптер

Почему Adapter?
На данный момент у заказчика все приватное расписание хранится на mail calendar (так исторически сложилось). Mail calendar использует протокол `CalDav` для передачи расписания по сети. 

Адаптер нужен если в будущем заказчик захочет перейти с Mail Calendar в другой сервис, тогда адаптер нужно будет просто поменять на этот *другой серви*с, и приложению не потребуются значительные изменения в архитектуре.
Либо если сам Mail поменяет что-то в своем API или изменит формат данных, то нужно будет только обновить адаптер.

Теперь `CalDavImpl` напрямую не связан с клиентом \+ детали реализации скрыты

Использование:

```kotlin
@Provides  
@Singleton  
fun provideScheduleRepository(): ScheduleRepository {  
    // Паттерн Adapter  
    val calDavAdapteeImpl = CalDavAdapteeImpl()  
    val calDavAdapter = CalDavAdapter(calDavAdapteeImpl)  
  
    // using the Adapter with Adaptee instance  
    return ScheduleRepositoryImpl(calDavAdapter)  
}
```

<br>

## **Задание 9. Bridge** 

```java
// Паттерн Bridge
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/7f2b4f0799084b1ddffb3525f6243a78ab7fc0a3)

в моем приложении есть экран настроек
настрийки в приложении разные: темная/светнлая тема, язык, включены/выкл уведомления, вход/гость.

эти настройки контролируются из Storage, в котором есть два метода:
```kotlin
fun save(data: T) 
fun get(): T
```


1. **Абстракция**:  общий интерфейс `Storage<T>`
описывает общие операции для всех типов хранилищ

2. **Реализация**: 
Это интерфейсы: `NotificationsSettingStorage`, `LanguageSettingStorage`, `IsAuthedUserStorage` и тд.
Реализация наследуется от общего интерфеса Storage, предоставляет конкретную логику работы с данными (внутри определяется с какими именно Options мы работаем, например:
```kotlin
// Паттерн Bridge  
interface ThemeSettingStorage : Storage<ThemeOption>{  
    override fun save(theme: ThemeOption)  
    override fun get() : ThemeOption  
}
```
)


3. **Связующее звенья**: 
`NotificationsSettingStorageImpl`, `LanguageSettingStorageImpl`, и тд.

Это связующие звена между абстракцией (интерфейсами) и конкретной реализацией (например, использование SharedPreferences)

```kotlin
// Паттерн Bridge  
class NotificationsSettingStorageImpl(context: Context) : NotificationsSettingStorage {  
    private val sharedPreferences =  
        context.getSharedPreferences(SHARED_PREFS_NOTIFICATIONS_SETTING, Context.MODE_PRIVATE)  
  
    override fun save(notifications: NotificationsOption) {  
        sharedPreferences.edit().putString(KEY_NOTIFICATIONS_SETTING, notifications.value).apply()  
    }  
  
    override fun get(): NotificationsOption {  
        return NotificationsOption(  
            sharedPreferences.getString(KEY_NOTIFICATIONS_SETTING, "ALL") ?: "ALL"  
        )  
    }  
}
```

Почему использую:
- есть несколько альтернативных реализаций и необходимо независимо менять интерфейс и реализацию 
- число классов в иерархии параметров настроек может в будущем быстро расти 
  
<br>

## **Задание 10. Composite** 

```java
// Паттерн Composite
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/d5959b41e9b2b4111b170923b6e2228356b12cbd)


я использую realtime firebase database.
мой json импортированный из файербейса:
[patterns\_5sem\_urfu/tasks/code/asnova-f7891-default-rtdb-export.json at main · lloppy/patterns\_5sem\_urfu · GitHub](https://github.com/lloppy/patterns_5sem_urfu/blob/main/tasks/code/asnova-f7891-default-rtdb-export.json)

**сделала испорт всех нод.**

использование:
```kotlin
val exporter = DatabaseExporter()  
exporter.loadDatabase()
```

Почему:
- у меня была бд
- у бд есть ноды, ключ-значение
- бд можно обойти рекурсивно (рекурсивный обход дерева)
<br>

## **Задание 11. Decorator**

```java
// Паттерн Decorator
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/870f5de6de1aed90fff9bb56530e6d916c7ebeb2)

у меня был UI элемент `SkeletonScreen`, который нужен был для обработки UI\`я экрана:
```kotlin
@Composable  
fun SkeletonScreen(  
    isLoading: Boolean,  
    skeleton: @Composable () -> Unit,  
    content: @Composable () -> Unit  
) {  
    if (isLoading)  
        skeleton()  
    else  
        content()  
}
```

этот компонент я использовала для экранов FeedScreen, SelectClassScreen и ScheduleScreen.

Призанаки:
- это конкретный декоратор для экранов
- дополнительное поведение - отображение состояния загрузки (loading state)
-  содержит (обертывает) экземпляр базового класса - обертывает базовый экран

[Decorator Pattern in Jetpack Compose Android Apps](https://www.blog.finotes.com/post/decorator-pattern-in-jetpack-compose-android-apps) - использовала как подсказку по применению


<br>

## **Задание 12. Facade**

```java
// Паттерн Facade
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/8b60d014afec3cdca9e68b27ebbe05fa18546e95)


Структура:

> [!картинка-схема]
> [Tu0vc1XwF50.jpg](https://sun9-4.userapi.com/impg/F7Dz2tspbbxFOxeUH0YLhwS2ddz-qJmu7Sr5kA/lj_S3XbKEf0.jpg?size=720x1280&quality=95&sign=904727a26990d872096ae7eaf4376317&type=album)


Почему:

У меня есть репозиторий новостей, где много методов. Они используются внутри репозитория. 
Поэтому я "вытащила наружу" только то, что используется извне (вытащила в NewsFacade) - и тем самым отделила внешние методы от внутренних в репозитории новостей. фасад скрыл излишние детали реализации, предоставляя доступ извне только к необходимым методам.

<br>

## **Задание 13. Flyweight** 

```java
// Паттерн Flyweight
```

[ссылка на Integer - используется внутри класса джавовского Integer в классе IntegerCache](https://github.com/openjdk/jdk13/blob/master/src/java.base/share/classes/java/lang/Integer.java)

Паттерн Flyweight используется внутри класса джавовского Integer.
он кэширует значения Integer\`ов  от -128 до 127 через IntegerCache. В этом диапазоне объекты повторно используются, что экономит память и снижает количество создаваемых экземпляров

поэтому если:
- сравнивать числа 127 == 127 будет True - ссылки повторно использутся, Flyweight паттерн
- сравнивать числа 128 == 128 будет False - ссылки повторно НЕ использутся, паттерн не используется


> [!картинка]
> [Tu0vc1XwF50.jpg](https://sun9-72.userapi.com/impg/3gMNSsdSXQikMc1uqF2SrsTZRVABuavl6_eUXw/V3BrWOdGrho.jpg?size=1600x1332&quality=95&sign=5e43b08cbb67e0ecff119ced454fa62d&type=album)

<br>

## **Задание 14. Proxy** 

```java
// Паттерн Proxy
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/c64d9d88b45a473b5dd80b462ce377a485911b97)

Сделала для двух репозиториев прокси кэширования

1. у меня был **UserRepository** и реализация *UserRepositoryImpl*. До рефакторинга и добавления паттерна, в *UserRepositoryImpl* методах добавляла логи - смотрела что приходит в функцию и как она обрабатывает. Поэтому решила отделить логи от самого функционального кода - добавила `class LoggingUserRepository(private val repository: **UserRepository**) : **UserRepository**` , где создала общий тег `private val tag = "LoggingUserRepository"`. 
Очень удобно и сразу очистился код в UserRepositoryImpl от лишних логов.

Кстати, поменять на прокcи тоже было легко:
```java
@Provides  
@Singleton  
fun provideUserRepository(  
    @ApplicationContext context: Context,  
    oneTapClient: SignInClient  
): UserRepository {  
    // Паттерн Proxy  
    val originalRepository = UserRepositoryImpl(context, oneTapClient) 
    return LoggingUserRepository(originalRepository)  
}
```

и поменяла одну строчку в DI:
```kotlin
@Provides  
@Singleton  
fun provideScheduleRepository(): ScheduleRepository {    
    // Паттерн Proxy  
    val originalRepository = ScheduleRepositoryImpl(calDavAdapter)  
    return LoggingScheduleRepository(originalRepository)  
}
```

2. то же самое сделала и с **ScheduleRepository** и его имплементацией *ScheduleRepositoryImpl*. Логирование теперь в LoggingScheduleRepository:
```java
// Паттерн Proxy  
class LoggingScheduleRepository(private val repository: ScheduleRepository) : ScheduleRepository {  
    private val tag = "LoggingScheduleRepository"
```
После этого почистила *ScheduleRepositoryImpl* от лишнего кода. 


После рефакторинга я имею:
- разделение логирования (LoggingScheduleRepository, LoggingUserRepository) и бизес-логики (*ScheduleRepositoryImpl*, *UserRepositoryImpl*)
- в логах появился свой Тэг, и функция `logResourceResult` для отображения результата `resource`
- код стал чище и понятнее, всем стало приятно :)
<br>

## **Задание 15. Сhain of responsibility** 

```java
// Паттерн Сhain of responsibility
// комментариев в коде нет, потому что это всё (что находится в папке) один паттерн
```

 код: [java-course-tinkoff/src/main/java/edu/hw5/task3 at 80720c130395f810c70f902013debcb01a84f166 · lloppy/java-course-tinkoff · GitHub](https://github.com/lloppy/java-course-tinkoff/tree/80720c130395f810c70f902013debcb01a84f166/src/main/java/edu/hw5/task3)
 это старый код (год назад писался) из другого репозитория

[ссылка на коммит](https://github.com/lloppy/java-course-tinkoff/commits/80720c130395f810c70f902013debcb01a84f166/src/main/java/edu/hw5/task3)


1. **Parser** - абстрактный класс, который определяет метод `getParseDate` и хранит ссылку на следующий парсер в цепочке

```java
public abstract class Parser {
    public Parser nextParser;

    public Parser(Parser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract Optional<LocalDate> getParseDate(String string);
}
```

2. **Конкретные парсеры** (`StringParser`, `MixParser`, `DateParser`)

- **StringParser** - обрабатывает строки с предопределенными значениями ("tomorrow", "today", "yesterday").
- **MixParser** - обрабатывает строки с указанием количества и единиц времени (например, "5 days ago").
- **DateParser** -  обрабатывает даты в различных форматах.

---
- если парсер может обработать строку, он возвращает результат. 
- если нет, он передает запрос следующему парсеру через вызов метода `getNextParser().getParseDate(string)`

```java
@Override 
public Optional<LocalDate> getParseDate(final String string) {
    if (string.equalsIgnoreCase("tomorrow")) {
        return Optional.of(LocalDate.now().plusDays(1));
    } else if (string.equalsIgnoreCase("today")) {
        return Optional.of(LocalDate.now());
    } else if (string.equalsIgnoreCase("yesterday")) {
        return Optional.of(LocalDate.now().minusDays(1));
    } else {
        return getNextParser().getParseDate(string); // передает следующему парсеру
    }
}
```

3. **Построение цепочки**
метод buildChain() в классе `Task3` делает цепочку парсеров

```java
private void buildChain() {
    DateParser dateParser = new DateParser(null);
    MixParser mixParser = new MixParser(dateParser);
    StringParser stringParser = new StringParser(mixParser);

    handlers = List.of(stringParser, mixParser, dateParser);
}
```

и применяется в методе parseDate() в классе `Task3`

```java
Optional<LocalDate> parseDate(String string) {
    return handlers.stream()
        .map(parser -> parser.getParseDate(string))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findFirst();
}
```


Почему CoR:
- можно добавлять новые парсеры или изменять порядок их обработки, при этом не изменяя текущий код
- в этом коде форматы дат обрабатываются последовательно, через цепочку парсеров. если значение подошло - оно будет возвращаться, а если ни один парсер не подошел - значит нужно дописать парсер на непокрытый случай
- это значит что система *расширяемая* 

<br>

## **Задание 16. Command** 

```java
// Паттерн Command
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/d6a968f8cda0833b7fdab0affca59a47bb3935b0)

[![Image](https://github.com/lloppy/patterns_5sem_urfu/blob/main/backup/files/Pasted%20image%2020241113203434.png)](https://github.com/lloppy/patterns_5sem_urfu/blob/main/backup/files/Pasted%20image%2020241113203434.png)



зачем нужен был рефакторинг на Команду:

- вынесла код, который относится к бд в отдельную вьюмодель:
```java
class ClassesViewModel @Inject constructor( 
	...
) : ViewModel(), CommandReceiver {}

```
Вьюмодель имплеметирует методы от CommandReceiver со всеми комадами.
В итоге мы изолировали код команд. 

- при добавлении новых команд в CommandReceiver его легко будет использовать через обработчик:

```java
val commandProcessor = { command: Command ->  
    classesViewModel.processCommand(command)  
}

```

- в интерфейсе Команда также потом можно реализовать отмену действия (реализацию отметны я не делала в коде):
```java
interface Command {  
    fun execute(receiver: CommandReceiver)  
    fun undo()  
}

```

В итоге разделила код из вьюмодели в `ClassesViewModel`, где описаны команды

<br>
## **Задание 17. Interpreter** 
не реализовала в моем коде, он не нужен тк:

- у меня нет админки, потому что сотрудницы заказчика не смогли бы написать команду (например для получения данных с бд, для них лучше одну кнопку сделать)
- у меня в коде были Регексп, но под паттерн это не уложить (там просто текст разбивался на поля дата-класса)
- нет также и возможности для пользователя задать кастомизацию командами

<br>

## **Задание 18. Iterator** 

```java
// Паттерн Iterator
```

[ссылка на коммит](https://github.com/lloppy/hapson/commit/c27105e38cd15a91963aa3d4e579c86fab41a5a5)



Новый проект: [Page not found · GitHub · GitHub](https://github.com/lloppy/hapson)

в Агрегаторе ClassIteratorImpl реализован внутренний анонимный класс Итератора. агрегатор ClassIteratorImpl управляет коллекцией классов организаций (которые будут пользоваться моим приложением) - ClassModel.

итератор обходит коллекцию классов ClassModel не раскрывая детали реализации агрегатора


также в ClassIteratorImpl я сделала кастомное добавление класса `addClass` - если лимит по кол-ву классов в текущей подписке будет превышен - то нужно будет просить купить подписку (и ограничить добавления класса ClassModel)

```java
class ClassIteratorImpl(private val capacity: Int) : Iterable<ClassModel> {  
    private val classes = mutableListOf<ClassModel>()  

    fun addClass(classModel: ClassModel) {  
        if (classes.size < capacity) {  
            classes.add(classModel)  
        } else {  
            Log.e("iterator", "Limit! Please buy and upgrade your current plan!")  
            Log.e("iterator", "Превышен лимит классов для организации!")  
        }  
    }
```

используется в стейте экрана: 

```java
data class ClassesScreenState(  
    val organizationId: String,  
    val organization: Organization = Repository.getOrganizationById(organizationId)!!,  
    val classModels: ClassIteratorImpl = ClassIteratorImpl(capacity = 5)  
){
	...
}

```

<br>

## **Задание 19. Mediator** 


```java
// Паттерн Mediator
```

[ссылка на коммит](https://github.com/lloppy/hapson/commit/af27ce56abcde132d6333f365e61a6e2519e595c)

Новый проект: [Fetching Title#h79w](https://github.com/lloppy/hapson)
пока в новом проекте пустовато.

добавила для ввода текста, две кнопки и логгирование для них через медиатор.

вся логика обработки событий и состояния компонентов - в классе `DialogMediator`. когда текст будет изменяется в TextBox, медиатор обработает изменение и обновит состояние.

Button и TextBox не зависят друг от друга напрямую = хорошо = снижается связанность между компонентами

---

элеметы (текстовое поле и кнопка) описала тут:

```java
abstract class UiElement(val mediator: Mediator)  
  
class Button(mediator: Mediator) : UiElement(mediator) {  
    fun click() {  
        mediator.notify(this, "click")  
    }  
}  
  
class TextBox(mediator: Mediator) : UiElement(mediator) {  
    var text: String by Delegates.observable("") { property, oldValue, newValue ->  
        mediator.notify(this, "text_changed")  
    }  
}
```


логи после взаимодействия с экраном (ввод текста и нажатия на кнопки): 

```java
Notify called with sender: TextBox, event: text_changed
2024-11-20 22:30:59.712 14700-14700 TextDialog              com.lloppy.hapson                    E  Input text changed to: всем привет
2024-11-20 22:31:02.618 14700-14700 TextDialog              com.lloppy.hapson                    E  Notify called with sender: Button, event: click
2024-11-20 22:31:02.619 14700-14700 TextDialog              com.lloppy.hapson                    E  Form submitted with input: всем привет
2024-11-20 22:31:03.444 14700-14700 TextDialog              com.lloppy.hapson                    E  Notify called with sender: Button, event: click
2024-11-20 22:31:03.444 14700-14700 TextDialog              com.lloppy.hapson                    E  Dialog dismissed
```

<br>

## **Задание 20. Memento** 


```java
// Паттерн Memento
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/01e4ce16907942f06486b993d1ac3f0b4cb2b106)


1. Originator:
```java
data class AsnovaStudentsClass(  
    val name: String = ""  
) : Prototype<AsnovaStudentsClass> {  
    fun createMemento(): AsnovaStudentsClassMemento {  
        return AsnovaStudentsClassMemento(this.name)  
    }  
  
    fun restoreMemento(memento: AsnovaStudentsClassMemento): AsnovaStudentsClass {  
        return this.copy(name = memento.name)  
    }  
}
```

2. Caretaker 
```java
class AsnovaStudentsClassCaretaker {  
    private val mementos = mutableListOf<AsnovaStudentsClassMemento>()
```

3. Memento 
```java
data class AsnovaStudentsClassMemento(  
    val name: String  
)
```


4. Использование в клиентском коде:
Писала в прошлом проекте. Есть классы, которые админ может редиктировать.
Когда админ нажимает на кнопку Удалить учебный класс-группу, значение сохраняется в list мементо. Паттерн нужнен чтобы потом можно было восстановить удаленую админом карточку:
```java
ClassCard(asnovaClass = asnovaClass, onClickDelete = {  
    // Паттерн Memento использование  
    caretaker.saveMemento(asnovaClass.createMemento())  
    viewModel.removeClass(asnovaClass)  
}) { selected ->  
    selectedClass = selected  
    showEditDialog = true  
}
```

Вот тут можно отменить отмену через mеmento:
```java
DropdownMenuItem(  
    text = { Text(text = "Отменить последнее изменение") },  
    onClick = {  
        caretaker.restoreMemento()?.let { memento ->  
            selectedClass?.restoreMemento(memento)  
            Toast.makeText(context, "Изменение отменено", Toast.LENGTH_SHORT).show()  
        } ?: run {  
            Toast.makeText(context, "Нет изменений для отмены", Toast.LENGTH_SHORT).show()  
        }  
        expanded = false  
    })
```

Логи проекта. Сначала я удалила 7 карточек с классами-группами. Потом восстановила 5 штук:
```java
2024-11-27 19:30:42.696 12684-12684 MementoPatternTag       com.example.asnova                   I  saveMemento: выездное обучение по высоте для ООО "Атоммашкомплекс"
2024-11-27 19:30:42.696 12684-12684 MementoPatternTag       com.example.asnova                   D  saveMemento - total size: 1
2024-11-27 19:30:43.204 12684-12684 MementoPatternTag       com.example.asnova                   I  saveMemento: Выездное обучение "Промышленный альпинист" для ООО "ТрансГеоСервис"
2024-11-27 19:30:43.205 12684-12684 MementoPatternTag       com.example.asnova                   D  saveMemento - total size: 2
2024-11-27 19:30:43.624 12684-12684 MementoPatternTag       com.example.asnova                   I  saveMemento: Выездное обучение по высоте для ООО «ПСО «Теплит»
2024-11-27 19:30:43.624 12684-12684 MementoPatternTag       com.example.asnova                   D  saveMemento - total size: 3
2024-11-27 19:30:43.988 12684-12684 MementoPatternTag       com.example.asnova                   I  saveMemento: Выездное обучение "Промышленный альпинист" для ЗАО "СервисГазификация"
2024-11-27 19:30:43.989 12684-12684 MementoPatternTag       com.example.asnova                   D  saveMemento - total size: 4
2024-11-27 19:30:44.362 12684-12684 MementoPatternTag       com.example.asnova                   I  saveMemento: Выездное обучение по высоте и ОЗП для Атомтеплоэлектросети
2024-11-27 19:30:44.362 12684-12684 MementoPatternTag       com.example.asnova                   D  saveMemento - total size: 5
2024-11-27 19:30:44.790 12684-12684 MementoPatternTag       com.example.asnova                   I  saveMemento: Выездное обучение по программе "Стропальщик"
2024-11-27 19:30:44.790 12684-12684 MementoPatternTag       com.example.asnova                   D  saveMemento - total size: 6
2024-11-27 19:30:45.298 12684-12684 MementoPatternTag       com.example.asnova                   I  saveMemento: Выездное обучение по профессии "Водитель аккумуляторного погрузчика до 4 кВт" для АО "Тепличное"
2024-11-27 19:30:45.298 12684-12684 MementoPatternTag       com.example.asnova                   D  saveMemento - total size: 7
2024-11-27 19:30:49.939 12684-12684 MementoPatternTag       com.example.asnova                   I  restoreMemento: remove AsnovaStudentsClassMemento(name=Выездное обучение по профессии "Водитель аккумуляторного погрузчика до 4 кВт" для АО "Тепличное")
2024-11-27 19:30:49.939 12684-12684 MementoPatternTag       com.example.asnova                   D  restoreMemento - total size: 7
2024-11-27 19:30:51.231 12684-12684 MementoPatternTag       com.example.asnova                   I  restoreMemento: remove AsnovaStudentsClassMemento(name=Выездное обучение по программе "Стропальщик")
2024-11-27 19:30:51.232 12684-12684 MementoPatternTag       com.example.asnova                   D  restoreMemento - total size: 6
2024-11-27 19:30:52.297 12684-12684 MementoPatternTag       com.example.asnova                   I  restoreMemento: remove AsnovaStudentsClassMemento(name=Выездное обучение по высоте и ОЗП для Атомтеплоэлектросети)
2024-11-27 19:30:52.297 12684-12684 MementoPatternTag       com.example.asnova                   D  restoreMemento - total size: 5
2024-11-27 19:30:53.515 12684-12684 MementoPatternTag       com.example.asnova                   I  restoreMemento: remove AsnovaStudentsClassMemento(name=Выездное обучение "Промышленный альпинист" для ЗАО "СервисГазификация")
2024-11-27 19:30:53.515 12684-12684 MementoPatternTag       com.example.asnova                   D  restoreMemento - total size: 4
2024-11-27 19:30:54.724 12684-12684 MementoPatternTag       com.example.asnova                   I  restoreMemento: remove AsnovaStudentsClassMemento(name=Выездное обучение по высоте для ООО «ПСО «Теплит»)
2024-11-27 19:30:54.724 12684-12684 MementoPatternTag       com.example.asnova                   D  restoreMemento - total size: 3
2024-11-27 19:30:56.501 12684-12684 MementoPatternTag       com.example.asnova                   I  restoreMemento: remove AsnovaStudentsClassMemento(name=Выездное обучение "Промышленный альпинист" для ООО "ТрансГеоСервис")
2024-11-27 19:30:56.502 12684-12684 MementoPatternTag       com.example.asnova                   D  restoreMemento - total size: 2
```

<br>

## **Задание 21. Observer** 


```java
// Паттерн Observer
```

[ссылка на коммит](https://github.com/lloppy/My-Asnova/commit/bc8a32dd5635bb478472c0fc6fda684c72b23b13)

**Паттерн обзервер реализован внутри андроид (androidx.lifecycle)**

В моем коде он используется как LiveData (точнее как расширение MutableLiveData: public class MutableLiveData\<T> extends LiveData\<T>) вот тут:

```java
// Паттерн Observer  
private val selectedDateMutableState = MutableLiveData(LocalDate.now())  
val selectedDate: MutableLiveData<LocalDate?> = selectedDateMutableState
```


**Про LiveData. как устроена и почему обзервер**

**Обзор LiveData внутри.**

Внутри есть метод observe и removeObserver:
```java
@MainThread  
public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {  
    assertMainThread("observe");  

    LifecycleBoundObserver wrapper = new LifecycleBoundObserver(owner, observer);  
    ObserverWrapper existing = mObservers.putIfAbsent(observer, wrapper);  
  ...
    owner.getLifecycle().addObserver(wrapper);  
}

@MainThread  
public void removeObserver(@NonNull final Observer<? super T> observer) {
	removed.detachObserver();
}
```

Когда значения внутри ЛайвДата будет обновлено(`setValue()` или `postValue()`) все активные наблюдатели получат уведомление:
```java
package androidx.lifecycle

fun interface Observer<T> {  
  
    /**  
     * Called when the data is changed to [value].  
     */    fun onChanged(value: T)  
}
```

```java
private void considerNotify(ObserverWrapper observer) {  
    observer.mLastVersion = mVersion;  
    observer.mObserver.onChanged((T) mData);  
}
```

Подписанная схема:
![[Pasted image 20241127202536.png]]

![схема](https://github.com/lloppy/patterns_5sem_urfu/blob/main/backup/files/Pasted%20image%2020241127202536.png)

---

еще интерфейс обзервер реализует медиатор:
```java
public class MediatorLiveData\<T> extends MutableLiveData\<T> {
```

<br>

## **Задание 22. State** 


```java
// Паттерн State
```

[ссылка на коммит. опять в старом репо, в новом я только начала модули делать. во всем старом приложении много стейтов, но отметила только в расписании один вариант использования](https://github.com/lloppy/My-Asnova/commit/b525e75ddf714cff213cc8278df194bc53dfb4a2)

У меня были уже стейты в приложении. Всю архитектуру экранов приложения (всех экранов) я делила на три части: Screen, ViewModel, State (так у каждого экрана). State нужен чтобы отображать текущее состояние экрана при взаимодейстии с бекендом. Если бекенд грузит - отображаем загрузку, если ошибка - ошибку, если все хорошо - выводим данные. 


1. Контекст - экран расписания fun ScheduleScreen:

```java
@Composable  
fun ScheduleScreen(  
    externalRouter: Router,  
    context: Context,  
    lifecycleOwner: LifecycleOwner,  
    viewModel: ScheduleScreenViewModel = hiltViewModel()  
) {  
    val state = viewModel.state
```

Если загрузка - показываем базовый экран skeleton():
```java
@Composable  
fun SkeletonScreen(  
    isLoading: Boolean,  
    skeleton: @Composable () -> Unit,  
    content: @Composable () -> Unit  
) {  
    if (isLoading)  
        skeleton()  
    else  
        content()  
}
```

Если ошибка - выводим тектом поверх экрана:
```java
if (state.value.error.isNotBlank()) {  
    item {  
        Text(  
            text = state.value.error,  
            color = MaterialTheme.colorScheme.error,  
            textAlign = TextAlign.Center,  
            modifier = Modifier  
                .fillMaxWidth()  
                .padding(horizontal = 20.dp)  
                .align(Alignment.Center)  
        )  
    }  
}
```

2. Стейт: 

```java
data class ScheduleState(  
    var privateSchedule: Map<LocalDate, List<ScheduleAsnovaPrivate>> = mutableMapOf(),  
    var siteSchedule: List<ScheduleAsnovaSite> = emptyList(),  
    val error: String = "",  
    val loading: Boolean = false,  
    var currentScheduleIsPrivate: Boolean = true  
)
```

3. Конкретные стейты:
Их три вида: ошибка, загрузка и успех

```java
sealed class Resource<T>(  
    val data: T? = null,  
    val message: String? = null,  
) {  
    class Success<T>(data: T?) : Resource<T>(data)  
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)  
    class Loading<T>(data: T? = null) : Resource<T>(data)  
}
```

Пример использования-загрузки данных с сайта во **ViewModel-классе**:
```java
private fun loadScheduleFromSite() {  
    getScheduleFromSiteUseCase(callback = { result ->  
        when (result) {  
            is Resource.Loading -> {  
                _state.value = ScheduleState(loading = true)  
            }  
  
            is Resource.Success -> {  
                _state.value = ScheduleState(siteSchedule = result.data ?: emptyList())  
                val temp = mutableListOf<ScheduleAsnovaSite>()  
                for (item in _state.value.siteSchedule) {  
                    temp.add(item)  
                }  
                _state.value.siteSchedule = temp  
            }  
  
            is Resource.Error -> {  
                _state.value = ScheduleState(  
                    error = result.message ?: "An unexpected error occurred"  
                )  
            }  
        }  
    })  
}
```


<br>

## **Задание 23. Strategy** 


```java
// Паттерн Strategy
```

[ссылка на коммит](https://github.com/lloppy/hapson/commit/c30893c32111c814a1730e5e0973e6b6d015fb16)

делала по этому туториалу: [Site Unreachable](https://medium.com/android-dev-hacks/strategy-design-pattern-android-real-life-example-a055bb0353b3) 

делала фильтрацию, по которой можно будет находить курс. теперь можно фильтровать по (1.) организации и по (2.) имени самого курса. 


1. Контекст - сам экран фильтрации
```java
fun CoursesSearch() {

	TextField(  
	    value = searchQuery,  
	    onValueChange = {  
	        searchQuery = it  
	  
	        // Паттерн Strategy  
	        val nameFilter = NameFilter(searchQuery)  
	        val organizationFilter = OrganizationFilter(selectedOrganizationId)  
	  
	        val filterManager = FilterManager()  
	        filteredCourses = filterManager.applyFilters(  
	            listOf(nameFilter, organizationFilter),  
	            originalCourses  
	        )  
	    },  
	    label = { Text("Поиск курсов") },  
	    modifier = Modifier.fillMaxWidth()  
)
```

2. Стратегия:
```java
// Паттерн Strategy  
interface Filterable {  
    fun applyFilter(courses: List<Course>): List<Course>  
}
```

3. Конкретные стратегии:
- class OrganizationFilter
- class NameFilter

```java
// Паттерн Strategy  
class NameFilter(private var nameQuery: String?) : Filterable {  
    override fun applyFilter(courses: List<Course>): List<Course> {  
        return if (!nameQuery.isNullOrEmpty()) {  
            courses.filter { it.name.contains(nameQuery!!, ignoreCase = true) }  
        } else {  
            courses  
        }  
    }  
}
```

**Зачем применять паттерн в коде:**
он обеспечивает  взаимозаменяемость фильтров = стратегию фильтрации можно сменить "на ходу" (например: в ExposedDropdownMenuBox или в TextField вводом)


<br>

## **Задание 24. Visitor** 


```java
// Паттерн Visitor
```

[ссылка на коммит]()




<br>

## **Задание 25. Template method** 


```java
// Паттерн Template method
```

[ссылка на коммит](https://github.com/lloppy/hapson/commit/ffb81d84f2444ce419fd43d92c672315be1c4626)

Сам жизненный цикл (android lifecycle) может быть рассмотрен как Template method. Эти методы всегда неизменны и всегда присутствуют в любом андроид приложении:

[![Image](https://www.edureka.co/blog/wp-content/uploads/2019/08/Activity-Life-Cycle-Android-Activity-Life-Cycle-Edureka-1.png)

- **onCreate()**: вызывается при создании Activity, используется для инициализации компонентов интерфейса и выполнения одноразовых операций.
- **onStart()**: вызывается перед тем, как Activity станет видимой для пользователя, подготавливает интерфейс к отображению.
- **onResume()**: вызывается, когда Activity становится активной и готовой к взаимодействию с пользователем.
- **onPause()**: вызывается, когда Activity теряет фокус, позволяет сохранить состояние и освободить ресурсы.
- **onStop()**: вызывается, когда Activity больше не видна, используется для освобождения ресурсов, которые не нужны в фоновом режиме.
- **onDestroy()**: вызывается перед уничтожением Activity, позволяет выполнить финальные операции по очистке ресурсов

Фиксирования инвариантная часть алгоритма - методы onCreate, onStart, onResume, onPause, onStop, onDestroy, при этом реализация конкретики может меняться в подклассах.

1. Рассмотрим метод onCreate()

	- По **дефолту** метод выглядит так: 

![Image](https://sun9-47.userapi.com/s/v1/ig2/3nujxp2CD2N4NQcPLjX3IemEfxC3btBaLPgNx84nOINeKlE-DYUPkWpTr02LWPzQbCMBtle3UX-qFkOxme1TlQPQ.jpg?quality=95&as=32x18,48x28,72x41,108x62,160x92,240x138,360x206,480x275,540x310,640x367,720x413,987x566&from=bu&u=Z2BE_iLaBPoUl6s2T8SMcn1U1fnj6uxKPEfvaJDgICc&cs=987x566)

- Но при создании приложения мы **изменяем** этот метод, где определяем ключевые фукнции приложения, вот так (мое приложение): 

![image](https://sun9-50.userapi.com/impg/UBaDomYdHmrN-tQWLdAF2zesF5mPKR0Fa16H4A/uo_TbLgh4wo.jpg?size=1004x888&quality=95&sign=8db80ac25aa83c64bf16171304e2cf78&type=album)

Чтобы поменять логику внутри методов, нужно переопределить методы ComponentActivity:

![image](https://sun9-8.userapi.com/impg/2VkkfpTMYn6HLouWXsSyznRMNaLsb4Pu9Gb1dw/aWbC3Zro3zQ.jpg?size=564x855&quality=95&sign=e9d2320adaf4bd1727c952040a2a7912&type=album)

методы обеспечивают инвариантную часть алгоритма, поэтому андроидщикам можно переопределять их в подклассах для реализации специфической логики приложения (общий алгоритм остается неизменным, а детали могут варьироваться = template method)

<br>

 [наверх](#Оглавление) 