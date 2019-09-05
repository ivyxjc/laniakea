package xyz.ivyxjc.laniakea

import xyz.ivyxjc.coracias.Coracias
import xyz.ivyxjc.laniakea.model.DefaultStepReportContainer
import xyz.ivyxjc.laniakea.model.TestReportContainer


class Laniakea {
    companion object {

        private val coracias = Coracias.Builder<Any>()
            .build()
        private val map = mutableMapOf<Long, TestReportContainer>()

        @JvmStatic
        fun reportSuccess(id: Long, desc: String, detail: String?) {
            map.putIfAbsent(id, TestReportContainer())
            map[id]!!.addStepReport(DefaultStepReportContainer(desc, true, detail))

        }

        @JvmStatic
        fun reportFail(id: Long, desc: String, detail: String?) {
            map.putIfAbsent(id, TestReportContainer())
            map[id]!!.addStepReport(DefaultStepReportContainer(desc, false, detail))
        }

        @JvmStatic
        fun report(id: Long, desc: String, detail: String?) {
            map.putIfAbsent(id, TestReportContainer())
            map[id]!!.addStepReport(DefaultStepReportContainer(desc, null, detail))
        }

        @JvmStatic
        fun reportData(id: Long, desc: String, data: List<Any>, tableName: String) {
            map.putIfAbsent(id, TestReportContainer())
            map[id]!!.addStepReport(DefaultStepReportContainer(desc, null, coracias.format(data, tableName)))
        }

        @JvmStatic
        fun reportData(id: Long, desc: String, data: List<Any>, tableName: String, columnNames: List<String>) {
            map.putIfAbsent(id, TestReportContainer())
            map[id]!!.addStepReport(
                DefaultStepReportContainer(
                    desc,
                    null,
                    coracias.format(data, tableName, columnNames)
                )
            )
        }

        @JvmStatic
        fun showReport(id: Long): String {
            val res = map[id]
            if (res == null) {
                throw RuntimeException("fail to get report by leaftId: $id")
            } else {
                return LANIAKEA_CONTAINER.replace("\${BODY}", res.showReport())
            }
        }
    }


}