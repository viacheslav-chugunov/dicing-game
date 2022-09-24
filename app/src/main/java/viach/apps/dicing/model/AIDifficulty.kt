package viach.apps.dicing.model

import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named

enum class AIDifficulty(
    val qualifier: StringQualifier
) {
    EASY(named("Easy")),
    NORMAL(named("Normal")),
    HARD(named("Hard"))
}