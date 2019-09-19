package xyz.ivyxjc.laniakea.model

internal class GroupReportContainer(val groupName: String) {
    private val map = mutableMapOf<Long, TestReportContainer>()

    fun addReport(id: Long, status: StepStatus, desc: String, detail: String?) {
        map.putIfAbsent(id, TestReportContainer())
        map[id]!!.addStepReport(DefaultStepReportContainer(desc, status, detail))
    }

    fun showReport(): String {
        val res = StringBuilder()
        res.append(
            """
            <div>
                <h1>${'$'}{GROUP}</h1>
            </div> 
        """.trimIndent()
        )
        if (map.isEmpty()) {
            throw RuntimeException("There is no data about group: $groupName")
        }
        map.forEach { (k, v) ->
            res.append(v.showReport(k))
            res.append("<br />")
        }
        return res.toString().replace("\${GROUP}", groupName)
    }

}