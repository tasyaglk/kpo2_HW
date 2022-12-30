# Домашнее задания №2 по КПО

  У нас имеется корневая папка. В этой папке могут находиться текстовые файлы, а также другие папки. В других папках также могут находиться текстовые файлы и папки (уровень вложенности может оказаться любым). Необходимо выявить все зависимости между файлами, построить сортированный список, для которого выполняется условие: если файл А, зависит от файла В, то файл А находится ниже файла В в списке.
  Также неободимо было выводить ошибки: существование циклической зависимости, существоввание зависимости от файла, клоторого нет в данной корневой папке и если нам подается пустая папка. 
  
  Решение:
  1. Сначала считывались файлы из корневой папки
  2. Далее для удобства использовались два HashMap'а - {ребенок, список его родителей} и {родитель, список его детей}
  3. После по этим спискам строилась зависимость
  4. Позже проверялось наличие ошибок. Если таковые были, то программа выводила, какая именно это ошибка, и завершала работу
  5. В конце по полученной последовательности в отдельный файл записывалось содержимое файлов в необходимом порядке
  6. Конец, мы молодцы, ура
