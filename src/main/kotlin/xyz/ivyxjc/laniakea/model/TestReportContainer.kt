package xyz.ivyxjc.laniakea.model

class TestReportContainer {
    private val list = mutableListOf<StepReportContainer>()

    fun addStepReport(stepReportContainer: StepReportContainer) {
        list.add(stepReportContainer)
    }

    fun showReport(): String {
        val res = StringBuilder();
        list.forEach {
            res.append(it.buildHtml() + "\n")
        }

        return res.toString()
    }
}