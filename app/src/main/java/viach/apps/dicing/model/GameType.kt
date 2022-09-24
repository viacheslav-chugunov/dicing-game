package viach.apps.dicing.model

import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named

enum class GameType(
    val qualifier: StringQualifier
) {
    USER_VS_AI(named("User VS AI")),
    USER_VS_USER(named("User VS User"))
}