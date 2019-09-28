package xyz.ivyxjc.laniakea.model

internal class TestReportContainer {
    private val list = mutableListOf<StepReportContainer>()

    fun getGroup(): String {
        var group = ""
        list.forEach {
            val tmpGroupName = it.getGroup()
            if (tmpGroupName != null) {
                if (group == "") {
                    group = tmpGroupName
                } else if (group != tmpGroupName) {
                    throw RuntimeException("Test step with same id should has same group name")
                }
            }
        }
        return group
    }

    fun addStepReport(stepReportContainer: StepReportContainer) {
        list.add(stepReportContainer)
    }

    fun showReport(id: Long): String {
        val res = StringBuilder();
        list.forEachIndexed { index, it ->
            res.append(it.buildHtml(id, index) + "\n")
        }

        return res.toString()
    }
}