package xyz.ivyxjc.laniakea

import xyz.ivyxjc.coracias.Coracias
import xyz.ivyxjc.laniakea.model.DefaultStepReportContainer
import xyz.ivyxjc.laniakea.model.GroupReportContainer
import xyz.ivyxjc.laniakea.model.StepStatus.*
import xyz.ivyxjc.laniakea.model.TestReportContainer


class Laniakea {
    companion object {
        private val coracias = Coracias.Builder<Any>()
            .build()
        //        private val map = mutableMapOf<String, GroupReportContainer>()
        private val map = mutableMapOf<Long, TestReportContainer>()

        @JvmStatic
        fun reportTitle(group: String?, id: Long, title: String) {
            map.putIfAbsent(id, TestReportContainer())
            map[id]!!.addStepReport(DefaultStepReportContainer(group, Title, title, null))
        }

        @JvmStatic
        fun reportSuccess(group: String?, id: Long, desc: String, detail: String?) {
            map.putIfAbsent(id, TestReportContainer())
            map[id]!!.addStepReport(DefaultStepReportContainer(group, Correct, desc, detail))
        }

        @JvmStatic
        fun reportFail(group: String?, id: Long, desc: String, detail: String?) {
            map.putIfAbsent(id, TestReportContainer())
            map[id]!!.addStepReport(DefaultStepReportContainer(group, Wrong, desc, detail))
        }

        @JvmStatic
        fun report(group: String?, id: Long, desc: String, detail: String?) {
            map.putIfAbsent(id, TestReportContainer())
            map[id]!!.addStepReport(DefaultStepReportContainer(group, Pure, desc, detail))
        }

        @JvmStatic
        fun reportData(group: String?, id: Long, desc: String, data: List<Any>, tableName: String) {
            map.putIfAbsent(id, TestReportContainer())
            map[id]!!.addStepReport(DefaultStepReportContainer(group, Pure, desc, coracias.format(data, tableName)))
        }

        @JvmStatic
        fun reportData(
            group: String?,
            id: Long,
            desc: String,
            data: List<Any>,
            tableName: String,
            columnNames: List<String>
        ) {
            map.putIfAbsent(id, TestReportContainer())
            map[id]!!.addStepReport(
                DefaultStepReportContainer(
                    group,
                    Pure,
                    desc,
                    coracias.format(data, tableName, columnNames)
                )
            )
        }

        @JvmStatic
        fun showReport(group: String): String {
            val map = getGroupReport()
            val data = map[group]
            if (data == null) {
                throw RuntimeException("There is no data about group: $group")
            } else {
                return LANIAKEA_CONTAINER.replace("\${BODY}", data.showReport())
            }
        }

        @JvmStatic
        fun showReport(): Map<String, String> {
            val res = mutableMapOf<String, String>()
            val map = getGroupReport()
            map.forEach { (k, u) ->
                val oneTestRes = LANIAKEA_CONTAINER.replace("\${TITLE}", k)
                    .replace("\${BODY}", u.showReport())
                res[k] = oneTestRes
            }
            return res
        }

        @JvmStatic
        private fun getGroupReport(): Map<String, GroupReportContainer> {
            val res = mutableMapOf<String, GroupReportContainer>()
            map.forEach { (k, u) ->
                val group = u.getGroup()
                res.putIfAbsent(group, GroupReportContainer(group))
                res[group]!!.addReport(k, u)
            }
            return res
        }
    }
}