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
        Laniakea.reportData(12112212, "Hello", list, "tablename")
        Laniakea.reportSuccess(12112212, "Enqueu one message", null)
        val res = Laniakea.showReport(12112212)
        val file = File("H:\\IVY\\temp\\bcd.html")
        val fileSink = file.sink()
        val bufferSink = fileSink.buffer()
        bufferSink.writeUtf8(res)
        bufferSink.flush()
    }


}