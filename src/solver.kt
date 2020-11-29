// All Directions
val X = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
val Y = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

fun search(
    grid: Array<String>,
    row: Int, col: Int,
    word: String,
    C: Int,
    R: Int
): Pair<Boolean, MutableList<Pair<Int, Int>>> {
    if (grid[row][col] != word[0]) return Pair(false, mutableListOf())

    val len: Int = word.length
    for (dir in 0..7) {
        var rd: Int = row + X[dir]
        var cd: Int = col + Y[dir]

        val locs: MutableList<Pair<Int, Int>> = mutableListOf()

        var k = 1
        while (k < len) {

            // If out of bound break
            if (rd >= R || rd < 0 || cd >= C || cd < 0) break

            // If not matched, break
            if (grid[rd][cd] != word[k]) break

            locs.add(Pair(rd, cd))

            rd += X[dir]
            cd += Y[dir]
            k++
        }

        if (k == len) return Pair(true, locs)
    }
    return Pair(false, mutableListOf())
}

fun findWord(
    grid: Array<String>,
    word: String
) {
    val c = grid[0].length
    val r = grid.size
    for (row in 0 until r) {
        for (col in 0 until c) {
            val res = search(grid, row, col, word.toUpperCase(), c, r)
            res.second.add(Pair(row, col))
            if (res.first) println(pretty(grid, res.second))
        }
    }
}

fun pretty(words: Array<String>, locs: MutableList<Pair<Int, Int>>): String {
    val res = words.withIndex().map { (row, it) ->
        it.toCharArray().withIndex().joinToString(" ") { (i, c) -> if (Pair(row, i) in locs) "[$c]" else " $c " }
    }

    return res.joinToString("\n")
}

fun main() {
    val words =
        arrayOf(
            "ESIVSHEZWLPKGPZLAP",
            "TTNRKRRVLEUOCRDZMA",
            "AUYENJEAWJFETWALFT",
            "RFCLOZBVWPDRAAPVHV",
            "BFPBITENOBHSIWTAYE",
            "EIIBOMGRATEFULNOPX",
            "LNQOBNAQROFTNKTIED",
            "EGFGYEPFFVREFCCEDS",
            "CZZINTURKEYULERURO",
            "WRCNODGNDNLQRDTCGA"
        )
    findWord(words, "potatoes")
}
