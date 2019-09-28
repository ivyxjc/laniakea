package xyz.ivyxjc.laniakea

import okio.buffer
import okio.sink
import org.junit.Test
import java.io.File

class LaniakeaTest {

    @Test
    fun test() {
        val list = List(2) {
            buildRandomBean()
        }
        Laniakea.reportTitle("g1", 12112212, "Testcase123456")
        Laniakea.reportData("g1", 12112212, "Hello", list, "tablename")
        Laniakea.reportSuccess("g1", 12112212, "Enqueu one message", null)

        Laniakea.reportTitle(null, 12112212, "Testcase23456")
        Laniakea.reportData(null, 12112222, "Hello", list, "tablename")
        Laniakea.reportSuccess("g1", 12112222, "Enqueu one message", null)

        Laniakea.reportTitle(null, 22112212, "Testcase23456")
        Laniakea.reportData(null, 22112222, "Hello", list, "tablename")
        Laniakea.reportSuccess("g2", 22112222, "Enqueu one message", null)

        val res = Laniakea.showReport()
        res.forEach { (k, u) ->
            val file = File("H:\\IVY\\temp\\laniakea\\$k.html")
            val fileSink = file.sink()
            val bufferSink = fileSink.buffer()
            bufferSink.writeUtf8(u)
            bufferSink.flush()
        }


    }


}