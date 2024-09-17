## **Задание 1**

## **Задание 2. Singleton**

Признаки, по которым уже **имеющиеся ранее** классы опознанны как singleton:
- Weird kind of singletons -- enum constants 

Список этих enum классов :

https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/models/gear/GearSpecials.java

https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/models/gear/GearType.java

https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/models/perks/Perk.java

https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/models/PlayerCharacterClass.java

https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/modes/GameModes.java

https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/rules/charactercreation/Race.java

https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/rules/magic/ActionRegulation.java

https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/rules/magic/QuantifierRegulation.java

https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/rules/magic/TimeRegulation.java

https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/userdecisions/UserDecision.java

https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/Direction.java

Пояснение: 
- enum в джаве - единственный экземпляр
- финальный и статический (enum = static final)
- есть геттер - можно обратиться через точку (.), чтобы получить значение
-  конструктор Enum всегда приватный (private)


<br>

Почему я **переписала** именно этот класс (GearEffects https://github.com/lloppy/patterns_5sem_urfu/tree/main/tasks/code/fantasygame/models/gear/GearEffects.java) как Singleton (не хранит состояние):
- у класса GearEffects был приватный конструктор
- имел статические методы
Я добавита инстанс и геттер для получения значение инстанса.


