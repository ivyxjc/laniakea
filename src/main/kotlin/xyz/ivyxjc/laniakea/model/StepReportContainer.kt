package xyz.ivyxjc.laniakea.model


interface StepReportContainer {
    fun buildHtml(): String
}

class DefaultStepReportContainer(val desc: String, val status: Boolean?, val detail: String?) : StepReportContainer {

    private val reportWithDetailTemplate: String = """
           <div class="card">
        <div class="card-header ${'$'}{STATUS}">
            <p>${'$'}{CARD_TITLE}</p>
        </div>
        <div class="card-body">
            <p class="card-text">${'$'}{CARD_TEXT}</p>
        </div>
    </div> 
    """.trimIndent()

    private val reportWithoutDetailTemplate: String = """
           <div class="card">
        <div class="card-header ${'$'}{STATUS}">
            <p>${'$'}{CARD_TITLE}</p>
        </div>
    </div> 
    """.trimIndent()

    override fun buildHtml(): String {
        val statusTmp = when {
            status == null -> "status_pure"
            status -> "status_true"
            else -> "status_false"
        }
        return if (detail != null) {
            var res = reportWithDetailTemplate
            res = res.replace("\${STATUS}", statusTmp)
            res = res.replace("\${CARD_TITLE}", desc)
            res = res.replace("\${CARD_TEXT}", detail)
            res
        } else {
            var res = reportWithoutDetailTemplate
            res = res.replace("\${STATUS}", statusTmp)
            res = res.replace("\${CARD_TITLE}", desc)
            res
        }

    }
}