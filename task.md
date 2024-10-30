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
<br>

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

[ссылки нет - внутри класса джавовского Integer в классе IntegerCache]()

Паттерн Flyweight используется внутри класса джавовского Integer.
он кэширует значения Integer\`ов  от -128 до 127 через IntegerCache. В этом диапазоне объекты повторно используются, что экономит память и снижает количество создаваемых экземпляров

поэтому если:
- сравнивать числа 127 == 127 будет True - ссылки повторно использутся, Flyweight паттерн
- сравнивать числа 128 == 128 будет False - ссылки повторно НЕ использутся, паттерн не используется


> [!картинка]
> [Tu0vc1XwF50.jpg](https://sun9-72.userapi.com/impg/3gMNSsdSXQikMc1uqF2SrsTZRVABuavl6_eUXw/V3BrWOdGrho.jpg?size=1600x1332&quality=95&sign=5e43b08cbb67e0ecff119ced454fa62d&type=album)



<br>

<br>


 [наверх](#Оглавление) 