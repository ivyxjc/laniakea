package xyz.ivyxjc.laniakea.model

import xyz.ivyxjc.laniakea.model.StepStatus.*


internal interface StepReportContainer {
    fun buildHtml(id: Long, index: Int): String
}

internal class DefaultStepReportContainer(val desc: String, val status: StepStatus, val detail: String?) :
    StepReportContainer {

    private val reportWithDetailTemplate: String = """
        <div class="card">
            <div class="card-header ${'$'}{STATUS}" id="headingOne">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#data${'$'}{ID-INDEX}" aria-expanded="true"
                        aria-controls="collapseOne">
                        ${'$'}{CARD_TITLE}
                    </button>
                </h5>
            </div>
            <div id="data${'$'}{ID-INDEX}" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                <div class="card-body">
                        ${'$'}{CARD_TEXT}
                </div>
            </div>
        </div>
    """.trimIndent()

    private val reportWithoutDetailTemplate: String = """
        <div class="card">
            <div class="card-header ${'$'}{STATUS}" id="headingOne">
                <h5 class="mb-0">
                    ${'$'}{CARD_TITLE}
                </h5>
            </div>
        </div>
    """.trimIndent()

    override fun buildHtml(id: Long, index: Int): String {
        val statusTmp = when (status) {
            Pure -> "laniakea-status-pure"
            Correct -> "laniakea-status-success"
            Wrong -> "laniakea-status-fail"
            Title -> "laniakea-step-title"
        }
        return if (detail != null) {
            var res = reportWithDetailTemplate
            res = res.replace("\${STATUS}", statusTmp)
            res = res.replace("\${CARD_TITLE}", desc)
            res = res.replace("\${CARD_TEXT}", detail)
            res = res.replace("\${ID-INDEX}", id.toString() + index.toString())
            res
        } else {
            var res = reportWithoutDetailTemplate
            res = res.replace("\${STATUS}", statusTmp)
            res = res.replace("\${CARD_TITLE}", desc)
            res = res.replace("\${ID-INDEX}", id.toString() + index.toString())
            res
        }

    }
}