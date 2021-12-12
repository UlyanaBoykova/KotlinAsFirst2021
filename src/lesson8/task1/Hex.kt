@file:Suppress("UNUSED_PARAMETER")

package lesson8.task1

import kotlin.math.*

/**
 * Точка (гекс) на шестиугольной сетке.
 * Координаты заданы как в примере (первая цифра - y, вторая цифра - x)
 *
 *       60  61  62  63  64  65
 *     50  51  52  53  54  55  56
 *   40  41  42  43  44  45  46  47
 * 30  31  32  33  34  35  36  37  38
 *   21  22  23  24  25  26  27  28
 *     12  13  14  15  16  17  18
 *       03  04  05  06  07  08
 *
 * В примерах к задачам используются те же обозначения точек,
 * к примеру, 16 соответствует HexPoint(x = 6, y = 1), а 41 -- HexPoint(x = 1, y = 4).
 *
 * В задачах, работающих с шестиугольниками на сетке, считать, что они имеют
 * _плоскую_ ориентацию:
 *  __
 * /  \
 * \__/
 *
 * со сторонами, параллельными координатным осям сетки.
 *
 * Более подробно про шестиугольные системы координат можно почитать по следующей ссылке:
 *   https://www.redblobgames.com/grids/hexagons/
 */
data class HexPoint(var x: Int, var y: Int) {
    /**
     * Средняя (3 балла)
     *
     * Найти целочисленное расстояние между двумя гексами сетки.
     * Расстояние вычисляется как число единичных отрезков в пути между двумя гексами.
     * Например, путь межу гексами 16 и 41 (см. выше) может проходить через 25, 34, 43 и 42 и имеет длину 5.
     */
    fun distance(other: HexPoint): Int = TODO()

    override fun toString(): String = "$y.$x"
}

/**
 * Правильный шестиугольник на гексагональной сетке.
 * Как окружность на плоскости, задаётся центральным гексом и радиусом.
 * Например, шестиугольник с центром в 33 и радиусом 1 состоит из гексов 42, 43, 34, 24, 23, 32.
 */
data class Hexagon(val center: HexPoint, val radius: Int) {

    /**
     * Средняя (3 балла)
     *
     * Рассчитать расстояние между двумя шестиугольниками.
     * Оно равно расстоянию между ближайшими точками этих шестиугольников,
     * или 0, если шестиугольники имеют общую точку.
     *
     * Например, расстояние между шестиугольником A с центром в 31 и радиусом 1
     * и другим шестиугольником B с центром в 26 и радиуоом 2 равно 2
     * (расстояние между точками 32 и 24)
     */
    fun distance(other: Hexagon): Int = TODO()

    /**
     * Тривиальная (1 балл)
     *
     * Вернуть true, если заданная точка находится внутри или на границе шестиугольника
     */
    fun contains(point: HexPoint): Boolean = TODO()
}

/**
 * Прямолинейный отрезок между двумя гексами
 */
class HexSegment(val begin: HexPoint, val end: HexPoint) {
    /**
     * Простая (2 балла)
     *
     * Определить "правильность" отрезка.
     * "Правильным" считается только отрезок, проходящий параллельно одной из трёх осей шестиугольника.
     * Такими являются, например, отрезок 30-34 (горизонталь), 13-63 (прямая диагональ) или 51-24 (косая диагональ).
     * А, например, 13-26 не является "правильным" отрезком.
     */
    fun isValid(): Boolean = TODO()

    /**
     * Средняя (3 балла)
     *
     * Вернуть направление отрезка (см. описание класса Direction ниже).
     * Для "правильного" отрезка выбирается одно из первых шести направлений,
     * для "неправильного" -- INCORRECT.
     */
    fun direction(): Direction = TODO()

    override fun equals(other: Any?) =
        other is HexSegment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

    override fun hashCode() =
        begin.hashCode() + end.hashCode()
}

/**
 * Направление отрезка на гексагональной сетке.
 * Если отрезок "правильный", то он проходит вдоль одной из трёх осей шестугольника.
 * Если нет, его направление считается INCORRECT
 */
