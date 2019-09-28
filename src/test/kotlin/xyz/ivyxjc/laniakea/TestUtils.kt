package xyz.ivyxjc.laniakea

import org.apache.commons.lang3.RandomStringUtils
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.abs
import kotlin.random.Random


fun buildRandomBean(): DataBean {
    val res = DataBean()
    res.guid = abs(Random.nextLong())
    res.key = RandomStringUtils.random(8)
    res.value = """
        <abc>
            <hello>
                world
            </hello>
        </abc>
    """.trimIndent()
    res.tradeDate = LocalDateTime.now()
    res.cancelDate = LocalDate.now()
    res.officeCode = RandomStringUtils.random(20)
    return res
}



