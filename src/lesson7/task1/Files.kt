@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import lesson3.task1.digitNumber
import java.io.File
import kotlin.math.*

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    for (line in File(inputName).readLines()) {
        if (((line.isNotEmpty()) && (line[0] != '_')) || (line.isEmpty())) {
            writer.write(line)
            writer.appendLine()
        }
    }
    writer.close()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> = TODO()


/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
    <body>
        <p>
            Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
            Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
        </p>
        <p>
            Suspendisse <s>et elit in enim tempus iaculis</s>.
        </p>
    </body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
* Утка по-пекински
    * Утка
    * Соус
* Салат Оливье
    1. Мясо
        * Или колбаса
    2. Майонез
    3. Картофель
    4. Что-то там ещё
* Помидоры
* Фрукты
    1. Бананы
    23. Яблоки
        1. Красные
        2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
  <body>
    <p>
      <ul>
        <li>
          Утка по-пекински
          <ul>
            <li>Утка</li>
            <li>Соус</li>
          </ul>
        </li>
        <li>
          Салат Оливье
          <ol>
            <li>Мясо
              <ul>
                <li>Или колбаса</li>
              </ul>
            </li>
            <li>Майонез</li>
            <li>Картофель</li>
            <li>Что-то там ещё</li>
          </ol>
        </li>
        <li>Помидоры</li>
        <li>Фрукты
          <ol>
            <li>Бананы</li>
            <li>Яблоки
              <ol>
                <li>Красные</li>
                <li>Зелёные</li>
              </ol>
            </li>
          </ol>
        </li>
      </ul>
    </p>
  </body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var s = ""
    var str1 = ""
    var prob = 0
    var nomstr = 0
    var mas: ArrayList<Int> = arrayListOf()
    var str: ArrayList<String> = arrayListOf()
    var spisok: ArrayList<Int> = arrayListOf()
    writer.write("<html><body><p>")
    for (line in File(inputName).readLines()) {
        for (i in line.indices) {
            if ((i > 1) && (line[i - 1] == ' ') && (line[i - 2] == '*')) s += line[i]
            if ((i > 2) && (line[i - 1] == ' ') && (line[i - 2] == '.') && (line[i - 3] in "123456789")) s += line[i]
            if ((s != "") && (s != line[i].toString())) s += line[i]
        }
        while (line[prob] == ' ') {
            prob++
        }
        if (line[prob] == '*') spisok.add(1)
        else spisok.add(0)
        str.add(s)
        mas.add(prob)
        nomstr++
        prob = 0
        s = ""
    }
    nomstr -= 1
    if (spisok[0] == 1) writer.write("<ul>")
    if (spisok[0] == 0) writer.write("<ol>")
    for (i in 0..nomstr) {
        writer.write("<li>")
        str1 = str[i]
        writer.write("$str1")
        if (((i != nomstr) && ((mas[i] - mas[i + 1] == 4) || (mas[i] == mas[i + 1]))) || (i == nomstr)) {
            writer.write("</li>")
        }
        if ((i != nomstr) && (mas[i + 1] < mas[i])) {
            var mas1 = mas[i]
            for (j in 1..(mas[i] - mas[i + 1]) / 4) {
                var l = 0
                mas1 -= 4
                for (k in i - 1 downTo 0) {
                    if (mas1 == mas[k]) {
                        l = k
                        break
                    }
                }
                if (spisok[l + 1] == 0) writer.write("</ol></li>")
                if (spisok[l + 1] == 1) writer.write("</ul></li>")
            }
        }
        if ((i == nomstr) && (0 < mas[i])) {
            var mas1 = mas[i]
            for (j in 1..mas[i] / 4) {
                var l = 0
                mas1 -= 4
                for (k in i - 1 downTo 0) {
                    if (mas1 == mas[k]) {
                        l = k
                        break
                    }
                }
                if ((l + 1 <= nomstr) && (spisok[l + 1] == 0)) writer.write("</ol></li>")
                if ((l + 1 <= nomstr) && (spisok[l + 1] == 1)) writer.write("</ul></li>")
            }
        }
        if ((i != nomstr) && (spisok[i + 1] == 1) && (mas[i] < mas[i + 1])) writer.write("<ul>")
        if ((i != nomstr) && (spisok[i + 1] == 0) && (mas[i] < mas[i + 1])) writer.write("<ol>")

    }
    if (spisok[0] == 1) writer.write("</ul>")
    if (spisok[0] == 0) writer.write("</ol>")
    writer.write("</p></body></html>")
    writer.close()
}


/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
   19935
*    111
--------
   19935
+ 19935
+19935
--------
 2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
  235
*  10
-----
    0
