package xyz.ivyxjc.laniakea.model

internal class GroupReportContainer(val groupName: String) {
    private val map = mutableMapOf<Long, TestReportContainer>()

    fun addReport(id: Long, testReportContainer: TestReportContainer) {
        map.putIfAbsent(id, testReportContainer)
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