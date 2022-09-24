package viach.apps.dicing.model

sealed interface Screen {
    val route: String

    abstract class Base(override val route: String) : Screen
    object Menu : Base("Menu")
    object PlayEasyGame : Base("Play Easy Game")
    object PlayNormalGame : Base("Play Normal Game")
    object PlayHardGame : Base("Play Hard Game")
    object TwoPlayersGame : Base("Two Players Game")
    object Rules : Base("Rules")
}