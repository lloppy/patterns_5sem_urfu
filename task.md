## **Задание 1**

## **Задание 2. Singleton**

Признаки, по которым уже **имеющиеся ранее** классы опознанны как singleton:
- Weird kind of singletons -- enum constants 

Пояснение: 
- enum в джаве - единственный экземпляр
- финальный и статический (enum = static final)
- есть геттер - можно обратиться через точку (.), чтобы получить значение
-  конструктор Enum всегда приватный (private)


Почему я **переписала** именно этот класс (GearEffects https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/models/gear/GearEffects.java) как Singleton (не хранит состояние):
- у класса GearEffects был приватный конструктор
- имел статические методы
Я добавита инстанс и геттер для получения значение инстанса.


