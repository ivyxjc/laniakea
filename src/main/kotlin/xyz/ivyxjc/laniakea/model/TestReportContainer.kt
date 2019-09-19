package xyz.ivyxjc.laniakea.model

internal class TestReportContainer {
    private val list = mutableListOf<StepReportContainer>()

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