enum class Direction {
    RIGHT,      // слева направо, например 30 -> 34
    UP_RIGHT,   // вверх-вправо, например 32 -> 62
    UP_LEFT,    // вверх-влево, например 25 -> 61
    LEFT,       // справа налево, например 34 -> 30
    DOWN_LEFT,  // вниз-влево, например 62 -> 32
    DOWN_RIGHT, // вниз-вправо, например 61 -> 25
    INCORRECT;  // отрезок имеет изгиб, например 30 -> 55 (изгиб в точке 35)

    /**
     * Простая (2 балла)
     *
     * Вернуть направление, противоположное данному.
     * Для INCORRECT вернуть INCORRECT
     */
    fun opposite(): Direction = TODO()

    /**
     * Средняя (3 балла)
     *
     * Вернуть направление, повёрнутое относительно
     * заданного на 60 градусов против часовой стрелки.
     *
     * Например, для RIGHT это UP_RIGHT, для UP_LEFT это LEFT, для LEFT это DOWN_LEFT.
     * Для направления INCORRECT бросить исключение IllegalArgumentException.
     * При решении этой задачи попробуйте обойтись без перечисления всех семи вариантов.
     */
    fun next(): Direction = TODO()

    /**
     * Простая (2 балла)
     *
     * Вернуть true, если данное направление совпадает с other или противоположно ему.
     * INCORRECT не параллельно никакому направлению, в том числе другому INCORRECT.
     */
    fun isParallel(other: Direction): Boolean = TODO()
}

/**
 * Средняя (3 балла)
 *
 * Сдвинуть точку в направлении direction на расстояние distance.
 * Бросить IllegalArgumentException(), если задано направление INCORRECT.
 * Для расстояния 0 и направления не INCORRECT вернуть ту же точку.
 * Для отрицательного расстояния сдвинуть точку в противоположном направлении на -distance.
 *
 * Примеры:
 * 30, direction = RIGHT, distance = 3 --> 33
 * 35, direction = UP_LEFT, distance = 2 --> 53
 * 45, direction = DOWN_LEFT, distance = 4 --> 05
 */
fun HexPoint.move(direction: Direction, distance: Int): HexPoint = TODO()

/**
 * Сложная (5 баллов)
 *
 * Найти кратчайший путь между двумя заданными гексами, представленный в виде списка всех гексов,
 * которые входят в этот путь.
 * Начальный и конечный гекс также входят в данный список.
 * Если кратчайших путей существует несколько, вернуть любой из них.
 *
 * Пример (для координатной сетки из примера в начале файла):
 *   pathBetweenHexes(HexPoint(y = 2, x = 2), HexPoint(y = 5, x = 3)) ->
 *     listOf(
 *       HexPoint(y = 2, x = 2),
 *       HexPoint(y = 2, x = 3),
 *       HexPoint(y = 3, x = 3),
 *       HexPoint(y = 4, x = 3),
 *       HexPoint(y = 5, x = 3)
 *     )
 */
fun pathBetweenHexes(from: HexPoint, to: HexPoint): List<HexPoint> = TODO()

/**
 * Очень сложная (20 баллов)
 *
 * Дано три точки (гекса). Построить правильный шестиугольник, проходящий через них
 * (все три точки должны лежать НА ГРАНИЦЕ, а не ВНУТРИ, шестиугольника).
 * Все стороны шестиугольника должны являться "правильными" отрезками.
 * Вернуть null, если такой шестиугольник построить невозможно.
 * Если шестиугольников существует более одного, выбрать имеющий минимальный радиус.
 *
 * Пример: через точки 13, 32 и 44 проходит правильный шестиугольник с центром в 24 и радиусом 2.
 * Для точек 13, 32 и 45 такого шестиугольника не существует.
 * Для точек 32, 33 и 35 следует вернуть шестиугольник радиусом 3 (с центром в 62 или 05).
 *
 * Если все три точки совпадают, вернуть шестиугольник нулевого радиуса с центром в данной точке.
 */