+235
-----
 2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
  19935 | 22
 -198     906
 ----
    13
    -0
    --
    135
   -132
   ----
      3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var vremenchastnoe = 0
    var pervoevychet = 0
    var m = 0
    var vychet = 0
    var poluch = 0
    var ostat = 0
    var ostat1 = 0
    var dl = 0
    var sled = 0
    val koltsifr = digitNumber(lhv)
    var k = koltsifr - 1
    if (lhv < rhv) {
        pervoevychet = (lhv / (10.0.pow(k))).toInt() // число доступное для деления
        vremenchastnoe = pervoevychet / rhv
    } else {
        while (vremenchastnoe < 1) { // промежуточное частное
            pervoevychet = (lhv / (10.0.pow(k))).toInt() // число доступное для деления
            vremenchastnoe = pervoevychet / rhv
            k--
        }
    }
    vychet = vremenchastnoe * rhv
    val pervayzifra = vychet / (10.0.pow((digitNumber(vychet) - 1))).toInt()
    val pervayzifra1 = lhv / (10.0.pow((digitNumber(lhv) - 1))).toInt()
    when {
        ((lhv / rhv < 10) && (digitNumber(vychet) < digitNumber(lhv))) -> {
            m = 1
            writer.write("$lhv | $rhv")    // первая строка
            writer.appendLine()
            for (i in 1 until digitNumber(lhv) - digitNumber(vychet)) {
                writer.write(" ")
                m++
            }
        }
        (pervayzifra > pervayzifra1) && (digitNumber(vychet) < digitNumber(lhv)) -> {
            writer.write("$lhv | $rhv")    // первая строка
            writer.appendLine()
            m = 1
        }
        else -> {
            writer.write(" $lhv | $rhv")    // первая строка
            writer.appendLine()
        }
    }
    writer.write("-$vychet")
    for (i in 1..koltsifr - digitNumber(vychet) - m + 3) writer.write(" ")
    poluch = lhv / rhv
    writer.write("$poluch")
    writer.appendLine()


    if ((lhv / rhv < 10) && (digitNumber(vychet) < digitNumber(lhv))) {
        for (i in 1..digitNumber(lhv)) writer.write("-")
        writer.appendLine()
    } else {
        for (i in 1..digitNumber(vychet) + 1) writer.write("-")
        writer.appendLine()
    }

    if ((lhv / rhv < 10) && (digitNumber(vychet) < digitNumber(lhv))) {
        ostat = lhv - vychet
    } else ostat = pervoevychet - vychet
    for (i in 1..digitNumber(vychet) + 1 - digitNumber(ostat)) {
        dl++
        writer.write(" ")
    }
    if (lhv / rhv < 10) {
        writer.write("$ostat")
    } else {
        writer.write("$ostat")
        ostat1 = ((lhv % 10.0.pow(koltsifr - digitNumber(pervoevychet))).toInt() /
                (10.0.pow(koltsifr - digitNumber(pervoevychet) - 1))).toInt()
        writer.write("$ostat1")
        writer.appendLine()
    }
    dl += digitNumber(ostat) + digitNumber(ostat1)
    ostat = ostat * 10 + ostat1
    sled = digitNumber(pervoevychet)
    if (lhv / rhv < 10) sled = 1000

    //---------------------------------------------------------

    var dl1 = 0
    var dl2 = 0
    while (koltsifr - sled > 0) {
        sled++
        vychet = (ostat / rhv) * rhv
        for (i in 1..dl - 1 - digitNumber(vychet)) {
            dl1++
            writer.write(" ")
        }
        writer.write("-$vychet")
        writer.appendLine()

        for (i in 1..min(dl1, dl - digitNumber(ostat))) writer.write(" ")
        for (i in 1..max(digitNumber(vychet) + 1, digitNumber(ostat))) writer.write("-")
        writer.appendLine()
        dl = 0

        ostat -= vychet
        for (i in 1..dl1 - digitNumber(ostat) + digitNumber(vychet) + 1) {
            dl++
            writer.write(" ")
        }
        writer.write("$ostat")
        when {
            (koltsifr - sled == 1) -> {
                ostat1 = lhv % (10.0.pow(koltsifr - sled)).toInt()
                writer.write("$ostat1")
                dl += digitNumber(ostat) + digitNumber(ostat1)
                ostat = ostat * 10 + ostat1
                writer.appendLine()
            }
            (koltsifr - sled == 0) -> {
                ostat1 = lhv % (10.0.pow(koltsifr - sled)).toInt()
            }
            else -> {
                ostat1 = (lhv % (10.0.pow(koltsifr - sled)).toInt() / (10.0.pow(koltsifr - sled - 1))).toInt()
                writer.write("$ostat1")
                dl += digitNumber(ostat) + digitNumber(ostat1)
                ostat = ostat * 10 + ostat1
                writer.appendLine()
            }
        }
        dl1 = 0
    }

    writer.close()

}














fun robot(inputName: String, moves: String): String {
    var asterisks = 0
    var x = 0
    var y = 0
    val freecells = mutableMapOf<Pair<Int, Int>, Int>()
    val text = File(inputName).readLines()
    for ((index1, line) in File(inputName).readLines().withIndex()) {
        if (!line.matches("""([.*#]*)""".toRegex()))
            throw IllegalArgumentException()
        for ((index, value) in line.withIndex()) {
            if (value == '*') {
                x = index + 1
                y = index1 + 1
                asterisks++
                freecells[Pair(x, y)] = 1
            }
            if (value == '.') {
                freecells[Pair(index1 + 1, index + 1)] = 1
            }
        }
        if ((index1 in text.indices) && (text[index1].length != text[0].length)) return "-1"
        if (asterisks > 1) return "-1"
    }
    if (!moves.matches("""[ldur]*""".toRegex()))
        throw IllegalArgumentException()
    for (i in moves.indices) {
        when {
            (moves[i] == 'l') && (freecells[Pair(y, x - 1)] == 1) -> x -= 1
            (moves[i] == 'r') && (freecells[Pair(y, x + 1)] == 1) -> x += 1
            (moves[i] == 'u') && (freecells[Pair(y - 1, x)] == 1) -> y -= 1
            (moves[i] == 'd') && (freecells[Pair(y + 1, x)] == 1) -> y += 1
        }
    }
    val y1 = text.size + 1 - y
    return "по горизонтали(x): $x, по вертикали(y): $y1"
}

















