package xyz.ivyxjc.laniakea

import xyz.ivyxjc.coracias.Coracias
import xyz.ivyxjc.laniakea.model.GroupReportContainer
import xyz.ivyxjc.laniakea.model.StepStatus.*


class Laniakea {
    companion object {
        private val coracias = Coracias.Builder<Any>()
            .build()
        private val map = mutableMapOf<String, GroupReportContainer>()

        @JvmStatic
        fun reportTitle(group: String, id: Long, title: String) {
            map.putIfAbsent(group, GroupReportContainer(group))
            map[group]!!.addReport(id, Title, title, null)
        }

        @JvmStatic
        fun reportSuccess(group: String, id: Long, desc: String, detail: String?) {
            map.putIfAbsent(group, GroupReportContainer(group))
            map[group]!!.addReport(id, Correct, desc, detail)
        }

        @JvmStatic
        fun reportFail(group: String, id: Long, desc: String, detail: String?) {
            map.putIfAbsent(group, GroupReportContainer(group))
            map[group]!!.addReport(id, Wrong, desc, detail)
        }

        @JvmStatic
        fun report(group: String, id: Long, desc: String, detail: String?) {
            map.putIfAbsent(group, GroupReportContainer(group))
            map[group]!!.addReport(id, Pure, desc, detail)
        }

        @JvmStatic
        fun reportData(group: String, id: Long, desc: String, data: List<Any>, tableName: String) {
            map.putIfAbsent(group, GroupReportContainer(group))
            map[group]!!.addReport(id, Pure, desc, coracias.format(data, tableName))
        }

        @JvmStatic
        fun reportData(
            group: String,
            id: Long,
            desc: String,
            data: List<Any>,
            tableName: String,
            columnNames: List<String>
        ) {
            map.putIfAbsent(group, GroupReportContainer(group))
            map[group]!!.addReport(id, Pure, desc, coracias.format(data, tableName, columnNames))
        }

        @JvmStatic
        fun showReport(group: String): String {
            val res = map[group]
            if (res == null) {
                throw RuntimeException("There is no data about group: $group")
            } else {
                return LANIAKEA_CONTAINER.replace("\${BODY}", res.showReport())
            }
        }
    }


}