fun hexagonByThreePoints(a: HexPoint, b: HexPoint, c: HexPoint): Hexagon? {
    var a11 = 0
    var b11 = 0
    var radius1 = 0
    val maxx1 = max(a.x, max(b.x, c.x))
    val minx1 = min(a.x, min(b.x, c.x))
    val maxy1 = max(a.y, max(b.y, c.y))
    val miny1 = min(a.y, min(b.y, c.y))
    val rasnizamax =
        max(max(a.y, max(b.y, c.y)) - min(a.y, min(b.y, c.y)), max(a.x, max(b.x, c.x)) - min(a.x, min(b.x, c.x)))
    val rasnizamin = min(
        min(abs(a.x - b.x), min(abs(a.x - c.x), abs(b.x - c.x))),
        min(abs(a.y - b.y), min(abs(a.y - c.y), abs(b.y - c.y)))
    )
    when {
        ((a == b) && (b == c)) -> return Hexagon(a, 0)
        (((a.x == b.x) && (b.x == c.x))) -> {
            radius1 = max(a.y, max(b.y, c.y)) - min(a.y, min(b.y, c.y))
            b11 = min(a.y, min(b.y, c.y))
            a11 = a.x + radius1
            return Hexagon(HexPoint(a11, b11), radius1)
        }
        (((a.y == b.y) && (b.y == c.y))) -> {
            radius1 = max(a.x, max(b.x, c.x)) - min(a.x, min(b.x, c.x))
            b11 = a.y + radius1
            a11 = min(a.x, min(b.x, c.x))
            return Hexagon(HexPoint(a11, b11), radius1)
        }
        else
        -> {
            for (radius in rasnizamin..rasnizamax) {
                for (b1 in maxy1 - radius..miny1 + radius) {
                    for (a1 in maxx1 - radius..minx1 + radius) {
                        if ((((a.y == -a.x + b1 + a1 + radius) && (a.x in a1..a1 + radius)) ||
                                    ((a.y == -a.x + b1 + a1 - radius) && (a.x in a1 - radius..a1)) ||
                                    ((a.y == b1 - radius) && (a.x in a1..a1 + radius)) ||
                                    ((a.y == b1 + radius) && (a.x in a1 - radius..a1)) ||
                                    ((a.x == a1 - radius) && (a.y in b1..b1 + radius)) ||
                                    ((a.x == a1 + radius) && (a.y in b1 - radius..b1))) &&

                            (((b.y == -b.x + b1 + a1 + radius) && (b.x in a1..a1 + radius)) ||
                                    ((b.y == -b.x + b1 + a1 - radius) && (b.x in a1 - radius..a1)) ||
                                    ((b.y == b1 - radius) && (b.x in a1..a1 + radius)) ||
                                    ((b.y == b1 + radius) && (b.x in a1 - radius..a1)) ||
                                    ((b.x == a1 - radius) && (b.y in b1..b1 + radius)) ||
                                    ((b.x == a1 + radius) && (b.y in b1 - radius..b1))) &&

                            (((c.y == -c.x + b1 + a1 + radius) && (c.x in a1..a1 + radius)) ||
                                    ((c.y == -c.x + b1 + a1 + radius) && (c.x in a1 - radius..a1)) ||
                                    ((c.y == b1 - radius) && (c.x in a1..a1 + radius)) ||
                                    ((c.y == b1 + radius) && (c.x in a1 - radius..a1)) ||
                                    ((c.x == a1 - radius) && (c.y in b1..b1 + radius)) ||
                                    ((c.x == a1 + radius) && (c.y in b1 - radius..b1)))
                        ) {
                            a11 = a1
                            b11 = b1
                            radius1 = radius
                            return Hexagon(HexPoint(a11, b11), radius1)
                        }
                    }
                }
            }
            return null
        }
    }
}

/**
 * Очень сложная (20 баллов)
 *
 * Дано множество точек (гексов). Найти правильный шестиугольник минимального радиуса,
 * содержащий все эти точки (безразлично, внутри или на границе).
 * Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит один гекс, вернуть шестиугольник нулевого радиуса с центром в данной точке.
 *
 * Пример: 13, 32, 45, 18 -- шестиугольник радиусом 3 (с центром, например, в 15)
 */
fun minContainingHexagon(vararg points: HexPoint): Hexagon = TODO()



